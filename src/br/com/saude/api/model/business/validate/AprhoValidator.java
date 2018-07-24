package br.com.saude.api.model.business.validate;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Aprho;

public class AprhoValidator extends GenericValidator<Aprho> {
	
	@Override
	public void validate(Aprho aprho) throws Exception {
		super.validate(aprho);
		
		if(aprho.getAprhoItens() != null)
			new AprhoItemValidator().validate(aprho.getAprhoItens());
		
		if(aprho.getAprhoEmpregados() != null)
			new AprhoEmpregadoValidator().validate(aprho.getAprhoEmpregados());
	}
}
