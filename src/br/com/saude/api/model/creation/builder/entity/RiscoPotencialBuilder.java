package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.RiscoPotencialFilter;
import br.com.saude.api.model.entity.po.RiscoPotencial;

public class RiscoPotencialBuilder extends GenericEntityBuilder<RiscoPotencial, RiscoPotencialFilter> {

	private Function<Map<String,RiscoPotencial>,RiscoPotencial> loadRiscoEmpregados;
	private Function<Map<String,RiscoPotencial>,RiscoPotencial> loadRiscoEmpregadosSimples;
	private Function<Map<String,RiscoPotencial>,RiscoPotencial> loadEquipes;
	private Function<Map<String,RiscoPotencial>,RiscoPotencial> loadAcoes;
	
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
						.loadTriagensAll()
						.getEntityList());
			
			return riscos.get("destino");
		};
		
		this.loadRiscoEmpregadosSimples = riscos -> {
			
			if(riscos.get("origem").getRiscoEmpregados() != null)
				riscos.get("destino").setRiscoEmpregados(RiscoEmpregadoBuilder
						.newInstance(riscos.get("origem").getRiscoEmpregados())
						.getEntityList());
			
			return riscos.get("destino");
		};
		
		this.loadEquipes = riscos -> {
			
			if(riscos.get("origem").getEquipes() != null)
				riscos.get("destino").setEquipes(EquipeBuilder
						.newInstance(riscos.get("origem").getEquipes())
						.getEntityList());
			
			return riscos.get("destino");
		};
		
		this.loadAcoes = riscos -> {
			if(riscos.get("origem").getRiscoEmpregados() != null)
				riscos.get("destino").setRiscoEmpregados(RiscoEmpregadoBuilder
						.newInstance(riscos.get("origem").getRiscoEmpregados())
						.loadAcoes()
						.getEntityList());
			
			return riscos.get("destino");
		};
		
	}
	
	public RiscoPotencialBuilder loadRiscoEmpregados() {
		return (RiscoPotencialBuilder) this.loadProperty(this.loadRiscoEmpregados);
	}
	
	public RiscoPotencialBuilder loadRiscoEmpregadosSimples() {
		return (RiscoPotencialBuilder) this.loadProperty(this.loadRiscoEmpregadosSimples);
	}
	
	public RiscoPotencialBuilder loadEquipes() {
		return (RiscoPotencialBuilder) this.loadProperty(this.loadEquipes);
	}
	
	public RiscoPotencialBuilder loadAcoes() {
		return (RiscoPotencialBuilder) this.loadProperty(this.loadAcoes);
	}

	@Override
	protected RiscoPotencial clone(RiscoPotencial risco) {
		RiscoPotencial newRisco = new RiscoPotencial();
		
		newRisco.setId(risco.getId());
		newRisco.setData(risco.getData());
		newRisco.setCondutaPercepcao(risco.getCondutaPercepcao());
		newRisco.setInicioAgendamento(risco.getInicioAgendamento());
		newRisco.setFimAgendamento(risco.getFimAgendamento());
		newRisco.setAtual(risco.isAtual());
		newRisco.setVersion(risco.getVersion());
		newRisco.setStatus(risco.getStatus());
		
		if(risco.getEmpregado() != null)
			newRisco.setEmpregado(EmpregadoBuilder.newInstance(risco.getEmpregado()).getEntity());
		
		if(risco.getEquipeResponsavel() != null)
			newRisco.setEquipeResponsavel(EquipeBuilder.newInstance(risco.getEquipeResponsavel()).getEntity());
		
		return newRisco;
	}

	@Override
	public RiscoPotencial cloneFromFilter(RiscoPotencialFilter filter) {
		return null;
	}
}
