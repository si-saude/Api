package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Email;

public class EmailDao extends GenericDao<Email> {
	private static EmailDao instance;
	
	private EmailDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = email -> {
			if ( email.getDestinatarios() != null )
				Hibernate.initialize(email.getDestinatarios());
			return email;
		};
	}
	
	public static EmailDao getInstance() {
		if(instance==null)
			instance = new EmailDao();
		return instance;
	}
	
	public Email getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
}
