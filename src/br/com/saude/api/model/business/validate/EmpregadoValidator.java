package br.com.saude.api.model.business.validate;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Empregado;

public class EmpregadoValidator extends GenericValidator<Empregado> {

	
	@Override
	public void validate(Empregado empregado) throws Exception {
		super.validate(empregado);
		
		if(empregado.getTelefones() != null)
			new TelefoneValidator().validate(empregado.getTelefones());
	}
}
