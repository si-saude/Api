package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.AuditoriaAtestadoFilter;
import br.com.saude.api.model.entity.po.AuditoriaAtestado;

public class AuditoriaAtestadoBuilder extends GenericEntityBuilder<AuditoriaAtestado, AuditoriaAtestadoFilter> {
	public static AuditoriaAtestadoBuilder newInstance(AuditoriaAtestado auditoriaAtestado) {
		return new AuditoriaAtestadoBuilder(auditoriaAtestado);
	}
	
	public static AuditoriaAtestadoBuilder newInstance(List<AuditoriaAtestado> auditoriaAtestados) {
		return new AuditoriaAtestadoBuilder(auditoriaAtestados);
	}
	
	private AuditoriaAtestadoBuilder(AuditoriaAtestado auditoriaAtestado) {
		super(auditoriaAtestado);
	}
	
	private AuditoriaAtestadoBuilder(List<AuditoriaAtestado> auditoriaAtestados) {
		super(auditoriaAtestados);
	}

	@Override
	protected void initializeFunctions() {}

	@Override
	protected AuditoriaAtestado clone(AuditoriaAtestado auditoriaAtestado) {
		AuditoriaAtestado newAuditoriaAtestado = new AuditoriaAtestado();
		
		newAuditoriaAtestado.setId(auditoriaAtestado.getId());
		newAuditoriaAtestado.setVersion(auditoriaAtestado.getVersion());
		newAuditoriaAtestado.setConforme(auditoriaAtestado.isConforme());
		
		if(auditoriaAtestado.getAtestado() != null)
			newAuditoriaAtestado.setAtestado(AtestadoBuilder
					.newInstance(auditoriaAtestado.getAtestado()).getEntity());
		
		if (auditoriaAtestado.getItemAuditoriaAtestado() != null)
			newAuditoriaAtestado.setItemAuditoriaAtestado(
					ItemAuditoriaAtestadoBuilder.newInstance(auditoriaAtestado.getItemAuditoriaAtestado()).getEntity());
		
		return newAuditoriaAtestado;
	}
	
	@Override
	public AuditoriaAtestado cloneFromFilter(AuditoriaAtestadoFilter filter) {
		return null;
	}
}
