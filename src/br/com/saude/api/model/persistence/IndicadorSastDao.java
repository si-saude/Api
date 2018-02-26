package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.IndicadorSast;

public class IndicadorSastDao extends GenericDao<IndicadorSast>{

	private static IndicadorSastDao instance;
	
	private IndicadorSastDao() {
		super();
	}
	
	public static IndicadorSastDao getInstance() {
		if(instance == null)
			instance = new IndicadorSastDao();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = indicadorSast -> {
			indicadorSast = loadEquipe(indicadorSast);
			indicadorSast = loadIndicadorSasts(indicadorSast);
			return indicadorSast;
		};
	}
	
	public IndicadorSast getByIdLoadAll(Object id) throws Exception {
		return super.getById(id, this.functionLoadAll);
	}

	private IndicadorSast loadEquipe(IndicadorSast indicadorSast) {
		if ( indicadorSast.getEquipe() != null)
			Hibernate.initialize(indicadorSast.getEquipe());
		return indicadorSast;		
	}
	
	private IndicadorSast loadIndicadorSasts(IndicadorSast indicadorSast) {
		if (indicadorSast.getIndicadorAssociadoSasts() != null)
			Hibernate.initialize(indicadorSast.getIndicadorAssociadoSasts());
		return indicadorSast;
	}
}