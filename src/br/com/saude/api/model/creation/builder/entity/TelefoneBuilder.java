package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.TelefoneFilter;
import br.com.saude.api.model.entity.po.Telefone;

public class TelefoneBuilder extends GenericEntityBuilder<Telefone,TelefoneFilter> {

	public static TelefoneBuilder newInstance(Telefone telefone) {
		return new TelefoneBuilder(telefone);
	}
	
	public static TelefoneBuilder newInstance(List<Telefone> telefones) {
		return new TelefoneBuilder(telefones);
	}
	
	private TelefoneBuilder(Telefone telefone) {
		super(telefone);
	}

	private TelefoneBuilder(List<Telefone> telefones) {
		super(telefones);
	}

	@Override
	protected Telefone clone(Telefone telefone) {
		Telefone newTelefone = new Telefone();
		
		newTelefone.setId(telefone.getId());
		newTelefone.setNumero(telefone.getNumero());
		newTelefone.setVersion(telefone.getVersion());
		
		return newTelefone;
	}

	@Override
	public Telefone cloneFromFilter(TelefoneFilter filter) {
		return null;
	}
}
