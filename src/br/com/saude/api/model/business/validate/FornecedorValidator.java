package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Fornecedor;

public class FornecedorValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		Fornecedor fornecedor = (Fornecedor)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<Fornecedor>> fornecedorlViolation = factory.getValidator().validate(fornecedor);
		
		if(fornecedorlViolation.size() > 0)
			throw new Exception(fornecedorlViolation.iterator().next().getMessage());
		
		if(fornecedor.getTelefones() != null)
			new TelefoneValidator().validate(fornecedor.getTelefones());
		
		if(fornecedor.getEndereco() != null)
			new EnderecoValidator().validate(fornecedor.getEndereco());
	}

}
