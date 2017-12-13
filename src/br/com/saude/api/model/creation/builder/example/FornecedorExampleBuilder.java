package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.FornecedorFilter;
import br.com.saude.api.model.entity.po.Fornecedor;

public class FornecedorExampleBuilder extends GenericExampleBuilder<Fornecedor,FornecedorFilter>{

	public static FornecedorExampleBuilder newInstance(FornecedorFilter filter) {
		return new FornecedorExampleBuilder(filter);
	}
	
	private FornecedorExampleBuilder(FornecedorFilter filter) {
		super(filter);
	}
	
	private void addRazaoSocial() {
		if(this.filter.getRazaoSocial() != null)
			this.entity.setRazaoSocial(Helper.filterLike(this.filter.getRazaoSocial()));
	}
	
	private void addCpfCnpj() {
		if(this.filter.getCpfCnpj() != null)
			this.entity.setCpfCnpj(Helper.filterLike(this.filter.getCpfCnpj()));
	}
	
	private void addCodigoSap() {
		if(this.filter.getCodigoSap() != null)
			this.entity.setCodigoSap(Helper.filterLike(this.filter.getCodigoSap()));
	}
	
	private void addEmail() {
		if(this.filter.getEmail() != null)
			this.entity.setEmail(Helper.filterLike(this.filter.getEmail()));
	}
	
	private void addTipoPessoa() {
		if(this.filter.getTipoPessoa() != null && !this.filter.getTipoPessoa().equals(""))
			this.entity.setTipoPessoa(this.filter.getTipoPessoa());
	}


	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addCodigoSap();
		addCpfCnpj();
		addEmail();
		addRazaoSocial();
		addTipoPessoa();
	}

	@Override
	protected void createExampleSelectList() {
		
	}
}
