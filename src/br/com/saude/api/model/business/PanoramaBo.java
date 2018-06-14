package br.com.saude.api.model.business;

import java.io.IOException;
import java.util.List;

import br.com.saude.api.model.entity.dto.PanoramaDto;
import br.com.saude.api.model.persistence.report.PanoramaReport;

public class PanoramaBo 
//	implements GenericReportBo<PanoramaDto>
{

	private static PanoramaBo instance;
	
	private PanoramaBo() {
		
	}
	
	public static PanoramaBo getInstance() {
		if(instance == null)
			instance = new PanoramaBo();
		return instance;
	}
	
	public List<PanoramaDto> getPanoramas() throws IOException{
		return PanoramaReport.getInstance().getPanoramas();
	}
}
