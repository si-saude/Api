package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class ItemAuditoriaAsoFilter extends GenericFilter {
	
	private AsoFilter aso;
	private String descricao;	
	private BooleanFilter conforme;
	
	public AsoFilter getAso() {
		return aso;
	}
	public void setAso(AsoFilter aso) {
		this.aso = aso;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BooleanFilter getConforme() {
		return conforme;
	}
	public void setConforme(BooleanFilter conforme) {
		this.conforme = conforme;
	}
	
	
}
