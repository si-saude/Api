package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.po.AvaliacaoHigieneOcupacional;
import br.com.saude.api.model.entity.filter.AvaliacaoHigieneOcupacionalFilter;

public class AvaliacaoHigieneOcupacionalBuilder extends GenericEntityBuilder<AvaliacaoHigieneOcupacional, AvaliacaoHigieneOcupacionalFilter> {

	private Function<Map<String,AvaliacaoHigieneOcupacional>,AvaliacaoHigieneOcupacional> loadLocal;

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
		this.loadLocal = avaliacaoHigieneOcupacionais ->{
			if(avaliacaoHigieneOcupacionais.get("origem").getLocal() != null) {
				avaliacaoHigieneOcupacionais.get("destino").setLocal(LocalizacaoBuilder.newInstance(avaliacaoHigieneOcupacionais.get("origem").getLocal()).getEntity());
			}
			return avaliacaoHigieneOcupacionais.get("destino");
		};
	}

	@Override
	protected AvaliacaoHigieneOcupacional clone(AvaliacaoHigieneOcupacional avaliacaoHigieneOcupacional) {
		AvaliacaoHigieneOcupacional newAvaliacaoHigieneOcupacional = new AvaliacaoHigieneOcupacional();
		
		newAvaliacaoHigieneOcupacional.setId(avaliacaoHigieneOcupacional.getId());
		newAvaliacaoHigieneOcupacional.setBrigada(avaliacaoHigieneOcupacional.isBrigada());
		newAvaliacaoHigieneOcupacional.setData(avaliacaoHigieneOcupacional.getData());
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
		newAvaliacaoHigieneOcupacional.setVersion(avaliacaoHigieneOcupacional.getVersion());
		
		if(avaliacaoHigieneOcupacional.getEmpregado() != null)
			newAvaliacaoHigieneOcupacional.setEmpregado(EmpregadoBuilder
					.newInstance(avaliacaoHigieneOcupacional.getEmpregado()).getEntity());
		
		if(avaliacaoHigieneOcupacional.getTecnico() != null)
			newAvaliacaoHigieneOcupacional.setTecnico(ProfissionalBuilder
					.newInstance(avaliacaoHigieneOcupacional.getTecnico()).getEntity());
		
		return newAvaliacaoHigieneOcupacional;
	}
	
	@Override
	public AvaliacaoHigieneOcupacional cloneFromFilter(AvaliacaoHigieneOcupacionalFilter filter) {
		return null;
	}
	
	public AvaliacaoHigieneOcupacionalBuilder loadLocal() {
		return (AvaliacaoHigieneOcupacionalBuilder) this.loadProperty(this.loadLocal);
	}

}
