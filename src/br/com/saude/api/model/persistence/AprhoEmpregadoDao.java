package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Aprho;
import br.com.saude.api.model.entity.po.AprhoEmpregado;
import br.com.saude.api.model.entity.po.Cargo;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.Gerencia;

public class AprhoEmpregadoDao extends GenericDao<AprhoEmpregado> {

	private static AprhoEmpregadoDao instance;
	
	private AprhoEmpregadoDao() {
		super();
	}
	
	public static AprhoEmpregadoDao getInstance() {
		if(instance == null)
			instance = new AprhoEmpregadoDao();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = aprhoEmpregado -> {
			if(aprhoEmpregado.getAprho() != null)
				aprhoEmpregado.setAprho((Aprho) Hibernate.unproxy(aprhoEmpregado.getAprho() ));
			
			if(aprhoEmpregado.getEmpregado() != null) {
				aprhoEmpregado.setEmpregado((Empregado) Hibernate.unproxy(aprhoEmpregado.getEmpregado() ));
				if(aprhoEmpregado.getEmpregado().getCargo()!=null)
					aprhoEmpregado.getEmpregado().setCargo((Cargo) Hibernate.unproxy(aprhoEmpregado.getEmpregado().getCargo()));
				if(aprhoEmpregado.getEmpregado().getGerencia()!=null)
					aprhoEmpregado.getEmpregado().setGerencia((Gerencia) Hibernate.unproxy(aprhoEmpregado.getEmpregado().getGerencia()));

			}
			
			return aprhoEmpregado;
		};
		
	}
	
	
	public PagedList<AprhoEmpregado> getListLoadAll(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder,this.functionLoadAll);
	}
	
}
