package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.hibernate.proxy.HibernateProxy;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.TriagemFilter;
import br.com.saude.api.model.entity.po.Atendimento;
import br.com.saude.api.model.entity.po.Triagem;

public class TriagemBuilder extends GenericEntityBuilder<Triagem,TriagemFilter> {

	private Function<Map<String,Triagem>,Triagem> loadIndicadorAssociadoSasts;
	private Function<Map<String,Triagem>,Triagem> loadIndicadorEquipe;
	private Function<Map<String,Triagem>,Triagem> loadRiscoEmpregado;
	private Function<Map<String,Triagem>,Triagem> loadAcoes;
	private Function<Map<String,Triagem>,Triagem> loadAtendimento;
	
	public static TriagemBuilder newInstance(Triagem triagem) {
		return new TriagemBuilder(triagem);
	}
	
	public static TriagemBuilder newInstance(List<Triagem> triagems) {
		return new TriagemBuilder(triagems);
	}
	
	private TriagemBuilder(Triagem triagem) {
		super(triagem);
	}

	private TriagemBuilder(List<Triagem> triagens) {
		super(triagens);
	}

	@Override
	protected void initializeFunctions() {
		this.loadIndicadorAssociadoSasts = triagens -> {
			
			if(triagens.get("origem").getIndicadorSast() != null &&
					triagens.get("origem").getIndicadorSast().getIndicadorAssociadoSasts() != null ) {
				
				triagens.get("destino").getIndicadorSast().setIndicadorAssociadoSasts(
						IndicadorAssociadoSastBuilder
							.newInstance(triagens.get("origem").getIndicadorSast().getIndicadorAssociadoSasts())
							.getEntityList());
				
			}
			
			return triagens.get("destino");
		};
		
		this.loadIndicadorEquipe = triagens -> {
			
			if(triagens.get("origem").getIndicadorSast() != null &&
					triagens.get("origem").getIndicadorSast().getEquipe() != null) {
				
				triagens.get("destino").getIndicadorSast().setEquipe(EquipeBuilder
						.newInstance(triagens.get("origem").getIndicadorSast().getEquipe()).getEntity());
			}
			
			return triagens.get("destino");
		};
		
		this.loadRiscoEmpregado = triagens -> {
			
			if(triagens.get("origem").getRiscoEmpregado() != null &&
					triagens.get("origem").getRiscoEmpregado() != null) {
				
				triagens.get("destino").setRiscoEmpregado(RiscoEmpregadoBuilder
						.newInstance(triagens.get("origem").getRiscoEmpregado()).getEntity());
			}
			
			return triagens.get("destino");
		};
		
		this.loadAtendimento = triagens -> {
			
			if(triagens.get("origem").getAtendimento() != null) {
				
				Atendimento atendimento = new Atendimento();
				atendimento.setId(triagens.get("origem").getAtendimento().getId());
				atendimento.setVersion(triagens.get("origem").getAtendimento().getVersion());
				
				triagens.get("destino").setAtendimento(atendimento);
			}
			
			return triagens.get("destino");
		};
		
		this.loadAcoes = triagens -> {
			if(triagens.get("origem").getAcoes() != null)
				triagens.get("destino").setAcoes(AcaoBuilder
						.newInstance(triagens.get("origem").getAcoes())
						.loadAcompanhamentos()
						.getEntityList());
			
			return triagens.get("destino");
		};
	}

	@Override
	protected Triagem clone(Triagem triagem) {
		Triagem cloneTriagem = new Triagem();
		
		cloneTriagem.setId(triagem.getId());
		cloneTriagem.setIndice(triagem.getIndice());
		cloneTriagem.setVersion(triagem.getVersion());
		cloneTriagem.setJustificativa(triagem.getJustificativa());
		cloneTriagem.setPrazo(triagem.getPrazo());
		
		if ( triagem.getIndicadorSast() != null )
			cloneTriagem.setIndicadorSast(new IndicadorSastBuilder(triagem.getIndicadorSast()).getEntity());
		
		if( triagem.getEquipeAbordagem() != null )
			cloneTriagem.setEquipeAbordagem(EquipeBuilder.newInstance(triagem.getEquipeAbordagem()).getEntity());
		
		if( triagem.getIntervencao() != null )
			cloneTriagem.setIntervencao(IntervencaoBuilder.newInstance(triagem.getIntervencao()).getEntity());
		
		if( triagem.getDiagnostico() != null)
			cloneTriagem.setDiagnostico(DiagnosticoBuilder.newInstance(triagem.getDiagnostico()).getEntity());
		
		if( triagem.getAtendimento() != null && !(triagem.getAtendimento() instanceof HibernateProxy)) {
			cloneTriagem.setAtendimento(new Atendimento());
			cloneTriagem.getAtendimento().setId(triagem.getAtendimento().getId());
		}
		
		return cloneTriagem;
	}
	
	public TriagemBuilder loadIndicadorAssociadoSasts() {
		return (TriagemBuilder) this.loadProperty(this.loadIndicadorAssociadoSasts);
	}
	
	public TriagemBuilder loadIndicadorEquipe() {
		return (TriagemBuilder) this.loadProperty(this.loadIndicadorEquipe);
	}
	
	public TriagemBuilder loadAcoes() {
		return (TriagemBuilder) this.loadProperty(this.loadAcoes);
	}
	
	public TriagemBuilder loadRiscoEmpregado() {
		return (TriagemBuilder) this.loadProperty(this.loadRiscoEmpregado);
	}
	
	public TriagemBuilder loadAtendimento() {
		return (TriagemBuilder) this.loadProperty(this.loadAtendimento);
	}
	
	@Override
	public Triagem cloneFromFilter(TriagemFilter filter) {
		return null;
	}
}
