package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.EquipeFilter;
import br.com.saude.api.model.entity.po.Equipe;

public class EquipeBuilder extends GenericEntityBuilder<Equipe,EquipeFilter> {
	
	private Function<Map<String,Equipe>,Equipe> loadCoordenador;

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
		this.loadCoordenador = equipes ->{
			if(equipes.get("origem").getCoordenador() != null) {
				equipes.get("destino").setCoordenador(ProfissionalBuilder.newInstance(
						equipes.get("origem").getCoordenador()).getEntity());
			}
			return equipes.get("destino");
		};
	
	}

	@Override
	protected Equipe clone(Equipe equipe) {
		Equipe newEquipe = new Equipe();
		
		newEquipe.setId(equipe.getId());
		newEquipe.setNome(equipe.getNome());
		newEquipe.setAbreviacao(equipe.getAbreviacao());
		newEquipe.setPrioridadeSast(equipe.getPrioridadeSast());
		newEquipe.setVersion(equipe.getVersion());
		
		return newEquipe;
	}

	public EquipeBuilder loadCoordenador() {
		return (EquipeBuilder) this.loadProperty(this.loadCoordenador);
	}
	
	@Override
	public Equipe cloneFromFilter(EquipeFilter filter) {
		return null;
	}

}