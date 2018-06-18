package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.CategoriaRiscoFilter;
import br.com.saude.api.model.entity.po.CategoriaRisco;

public class CategoriaRiscoBuilder extends GenericEntityBuilder<CategoriaRisco, CategoriaRiscoFilter> {

	public static CategoriaRiscoBuilder newInstance(CategoriaRisco categoriaRisco) {
		return new CategoriaRiscoBuilder(categoriaRisco);
	}
	
	public static CategoriaRiscoBuilder newInstance(List<CategoriaRisco> categoriaRiscos) {
		return new CategoriaRiscoBuilder(categoriaRiscos);
	}
	
	private CategoriaRiscoBuilder(List<CategoriaRisco> categoriaRiscos) {
		super(categoriaRiscos);
	}

	protected CategoriaRiscoBuilder(CategoriaRisco categoriaRisco) {
		super(categoriaRisco);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected CategoriaRisco clone(CategoriaRisco categoriaRisco) {
		CategoriaRisco newCategoriaRisco = new CategoriaRisco();
		
		newCategoriaRisco.setId(categoriaRisco.getId());
		newCategoriaRisco.setDescricao(categoriaRisco.getDescricao());
		newCategoriaRisco.setObservacao(categoriaRisco.getObservacao());
		newCategoriaRisco.setVersion(categoriaRisco.getVersion());
		
		return newCategoriaRisco;
	}

	@Override
	public CategoriaRisco cloneFromFilter(CategoriaRiscoFilter filter) {
		return null;
	}
}
