package br.com.saude.api.model.business;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

import javax.imageio.ImageIO;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.ProfissionalBuilder;
import br.com.saude.api.model.creation.builder.example.ProfissionalExampleBuilder;
import br.com.saude.api.model.entity.filter.ProfissionalFilter;
import br.com.saude.api.model.entity.po.CurriculoCurso;
import br.com.saude.api.model.entity.po.Profissional;
import br.com.saude.api.model.persistence.ProfissionalDao;

public class ProfissionalBo extends GenericBo<Profissional, ProfissionalFilter, ProfissionalDao, 
												ProfissionalBuilder, ProfissionalExampleBuilder> {
	
	private static ProfissionalBo instance;
	
	private ProfissionalBo() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoad = builder -> {
			return builder.loadEquipe().loadLocalizacao().loadCargo();
		};
		
		this.functionLoadAll = builder -> {
			return this.functionLoad.apply(builder)
						.loadEndereco().loadProfissionalConselho()
						.loadCurriculo().loadTelefones().loadProfissionalVacinas();
		};
	}
	
	public static ProfissionalBo getInstance() {
		if(instance==null)
			instance = new ProfissionalBo();
		return instance;
	}
	
	@Override
	public PagedList<Profissional> getList(ProfissionalFilter filter) throws Exception{
		return getList(getDao().getListLoadEquipeLocalizacaoFuncao(getExampleBuilder(filter).example()), 
						functionLoad);
	}
	
	@Override
	public Profissional getById(Object id) throws Exception {
		Profissional profissional = getByEntity(getDao().getByIdLoadAll(id), functionLoadAll); 
		profissional = loadFiles(profissional);
		return profissional;
	}
	
	@Override
	public Profissional save(Profissional profissional) throws Exception {

		if(profissional.getCurriculo() != null) {
			profissional.getCurriculo().setProfissional(profissional);
			
			for(CurriculoCurso curriculoCurso : profissional.getCurriculo().getCurriculoCursos())
				curriculoCurso.setCurriculo(profissional.getCurriculo());
		}
		
		profissional.getProfissionalVacinas().forEach(p->p.setProfissional(profissional));
		
		Profissional newProfissional = super.save(profissional); 
		saveFiles(profissional,newProfissional);
		return newProfissional;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	private void saveFiles(Profissional profissional, Profissional newProfissional) throws URISyntaxException, IOException {
		if(profissional.getAssinatura() != null) {
			byte[] assinaturaArray = new byte[profissional.getAssinatura().size()];
			
			for (int i = 0; i < profissional.getAssinatura().size(); i++) {
				assinaturaArray[i] = new Integer(profissional.getAssinatura().get(i+"")).byteValue();
			}
			
			URI uri = new URI(getClass().getProtectionDomain().getCodeSource().getLocation().toString()
					+"profissional/assinatura/"+newProfissional.getId()+".png");
			File file = new File(uri.getPath());
			file.getParentFile().mkdirs();
			
			InputStream in = new ByteArrayInputStream(assinaturaArray);
			BufferedImage bImageFromConvert = ImageIO.read(in);
			ImageIO.write(bImageFromConvert, "png", new File(uri.getPath()));
		}
	}
	
	@SuppressWarnings("resource")
	private Profissional loadFiles(Profissional profissional) throws URISyntaxException{
		
		URI uri = new URI(getClass().getProtectionDomain().getCodeSource().getLocation().toString()
				+"profissional/assinatura/"+profissional.getId()+".png");
		
		File imgPath = new File(uri.getPath());
		
		try {
			FileInputStream fileInputStreamReader = new FileInputStream(imgPath);			
			byte[] assinaturaArray = new byte[(int) imgPath.length()];
			fileInputStreamReader.read(assinaturaArray);
			profissional.setAssinaturaBase64(Base64.getEncoder().encodeToString(assinaturaArray));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return profissional;
	}
}
