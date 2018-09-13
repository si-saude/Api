package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.MotivoRecusaAtestadoFilter;
import br.com.saude.api.model.entity.po.MotivoRecusaAtestado;

public class MotivoRecusaAtestadoBuilder extends GenericEntityBuilder<MotivoRecusaAtestado, MotivoRecusaAtestadoFilter> {

	public static MotivoRecusaAtestadoBuilder newInstance(MotivoRecusaAtestado motivoRecusaAtestado) {
		return new MotivoRecusaAtestadoBuilder(motivoRecusaAtestado);
	}
	
	public static MotivoRecusaAtestadoBuilder newInstance(List<MotivoRecusaAtestado> motivoRecusaAtestados) {
		return new MotivoRecusaAtestadoBuilder(motivoRecusaAtestados);
	}
	
	private MotivoRecusaAtestadoBuilder(List<MotivoRecusaAtestado> motivoRecusaAtestados) {
		super(motivoRecusaAtestados);
	}

	private MotivoRecusaAtestadoBuilder(MotivoRecusaAtestado motivoRecusaAtestado) {
		super(motivoRecusaAtestado);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected MotivoRecusaAtestado clone(MotivoRecusaAtestado motivoRecusaAtestado) {
		MotivoRecusaAtestado newMotivoRecusaAtestado = new MotivoRecusaAtestado();
		
		newMotivoRecusaAtestado.setId(motivoRecusaAtestado.getId());
		newMotivoRecusaAtestado.setDescricao(motivoRecusaAtestado.getDescricao());
		newMotivoRecusaAtestado.setPermiteReabertura(motivoRecusaAtestado.isPermiteReabertura());
		newMotivoRecusaAtestado.setVersion(motivoRecusaAtestado.getVersion());
		
		return newMotivoRecusaAtestado;
	}

	@Override
	public MotivoRecusaAtestado cloneFromFilter(MotivoRecusaAtestadoFilter filter) {
		return null;
	}

}
