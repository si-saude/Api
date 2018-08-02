package br.com.saude.api.model.persistence.report;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.HibernateHelper;
import br.com.saude.api.generic.TypeFilter;
import br.com.saude.api.model.business.FeriadoBo;
import br.com.saude.api.model.entity.dto.AtestadoDto;
import br.com.saude.api.model.entity.filter.FeriadoFilter;
import br.com.saude.api.model.entity.po.Feriado;

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
				atestado.setMatricula((String) row[2]);
				atestado.setGerencia((String) row[3]);
				atestado.setBase((String) row[4]);
				atestado.setInicio((String) row[5]);
				atestado.setImpossibilidadeLocomocao((boolean)row[6]);
				atestado.setStatus((String)row[7]);
				atestado.setLancadoSap((boolean) row[8]);
				atestado.setAtestadoFisicoRecebido((boolean) row[9]);
				atestado.setControleLicenca((boolean) row[10]);
				atestado.setDataSolicitacao((String) row[11]);
				atestado.setDataAgendamento((String) row[12]);
				atestado.setNumeroCat((String)row[13]);
				atestado.setNumeroDias((int) row[14]);
				
				DateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
				
				//CALCULA O FIM DO ATESTADO
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(sdf.parse(atestado.getInicio()));
				Helper.getValidDates(calendar, atestado.getNumeroDias() - 1);
				atestado.setFim(sdf.format(calendar.getTime()));
				
				//CALCULA O PRAZO RECEBIMENTO
				calendar.setTime(sdf.parse(atestado.getDataSolicitacao()));
				Calendar calendar2 = Calendar.getInstance();
				calendar2.setTime(sdf.parse(atestado.getInicio()));
				int prazoRecebimento = 0;
				
				if ( calendar.before(calendar2) )
					throw new Exception("Data de inicio maior que data do recebimento.");
				
				while ( !calendar.equals(calendar2) ) {
					FeriadoFilter feriadoFilter = new FeriadoFilter();
					feriadoFilter.setData(new DateFilter());
					feriadoFilter.getData().setInicio(calendar.getTime());
					feriadoFilter.getData().setTypeFilter(TypeFilter.IGUAL);
					ArrayList<Feriado> feriados = (ArrayList<Feriado>) FeriadoBo.getInstance().getList(feriadoFilter).getList();
					
					if( calendar.get(Calendar.DAY_OF_WEEK) <= 5 && feriados.size() == 0 ) {
						prazoRecebimento++;
					}
					calendar.add( Calendar.DAY_OF_MONTH, -1);
				}
				atestado.setPrazoRecebimento(prazoRecebimento);
				
				//SETA BOOLEANO RECEBIDO NO PRAZO
				if ( prazoRecebimento <= 2 )
					atestado.setRecebidoNoPrazo(true);
				else atestado.setRecebidoNoPrazo(false);
				
				
				//SETA MES DE RECEBIMENTO
				calendar.setTime(sdf.parse(atestado.getDataSolicitacao()));
				atestado.setMesRecebimento(
						Helper.getStringMonth(calendar.get(Calendar.MONTH)));

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
