package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.TriagemFilter;
import br.com.saude.api.model.entity.po.Triagem;

public class TriagemBuilder extends GenericEntityBuilder<Triagem,TriagemFilter> {

	public static TriagemBuilder newInstance(Triagem triagem) {
		return new TriagemBuilder(triagem);
	}
	
	public static TriagemBuilder newInstance(List<Triagem> triagems) {
		return new TriagemBuilder(triagems);
	}
	
	private TriagemBuilder(Triagem triagem) {
		super(triagem);
	}

	private TriagemBuilder(List<Triagem> triagens) {
		super(triagens);
	}

	@Override
	protected void initializeFunctions() {
	}

	@Override
	protected Triagem clone(Triagem triagem) {
		Triagem cloneTriagem = new Triagem();
		
		cloneTriagem.setId(triagem.getId());
		cloneTriagem.setIndice(triagem.getIndice());
		cloneTriagem.setVersion(triagem.getVersion());
		
		if ( cloneTriagem.getIndicadorSast() != null )
			cloneTriagem.setIndicadorSast(new IndicadorSastBuilder(triagem.getIndicadorSast()).getEntity());
		
		return cloneTriagem;
	}

	@Override
	public Triagem cloneFromFilter(TriagemFilter filter) {
		return null;
	}
}