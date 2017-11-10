package br.com.saude.api.model.business;

import java.util.function.Function;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.EmpregadoBuilder;
import br.com.saude.api.model.creation.builder.example.EmpregadoExampleBuilder;
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.persistence.EmpregadoDao;

public class EmpregadoBo extends GenericBo<Empregado, EmpregadoFilter, EmpregadoDao, 
											EmpregadoBuilder, EmpregadoExampleBuilder> {
	
	private Function<EmpregadoBuilder,EmpregadoBuilder> functionLoadGrupoMonitoramentos;
	
	private static EmpregadoBo instance;
	
	private EmpregadoBo() {
		super();
	}
		
	public static EmpregadoBo getInstance() {
		if(instance == null)
			instance = new EmpregadoBo();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoad = builder -> {
			return builder.loadCargo().loadFuncao().loadGerencia().loadPessoa();
		};
		
		this.functionLoadAll = builder -> {
			return this.functionLoad.apply(builder).loadBase()
						.loadRegime().loadGhe().loadGhee()
						.loadInstalacoes()
						.loadEmpregadoVacinas()
						.loadGrupoMonitoramentos()
						.loadHistoricoGrupoMonitoramentos()
						.loadEndereco().loadTelefones();
		};
		
		this.functionLoadGrupoMonitoramentos = builder -> {
			return this.functionLoad.apply(builder).loadGrupoMonitoramentos();
		};
	}
	
	@Override
	public PagedList<Empregado> getList(EmpregadoFilter filter) throws Exception {
		return super.getList(getDao().getListFunctionLoad(getExampleBuilder(filter).example()), this.functionLoad);
	}
	
	public PagedList<Empregado> getListFunctionLoadGrupoMonitoramentos(EmpregadoFilter filter) throws Exception{
		return super.getList(getDao().getListFunctionLoadGrupoMonitoramentos(getExampleBuilder(filter)
									.example()), this.functionLoadGrupoMonitoramentos);
	}
	
	@Override
	public List<Empregado> getSelectList(EmpregadoFilter filter) throws Exception {
		return super.getSelectList(this.getDao().getListFunctionLoad(this.getExampleBuilder(filter).exampleSelectList()).getList(), this.functionLoad);
	}
	
	@Override
	public Empregado getById(Object id) throws Exception {
		Empregado empregado = getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
		empregado = loadFiles(empregado);
		return empregado;
	}
	
	@Override
	public Empregado save(Empregado empregado) throws Exception {
		
		empregado.getEmpregadoVacinas().forEach(e->e.setEmpregado(empregado));
		empregado.getHistoricoGrupoMonitoramentos().forEach(h->h.setEmpregado(empregado));
		
		Empregado newEmpregado = super.save(empregado);
		saveFiles(empregado, newEmpregado);
		return newEmpregado;
	}
	
	@SuppressWarnings("resource")
	private Empregado loadFiles(Empregado empregado) throws URISyntaxException{
		
		URI uri = new URI(getClass().getProtectionDomain().getCodeSource().getLocation().toString()
				+"empregado/foto/"+empregado.getId()+".png");
		
		File imgPath = new File(uri.getPath());
		
		try {
			FileInputStream fileInputStreamReader = new FileInputStream(imgPath);			
			byte[] fotoArray = new byte[(int) imgPath.length()];
			fileInputStreamReader.read(fotoArray);
			empregado.setFotoBase64(Base64.getEncoder().encodeToString(fotoArray));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		URI uriAssinatura = new URI(getClass().getProtectionDomain().getCodeSource().getLocation().toString()
				+"empregado/assinatura/"+empregado.getId()+".png");
		
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
		if(empregado.getAssinatura() != null) {
			byte[] assinaturaArray = new byte[empregado.getAssinatura().size()];
			
			for (int i = 0; i < empregado.getAssinatura().size(); i++) {
				assinaturaArray[i] = new Integer(empregado.getAssinatura().get(i+"")).byteValue();
			}
			
			URI uri = new URI(getClass().getProtectionDomain().getCodeSource().getLocation().toString()
					+"empregado/assinatura/"+newEmpregado.getId()+".png");
			File file = new File(uri.getPath());
			file.getParentFile().mkdirs();
			
			InputStream in = new ByteArrayInputStream(assinaturaArray);
			BufferedImage bImageFromConvert = ImageIO.read(in);
			ImageIO.write(bImageFromConvert, "png", new File(uri.getPath()));
		}
		if(empregado.getFoto() != null) {
			byte[] fotoArray = new byte[empregado.getFoto().size()];
			
			for (int i = 0; i < empregado.getFoto().size(); i++) {
				fotoArray[i] = new Integer(empregado.getFoto().get(i+"")).byteValue();
			}
			
			URI uri = new URI(getClass().getProtectionDomain().getCodeSource().getLocation().toString()
					+"empregado/foto/"+newEmpregado.getId()+".png");
			File file = new File(uri.getPath());
			file.getParentFile().mkdirs();
			
			InputStream in = new ByteArrayInputStream(fotoArray);
			BufferedImage bImageFromConvert = ImageIO.read(in);
			ImageIO.write(bImageFromConvert, "png", new File(uri.getPath()));
		}
	}
	
}
