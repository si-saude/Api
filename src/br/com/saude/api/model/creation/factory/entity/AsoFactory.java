package br.com.saude.api.model.creation.factory.entity;

import br.com.saude.api.model.entity.po.Aso;
import br.com.saude.api.model.entity.po.Empregado;

public class AsoFactory {

	private Aso aso;
	
	public static AsoFactory newInstance() {
		return new AsoFactory();
	}
	
	private AsoFactory() {
		this.aso = new Aso();
	}
	
	public AsoFactory empregado(Empregado empregado) {
		this.aso.setEmpregado(empregado);
		return this;
	}
	
	public Aso get() {
		return this.aso;
	}
}
