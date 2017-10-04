package br.com.saude.api.model.creation.builder.example;

import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.EnderecoFilter;
import br.com.saude.api.model.entity.po.Endereco;

public class EnderecoExampleBuilder extends GenericExampleBuilder<Endereco,EnderecoFilter> {

	public static EnderecoExampleBuilder newInstance(EnderecoFilter filter) {
		return new EnderecoExampleBuilder(filter);
	}
	
	private EnderecoExampleBuilder(EnderecoFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addBairro();
		addCep();
		addCidade();
		addComplemento();
		addLogradouro();
		addNumero();
	}

	@Override
	protected void createExampleSelectList() {
		
	}

	private void addBairro() {
		if(this.filter.getBairro() != null)
			this.entity.setBairro(Helper.filterLike(this.filter.getBairro()));
	}
	
	private void addCep() {
		if(this.filter.getCep()!= null)
			this.entity.setCep(Helper.filterLike(this.filter.getCep()));
	}
	
	private void addComplemento() {
		if(this.filter.getComplemento()!= null)
			this.entity.setComplemento(Helper.filterLike(this.filter.getComplemento()));
	}
	
	private void addLogradouro() {
		if(this.filter.getLogradouro()!= null)
			this.entity.setLogradouro(Helper.filterLike(this.filter.getLogradouro()));
	}
	
	private void addNumero() {
		if(this.filter.getNumero()!= null)
			this.entity.setNumero(Helper.filterLike(this.filter.getNumero()));
	}
	
	private void addCidade() throws InstantiationException, IllegalAccessException {
		if(this.filter.getCidade()!=null) {
			CriteriaExample criteriaExample = CidadeExampleBuilder
					.newInstance(this.filter.getCidade()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("cidade", criteriaExample, JoinType.INNER_JOIN));
		}
	}
}
