package br.com.saude.api.model.business;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.business.validate.GheValidator;
import br.com.saude.api.model.creation.builder.entity.GheBuilder;
import br.com.saude.api.model.creation.builder.example.GheExampleBuilder;
import br.com.saude.api.model.entity.filter.GheFilter;
import br.com.saude.api.model.entity.po.Ghe;
import br.com.saude.api.model.persistence.GheDao;

public class GheBo extends GenericBo<Ghe, GheFilter, GheDao, GheBuilder, GheExampleBuilder> {

	private static GheBo instance;
	
	private GheBo() {
		super();
	}
	
	public static GheBo getInstance() {
		if(instance==null)
			instance = new GheBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadAll();
		};
	}
	
	@Override
	public Ghe save(Ghe ghe) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, Exception {
		
		//GERAR DATA DE CRIAÇÃO
		if(ghe.getId() == 0)
			ghe.setDataCriacao(new Date());
		
		return super.save(ghe);
	}
	
	@Override
	public Ghe getById(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
	public PagedList<Ghe> getListGheAtivos(GheFilter gheFilterAux) throws Exception {	
		
		PagedList<Ghe> gheAux = getList(
				GheExampleBuilder.newInstance(gheFilterAux).exampleAtivo());
		
		return gheAux;
	}
	
	public void importFile(File arquivo) throws Exception {
		try {
			List<Ghe> ghes = new ArrayList<Ghe>();
			Workbook workbook = WorkbookFactory.create(arquivo);
			
			Sheet sheet = workbook.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			
			for (int r = 1; r < rows; r++)
				ghes.add(getGheFromRow(sheet.getRow(r)));
			
			try {
				GheValidator gheValidator = new GheValidator();
				gheValidator.validate(ghes);
				this.saveList(ghes);
			}catch (Exception e) {
				
			}
		}catch(Exception ex) {
			
		}
	}
	
	private Ghe getGheFromRow(Row row) {
		Ghe ghe = new Ghe();
		ghe.setCodigo(row.getCell(0).getStringCellValue());
		ghe.setNome(row.getCell(1).getStringCellValue());
		return ghe;
	}

}
