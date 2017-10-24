package br.com.saude.api.model.business.validate;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.EmpregadoVacina;

public class EmpregadoVacinaValidator extends GenericValidator<EmpregadoVacina> {

	@Override
	public void validate(EmpregadoVacina empregadoVacina) throws Exception {
		super.validate(empregadoVacina);
		
		if(empregadoVacina.getVacina() != null && empregadoVacina.getVacina().getId() == 0)
			throw new Exception("É necessário informar a Vacina do Empregado.");
	}
}
