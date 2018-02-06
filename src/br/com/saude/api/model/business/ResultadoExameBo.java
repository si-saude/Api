package br.com.saude.api.model.business;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.javatuples.Triplet;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.generic.TypeFilter;
import br.com.saude.api.model.business.validate.ResultadoExameValidator;
import br.com.saude.api.model.creation.builder.entity.ConvocacaoBuilder;
import br.com.saude.api.model.creation.builder.entity.ExameBuilder;
import br.com.saude.api.model.creation.builder.entity.ResultadoExameBuilder;
import br.com.saude.api.model.creation.builder.example.ConvocacaoExampleBuilder;
import br.com.saude.api.model.creation.builder.example.EmpregadoConvocacaoExampleBuilder;
import br.com.saude.api.model.creation.builder.example.EmpregadoExampleBuilder;
import br.com.saude.api.model.creation.builder.example.ExameExampleBuilder;
import br.com.saude.api.model.creation.builder.example.ItemResultadoExameExampleBuilder;
import br.com.saude.api.model.creation.builder.example.ProfissiogramaExampleBuilder;
import br.com.saude.api.model.creation.builder.example.ResultadoExameExampleBuilder;
import br.com.saude.api.model.entity.filter.ConvocacaoFilter;
import br.com.saude.api.model.entity.filter.EmpregadoConvocacaoFilter;
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.filter.ExameFilter;
import br.com.saude.api.model.entity.filter.ItemResultadoExameFilter;
import br.com.saude.api.model.entity.filter.ProfissiogramaFilter;
import br.com.saude.api.model.entity.filter.ResultadoExameFilter;
import br.com.saude.api.model.entity.imports.ResultadoExameImport;
import br.com.saude.api.model.entity.po.CampoExame;
import br.com.saude.api.model.entity.po.Convocacao;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.EmpregadoConvocacao;
import br.com.saude.api.model.entity.po.Exame;
import br.com.saude.api.model.entity.po.ItemResultadoExame;
import br.com.saude.api.model.entity.po.Profissiograma;
import br.com.saude.api.model.entity.po.ResultadoExame;
import br.com.saude.api.model.persistence.ConvocacaoDao;
import br.com.saude.api.model.persistence.EmpregadoConvocacaoDao;
import br.com.saude.api.model.persistence.EmpregadoDao;
import br.com.saude.api.model.persistence.ExameDao;
import br.com.saude.api.model.persistence.ItemResultadoExameDao;
import br.com.saude.api.model.persistence.ProfissiogramaDao;
import br.com.saude.api.model.persistence.ResultadoExameDao;
import br.com.saude.api.util.constant.AcaoResultadoExame;
import br.com.saude.api.util.constant.TipoConvocacao;
import br.com.saude.api.util.constant.TipoResultadoExame;

