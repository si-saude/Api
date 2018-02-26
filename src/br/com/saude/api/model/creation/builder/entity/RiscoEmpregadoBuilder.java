package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.RiscoEmpregadoFilter;
import br.com.saude.api.model.entity.po.RiscoEmpregado;

public class RiscoEmpregadoBuilder extends GenericEntityBuilder<RiscoEmpregado,RiscoEmpregadoFilter> {

	public static RiscoEmpregadoBuilder newInstance(RiscoEmpregado riscoEmpregado) {
		return new RiscoEmpregadoBuilder(riscoEmpregado);
	}
	
	public static RiscoEmpregadoBuilder newInstance(List<RiscoEmpregado> riscoEmpregados) {
		return new RiscoEmpregadoBuilder(riscoEmpregados);
	}
	
	private RiscoEmpregadoBuilder(RiscoEmpregado riscoEmpregado) {
		super(riscoEmpregado);
	}

	private RiscoEmpregadoBuilder(List<RiscoEmpregado> riscoEmpregados) {
		super(riscoEmpregados);
	}

	@Override
	protected void initializeFunctions() {}

	@Override
	protected RiscoEmpregado clone(RiscoEmpregado riscoEmpregado) {
		RiscoEmpregado cloneRiscoEmpregado = new RiscoEmpregado();
		
		cloneRiscoEmpregado.setId(riscoEmpregado.getId());
		cloneRiscoEmpregado.setVersion(riscoEmpregado.getVersion());
		
		if ( riscoEmpregado.getEmpregado() != null )
			cloneRiscoEmpregado.setEmpregado(EmpregadoBuilder.newInstance(riscoEmpregado.getEmpregado()).getEntity());
		
		if ( riscoEmpregado.getEquipe() != null )
			cloneRiscoEmpregado.setEquipe(EquipeBuilder.newInstance(riscoEmpregado.getEquipe()).getEntity());
		
		return cloneRiscoEmpregado;
	}

	@Override
	public RiscoEmpregado cloneFromFilter(RiscoEmpregadoFilter filter) {
		return null;
	}
}
