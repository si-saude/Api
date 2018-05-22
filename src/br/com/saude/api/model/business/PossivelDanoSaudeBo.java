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
import br.com.saude.api.model.business.validate.PossivelDanoSaudeValidator;
import br.com.saude.api.model.creation.builder.entity.PossivelDanoSaudeBuilder;
import br.com.saude.api.model.creation.builder.example.PossivelDanoSaudeExampleBuilder;
import br.com.saude.api.model.entity.filter.PossivelDanoSaudeFilter;
import br.com.saude.api.model.entity.po.PossivelDanoSaude;
import br.com.saude.api.model.persistence.PossivelDanoSaudeDao;

public class PossivelDanoSaudeBo extends GenericBo<PossivelDanoSaude, PossivelDanoSaudeFilter, PossivelDanoSaudeDao, PossivelDanoSaudeBuilder, PossivelDanoSaudeExampleBuilder> {

	private static PossivelDanoSaudeBo instance;

	private PossivelDanoSaudeBo() {
		super();
	}

	public static PossivelDanoSaudeBo getInstance() {
		if (instance == null)
			instance = new PossivelDanoSaudeBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {

	}

	public void importFile(File arquivo) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		try {
			ArrayList<PossivelDanoSaude> possivelDanoSaudes = new ArrayList<PossivelDanoSaude>();
			Workbook workbook = WorkbookFactory.create(arquivo);
			if ( workbook instanceof HSSFWorkbook ) {
				HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0) continue;
					PossivelDanoSaude possivelDanoSaude = new PossivelDanoSaude();
					possivelDanoSaude.setDescricao(sheet.getRow(r).getCell(0).getStringCellValue());
					possivelDanoSaudes.add(possivelDanoSaude);
				}
			} else if ( workbook instanceof XSSFWorkbook ) {
				XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0) continue;
					PossivelDanoSaude possivelDanoSaude = new PossivelDanoSaude();
					possivelDanoSaude.setDescricao(sheet.getRow(r).getCell(0).getStringCellValue());
					possivelDanoSaudes.add(possivelDanoSaude);
				}
			}
			try {
				PossivelDanoSaudeValidator bV = new PossivelDanoSaudeValidator();
				bV.validate(possivelDanoSaudes);
				this.saveList(possivelDanoSaudes);
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
