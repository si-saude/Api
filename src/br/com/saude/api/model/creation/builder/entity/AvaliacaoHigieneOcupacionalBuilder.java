package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.po.AvaliacaoHigieneOcupacional;
import br.com.saude.api.model.entity.filter.AvaliacaoHigieneOcupacionalFilter;

public class AvaliacaoHigieneOcupacionalBuilder extends GenericEntityBuilder<AvaliacaoHigieneOcupacional, AvaliacaoHigieneOcupacionalFilter> {

	private Function<Map<String,AvaliacaoHigieneOcupacional>,AvaliacaoHigieneOcupacional> loadEmpregado;
	private Function<Map<String,AvaliacaoHigieneOcupacional>,AvaliacaoHigieneOcupacional> loadCargo;
	private Function<Map<String,AvaliacaoHigieneOcupacional>,AvaliacaoHigieneOcupacional> loadGerencia;
	private Function<Map<String,AvaliacaoHigieneOcupacional>,AvaliacaoHigieneOcupacional> loadGhe;
	private Function<Map<String,AvaliacaoHigieneOcupacional>,AvaliacaoHigieneOcupacional> loadQuestionario;
	
	public static AvaliacaoHigieneOcupacionalBuilder newInstance(AvaliacaoHigieneOcupacional avaliacaoHigieneOcupacional) {
		return new AvaliacaoHigieneOcupacionalBuilder(avaliacaoHigieneOcupacional);
	}
	
	public static AvaliacaoHigieneOcupacionalBuilder newInstance(List<AvaliacaoHigieneOcupacional> avaliacaoHigieneOcupacionais) {
		return new AvaliacaoHigieneOcupacionalBuilder(avaliacaoHigieneOcupacionais);
	}
	
	private AvaliacaoHigieneOcupacionalBuilder(AvaliacaoHigieneOcupacional avaliacaoHigieneOcupacional) {
		super(avaliacaoHigieneOcupacional);
	}
	
	private AvaliacaoHigieneOcupacionalBuilder(List<AvaliacaoHigieneOcupacional> avaliacaoHigieneOcupacionais) {
		super(avaliacaoHigieneOcupacionais);
	}

	@Override
	protected void initializeFunctions() {

		this.loadEmpregado = avaliacaoHO -> {
			if(avaliacaoHO.get("origem").getEmpregado() != null)
				avaliacaoHO.get("destino").setEmpregado(EmpregadoBuilder
						.newInstance(avaliacaoHO.get("origem").getEmpregado()).getEntity());
			return avaliacaoHO.get("destino");
		};	
		
		this.loadCargo = avaliacaoHO -> {
			if(avaliacaoHO.get("origem").getCargo() != null)
				avaliacaoHO.get("destino").setCargo(CargoBuilder
						.newInstance(avaliacaoHO.get("origem").getCargo()).getEntity());
			return avaliacaoHO.get("destino");
		};	
		
		this.loadGerencia = avaliacaoHO -> {
			if(avaliacaoHO.get("origem").getGerencia() != null)
				avaliacaoHO.get("destino").setGerencia(GerenciaBuilder
						.newInstance(avaliacaoHO.get("origem").getGerencia()).getEntity());
			return avaliacaoHO.get("destino");
		};	
		
		this.loadGhe = avaliacaoHO -> {
			if(avaliacaoHO.get("origem").getGhe() != null)
				avaliacaoHO.get("destino").setGhe(GheBuilder
						.newInstance(avaliacaoHO.get("origem").getGhe()).getEntity());
			return avaliacaoHO.get("destino");
		};	
		
		this.loadQuestionario = avaliacaoHO -> {
			if(avaliacaoHO.get("origem").getQuestionarioVedacaoMascara() != null)
				avaliacaoHO.get("destino").setQuestionarioVedacaoMascara(QuestionarioVedacaoMascaraBuilder
						.newInstance(avaliacaoHO.get("origem").getQuestionarioVedacaoMascara()).getEntity());
			return avaliacaoHO.get("destino");
		};
	}

