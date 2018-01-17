package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.AtendimentoFilter;
import br.com.saude.api.model.entity.po.Atendimento;

public class AtendimentoBuilder extends GenericEntityBuilder<Atendimento, AtendimentoFilter> {

	private Function<Map<String,Atendimento>,Atendimento> loadTarefa;
	private Function<Map<String,Atendimento>,Atendimento> loadAso;
	
	public static AtendimentoBuilder newInstance(Atendimento atendimento) {
		return new AtendimentoBuilder(atendimento);
	}
	
	public static AtendimentoBuilder newInstance(List<Atendimento> atendimentos) {
		return new AtendimentoBuilder(atendimentos);
	}
	
	private AtendimentoBuilder(Atendimento atendimento) {
		super(atendimento);
	}
	
	private AtendimentoBuilder(List<Atendimento> atendimentos) {
		super(atendimentos);
	}

	@Override
	protected void initializeFunctions() {
		this.loadTarefa = atendimentos -> {
			if(atendimentos.get("origem").getTarefa() != null)
				atendimentos.get("destino").setTarefa(TarefaBuilder
						.newInstance(atendimentos.get("origem").getTarefa())
						.getEntity());
			return atendimentos.get("destino");
		};
		
		this.loadAso = atendimentos -> {
			if(atendimentos.get("origem").getAso() != null)
				atendimentos.get("destino").setAso(AsoBuilder
						.newInstance(atendimentos.get("origem").getAso())
						.getEntity());
			return atendimentos.get("destino");
		};
	}

	@Override
	protected Atendimento clone(Atendimento atendimento) {
		Atendimento newAtendimento = new Atendimento();
		
		newAtendimento.setId(atendimento.getId());
		newAtendimento.setVersion(atendimento.getVersion());
		
		if(atendimento.getFilaAtendimentoOcupacional() != null)
			newAtendimento.setFilaAtendimentoOcupacional(FilaAtendimentoOcupacionalBuilder
					.newInstance(atendimento.getFilaAtendimentoOcupacional())
					.loadLocalizacao()
					.getEntity());
		
		if(atendimento.getFilaEsperaOcupacional() != null)
			newAtendimento.setFilaEsperaOcupacional(FilaEsperaOcupacionalBuilder
					.newInstance(atendimento.getFilaEsperaOcupacional())
					.loadLocalizacao()
					.getEntity());
		
		return newAtendimento;
	}
	
	public AtendimentoBuilder loadTarefa() {
		return (AtendimentoBuilder) this.loadProperty(this.loadTarefa);
	}
	
	public AtendimentoBuilder loadAso() {
		return (AtendimentoBuilder) this.loadProperty(this.loadAso);
	}

	@Override
	public Atendimento cloneFromFilter(AtendimentoFilter filter) {
		return null;
	}
}
