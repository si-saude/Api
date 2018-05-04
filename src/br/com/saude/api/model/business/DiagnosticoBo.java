package br.com.saude.api.model.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.business.validate.DiagnosticoValidator;
import br.com.saude.api.model.creation.builder.entity.DiagnosticoBuilder;
import br.com.saude.api.model.creation.builder.example.DiagnosticoExampleBuilder;
import br.com.saude.api.model.entity.filter.DiagnosticoFilter;
import br.com.saude.api.model.entity.po.Diagnostico;
import br.com.saude.api.model.entity.po.Eixo;
import br.com.saude.api.model.persistence.DiagnosticoDao;

public class DiagnosticoBo extends GenericBo<Diagnostico, DiagnosticoFilter, DiagnosticoDao, 
DiagnosticoBuilder, DiagnosticoExampleBuilder> {

	private static DiagnosticoBo instance;
	
	private DiagnosticoBo() {
		super();
	}
	
	public static DiagnosticoBo getInstance() {
		if(instance==null)
			instance = new DiagnosticoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {}
	
	public void importFile( File arquivo ) throws Exception {
		try {
			ArrayList<Diagnostico> diagnosticos = new ArrayList<Diagnostico>();
			Workbook workbook = WorkbookFactory.create(arquivo);
			if (workbook instanceof HSSFWorkbook) {
				HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0)
						continue;
					Diagnostico diagnostico = new Diagnostico();
					
					diagnostico.setId((int)sheet.getRow(r).getCell(0).getNumericCellValue());
					
					Eixo eixo = EixoBo.getInstance().getById((int)sheet.getRow(r).getCell(1).getNumericCellValue());
					if ( eixo != null )
						diagnostico.setEixo(eixo);
					else continue;
					
					diagnostico.setCodigo((String)sheet.getRow(r).getCell(2).getStringCellValue());
					diagnostico.setDescricao((String)sheet.getRow(r).getCell(3).getStringCellValue());
					
					diagnostico.setInativo(sheet.getRow(r).getCell(4).getStringCellValue().equals("A") ?
							false : true); 
					
					diagnosticos.add(diagnostico);
				}
			} else if (workbook instanceof XSSFWorkbook) {
				XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0)
						continue;
					Diagnostico diagnostico = new Diagnostico();
					
					diagnostico.setId((int)sheet.getRow(r).getCell(0).getNumericCellValue());
					
					Eixo eixo = EixoBo.getInstance().getById((int)sheet.getRow(r).getCell(1).getNumericCellValue());
					if ( eixo != null )
						diagnostico.setEixo(eixo);
					else continue;
					
					if ( sheet.getRow(r).getCell(2).getCellTypeEnum() == CellType.NUMERIC ) {
						diagnostico.setCodigo(String.valueOf((int)sheet.getRow(r).getCell(2).getNumericCellValue()));
					} else if ( sheet.getRow(r).getCell(2).getCellTypeEnum() == CellType.STRING ) {
						diagnostico.setCodigo(sheet.getRow(r).getCell(2).getStringCellValue());
					} else continue;
					
					diagnostico.setDescricao((String)sheet.getRow(r).getCell(3).getStringCellValue());
					
					diagnostico.setInativo(sheet.getRow(r).getCell(4).getStringCellValue().equals("A") ?
							false : true); 
					
					diagnosticos.add(diagnostico);
				}
			}
			try {
				DiagnosticoValidator dV = new DiagnosticoValidator();
				dV.validate(diagnosticos);
				this.saveList(diagnosticos);
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
