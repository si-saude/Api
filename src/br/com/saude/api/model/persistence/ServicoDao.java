package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Servico;

public class ServicoDao extends GenericDao<Servico> {

	private static ServicoDao instance;
	
	private ServicoDao() {
		super();
	}
	
	public static ServicoDao getInstance() {
		if(instance == null)
			instance = new ServicoDao();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = servico ->{
			if(servico.getAtividades() != null)
				Hibernate.initialize(servico.getAtividades());
			return servico;
		};
	}
	
	public Servico getByIdLoadAll(Object id) throws Exception {
		return super.getById(id, this.functionLoadAll);
	}
	
	public PagedList<Servico> getListLoadAll(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder, this.functionLoadAll);
	}
}
