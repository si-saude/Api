package br.com.saude.api.model.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.business.validate.ResultadoExameValidator;
import br.com.saude.api.model.creation.builder.entity.ConvocacaoBuilder;
import br.com.saude.api.model.creation.builder.entity.EmpregadoConvocacaoBuilder;
import br.com.saude.api.model.creation.builder.entity.ResultadoExameBuilder;
import br.com.saude.api.model.creation.builder.example.ConvocacaoExampleBuilder;
import br.com.saude.api.model.creation.builder.example.EmpregadoConvocacaoExampleBuilder;
import br.com.saude.api.model.creation.builder.example.EmpregadoExampleBuilder;
import br.com.saude.api.model.creation.builder.example.ExameExampleBuilder;
import br.com.saude.api.model.creation.builder.example.ProfissiogramaExampleBuilder;
import br.com.saude.api.model.creation.builder.example.ResultadoExameExampleBuilder;
import br.com.saude.api.model.entity.filter.ConvocacaoFilter;
import br.com.saude.api.model.entity.filter.EmpregadoConvocacaoFilter;
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.filter.ExameFilter;
import br.com.saude.api.model.entity.filter.ProfissiogramaFilter;
import br.com.saude.api.model.entity.filter.ResultadoExameFilter;
import br.com.saude.api.model.entity.po.Convocacao;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.EmpregadoConvocacao;
import br.com.saude.api.model.entity.po.Exame;
import br.com.saude.api.model.entity.po.Profissiograma;
import br.com.saude.api.model.entity.po.ResultadoExame;
import br.com.saude.api.model.persistence.ConvocacaoDao;
import br.com.saude.api.model.persistence.EmpregadoConvocacaoDao;
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

	public String importFile(File arquivo) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		
		List<ResultadoExame> resultadoExames = new ArrayList<ResultadoExame>();
		List<Convocacao> convocacoes = new ArrayList<Convocacao>();
		List<Convocacao> convocacoesPersistencia = new ArrayList<Convocacao>();
		List<Empregado> empregados = new ArrayList<Empregado>();
		List<Exame> exames = new ArrayList<Exame>();
		List<EmpregadoConvocacao> empregadoConvocacoesPersistencia = new ArrayList<EmpregadoConvocacao>();
		String matriculasNaoCadastradas = "";

		try {
			Workbook workbook = WorkbookFactory.create(arquivo);
			if (workbook instanceof HSSFWorkbook) {
				HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0)
						continue;

					String tipo = sheet.getRow(r).getCell(4).getStringCellValue();
					Date cellDate = sheet.getRow(r).getCell(9).getDateCellValue(); 
					String ano = String.valueOf(cellDate.getYear());
					String titulo = tipo + ano;
					String matricula = String.valueOf(sheet.getRow(r).getCell(3).getNumericCellValue()).substring(0, 7);
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
						if (es == null) {
							matriculasNaoCadastradas += matricula + ", "; 
							continue;
						} else {
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
						if (exs.size() == 0) {
							continue;
						} else
							exs.get(0);
							exames.add(exs.get(0));
					} else
						exame = exs.get(0);
					
					Convocacao convocacao = null;
					List<Convocacao> cs = new ArrayList<Convocacao>();
					cs = convocacoes.stream().filter(g -> g.getTitulo().equals(titulo)).collect(Collectors.toList());
					if (cs.size() == 0) {
						cs = fetchConvocacaoByTitulo(titulo);
						if (cs.size() == 0) {
							Convocacao c = new Convocacao();
							c.setTitulo(titulo);
							c.setTipo(tipo);
							c.setProfissiograma(profissiograma);
							c.setEmpregadoConvocacoes(new ArrayList<EmpregadoConvocacao>());
							convocacoes.add(c);
							convocacoesPersistencia.add(c);
						} else {
							convocacoes.add(cs.get(0));
							convocacao = cs.get(0);
						}
					} else
						convocacao = cs.get(0);
					
					EmpregadoConvocacao empregadoConvocacao = null;
					if ( convocacao.getEmpregadoConvocacoes() == null ) {
						convocacao.setEmpregadoConvocacoes(new ArrayList<EmpregadoConvocacao>());
					}
					List<EmpregadoConvocacao> ecs = new ArrayList<EmpregadoConvocacao>();
					
					ecs = (List<EmpregadoConvocacao>) convocacao.getEmpregadoConvocacoes().stream().filter(ec ->
					ec.getEmpregado().getMatricula().equals(matricula)).collect(Collectors.toList());

					if (ecs.size() == 0) {
						EmpregadoConvocacao ec = new EmpregadoConvocacao();
						ec.setConvocacao(convocacao);
						ec.setEmpregado(empregado);
						convocacao.getEmpregadoConvocacoes().add(ec);
						empregadoConvocacoesPersistencia.add(ec);
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
			} else if (workbook instanceof XSSFWorkbook) {
				XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0)
						continue;
					
					String tipo = sheet.getRow(r).getCell(4).getStringCellValue();
					Date cellDate = sheet.getRow(r).getCell(9).getDateCellValue(); 
					String ano = String.valueOf(cellDate.getYear());
					ano = "20"+ano.substring(1, ano.length());
					String titulo = tipo + " " + ano;
					String matricula = String.valueOf(sheet.getRow(r).getCell(3).getStringCellValue());
					System.out.println(matricula);
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
						if ( es == null || es.size() == 0 ) {
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
						if ( exs == null || exs.size() == 0 ) {
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
					if ( convocacao.getEmpregadoConvocacoes() == null ) {
						convocacao.setEmpregadoConvocacoes(new ArrayList<EmpregadoConvocacao>());
					}
					List<EmpregadoConvocacao> ecs = new ArrayList<EmpregadoConvocacao>();
					
					ecs = (List<EmpregadoConvocacao>) convocacao.getEmpregadoConvocacoes().stream().filter(ec ->
					ec.getEmpregado().getMatricula().equals(matricula)).collect(Collectors.toList());

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
			}
			try {
				ResultadoExameValidator rEV = new ResultadoExameValidator();
				rEV.validate(resultadoExames);
				ConvocacaoDao.getInstance().saveList(convocacoesPersistencia, empregadoConvocacoesPersistencia, resultadoExames);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return matriculasNaoCadastradas;
	}

	private ResultadoExame recordResultadoExame(List<Convocacao> convocacoes, List<Empregado> empregados,
			List<Exame> exames, List<EmpregadoConvocacao> empregadoConvocacoes, String tipo, String titulo,
			String matricula, String codigoExame, Date data, String acao, String local, String tipoResultadoExame)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, Exception {

		Profissiograma profissiograma = ProfissiogramaDao.getInstance().getFirst(
				ProfissiogramaExampleBuilder.newInstance(new ProfissiogramaFilter()).example().getCriterions());
		if (profissiograma == null)
			return null;

		Convocacao convocacao = prepareConvocacao(convocacoes, profissiograma, titulo, tipo);
		convocacoes.add(convocacao);

		Empregado empregado = prepareEmpregado(empregados, matricula);
		if (empregado == null)
			return null;
		else
			empregados.add(empregado);

		Exame exame = prepareExame(exames, codigoExame);
		if (exame == null)
			return null;
		else
			exames.add(exame);

		EmpregadoConvocacao empregadoConvocacao = prepareEmpregadoConvocacao(empregadoConvocacoes, empregado,
				convocacao);
		empregadoConvocacoes.add(empregadoConvocacao);

		ResultadoExame resultadoExame = new ResultadoExame();
		resultadoExame.setEmpregadoConvocacao(empregadoConvocacao);
		resultadoExame.setExame(exame);
		resultadoExame.setAcao(acao);
		resultadoExame.setLocal(local);
		resultadoExame.setTipo(tipoResultadoExame);
		resultadoExame.setData(data);
		return resultadoExame;
	}

	private Convocacao prepareConvocacao(List<Convocacao> convocacoes, Profissiograma profissiograma, String titulo,
			String tipo) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, Exception {

		List<Convocacao> cs = null;
		cs = convocacoes.stream().filter(g -> g.getTitulo().equals(titulo)).collect(Collectors.toList());
		
		if (cs.size() == 0) {
			cs = fetchConvocacaoByTitulo(titulo);
			if (cs == null) {
				Convocacao c = new Convocacao();
				c.setTitulo(titulo);
				c.setTipo(tipo);
				c.setProfissiograma(profissiograma);
				c.setEmpregadoConvocacoes(new ArrayList<EmpregadoConvocacao>());
				return c;
			} else
				return cs.get(0);
		} else
			return cs.get(0);
	}

	private List<Convocacao> fetchConvocacaoByTitulo(String titulo) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {

		ConvocacaoFilter filter = new ConvocacaoFilter();

		filter.setTitulo(titulo);
		filter.setPageNumber(1);
		filter.setPageSize(Integer.MAX_VALUE);
		List<Convocacao> convocacoes = ConvocacaoDao.getInstance()
				.getListLoadAll(ConvocacaoExampleBuilder.newInstance(filter).exampleByTitulo()).getList();
		if ( convocacoes.size() > 0 )
			convocacoes = ConvocacaoBuilder.newInstance(convocacoes).
				loadProfissiograma().
				loadGerenciaConvocacoes().
				loadEmpregadoConvocacoes().
				getEntityList();
		else return null;
		
		return convocacoes;
		
	}

	private Empregado prepareEmpregado(List<Empregado> empregados, String matricula)
			throws InstantiationException, IllegalAccessException, Exception {

		List<Empregado> es = null;
		es = empregados.stream().filter(e -> e.getMatricula().equals(matricula)).collect(Collectors.toList());

		if (es.size() == 0) {
			es = fetchEmpregadoByMatricula(matricula);
			if (es == null) {
				return null;
			} else
				return es.get(0);
		} else
			return es.get(0);
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

	private Exame prepareExame(List<Exame> exames, String codigo)
			throws InstantiationException, IllegalAccessException, Exception {

		List<Exame> es = null;
		es = exames.stream().filter(e -> e.getCodigo().equals(codigo)).collect(Collectors.toList());

		if (es.size() == 0) {
			es = fetchExameByCodigo(codigo);
			if (es == null) {
				return null;
			} else
				return es.get(0);
		} else
			return es.get(0);
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

	private EmpregadoConvocacao prepareEmpregadoConvocacao(List<EmpregadoConvocacao> empregadoConvocacoes,
			Empregado empregado, Convocacao convocacao)
			throws InstantiationException, IllegalAccessException, Exception {

		List<EmpregadoConvocacao> ecs = null;
		ecs = (List<EmpregadoConvocacao>) empregadoConvocacoes.stream().filter(ec -> {
			if (ec.getEmpregado().getMatricula().equals(empregado.getMatricula())
					&& ec.getConvocacao().getTitulo().equals(convocacao.getTitulo())) {
				return true;
			} else
				return false;
		}).collect(Collectors.toList());

		if (ecs.size() == 0) {
			ecs = fetchEmpregadoConvocacaoByEmpregadoAndConvocacao(empregado, convocacao);
			if ( ecs == null ) {
				EmpregadoConvocacao ec = new EmpregadoConvocacao();
				ec.setConvocacao(convocacao);
				ec.setEmpregado(empregado);
				return ec;
			} else
				return ecs.get(0);
		} else
			return ecs.get(0);
	}

	private List<EmpregadoConvocacao> fetchEmpregadoConvocacaoByEmpregadoAndConvocacao(Empregado empregado,
			Convocacao convocacao) throws InstantiationException, IllegalAccessException, Exception {

		EmpregadoConvocacaoFilter filter = new EmpregadoConvocacaoFilter();
		ConvocacaoFilter convocacaoFilter = new ConvocacaoFilter();
		EmpregadoFilter empregadoFilter = new EmpregadoFilter();
		convocacaoFilter.setTitulo(convocacao.getTitulo());
		empregadoFilter.setMatricula(empregado.getMatricula());

		filter.setConvocacao(convocacaoFilter);
		filter.setEmpregado(empregadoFilter);
		filter.setPageNumber(1);
		filter.setPageSize(Integer.MAX_VALUE);
		
		List<EmpregadoConvocacao> empregadoConvocacoes = EmpregadoConvocacaoDao.getInstance()
				.getListFunctionLoadAll(EmpregadoConvocacaoExampleBuilder.newInstance(filter).example()).getList();
		if (empregadoConvocacoes.size() > 0) 
			empregadoConvocacoes = EmpregadoConvocacaoBuilder.newInstance(empregadoConvocacoes).
				loadConvocacao().
				loadEmpregado().
				loadExames().
				getEntityList();
		else return null;
		
		return empregadoConvocacoes;
	}
	
	@Override
	public PagedList<ResultadoExame> getList(ResultadoExameFilter filter) throws Exception {
		return super.getList(getDao().getListFunctionLoad(getExampleBuilder(filter).example()), this.functionLoad);
	}
}
