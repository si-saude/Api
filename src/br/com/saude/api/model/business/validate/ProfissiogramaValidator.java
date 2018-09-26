package br.com.saude.api.model.business.validate;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Profissiograma;

public class ProfissiogramaValidator extends GenericValidator<Profissiograma> {

	@Override
	public void validate(Profissiograma profissiograma) throws Exception {
		super.validate(profissiograma);
		
		if(profissiograma.getGrupoMonitoramentoProfissiogramas() != null)
			new GrupoMonitoramentoProfissiogramaValidator().validate(profissiograma.getGrupoMonitoramentoProfissiogramas());
	}
}
