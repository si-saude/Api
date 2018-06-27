package br.com.saude.api.model.business;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
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
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.generic.TypeFilter;
import br.com.saude.api.model.creation.builder.entity.EmpregadoConvocacaoBuilder;
import br.com.saude.api.model.creation.builder.example.EmpregadoConvocacaoExampleBuilder;
import br.com.saude.api.model.entity.filter.ConvocacaoFilter;
import br.com.saude.api.model.entity.filter.EmpregadoConvocacaoFilter;
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.po.EmpregadoConvocacao;
import br.com.saude.api.model.entity.po.ResultadoExame;
import br.com.saude.api.model.persistence.EmpregadoConvocacaoDao;

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
			return this.functionLoad.apply(builder).loadExames().loadResultadoExames();
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
		EmpregadoConvocacao empregadoConvocacao = super.getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
		
		if(empregadoConvocacao.getResultadoExames() != null){
			empregadoConvocacao.getResultadoExames().forEach(r->{
				if(r.getExame().getOrdem() == 0)
					r.getExame().setOrdem(Integer.MAX_VALUE);
			});
			
			empregadoConvocacao.getResultadoExames().sort(new Comparator<ResultadoExame>() {
				public int compare(ResultadoExame arg0, ResultadoExame arg1) {					
					return new Integer(arg0.getExame().getOrdem()).compareTo(arg1.getExame().getOrdem());
				}
			});
		}
		
		return empregadoConvocacao;
	}
	
	@Override
	public EmpregadoConvocacao save(EmpregadoConvocacao eC) throws Exception {
		eC.getEmpregadoConvocacaoExames().forEach(e-> e.setEmpregadoConvocacao(eC));
		eC.getResultadoExames().forEach(rE -> { 
			rE.setEmpregadoConvocacao(eC);
			rE.getItemResultadoExames().forEach(iRE -> iRE.setResultadoExame(rE));
		});
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
				
				//OBTER A DATA DE REALIZAÇÃO
				Date realizacao = sheet.getRow(row).getCell(3).getDateCellValue();
				
				// VERIFICAR SE O EMPREGADO CONVOCACAO EXISTE NA LISTA
				EmpregadoConvocacao eC = null;
				int rowAux = row;
				List<EmpregadoConvocacao> listAux = list.stream().filter(e -> e.getEmpregado().getMatricula()
						.contains(new Long((long)sheet.getRow(rowAux).getCell(1).getNumericCellValue()).toString()))
						.collect(Collectors.toList());
				
				if(listAux.size()> 0)
					eC = listAux.get(0);
				else {
					//OBTER O EMPREGADO CONVOCAÇÃO DO BANCO E ADICIONAR NA LISTA
					EmpregadoConvocacaoFilter filter = new EmpregadoConvocacaoFilter();
					filter.setPageNumber(1);
					filter.setPageSize(1);
					filter.setEmpregado(new EmpregadoFilter());
					filter.getEmpregado().setMatricula(new Long((long)sheet.getRow(row).getCell(1).getNumericCellValue()).toString());
					filter.setConvocacao(new ConvocacaoFilter());
					filter.getConvocacao().setTipo(sheet.getRow(row).getCell(4).getStringCellValue().trim());
					filter.getConvocacao().setFim(new DateFilter());
					filter.getConvocacao().getFim().setTypeFilter(TypeFilter.MAIOR);
					filter.getConvocacao().getFim().setInicio(realizacao);
					
					PagedList<EmpregadoConvocacao> pagedList = getListLoadAll(filter);
					
					if(pagedList.getTotal() > 0)
						eC = pagedList.getList().get(0);
				}
				
				if(eC != null) {
					//VERIFICAR EXAMES
					for(int i=0;i <= 27;i++) {
						String value = sheet.getRow(row).getCell(i+5).getStringCellValue();
						
						if(value != null && (value.contains("x") || value.contains("X"))) {
							String[] cdExames = getExames(sheet.getRow(row),i);
							
							if(cdExames != null && cdExames.length > 0) {
								for(String cdExame : cdExames) {
									
									if(eC.getEmpregadoConvocacaoExames().stream().filter(x->
											x.getExame().getCodigo().equals(cdExame) &&
											x.getRealizacao() == null).count() > 0) {
										
											eC.getEmpregadoConvocacaoExames().stream().filter(x->
													x.getExame().getCodigo().equals(cdExame) &&
													x.getRealizacao() == null).findFirst().get()
												.setRealizacao(realizacao);
									}
								}
							}
						}
					}
					
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
		}
		return null;
	}
}
