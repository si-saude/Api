package br.com.saude.api.model.persistence.report;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.com.saude.api.generic.HibernateHelper;
import br.com.saude.api.model.entity.dto.PreRequisitosAgendamentoDto;

public class PreRequisitosAgendamentoReport {
private static PreRequisitosAgendamentoReport instance;
	
	private PreRequisitosAgendamentoReport() {
		
	}
	
	public static PreRequisitosAgendamentoReport getInstance() {
		if(instance == null)
			instance = new PreRequisitosAgendamentoReport();
		return instance;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<PreRequisitosAgendamentoDto> getPanoramas() throws IOException {
		
		BufferedReader in = new BufferedReader(new FileReader(getClass().getProtectionDomain()
				.getCodeSource().getLocation().toString().replace("file:/", "")
				+"br/com/saude/api/model/persistence/sql/QueryPreRequisitosAgendamento.sql"));
		String str;
		StringBuffer query = new StringBuffer();
		while ((str = in.readLine()) != null) {
			query.append(str + "\n ");
		}
		in.close();
		
		List<PreRequisitosAgendamentoDto> preRequisitosAgendamentos = new ArrayList<PreRequisitosAgendamentoDto>();

		Session session = HibernateHelper.getSession();
		
		try {
			List<Object[]> list = session.createSQLQuery(query.toString()).list();
			PreRequisitosAgendamentoDto preRequisitosAgendamento = null;
			
			for(Object[] row : list) {
				preRequisitosAgendamento = new PreRequisitosAgendamentoDto();
				
				preRequisitosAgendamento.setMatricula((String) row[0]);
				preRequisitosAgendamento.setChave((String)row[1]);
				preRequisitosAgendamento.setNome((String)row[2]);
				preRequisitosAgendamento.setGerencia((String)row[3]);
				preRequisitosAgendamento.setBase((String)row[4]);
				preRequisitosAgendamento.setResultadoAuditado((boolean)row[6]);
				preRequisitosAgendamento.setAgendado(row[7] != null ? (int)row[7] : 0);
				preRequisitosAgendamento.setTitulo((String)row[8]);
				preRequisitosAgendamento.setStatusPreclinico((String)row[9]);
				preRequisitosAgendamento.setDatasPreclinico((String)row[10]);
				preRequisitosAgendamento.setExamesPendentes((String)row[11]);

				preRequisitosAgendamentos.add(preRequisitosAgendamento);
			}
		}catch (Exception ex) {
			throw ex;
		}
		finally {
			HibernateHelper.close(session);
		}
		
		return preRequisitosAgendamentos;
	}
}
