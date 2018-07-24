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
import br.com.saude.api.model.business.validate.ParteCorpoAtingidaValidator;
import br.com.saude.api.model.creation.builder.entity.ParteCorpoAtingidaBuilder;
import br.com.saude.api.model.creation.builder.example.ParteCorpoAtingidaExampleBuilder;
import br.com.saude.api.model.entity.filter.ParteCorpoAtingidaFilter;
import br.com.saude.api.model.entity.po.ParteCorpoAtingida;
import br.com.saude.api.model.persistence.ParteCorpoAtingidaDao;

public class ParteCorpoAtingidaBo extends GenericBo<ParteCorpoAtingida, ParteCorpoAtingidaFilter, ParteCorpoAtingidaDao, ParteCorpoAtingidaBuilder, ParteCorpoAtingidaExampleBuilder> {

	private static ParteCorpoAtingidaBo instance;

	private ParteCorpoAtingidaBo() {
		super();
	}

	public static ParteCorpoAtingidaBo getInstance() {
		if (instance == null)
			instance = new ParteCorpoAtingidaBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {

	}

	public void importFile(File arquivo) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		try {
			ArrayList<ParteCorpoAtingida> parteCorpoAtingidas = new ArrayList<ParteCorpoAtingida>();
			Workbook workbook = WorkbookFactory.create(arquivo);
			if ( workbook instanceof HSSFWorkbook ) {
				HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0) continue;
					ParteCorpoAtingida parteCorpoAtingida = new ParteCorpoAtingida();
					parteCorpoAtingida.setCodigo(sheet.getRow(r).getCell(0).getStringCellValue());
					parteCorpoAtingida.setDescricao(sheet.getRow(r).getCell(1).getStringCellValue());
					parteCorpoAtingidas.add(parteCorpoAtingida);
				}
			} else if ( workbook instanceof XSSFWorkbook ) {
				XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0) continue;
					ParteCorpoAtingida parteCorpoAtingida = new ParteCorpoAtingida();
					parteCorpoAtingida.setCodigo(sheet.getRow(r).getCell(0).getStringCellValue());
					parteCorpoAtingida.setDescricao(sheet.getRow(r).getCell(1).getStringCellValue());
					parteCorpoAtingidas.add(parteCorpoAtingida);
				}
			}
			try {
				ParteCorpoAtingidaValidator pV = new ParteCorpoAtingidaValidator();
				pV.validate(parteCorpoAtingidas);
				this.saveList(parteCorpoAtingidas);
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
