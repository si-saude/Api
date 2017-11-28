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
import br.com.saude.api.model.business.validate.CargoValidator;
import br.com.saude.api.model.creation.builder.entity.CargoBuilder;
import br.com.saude.api.model.creation.builder.example.CargoExampleBuilder;
import br.com.saude.api.model.entity.filter.CargoFilter;
import br.com.saude.api.model.entity.po.Cargo;
import br.com.saude.api.model.persistence.CargoDao;

public class CargoBo extends GenericBo<Cargo, CargoFilter, CargoDao, CargoBuilder, 
										CargoExampleBuilder> {
	
	private static CargoBo instance;
	
	private CargoBo() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadCursos();
		};
	}
	
	public static CargoBo getInstance() {
		if(instance==null)
			instance = new CargoBo();
		return instance;
	}
	
	@Override
	public Cargo getById(Object id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return getByEntity(getDao().getByIdLoadAll(id),this.functionLoadAll);
	}

	public void importFile(File arquivo) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		try {
			ArrayList<Cargo> cargos = new ArrayList<Cargo>();
			Workbook workbook = WorkbookFactory.create(arquivo);
			if (workbook instanceof HSSFWorkbook) {
				HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0)
						continue;
					Cargo cargo = new Cargo();
					cargo.setId((int) sheet.getRow(r).getCell(0).getNumericCellValue());
					cargo.setNome(sheet.getRow(r).getCell(1).getStringCellValue());
					cargos.add(cargo);
				}
			} else if (workbook instanceof XSSFWorkbook) {
				XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0)
						continue;
					Cargo cargo = new Cargo();
					cargo.setId((int) sheet.getRow(r).getCell(0).getNumericCellValue());
					cargo.setNome(sheet.getRow(r).getCell(1).getStringCellValue());
					cargos.add(cargo);
				}
			}
			try {
				CargoValidator cV = new CargoValidator();
				cV.validate(cargos);
				this.saveList(cargos);
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
