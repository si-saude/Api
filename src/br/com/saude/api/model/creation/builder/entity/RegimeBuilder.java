package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.RegimeFilter;
import br.com.saude.api.model.entity.po.Regime;

public class RegimeBuilder extends GenericEntityBuilder<Regime, RegimeFilter> {

	public static RegimeBuilder newInstance(Regime regime) {
		return new RegimeBuilder(regime);
	}
	
	public static RegimeBuilder newInstance(List<Regime> regimes) {
		return new RegimeBuilder(regimes);
	}
	
	private RegimeBuilder(Regime regime) {
		super(regime);
	}

	private RegimeBuilder(List<Regime> regimes) {
		super(regimes);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected Regime clone(Regime regime) {
		Regime newRegime = new Regime();
		
		newRegime.setId(regime.getId());
		newRegime.setNome(regime.getNome());
		newRegime.setVersion(regime.getVersion());
		
		return newRegime;
	}

	@Override
	public Regime cloneFromFilter(RegimeFilter filter) {
		return null;
	}
}
