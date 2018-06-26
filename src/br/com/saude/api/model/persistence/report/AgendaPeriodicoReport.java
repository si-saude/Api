package br.com.saude.api.model.persistence.report;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.HibernateHelper;
import br.com.saude.api.model.entity.dto.AgendaPeriodicoDto;

public class AgendaPeriodicoReport {

private static AgendaPeriodicoReport instance;
	
	private AgendaPeriodicoReport() {
		
	}
	
	public static AgendaPeriodicoReport getInstance() {
		if(instance == null)
			instance = new AgendaPeriodicoReport();
		return instance;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<AgendaPeriodicoDto> getAgendaPeriodicos(String dataInicioInicio, String dataInicioFim, String servicoId) 
			throws Exception {
		
		BufferedReader in = new BufferedReader(new FileReader(getClass().getProtectionDomain()
				.getCodeSource().getLocation().toString().replace("file:/", "")
				+"br/com/saude/api/model/persistence/sql/QueryAgendaPeriodico.sql"));
		String str;
		StringBuffer query = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		while ((str = in.readLine()) != null) {
			query.append(str + "\n ");
		}
		in.close();
		
		while ( query.indexOf("[DATA_INICIO_INICIO]") > -1 ) {
			int indexReplace = query.indexOf("[DATA_INICIO_INICIO]");
			query.replace(indexReplace, indexReplace+20, "'"+dataInicioInicio+"'");
		}
		
		while ( query.indexOf("[DATA_INICIO_FIM]") > -1 ) {
			int indexReplace = query.indexOf("[DATA_INICIO_FIM]");
			query.replace(indexReplace, indexReplace+17, "'"+dataInicioFim+"'");
		}
		
		while ( query.indexOf("[SERVICO_ID]") > -1 ) {
			int indexReplace = query.indexOf("[SERVICO_ID]");
			query.replace(indexReplace, indexReplace+12, "'"+servicoId+"'");
		}
		
		List<AgendaPeriodicoDto> agendaPeriodicos = new ArrayList<AgendaPeriodicoDto>();

		Session session = HibernateHelper.getSession();
		
		try {
			List<Object[]> list = session.createSQLQuery(query.toString()).list();
			AgendaPeriodicoDto agendaPeriodico = null;
			
			for(Object[] row : list) {
				agendaPeriodico = new AgendaPeriodicoDto();
				
				agendaPeriodico.setEmpregadoId((int)row[0]);
				agendaPeriodico.setEmpregadoNome((String)row[1]);
				if ( sdf.parse(row[3].toString()).before(Helper.getToday()) && 
						( !((String)row[2]).equals("ABERTA") && !((String)row[2]).equals("CANCELADA") && !((String)row[2]).equals("FINALIZADO") ) )
					agendaPeriodico.setStatus("PENDENTE");
				else if ( ((String)row[2]).equals("ABERTA") )
					agendaPeriodico.setStatus( "AGENDADO" );
				else agendaPeriodico.setStatus((String)row[2]); 
				agendaPeriodico.setData((String)row[3].toString());
				agendaPeriodico.setNomeServico((String)row[4].toString());
				try {
					agendaPeriodico.setPendencias((String)row[5].toString());
				} catch(NullPointerException ex) {
					agendaPeriodico.setPendencias("");
				}
				
				agendaPeriodicos.add(agendaPeriodico);
			}
		}catch (Exception ex) {
			throw ex;
		}
		finally {
			HibernateHelper.close(session);
		}
		
		return agendaPeriodicos;
	}
}
