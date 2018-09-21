package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.HistoricoAtestadoFilter;
import br.com.saude.api.model.entity.po.HistoricoAtestado;

public class HistoricoAtestadoBuilder extends GenericEntityBuilder<HistoricoAtestado, HistoricoAtestadoFilter> {
	public static HistoricoAtestadoBuilder newInstance(HistoricoAtestado historicoAtestado) {
		return new HistoricoAtestadoBuilder(historicoAtestado);
	}
	
	public static HistoricoAtestadoBuilder newInstance(List<HistoricoAtestado> historicoAtestados) {
		return new HistoricoAtestadoBuilder(historicoAtestados);
	}
	
	private HistoricoAtestadoBuilder(List<HistoricoAtestado> historicoAtestados) {
		super(historicoAtestados);
	}

	private HistoricoAtestadoBuilder(HistoricoAtestado historicoAtestado) {
		super(historicoAtestado);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected HistoricoAtestado clone(HistoricoAtestado historicoAtestado) {
		HistoricoAtestado newHistoricoAtestado = new HistoricoAtestado();
		
		newHistoricoAtestado.setId(historicoAtestado.getId());
		newHistoricoAtestado.setData(historicoAtestado.getData());
		newHistoricoAtestado.setStatus(historicoAtestado.getStatus());
		newHistoricoAtestado.setVersion(historicoAtestado.getVersion());
		
		if ( historicoAtestado.getProfissional() != null )
			newHistoricoAtestado.setProfissional(ProfissionalBuilder.newInstance(historicoAtestado.getProfissional())
					.getEntity());
		
		return newHistoricoAtestado;
	}

	@Override
	public HistoricoAtestado cloneFromFilter(HistoricoAtestadoFilter filter) {
		return null;
	}
}
