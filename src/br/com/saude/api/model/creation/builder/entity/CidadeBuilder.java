package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.CidadeFilter;
import br.com.saude.api.model.entity.po.Cidade;

public class CidadeBuilder extends GenericEntityBuilder<Cidade,CidadeFilter> {

	public static CidadeBuilder newInstance(Cidade cidade) {
		return new CidadeBuilder(cidade);
	}
	
	public static CidadeBuilder newInstance(List<Cidade> cidades) {
		return new CidadeBuilder(cidades);
	}
	
	private CidadeBuilder(List<Cidade> cidades) {
		super(cidades);
	}

	private CidadeBuilder(Cidade cidade) {
		super(cidade);
	}

	@Override
	protected Cidade clone(Cidade cidade) {
		Cidade newCidade = new Cidade();
		
		newCidade.setId(cidade.getId());
		newCidade.setNome(cidade.getNome());
		newCidade.setUf(cidade.getUf());
		newCidade.setVersion(cidade.getVersion());
		
		return newCidade;
	}

	@Override
	public Cidade cloneFromFilter(CidadeFilter filter) {
		return null;
	}

}
