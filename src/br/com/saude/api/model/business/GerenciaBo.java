package br.com.saude.api.model.business;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.business.validate.GerenciaValidator;
import br.com.saude.api.model.creation.builder.entity.GerenciaBuilder;
import br.com.saude.api.model.creation.builder.example.GerenciaExampleBuilder;
import br.com.saude.api.model.entity.filter.GerenciaFilter;
import br.com.saude.api.model.entity.po.Gerencia;
import br.com.saude.api.model.persistence.GerenciaDao;

public class GerenciaBo
		extends GenericBo<Gerencia, GerenciaFilter, GerenciaDao, GerenciaBuilder, GerenciaExampleBuilder> {

	private static GerenciaBo instance;

	private GerenciaBo() {
		super();
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadGerente().loadSecretario1().loadSecretario2();
		};
	}

	public static GerenciaBo getInstance() {
		if (instance == null)
			instance = new GerenciaBo();
		return instance;
	}

	@Override
	public Gerencia getById(Object id) throws Exception {
		return this.getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}

	public List<Gerencia> getListNotIn(List<Integer> ids) throws Exception {
		GerenciaFilter filter = new GerenciaFilter();
		filter.setPageNumber(1);
		filter.setPageSize(Integer.MAX_VALUE);

		return GerenciaBuilder
				.newInstance(
						this.getDao().getList(GerenciaExampleBuilder.newInstance(filter).exampleNotIn(ids)).getList())
				.getEntityList();
	}

	public void importFile(File arquivo) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		try {
			List<Gerencia> gerencias = new ArrayList<Gerencia>();
			Workbook workbook = WorkbookFactory.create(arquivo);
			if (workbook instanceof HSSFWorkbook) {
				HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0)
						continue;
					String[] splitedCC = sheet.getRow(r).getCell(1).getStringCellValue().split("/");

					constructArrayGerencia(splitedCC, gerencias);
				}

			} else if (workbook instanceof XSSFWorkbook) {
				XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0)
						continue;
					String[] splitedCC = sheet.getRow(r).getCell(1).getStringCellValue().split("/");

					constructArrayGerencia(splitedCC, gerencias);
				}
			}
			try {
				GerenciaValidator gV = new GerenciaValidator();
				gV.validate(gerencias);
				System.out.println(gerencias);
				this.saveList(gerencias);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private List<Gerencia> fetchGerenciaByCodigoCompletoDB(String codigoCompleto) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {

		GerenciaFilter filter = new GerenciaFilter();

		filter.setCodigoCompleto(codigoCompleto);
		filter.setPageNumber(1);
		filter.setPageSize(Integer.MAX_VALUE);
		List<Gerencia> gerencias = getDao().getList(getExampleBuilder(filter).example()).getList();
		if ( gerencias != null)
			return gerencias;
		return null;
	}

	private String chainGerencia(String[] splitedCC, int index) {
		String chainedCC = "";
		for (int i = 0; i < index; i++) {
			chainedCC += splitedCC[i] + "/";
		}

		return chainedCC.substring(0, chainedCC.length() - 1);
	}

	private Gerencia getGerenciaInArrayOrDB(String[] splitedCC, int nivel, List<Gerencia> gerencias)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, Exception {

		String codigoCompleto = chainGerencia(splitedCC, nivel);
		List<Gerencia> gerencia = gerencias.stream().filter(g -> g.getCodigoCompleto().equals(codigoCompleto)).collect(Collectors.toList());

		if (gerencia == null || gerencia.size() == 0) {
			// busca no banco
			List<Gerencia> gs = this.fetchGerenciaByCodigoCompletoDB(codigoCompleto);

			if (gs != null && gs.size() > 0) {
				return gs.get(0);
			} else {
				Gerencia g = new Gerencia();
				String cc = chainGerencia(splitedCC, nivel - 1);
				g.setGerencia(
						(Gerencia) gerencias.stream().filter(ger -> ger.getCodigoCompleto().equals(cc)).collect(Collectors.toList()).get(0));
				g.setCodigo(splitedCC[nivel - 1]);
				return g;
			}
		}

		return null;
	}

	public void constructArrayGerencia(String[] splitedCC, List<Gerencia> gerencias) throws IllegalAccessException, 
				IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		
		if (splitedCC.length > 0) {
			List<Gerencia> gerencia = gerencias.stream().filter(g -> g.getCodigoCompleto().equals(splitedCC[0] ) ).collect(Collectors.toList());
			
			if ( gerencia.size() == 0 || gerencia == null ) {
				List<Gerencia> gerenciaMae = this.fetchGerenciaByCodigoCompletoDB(splitedCC[0]);

				if (gerenciaMae != null && gerenciaMae.size() > 0) {
					gerencias.add(gerenciaMae.get(0));
				} else {
					Gerencia g = new Gerencia();
					g.setCodigo(splitedCC[0]);
					gerencias.add(g);
				}
			}

			if (splitedCC.length > 1) {
				Gerencia g = getGerenciaInArrayOrDB(splitedCC, 2, gerencias);
				if (g != null)
					gerencias.add(g);

				if (splitedCC.length > 2) {
					g = getGerenciaInArrayOrDB(splitedCC, 3, gerencias);
					if (g != null)
						gerencias.add(g);

					if (splitedCC.length > 3) {
						g = getGerenciaInArrayOrDB(splitedCC, 4, gerencias);
						if (g != null)
							gerencias.add(g);
					}
				}
			}
		}
	}
}
