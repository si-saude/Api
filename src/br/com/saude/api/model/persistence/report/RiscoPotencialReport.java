package br.com.saude.api.model.persistence.report;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.HibernateHelper;
import br.com.saude.api.model.entity.dto.RiscoPotencialDto;
import br.com.saude.api.util.constant.StatusRPSat;

public class RiscoPotencialReport {
	
private static RiscoPotencialReport instance;
	
	private RiscoPotencialReport() {
		
	}
	
	public static RiscoPotencialReport getInstance() {
		if(instance == null)
			instance = new RiscoPotencialReport();
		return instance;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<RiscoPotencialDto> getRiscoPotenciais(String uf) throws IOException {
		
		BufferedReader in = new BufferedReader(new FileReader(Helper.getProjectPath().replace("file:/", "")
				+"br/com/saude/api/model/persistence/sql/QueryRiscoPotencial.sql"));
		String str;
		StringBuffer query = new StringBuffer();
		while ((str = in.readLine()) != null) {
			query.append(str + "\n ");
		}
		in.close();
		
		int indexReplace = query.indexOf("[BASE_ID]");
		query.replace(indexReplace, indexReplace+9, String.valueOf("'"+uf+"'"));
		
		List<RiscoPotencialDto> riscoPotenciais = new ArrayList<RiscoPotencialDto>();

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
		
		RiscoPotencialDto riscoPotencial = null;
		
		for(Object[] row : list) {
			riscoPotencial = new RiscoPotencialDto();
			
			riscoPotencial.setId((int) row[0]);
			riscoPotencial.setRanking((double)row[1]);
			
			if ( riscoPotencial.getRanking() > 0 && riscoPotencial.getRanking() < 0.57 )
				riscoPotencial.setStatusRPSat(StatusRPSat.getInstance().ACEITAVEL);
			else if ( riscoPotencial.getRanking() >= 0.57 && riscoPotencial.getRanking() < 0.725 )
				riscoPotencial.setStatusRPSat(StatusRPSat.getInstance().TOLERAVEL);
			else if ( riscoPotencial.getRanking() >= 0.725 )
				riscoPotencial.setStatusRPSat(StatusRPSat.getInstance().INACEITAVEL);
				
			riscoPotencial.setEmpregadoNome((String)row[2]);
			riscoPotencial.setEquipeResponsavelNome((String)row[3]);
			riscoPotencial.setData((String)row[4]);
			riscoPotencial.setStatus((String)row[5]);
			riscoPotencial.setAbreviacaoEquipeAcolhimento((String)row[6]);
				
			riscoPotenciais.add(riscoPotencial);
		}
		
		return riscoPotenciais;
	}
}
