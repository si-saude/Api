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
import br.com.saude.api.model.business.validate.EnfaseValidator;
import br.com.saude.api.model.creation.builder.entity.EnfaseBuilder;
import br.com.saude.api.model.creation.builder.example.EnfaseExampleBuilder;
import br.com.saude.api.model.entity.filter.EnfaseFilter;
import br.com.saude.api.model.entity.po.Enfase;
import br.com.saude.api.model.persistence.EnfaseDao;

public class EnfaseBo extends GenericBo<Enfase, EnfaseFilter, EnfaseDao, EnfaseBuilder, EnfaseExampleBuilder> {

	private static EnfaseBo instance;

	private EnfaseBo() {
		super();
	}

	public static EnfaseBo getInstance() {
		if (instance == null)
			instance = new EnfaseBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {

	}

	public void importFile(File arquivo) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		try {
			ArrayList<Enfase> enfases = new ArrayList<Enfase>();
			Workbook workbook = WorkbookFactory.create(arquivo);
			if ( workbook instanceof HSSFWorkbook ) {
				HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0) continue;
					Enfase enfase = new Enfase();
					enfase.setDescricao(sheet.getRow(r).getCell(0).getStringCellValue());
					enfases.add(enfase);
				}
			} else if ( workbook instanceof XSSFWorkbook ) {
				XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0) continue;
					Enfase enfase = new Enfase();
					enfase.setDescricao(sheet.getRow(r).getCell(0).getStringCellValue());
					enfases.add(enfase);
				}
			}
			try {
				EnfaseValidator eV = new EnfaseValidator();
				eV.validate(enfases);
				this.saveList(enfases);
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