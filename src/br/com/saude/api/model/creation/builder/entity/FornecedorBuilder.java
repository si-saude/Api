package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.FornecedorFilter;
import br.com.saude.api.model.entity.po.Fornecedor;

public class FornecedorBuilder extends GenericEntityBuilder<Fornecedor,FornecedorFilter>{

	public static FornecedorBuilder newInstance(Fornecedor fornecedor) {
		return new FornecedorBuilder(fornecedor);
	}
	
	public static FornecedorBuilder newInstance(List<Fornecedor> fornecedores) {
		return new FornecedorBuilder(fornecedores);
	}
	
	private FornecedorBuilder(List<Fornecedor> fornecedores) {
		super(fornecedores);
	}

	private FornecedorBuilder(Fornecedor fornecedore) {
		super(fornecedore);
	}

	@Override
	protected Fornecedor clone(Fornecedor fornecedor) {
		Fornecedor newFornecedor = new Fornecedor();
		
		newFornecedor.setId(fornecedor.getId());
		newFornecedor.setCodigoSap(fornecedor.getCodigoSap());
		newFornecedor.setCpfCnpj(fornecedor.getCpfCnpj());
		newFornecedor.setEmail(fornecedor.getEmail());
		newFornecedor.setRazaoSocial(fornecedor.getRazaoSocial());
		newFornecedor.setTipoPessoa(fornecedor.getTipoPessoa());
		newFornecedor.setVersion(fornecedor.getVersion());
		
		return newFornecedor;
	}
	
	public FornecedorBuilder loadEndereco() {
		if(this.entity != null) {
			this.newEntity = loadEndereco(this.entity,this.newEntity);
		}else {
			for(Fornecedor fornecedor:this.entityList) {
				Fornecedor newFornecedor = this.newEntityList.stream()
						.filter(e->e.getId() == fornecedor.getId())
						.iterator().next();
				newFornecedor = loadEndereco(fornecedor,newFornecedor);
			}
		}
		return this;
	}
	
	private Fornecedor loadEndereco(Fornecedor origem,Fornecedor destino) {
		if(origem.getEndereco() != null) {
			destino.setEndereco(EnderecoBuilder.newInstance(origem.getEndereco()).getEntity());
		}
		return destino;
	}
	
	public FornecedorBuilder loadTelefones() {
		if(this.entity != null) {
			this.newEntity = loadTelefones(this.entity,this.newEntity);
		}else {
			for(Fornecedor fornecedor:this.entityList) {
				Fornecedor newFornecedor = this.newEntityList.stream()
						.filter(e->e.getId() == fornecedor.getId())
						.iterator().next();
				newFornecedor = loadTelefones(fornecedor,newFornecedor);
			}
		}
		return this;
	}
	
	private Fornecedor loadTelefones(Fornecedor origem,Fornecedor destino) {
		if(origem.getTelefones() != null) {
			destino.setTelefones(TelefoneBuilder.newInstance(origem.getTelefones()).getEntityList());
		}
		return destino;
	}

	@Override
	public Fornecedor cloneFromFilter(FornecedorFilter filter) {
		return null;
	}
}
