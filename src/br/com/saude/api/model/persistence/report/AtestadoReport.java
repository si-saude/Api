package br.com.saude.api.model.persistence.report;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.com.saude.api.generic.HibernateHelper;
import br.com.saude.api.model.entity.dto.AtestadoDto;

public class AtestadoReport {

	private static AtestadoReport instance;

	private AtestadoReport() {

	}

	public static AtestadoReport getInstance() {
		if (instance == null)
			instance = new AtestadoReport();
		return instance;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<AtestadoDto> getAtestados() throws Exception {

		BufferedReader in = new BufferedReader(new FileReader(
				getClass().getProtectionDomain().getCodeSource().getLocation().toString().replace("file:/", "")
						+ "br/com/saude/api/model/persistence/sql/QueryAtestado.sql"));
		String str;
		StringBuffer query = new StringBuffer();
		while ((str = in.readLine()) != null) {
			query.append(str + "\n ");
		}
		in.close();

		List<AtestadoDto> atestados = new ArrayList<AtestadoDto>();

		Session session = HibernateHelper.getSession();

		try {
			List<Object[]> list = session.createSQLQuery(query.toString()).list();
			AtestadoDto atestado = null;

			for (Object[] row : list) {
				atestado = new AtestadoDto();

				atestado.setNomeEmpregado((String) row[0]);
				atestado.setCid((String) row[1]);
				atestado.setInicio((String) row[2]);
				atestado.setImpossibilidadeLocomocao((boolean)row[3]);
				atestado.setStatus((String)row[4]);
				atestado.setLancadoSap((boolean) row[5]);
				atestado.setAtestadoFisicoRecebido((boolean) row[6]);
				atestado.setControleLicenca((boolean) row[7]);
				atestado.setDataSolicitacao((String) row[8]);
				atestado.setDataAgendamento((String) row[9]);
				atestado.setNumeroCat((String)row[10]);
				atestado.setNumeroDias((int) row[11]);

				atestados.add(atestado);
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			HibernateHelper.close(session);
		}

		return atestados;
	}

}
