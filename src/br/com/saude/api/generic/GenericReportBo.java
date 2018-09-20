package br.com.saude.api.generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.Base64;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface GenericReportBo<T> {

	default String createFileToExport( List<T> entities ) throws Exception {
		String ret = null;
		
		if ( entities == null )
			throw new Exception("Arquivo vazio. Favor escolher algum valor.");
		
		Field[] fields = entities.get(0).getClass().getDeclaredFields();
		String name = entities.get(0).getClass().getName();
		@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(name);
		Row headerRow = sheet.createRow(0);
		
		//CONSTROI O CABEÇALHO
		for (int i = 0; i < fields.length; i++ ) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(fields[i].getName());
		}
		
		int rowNum = 1;
		//ADICIONA OS ITENS NO ARQUIVO
		for ( T entity : entities ) {
			Row row = sheet.createRow(rowNum++);
			
			for ( int i = 0; i <= fields.length - 1; i++ ) {
				fields[i].setAccessible(true);
				if ( fields[i].get(entity) == null )
					row.createCell(i).setCellValue("");
				else if ( fields[i].get(entity) instanceof Boolean )
					if ( fields[i].get(entity).toString().equals("true") )
						row.createCell(i).setCellValue("SIM");
					else row.createCell(i).setCellValue("NÃO");
				else
					row.createCell(i).setCellValue(fields[i].get(entity).toString());
			}
		}
		
		URI uri = new URI(Helper.getProjectPath() + "reports/arquivos/");
		File file = new File(uri.getPath());
		file.mkdirs();
		
		String pathFile = file + name + ".xlsx";
		File filePath = new File(pathFile);
		
		FileOutputStream fileOutput = new FileOutputStream(pathFile);
		workbook.write(fileOutput);
		fileOutput.flush();
		fileOutput.close();
		
		try {
			@SuppressWarnings("resource")
			FileInputStream fileInputStreamReader = new FileInputStream(filePath);
			byte[] fileArray = new byte[(int) filePath.length()];
			fileInputStreamReader.read(fileArray);
			ret = Base64.getEncoder().encodeToString(fileArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
}