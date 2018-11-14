package br.com.saude.api.model.business;

import java.util.function.Function;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.business.validate.EmpregadoValidator;
import br.com.saude.api.model.creation.builder.entity.EmpregadoBuilder;
import br.com.saude.api.model.creation.builder.example.EmpregadoExampleBuilder;
import br.com.saude.api.model.creation.builder.example.PessoaExampleBuilder;
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.filter.PessoaFilter;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.GrupoMonitoramento;
import br.com.saude.api.model.persistence.EmpregadoDao;
import br.com.saude.api.model.persistence.PessoaDao;

public class EmpregadoBo
		extends GenericBo<Empregado, EmpregadoFilter, EmpregadoDao, EmpregadoBuilder, EmpregadoExampleBuilder> {

	private Function<EmpregadoBuilder, EmpregadoBuilder> functionLoadGrupoMonitoramentos;
	private Function<EmpregadoBuilder, EmpregadoBuilder> functionLoadTipoGrupoMonitoramentos;

	private static EmpregadoBo instance;

	private EmpregadoBo() {
		super();
	}

	public static EmpregadoBo getInstance() {
		if (instance == null)
			instance = new EmpregadoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
	this.functionLoad = builder -> {
			return builder.loadCargo().loadFuncao().loadGerencia().loadPessoa();
		};

		this.functionLoadAll = builder -> {
			return this.functionLoad.apply(builder).loadBase().loadRegime().loadGhe().loadGhee().loadInstalacoes()
					.loadEmpregadoVacinas().loadTipoGrupoMonitoramento().loadHistoricoGrupoMonitoramentos().loadEndereco()
					.loadTelefones().loadEnfase();
		};

		this.functionLoadTipoGrupoMonitoramentos = builder -> {
			return this.functionLoad.apply(builder).loadTipoGrupoMonitoramento();
		};
		
		this.functionLoadGrupoMonitoramentos = builder -> {
			return this.functionLoad.apply(builder).loadGrupoMonitoramentos();
		};
	}

	@Override
	public PagedList<Empregado> getList(EmpregadoFilter filter) throws Exception {
		return super.getList(getDao().getListFunctionLoad(getExampleBuilder(filter).example()), this.functionLoad);
	}
	
	public PagedList<Empregado> getListEq(EmpregadoFilter filter) throws Exception {
		return super.getList(getDao().getListFunctionLoad(getExampleBuilder(filter).exampleEq()), this.functionLoad);
	}
	
	public PagedList<Empregado> getListFunctionLoadGrupoMonitoramentos(EmpregadoFilter filter) throws Exception {
		return super.getList(getDao().getListFunctionLoadGrupoMonitoramentos(getExampleBuilder(filter).example()),
				this.functionLoadGrupoMonitoramentos);
	}

	@Override
	public List<Empregado> getSelectList(EmpregadoFilter filter) throws Exception {
		return super.getSelectList(
				this.getDao().getListFunctionLoad(this.getExampleBuilder(filter).exampleSelectList()).getList(),
				this.functionLoad);
	}

	@Override
	public Empregado getById(Object id) throws Exception {
		Empregado empregado = getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
		empregado = loadFiles(empregado);
		return empregado;
	}

	public Empregado getByIdLoadGrupoMonitoramentos(Object id) throws Exception {
		return getByEntity(getDao().getByIdLoadGrupoMonitoramento(id), this.functionLoadGrupoMonitoramentos);
	}
	
	public Empregado getByIdLoadTipoGrupoMonitoramento(Object id) throws Exception {
		return getByEntity(getDao().getByIdLoadTipoGrupoMonitoramento(id), this.functionLoadTipoGrupoMonitoramentos);
	}
	
	@Override
	public Empregado save(Empregado empregado) throws Exception {

		empregado.getEmpregadoVacinas().forEach(e -> e.setEmpregado(empregado));
		empregado.getHistoricoGrupoMonitoramentos().forEach(h -> h.setEmpregado(empregado));

		Empregado newEmpregado = super.save(empregado);
		saveFiles(empregado, newEmpregado);
		return newEmpregado;
	}
	
	public Empregado saveAndReturn(Empregado empregado) throws Exception {
		EmpregadoFilter filter = new EmpregadoFilter();
		filter.setChave(empregado.getChave());
		filter.setMatricula(empregado.getMatricula());
		filter.setPageNumber(1);
		filter.setPageSize(1);
		List<Empregado> empregados = getDao().getListFunctionLoadAll(
				getExampleBuilder(filter).exampleOrChaveMatriculaCpf()).getList();
		if ( empregados.size() > 0 )
			throw new Exception("Já existe um empregado cadastrado com os dados informados.");
		else {
			PessoaFilter pessoaFilter = new PessoaFilter();
			pessoaFilter.setCpf(empregado.getPessoa().getCpf());
			pessoaFilter.setPageNumber(1);
			pessoaFilter.setPageSize(1);
			if ( PessoaDao.getInstance().getList(
					PessoaExampleBuilder.newInstance(pessoaFilter).example()).getList().size() > 0 )
				throw new Exception("Já existe um empregado cadastrado com os dados informados.");
		}
		empregado = this.save(empregado);
		
		empregado = getBuilder(
				getDao().getByIdLoad(empregado.getId())).loadCargo().loadFuncao().loadGerencia().getEntity();
		
		return empregado;
	}

	@SuppressWarnings("resource")
	private Empregado loadFiles(Empregado empregado) throws URISyntaxException {

		URI uri = new URI(Helper.getProjectPath() + "empregado/foto/"+ empregado.getId() + ".png");

		File imgPath = new File(uri.getPath());

		try {
			FileInputStream fileInputStreamReader = new FileInputStream(imgPath);
			byte[] fotoArray = new byte[(int) imgPath.length()];
			fileInputStreamReader.read(fotoArray);
			empregado.setFotoBase64(Base64.getEncoder().encodeToString(fotoArray));
		} catch (IOException e) {
			e.printStackTrace();
		}

		URI uriAssinatura = new URI(Helper.getProjectPath()+ "empregado/assinatura/" + empregado.getId() + ".png");

		File imgPathAssinatura = new File(uriAssinatura.getPath());

		try {
			FileInputStream fileInputStreamReader = new FileInputStream(imgPathAssinatura);
			byte[] assinaturaArray = new byte[(int) imgPathAssinatura.length()];
			fileInputStreamReader.read(assinaturaArray);
			empregado.setAssinaturaBase64(Base64.getEncoder().encodeToString(assinaturaArray));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return empregado;
	}

	@SuppressWarnings("unlikely-arg-type")
	private void saveFiles(Empregado empregado, Empregado newEmpregado) throws URISyntaxException, IOException {
		if (empregado.getAssinatura() != null) {
			byte[] assinaturaArray = new byte[empregado.getAssinatura().size()];

			for (int i = 0; i < empregado.getAssinatura().size(); i++) {
				assinaturaArray[i] = new Integer(empregado.getAssinatura().get(i + "")).byteValue();
			}

			URI uri = new URI(Helper.getProjectPath()+ "empregado/assinatura/" + newEmpregado.getId() + ".png");
			File file = new File(uri.getPath());
			file.getParentFile().mkdirs();

			InputStream in = new ByteArrayInputStream(assinaturaArray);
			BufferedImage bImageFromConvert = ImageIO.read(in);
			ImageIO.write(bImageFromConvert, "png", new File(uri.getPath()));
		}
		if (empregado.getFoto() != null) {
			byte[] fotoArray = new byte[empregado.getFoto().size()];

			for (int i = 0; i < empregado.getFoto().size(); i++) {
				fotoArray[i] = new Integer(empregado.getFoto().get(i + "")).byteValue();
			}

			URI uri = new URI(Helper.getProjectPath()+ "empregado/foto/" + newEmpregado.getId() + ".png");
			File file = new File(uri.getPath());
			file.getParentFile().mkdirs();

			InputStream in = new ByteArrayInputStream(fotoArray);
			BufferedImage bImageFromConvert = ImageIO.read(in);
			ImageIO.write(bImageFromConvert, "png", new File(uri.getPath()));
		}
	}

	public void importFile(File arquivo) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		try {
			List<Empregado> empregados = new ArrayList<Empregado>();
			List<GrupoMonitoramento> grupoMonitoramentos = new ArrayList<GrupoMonitoramento>();
			Empregado empregado = null;
			GrupoMonitoramento grupoMonitoramento = null;
			
			Workbook workbook = WorkbookFactory.create(arquivo);
			if (workbook instanceof HSSFWorkbook) {
				HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0)
						continue;
										
					int aux = r;
					
					if(sheet.getRow(aux).getCell(0) == null)
						continue;
						
					if ( empregados.stream().filter(e->
						e.getMatricula() == sheet.getRow(aux).getCell(0).getStringCellValue()
					).count() == 0 ) {
						List<Empregado> es = fetchEmpregadoByMatriculaDB(sheet.getRow(aux).getCell(0).getStringCellValue());
						if ( es.size() > 0 )
							empregados.add(es.get(0));
					}
					
					if ( grupoMonitoramentos.stream().filter(e->
						e.getNome() == sheet.getRow(aux).getCell(1).getStringCellValue()
					).count() == 0 ) {
						List<GrupoMonitoramento> gms = GrupoMonitoramentoBo.getInstance()
								.fetchGMonitByNomeDB(sheet.getRow(aux).getCell(1).getStringCellValue());
						if ( gms.size() > 0 )
							grupoMonitoramentos.add(gms.get(0));
					}	
				}
				for (int r = 0; r < rows; r++) {
					if (r == 0)
						continue;
					
					int aux = r;
					
					if(sheet.getRow(aux).getCell(0) == null)
						continue;
					
					try {
						empregado = empregados.stream().filter(e -> e.getMatricula() == sheet.getRow(aux)
								.getCell(0).getStringCellValue() ).findFirst().get();
					}catch(NoSuchElementException ex) {
						empregado = null;
					}
					
					try {
						grupoMonitoramento = grupoMonitoramentos.stream().filter(e -> e.getNome() == sheet.getRow(aux)
								.getCell(1).getStringCellValue() ).findFirst().get();
					}catch(NoSuchElementException ex) {
						grupoMonitoramento = null;
					}
					
					if ( empregado != null && grupoMonitoramento != null ) {
						GrupoMonitoramento gAux = grupoMonitoramento;
						if(empregado.getGrupoMonitoramentos() != null && empregado.getGrupoMonitoramentos()
								.stream().filter(g->g.getId() == gAux.getId()).count() > 0)
							continue;
						
						empregado.getGrupoMonitoramentos().add(grupoMonitoramento);
					}					
				}
			} else if (workbook instanceof XSSFWorkbook) {
				XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					if (r == 0)
						continue;
					
					int aux = r;
					if ( empregados.stream().filter(e->
						e.getMatricula().equals(sheet.getRow(aux).getCell(0).getStringCellValue())
					).count() == 0 ) {
						List<Empregado> es = fetchEmpregadoByMatriculaDB(sheet.getRow(aux).getCell(0).getStringCellValue());
						if ( es.size() > 0 )
							empregados.add(es.get(0));
					}
					
					if ( grupoMonitoramentos.stream().filter(e->
						e.getNome().equals(sheet.getRow(aux).getCell(1).getStringCellValue())
					).count() == 0 ) {
						List<GrupoMonitoramento> gms = GrupoMonitoramentoBo.getInstance()
								.fetchGMonitByNomeDB(sheet.getRow(aux).getCell(1).getStringCellValue());
						if ( gms.size() > 0 )
							grupoMonitoramentos.add(gms.get(0));
					}	
				}
				for (int r = 0; r < rows; r++) {
					if (r == 0)
						continue;
					
					int aux = r;
					try {
						empregado = empregados.stream().filter(e -> e.getMatricula().equals(sheet.getRow(aux)
								.getCell(0).getStringCellValue()) ).findFirst().get();
					}catch(NoSuchElementException ex) {
						empregado = null;
					}
					
					try {
						grupoMonitoramento = grupoMonitoramentos.stream().filter(e -> e.getNome().equals(sheet.getRow(aux)
								.getCell(1).getStringCellValue()) ).findFirst().get();
					}catch(NoSuchElementException ex) {
						grupoMonitoramento = null;
					}
					
					if ( empregado != null && grupoMonitoramento != null ) {
						GrupoMonitoramento gAux = grupoMonitoramento;
						if(empregado.getGrupoMonitoramentos() != null && empregado.getGrupoMonitoramentos()
								.stream().filter(g->g.getId() == gAux.getId()).count() > 0)
							continue;
						
						empregado.getGrupoMonitoramentos().add(grupoMonitoramento);
					}
				}
			}
			try {
				EmpregadoValidator eV = new EmpregadoValidator();
				eV.validate(empregados);
				this.saveList(empregados);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private List<Empregado> fetchEmpregadoByMatriculaDB(String matricula) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException, Exception {
		
		EmpregadoFilter filter = new EmpregadoFilter();

		filter.setMatricula(matricula);
		filter.setPageNumber(1);
		filter.setPageSize(Integer.MAX_VALUE);
		List<Empregado> empregados = getDao().getListFunctionLoadAll(getExampleBuilder(filter).example()).getList();
		if ( empregados != null)
			return empregados;
		return null;
	}

}
