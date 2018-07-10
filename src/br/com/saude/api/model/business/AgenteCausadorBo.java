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
import br.com.saude.api.model.business.validate.AgenteCausadorValidator;
import br.com.saude.api.model.creation.builder.entity.AgenteCausadorBuilder;
import br.com.saude.api.model.creation.builder.example.AgenteCausadorExampleBuilder;
import br.com.saude.api.model.entity.filter.AgenteCausadorFilter;
import br.com.saude.api.model.entity.po.AgenteCausador;
import br.com.saude.api.model.persistence.AgenteCausadorDao;

public class AgenteCausadorBo extends GenericBo<AgenteCausador, AgenteCausadorFilter, AgenteCausadorDao, AgenteCausadorBuilder, AgenteCausadorExampleBuilder> {

	private static AgenteCausadorBo instance;

	private AgenteCausadorBo() {
		super();
	}

	public static AgenteCausadorBo getInstance() {
		if (instance == null)
			instance = new AgenteCausadorBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {

	}

	public void importFile(File arquivo) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		try {
			ArrayList<AgenteCausador> agenteCausadors = new ArrayList<AgenteCausador>();
			Workbook workbook = WorkbookFactory.create(arquivo);
			if ( workbook instanceof HSSFWorkbook ) {
				HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0) continue;
					AgenteCausador agenteCausador = new AgenteCausador();
					agenteCausador.setCodigo(sheet.getRow(r).getCell(0).getStringCellValue());
					agenteCausador.setDescricao(sheet.getRow(r).getCell(1).getStringCellValue());
					agenteCausadors.add(agenteCausador);
				}
			} else if ( workbook instanceof XSSFWorkbook ) {
				XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0) continue;
					AgenteCausador agenteCausador = new AgenteCausador();
					agenteCausador.setCodigo(sheet.getRow(r).getCell(0).getStringCellValue());
					agenteCausador.setDescricao(sheet.getRow(r).getCell(1).getStringCellValue());
					agenteCausadors.add(agenteCausador);
				}
			}
			try {
				AgenteCausadorValidator aV = new AgenteCausadorValidator();
				aV.validate(agenteCausadors);
				this.saveList(agenteCausadors);
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
