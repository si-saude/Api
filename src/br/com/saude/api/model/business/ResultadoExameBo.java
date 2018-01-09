package br.com.saude.api.model.business;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.business.validate.ResultadoExameValidator;
import br.com.saude.api.model.creation.builder.entity.ConvocacaoBuilder;
import br.com.saude.api.model.creation.builder.entity.ResultadoExameBuilder;
import br.com.saude.api.model.creation.builder.example.ConvocacaoExampleBuilder;
import br.com.saude.api.model.creation.builder.example.EmpregadoExampleBuilder;
import br.com.saude.api.model.creation.builder.example.ExameExampleBuilder;
import br.com.saude.api.model.creation.builder.example.ProfissiogramaExampleBuilder;
import br.com.saude.api.model.creation.builder.example.ResultadoExameExampleBuilder;
import br.com.saude.api.model.entity.filter.ConvocacaoFilter;
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.filter.ExameFilter;
import br.com.saude.api.model.entity.filter.ProfissiogramaFilter;
import br.com.saude.api.model.entity.filter.ResultadoExameFilter;
import br.com.saude.api.model.entity.imports.ResultadoExameImport;
import br.com.saude.api.model.entity.po.Convocacao;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.EmpregadoConvocacao;
import br.com.saude.api.model.entity.po.Exame;
import br.com.saude.api.model.entity.po.Profissiograma;
import br.com.saude.api.model.entity.po.ResultadoExame;
import br.com.saude.api.model.persistence.ConvocacaoDao;
import br.com.saude.api.model.persistence.EmpregadoDao;
import br.com.saude.api.model.persistence.ExameDao;
import br.com.saude.api.model.persistence.ProfissiogramaDao;
import br.com.saude.api.model.persistence.ResultadoExameDao;

