package br.com.saude.api.model.persistence.report;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.HibernateHelper;
import br.com.saude.api.model.entity.dto.SolicitacaoCentralIntegraDto;

public class SolicitacaoCentralIntegraReport {
	
private static SolicitacaoCentralIntegraReport instance;
	
	private SolicitacaoCentralIntegraReport() {
		
	}
	
	public static SolicitacaoCentralIntegraReport getInstance() {
		if(instance == null)
			instance = new SolicitacaoCentralIntegraReport();
		return instance;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<SolicitacaoCentralIntegraDto> getSolicitacaoCentralIntegra(String cpf) throws IOException {
		
		BufferedReader in = new BufferedReader(new FileReader(Helper.getProjectPath().replace("file:/", "")
				+"br/com/saude/api/model/persistence/sql/QuerySolicitacaoCentralIntegra.sql"));
		String str;
		StringBuffer query = new StringBuffer();
		while ((str = in.readLine()) != null) {
			query.append(str + "\n ");
		}
		in.close();
		
		int indexReplace = query.indexOf("[CLIENTE_CPF]");
		query.replace(indexReplace, indexReplace+13, "'"+cpf+"'");
		
		List<SolicitacaoCentralIntegraDto> solicitacaoCentralIntegras = new ArrayList<SolicitacaoCentralIntegraDto>();

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
		
		SolicitacaoCentralIntegraDto solicitacaoCentralIntegra = null;
		
		for(Object[] row : list) {
			solicitacaoCentralIntegra = new SolicitacaoCentralIntegraDto();
			
			solicitacaoCentralIntegra.setId((int)row[0]);
			solicitacaoCentralIntegra.setNome((String)row[1]);
			solicitacaoCentralIntegra.setDescricao((String)row[2]);
			solicitacaoCentralIntegra.setPrazo((String)row[3]);
			solicitacaoCentralIntegra.setAbertura((String)row[4]);
			solicitacaoCentralIntegra.setAtrasado((boolean)row[5]);
			solicitacaoCentralIntegra.setStatus((String)row[6]);
			solicitacaoCentralIntegra.setObservacao((String)row[7]);
			solicitacaoCentralIntegra.setConcluido((boolean)row[8]);
			
			solicitacaoCentralIntegras.add(solicitacaoCentralIntegra);
		}
		
		return solicitacaoCentralIntegras;
	}
}
