package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.FonteGeradoraFilter;
import br.com.saude.api.model.entity.po.FonteGeradora;

public class FonteGeradoraBuilder extends GenericEntityBuilder<FonteGeradora, FonteGeradoraFilter> {

	public static FonteGeradoraBuilder newInstance(FonteGeradora fonteGeradora) {
		return new FonteGeradoraBuilder(fonteGeradora);
	}
	
	public static FonteGeradoraBuilder newInstance(List<FonteGeradora> fonteGeradoras) {
		return new FonteGeradoraBuilder(fonteGeradoras);
	}
	
	private FonteGeradoraBuilder(List<FonteGeradora> fonteGeradoras) {
		super(fonteGeradoras);
	}

	protected FonteGeradoraBuilder(FonteGeradora fonteGeradora) {
		super(fonteGeradora);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected FonteGeradora clone(FonteGeradora fonteGeradora) {
		FonteGeradora newFonteGeradora = new FonteGeradora();
		
		newFonteGeradora.setId(fonteGeradora.getId());
		newFonteGeradora.setDescricao(fonteGeradora.getDescricao());
		newFonteGeradora.setVersion(fonteGeradora.getVersion());
		
		return newFonteGeradora;
	}

	@Override
	public FonteGeradora cloneFromFilter(FonteGeradoraFilter filter) {
		return null;
	}
}
