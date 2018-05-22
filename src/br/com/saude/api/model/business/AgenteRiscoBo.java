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
import br.com.saude.api.model.business.validate.AgenteRiscoValidator;
import br.com.saude.api.model.creation.builder.entity.AgenteRiscoBuilder;
import br.com.saude.api.model.creation.builder.example.AgenteRiscoExampleBuilder;
import br.com.saude.api.model.entity.filter.AgenteRiscoFilter;
import br.com.saude.api.model.entity.po.AgenteRisco;
import br.com.saude.api.model.persistence.AgenteRiscoDao;

public class AgenteRiscoBo extends GenericBo<AgenteRisco, AgenteRiscoFilter, AgenteRiscoDao, AgenteRiscoBuilder, AgenteRiscoExampleBuilder> {

	private static AgenteRiscoBo instance;

	private AgenteRiscoBo() {
		super();
	}

	public static AgenteRiscoBo getInstance() {
		if (instance == null)
			instance = new AgenteRiscoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {

	}

	public void importFile(File arquivo) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		try {
			ArrayList<AgenteRisco> categoriaAgenteRiscos = new ArrayList<AgenteRisco>();
			Workbook workbook = WorkbookFactory.create(arquivo);
			if ( workbook instanceof HSSFWorkbook ) {
				HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0) continue;
					AgenteRisco categoriaAgenteRisco = new AgenteRisco();
					categoriaAgenteRisco.setDescricao(sheet.getRow(r).getCell(0).getStringCellValue());
					categoriaAgenteRisco.setCategoriaAgenteRisco(sheet.getRow(r).getCell(1).getStringCellValue());
					categoriaAgenteRiscos.add(categoriaAgenteRisco);
				}
			} else if ( workbook instanceof XSSFWorkbook ) {
				XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0) continue;
					AgenteRisco categoriaAgenteRisco = new AgenteRisco();
					categoriaAgenteRisco.setDescricao(sheet.getRow(r).getCell(0).getStringCellValue());
					categoriaAgenteRisco.setCategoriaAgenteRisco(sheet.getRow(r).getCell(1).getStringCellValue());
					categoriaAgenteRiscos.add(categoriaAgenteRisco);
				}
			}
			try {
				AgenteRiscoValidator bV = new AgenteRiscoValidator();
				bV.validate(categoriaAgenteRiscos);
				this.saveList(categoriaAgenteRiscos);
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
