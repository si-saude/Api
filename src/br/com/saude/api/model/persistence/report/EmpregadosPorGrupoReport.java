package br.com.saude.api.model.persistence.report;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.com.saude.api.generic.HibernateHelper;
import br.com.saude.api.model.entity.dto.EmpregadosPorGrupoDto;

public class EmpregadosPorGrupoReport {

	private static EmpregadosPorGrupoReport instance;
	
	private EmpregadosPorGrupoReport() {
		
	}
	
	public static EmpregadosPorGrupoReport getInstance() {
		if(instance == null)
			instance = new EmpregadosPorGrupoReport();
		return instance;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<EmpregadosPorGrupoDto> getEmpregadosPorGrupo(int id) throws IOException {
		
		BufferedReader in = new BufferedReader(new FileReader(getClass().getProtectionDomain()
				.getCodeSource().getLocation().toString().replace("file:/", "")
				+"br/com/saude/api/model/persistence/sql/QueryEmpregadosPorGrupo.sql"));
		String str;
		StringBuffer query = new StringBuffer();
		while ((str = in.readLine()) != null) {
			query.append(str + "\n ");
		}
		in.close();
		
		int indexReplace = query.indexOf("[GRUPOMONITORAMENTO_ID]");
		query.replace(indexReplace, indexReplace+23, String.valueOf(id));
		
		List<EmpregadosPorGrupoDto> empregadosPorGrupos = new ArrayList<EmpregadosPorGrupoDto>();

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
		
		EmpregadosPorGrupoDto empregadosPorGrupo = null;
		
		for(Object[] row : list) {
			empregadosPorGrupo = new EmpregadosPorGrupoDto();
			
			empregadosPorGrupo.setNome((String)row[0]);
			empregadosPorGrupo.setChave((String)row[1]);
			empregadosPorGrupo.setMatricula((String)row[2]);
			empregadosPorGrupo.setGerencia((String)row[3]);
			
			empregadosPorGrupos.add(empregadosPorGrupo);
		}
		
		return empregadosPorGrupos;
	}
}
