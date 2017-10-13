package br.com.saude.api.model.business.validate;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Fornecedor;

public class FornecedorValidator extends GenericValidator<Fornecedor> {

	@Override
	public void validate(Fornecedor fornecedor) throws Exception {
		if(fornecedor.getTelefones() != null)
			new TelefoneValidator().validate(fornecedor.getTelefones());
		
		if(fornecedor.getEndereco() != null)
			new EnderecoValidator().validate(fornecedor.getEndereco());
	}
}
