package br.com.saude.api.model.persistence.report;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;

import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.HibernateHelper;
import br.com.saude.api.model.business.FeriadoBo;
import br.com.saude.api.model.entity.dto.ControleAtestadoDto;

public class ControleAtestadoReport {

	private static ControleAtestadoReport instance;

	private ControleAtestadoReport() {

	}

	public static ControleAtestadoReport getInstance() {
		if (instance == null)
			instance = new ControleAtestadoReport();
		return instance;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<ControleAtestadoDto> getAtestados() throws Exception {

		BufferedReader in = new BufferedReader(new FileReader(
				Helper.getProjectPath().replace("file:/", "")
						+ "br/com/saude/api/model/persistence/sql/QueryControleAtestado.sql"));
		String str;
		StringBuffer query = new StringBuffer();
		while ((str = in.readLine()) != null) {
			query.append(str + "\n ");
		}
		in.close();

		List<ControleAtestadoDto> atestados = new ArrayList<ControleAtestadoDto>();

		Session session = HibernateHelper.getSession();
		List<Object[]> list = new ArrayList<Object[]>();
		
		try {
			list = session.createSQLQuery(query.toString()).list();
		} catch (Exception ex) {
			throw ex;
		} finally {
			HibernateHelper.close(session);
		}
		
		ControleAtestadoDto atestado = null;

		for (Object[] row : list) {
			atestado = new ControleAtestadoDto();
			
			Calendar calendar1 = Calendar.getInstance();
			Calendar calendar2 = Calendar.getInstance();

			atestado.setNomeEmpregado((String) row[0]);
			atestado.setMatricula((String) row[1]);
			atestado.setGerencia((String) row[2]);
			atestado.setBase((String) row[3]);
			
			calendar1.setTimeInMillis((long) ((Timestamp)row[4]).getTime());
			atestado.setInicioAtestado(calendar1.get(Calendar.DATE) + "/" +
					((Integer)(calendar1.get(Calendar.MONTH)+1)).toString() + "/" +
					calendar1.get(Calendar.YEAR));
			
			calendar1.setTimeInMillis((long) ((Timestamp)row[5]).getTime());
			atestado.setFimAtestado(calendar1.get(Calendar.DATE) + "/" +
					((Integer)(calendar1.get(Calendar.MONTH)+1)).toString() + "/" +
					calendar1.get(Calendar.YEAR));
			
			atestado.setNumeroDias((int) row[6]);
			
			calendar2.setTimeInMillis((long) ((Timestamp)row[7]).getTime());
			atestado.setDataRecebimento(calendar2.get(Calendar.DATE) + "/" +
					((Integer)(calendar2.get(Calendar.MONTH)+1)).toString() + "/" +
					calendar2.get(Calendar.YEAR));
			
			calendar1.setTimeInMillis((long) ((Timestamp)row[4]).getTime());
			atestado.setPrazoRecebimento(FeriadoBo.getInstance().getDaysBetweenDates(calendar1, calendar2));
			if ( atestado.getPrazoRecebimento() <= 3 )
				atestado.setRecebidoNoPrazo(true);
			else atestado.setRecebidoNoPrazo(false);
			
			atestado.setMesRecebimento(Helper.getStringMonth( Integer.parseInt((String) row[8]) - 1 ));
			
			if(row[9] != null) {
				calendar1.setTimeInMillis((long) ((Timestamp)row[9]).getTime());
				atestado.setDataEntrega(calendar1.get(Calendar.DATE) + "/" +
						((Integer)(calendar1.get(Calendar.MONTH)+1)).toString() + "/" +
						calendar1.get(Calendar.YEAR));
			}
			
			if(row[10] != null) {
				calendar2.setTimeInMillis((long) ((Timestamp)row[10]).getTime());
				atestado.setDataHomologacao(calendar1.get(Calendar.DATE) + "/" +
						((Integer)(calendar1.get(Calendar.MONTH)+1)).toString() + "/" +
						calendar1.get(Calendar.YEAR));
			}
			
			atestado.setHomologadoNoPrazo(true);
			if(row[9] != null && row[10] != null) {
				atestado.setPrazoHomologacao(FeriadoBo.getInstance().getDaysBetweenDates(calendar1, calendar2));
				if ( atestado.getPrazoHomologacao() <= (int) row[11] )
					atestado.setHomologadoNoPrazo(true);
				else atestado.setHomologadoNoPrazo(false);
				
				atestado.setMesHomologacao(Helper.getStringMonth( Integer.parseInt((String) row[12]) - 1 ));
			}
			
			
			if(row[13] != null)
				atestado.setAbreviacaoEquipe((String) row[13]);
			
			if(row[14] != null)
				atestado.setTarefaProfissional((String) row[14]);
			
			atestado.setAtestadoFisicoRecebido((boolean) row[15]);
			
			if(row[16] != null)
				atestado.setObservacao((String) row[16]);
			
			atestado.setStatusAtestado((String) row[17]);
			
			atestados.add(atestado);
		}

		return atestados;
	}

}