public class ResultadoExameBo extends
		GenericBo<ResultadoExame, ResultadoExameFilter, ResultadoExameDao, ResultadoExameBuilder, ResultadoExameExampleBuilder> {
	
	private Function<ResultadoExameBuilder,ResultadoExameBuilder> loadItemResultadoExames;
	
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
			return builder.loadEmpregadoConvocacao();
		};
		
		this.functionLoadAll = builder -> {
			return this.functionLoad.apply(builder).loadItemResultadoExames();
		};
		
		this.loadItemResultadoExames = builder -> {
			return builder.loadItemResultadoExames();
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
			int rows = sheet.getPhysicalNumberOfRows();
			for (int r = 0; r < rows; r++) {
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
						if (!matriculasNaoCadastradas.contains(matricula))
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
		matriculasNaoCadastradas = matriculasNaoCadastradas.substring(0, matriculasNaoCadastradas.length() - 2);
		return matriculasNaoCadastradas;
	}

//	public String importFileFromTxt(File arquivo) throws InstantiationException, IllegalAccessException, Exception {
//		List<EmpregadoConvocacao> empregadoConvocacoes = new ArrayList<EmpregadoConvocacao>();
//		List<Exame> exames = new ArrayList<Exame>();
//		List<ResultadoExame> resultadoExames = new ArrayList<ResultadoExame>();
//		List<ItemResultadoExame> itemResultadoExames = new ArrayList<ItemResultadoExame>();
//		String matriculasNaoCadastradas = "";
//		BufferedReader br = null;
//		FileReader fr = null;
//
//		try {
//			fr = new FileReader(arquivo);
//			br = new BufferedReader(fr);
//
//			String currentLine;
//			String resultadoVariasLinhas = "";
//
//			while ( (currentLine = br.readLine()) != null ) {
//				if ( String.valueOf(currentLine.charAt(0)).equals("M") ) continue;
//				String tipoImportacao = String.valueOf(currentLine.charAt(1));
//				String matricula = currentLine.substring(16, 38).trim();
//				String convocacaoTipo = this.getTipoConvocacaoFromLineTxt(currentLine);
//				String data = currentLine.substring(38, 46);
//				String codigoExame = currentLine.substring(46, 53);
//				String codigoCampo = currentLine.substring(53, 56);
//				String linha = currentLine.substring(56, 58);
//				String resultado = currentLine.substring(58, currentLine.length());
//				DateFormat format = new SimpleDateFormat("yyyyMMdd");
//				Date convocacaoDate = format.parse(data);
//				Date resultadoExameDate = null;
//				if (codigoCampo.equals("010") ) {
//					DateFormat formatRED = new SimpleDateFormat("yyyyMMdd");
//					resultadoExameDate = formatRED.parse(resultado);
//				}
//				
//				EmpregadoConvocacao empregadoConvocacao = null;
//				Optional<EmpregadoConvocacao> eC = empregadoConvocacoes.stream().filter(ec -> {
//					if ( ec.getEmpregado().getMatricula() == matricula && 
//							ec.getConvocacao().getTipo() == convocacaoTipo &&
//							convocacaoDate.after(ec.getConvocacao().getInicio()) &&
//							convocacaoDate.before( ec.getConvocacao().getFim()) )
//							return true;
//					return false;
//				}).findFirst();
//				if ( !eC.isPresent() ) {
//					List<EmpregadoConvocacao> eCs = this.fetchEmpregadoConvocacaoDB(matricula, convocacaoDate, convocacaoTipo);
//					if ( eCs == null ) {
//						if ( !matriculasNaoCadastradas.contains(matricula) )
//							matriculasNaoCadastradas += matricula + ", ";
//						continue;
//					} else empregadoConvocacao = eCs.get(0);
//				} else empregadoConvocacao = eC.get();
//				
//				Exame exame = null;
//				Optional<Exame> ex = exames.stream().filter(ex1 -> ex1.getCodigo().equals(codigoExame)).findFirst();
//				if ( !ex.isPresent() ) {
//					List<Exame> es = this.fetchExameByCodigo(codigoExame);
//					if ( es == null ) {
//						if ( !matriculasNaoCadastradas.contains(matricula) )
//							matriculasNaoCadastradas += matricula + ", ";
//						continue;
//					} else exame = es.get(0);
//				} else exame = ex.get();
//				
//				ItemResultadoExame itemResultadoExame = new ItemResultadoExame();
//				
//				CampoExame campoExame = null;
//				Optional<CampoExame> cE = exame.getCampoExames().stream().
//						filter(e -> e.getCodigo().equals(codigoCampo)).findFirst();
//				if ( !cE.isPresent() ) continue;
//				else campoExame = cE.get();
//				
//				final int empregadoConvocacaoId = empregadoConvocacao.getId();
//				final int exameId = exame.getId();
//				Optional<ResultadoExame> rE = resultadoExames.stream().filter(r -> {
//					if ( r.getEmpregadoConvocacao().getId() == empregadoConvocacaoId &&
//							r.getExame().getId() == exameId)
//						return true;
//					return false;
//				}).findFirst();
//				
//				ResultadoExame resultadoExame = null;
//				
//				//se o tipo da importacao é alteracao
//				if (tipoImportacao.equals("A")) {
//					
//					// se resultado exame ja existe na lista, quer dizer que ja foi setada a data e deve-se setar os outros campos
//					if ( rE.isPresent() ) {
//						resultadoExame = rE.get();
//						//busca o campo na lista
//						Optional<ItemResultadoExame> iRE = itemResultadoExames.stream().filter(i -> {
//							if ( i.getResultadoExame().getEmpregadoConvocacao().getId() == empregadoConvocacaoId &&
//									i.getResultadoExame().getExame().getId() == exameId &&
//									i.getCodigo().equals(codigoCampo)) 
//								return true;
//							return false;
//						}).findFirst();
//						
//						resultadoVariasLinhas += resultado + " ";
//						//verifica se o campo possui mais de uma linha
//						if ( Integer.parseInt(linha) > 1 ) {
//							// se o campo possui mais de uma linha e ja foi add na lista de campos, atualiza seu resultado
//							if ( iRE.isPresent() ) {
//								itemResultadoExame = iRE.get();
//								
//								// verifica se o campo existe no resultado exame
//								Optional<ItemResultadoExame> i = resultadoExame.getItemResultadoExames().stream().
//									filter(iREx -> iREx.getCodigo().equals(codigoCampo)).findFirst();
//								
//								// se nao existe, nao deve ser atualizado
//								if ( !i.isPresent() ) continue;
//								
//								//constroi item resultado exame e atualiza a lista de itens resultado exames
//								itemResultadoExame = i.get();
//								itemResultadoExames.remove(itemResultadoExames.indexOf(itemResultadoExame));
//								itemResultadoExame.setResultado(resultadoVariasLinhas);
//								itemResultadoExames.add(itemResultadoExame);
//								
//								//atualiza a lista de resultado exames e de item resultado exames em resultado exame
//								resultadoExames.remove(resultadoExames.indexOf(resultadoExame));
//								resultadoExame.getItemResultadoExames().remove(resultadoExame.getItemResultadoExames().indexOf(i.get()));
//								resultadoExame.getItemResultadoExames().add(itemResultadoExame);
//								resultadoExames.add(resultadoExame);
//							// se o campo possui mais de uma linha e nao esta add na lista de campos, cria um novo campo e add na lista
//							} else {
//								itemResultadoExame.setResultadoExame(resultadoExame);
//								itemResultadoExame.setCodigo(campoExame.getCodigo());
//								itemResultadoExame.setTitulo(campoExame.getTitulo());
//								itemResultadoExame.setResultado(resultadoVariasLinhas);
//								itemResultadoExames.add(itemResultadoExame);
//								resultadoExames.remove(resultadoExames.indexOf(resultadoExame));
//								resultadoExame.getItemResultadoExames().add(itemResultadoExame);
//								resultadoExames.add(resultadoExame);
//							}
//						//se o campo n possui mais de uma linha, ele deve ser atualizado
//						} else {
//							resultadoVariasLinhas = "";
//							
//							// pega o campo do resultado exame que deve ser atualizado 
//							Optional<ItemResultadoExame> iREx = resultadoExame.getItemResultadoExames().stream().
//									filter(iR -> iR.getCodigo().equals(codigoCampo)).findFirst();
//							
//							// se o campo nao existe no resultado exame, continua
//							if ( !iREx.isPresent() ) continue;
//							
//							int indexIREx = resultadoExame.getItemResultadoExames().indexOf(iREx.get());
//							itemResultadoExame = resultadoExame.getItemResultadoExames().get(indexIREx);
//							itemResultadoExame.setResultado(resultado);
//							itemResultadoExames.add(itemResultadoExame);
//							resultadoExames.remove(resultadoExames.indexOf(resultadoExame));
//							resultadoExame.getItemResultadoExames().add(itemResultadoExame);
//							resultadoExames.add(resultadoExame);	
//						}
//					// se resultado exame nao existe na lista, deve ser buscado no banco
//					} else {
//						//busca o resultado exame do banco
//						List<ResultadoExame> rEs = fetchResultadoExameDB(empregadoConvocacao, exame);
//						// se n existir no banco, continua
//						if ( rEs != null ) {
//							resultadoExame = rEs.get(0);
//							
//							//se o primeiro campo nao for um de data, continua
//							if ( !campoExame.getCodigo().equals("010") ) continue;
//							
//							//busca o item resultado exame que contenha um campo data dentro do resultado exame
//							Optional<ItemResultadoExame> iREx = resultadoExame.getItemResultadoExames().stream().filter(iRE -> 
//								iRE.getCodigo().equals("010")).findFirst();
//							
//							//se nao existir um campo data no resultado exame, continua
//							if ( !iREx.isPresent() ) continue;
//							
//							//se existir o campo data, ele deve ser atualizado e add na lista de itens e de resultados
//							itemResultadoExame = iREx.get();
//
//							int indexIREx = resultadoExame.getItemResultadoExames().indexOf(itemResultadoExame);
//							resultadoExame.getItemResultadoExames().get(indexIREx).setResultado(resultado);
//							itemResultadoExames.add(itemResultadoExame);
//							resultadoExame.setData(resultadoExameDate);				
//							resultadoExames.add(resultadoExame);
//							resultadoVariasLinhas = "";
//						} else continue;
//					}
//				//se o tipo de importacao for adicionar
//				} else {
//					// se o resultado exame nao existe na lista
//					if ( !rE.isPresent() ) {
//						// deve ser adicionado um novo resultado exame atraves do campo data
//						if ( !codigoCampo.equals("010") ) continue;
//						
//						resultadoExame = new ResultadoExame();
//						
//						if (resultadoExame.getItemResultadoExames() == null)
//							resultadoExame.setItemResultadoExames(new ArrayList<ItemResultadoExame>());
//						
//						itemResultadoExame.setResultadoExame(resultadoExame);
//						itemResultadoExame.setCodigo(campoExame.getCodigo());
//						itemResultadoExame.setTitulo(campoExame.getTitulo());
//						itemResultadoExame.setResultado(resultado);
//						itemResultadoExames.add(itemResultadoExame);
//						resultadoExame.getItemResultadoExames().add(itemResultadoExame);
//						resultadoExame.setEmpregadoConvocacao(empregadoConvocacao);
//						resultadoExame.setExame(exame);
//						resultadoExame.setAcao(AcaoResultadoExame.getInstance().REALIZADO);
//						resultadoExame.setTipo(TipoResultadoExame.getInstance().PRECLINICO);
//						resultadoExame.setLocal("PMSO");
//						resultadoExame.setData(resultadoExameDate);
//						resultadoExames.add(resultadoExame);
//						resultadoVariasLinhas = "";
//					// se o resultado exame existe na lista, deve ser atualizado 
//					} else {
//						resultadoExame = rE.get();
//						
//						// se existe mais de uma linha para o resultado exame, o seu resultado deve ser atualizado ou criado um item
//						if ( Integer.parseInt(linha) > 1 ) {
//							
//							// busca o item resultado exame dentro da lista
//							Optional<ItemResultadoExame> iRE = itemResultadoExames.stream().filter(i -> {
//								if ( i.getResultadoExame().getEmpregadoConvocacao().getId() == empregadoConvocacaoId &&
//										i.getResultadoExame().getExame().getId() == exameId &&
//										i.getCodigo().equals(codigoCampo)) 
//									return true;
//								return false;
//							}).findFirst();
//							
//							resultadoVariasLinhas += resultado + " ";
//							
//							//se existir o item resultado exame na lista, atualiza o resultado
//							if ( iRE.isPresent() ) {
//								itemResultadoExame = iRE.get();
//								
//								// verifica se o campo existe no resultado exame
//								Optional<ItemResultadoExame> i = resultadoExame.getItemResultadoExames().stream().
//									filter(iREx -> iREx.getCodigo().equals(codigoCampo)).findFirst();
//								
//								// se nao existe, nao deve ser atualizado
//								if ( !i.isPresent() ) continue;
//								
//								//constroi item resultado exame e atualiza a lista de itens resultado exames
//								itemResultadoExame = i.get();
//								itemResultadoExames.remove(itemResultadoExames.indexOf(itemResultadoExame));
//								itemResultadoExame.setResultado(resultadoVariasLinhas);
//								itemResultadoExames.add(itemResultadoExame);
//								
//								//atualiza a lista de resultado exames e de item resultado exames em resultado exame
//								resultadoExames.remove(resultadoExames.indexOf(resultadoExame));
//								resultadoExame.getItemResultadoExames().remove(resultadoExame.getItemResultadoExames().indexOf(i.get()));
//								resultadoExame.getItemResultadoExames().add(itemResultadoExame);
//								resultadoExames.add(resultadoExame);
//							//se nao existe o item resultado exame na lista, deve ser criado um e add
//							} else {
//								itemResultadoExame.setResultadoExame(resultadoExame);
//								itemResultadoExame.setCodigo(campoExame.getCodigo());
//								itemResultadoExame.setTitulo(campoExame.getTitulo());
//								itemResultadoExame.setResultado(resultadoVariasLinhas);
//								itemResultadoExames.add(itemResultadoExame);
//								resultadoExames.remove(resultadoExames.indexOf(resultadoExame));
//								resultadoExame.getItemResultadoExames().add(itemResultadoExame);
//								resultadoExames.add(resultadoExame);
//							}
//						// se nao existe mais de uma linha no item resultado exame, deve atualizado o resultado exame
//						} else {
//							resultadoVariasLinhas = "";
//							itemResultadoExame.setResultadoExame(resultadoExame);
//							itemResultadoExame.setCodigo(campoExame.getCodigo());
//							itemResultadoExame.setTitulo(campoExame.getTitulo());
//							itemResultadoExame.setResultado(resultado);
//							itemResultadoExames.add(itemResultadoExame);
//							resultadoExames.remove(resultadoExames.indexOf(resultadoExame));
//							resultadoExame.getItemResultadoExames().add(itemResultadoExame);
//							resultadoExames.add(resultadoExame);
//						}
//					}
//				}
//				
//			}
//			try {
//				ResultadoExameValidator rEV = new ResultadoExameValidator();
//				rEV.validate(resultadoExames);
//				ResultadoExameDao.getInstance().saveList(resultadoExames);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		matriculasNaoCadastradas = matriculasNaoCadastradas.substring(0, matriculasNaoCadastradas.length() - 2);
//		return matriculasNaoCadastradas;
//	}
	
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	public String importFileFromTxt(File arquivo) throws InstantiationException, IllegalAccessException, Exception {
		List<EmpregadoConvocacao> empregadoConvocacoes = new ArrayList<EmpregadoConvocacao>();
		List<Exame> exames = new ArrayList<Exame>();
		List<ResultadoExame> resultadoExames = new ArrayList<ResultadoExame>();
		List<ItemResultadoExame> itemResultadoExames = new ArrayList<ItemResultadoExame>();
		String matriculasNaoCadastradas = "";
		BufferedReader br = null;
		FileReader fr = null;

		try {
			fr = new FileReader(arquivo);
			br = new BufferedReader(fr);

			String currentLine;
			String resultadoVariasLinhas = "";

			while ( (currentLine = br.readLine()) != null ) {
				Triplet junpLines = null;
				if ( String.valueOf(currentLine.charAt(0)).equals("M") ) continue;
				String tipoImportacao = String.valueOf(currentLine.charAt(1));
				String matricula = currentLine.substring(16, 38).trim();
				String convocacaoTipo = this.getTipoConvocacaoFromLineTxt(currentLine);
				String data = currentLine.substring(38, 46);
				String codigoExame = currentLine.substring(46, 53);
				String codigoCampo = currentLine.substring(53, 56);
				String linha = currentLine.substring(56, 58);
				String resultado = currentLine.substring(58, currentLine.length());
				DateFormat format = new SimpleDateFormat("yyyyMMdd");
				Date convocacaoDate = format.parse(data);
				Date resultadoExameDate = null;
				if (codigoCampo.equals("010") ) {
					DateFormat formatRED = new SimpleDateFormat("yyyyMMdd");
					resultadoExameDate = formatRED.parse(resultado);
				}
				
				if ( junpLines != null && matricula.equals(junpLines.getValue0()) && 
						codigoExame.equals(junpLines.getValue1()) &&
						convocacaoDate.equals(junpLines.getValue2()) ) continue;
				else junpLines = null;
				
				//VERIFICA SE O ITEM DA LINHA JÁ EXITE NO BANCO
				if ( codigoCampo.equals("010") ) {
					List<ItemResultadoExame> iRExs = this.fetchItemResultadoExame(matricula, resultado, codigoExame, convocacaoDate);
					if ( iRExs != null ) {
						junpLines = new Triplet(matricula, codigoExame, convocacaoDate);
						continue;
					}
				}
				
				EmpregadoConvocacao empregadoConvocacao = null;
				Optional<EmpregadoConvocacao> eC = empregadoConvocacoes.stream().filter(ec -> {
					if ( ec.getEmpregado().getMatricula() == matricula && 
							ec.getConvocacao().getTipo() == convocacaoTipo &&
							convocacaoDate.after(ec.getConvocacao().getInicio()) &&
							convocacaoDate.before( ec.getConvocacao().getFim()) )
							return true;
					return false;
				}).findFirst();
				if ( !eC.isPresent() ) {
					List<EmpregadoConvocacao> eCs = this.fetchEmpregadoConvocacaoDB(matricula, convocacaoDate, convocacaoTipo);
					if ( eCs == null ) {
						if ( !matriculasNaoCadastradas.contains(matricula) )
							matriculasNaoCadastradas += matricula + ", ";
						continue;
					} else {
						empregadoConvocacao = eCs.get(0);
						empregadoConvocacoes.add(empregadoConvocacao);
					}
				} else empregadoConvocacao = eC.get();
				
				Exame exame = null;
				Optional<Exame> ex = exames.stream().filter(ex1 -> ex1.getCodigo().equals(codigoExame)).findFirst();
				if ( !ex.isPresent() ) {
					List<Exame> es = this.fetchExameByCodigo(codigoExame);
					if ( es == null ) {
						if ( !matriculasNaoCadastradas.contains(matricula) )
							matriculasNaoCadastradas += matricula + ", ";
						continue;
					} else {
						exame = es.get(0);
						exames.add(exame);
					}
				} else exame = ex.get();
				
				ItemResultadoExame itemResultadoExame = new ItemResultadoExame();
				
				CampoExame campoExame = null;
				Optional<CampoExame> cE = exame.getCampoExames().stream().
						filter(e -> e.getCodigo().equals(codigoCampo)).findFirst();
				if ( !cE.isPresent() ) continue;
				else campoExame = cE.get();
				
				final int empregadoConvocacaoId = empregadoConvocacao.getId();
				final int exameId = exame.getId();
				Optional<ResultadoExame> rE = resultadoExames.stream().filter(r -> {
					if ( r.getEmpregadoConvocacao().getId() == empregadoConvocacaoId &&
							r.getExame().getId() == exameId)
						return true;
					return false;
				}).findFirst();
				
				ResultadoExame resultadoExame = null;
				
				//se o tipo da importacao é alteracao
				if (tipoImportacao.equals("A")) {
					
					// se resultado exame ja existe na lista, quer dizer que ja foi setada a data e deve-se setar os outros campos
					if ( rE.isPresent() ) {
						resultadoExame = rE.get();
						//busca o campo na lista
						Optional<ItemResultadoExame> iRE = itemResultadoExames.stream().filter(i -> {
							if ( i.getResultadoExame().getEmpregadoConvocacao().getId() == empregadoConvocacaoId &&
									i.getResultadoExame().getExame().getId() == exameId &&
									i.getCodigo().equals(codigoCampo)) 
								return true;
							return false;
						}).findFirst();
						
						resultadoVariasLinhas += resultado + " ";
						//verifica se o campo possui mais de uma linha
						if ( Integer.parseInt(linha) > 1 ) {
							// se o campo possui mais de uma linha e ja foi add na lista de campos, atualiza seu resultado
							if ( iRE.isPresent() ) {
								itemResultadoExame = iRE.get();
								
								// verifica se o campo existe no resultado exame
								Optional<ItemResultadoExame> i = resultadoExame.getItemResultadoExames().stream().
									filter(iREx -> iREx.getCodigo().equals(codigoCampo)).findFirst();
								
								// se nao existe, nao deve ser atualizado
								if ( !i.isPresent() ) continue;
								
								//constroi item resultado exame e atualiza a lista de itens resultado exames
								itemResultadoExame = i.get();
								itemResultadoExames.remove(itemResultadoExames.indexOf(itemResultadoExame));
								itemResultadoExame.setResultado(resultadoVariasLinhas);
								itemResultadoExames.add(itemResultadoExame);
								
								//atualiza a lista de resultado exames e de item resultado exames em resultado exame
								resultadoExames.remove(resultadoExames.indexOf(resultadoExame));
								resultadoExame.getItemResultadoExames().remove(resultadoExame.getItemResultadoExames().indexOf(i.get()));
								resultadoExame.getItemResultadoExames().add(itemResultadoExame);
								resultadoExames.add(resultadoExame);
							// se o campo possui mais de uma linha e nao esta add na lista de campos, cria um novo campo e add na lista
							} else {
								itemResultadoExame.setResultadoExame(resultadoExame);
								itemResultadoExame.setCodigo(campoExame.getCodigo());
								itemResultadoExame.setTitulo(campoExame.getTitulo());
								itemResultadoExame.setResultado(resultadoVariasLinhas);
								itemResultadoExames.add(itemResultadoExame);
								resultadoExames.remove(resultadoExames.indexOf(resultadoExame));
								resultadoExame.getItemResultadoExames().add(itemResultadoExame);
								resultadoExames.add(resultadoExame);
							}
						//se o campo n possui mais de uma linha, ele deve ser atualizado
						} else {
							resultadoVariasLinhas = "";
							
							// pega o campo do resultado exame que deve ser atualizado 
							Optional<ItemResultadoExame> iREx = resultadoExame.getItemResultadoExames().stream().
									filter(iR -> iR.getCodigo().equals(codigoCampo)).findFirst();
							
							// se o campo nao existe no resultado exame, continua
							if ( !iREx.isPresent() ) continue;
							
							int indexIREx = resultadoExame.getItemResultadoExames().indexOf(iREx.get());
							itemResultadoExame = resultadoExame.getItemResultadoExames().get(indexIREx);
							itemResultadoExame.setResultado(resultado);
							itemResultadoExames.add(itemResultadoExame);
							resultadoExames.remove(resultadoExames.indexOf(resultadoExame));
							resultadoExame.getItemResultadoExames().add(itemResultadoExame);
							resultadoExames.add(resultadoExame);	
						}
					// se resultado exame nao existe na lista, deve ser buscado no banco
					} else {
						//busca o resultado exame do banco
						List<ResultadoExame> rEs = fetchResultadoExameDB(empregadoConvocacao, exame);
						// se n existir no banco, continua
						if ( rEs != null ) {
							resultadoExame = rEs.get(0);
							
							//se o primeiro campo nao for um de data, continua
							if ( !campoExame.getCodigo().equals("010") ) continue;
							
							//busca o item resultado exame que contenha um campo data dentro do resultado exame
							Optional<ItemResultadoExame> iREx = resultadoExame.getItemResultadoExames().stream().filter(iRE -> 
								iRE.getCodigo().equals("010")).findFirst();
							
							//se nao existir um campo data no resultado exame, continua
							if ( !iREx.isPresent() ) continue;
							
							//se existir o campo data, ele deve ser atualizado e add na lista de itens e de resultados
							itemResultadoExame = iREx.get();

							int indexIREx = resultadoExame.getItemResultadoExames().indexOf(itemResultadoExame);
							resultadoExame.getItemResultadoExames().get(indexIREx).setResultado(resultado);
							itemResultadoExames.add(itemResultadoExame);
							resultadoExame.setData(resultadoExameDate);				
							resultadoExames.add(resultadoExame);
							resultadoVariasLinhas = "";
						} else continue;
					}
				//se o tipo de importacao for adicionar
				} else {
					// se o resultado exame nao existe na lista
					if ( !rE.isPresent() ) {
						// deve ser adicionado um novo resultado exame atraves do campo data
						if ( !codigoCampo.equals("010") ) continue;
						
						resultadoExame = new ResultadoExame();
						
						if (resultadoExame.getItemResultadoExames() == null)
							resultadoExame.setItemResultadoExames(new ArrayList<ItemResultadoExame>());
						
						itemResultadoExame.setResultadoExame(resultadoExame);
						itemResultadoExame.setCodigo(campoExame.getCodigo());
						itemResultadoExame.setTitulo(campoExame.getTitulo());
						itemResultadoExame.setResultado(resultado);
						itemResultadoExames.add(itemResultadoExame);
						resultadoExame.getItemResultadoExames().add(itemResultadoExame);
						resultadoExame.setEmpregadoConvocacao(empregadoConvocacao);
						resultadoExame.setExame(exame);
						resultadoExame.setAcao(AcaoResultadoExame.getInstance().REALIZADO);
						resultadoExame.setTipo(TipoResultadoExame.getInstance().PRECLINICO);
						resultadoExame.setLocal("PMSO");
						resultadoExame.setData(resultadoExameDate);
						resultadoExames.add(resultadoExame);
						resultadoVariasLinhas = "";
					// se o resultado exame existe na lista, deve ser atualizado 
					} else {
						resultadoExame = rE.get();
						
						// se existe mais de uma linha para o resultado exame, o seu resultado deve ser atualizado ou criado um item
						if ( Integer.parseInt(linha) > 1 ) {
							
							// busca o item resultado exame dentro da lista
							Optional<ItemResultadoExame> iRE = itemResultadoExames.stream().filter(i -> {
								if ( i.getResultadoExame().getEmpregadoConvocacao().getId() == empregadoConvocacaoId &&
										i.getResultadoExame().getExame().getId() == exameId &&
										i.getCodigo().equals(codigoCampo)) 
									return true;
								return false;
							}).findFirst();
							
							resultadoVariasLinhas += resultado + " ";
							
							//se existir o item resultado exame na lista, atualiza o resultado
							if ( iRE.isPresent() ) {
								itemResultadoExame = iRE.get();
								
								// verifica se o campo existe no resultado exame
								Optional<ItemResultadoExame> i = resultadoExame.getItemResultadoExames().stream().
									filter(iREx -> iREx.getCodigo().equals(codigoCampo)).findFirst();
								
								// se nao existe, nao deve ser atualizado
								if ( !i.isPresent() ) continue;
								
								//constroi item resultado exame e atualiza a lista de itens resultado exames
								itemResultadoExame = i.get();
								itemResultadoExames.remove(itemResultadoExames.indexOf(itemResultadoExame));
								itemResultadoExame.setResultado(resultadoVariasLinhas);
								itemResultadoExames.add(itemResultadoExame);
								
								//atualiza a lista de resultado exames e de item resultado exames em resultado exame
								resultadoExames.remove(resultadoExames.indexOf(resultadoExame));
								resultadoExame.getItemResultadoExames().remove(resultadoExame.getItemResultadoExames().indexOf(i.get()));
								resultadoExame.getItemResultadoExames().add(itemResultadoExame);
								resultadoExames.add(resultadoExame);
							//se nao existe o item resultado exame na lista, deve ser criado um e add
							} else {
								itemResultadoExame.setResultadoExame(resultadoExame);
								itemResultadoExame.setCodigo(campoExame.getCodigo());
								itemResultadoExame.setTitulo(campoExame.getTitulo());
								itemResultadoExame.setResultado(resultadoVariasLinhas);
								itemResultadoExames.add(itemResultadoExame);
								resultadoExames.remove(resultadoExames.indexOf(resultadoExame));
								resultadoExame.getItemResultadoExames().add(itemResultadoExame);
								resultadoExames.add(resultadoExame);
							}
						// se nao existe mais de uma linha no item resultado exame, deve atualizado o resultado exame
						} else {
							resultadoVariasLinhas = "";
							itemResultadoExame.setResultadoExame(resultadoExame);
							itemResultadoExame.setCodigo(campoExame.getCodigo());
							itemResultadoExame.setTitulo(campoExame.getTitulo());
							itemResultadoExame.setResultado(resultado);
							itemResultadoExames.add(itemResultadoExame);
							resultadoExames.remove(resultadoExames.indexOf(resultadoExame));
							resultadoExame.getItemResultadoExames().add(itemResultadoExame);
							resultadoExames.add(resultadoExame);
						}
					}
				}
				
			}
			try {
				ResultadoExameValidator rEV = new ResultadoExameValidator();
				rEV.validate(resultadoExames);
				ResultadoExameDao.getInstance().saveList(resultadoExames);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		matriculasNaoCadastradas = matriculasNaoCadastradas.substring(0, matriculasNaoCadastradas.length() - 2);
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
		if ( convocacoes.size() > 0 && convocacoes != null )
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
		if ( empregados != null && empregados.size() > 0 )
			return empregados;
		return null;
	}

	private List<Exame> fetchExameByCodigo(String codigo)
			throws InstantiationException, IllegalAccessException, Exception {

		ExameFilter filter = new ExameFilter();

		filter.setCodigo(codigo);
		filter.setPageNumber(1);
		filter.setPageSize(Integer.MAX_VALUE);
		List<Exame> exames = ExameDao.getInstance().getListLoadAll(ExameExampleBuilder.newInstance(filter).example())
				.getList();
		if ( exames != null && exames.size() > 0 ) 
			exames = ExameBuilder.newInstance(exames).loadCampoExames().getEntityList();
		else return null;
		
		return exames;
	}

	@Override
	public PagedList<ResultadoExame> getList(ResultadoExameFilter filter) throws Exception {
		return super.getList(getDao().getListFunctionLoad(getExampleBuilder(filter).example()), this.functionLoad);
	}
	
	public PagedList<ResultadoExame> getListLoadItemResultadoExames(ResultadoExameFilter filter) throws Exception {
		return super.getList(getDao().getListLoadItemResultadoExames(getExampleBuilder(filter).example()), this.loadItemResultadoExames);
	}
	
	@Override
	protected ResultadoExame getById(Object id, Function<ResultadoExameBuilder, ResultadoExameBuilder> loadFunction)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, Exception {
		return super.getById(id, this.functionLoadAll);
	}
	
	private List<EmpregadoConvocacao> fetchEmpregadoConvocacaoDB(String matricula, Date convocacaoDate, String convocacaoTipo) throws InstantiationException, IllegalAccessException, Exception {
		EmpregadoConvocacaoFilter empregadoConvocacaoFilter = new EmpregadoConvocacaoFilter();
		EmpregadoFilter empregadoFilter = new EmpregadoFilter();
		ConvocacaoFilter convocacaoFilter = new ConvocacaoFilter();
		DateFilter dateFilter = new DateFilter();
		dateFilter.setInicio(convocacaoDate);
		
		Calendar c = Calendar.getInstance();
		c.setTime(convocacaoDate);
		c.add(Calendar.DATE, 1);
		
		dateFilter.setTypeFilter(TypeFilter.MAIOR);
		
		empregadoFilter.setMatricula(matricula);
		
		convocacaoFilter.setTipo(convocacaoTipo);
		convocacaoFilter.setFim(dateFilter);
		
		empregadoFilter.setPageSize(Integer.MAX_VALUE);
		empregadoFilter.setPageNumber(1);
		convocacaoFilter.setPageSize(Integer.MAX_VALUE);
		convocacaoFilter.setPageNumber(1);
		
		empregadoConvocacaoFilter.setEmpregado(empregadoFilter);
		empregadoConvocacaoFilter.setConvocacao(convocacaoFilter);
		
		empregadoConvocacaoFilter.setPageSize(Integer.MAX_VALUE);
		empregadoConvocacaoFilter.setPageNumber(1);
		
		List<EmpregadoConvocacao> empregadoConvocacoes = EmpregadoConvocacaoDao.getInstance()
				.getList(EmpregadoConvocacaoExampleBuilder.newInstance(empregadoConvocacaoFilter).example()).getList();
		
		if ( empregadoConvocacoes != null && empregadoConvocacoes.size() > 0 )
			return empregadoConvocacoes;
		
		return null;
	}
	
	private List<ResultadoExame> fetchResultadoExameDB(EmpregadoConvocacao empregadoConvocacao, Exame exame) throws InstantiationException, IllegalAccessException, Exception {
		EmpregadoConvocacaoFilter empregadoConvocacaoFilter = new EmpregadoConvocacaoFilter();
		EmpregadoFilter empregadoFilter = new EmpregadoFilter();
		ExameFilter exameFilter = new ExameFilter();
		ResultadoExameFilter resultadoExameFilter = new ResultadoExameFilter();
		
		empregadoFilter.setMatricula(empregadoConvocacao.getEmpregado().getMatricula());
		exameFilter.setCodigo(exame.getCodigo());
		empregadoConvocacaoFilter.setEmpregado(empregadoFilter);
		
		
		resultadoExameFilter.setPageNumber(1);
		resultadoExameFilter.setPageSize(Integer.MAX_VALUE);
		resultadoExameFilter.setEmpregadoConvocacao(empregadoConvocacaoFilter);
		resultadoExameFilter.setExame(exameFilter);
		
		List<ResultadoExame> resultadoExames = ResultadoExameDao.getInstance().
				getListFunctionLoadAll(ResultadoExameExampleBuilder.newInstance(resultadoExameFilter).example()).getList();
		
		if ( resultadoExames != null && resultadoExames.size() > 0 )
			return resultadoExames;
		
		return null;
	}
	
	private List<ItemResultadoExame> fetchItemResultadoExame(String matricula, String resultado, String codigoExame, Date convocacaoDate) throws InstantiationException, IllegalAccessException, Exception {
		EmpregadoFilter empregadoFilter = new EmpregadoFilter();
		ExameFilter exameFilter = new ExameFilter();
		EmpregadoConvocacaoFilter empregadoConvocacaoFilter = new EmpregadoConvocacaoFilter();
		ResultadoExameFilter resultadoExameFilter = new ResultadoExameFilter();
		ItemResultadoExameFilter itemResultadoExameFilter = new ItemResultadoExameFilter();
		ConvocacaoFilter convocacaoFilter = new ConvocacaoFilter();
		DateFilter dateFilter = new DateFilter();
		
		Calendar c = Calendar.getInstance();
		c.setTime(convocacaoDate);
		c.add(Calendar.DATE, 1);
		
		dateFilter.setInicio(convocacaoDate);
		dateFilter.setTypeFilter(TypeFilter.IGUAL);
		exameFilter.setCodigo(codigoExame);
		empregadoFilter.setMatricula(matricula);
		empregadoConvocacaoFilter.setEmpregado(empregadoFilter);
		empregadoConvocacaoFilter.setConvocacao(convocacaoFilter);
		resultadoExameFilter.setEmpregadoConvocacao(empregadoConvocacaoFilter);
		resultadoExameFilter.setExame(exameFilter);
		itemResultadoExameFilter.setResultadoExame(resultadoExameFilter);
		itemResultadoExameFilter.setResultado(resultado);
		
		itemResultadoExameFilter.setPageNumber(1);
		itemResultadoExameFilter.setPageSize(Integer.MAX_VALUE);
		
		List<ItemResultadoExame> itemResultadoExames = ItemResultadoExameDao.getInstance().
				getListFunctionLoadAll(ItemResultadoExameExampleBuilder.newInstance(itemResultadoExameFilter).example()).getList();
		
		if ( itemResultadoExames != null && itemResultadoExames.size() > 0 )
			return itemResultadoExames;
		
		return null; 
	}
	
	private String getTipoConvocacaoFromLineTxt(String currentLine) {

		switch (Character.toString(currentLine.charAt(2))) {

		case "P":
			return TipoConvocacao.PERIODICO;
		case "M":
			return TipoConvocacao.MUDANCA_DE_FUNCAO;
		case "D":
			return TipoConvocacao.DEMISSIONAL;
		case "E":
			return TipoConvocacao.ESPECIAL;
		case "R":
			return TipoConvocacao.RETORNO_AO_TRABALHO;
		case "S":
			return TipoConvocacao.SUPLETIVO;
		case "A":
			return TipoConvocacao.ADMISSIONAL;

		}

		return null;
	}

}
