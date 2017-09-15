package br.com.saude.api.generic;

import java.util.List;

public abstract class GenericValidator {
	
	public abstract void validate(Object entity) throws Exception;
	
	public void validate(List<?> entityList) throws Exception {
		if(entityList != null)
			for(Object entity : entityList)
				validate(entity);
	}
}
