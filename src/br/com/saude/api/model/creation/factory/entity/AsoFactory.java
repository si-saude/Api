package br.com.saude.api.model.creation.factory.entity;

import br.com.saude.api.model.entity.po.Aso;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.EmpregadoConvocacao;

public class AsoFactory {

	private Aso aso;
	
	public static AsoFactory newInstance() {
		return new AsoFactory();
	}
	
	private AsoFactory() {
		this.aso = new Aso();
	}
	
	public AsoFactory empregadoConvocacao() {
		this.aso.setEmpregadoConvocacao(new EmpregadoConvocacao());
		return this;
	}
	
	public AsoFactory empregadoConvocacaoEmpregado(Empregado empregado) {
		this.aso.getEmpregadoConvocacao().setEmpregado(empregado);
		return this;
	}
	
	public Aso get() {
		return this.aso;
	}
}
