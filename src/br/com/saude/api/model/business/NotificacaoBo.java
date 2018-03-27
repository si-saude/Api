package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.NotificacaoBuilder;
import br.com.saude.api.model.creation.builder.example.NotificacaoExampleBuilder;
import br.com.saude.api.model.entity.filter.NotificacaoFilter;
import br.com.saude.api.model.entity.po.Notificacao;
import br.com.saude.api.model.persistence.NotificacaoDao;

public class NotificacaoBo extends GenericBo<Notificacao, NotificacaoFilter, NotificacaoDao, 
NotificacaoBuilder, NotificacaoExampleBuilder> {

	private static NotificacaoBo instance;
	
	private NotificacaoBo() {
		super();
	}
	
	public static NotificacaoBo getInstance() {
		if(instance == null)
			instance = new NotificacaoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadUsuario();
		};
	}
	
	@Override
	public Notificacao getById(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
}
