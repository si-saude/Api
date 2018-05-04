package br.com.saude.api.model.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.business.validate.IntervencaoValidator;
import br.com.saude.api.model.creation.builder.entity.IntervencaoBuilder;
import br.com.saude.api.model.creation.builder.example.IntervencaoExampleBuilder;
import br.com.saude.api.model.entity.filter.EquipeFilter;
import br.com.saude.api.model.entity.filter.IntervencaoFilter;
import br.com.saude.api.model.entity.po.Equipe;
import br.com.saude.api.model.entity.po.Intervencao;
import br.com.saude.api.model.persistence.IntervencaoDao;

public class IntervencaoBo extends GenericBo<Intervencao, IntervencaoFilter, 
IntervencaoDao, IntervencaoBuilder, IntervencaoExampleBuilder> {
	
	private static IntervencaoBo instance;
	
	private IntervencaoBo() {
		super();
	}
	
	public static IntervencaoBo getInstance() {
		if(instance == null)
			instance = new IntervencaoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {	
	}
	
	public void importFile( File arquivo ) throws Exception {
		try {
			ArrayList<Intervencao> intervencoes = new ArrayList<Intervencao>();
			Workbook workbook = WorkbookFactory.create(arquivo);
			if (workbook instanceof HSSFWorkbook) {
				HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0)
						continue;
					Intervencao intervencao = new Intervencao();
					
					intervencao.setId((int)sheet.getRow(r).getCell(0).getNumericCellValue());
					
					Equipe equipe = fetchEquipeByAbreviacao((String) sheet.getRow(r).getCell(1).getStringCellValue());
					if ( equipe != null ) intervencao.setEquipe(equipe);
					else continue;
					
					intervencao.setDescricao((String)sheet.getRow(r).getCell(2).getStringCellValue());
					intervencao.setInativo(sheet.getRow(r).getCell(3).getStringCellValue().equals("A") ?
							false : true); 
					
					intervencoes.add(intervencao);
				}
			} else if (workbook instanceof XSSFWorkbook) {
				XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0)
						continue;
					Intervencao intervencao = new Intervencao();
					
					intervencao.setId((int)sheet.getRow(r).getCell(0).getNumericCellValue());
					
					Equipe equipe = fetchEquipeByAbreviacao((String) sheet.getRow(r).getCell(1).getStringCellValue());
					if ( equipe != null ) intervencao.setEquipe(equipe);
					else continue;
					
					intervencao.setDescricao((String)sheet.getRow(r).getCell(2).getStringCellValue());
					intervencao.setInativo(sheet.getRow(r).getCell(3).getStringCellValue().equals("A") ?
							false : true); 
					
					intervencoes.add(intervencao);
				}
			}
			try {
				IntervencaoValidator iV = new IntervencaoValidator();
				iV.validate(intervencoes);
				this.saveList(intervencoes);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private Equipe fetchEquipeByAbreviacao(String abreviacao) throws Exception {
		EquipeFilter equipeFilter = new EquipeFilter();
		equipeFilter.setAbreviacao(abreviacao);
		equipeFilter.setPageNumber(1);
		equipeFilter.setPageSize(1);
		
		PagedList<Equipe> equipes = EquipeBo.getInstance().getList(equipeFilter);
		if ( equipes.getList() != null && equipes.getList().size() > 0 ) return equipes.getList().get(0);
		else return null;
	}
	
}