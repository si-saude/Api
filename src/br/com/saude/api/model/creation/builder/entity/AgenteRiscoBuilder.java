package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.AgenteRiscoFilter;
import br.com.saude.api.model.entity.po.AgenteRisco;

public class AgenteRiscoBuilder extends GenericEntityBuilder<AgenteRisco, AgenteRiscoFilter> {

	public static AgenteRiscoBuilder newInstance(AgenteRisco agenteRisco) {
		return new AgenteRiscoBuilder(agenteRisco);
	}
	
	public static AgenteRiscoBuilder newInstance(List<AgenteRisco> agenteRiscos) {
		return new AgenteRiscoBuilder(agenteRiscos);
	}
	
	private AgenteRiscoBuilder(List<AgenteRisco> agenteRiscos) {
		super(agenteRiscos);
	}

	private AgenteRiscoBuilder(AgenteRisco agenteRisco) {
		super(agenteRisco);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected AgenteRisco clone(AgenteRisco agenteRisco) {
		AgenteRisco newAgenteRisco = new AgenteRisco();
		
		newAgenteRisco.setId(agenteRisco.getId());
		newAgenteRisco.setDescricao(agenteRisco.getDescricao());
		newAgenteRisco.setCategoriaAgenteRisco(agenteRisco.getCategoriaAgenteRisco());
		newAgenteRisco.setVersion(agenteRisco.getVersion());
		
		return newAgenteRisco;
	}

	@Override
	public AgenteRisco cloneFromFilter(AgenteRiscoFilter filter) {
		return null;
	}
}
