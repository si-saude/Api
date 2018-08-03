package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.AprhoEmpregadoFilter;
import br.com.saude.api.model.entity.po.AprhoEmpregado;

public class AprhoEmpregadoBuilder 
		extends GenericEntityBuilder<AprhoEmpregado, AprhoEmpregadoFilter> {

	private Function<Map<String,AprhoEmpregado>,AprhoEmpregado> loadAprho;
	private Function<Map<String,AprhoEmpregado>,AprhoEmpregado> loadEmpregado;
	
	
	public static AprhoEmpregadoBuilder newInstance(AprhoEmpregado aprhoEmpregado) {
		return new AprhoEmpregadoBuilder(aprhoEmpregado);
	}
	
	public static AprhoEmpregadoBuilder newInstance(List<AprhoEmpregado> aprhoEmpregados) {
		return new AprhoEmpregadoBuilder(aprhoEmpregados);
	}
	
	private AprhoEmpregadoBuilder(List<AprhoEmpregado> aprhoEmpregados) {
		super(aprhoEmpregados);
	}

	private AprhoEmpregadoBuilder(AprhoEmpregado aprhoEmpregado) {
		super(aprhoEmpregado);
	}
	
	@Override
	protected AprhoEmpregado clone(AprhoEmpregado aprhoEmpregado) {
		AprhoEmpregado newAprhoEmpregado = new AprhoEmpregado();
		newAprhoEmpregado.setId(aprhoEmpregado.getId());
		newAprhoEmpregado.setAtual(aprhoEmpregado.isAtual());
		newAprhoEmpregado.setEntrevistado(aprhoEmpregado.isEntrevistado());
		newAprhoEmpregado.setVersion(aprhoEmpregado.getVersion());
		
		return newAprhoEmpregado;
	}
	
	public AprhoEmpregadoBuilder loadAprho() {
		return (AprhoEmpregadoBuilder) this.loadProperty(this.loadAprho);
	}

	public AprhoEmpregadoBuilder loadEmpregado() {
		return (AprhoEmpregadoBuilder) this.loadProperty(this.loadEmpregado);
	}
	@Override
	protected void initializeFunctions() {
		
		this.loadAprho = aprhoEmpregados ->{
			if(aprhoEmpregados.get("origem").getAprho() != null) {
				aprhoEmpregados.get("destino").setAprho(AprhoBuilder.
						newInstance(aprhoEmpregados.get("origem").getAprho()).getEntity());
			}
			return aprhoEmpregados.get("destino");
		};
		
		this.loadEmpregado = aprhoEmpregados ->{
			if(aprhoEmpregados.get("origem").getEmpregado() != null) {
				aprhoEmpregados.get("destino").setEmpregado(EmpregadoBuilder.
						newInstance(aprhoEmpregados.get("origem").getEmpregado()).loadGerencia().loadCargo().getEntity());
			}
			return aprhoEmpregados.get("destino");
		};
		
	}

	@Override
	public AprhoEmpregado cloneFromFilter(AprhoEmpregadoFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
