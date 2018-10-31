package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.IndicadorConhecimentoAlimentar;

public class IndicadorConhecimentoAlimentarDao extends GenericDao<IndicadorConhecimentoAlimentar> {
	
	private static IndicadorConhecimentoAlimentarDao instance;
	
	private IndicadorConhecimentoAlimentarDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = indicadorConhecimentoAlimentar -> {
			
			if(indicadorConhecimentoAlimentar.getItemIndicadorConhecimentoAlimentares()!=null) {
				indicadorConhecimentoAlimentar.getItemIndicadorConhecimentoAlimentares().forEach(ica -> {
					Hibernate.initialize(ica);
				});
			}
			
			return indicadorConhecimentoAlimentar;
		};
	}
	
	public static IndicadorConhecimentoAlimentarDao getInstance() {
		if(instance == null)
			instance = new IndicadorConhecimentoAlimentarDao();
		return instance;
	}
	
	@Override
	public IndicadorConhecimentoAlimentar getById(Object id) throws Exception {
		return super.getById(id, this.functionLoadAll);
	}

	@Override
	public PagedList<IndicadorConhecimentoAlimentar> getList(GenericExampleBuilder<?, ?> exampleBuilder)
			throws Exception {
		return super.getList(exampleBuilder, functionLoadAll);
	}

}