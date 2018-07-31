package br.com.saude.api.model.business;

import java.io.IOException;
import java.util.List;

import br.com.saude.api.generic.GenericReportBo;
import br.com.saude.api.model.entity.dto.PreRequisitosAgendamentoDto;
import br.com.saude.api.model.persistence.report.PreRequisitosAgendamentoReport;

public class PreRequisitosAgendamentoBo implements GenericReportBo<PreRequisitosAgendamentoDto>
{

	private static PreRequisitosAgendamentoBo instance;
	
	private PreRequisitosAgendamentoBo() {
		
	}
	
	public static PreRequisitosAgendamentoBo getInstance() {
		if(instance == null)
			instance = new PreRequisitosAgendamentoBo();
		return instance;
	}
	
	public List<PreRequisitosAgendamentoDto> getPanoramas() throws IOException{
		return PreRequisitosAgendamentoReport.getInstance().getPanoramas();
	}
}