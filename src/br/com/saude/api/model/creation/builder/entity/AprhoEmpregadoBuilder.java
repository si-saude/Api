package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.AprhoEmpregadoFilter;
import br.com.saude.api.model.entity.po.AprhoEmpregado;

public class AprhoEmpregadoBuilder 
		extends GenericEntityBuilder<AprhoEmpregado, AprhoEmpregadoFilter> {

	
	public static AprhoEmpregadoBuilder newInstance(AprhoEmpregado aprhoItem) {
		return new AprhoEmpregadoBuilder(aprhoItem);
	}
	
	public static AprhoEmpregadoBuilder newInstance(List<AprhoEmpregado> aprhoItens) {
		return new AprhoEmpregadoBuilder(aprhoItens);
	}
	
	private AprhoEmpregadoBuilder(List<AprhoEmpregado> aprhoItens) {
		super(aprhoItens);
	}

	private AprhoEmpregadoBuilder(AprhoEmpregado aprhoItem) {
		super(aprhoItem);
	}
	
	@Override
	protected AprhoEmpregado clone(AprhoEmpregado aprhoItem) {
		AprhoEmpregado newAprhoEmpregado = new AprhoEmpregado();
		newAprhoEmpregado.setId(aprhoItem.getId());
		newAprhoEmpregado.setAtual(aprhoItem.isAtual());
		
		if(aprhoItem.getAprho() != null)
			newAprhoEmpregado.setAprho(new AprhoBuilder(aprhoItem.getAprho()).getEntity());
		
		if(aprhoItem.getEmpregado() != null)
			newAprhoEmpregado.setEmpregado(new EmpregadoBuilder(aprhoItem.getEmpregado()).getEntity());
		
		return newAprhoEmpregado;
	}

	@Override
	protected void initializeFunctions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AprhoEmpregado cloneFromFilter(AprhoEmpregadoFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}
}
