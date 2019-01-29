package br.com.saude.api.model.business;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.OrderFilter;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.generic.TypeFilter;
import br.com.saude.api.model.creation.builder.entity.EmpregadoConvocacaoBuilder;
import br.com.saude.api.model.creation.builder.example.EmpregadoConvocacaoExampleBuilder;
import br.com.saude.api.model.entity.filter.ConvocacaoFilter;
import br.com.saude.api.model.entity.filter.EmpregadoConvocacaoFilter;
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.filter.ExameFilter;
import br.com.saude.api.model.entity.po.CampoExame;
import br.com.saude.api.model.entity.po.EmpregadoConvocacao;
import br.com.saude.api.model.entity.po.EmpregadoConvocacaoExame;
import br.com.saude.api.model.entity.po.Exame;
import br.com.saude.api.model.persistence.EmpregadoConvocacaoDao;
import br.com.saude.api.util.constant.TipoConvocacao;

public class EmpregadoConvocacaoBo 
	extends GenericBo<EmpregadoConvocacao, EmpregadoConvocacaoFilter, 
		EmpregadoConvocacaoDao, EmpregadoConvocacaoBuilder, EmpregadoConvocacaoExampleBuilder> {
	
	private static EmpregadoConvocacaoBo instance;
	
	private EmpregadoConvocacaoBo() {
		super();
	}
	
	public static EmpregadoConvocacaoBo getInstance() {
		if(instance == null)
			instance = new EmpregadoConvocacaoBo();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoad = builder -> {
			return builder.loadEmpregado().loadConvocacao();
		};
		
		this.functionLoadAll = builder -> {
			return this.functionLoad.apply(builder).loadExames();
		};
	}
	
	@Override
	public PagedList<EmpregadoConvocacao> getList(EmpregadoConvocacaoFilter filter) throws Exception {
		return super.getList(getDao().getListFunctionLoad(getExampleBuilder(filter).example()), 
				this.functionLoad);
	}
	
	public PagedList<EmpregadoConvocacao> getListLoadAll(EmpregadoConvocacaoFilter filter) throws Exception {
		return super.getList(getDao().getListFunctionLoadAll(getExampleBuilder(filter).example()), 
				this.functionLoadAll);
	}

	@Override
	public EmpregadoConvocacao getById(Object id) throws Exception {
		return super.getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}
	
	@Override
	public EmpregadoConvocacao save(EmpregadoConvocacao eC) throws Exception {
		eC.getEmpregadoConvocacaoExames().forEach(e-> e.setEmpregadoConvocacao(eC));
		return super.save(eC);
	}
	
	public void importFile(File arquivo) throws Exception {
		/* 1 - Alterar a propriedade de recebimento para realização - OK
		 * 2 - Ler o arquivo - OK
		 * 3 - Para cada linha, obter o(s) registro de EmpregadoConvocacaoExame correspondente
		 * 4 - Setar a data de realização
		 * 5 - adicionar na lista
		 * 6 - salvar a lista
		 */
		
		Workbook workbook = WorkbookFactory.create(arquivo);
		
		if ( workbook instanceof HSSFWorkbook ) {
			
			List<EmpregadoConvocacao> list = new ArrayList<EmpregadoConvocacao>();
			
			int row = 1;
			HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
			
			while(row < sheet.getPhysicalNumberOfRows() 
					&& sheet.getRow(row).getCell(0).getStringCellValue() != null
					&& sheet.getRow(row).getCell(0).getStringCellValue().length() > 0) {
				
				int rowAux = row;
				
				String matricula = null;
				
				try {
					matricula = new Long((long)sheet.getRow(rowAux).getCell(1).getNumericCellValue()).toString();
				}catch(IllegalStateException ex) {
					matricula = sheet.getRow(rowAux).getCell(1).getStringCellValue();
				}
				
				String tipoConvocacao = sheet.getRow(row).getCell(4).getStringCellValue().trim();
				Date realizacao = sheet.getRow(row).getCell(3).getDateCellValue();
				String mat = matricula;
				
				// VERIFICAR SE O EMPREGADO CONVOCACAO EXISTE NA LISTA
				EmpregadoConvocacao eC = null;
				List<EmpregadoConvocacao> listAux = list.stream().filter(e -> e.getEmpregado().getMatricula()
						.contains(mat))
						.collect(Collectors.toList());
				
				if(listAux.size()> 0)
					eC = listAux.get(0);
				else {
					//OBTER O EMPREGADO CONVOCAÇÃO DO BANCO E ADICIONAR NA LISTA
					EmpregadoConvocacaoFilter filter = new EmpregadoConvocacaoFilter();
					filter.setPageNumber(1);
					filter.setPageSize(1);
					filter.setEmpregado(new EmpregadoFilter());
					filter.getEmpregado().setMatricula(matricula);
					filter.setConvocacao(new ConvocacaoFilter());
					filter.getConvocacao().setTipo(tipoConvocacao);
					filter.setDataConvocacao(new DateFilter());
					filter.getDataConvocacao().setTypeFilter(TypeFilter.MENOR_IGUAL);
					filter.getDataConvocacao().setInicio(Helper.getNow());
					filter.setOrder(new OrderFilter());
					filter.getOrder().setDesc(true);
					filter.getOrder().setProperty("dataConvocacao");
					
					PagedList<EmpregadoConvocacao> pagedList = getListLoadAll(filter);
					
					if(pagedList.getTotal() > 0)
						eC = pagedList.getList().get(0);
				}
				
				if(eC != null) {
					//VERIFICAR EXAMES
					for(int i=0;i <= 41;i++) {
						String value = sheet.getRow(row).getCell(i+5).getStringCellValue();
						
						if(value != null && (value.contains("x") || value.contains("X"))) {
							String[] cdExames = getExames(sheet.getRow(row),i);
							
							if(cdExames != null && cdExames.length > 0) {
								for(String cdExame : cdExames) {
									List<EmpregadoConvocacaoExame> empregadoConvocacaoExames = 
											eC.getEmpregadoConvocacaoExames().stream().filter(x->
												x.getExame().getCodigo().equals(cdExame) &&
												x.getRealizacao() == null).collect(Collectors.toList());
									if(empregadoConvocacaoExames.size() > 0) {
										empregadoConvocacaoExames.get(0).setRealizacao(realizacao);
										empregadoConvocacaoExames.get(0).setImportado(true);
									}
								}
							}
						}
					}
					
					EmpregadoConvocacao eAux = eC;
					eC.getEmpregadoConvocacaoExames().forEach(x->x.setEmpregadoConvocacao(eAux));
					list.add(eC);
				}
				
				row++;
			}
			
			saveList(list);
		}
	}
	
	private String[] getExames(HSSFRow row, int posicao) {
		switch(posicao) {
		case 0:
			return new String[]{"EPA0010"};
		case 1:
			return new String[]{"EEA0030"};
		case 2:
			return new String[]{"EPA0020"};
		case 3:
			return new String[]{"EEA0003"};
		case 4:
			return new String[]{"EEA0018","EDA0026","EDA0028"};
		case 6:
			return new String[]{"EHA0003","EHA0006","EHA0009"};
		case 7:
			return new String[]{"EBA0003","EBA0008","EBA0004","EBA0005"};
		case 8:
			return new String[]{"EBA0013"};
		case 9:
			return new String[]{"EBA0022"};
		case 10:
			return new String[]{"EBA0023"};
		case 11:
			return new String[]{"EBA0032"};
		case 12:
			return new String[]{"EBA0034"};
		case 13:
			return new String[]{"EBA0036"};
		case 14:
			return new String[]{"ESA0012"};
		case 15:
			return new String[]{"ESA0010"};
		case 16:
			return new String[]{"ESA0009"};
		case 17:
			return new String[]{"ESA0015"};
		case 18:
			return new String[]{"ESA0006"};
		case 19:
			return new String[]{"EUA0010"};
		case 20:
			return new String[]{"EBA0017"};
		case 21:
			return new String[]{"ESA0062"};
		case 22:
			return new String[]{"EFA0040"};
		case 23:
			return new String[]{"ESA0071"};
		case 24:
			return new String[]{"EPA0040"};
		case 25:
			return new String[]{"EEA0048"};
		case 26:
			return new String[]{"EEA0051"};
		case 27:
			return new String[]{"ERA0021"};
		case 41:
			return new String[]{"EEA0045"};
		}
		return null;
	}
	
	
	public void importFileTxt(File arquivo) throws Exception {
		
		List<EmpregadoConvocacao> empregadoConvocacoes = new ArrayList<EmpregadoConvocacao>();
		
		String matricula = "";
		String codigoExame = "";
		String campoExame = "";
		String informacao = "";
		Date data = null;
		BufferedReader br = null;
		FileReader fr = null;
		SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
		EmpregadoConvocacaoFilter filter;
		ExameFilter exameFilter;
		EmpregadoConvocacao empregadoConvocacao = null;
		EmpregadoConvocacaoExame empregadoConvocacaoExame = null;
		Exame exameAux = null;
		
		try {
			fr = new FileReader(arquivo);
			br = new BufferedReader(fr);

			String currentLine;

			while ( (currentLine = br.readLine()) != null ) {
				if (! String.valueOf(currentLine.charAt(0)).equals("D") ) continue;					
				matricula = currentLine.substring(16, 38).trim();
				data = originalFormat.parse(currentLine.substring(38, 46).trim());
				codigoExame = currentLine.substring(46, 53).trim();
				campoExame = currentLine.substring(53, 56).trim();		
				informacao = currentLine.substring(58).trim();		
				empregadoConvocacaoExame = new EmpregadoConvocacaoExame();
				String  matriculaAux = matricula;	
				String  codigoExameAux = codigoExame;	
				String campoExameAux = campoExame;
				String informacaoAux = informacao;				
				
				List<EmpregadoConvocacao> empAux = empregadoConvocacoes.stream().filter(x-> x.getEmpregado().getMatricula( ).equals(matriculaAux)).collect(Collectors.toList());
				
				if(empAux.stream().count() > 0) {
					empregadoConvocacao = empAux.get(0);
				}else
					empregadoConvocacao = null;
								
				if(empregadoConvocacao == null) {				
					filter = new EmpregadoConvocacaoFilter(); 
					filter.setEmpregado(new EmpregadoFilter());
					filter.getEmpregado().setMatricula(matriculaAux);	
					filter.setConvocacao(new ConvocacaoFilter());
					filter.getConvocacao().setTipo(TipoConvocacao.PERIODICO);
					filter.getConvocacao().setInicio(new DateFilter());
					filter.getConvocacao().getInicio().setTypeFilter(TypeFilter.ENTRE);
					filter.getConvocacao().getInicio().setInicio(Helper.cloneDate(data));
					filter.getConvocacao().getInicio().setFim(Helper.cloneDate(data));
					filter.setOrder(new OrderFilter());
					filter.getOrder().setDesc(true);
					filter.getOrder().setProperty("dataConvocacao");
					filter.setPageNumber(1);
					filter.setPageSize(1);
					
				    PagedList<EmpregadoConvocacao> empConvocacoes = getListLoadAll(filter);
					
				   if(empConvocacoes.getTotal() > 0) {
					   empregadoConvocacao = empConvocacoes.getList().get(0);
					   empregadoConvocacoes.add(empregadoConvocacao);
				   }
				}
				
				if(empregadoConvocacao != null) {
					
					if(empregadoConvocacao.getEmpregadoConvocacaoExames() == null) 
						empregadoConvocacao.setEmpregadoConvocacaoExames(new ArrayList<EmpregadoConvocacaoExame>());
					  
						List<Exame> exAux = empregadoConvocacao.getEmpregadoConvocacaoExames().stream().map(x -> x.getExame()).filter(ex-> ex.getCodigo().equals(codigoExameAux)).collect(Collectors.toList());
						
						if(exAux.size() == 0){
							
							exameFilter = new ExameFilter(); 
							exameFilter.setCodigo(codigoExameAux);	
							exameFilter.setPageNumber(1);
							exameFilter.setPageSize(1);
							
							PagedList<Exame> exaAux = ExameBo.getInstance().getList(exameFilter);
								
						    if(exaAux.getTotal() > 0) {
						    	exameAux = exaAux.getList().get(0);
						    	empregadoConvocacaoExame.setEmpregadoConvocacao(empregadoConvocacao);
						    	empregadoConvocacaoExame.setExame(exameAux);					    	
						    	empregadoConvocacao.getEmpregadoConvocacaoExames().add(empregadoConvocacaoExame);
						    }						
						}	

						empregadoConvocacao.getEmpregadoConvocacaoExames().forEach(eCE->{
							
							if(eCE.getExame().getCodigo().equals(codigoExameAux)) {
								
								try {
									eCE.setExame(ExameBo.getInstance().getById((eCE.getExame().getId())));
								} catch (Exception e) {
									
									e.printStackTrace();
								}	
								List<CampoExame> campoExamesAux= eCE.getExame().getCampoExames().stream().filter(x->x.getCodigo().equals(campoExameAux)).collect(Collectors.toList());
								
								if((!campoExameAux.equals("010")) && campoExamesAux != null && campoExamesAux.size() > 0) {
									String s ="";
									if(!eCE.isResultadoInicializado()) {
										eCE.setResultadoInicializado(true);
										eCE.setResultado("");							
									}else
										s=" / ";						
									
									
							
									eCE.setResultado(eCE.getResultado()+ s + informacaoAux);
								}
							}
						});
				}
						
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(empregadoConvocacoes != null && empregadoConvocacoes.size() > 0) {
			empregadoConvocacoes.forEach(e->{
				e.getEmpregadoConvocacaoExames().forEach(eCE -> eCE.setEmpregadoConvocacao(e));
			});
			
			saveList(empregadoConvocacoes);
		}
	}	
}
