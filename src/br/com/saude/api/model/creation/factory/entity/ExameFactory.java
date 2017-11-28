package br.com.saude.api.model.creation.factory.entity;

import br.com.saude.api.model.entity.po.Exame;

public class ExameFactory {

	private Exame exame;
	
	public static ExameFactory newInstance() {
		return new ExameFactory();
	}
	
	private ExameFactory() {
		this.exame = new Exame();
	}
	
	public ExameFactory descricao(String descricao) {
		this.exame.setDescricao(descricao);
		return this;
	}
	
	public Exame get() {
		return this.exame;
	}
}
