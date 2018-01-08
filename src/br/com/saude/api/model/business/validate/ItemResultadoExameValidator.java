package br.com.saude.api.model.business.validate;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.ItemResultadoExame;

public class ItemResultadoExameValidator extends GenericValidator<ItemResultadoExame> {

	@Override
	public void validate(ItemResultadoExame item) throws Exception {
		
		super.validate(item);
		
		if(item.getResultadoExame() != null)
			new ResultadoExameValidator().validate(item.getResultadoExame());
	}
	
}
