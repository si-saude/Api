package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.model.entity.filter.AvaliacaoHigieneOcupacionalFilter;
import br.com.saude.api.model.entity.po.AvaliacaoHigieneOcupacional;

public class AvaliacaoHigieneOcupacionalExampleBuilder extends GenericExampleBuilder<AvaliacaoHigieneOcupacional, AvaliacaoHigieneOcupacionalFilter> {
	
	public static AvaliacaoHigieneOcupacionalExampleBuilder newInstance(AvaliacaoHigieneOcupacionalFilter filter) {
		return new AvaliacaoHigieneOcupacionalExampleBuilder(filter);
	}
	
	private AvaliacaoHigieneOcupacionalExampleBuilder(AvaliacaoHigieneOcupacionalFilter filter) {
		super(filter);
	}
	
	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addId();
		addEmpregado();
		addBrigado();
		addEspacoConfina();
		addUsoVoluntario();
		addNaoBarbeado();
		addNaoUtilizaMascara();
		addTesteSensibilidadeInsatisfatorio();
		addNaoConcordaCategoriaRiscos();
		addNaoConcordaFrequenciaExposicaoRiscos();
		addNaoConcordaAtividades();
		addNaoConcordaAgentesRiscos();
		addConcordaDescricaoAprhoGhe();
		addEnsaioVedacaoRealizado();
		addFiscalSopSg();
		addHOconcordaDescricaoAprhoGhe();
		addOpEcolEcomp();
		addOutros();
	}
	
	@Override
	protected void createExampleSelectList() { }
	
	private void addId() {
		if(this.filter.getId() > 0)
			this.criterions.add(Restrictions.eq("id", this.filter.getId()));
	}	
	
	protected void addBrigado() {
		this.entity.setBrigada(this.addBoolean("brigada", this.filter.isBrigada()));
	}
	
	protected void addEspacoConfina() {
		this.entity.setEspacoConfinado(this.addBoolean("espacoConfinado", this.filter.isEspacoConfinado()));
	}
	
	protected void addUsoVoluntario() {
		this.entity.setUsoVoluntario(this.addBoolean("usoVoluntario", this.filter.isUsoVoluntario()));
	}
	
	protected void addNaoBarbeado() {
		this.entity.setNaoBarbeado(this.addBoolean("naoBarbeado", this.filter.isNaoBarbeado()));
	}
	
	protected void addNaoUtilizaMascara() {
		this.entity.setNaoUtilizaMascara(this.addBoolean("naoUtilizaMascara", this.filter.isNaoUtilizaMascara()));
	}
	
	protected void addTesteSensibilidadeInsatisfatorio() {
		this.entity.setTesteSensibilidadeInsatisfatorio(this.addBoolean("testeSensibilidadeInsatisfatorio", this.filter.isTesteSensibilidadeInsatisfatorio()));
	}
	
	protected void addConcordaDescricaoAprhoGhe() {
		this.entity.setConcordaDescricaoAprhoGhe(this.addBoolean("concordaDescricaoAprhoGhe", this.filter.isConcordaDescricaoAprhoGhe()));
	}
	
	protected void addNaoConcordaAgentesRiscos() {
		this.entity.setNaoConcordaAgentesRiscos(this.addBoolean("naoConcordaAgentesRiscos", this.filter.isNaoConcordaAgentesRiscos()));
	}
	
	protected void addNaoConcordaAtividades() {
		this.entity.setNaoConcordaAtividades(this.addBoolean("naoConcordaAtividades", this.filter.isNaoConcordaAtividades()));
	}
	
	protected void addNaoConcordaFrequenciaExposicaoRiscos() {
		this.entity.setNaoConcordaFrequenciaExposicaoRiscos(this.addBoolean("naoConcordaFrequenciaExposicaoRiscos", this.filter.isNaoConcordaFrequenciaExposicaoRiscos()));
	}
	
	protected void addNaoConcordaCategoriaRiscos() {
		this.entity.setNaoConcordaCategoriaRiscos(this.addBoolean("naoConcordaCategoriaRiscos", this.filter.isNaoConcordaCategoriaRiscos()));
	}
	
	private void addEmpregado() throws InstantiationException, IllegalAccessException {
		if(this.filter.getEmpregado()!=null) {
			CriteriaExample criteriaExample = EmpregadoExampleBuilder
					.newInstance(this.filter.getEmpregado()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("empregado", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	protected void addEnsaioVedacaoRealizado() {
		this.entity.setNaoConcordaCategoriaRiscos(this.addBoolean("ensaioVedacaoRealizado", this.filter.getEnsaioVedacaoRealizado()));
	}
	
	protected void addFiscalSopSg() {
		this.entity.setNaoConcordaCategoriaRiscos(this.addBoolean("fiscalSopSg", this.filter.getFiscalSopSg()));
	}
	
	
	protected void addHOconcordaDescricaoAprhoGhe() {
		this.entity.setNaoConcordaCategoriaRiscos(this.addBoolean("hOconcordaDescricaoAprhoGhe", this.filter.getHOconcordaDescricaoAprhoGhe()));
	}
	
	protected void addOpEcolEcomp() {
		this.entity.setNaoConcordaCategoriaRiscos(this.addBoolean("opEcolEcomp", this.filter.getOpEcolEcomp()));
	}
	
	protected void addOutros() {
		this.entity.setNaoConcordaCategoriaRiscos(this.addBoolean("outros", this.filter.getOutros()));
	}	
}