public class ResultadoExameBo extends
		GenericBo<ResultadoExame, ResultadoExameFilter, ResultadoExameDao, ResultadoExameBuilder, ResultadoExameExampleBuilder> {
	private static ResultadoExameBo instance;

	private ResultadoExameBo() {
		super();
	}

	public static ResultadoExameBo getInstance() {
		if (instance == null)
			instance = new ResultadoExameBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoad = builder -> {
			return builder;
		};
	}

	public ResultadoExame getUltimoByEmpregadoExame(ResultadoExame resultadoExame) throws Exception {
		return getDao().getUltimoByEmpregadoExame(resultadoExame);
	}

	@SuppressWarnings("unlikely-arg-type")
	public String importFile(ResultadoExameImport arquivo) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		List<ResultadoExame> resultadoExames = new ArrayList<ResultadoExame>();
		List<Convocacao> convocacoes = new ArrayList<Convocacao>();
		List<Convocacao> convocacoesPersistencia = new ArrayList<Convocacao>();
		List<Empregado> empregados = new ArrayList<Empregado>();
		List<Exame> exames = new ArrayList<Exame>();
		List<EmpregadoConvocacao> empregadoConvocacoesPersistencia = new ArrayList<EmpregadoConvocacao>();
		String matriculasNaoCadastradas = "";

		try {
			byte[] arquivoArray = new byte[arquivo.getArquivo().size()];

			for (int i = 0; i < arquivo.getArquivo().size(); i++) {
				arquivoArray[i] = new Integer(arquivo.getArquivo().get(i + "")).byteValue();
			}

			InputStream in = new ByteArrayInputStream(arquivoArray);
			@SuppressWarnings("resource")
			Workbook workbook = new XSSFWorkbook(in);

			XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
			System.out.println(sheet.toString());
			for (int r = 0; r < 4; r++) {
				if (r == 0)
					continue;

				String tipo = sheet.getRow(r).getCell(4).getStringCellValue();
				Date cellDate = sheet.getRow(r).getCell(9).getDateCellValue();
				@SuppressWarnings("deprecation")
				String ano = String.valueOf(arquivo.getFim().getYear());
				ano = "20" + ano.substring(1, ano.length());
				String titulo = tipo + " " + ano;
				String matricula = String.valueOf(sheet.getRow(r).getCell(3).getStringCellValue());
				String codigoExame = sheet.getRow(r).getCell(7).getStringCellValue();
				String acao = "";
				try {
					acao = sheet.getRow(r).getCell(8).getStringCellValue();
				} catch (NullPointerException npe) {
					acao = "";
				}
				String local = sheet.getRow(r).getCell(10).getStringCellValue();
				String tipoResultadoExame = sheet.getRow(r).getCell(5).getStringCellValue();

				Profissiograma profissiograma = ProfissiogramaDao.getInstance().getFirst(
						ProfissiogramaExampleBuilder.newInstance(new ProfissiogramaFilter()).example().getCriterions());
				if (profissiograma == null)
					return "";

				Empregado empregado = null;
				List<Empregado> es = new ArrayList<Empregado>();
				es = empregados.stream().filter(e -> e.getMatricula().equals(matricula)).collect(Collectors.toList());

				if (es.size() == 0) {
					es = fetchEmpregadoByMatricula(matricula);
					if (es == null || es.size() == 0) {
						if ( !matriculasNaoCadastradas.contains(matricula) )
							matriculasNaoCadastradas += matricula + ", ";
						continue;
					} else {
						System.out.println(matricula);
						empregado = es.get(0);
						empregados.add(es.get(0));
					}
				} else
					empregado = es.get(0);

				Exame exame = null;
				List<Exame> exs = new ArrayList<Exame>();
				exs = exames.stream().filter(e -> e.getCodigo().equals(codigoExame)).collect(Collectors.toList());

				if (exs.size() == 0) {
					exs = fetchExameByCodigo(codigoExame);
					if (exs == null || exs.size() == 0) {
						continue;
					} else
						exame = exs.get(0);
					exames.add(exs.get(0));
				} else
					exame = exs.get(0);

				Convocacao convocacao = null;
				List<Convocacao> cs = new ArrayList<Convocacao>();
				cs = convocacoes.stream().filter(g -> g.getTitulo().equals(titulo)).collect(Collectors.toList());
				if (cs.size() == 0) {
					cs = fetchConvocacaoByTitulo(titulo);
					if (cs == null) {
						Convocacao c = new Convocacao();
						c.setTitulo(titulo);
						c.setTipo(tipo);
						c.setInicio(arquivo.getInicio());
						c.setFim(arquivo.getFim());
						c.setProfissiograma(profissiograma);
						c.setEmpregadoConvocacoes(new ArrayList<EmpregadoConvocacao>());
						convocacoes.add(c);
						convocacoesPersistencia.add(c);
						convocacao = c;
					} else {
						convocacoes.add(cs.get(0));
						convocacao = cs.get(0);
					}
				} else
					convocacao = cs.get(0);

				EmpregadoConvocacao empregadoConvocacao = new EmpregadoConvocacao();
				if (convocacao.getEmpregadoConvocacoes() == null) {
					convocacao.setEmpregadoConvocacoes(new ArrayList<EmpregadoConvocacao>());
				}
				List<EmpregadoConvocacao> ecs = new ArrayList<EmpregadoConvocacao>();

				ecs = (List<EmpregadoConvocacao>) convocacao.getEmpregadoConvocacoes().stream()
						.filter(ec -> ec.getEmpregado().getMatricula().equals(matricula)).collect(Collectors.toList());

				if (ecs.size() == 0) {
					empregadoConvocacao.setConvocacao(convocacao);
					empregadoConvocacao.setEmpregado(empregado);
					convocacao.getEmpregadoConvocacoes().add(empregadoConvocacao);
					empregadoConvocacoesPersistencia.add(empregadoConvocacao);
				} else
					empregadoConvocacao = ecs.get(0);

				ResultadoExame resultadoExame = new ResultadoExame();
				resultadoExame.setEmpregadoConvocacao(empregadoConvocacao);
				resultadoExame.setExame(exame);
				resultadoExame.setAcao(acao);
				resultadoExame.setLocal(local);
				resultadoExame.setTipo(tipoResultadoExame);
				resultadoExame.setData(cellDate);

				resultadoExames.add(resultadoExame);
			}
			workbook.close();
			try {
				ResultadoExameValidator rEV = new ResultadoExameValidator();
				rEV.validate(resultadoExames);
				ConvocacaoDao.getInstance().saveList(convocacoesPersistencia, empregadoConvocacoesPersistencia,
						resultadoExames);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		matriculasNaoCadastradas = matriculasNaoCadastradas.substring(0, matriculasNaoCadastradas.length()-2);
		return matriculasNaoCadastradas;
	}

	private List<Convocacao> fetchConvocacaoByTitulo(String titulo) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {

		ConvocacaoFilter filter = new ConvocacaoFilter();

		filter.setTitulo(titulo);
		filter.setPageNumber(1);
		filter.setPageSize(Integer.MAX_VALUE);
		List<Convocacao> convocacoes = ConvocacaoDao.getInstance()
				.getListLoadAll(ConvocacaoExampleBuilder.newInstance(filter).exampleByTitulo()).getList();
		if (convocacoes.size() > 0)
			convocacoes = ConvocacaoBuilder.newInstance(convocacoes).loadProfissiograma().loadGerenciaConvocacoes()
					.loadEmpregadoConvocacoes().getEntityList();
		else
			return null;

		return convocacoes;

	}

	private List<Empregado> fetchEmpregadoByMatricula(String matricula)
			throws InstantiationException, IllegalAccessException, Exception {

		EmpregadoFilter filter = new EmpregadoFilter();

		filter.setMatricula(matricula);
		filter.setPageNumber(1);
		filter.setPageSize(Integer.MAX_VALUE);
		List<Empregado> empregados = EmpregadoDao.getInstance()
				.getList(EmpregadoExampleBuilder.newInstance(filter).example()).getList();
		if (empregados != null)
			return empregados;
		return null;
	}

	private List<Exame> fetchExameByCodigo(String codigo)
			throws InstantiationException, IllegalAccessException, Exception {

		ExameFilter filter = new ExameFilter();

		filter.setCodigo(codigo);
		filter.setPageNumber(1);
		filter.setPageSize(Integer.MAX_VALUE);
		List<Exame> exames = ExameDao.getInstance().getList(ExameExampleBuilder.newInstance(filter).example())
				.getList();

		if (exames != null)
			return exames;
		return null;
	}

	@Override
	public PagedList<ResultadoExame> getList(ResultadoExameFilter filter) throws Exception {
		return super.getList(getDao().getListFunctionLoad(getExampleBuilder(filter).example()), this.functionLoad);
	}
}
