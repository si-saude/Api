package br.com.saude.api.model.persistence.report;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;

import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.HibernateHelper;
import br.com.saude.api.model.entity.dto.ConformidadeAsoDto;

public class ConformidadeAsoReport {
	private static ConformidadeAsoReport instance;

	private ConformidadeAsoReport() {

	}

	public static ConformidadeAsoReport getInstance() {
		if (instance == null)
			instance = new ConformidadeAsoReport();
		return instance;
	}

	public List<ConformidadeAsoDto> getAsos() throws Exception {

		StringBuffer query = parseSqlToString();

		return performQuery(query);
	}
	
	public List<ConformidadeAsoDto> getAsosByAno(int ano) throws Exception {

		StringBuffer query = parseSqlToString();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.YEAR, ano);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		int indexReplace = query.indexOf("[DATA_INICIO]");
		query.replace(indexReplace, indexReplace+13, "'"+sdf.format(calendar.getTime())+"'");
		
		indexReplace = query.indexOf("[DATA_INICIO]");
		query.replace(indexReplace, indexReplace+13, "'"+sdf.format(calendar.getTime())+"'");
		
		calendar.set(Calendar.DATE, 31);
		calendar.set(Calendar.MONTH, 11);
		
		indexReplace = query.indexOf("[DATA_FIM]");
		query.replace(indexReplace, indexReplace+10, "'"+sdf.format(calendar.getTime())+"'");
		
		indexReplace = query.indexOf("[DATA_FIM]");
		query.replace(indexReplace, indexReplace+10, "'"+sdf.format(calendar.getTime())+"'");
		
		return performQuery(new StringBuffer(query));
	}


	private StringBuffer parseSqlToString() throws FileNotFoundException, IOException {
		BufferedReader in = new BufferedReader(new FileReader(
				Helper.getProjectPath().replace("file:/", "")
						+ "br/com/saude/api/model/persistence/sql/QueryConformidadeAso.sql"));
		String str;
		StringBuffer query = new StringBuffer();
		while ((str = in.readLine()) != null) {
			query.append(str + "\n ");
		}
		in.close();
		return query;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	private List<ConformidadeAsoDto> performQuery(StringBuffer query) throws Exception {
		List<ConformidadeAsoDto> asos = new ArrayList<ConformidadeAsoDto>();

		Session session = HibernateHelper.getSession();
		List<Object[]> list = new ArrayList<Object[]>();
		
		try {
			list = session.createSQLQuery(query.toString()).list();
		} catch (Exception ex) {
			throw ex;
		} finally {
			HibernateHelper.close(session);
		}
		
		ConformidadeAsoDto aso = null;

		for (Object[] row : list) {
			aso = new ConformidadeAsoDto();
			
			Calendar calendar1 = Calendar.getInstance();
			
			aso.setNome((String) row[0]);
			aso.setMatricula((String) row[1]);
			aso.setGerencia((String) row[2]);
			aso.setBase((String) row[3]);
			aso.setConforme((boolean) row[4]);
			
			calendar1.setTimeInMillis((long) ((Timestamp)row[5]).getTime());
			aso.setDataRealizacao(calendar1.get(Calendar.DATE) + "/" +
					((Integer)(calendar1.get(Calendar.MONTH)+1)).toString() + "/" +
					calendar1.get(Calendar.YEAR));
			
			calendar1.setTimeInMillis((long) ((Timestamp)row[6]).getTime());
			aso.setDataValidade(calendar1.get(Calendar.DATE) + "/" +
					((Integer)(calendar1.get(Calendar.MONTH)+1)).toString() + "/" +
					calendar1.get(Calendar.YEAR));
			
			asos.add(aso);
		}

		return asos;
	}
}
