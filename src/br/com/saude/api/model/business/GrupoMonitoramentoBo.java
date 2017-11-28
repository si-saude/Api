package br.com.saude.api.model.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.business.validate.GrupoMonitoramentoValidator;
import br.com.saude.api.model.creation.builder.entity.GrupoMonitoramentoBuilder;
import br.com.saude.api.model.creation.builder.example.GrupoMonitoramentoExampleBuilder;
import br.com.saude.api.model.entity.filter.GrupoMonitoramentoFilter;
import br.com.saude.api.model.entity.po.GrupoMonitoramento;
import br.com.saude.api.model.persistence.GrupoMonitoramentoDao;

public class GrupoMonitoramentoBo extends
		GenericBo<GrupoMonitoramento, GrupoMonitoramentoFilter, GrupoMonitoramentoDao, GrupoMonitoramentoBuilder, GrupoMonitoramentoExampleBuilder> {

	private Function<GrupoMonitoramentoBuilder, GrupoMonitoramentoBuilder> functionLoadGrupoMonitoramentoExames;

	private static GrupoMonitoramentoBo instance;

	private GrupoMonitoramentoBo() {
		super();
	}

	public static GrupoMonitoramentoBo getInstance() {
		if (instance == null)
			instance = new GrupoMonitoramentoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoad = builder -> {
			return builder.loadTipoGrupoMonitoramento();
		};

		this.functionLoadAll = builder -> {
			return this.functionLoad.apply(builder).loadGrupoMonitoramentoExames().loadEmpregados();
		};

		this.functionLoadGrupoMonitoramentoExames = builder -> {
			return this.functionLoad.apply(builder).loadGrupoMonitoramentoExames();
		};
	}

	@Override
	public GrupoMonitoramento getById(Object id) throws Exception {
		return this.getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}

	@Override
	public PagedList<GrupoMonitoramento> getList(GrupoMonitoramentoFilter filter) throws Exception {
		return this.getList(getDao().getListFunctionLoad(getExampleBuilder(filter).example()), this.functionLoad);
	}

	public PagedList<GrupoMonitoramento> getListLoadGrupoMonitoramentoExames(GrupoMonitoramentoFilter filter)
			throws Exception {
		return this.getList(getDao().getListFunctionLoadGrupoMonitoramentoExames(getExampleBuilder(filter).example()),
				this.functionLoadGrupoMonitoramentoExames);
	}

	@Override
	public List<GrupoMonitoramento> getSelectList(GrupoMonitoramentoFilter filter) throws Exception {
		return this.getSelectList(getDao().getListFunctionLoad(getExampleBuilder(filter).example()).getList(),
				this.functionLoad);
	}

	@Override
	public GrupoMonitoramento save(GrupoMonitoramento grupoMonitoramento) throws Exception {

		grupoMonitoramento.getGrupoMonitoramentoExames().forEach(g -> g.setGrupoMonitoramento(grupoMonitoramento));

		return super.save(grupoMonitoramento);
	}

	public void importFile(File arquivo) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		try {
			ArrayList<GrupoMonitoramento> grupoMonitoramentos = new ArrayList<GrupoMonitoramento>();
			Workbook workbook = WorkbookFactory.create(arquivo);
			if (workbook instanceof HSSFWorkbook) {
				HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0)
						continue;
					GrupoMonitoramento grupoMonitoramento = new GrupoMonitoramento();
					grupoMonitoramento.setId((int) sheet.getRow(r).getCell(0).getNumericCellValue());
					grupoMonitoramento.setNome(sheet.getRow(r).getCell(1).getStringCellValue());
					grupoMonitoramentos.add(grupoMonitoramento);
				}
			} else if (workbook instanceof XSSFWorkbook) {
				XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0)
						continue;
					GrupoMonitoramento grupoMonitoramento = new GrupoMonitoramento();
					grupoMonitoramento.setId((int) sheet.getRow(r).getCell(0).getNumericCellValue());
					grupoMonitoramento.setNome(sheet.getRow(r).getCell(1).getStringCellValue());
					grupoMonitoramentos.add(grupoMonitoramento);
				}
			}
			try {
				GrupoMonitoramentoValidator gMV = new GrupoMonitoramentoValidator();
				gMV.validate(grupoMonitoramentos);
				this.saveList(grupoMonitoramentos);
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
