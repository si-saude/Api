package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.AcaoIntervencao;

public class AcaoIntervencaoDao extends GenericDao<AcaoIntervencao> {

	private static AcaoIntervencaoDao instance;
	
	private AcaoIntervencaoDao() {
		super();
	}
	
	public static AcaoIntervencaoDao getInstance() {
		if(instance==null)
			instance = new AcaoIntervencaoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoad = acaoIntervencao -> {
			acaoIntervencao = loadEquipe(acaoIntervencao);
			return acaoIntervencao;
		};
		
		this.functionLoadAll = acaoIntervencao ->{
			acaoIntervencao = loadEquipe(acaoIntervencao);
			return acaoIntervencao;		
		};
		
		
	}
	private AcaoIntervencao loadEquipe(AcaoIntervencao acaoIntervencao) {
		if(acaoIntervencao.getEquipe()!=null)
			Hibernate.initialize(acaoIntervencao.getEquipe());
		return acaoIntervencao;
	}
	
	public AcaoIntervencao getById(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}

}
