package br.com.saude.api.model.business;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.TypeFilter;
import br.com.saude.api.model.creation.builder.entity.AtestadoBuilder;
import br.com.saude.api.model.creation.builder.example.AtestadoExampleBuilder;
import br.com.saude.api.model.entity.filter.AtestadoFilter;
import br.com.saude.api.model.entity.filter.FeriadoFilter;
import br.com.saude.api.model.entity.po.Atestado;
import br.com.saude.api.model.entity.po.Feriado;
import br.com.saude.api.model.entity.po.Servico;
import br.com.saude.api.model.persistence.AtestadoDao;
import br.com.saude.api.util.constant.StatusAtestado;
import br.com.saude.api.util.constant.StatusTarefa;

public class AtestadoBo  extends GenericBo<Atestado, AtestadoFilter, AtestadoDao, AtestadoBuilder, AtestadoExampleBuilder> {

	private static AtestadoBo instance;

	private AtestadoBo() {
		super();
	}

	public static AtestadoBo getInstance() {
		if (instance == null)
			instance = new AtestadoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {}

	@SuppressWarnings("static-access")
	@Override
	public Atestado save(Atestado atestado) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		
		Servico servico = ServicoBo.getInstance().getById(atestado.getTarefa().getServico().getId());
		
		if ( servico.getAtividades() == null )
			throw new Exception("Não existe atividade cadastrada para o serviço. Por favor, contacte o administrador do sistema.");
		
		atestado.getTarefa().setEquipe(servico.getAtividades().get(0).getEquipe());
		
		
		atestado.getTarefa().setStatus(StatusTarefa.getInstance().ABERTA);
		atestado.setStatus(StatusAtestado.getInstance().EM_ANALISE);
		atestado.setDataSolicitacao(Helper.getToday());
		
		FeriadoFilter feriadoFilter = new FeriadoFilter();
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(atestado.getTarefa().getInicio());
	    
		int days = 0;
	    while ( days != 4 ) {
	    	feriadoFilter.setData(new DateFilter());
			feriadoFilter.getData().setInicio(calendar.getTime());
			feriadoFilter.getData().setTypeFilter(TypeFilter.IGUAL);
			ArrayList<Feriado> feriados = (ArrayList<Feriado>) FeriadoBo.getInstance().getList(feriadoFilter).getList();
			
			if ( feriados.size() > 0 ) {
				calendar.setTime(feriados.get(0).getData());
				
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				continue;
			}
	    
			if( calendar.get(Calendar.DAY_OF_WEEK) <= 5 )
	            days++;
			calendar.add( Calendar.DAY_OF_MONTH, 1 );
	    }
	    
//	    Calendar tomorrow = Calendar.getInstance();
//	    tomorrow.setTime(Helper.getToday());
//	    tomorrow.add(Calendar.DAY_OF_MONTH, 1);
	    
	    if ( calendar.getTime().before( Helper.getToday() ) )
	    	throw new Exception("Não é possível enviar atestados com mais de três dias de atraso.");
	    
	    saveFiles(atestado);
	    atestado = super.save(atestado);
		return atestado;
	}
	
	@SuppressWarnings("resource")
	public String getAnexo(int id) throws Exception {
		URI uri = new URI(getClass().getProtectionDomain().getCodeSource().getLocation().toString() + "atestado/anexo/"
				+ id + ".zip");
		
		File anexoPath = new File(uri.getPath());

		byte[] zipArray = new byte[(int) anexoPath.length()];
		
		try {
			new FileInputStream(anexoPath).read(zipArray);
		} catch (FileNotFoundException e) {
			throw new Exception("Arquivo não encontrado para esse atestado.");
		} catch (IOException e) {
			throw new Exception("Erro no servidor. Por favor, contacte o administrador do sistema.");
		}
		
		return Base64.getEncoder().encodeToString(zipArray);
	}

	@SuppressWarnings({ "resource", "unused" })
	private Atestado loadFiles(Atestado atestado) throws URISyntaxException {

		URI uri = new URI(getClass().getProtectionDomain().getCodeSource().getLocation().toString() + "atestado/anexo/"
				+ atestado.getId() + ".zip");

		File anexoPath = new File(uri.getPath());

		try {
			FileInputStream fileInputStreamReader = new FileInputStream(anexoPath);
			byte[] anexoArray = new byte[(int) anexoPath.length()];
			fileInputStreamReader.read(anexoArray);
			atestado.setAnexoBase64(Base64.getEncoder().encodeToString(anexoArray));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return atestado;
	}

	@SuppressWarnings("unlikely-arg-type")
	private void saveFiles(Atestado atestado) throws URISyntaxException, IOException {
		if (atestado.getAnexo() != null) {
			
			URI uri = new URI(getClass().getProtectionDomain().getCodeSource().getLocation().toString()
					+ "atestado/anexo/" + atestado.getId() + ".zip");
			File file = new File(uri.getPath());
			file.getParentFile().mkdirs();
			
			
			byte[] anexoArray = new byte[atestado.getAnexo().size()];
			
			for (int i = 0; i < atestado.getAnexo().size(); i++) {
				anexoArray[i] = new Integer(atestado.getAnexo().get(i + "")).byteValue();
			}

			InputStream in;
			FileOutputStream stream = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			
			in = new ByteArrayInputStream(anexoArray);
			
			int len;
        	while ((len = in.read(buffer)) > 0) {
        		stream.write(buffer, 0, len);
        	}

        	in.close();
        	stream.close();
		}
	}

}