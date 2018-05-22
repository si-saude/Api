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
import br.com.saude.api.model.business.validate.CategoriaRiscoValidator;
import br.com.saude.api.model.creation.builder.entity.CategoriaRiscoBuilder;
import br.com.saude.api.model.creation.builder.example.CategoriaRiscoExampleBuilder;
import br.com.saude.api.model.entity.filter.CategoriaRiscoFilter;
import br.com.saude.api.model.entity.po.CategoriaRisco;
import br.com.saude.api.model.persistence.CategoriaRiscoDao;

public class CategoriaRiscoBo extends GenericBo<CategoriaRisco, CategoriaRiscoFilter, CategoriaRiscoDao, CategoriaRiscoBuilder, CategoriaRiscoExampleBuilder> {

	private static CategoriaRiscoBo instance;

	private CategoriaRiscoBo() {
		super();
	}

	public static CategoriaRiscoBo getInstance() {
		if (instance == null)
			instance = new CategoriaRiscoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {

	}

	public void importFile(File arquivo) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		try {
			ArrayList<CategoriaRisco> categoriaRiscos = new ArrayList<CategoriaRisco>();
			Workbook workbook = WorkbookFactory.create(arquivo);
			if ( workbook instanceof HSSFWorkbook ) {
				HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0) continue;
					CategoriaRisco categoriaRisco = new CategoriaRisco();
					categoriaRisco.setDescricao(sheet.getRow(r).getCell(0).getStringCellValue());
					categoriaRisco.setObservacao(sheet.getRow(r).getCell(1).getStringCellValue());
					categoriaRiscos.add(categoriaRisco);
				}
			} else if ( workbook instanceof XSSFWorkbook ) {
				XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0) continue;
					CategoriaRisco categoriaRisco = new CategoriaRisco();
					categoriaRisco.setDescricao(sheet.getRow(r).getCell(0).getStringCellValue());
					categoriaRisco.setObservacao(sheet.getRow(r).getCell(1).getStringCellValue());
					categoriaRiscos.add(categoriaRisco);
				}
			}
			try {
				CategoriaRiscoValidator bV = new CategoriaRiscoValidator();
				bV.validate(categoriaRiscos);
				this.saveList(categoriaRiscos);
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
