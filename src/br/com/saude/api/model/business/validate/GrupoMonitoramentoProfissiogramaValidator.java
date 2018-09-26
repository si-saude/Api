package br.com.saude.api.model.business.validate;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.GrupoMonitoramentoProfissiograma;

public class GrupoMonitoramentoProfissiogramaValidator extends GenericValidator<GrupoMonitoramentoProfissiograma> {

	@Override
	public void validate(GrupoMonitoramentoProfissiograma grupoMonitoramentoProfissiograma) throws Exception {
		super.validate(grupoMonitoramentoProfissiograma);
		
		if(grupoMonitoramentoProfissiograma.getGrupoMonitoramentoProfissiogramaExames() != null)
			new GrupoMonitoramentoProfissiogramaExameValidator().validate(grupoMonitoramentoProfissiograma.getGrupoMonitoramentoProfissiogramaExames());
	}
}
