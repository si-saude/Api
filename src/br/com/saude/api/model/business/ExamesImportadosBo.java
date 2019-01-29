package br.com.saude.api.model.business;

import java.io.IOException;
import java.util.List;

import br.com.saude.api.generic.GenericReportBo;
import br.com.saude.api.model.entity.dto.ExamesImportadosDto;
import br.com.saude.api.model.persistence.report.ExamesImportadosReport;

public class ExamesImportadosBo implements GenericReportBo<ExamesImportadosDto>
{

	private static ExamesImportadosBo instance;
	
	private ExamesImportadosBo() {
		
	}
	
	public static ExamesImportadosBo getInstance() {
		if(instance == null)
			instance = new ExamesImportadosBo();
		return instance;
	}
	
	public List<ExamesImportadosDto> getExamesImportados() throws IOException{
		return ExamesImportadosReport.getInstance().getExamesImportados();
	}
}
