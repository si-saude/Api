package br.com.saude.api.model.creation.builder.example;

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
		addBrigado();
		addEspacoConfina();
		addUsoVoluntario();
		addNaoBarbeado();
		addNaoUtilizaMascara();
		addTesteSensibilidadeInsatisfatorio();
	}
	
	@Override
	protected void createExampleSelectList() { }
	
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

}
