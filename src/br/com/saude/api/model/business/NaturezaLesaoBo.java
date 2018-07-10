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
import br.com.saude.api.model.business.validate.NaturezaLesaoValidator;
import br.com.saude.api.model.creation.builder.entity.NaturezaLesaoBuilder;
import br.com.saude.api.model.creation.builder.example.NaturezaLesaoExampleBuilder;
import br.com.saude.api.model.entity.filter.NaturezaLesaoFilter;
import br.com.saude.api.model.entity.po.NaturezaLesao;
import br.com.saude.api.model.persistence.NaturezaLesaoDao;

public class NaturezaLesaoBo extends GenericBo<NaturezaLesao, NaturezaLesaoFilter, NaturezaLesaoDao, NaturezaLesaoBuilder, NaturezaLesaoExampleBuilder> {

	private static NaturezaLesaoBo instance;

	private NaturezaLesaoBo() {
		super();
	}

	public static NaturezaLesaoBo getInstance() {
		if (instance == null)
			instance = new NaturezaLesaoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {

	}

	public void importFile(File arquivo) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		try {
			ArrayList<NaturezaLesao> naturezaLesaos = new ArrayList<NaturezaLesao>();
			Workbook workbook = WorkbookFactory.create(arquivo);
			if ( workbook instanceof HSSFWorkbook ) {
				HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0) continue;
					NaturezaLesao naturezaLesao = new NaturezaLesao();
					naturezaLesao.setCodigo(sheet.getRow(r).getCell(0).getStringCellValue());
					naturezaLesao.setDescricao(sheet.getRow(r).getCell(1).getStringCellValue());
					naturezaLesaos.add(naturezaLesao);
				}
			} else if ( workbook instanceof XSSFWorkbook ) {
				XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0) continue;
					NaturezaLesao naturezaLesao = new NaturezaLesao();
					naturezaLesao.setCodigo(sheet.getRow(r).getCell(0).getStringCellValue());
					naturezaLesao.setDescricao(sheet.getRow(r).getCell(1).getStringCellValue());
					naturezaLesaos.add(naturezaLesao);
				}
			}
			try {
				NaturezaLesaoValidator nV = new NaturezaLesaoValidator();
				nV.validate(naturezaLesaos);
				this.saveList(naturezaLesaos);
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
