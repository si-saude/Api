package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.PessoaFilter;
import br.com.saude.api.model.entity.po.Pessoa;

public class PessoaExampleBuilder extends GenericExampleBuilder<Pessoa,PessoaFilter>{

	public static PessoaExampleBuilder newInstance(PessoaFilter filter) {
		return new PessoaExampleBuilder(filter);
	}
	
	protected PessoaExampleBuilder(PessoaFilter filter) {
		super(filter);
	}

	public void addNome() {
		if(this.filter.getNome()!=null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
	
	private void addCpf() {
		if(this.filter.getCpf()!= null)
			this.entity.setCpf(Helper.filterLike(this.filter.getCpf()));
	}
	
	private void addDataNascimento() {
		this.addData("dataNascimento", this.filter.getDataNascimento());
	}
	
	private void addRg() {
		if(this.filter.getRg()!= null)
			this.entity.setRg(Helper.filterLike(this.filter.getRg()));
	}
	
	private void addEmail() {
		if(this.filter.getEmail()!= null)
			this.entity.setEmail(Helper.filterLike(this.filter.getEmail()));
	}
	
	private void addSexo() {
		if(this.filter.getSexo()!= null)
			this.entity.setSexo(this.filter.getSexo());
	}
	
	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addNome();
		addCpf();
		addDataNascimento();
		addRg();
		addSexo();
		addEmail();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		addNome();
		addCpf();
		addDataNascimento();
		addRg();
		addSexo();
		addEmail();
	}

}
