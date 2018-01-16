package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.AsoFilter;
import br.com.saude.api.model.entity.po.Aso;

public class AsoBuilder extends GenericEntityBuilder<Aso, AsoFilter> {

	private Function<Map<String,Aso>,Aso> loadEmpregado;
	private Function<Map<String,Aso>,Aso> loadAtendimento;
	private Function<Map<String,Aso>,Aso> loadAlteracoes;
	
	public static AsoBuilder newInstance(Aso aso) {
		return new AsoBuilder(aso);
	}
	
	public static AsoBuilder newInstance(List<Aso> asos) {
		return new AsoBuilder(asos);
	}
	
	private AsoBuilder(Aso aso) {
		super(aso);
	}
	
	private AsoBuilder(List<Aso> asos) {
		super(asos);
	}

	@Override
	protected void initializeFunctions() {
		this.loadEmpregado = asos -> {
			if(asos.get("origem").getEmpregado() != null)
				asos.get("destino").setEmpregado(EmpregadoBuilder
						.newInstance(asos.get("origem").getEmpregado()).getEntity());
			return asos.get("destino");
		};
		
		this.loadAtendimento = asos -> {
			if(asos.get("origem").getAtendimento() != null)
				asos.get("destino").setAtendimento(AtendimentoBuilder
						.newInstance(asos.get("origem").getAtendimento())
						.getEntity());
			return asos.get("destino");
		};
		
		this.loadAlteracoes = asos -> {
			if(asos.get("origem").getAsoAlteracoes() != null)
				asos.get("destino").setAsoAlteracoes(AsoAlteracaoBuilder
						.newInstance(asos.get("origem").getAsoAlteracoes())
						.getEntityList());
			
			return asos.get("destino");
		};
	}

	@Override
	protected Aso clone(Aso aso) {
		Aso newAso = new Aso();
		
		newAso.setId(aso.getId());
		newAso.setData(aso.getData());
		newAso.setValidade(aso.getValidade());
		newAso.setVersion(aso.getVersion());
		
		return newAso;
	}
	
	public AsoBuilder loadEmpregado() {
		return (AsoBuilder) this.loadProperty(this.loadEmpregado);
	}
	
	public AsoBuilder loadAtendimento() {
		return (AsoBuilder) this.loadProperty(this.loadAtendimento);
	}
	
	public AsoBuilder loadAlteracoes() {
		return (AsoBuilder) this.loadProperty(this.loadAlteracoes);
	}

	@Override
	public Aso cloneFromFilter(AsoFilter filter) {
		return null;
	}
}
