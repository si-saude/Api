package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.FichaColetaFilter;
import br.com.saude.api.model.entity.po.FichaColeta;
import br.com.saude.api.model.entity.po.RespostaFichaColeta;

public class FichaColetaBuilder extends GenericEntityBuilder<FichaColeta, FichaColetaFilter> {
	public static FichaColetaBuilder newInstance(FichaColeta fichaColeta) {
		return new FichaColetaBuilder(fichaColeta);
	}
	
	public static FichaColetaBuilder newInstance(List<FichaColeta> fichaColetas) {
		return new FichaColetaBuilder(fichaColetas);
	}
	
	private FichaColetaBuilder(FichaColeta fichaColeta) {
		super(fichaColeta);
	}
	
	private FichaColetaBuilder(List<FichaColeta> fichaColetas) {
		super(fichaColetas);
	}

	@Override
	protected void initializeFunctions() {
	}

	@Override
	protected FichaColeta clone(FichaColeta fichaColeta) {
		FichaColeta cloneFichaColeta = new FichaColeta();
		
		cloneFichaColeta.setId(fichaColeta.getId());
		cloneFichaColeta.setVersion(fichaColeta.getVersion());
		if ( fichaColeta.getRespostaFichaColetas() != null )
			cloneFichaColeta.setRespostaFichaColetas(
					(List<RespostaFichaColeta>) RespostaFichaColetaBuilder
					.newInstance(fichaColeta.getRespostaFichaColetas()).getEntityList());
		
		return cloneFichaColeta;
	}

	@Override
	public FichaColeta cloneFromFilter(FichaColetaFilter filter) {
		return null;
	}
}
