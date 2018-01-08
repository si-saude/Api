package br.com.saude.api.model.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.business.validate.ExameValidator;
import br.com.saude.api.model.creation.builder.entity.ExameBuilder;
import br.com.saude.api.model.creation.builder.example.ExameExampleBuilder;
import br.com.saude.api.model.entity.filter.ExameFilter;
import br.com.saude.api.model.entity.po.Exame;
import br.com.saude.api.model.persistence.ExameDao;

public class ExameBo extends GenericBo<Exame, ExameFilter, ExameDao, ExameBuilder, ExameExampleBuilder> {

	private static ExameBo instance;

	private ExameBo() {
		super();
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadCampoExames();
		};
	}

	public static ExameBo getInstance() {
		if (instance == null)
			instance = new ExameBo();
		return instance;
	}
	
	@Override
	public Exame getById(Object id) throws Exception {
		return this.getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}
	
	public List<Exame> getSelectListAll(ExameFilter filter)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, Exception {
		return super.getSelectList(getDao().getListLoadAll(getExampleBuilder(filter).exampleSelectList()).getList(), 
				this.functionLoadAll);
	}
	
	@Override
	public Exame save(Exame exame) throws Exception {

		exame.getCampoExames().forEach(e -> e.setExame(exame));

		return super.save(exame);
	}

	public void importFile(File arquivo) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		try {
			ArrayList<Exame> exames = new ArrayList<Exame>();
			Workbook workbook = WorkbookFactory.create(arquivo);
			if (workbook instanceof HSSFWorkbook) {
				HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0)
						continue;
					Exame exame = new Exame();
					exame.setId((int) sheet.getRow(r).getCell(0).getNumericCellValue());
					exame.setCodigo(sheet.getRow(r).getCell(1).getStringCellValue());
					exame.setDescricao(sheet.getRow(r).getCell(2).getStringCellValue());
					exames.add(exame);
				}
			} else if (workbook instanceof XSSFWorkbook) {
				XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0)
						continue;
					Exame exame = new Exame();
					exame.setId((int) sheet.getRow(r).getCell(0).getNumericCellValue());
					exame.setCodigo(sheet.getRow(r).getCell(1).getStringCellValue());
					exame.setDescricao(sheet.getRow(r).getCell(2).getStringCellValue());
					exames.add(exame);
				}
			}
			try {
				ExameValidator eV = new ExameValidator();
				eV.validate(exames);
				this.saveList(exames);
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
