package br.com.saude.api.model.persistence.report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import br.com.saude.api.generic.HibernateHelper;
import br.com.saude.api.model.entity.dto.SugestaoAgendamentoDto;

public class SugestaoAgendamentoReport {

private static SugestaoAgendamentoReport instance;
	
	private SugestaoAgendamentoReport() {
		
	}
	
	public static SugestaoAgendamentoReport getInstance() {
		if(instance == null)
			instance = new SugestaoAgendamentoReport();
		return instance;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked", "static-access" })
	public List<SugestaoAgendamentoDto> getSugestaoAgendamentos(int idConvocacao) throws IOException {
		List<SugestaoAgendamentoDto> sugestaoAgendamentos = new ArrayList<SugestaoAgendamentoDto>();

		Session session = HibernateHelper.getSession();
		List<Object[]> list = new ArrayList<Object[]>();
		
		try {
			list = session.createSQLQuery("select * from get_sugestao_agendamento("+idConvocacao+")").list();
		}catch (Exception ex) {
			throw ex;
		}
		finally {
			HibernateHelper.close(session);
		}
		
		SugestaoAgendamentoDto sugestaoAgendamento = null;
		
		for(Object[] row : list) {
			sugestaoAgendamento = new SugestaoAgendamentoDto();
			
			sugestaoAgendamento.setGerencia((String)row[0]);
			sugestaoAgendamento.setNome((String)row[1]);
			sugestaoAgendamento.setMatricula((String)row[2]);
			Calendar calendar = Calendar.getInstance();
			if ( (Date)row[3] != null ) {
				calendar.setTime((Date)row[3]);
				sugestaoAgendamento.setData((String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)).length() == 1 ? "0"+String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) : calendar.get(Calendar.DAY_OF_MONTH))+"/"+
						(String.valueOf(calendar.get(Calendar.MONTH)+1).length() == 1 ? "0"+String.valueOf(calendar.get(Calendar.MONTH)+1) : (calendar.get(Calendar.MONTH)+1)) +"/"+
						calendar.get(Calendar.YEAR));
			} else sugestaoAgendamento.setData("");
			if ( (Date)row[4] != null ) {
				calendar.setTime((Date)row[4]);
				sugestaoAgendamento.setValidade(calendar.get(Calendar.YEAR)+"-"+
						(String.valueOf(calendar.get(Calendar.MONTH)+1).length() == 1 ? "0"+String.valueOf(calendar.get(Calendar.MONTH)+1) : (calendar.get(Calendar.MONTH)+1)) +"-"+
						(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)).length() == 1 ? "0"+String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) : calendar.get(Calendar.DAY_OF_MONTH)));
			} else sugestaoAgendamento.setValidade("");
			if ( (Date)row[5] != null ) {
				calendar.setTime((Date)row[5]);
				sugestaoAgendamento.setSugestao(calendar.get(Calendar.YEAR)+"-"+
						(String.valueOf(calendar.get(Calendar.MONTH)+1).length() == 1 ? "0"+String.valueOf(calendar.get(Calendar.MONTH)+1) : (calendar.get(Calendar.MONTH)+1)) +"-"+
						(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)).length() == 1 ? "0"+String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) : calendar.get(Calendar.DAY_OF_MONTH)));
			}
			
			sugestaoAgendamentos.add(sugestaoAgendamento);
		}
		
		return sugestaoAgendamentos;
	}
}
