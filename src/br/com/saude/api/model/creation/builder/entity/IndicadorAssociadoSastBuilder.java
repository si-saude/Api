package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.IndicadorAssociadoSastFilter;
import br.com.saude.api.model.entity.po.IndicadorAssociadoSast;

public class IndicadorAssociadoSastBuilder  extends GenericEntityBuilder<IndicadorAssociadoSast,IndicadorAssociadoSastFilter>{

	protected IndicadorAssociadoSastBuilder(IndicadorAssociadoSast indicadorAssociadoSast) {
		super(indicadorAssociadoSast);
	}

	private IndicadorAssociadoSastBuilder(List<IndicadorAssociadoSast> indicadorAssociadoSasts) {
		super(indicadorAssociadoSasts);
	}
	
	public static IndicadorAssociadoSastBuilder newInstance(IndicadorAssociadoSast indicadorAssociadoSast) {
		return new IndicadorAssociadoSastBuilder(indicadorAssociadoSast);
	}
	
	public static IndicadorAssociadoSastBuilder newInstance(List<IndicadorAssociadoSast> indicadorAssociadoSasts) {
		return new IndicadorAssociadoSastBuilder(indicadorAssociadoSasts);
	}
	
	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected IndicadorAssociadoSast clone(IndicadorAssociadoSast indicadorAssociadoSast) {
		IndicadorAssociadoSast cloneIndicadorAssociadoSast = new IndicadorAssociadoSast();
		
		cloneIndicadorAssociadoSast.setId(indicadorAssociadoSast.getId());
		cloneIndicadorAssociadoSast.setVersion(indicadorAssociadoSast.getVersion());
		cloneIndicadorAssociadoSast.setCodigo(indicadorAssociadoSast.getCodigo());

		return cloneIndicadorAssociadoSast;
	}

	@Override
	public IndicadorAssociadoSast cloneFromFilter(IndicadorAssociadoSastFilter filter) {
		return null;
	}

}
