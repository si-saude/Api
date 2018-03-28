package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.RiscoPotencialBuilder;
import br.com.saude.api.model.creation.builder.example.RiscoPotencialExampleBuilder;
import br.com.saude.api.model.entity.filter.RiscoPotencialFilter;
import br.com.saude.api.model.entity.po.RiscoPotencial;
import br.com.saude.api.model.persistence.RiscoPotencialDao;

public class RiscoPotencialBo extends GenericBo<RiscoPotencial, RiscoPotencialFilter, 
	RiscoPotencialDao, RiscoPotencialBuilder, RiscoPotencialExampleBuilder> {

	private static RiscoPotencialBo instance;
	
	private RiscoPotencialBo() {
		super();
	}
	
	public static RiscoPotencialBo getInstance() {
		if(instance == null)
			instance = new RiscoPotencialBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadRiscoEmpregados().loadEquipes();
		};
	}
	
	@Override
	public RiscoPotencial getById(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
	public PagedList<RiscoPotencial> getListLoadAll(RiscoPotencialFilter filter) throws Exception {
		return super.getList(getDao().getListLoadAll(getExampleBuilder(filter).example()), this.functionLoadAll);
	}

	@Override
	public RiscoPotencial save(RiscoPotencial riscoPotencial) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		
		if ( riscoPotencial.getRiscoEmpregados() != null && riscoPotencial.getRiscoEmpregados().size() > 0 ) {
			riscoPotencial.getRiscoEmpregados().forEach(rE -> { 
				rE.setRiscoPotencial(riscoPotencial);
				
				if ( rE.getTriagens() != null && rE.getTriagens().size() > 0 ) {
					rE.getTriagens().forEach(t -> { 
						t.setRiscoEmpregado(rE);
						if ( t.getAcoes() != null && t.getAcoes().size() > 0 )
							t.getAcoes().forEach(a -> a.setTriagem(t));
					});
				}

			});
			
		}
		
		return super.save(riscoPotencial);
	}
	
}
