package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.GheeFilter;
import br.com.saude.api.model.entity.po.Ghee;

public class GheeBuilder extends GenericEntityBuilder<Ghee, GheeFilter> {

	public static GheeBuilder newInstance(Ghee ghee) {
		return new GheeBuilder(ghee);
	}
	
	public static GheeBuilder newInstance(List<Ghee> ghees) {
		return new GheeBuilder(ghees);
	}
	
	private GheeBuilder(List<Ghee> ghees) {
		super(ghees);
	}

	private GheeBuilder(Ghee ghee) {
		super(ghee);
	}
	
	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected Ghee clone(Ghee Ghee) {
		Ghee newGhee = new Ghee();
		
		newGhee.setId(Ghee.getId());
		newGhee.setCodigo(Ghee.getCodigo());
		newGhee.setDataCriacao(Ghee.getDataCriacao());
		newGhee.setDataDesativacao(Ghee.getDataDesativacao());
		newGhee.setDescricao(Ghee.getDescricao());
		newGhee.setNome(Ghee.getNome());
		newGhee.setVersion(Ghee.getVersion());
		
		return newGhee;
	}

	@Override
	public Ghee cloneFromFilter(GheeFilter filter) {
		return null;
	}
}
