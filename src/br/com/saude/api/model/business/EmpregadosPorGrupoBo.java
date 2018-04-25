package br.com.saude.api.model.business;

import java.io.IOException;
import java.util.List;

import br.com.saude.api.model.entity.dto.EmpregadosPorGrupoDto;
import br.com.saude.api.model.persistence.report.EmpregadosPorGrupoReport;

public class EmpregadosPorGrupoBo {
	
	private static EmpregadosPorGrupoBo instance;
	
	private EmpregadosPorGrupoBo() {
		
	}
	
	public static EmpregadosPorGrupoBo getInstance() {
		if(instance == null)
			instance = new EmpregadosPorGrupoBo();
		return instance;
	}
	
	public List<EmpregadosPorGrupoDto> getEmpregadosPorGrupo(int id) throws IOException{
		return EmpregadosPorGrupoReport.getInstance().getEmpregadosPorGrupo(id);
	}
}
