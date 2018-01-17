package br.com.saude.api.model.business;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

import br.com.saude.api.generic.GenericBo;
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
			Period periodo = Period.between(pessoa.getDataNascimento().toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDate(), 
					LocalDate.now());
			pessoa.setIdade(periodo.getYears());
		}
		return pessoa;
	}
}
