package br.com.saude.api.model.persistence.report;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.HibernateHelper;
import br.com.saude.api.model.entity.dto.ExamesImportadosDto;

public class ExamesImportadosReport {
	private static ExamesImportadosReport instance;
	
	private ExamesImportadosReport() {
		
	}
	
	public static ExamesImportadosReport getInstance() {
		if(instance == null)
			instance = new ExamesImportadosReport();
		return instance;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<ExamesImportadosDto> getExamesImportados() throws IOException {
		
		BufferedReader in = new BufferedReader(new FileReader(Helper.getProjectPath().replace("file:/", "")
				+"br/com/saude/api/model/persistence/sql/QueryExamesImportados.sql"));
		String str;
		StringBuffer query = new StringBuffer();
		while ((str = in.readLine()) != null) {
			query.append(str + "\n ");
		}
		in.close();
		
		List<ExamesImportadosDto> examesImportados = new ArrayList<ExamesImportadosDto>();

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
		
		ExamesImportadosDto examesImportado = null;
		
		for(Object[] row : list) {
			examesImportado = new ExamesImportadosDto();
			
			examesImportado.setMatricula((String) row[0]);
			examesImportado.setChave((String)row[1]);
			examesImportado.setNome((String)row[2]);
			examesImportado.setGerencia((String)row[3]);
			examesImportado.setBase((String)row[4]);
			examesImportado.setResultadoAuditado((boolean)row[7]);
			examesImportado.setTitulo((String)row[8]);
			examesImportado.setStatusPreclinico((String)row[9]);
			examesImportado.setDatasPreclinico((String)row[10]);
			examesImportado.setExamesPendentes((String)row[11]);
			examesImportado.setExamesImportados((String)row[12]);

			examesImportados.add(examesImportado);
		}
		
		return examesImportados;
	}
}
