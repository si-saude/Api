package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.hibernate.collection.internal.PersistentBag;
import org.hibernate.proxy.HibernateProxy;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.AtendimentoFilter;
import br.com.saude.api.model.entity.po.Atendimento;

public class AtendimentoBuilder extends GenericEntityBuilder<Atendimento, AtendimentoFilter> {

	private Function<Map<String,Atendimento>,Atendimento> loadTarefa;
	private Function<Map<String,Atendimento>,Atendimento> loadTriagens;
	private Function<Map<String,Atendimento>,Atendimento> loadAso;
	private Function<Map<String,Atendimento>,Atendimento> loadQuestionario;
	
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
						.loadAptidoes()
						.loadAlteracoes()
						.loadExamesConvocacao()
						.loadAvaliacoes()
						.getEntity());
			return atendimentos.get("destino");
		};
		
		this.loadTriagens = atendimentos -> {
			if (atendimentos.get("origem").getTriagens() != null)
				atendimentos.get("destino").setTriagens(TriagemBuilder
						.newInstance(atendimentos.get("origem").getTriagens())
						.getEntityList());
			return atendimentos.get("destino");
		};
		
		this.loadQuestionario = atendimentos -> {
			if (atendimentos.get("origem").getQuestionario() != null)
				atendimentos.get("destino").setQuestionario(QuestionarioConhecimentoAlimentarBuilder
						.newInstance(atendimentos.get("origem").getQuestionario())
						.getEntity());
			return atendimentos.get("destino");
		};
	}

	@Override
	protected Atendimento clone(Atendimento atendimento) {
		Atendimento newAtendimento = new Atendimento();
		
		newAtendimento.setId(atendimento.getId());
		newAtendimento.setVersion(atendimento.getVersion());
				
		if(atendimento.getFilaAtendimentoOcupacional() != null) {
			FilaAtendimentoOcupacionalBuilder filaAtendBuilder = FilaAtendimentoOcupacionalBuilder
					.newInstance(atendimento.getFilaAtendimentoOcupacional());
			
			if(!(atendimento.getFilaAtendimentoOcupacional().getLocalizacao() instanceof HibernateProxy))
				filaAtendBuilder = filaAtendBuilder.loadLocalizacao();
			
			if(!(atendimento.getFilaAtendimentoOcupacional().getAtualizacoes() instanceof PersistentBag))
				filaAtendBuilder = filaAtendBuilder.loadAtualizacoes();
			
			newAtendimento.setFilaAtendimentoOcupacional(filaAtendBuilder.getEntity());
		}
		
		if(atendimento.getFilaEsperaOcupacional() != null) {
			FilaEsperaOcupacionalBuilder filaEsperaBuilder = FilaEsperaOcupacionalBuilder
					.newInstance(atendimento.getFilaEsperaOcupacional());
			
			if(!(atendimento.getFilaEsperaOcupacional().getLocalizacao() instanceof HibernateProxy))
				filaEsperaBuilder = filaEsperaBuilder.loadLocalizacao();
			
			if(!(atendimento.getFilaEsperaOcupacional().getFichaColeta() instanceof HibernateProxy))
				filaEsperaBuilder = filaEsperaBuilder.loadFichaColeta();
			
			if(!(atendimento.getFilaEsperaOcupacional().getRiscoPotencial() instanceof HibernateProxy))
				filaEsperaBuilder = filaEsperaBuilder.loadRiscoPotencialEquipes();
			
			newAtendimento.setFilaEsperaOcupacional(filaEsperaBuilder.getEntity());
		}
		
		return newAtendimento;
	}
	
	public AtendimentoBuilder loadTarefa() {
		return (AtendimentoBuilder) this.loadProperty(this.loadTarefa);
	}
	
	public AtendimentoBuilder loadAso() {
		return (AtendimentoBuilder) this.loadProperty(this.loadAso);
	}
	
	public AtendimentoBuilder loadQuestionario() {
		return (AtendimentoBuilder) this.loadProperty(this.loadQuestionario);
	}
	
	public AtendimentoBuilder loadTriagens() {
		return (AtendimentoBuilder) this.loadProperty(this.loadTriagens);
	}

	@Override
	public Atendimento cloneFromFilter(AtendimentoFilter filter) {
		return null;
	}
}
