package br.com.saude.api.model.business;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.business.validate.FonteGeradoraValidator;
import br.com.saude.api.model.creation.builder.entity.FonteGeradoraBuilder;
import br.com.saude.api.model.creation.builder.example.FonteGeradoraExampleBuilder;
import br.com.saude.api.model.entity.filter.FonteGeradoraFilter;
import br.com.saude.api.model.entity.po.FonteGeradora;
import br.com.saude.api.model.persistence.FonteGeradoraDao;

public class FonteGeradoraBo extends GenericBo<FonteGeradora, FonteGeradoraFilter, FonteGeradoraDao, FonteGeradoraBuilder, FonteGeradoraExampleBuilder> {

	private static FonteGeradoraBo instance;

	private FonteGeradoraBo() {
		super();
	}

	public static FonteGeradoraBo getInstance() {
		if (instance == null)
			instance = new FonteGeradoraBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {

	}

	public void importFile(File arquivo) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {

		ArrayList<FonteGeradora> fonteGeradoras = new ArrayList<FonteGeradora>();
		Workbook workbook = WorkbookFactory.create(arquivo);
		if ( workbook instanceof HSSFWorkbook ) {
			HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			for (int r = 0; r < rows; r++) {
				if (r == 0) continue;
				FonteGeradora fonteGeradora = new FonteGeradora();
				fonteGeradora.setDescricao(sheet.getRow(r).getCell(0).getStringCellValue());
				fonteGeradoras.add(fonteGeradora);
			}
		} else if ( workbook instanceof XSSFWorkbook ) {
			XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			for (int r = 0; r < rows; r++) {
				if (r == 0) continue;
				FonteGeradora fonteGeradora = new FonteGeradora();
				fonteGeradora.setDescricao(sheet.getRow(r).getCell(0).getStringCellValue());
				fonteGeradoras.add(fonteGeradora);
			}
		}

		FonteGeradoraValidator bV = new FonteGeradoraValidator();
		bV.validate(fonteGeradoras);
		this.saveList(fonteGeradoras);
	}
}
