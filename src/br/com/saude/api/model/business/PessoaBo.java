package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.creation.builder.entity.PessoaBuilder;
import br.com.saude.api.model.creation.builder.example.PessoaExampleBuilder;
import br.com.saude.api.model.entity.filter.PessoaFilter;
import br.com.saude.api.model.entity.po.Pessoa;
import br.com.saude.api.model.persistence.PessoaDao;

public class PessoaBo extends GenericBo<Pessoa, PessoaFilter, PessoaDao, PessoaBuilder, PessoaExampleBuilder> {
	
	private static PessoaBo instance;

	private PessoaBo() {
		super();
	}

	public static PessoaBo getInstance() {
		if (instance == null)
			instance = new PessoaBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {

	}
	
	public Pessoa loadIdade(Pessoa pessoa) {
		if(pessoa.getDataNascimento() != null) {
			pessoa.setIdade(Helper.calculateIdade(pessoa.getDataNascimento()).getYears());
		}
		return pessoa;
	}
}
