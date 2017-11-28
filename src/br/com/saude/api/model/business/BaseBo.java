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
import br.com.saude.api.model.business.validate.BaseValidator;
import br.com.saude.api.model.creation.builder.entity.BaseBuilder;
import br.com.saude.api.model.creation.builder.example.BaseExampleBuilder;
import br.com.saude.api.model.entity.filter.BaseFilter;
import br.com.saude.api.model.entity.po.Base;
import br.com.saude.api.model.persistence.BaseDao;

public class BaseBo extends GenericBo<Base, BaseFilter, BaseDao, BaseBuilder, BaseExampleBuilder> {

	private static BaseBo instance;

	private BaseBo() {
		super();
	}

	public static BaseBo getInstance() {
		if (instance == null)
			instance = new BaseBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {

	}

	public void importFile(File arquivo) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		try {
			ArrayList<Base> bases = new ArrayList<Base>();
			Workbook workbook = WorkbookFactory.create(arquivo);
			if ( workbook instanceof HSSFWorkbook ) {
				HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0) continue;
					Base base = new Base();
					base.setId((int)sheet.getRow(r).getCell(0).getNumericCellValue());
					base.setNome(sheet.getRow(r).getCell(1).getStringCellValue());
					bases.add(base);
				}
			} else if ( workbook instanceof XSSFWorkbook ) {
				XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0) continue;
					Base base = new Base();
					base.setId((int)sheet.getRow(r).getCell(0).getNumericCellValue());
					base.setNome(sheet.getRow(r).getCell(1).getStringCellValue());
					bases.add(base);
				}
			}
			try {
				BaseValidator bV = new BaseValidator();
				bV.validate(bases);
				this.saveList(bases);
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
