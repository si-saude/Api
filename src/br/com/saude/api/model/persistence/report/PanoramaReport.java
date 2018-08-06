package br.com.saude.api.model.persistence.report;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.com.saude.api.generic.HibernateHelper;
import br.com.saude.api.model.entity.dto.PanoramaDto;

public class PanoramaReport {

	private static PanoramaReport instance;
	
	private PanoramaReport() {
		
	}
	
	public static PanoramaReport getInstance() {
		if(instance == null)
			instance = new PanoramaReport();
		return instance;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<PanoramaDto> getPanoramas() throws IOException {
		
		BufferedReader in = new BufferedReader(new FileReader(getClass().getProtectionDomain()
				.getCodeSource().getLocation().toString().replace("file:/", "")
				+"br/com/saude/api/model/persistence/sql/QueryPanorama.sql"));
		String str;
		StringBuffer query = new StringBuffer();
		while ((str = in.readLine()) != null) {
			query.append(str + "\n ");
		}
		in.close();
		
		List<PanoramaDto> panoramas = new ArrayList<PanoramaDto>();

		Session session = HibernateHelper.getSession();
		List<Object[]> list = new ArrayList<Object[]>();
		
		try {
			list = session.createSQLQuery(query.toString()).list();
		}catch (Exception ex) {
			throw ex;
		}
		finally {
			HibernateHelper.close(session);
		}
		
		PanoramaDto panorama = null;
		
		for(Object[] row : list) {
			panorama = new PanoramaDto();
			
			panorama.setId((int) row[0]);
			panorama.setGerencia((String)row[1]);
			panorama.setMatricula((String)row[2]);
			panorama.setNome((String)row[3]);
			panorama.setMesConvocacao((String)row[4]);
			panorama.setBase((String)row[5]);
			panorama.setDataAsoAnoAnterior((String)row[6]);
			panorama.setDataRealizacaoPreClinico((String)row[7]);
			panorama.setDataAsoAnoAtual((String)row[8]);
			panorama.setSituacaoAso((String)row[9]);
			panorama.setAsoNoPrazo((String)row[10]);
			
			if(row[11] != null)
				panorama.setPendencias((String)row[11]);
			
			panorama.setGerenciaPrimeiraLinha((String)row[12]);
			panorama.setStatusPreClinico((String)row[13]);
			
			if(row[14] != null)
				panorama.setExamesPendentes((String)row[14]);
			
			if ( panorama.getExamesPendentes() != null && panorama.getExamesPendentes().length() > 0 )
				 panorama.setExistePendenciaExames(true);
			else panorama.setExistePendenciaExames(false);
				
			panoramas.add(panorama);
		}
		
		return panoramas;
	}
}
