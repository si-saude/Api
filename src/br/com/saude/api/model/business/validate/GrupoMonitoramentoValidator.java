package br.com.saude.api.model.business.validate;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.GrupoMonitoramento;

public class GrupoMonitoramentoValidator extends GenericValidator<GrupoMonitoramento> {

	
	@Override
	public void validate(GrupoMonitoramento grupoMonitoramento) throws Exception {
		super.validate(grupoMonitoramento);
		
		if(grupoMonitoramento.getGrupoMonitoramentoExames() != null)
			new GrupoMonitoramentoExameValidator().validate(grupoMonitoramento.getGrupoMonitoramentoExames());
	}
}
