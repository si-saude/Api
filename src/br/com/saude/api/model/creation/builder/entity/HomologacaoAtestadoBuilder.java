package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.HomologacaoAtestadoFilter;
import br.com.saude.api.model.entity.po.HomologacaoAtestado;

public class HomologacaoAtestadoBuilder extends GenericEntityBuilder<HomologacaoAtestado, HomologacaoAtestadoFilter> {

	public static HomologacaoAtestadoBuilder newInstance(HomologacaoAtestado homologacaoAtestado) {
		return new HomologacaoAtestadoBuilder(homologacaoAtestado);
	}
	
	public static HomologacaoAtestadoBuilder newInstance(List<HomologacaoAtestado> homologacaoAtestados) {
		return new HomologacaoAtestadoBuilder(homologacaoAtestados);
	}
	
	private HomologacaoAtestadoBuilder(List<HomologacaoAtestado> homologacaoAtestados) {
		super(homologacaoAtestados);
	}

	private HomologacaoAtestadoBuilder(HomologacaoAtestado homologacaoAtestado) {
		super(homologacaoAtestado);
	}

	@Override
	protected void initializeFunctions() {}

	@Override
	protected HomologacaoAtestado clone(HomologacaoAtestado homologacaoAtestado) {
		HomologacaoAtestado newHomologacaoAtestado = new HomologacaoAtestado();
		
		newHomologacaoAtestado.setId(homologacaoAtestado.getId());
		newHomologacaoAtestado.setDataEntrega(homologacaoAtestado.getDataEntrega());
		newHomologacaoAtestado.setDataHomologacao(homologacaoAtestado.getDataHomologacao());
		newHomologacaoAtestado.setObservacao(homologacaoAtestado.getObservacao());
		newHomologacaoAtestado.setStatus(homologacaoAtestado.getStatus());
		newHomologacaoAtestado.setVersion(homologacaoAtestado.getVersion());
		
		if(homologacaoAtestado.getProfissional() != null )
			newHomologacaoAtestado.setProfissional(ProfissionalBuilder.newInstance(homologacaoAtestado.getProfissional()).getEntity());
		
		if(homologacaoAtestado.getAtestado() != null)
			newHomologacaoAtestado.setAtestado(AtestadoBuilder.newInstance(homologacaoAtestado.getAtestado()).getEntity());
		
		return newHomologacaoAtestado;
	}

	@Override
	public HomologacaoAtestado cloneFromFilter(HomologacaoAtestadoFilter filter) {
		return null;
	}
	
}
