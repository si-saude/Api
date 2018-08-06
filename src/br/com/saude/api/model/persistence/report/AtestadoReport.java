package br.com.saude.api.model.persistence.report;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;

import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.HibernateHelper;
import br.com.saude.api.model.business.FeriadoBo;
import br.com.saude.api.model.business.ProfissionalBo;
import br.com.saude.api.model.entity.dto.AtestadoDto;
import br.com.saude.api.model.entity.po.Profissional;

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
		List<Object[]> list = new ArrayList<Object[]>();
		
		try {
			list = session.createSQLQuery(query.toString()).list();
		} catch (Exception ex) {
			throw ex;
		} finally {
			HibernateHelper.close(session);
		}
		
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
			atestado.setNumeroCat((String) row[13]);
			atestado.setNumeroDias((int) row[14]);
			atestado.setDataEntrega((String) row[15]);
			atestado.setDataHomologacao((String) row[16]);
			atestado.setNomeProfissionalHomologacao((String) row[17]);
			atestado.setObservacao((String) row[18]);
			
			DateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
			
			//CALCULA O FIM DO ATESTADO
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(sdf.parse(atestado.getInicio()));
			FeriadoBo.getInstance().getValidDates(calendar, atestado.getNumeroDias() - 1);
			atestado.setFim(sdf.format(calendar.getTime()));
			
			//CALCULA O PRAZO RECEBIMENTO
			calendar.setTime(sdf.parse(atestado.getDataSolicitacao()));
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(sdf.parse(atestado.getInicio()));
			atestado.setPrazoRecebimento(FeriadoBo.getInstance().getDaysBetweenDates(calendar, calendar2));
			
			//SETA BOOLEANO RECEBIDO NO PRAZO
			if ( atestado.getPrazoRecebimento() <= 3 )
				atestado.setRecebidoNoPrazo(true);
			
			//SETA MES DE RECEBIMENTO
			calendar.setTime(sdf.parse(atestado.getDataSolicitacao()));
			atestado.setMesRecebimento(
					Helper.getStringMonth(calendar.get(Calendar.MONTH)));
			
			//CALCULA O PRAZO DA HOMOLOGACAO
			if ( atestado.getDataEntrega() != null )
				calendar.setTime(sdf.parse(atestado.getDataEntrega()));
			if ( atestado.getDataHomologacao() != null )
				calendar2.setTime(sdf.parse(atestado.getDataHomologacao()));
			if ( atestado.getDataEntrega() != null && atestado.getDataHomologacao() != null ) {
				atestado.setPrazoHomologacao(FeriadoBo.getInstance().getDaysBetweenDates(calendar, calendar2));
				//SETA HOMOLOGADO NO PRAZO
				if ( atestado.getPrazoHomologacao() <= 3 )
					atestado.setHomologacaoNoPrazo(true);
				//SETA MES HOMOLOGACAO
				atestado.setMesHomologacao(Helper.getStringMonth(calendar2.get(Calendar.MONTH)));
			}
			
			//SETA EQUIPE PROFISSIONAL HOMOLOGACAO
			if ( row[19] != null ) {
				Profissional profissional = ProfissionalBo.getInstance().getById((int) row[19]);
				atestado.setMedicoOdonto(profissional.getEquipe().getNome());
			}
			
			atestados.add(atestado);
		}

		return atestados;
	}

}
