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
import br.com.saude.api.model.business.validate.EixoValidator;
import br.com.saude.api.model.creation.builder.entity.EixoBuilder;
import br.com.saude.api.model.creation.builder.example.EixoExampleBuilder;
import br.com.saude.api.model.entity.filter.EixoFilter;
import br.com.saude.api.model.entity.filter.EquipeFilter;
import br.com.saude.api.model.entity.po.Eixo;
import br.com.saude.api.model.entity.po.Equipe;
import br.com.saude.api.model.persistence.EixoDao;

public class EixoBo extends GenericBo<Eixo, EixoFilter, EixoDao, 
EixoBuilder, EixoExampleBuilder> {

	private static EixoBo instance;
	
	private EixoBo() {
		super();
	}
	
	public static EixoBo getInstance() {
		if(instance==null)
			instance = new EixoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {}
	
	public void importFile( File arquivo ) throws Exception {
		try {
			ArrayList<Eixo> eixos = new ArrayList<Eixo>();
			Workbook workbook = WorkbookFactory.create(arquivo);
			if (workbook instanceof HSSFWorkbook) {
				HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0)
						continue;
					Eixo eixo = new Eixo();
					
					eixo.setId((int)sheet.getRow(r).getCell(0).getNumericCellValue());
					eixo.setTitulo((String) sheet.getRow(r).getCell(1).getStringCellValue());
					
					Equipe equipe = fetchEquipeByAbreviacao((String) sheet.getRow(r).getCell(2).getStringCellValue());
					if ( equipe != null ) eixo.setEquipe(equipe);
					else continue;
					
					eixos.add(eixo);
				}
			} else if (workbook instanceof XSSFWorkbook) {
				XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0)
						continue;
					Eixo eixo = new Eixo();
					
					eixo.setId((int)sheet.getRow(r).getCell(0).getNumericCellValue());
					eixo.setTitulo((String) sheet.getRow(r).getCell(1).getStringCellValue());
					
					Equipe equipe = fetchEquipeByAbreviacao((String) sheet.getRow(r).getCell(2).getStringCellValue());
					if ( equipe != null ) eixo.setEquipe(equipe);
					else continue;
					
					eixos.add(eixo);
				}
			}
			try {
				EixoValidator eV = new EixoValidator();
				eV.validate(eixos);
				this.saveList(eixos);
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