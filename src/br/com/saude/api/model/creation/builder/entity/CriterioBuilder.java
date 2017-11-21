package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.CriterioFilter;
import br.com.saude.api.model.entity.po.Criterio;

public class CriterioBuilder extends GenericEntityBuilder<Criterio,CriterioFilter> {

	public static CriterioBuilder newInstance(Criterio criterio) {
		return new CriterioBuilder(criterio);
	}
	
	public static CriterioBuilder newInstance(List<Criterio> criterios) {
		return new CriterioBuilder(criterios);
	}
	
	private CriterioBuilder(List<Criterio> criterios) {
		super(criterios);
	}

	private CriterioBuilder(Criterio criterio) {
		super(criterio);
	}

	@Override
	protected void initializeFunctions() {
		
	}
	
	@Override
	protected Criterio clone(Criterio criterio) {
		Criterio newCriterio = new Criterio();
		
		newCriterio.setId(criterio.getId());
		newCriterio.setNome(criterio.getNome());
		newCriterio.setTipo(criterio.getTipo());
		newCriterio.setValor(criterio.getValor());
		newCriterio.setOperador(criterio.getOperador());
		newCriterio.setVersion(criterio.getVersion());
		
		return newCriterio;
	}

	@Override
	public Criterio cloneFromFilter(CriterioFilter filter) {
		return null;
	}
}
