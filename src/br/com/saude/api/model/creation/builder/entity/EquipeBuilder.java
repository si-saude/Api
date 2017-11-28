package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.EquipeFilter;
import br.com.saude.api.model.entity.po.Equipe;

public class EquipeBuilder extends GenericEntityBuilder<Equipe,EquipeFilter> {

	public static EquipeBuilder newInstance(Equipe equipe) {
		return new EquipeBuilder(equipe);
	}
	
	public static EquipeBuilder newInstance(List<Equipe> equipes) {
		return new EquipeBuilder(equipes);
	}
	
	private EquipeBuilder(List<Equipe> equipes) {
		super(equipes);
	}

	private EquipeBuilder(Equipe equipe) {
		super(equipe);
	}
	
	@Override
	protected void initializeFunctions() {
	
	}

	@Override
	protected Equipe clone(Equipe equipe) {
		Equipe newEquipe = new Equipe();
		
		newEquipe.setId(equipe.getId());
		newEquipe.setNome(equipe.getNome());
		newEquipe.setAbreviacao(equipe.getAbreviacao());
		newEquipe.setVersion(equipe.getVersion());
		
		return newEquipe;
	}

	@Override
	public Equipe cloneFromFilter(EquipeFilter filter) {
		return null;
	}

}
