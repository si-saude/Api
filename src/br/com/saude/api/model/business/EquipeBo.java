package br.com.saude.api.model.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.business.validate.EquipeValidator;
import br.com.saude.api.model.creation.builder.entity.EquipeBuilder;
import br.com.saude.api.model.creation.builder.example.EquipeExampleBuilder;
import br.com.saude.api.model.entity.filter.EquipeFilter;
import br.com.saude.api.model.entity.po.Equipe;
import br.com.saude.api.model.persistence.EquipeDao;

public class EquipeBo extends GenericBo<Equipe, EquipeFilter, EquipeDao, EquipeBuilder, EquipeExampleBuilder> {

	private static EquipeBo instance;

	private EquipeBo() {
		super();
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadCoordenador();
		};
	}

	public static EquipeBo getInstance() {
		if (instance == null)
			instance = new EquipeBo();
		return instance;
	}
	
	@Override
	public Equipe getById(Object id) throws Exception {
		Equipe equipe = getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
		return equipe;
	}

	public void importFile(File arquivo) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		try {
			ArrayList<Equipe> equipes = new ArrayList<Equipe>();
			Workbook workbook = WorkbookFactory.create(arquivo);
			if (workbook instanceof HSSFWorkbook) {
				HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0)
						continue;
					Equipe equipe = new Equipe();
					equipe.setId((int) sheet.getRow(r).getCell(0).getNumericCellValue());
					equipe.setNome(sheet.getRow(r).getCell(1).getStringCellValue());
					equipe.setAbreviacao(sheet.getRow(r).getCell(2).getStringCellValue());
					equipes.add(equipe);
				}
			} else if (workbook instanceof XSSFWorkbook) {
				XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0)
						continue;
					Equipe equipe = new Equipe();
					equipe.setId((int) sheet.getRow(r).getCell(0).getNumericCellValue());
					equipe.setNome(sheet.getRow(r).getCell(1).getStringCellValue());
					equipe.setAbreviacao(sheet.getRow(r).getCell(2).getStringCellValue());
					equipes.add(equipe);
				}
			}
			try {
				EquipeValidator eV = new EquipeValidator();
				eV.validate(equipes);
				this.saveList(equipes);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
