package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.model.entity.filter.QuestionarioVedacaoMascaraFilter;
import br.com.saude.api.model.entity.po.QuestionarioVedacaoMascara;

public class QuestionarioVedacaoMascaraExampleBuilder extends GenericExampleBuilder<QuestionarioVedacaoMascara, QuestionarioVedacaoMascaraFilter> {
	public static QuestionarioVedacaoMascaraExampleBuilder newInstance(QuestionarioVedacaoMascaraFilter filter) {
		return new QuestionarioVedacaoMascaraExampleBuilder(filter);
	}
	
	private QuestionarioVedacaoMascaraExampleBuilder(QuestionarioVedacaoMascaraFilter filter) {
		super(filter);
	}
	
	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addFumacaIrritante();
		addSacarina();
		addAcetatoIsoamil();
		addBenzoatoDetonium();
		addBarba();
		addBigode();
		addCosteleta();
		addnAPelosFace();
		addOculos();
		addLenteContato();
		addnACorrecaoVisao();
		addSatisfatoria();
		addDeficiente();
		addnATesteQualitativo();	
		addSatisfatoriaTestePressao();
		addDeficienteTestePressao();
		addnATestePressao();
		addResultadoTesteVedacao();
		addEsforcoFisicoUtilizandoMascara();
	}
	
	@Override
	protected void createExampleSelectList() { }
	
	protected void addFumacaIrritante() {
		this.entity.setFumacaIrritante(this.addBoolean("fumacaIrritante", this.filter.getFumacaIrritante()));
	}
	
	protected void addSacarina() {
		this.entity.setSacarina(this.addBoolean("sacarina", this.filter.getSacarina()));
	}
	
	protected void addAcetatoIsoamil() {
		this.entity.setAcetatoIsoamil(this.addBoolean("acetatoIsoamil", this.filter.getAcetatoIsoamil()));
	}
	
	protected void addBenzoatoDetonium() {
		this.entity.setBenzoatoDetonium(this.addBoolean("benzoatoDetonium", this.filter.getBenzoatoDetonium()));
	}
	
	protected void addBarba() {
		this.entity.setBarba(this.addBoolean("barba", this.filter.getBarba()));
	}
	
	protected void addBigode() {
		this.entity.setBigode(this.addBoolean("bigode", this.filter.getBigode()));
	}
	
	protected void addCosteleta() {
		this.entity.setCosteleta(this.addBoolean("costeleta", this.filter.getCosteleta()));
	}
	
	protected void addnAPelosFace() {
		this.entity.setnAPelosFace(this.addBoolean("nAPelosFace", this.filter.getnAPelosFace()));
	}
	
	protected void addOculos() {
		this.entity.setOculos(this.addBoolean("oculos", this.filter.getOculos()));
	}
	
	protected void addLenteContato() {
		this.entity.setLenteContato(this.addBoolean("lenteContato", this.filter.getLenteContato()));
	}
	
	protected void addnACorrecaoVisao() {
		this.entity.setnACorrecaoVisao(this.addBoolean("nACorrecaoVisao", this.filter.getnACorrecaoVisao()));
	}
	
	protected void addSatisfatoria() {
		this.entity.setSatisfatoria(this.addBoolean("satisfatoria", this.filter.getSatisfatoria()));
	}
	
	protected void addDeficiente() {
		this.entity.setDeficiente(this.addBoolean("deficiente", this.filter.getDeficiente()));
	}
	
	protected void addnATesteQualitativo() {
		this.entity.setnATesteQualitativo(this.addBoolean("nATesteQualitativo", this.filter.getnATesteQualitativo()));
	}
	
	protected void addSatisfatoriaTestePressao() {
		this.entity.setSatisfatoriaTestePressao(this.addBoolean("satisfatoriaTestePressao", this.filter.getSatisfatoriaTestePressao()));
	}
	
	protected void addDeficienteTestePressao() {
		this.entity.setDeficienteTestePressao(this.addBoolean("deficienteTestePressao", this.filter.getDeficienteTestePressao()));
	}
	
	protected void addnATestePressao() {
		this.entity.setnATestePressao(this.addBoolean("nATestePressao", this.filter.getnATestePressao()));
	}
	
	protected void addResultadoTesteVedacao() {
		this.entity.setResultadoTesteVedacao(this.addBoolean("resultadoTesteVedacao", this.filter.getResultadoTesteVedacao()));
	}
	
	protected void addEsforcoFisicoUtilizandoMascara() {
		this.entity.setEsforcoFisicoUtilizandoMascara(this.addBoolean("esforcoFisicoUtilizandoMascara", this.filter.getEsforcofisicoUtilizandoMascara()));
	}
}
