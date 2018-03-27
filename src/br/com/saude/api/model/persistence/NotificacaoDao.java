package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Notificacao;

public class NotificacaoDao extends GenericDao<Notificacao>{

	private static NotificacaoDao instance;
	
	private NotificacaoDao() {
		super();
	}
	
	public static NotificacaoDao getInstance() {
		if(instance==null)
			instance = new NotificacaoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = notificacao -> {
			
			if(notificacao.getUsuario() != null)
				Hibernate.initialize(notificacao.getUsuario());
			
			return notificacao;
		};
	}
	
	@Override
	public Notificacao getById(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
}
