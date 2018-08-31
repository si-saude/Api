package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.model.entity.filter.AtestadoFilter;
import br.com.saude.api.model.entity.po.Atestado;

public class AtestadoExampleBuilder extends GenericExampleBuilder<Atestado, AtestadoFilter> {

	public static AtestadoExampleBuilder newInstance(AtestadoFilter filter) {
		return new AtestadoExampleBuilder(filter);
	}
	
	private AtestadoExampleBuilder(AtestadoFilter filter) {
		super(filter);
	}
	
	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException { 
		addAtestadoFisicoRecebido();
		addControleLicenca();
		addImpossibilidadeLocomocao();
		addLancadoSap();
		addAposentadoInss();
		addPresencial();
		addPossuiFeriasAgendadas();
		addCiente();
	}
	
	@Override
	protected void createExampleSelectList() { }
	
	protected void addAtestadoFisicoRecebido() {
		this.entity.setAtestadoFisicoRecebido(this.addBoolean("atestadoFisicoRecebido", this.filter.getAtestadoFisicoRecebido()));
	}
	
	protected void addControleLicenca() {
		this.entity.setControleLicenca(this.addBoolean("controleLicenca", this.filter.getControleLicenca()));
	}
	
	protected void addImpossibilidadeLocomocao() {
		this.entity.setImpossibilidadeLocomocao(this.addBoolean("impossibilidadeLocomocao", this.filter.getImpossibilidadeLocomocao()));
	}
	
	protected void addLancadoSap() {
		this.entity.setLancadoSap(this.addBoolean("lancadoSap", this.filter.getLancadoSap()));
	}
	
	protected void addAposentadoInss() {
		this.entity.setAposentadoInss(this.addBoolean("aposentadoInss", this.filter.getAposentadoInss()));
	}
	
	protected void addPresencial() {
		this.entity.setPresencial(this.addBoolean("presencial", this.filter.getPresencial()));
	}
	
	protected void addPossuiFeriasAgendadas() {
		this.entity.setPossuiFeriasAgendadas(this.addBoolean("possuiFeriasAgendadas", this.filter.getPossuiFeriasAgendadas()));
	}
	
	protected void addCiente() {
		this.entity.setCiente(this.addBoolean("ciente", this.filter.getCiente()));
	}
}
