package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.EnderecoFilter;
import br.com.saude.api.model.entity.po.Endereco;

public class EnderecoBuilder extends GenericEntityBuilder<Endereco,EnderecoFilter> {

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
		if(this.entity != null) {
			this.entity = loadCidade(this.entity, this.newEntity);
		}else {
			for(Endereco endereco:this.entityList) {
				Endereco newEndereco = this.newEntityList.stream()
						.filter(e->e.getId() == endereco.getId())
						.iterator().next();
				newEndereco = loadCidade(endereco,newEndereco);
			}
		}
		return this;
	}
	
	private Endereco loadCidade(Endereco origem, Endereco destino) {
		if(origem.getCidade() != null) {
			destino.setCidade(CidadeBuilder.newInstance(origem.getCidade()).getEntity());
		}
		
		return destino;
	}
	
	@Override
	public Endereco cloneFromFilter(EnderecoFilter filter) {
		return null;
	}
}
