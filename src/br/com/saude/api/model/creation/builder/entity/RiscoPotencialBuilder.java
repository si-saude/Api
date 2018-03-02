package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.hibernate.proxy.HibernateProxy;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.RiscoPotencialFilter;
import br.com.saude.api.model.entity.po.RiscoPotencial;

public class RiscoPotencialBuilder extends GenericEntityBuilder<RiscoPotencial, RiscoPotencialFilter> {

	private Function<Map<String,RiscoPotencial>,RiscoPotencial> loadRiscoEmpregados;
	
	public static RiscoPotencialBuilder newInstance(RiscoPotencial risco) {
		return new RiscoPotencialBuilder(risco);
	}
	
	public static RiscoPotencialBuilder newInstance(List<RiscoPotencial> riscos) {
		return new RiscoPotencialBuilder(riscos);
	}
	
	private RiscoPotencialBuilder(RiscoPotencial risco) {
		super(risco);
	}
	
	private RiscoPotencialBuilder(List<RiscoPotencial> riscos) {
		super(riscos);
	}

	@Override
	protected void initializeFunctions() {
		this.loadRiscoEmpregados = riscos -> {
			
			if(riscos.get("origem").getRiscoEmpregados() != null)
				riscos.get("destino").setRiscoEmpregados(RiscoEmpregadoBuilder
						.newInstance(riscos.get("origem").getRiscoEmpregados())
						.getEntityList());
			
			return riscos.get("destino");
		};
	}
	
	public RiscoPotencialBuilder loadRiscoEmpregados() {
		return (RiscoPotencialBuilder) this.loadProperty(this.loadRiscoEmpregados);
	}

	@Override
	protected RiscoPotencial clone(RiscoPotencial risco) {
		RiscoPotencial newRisco = new RiscoPotencial();
		
		newRisco.setId(risco.getId());
		newRisco.setData(risco.getData());
		newRisco.setVersion(risco.getVersion());
		
		if(newRisco.getEmpregado() != null && !(newRisco.getEmpregado() instanceof HibernateProxy))
			risco.setEmpregado(EmpregadoBuilder.newInstance(risco.getEmpregado()).getEntity());
		
		return newRisco;
	}

	@Override
	public RiscoPotencial cloneFromFilter(RiscoPotencialFilter filter) {
		return null;
	}
}
