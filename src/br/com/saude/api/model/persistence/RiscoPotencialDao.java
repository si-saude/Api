package br.com.saude.api.model.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.RiscoPotencial;
import br.com.saude.api.model.entity.po.RiscoEmpregado;

public class RiscoPotencialDao extends GenericDao<RiscoPotencial> {

	private static RiscoPotencialDao instance;
	
	private RiscoPotencialDao() {
		super();
	}
	
	public static RiscoPotencialDao getInstance() {
		if(instance==null)
			instance = new RiscoPotencialDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = risco -> {
			
			if(risco.getRiscoEmpregados() != null) {
				List<RiscoEmpregado> riscos = new ArrayList<RiscoEmpregado>();
				
				risco.getRiscoEmpregados().forEach(r->{
					riscos.add((RiscoEmpregado) Hibernate.unproxy(r));
				});
				
				risco.setRiscoEmpregados(riscos);
			}
			
			return risco;
		};
	}
	
	@Override
	public RiscoPotencial getById(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
}
