package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.EmailBuilder;
import br.com.saude.api.model.creation.builder.example.EmailExampleBuilder;
import br.com.saude.api.model.entity.filter.EmailFilter;
import br.com.saude.api.model.entity.po.Email;
import br.com.saude.api.model.persistence.EmailDao;

public class EmailBo extends GenericBo<Email, EmailFilter, EmailDao,EmailBuilder, EmailExampleBuilder> {
	private static EmailBo instance;
	
	private EmailBo() {
		super();
	}
	
	public static EmailBo getInstance() {
		if(instance==null)
			instance = new EmailBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadDestinatario();
		};
	}
	
	@Override
	public Email getById(Object id) throws Exception {
		return getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}

	@Override
	public Email save(Email email) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, Exception {
		return super.save(email);
	}
	
	
	
}
