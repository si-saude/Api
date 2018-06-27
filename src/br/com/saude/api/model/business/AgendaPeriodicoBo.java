package br.com.saude.api.model.business;

import java.util.List;

import br.com.saude.api.generic.GenericReportBo;
import br.com.saude.api.model.entity.dto.AgendaPeriodicoDto;
import br.com.saude.api.model.persistence.report.AgendaPeriodicoReport;

public class AgendaPeriodicoBo implements GenericReportBo<AgendaPeriodicoDto> {
	
	private static AgendaPeriodicoBo instance;
	
	private AgendaPeriodicoBo() {
		
	}
	
	public static AgendaPeriodicoBo getInstance() {
		if(instance == null)
			instance = new AgendaPeriodicoBo();
		return instance;
	}
	
	public List<AgendaPeriodicoDto> getAgendaPeriodicos(String dataInicioInicio, String dataInicioFim, String servicoId) throws Exception{
		return AgendaPeriodicoReport.getInstance().getAgendaPeriodicos(dataInicioInicio, dataInicioFim, servicoId);
	}

}
