package br.com.saude.api.model.business.validate;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Pessoa;

public class PessoaValidator extends GenericValidator<Pessoa>{

	@Override
	public void validate(Pessoa pessoa) throws Exception {
		super.validate(pessoa);
		
		if(pessoa.getTelefones() != null)
			new TelefoneValidator().validate(pessoa.getTelefones());
		
		if(pessoa.getEndereco() != null)
			new EnderecoValidator().validate(pessoa.getEndereco());
		
	}
}
