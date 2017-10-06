package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.IndicadorRiscoFilter;
import br.com.saude.api.model.entity.po.IndicadorRisco;

public abstract class IndicadorRiscoBuilder<T extends IndicadorRisco> extends GenericEntityBuilder<T,IndicadorRiscoFilter> {
	
	protected IndicadorRiscoBuilder(T indicadorRisco) {
		super(indicadorRisco);
	}

	protected IndicadorRiscoBuilder(List<T> indicadorRiscos) {
		super(indicadorRiscos);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected T clone(T indicadorRisco) {
		T newIndicadorRisco = null;
		
		try {
			newIndicadorRisco = (T)indicadorRisco.getClass().newInstance();
			
			newIndicadorRisco.setId(indicadorRisco.getId());
			newIndicadorRisco.setNome(indicadorRisco.getNome());
			newIndicadorRisco.setVersion(indicadorRisco.getVersion());
			newIndicadorRisco.setIndice0(indicadorRisco.getIndice0());
			newIndicadorRisco.setIndice1(indicadorRisco.getIndice1());
			newIndicadorRisco.setIndice2(indicadorRisco.getIndice2());
			newIndicadorRisco.setIndice3(indicadorRisco.getIndice3());
			newIndicadorRisco.setIndice4(indicadorRisco.getIndice4());
			newIndicadorRisco.setIndice5(indicadorRisco.getIndice5());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return newIndicadorRisco;
	}

	@Override
	public T cloneFromFilter(IndicadorRiscoFilter filter) {
		return null;
	}

}
