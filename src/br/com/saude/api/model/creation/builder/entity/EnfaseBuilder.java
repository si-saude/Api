package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.EnfaseFilter;
import br.com.saude.api.model.entity.po.Enfase;

public class EnfaseBuilder extends GenericEntityBuilder<Enfase, EnfaseFilter> {
	public static EnfaseBuilder newInstance(Enfase enfase) {
		return new EnfaseBuilder(enfase);
	}
	
	public static EnfaseBuilder newInstance(List<Enfase> enfases) {
		return new EnfaseBuilder(enfases);
	}
	
	private EnfaseBuilder(List<Enfase> enfases) {
		super(enfases);
	}

	private EnfaseBuilder(Enfase enfase) {
		super(enfase);
	}
	
	@Override
	protected void initializeFunctions() {}

	@Override
	protected Enfase clone(Enfase enfase) {
		Enfase newEnfase = new Enfase();
		
		newEnfase.setId(enfase.getId());
		newEnfase.setDescricao(enfase.getDescricao());
		newEnfase.setVersion(enfase.getVersion());
		
		return newEnfase;
	}
	
	@Override
	public Enfase cloneFromFilter(EnfaseFilter filter) {
		return null;
	}

}
