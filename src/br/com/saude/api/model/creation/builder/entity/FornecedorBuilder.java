package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.FornecedorFilter;
import br.com.saude.api.model.entity.po.Fornecedor;

public class FornecedorBuilder extends GenericEntityBuilder<Fornecedor,FornecedorFilter>{

	private Function<Map<String,Fornecedor>,Fornecedor> loadEndereco;
	private Function<Map<String,Fornecedor>,Fornecedor> loadTelefones;
	
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
	protected void initializeFunctions() {
		this.loadEndereco = fornecedores -> {
			if(fornecedores.get("origem").getEndereco() != null) {
				fornecedores.get("destino").setEndereco(EnderecoBuilder.newInstance(fornecedores.get("origem").getEndereco()).loadCidade().getEntity());
			}
			return fornecedores.get("destino");
		};
		
		this.loadTelefones = fornecedores -> {
			if(fornecedores.get("origem").getTelefones() != null) {
				fornecedores.get("destino").setTelefones(TelefoneBuilder.newInstance(fornecedores.get("origem").getTelefones()).getEntityList());
			}
			return fornecedores.get("destino");
		};
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
		return (FornecedorBuilder) this.loadProperty(this.loadEndereco);
	}
	
	public FornecedorBuilder loadTelefones() {
		return (FornecedorBuilder) this.loadProperty(this.loadTelefones);
	}

	@Override
	public Fornecedor cloneFromFilter(FornecedorFilter filter) {
		return null;
	}
}
