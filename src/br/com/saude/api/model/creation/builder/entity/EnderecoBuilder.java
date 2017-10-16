package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.EnderecoFilter;
import br.com.saude.api.model.entity.po.Endereco;

public class EnderecoBuilder extends GenericEntityBuilder<Endereco,EnderecoFilter> {

	private Function<Map<String,Endereco>,Endereco> loadCidade;
	
	public static EnderecoBuilder newInstance(Endereco endereco) {
		return new EnderecoBuilder(endereco);
	}
	
	public static EnderecoBuilder newInstance(List<Endereco> enderecos) {
		return new EnderecoBuilder(enderecos);
	}
	
	private EnderecoBuilder(List<Endereco> enderecos) {
		super(enderecos);
	}

	private EnderecoBuilder(Endereco endereco) {
		super(endereco);
	}
	
	@Override
	protected void initializeFunctions() {
		this.loadCidade = cidades -> {
			if(cidades.get("origem").getCidade() != null) {
				cidades.get("destino").setCidade(CidadeBuilder.newInstance(cidades.get("origem").getCidade()).getEntity());
			}
			return cidades.get("destino");
		};
	}

	@Override
	protected Endereco clone(Endereco endereco) {
		Endereco newEndereco = new Endereco();
		
		newEndereco.setId(endereco.getId());
		newEndereco.setLogradouro(endereco.getLogradouro());
		newEndereco.setBairro(endereco.getBairro());
		newEndereco.setCep(endereco.getCep());
		newEndereco.setComplemento(endereco.getComplemento());
		newEndereco.setNumero(endereco.getNumero());
		newEndereco.setVersion(endereco.getVersion());
		
		return newEndereco;
	}

	public EnderecoBuilder loadCidade() {
		return (EnderecoBuilder) this.loadProperty(this.loadCidade);
	}
	
	@Override
	public Endereco cloneFromFilter(EnderecoFilter filter) {
		return null;
	}
}