	@Override
	protected AvaliacaoHigieneOcupacional clone(AvaliacaoHigieneOcupacional avaliacaoHigieneOcupacional) {
		AvaliacaoHigieneOcupacional newAvaliacaoHigieneOcupacional = new AvaliacaoHigieneOcupacional();
		
		newAvaliacaoHigieneOcupacional.setId(avaliacaoHigieneOcupacional.getId());
		newAvaliacaoHigieneOcupacional.setBrigada(avaliacaoHigieneOcupacional.isBrigada());
		newAvaliacaoHigieneOcupacional.setEspacoConfinado(avaliacaoHigieneOcupacional.isEspacoConfinado());
		newAvaliacaoHigieneOcupacional.setFim(avaliacaoHigieneOcupacional.getFim());
		newAvaliacaoHigieneOcupacional.setInicio(avaliacaoHigieneOcupacional.getInicio());
		newAvaliacaoHigieneOcupacional.setNaoBarbeado(avaliacaoHigieneOcupacional.isNaoBarbeado());
		newAvaliacaoHigieneOcupacional.setNaoUtilizaMascara(avaliacaoHigieneOcupacional.isNaoUtilizaMascara());
		newAvaliacaoHigieneOcupacional.setObservacao(avaliacaoHigieneOcupacional.getObservacao());
		newAvaliacaoHigieneOcupacional.setTesteSensibilidadeInsatisfatorio(avaliacaoHigieneOcupacional.isTesteSensibilidadeInsatisfatorio());
		newAvaliacaoHigieneOcupacional.setUsoVoluntario(avaliacaoHigieneOcupacional.isUsoVoluntario());
		newAvaliacaoHigieneOcupacional.setEnsaioVedacao(avaliacaoHigieneOcupacional.getEnsaioVedacao());
		newAvaliacaoHigieneOcupacional.setConcordaDescricaoAprhoGhe(avaliacaoHigieneOcupacional.isConcordaDescricaoAprhoGhe());
		newAvaliacaoHigieneOcupacional.setNaoConcordaAgentesRiscos(avaliacaoHigieneOcupacional.isNaoConcordaAgentesRiscos());
		newAvaliacaoHigieneOcupacional.setNaoConcordaAtividades(avaliacaoHigieneOcupacional.isNaoConcordaAtividades());
		newAvaliacaoHigieneOcupacional.setNaoConcordaFrequenciaExposicaoRiscos(avaliacaoHigieneOcupacional.isNaoConcordaFrequenciaExposicaoRiscos());
		newAvaliacaoHigieneOcupacional.setNaoConcordaCategoriaRiscos(avaliacaoHigieneOcupacional.isNaoConcordaCategoriaRiscos());
		newAvaliacaoHigieneOcupacional.setMotivoAnalisePreliminar(avaliacaoHigieneOcupacional.getMotivoAnalisePreliminar());
		newAvaliacaoHigieneOcupacional.setNaoConcordaCategoriaRiscos(avaliacaoHigieneOcupacional.isNaoConcordaCategoriaRiscos());
		newAvaliacaoHigieneOcupacional.setJustificativaHO(avaliacaoHigieneOcupacional.getJustificativaHO());
		newAvaliacaoHigieneOcupacional.setObservacaoGHE(avaliacaoHigieneOcupacional.getObservacaoGHE());
		newAvaliacaoHigieneOcupacional.setHOconcordaDescricaoAprhoGhe(avaliacaoHigieneOcupacional.isConcordaDescricaoAprhoGhe());
		newAvaliacaoHigieneOcupacional.setFiscalSopSg(avaliacaoHigieneOcupacional.isFiscalSopSg());
		newAvaliacaoHigieneOcupacional.setOpEcolEcomp(avaliacaoHigieneOcupacional.isOpEcolEcomp());
		newAvaliacaoHigieneOcupacional.setOutros(avaliacaoHigieneOcupacional.isOutros());
		newAvaliacaoHigieneOcupacional.setAprho(avaliacaoHigieneOcupacional.getAprho());
		newAvaliacaoHigieneOcupacional.setEnsaioVedacaoRealizado(avaliacaoHigieneOcupacional.isEnsaioVedacaoRealizado());
		
		newAvaliacaoHigieneOcupacional.setVersion(avaliacaoHigieneOcupacional.getVersion());
		
		if(avaliacaoHigieneOcupacional.getEmpregado() != null)
			newAvaliacaoHigieneOcupacional.setEmpregado(EmpregadoBuilder
					.newInstance(avaliacaoHigieneOcupacional.getEmpregado()).getEntity());		
		
		if(avaliacaoHigieneOcupacional.getGhe() != null)
			newAvaliacaoHigieneOcupacional.setGhe(GheBuilder
					.newInstance(avaliacaoHigieneOcupacional.getGhe()).getEntity());
		
		if(avaliacaoHigieneOcupacional.getCargo() != null)
			newAvaliacaoHigieneOcupacional.setCargo(CargoBuilder
					.newInstance(avaliacaoHigieneOcupacional.getCargo()).getEntity());
		
		if(avaliacaoHigieneOcupacional.getGerencia() != null)
			newAvaliacaoHigieneOcupacional.setGerencia(GerenciaBuilder
					.newInstance(avaliacaoHigieneOcupacional.getGerencia()).getEntity());
		
		if(avaliacaoHigieneOcupacional.getProfissional() != null)
			newAvaliacaoHigieneOcupacional.setProfissional(ProfissionalBuilder
					.newInstance(avaliacaoHigieneOcupacional.getProfissional()).getEntity());
		
		if(avaliacaoHigieneOcupacional.getQuestionarioVedacaoMascara() != null)
			newAvaliacaoHigieneOcupacional.setQuestionarioVedacaoMascara(QuestionarioVedacaoMascaraBuilder
					.newInstance(avaliacaoHigieneOcupacional.getQuestionarioVedacaoMascara()).getEntity());
		
		return newAvaliacaoHigieneOcupacional;
	}
	
	@Override
	public AvaliacaoHigieneOcupacional cloneFromFilter(AvaliacaoHigieneOcupacionalFilter filter) {
		return null;
	}
	
	public AvaliacaoHigieneOcupacionalBuilder loadEmpregado() {
		return (AvaliacaoHigieneOcupacionalBuilder) this.loadProperty(this.loadEmpregado);
	}
	public AvaliacaoHigieneOcupacionalBuilder loadCargo() {
		return (AvaliacaoHigieneOcupacionalBuilder) this.loadProperty(this.loadCargo);
	}
	public AvaliacaoHigieneOcupacionalBuilder loadGerencia() {
		return (AvaliacaoHigieneOcupacionalBuilder) this.loadProperty(this.loadGerencia);
	}
	public AvaliacaoHigieneOcupacionalBuilder loadGhe() {
		return (AvaliacaoHigieneOcupacionalBuilder) this.loadProperty(this.loadGhe);
	}
	public AvaliacaoHigieneOcupacionalBuilder loadQuestionario() {
		return (AvaliacaoHigieneOcupacionalBuilder) this.loadProperty(this.loadQuestionario);
	}

}
