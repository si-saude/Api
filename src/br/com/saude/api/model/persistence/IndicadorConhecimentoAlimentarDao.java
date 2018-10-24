package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.IndicadorConhecimentoAlimentar;

public class IndicadorConhecimentoAlimentarDao extends GenericDao<IndicadorConhecimentoAlimentar> {
	
	private static IndicadorConhecimentoAlimentarDao instance;
	
	private IndicadorConhecimentoAlimentarDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = indicadorConhecimentoAlimentar -> {
			
			if(indicadorConhecimentoAlimentar.getItemIndicadorConhecimentoAlimentares()!=null)
				Hibernate.initialize(indicadorConhecimentoAlimentar.getItemIndicadorConhecimentoAlimentares());
			
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
		// TODO Auto-generated method stub
		return super.getById(id, this.functionLoadAll);
	}


}
