package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.RiscoEmpregadoFilter;
import br.com.saude.api.model.entity.po.RiscoEmpregado;

public class RiscoEmpregadoBuilder extends GenericEntityBuilder<RiscoEmpregado,RiscoEmpregadoFilter> {

	private Function<Map<String,RiscoEmpregado>,RiscoEmpregado> loadRiscoPotencial;
	private Function<Map<String,RiscoEmpregado>,RiscoEmpregado> loadTriagens;
	private Function<Map<String,RiscoEmpregado>,RiscoEmpregado> loadTriagensAll;
	private Function<Map<String,RiscoEmpregado>,RiscoEmpregado> loadAcoes;
	
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
	protected void initializeFunctions() {
		this.loadRiscoPotencial = riscos -> {
			
			if(riscos.get("origem").getRiscoPotencial() != null)
				riscos.get("destino").setRiscoPotencial(RiscoPotencialBuilder
						.newInstance(riscos.get("origem").getRiscoPotencial()).getEntity());
			
			return riscos.get("destino");
		};
		
		this.loadTriagens = riscos -> {
			
			if(riscos.get("origem").getTriagens() != null)
				riscos.get("destino").setTriagens(TriagemBuilder
						.newInstance(riscos.get("origem").getTriagens()).getEntityList());
			
			return riscos.get("destino");
		}; 
		
		this.loadTriagensAll = riscos -> {
			
			if(riscos.get("origem").getTriagens() != null)
				riscos.get("destino").setTriagens(TriagemBuilder
						.newInstance(riscos.get("origem").getTriagens())
						.loadIndicadorAssociadoSasts()
						.getEntityList());
			
			return riscos.get("destino");
		};
		
		this.loadAcoes = riscos -> {
			
			if(riscos.get("origem").getTriagens() != null)
				riscos.get("destino").setTriagens(TriagemBuilder
						.newInstance(riscos.get("origem").getTriagens())
						.loadAcoes()
						.getEntityList());
			
			return riscos.get("destino");
		}; 
		
	}

	@Override
	protected RiscoEmpregado clone(RiscoEmpregado riscoEmpregado) {
		RiscoEmpregado cloneRiscoEmpregado = new RiscoEmpregado();
		
		cloneRiscoEmpregado.setId(riscoEmpregado.getId());
		cloneRiscoEmpregado.setVersion(riscoEmpregado.getVersion());
		cloneRiscoEmpregado.setValor(riscoEmpregado.getValor());
		cloneRiscoEmpregado.setData(riscoEmpregado.getData());
		cloneRiscoEmpregado.setStatus(riscoEmpregado.getStatus());
		
		if ( riscoEmpregado.getEquipe() != null )
			cloneRiscoEmpregado.setEquipe(EquipeBuilder.newInstance(riscoEmpregado.getEquipe()).getEntity());
		
		if ( riscoEmpregado.getProfissional() != null )
			cloneRiscoEmpregado.setProfissional(
					ProfissionalBuilder.newInstance(riscoEmpregado.getProfissional()).getEntity());
		
		return cloneRiscoEmpregado;
	}
	
	public RiscoEmpregadoBuilder loadRiscoPotencial() {
		return (RiscoEmpregadoBuilder) loadProperty(this.loadRiscoPotencial);
	}
	
	public RiscoEmpregadoBuilder loadTriagens() {
		return (RiscoEmpregadoBuilder) loadProperty(this.loadTriagens);
	}
	
	public RiscoEmpregadoBuilder loadTriagensAll() {
		return (RiscoEmpregadoBuilder) loadProperty(this.loadTriagensAll);
	}
	
	public RiscoEmpregadoBuilder loadAcoes() {
		return (RiscoEmpregadoBuilder) loadProperty(this.loadAcoes);
	}
	
	@Override
	public RiscoEmpregado cloneFromFilter(RiscoEmpregadoFilter filter) {
		return null;
	}
}
