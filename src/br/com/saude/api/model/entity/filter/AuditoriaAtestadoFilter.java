package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class AuditoriaAtestadoFilter extends GenericFilter {
	private BooleanFilter conforme;
	private AtestadoFilter atestado;
	private ItemAuditoriaAtestadoFilter itemAuditoriaAtestado;
	
	public BooleanFilter getConforme() {
		return conforme;
	}
	public void setConforme(BooleanFilter conforme) {
		this.conforme = conforme;
	}
	public AtestadoFilter getAtestado() {
		return atestado;
	}
	public void setAtestado(AtestadoFilter atestado) {
		this.atestado = atestado;
	}
	public ItemAuditoriaAtestadoFilter getItemAuditoriaAtestado() {
		return itemAuditoriaAtestado;
	}
	public void setItemAuditoriaAtestado(ItemAuditoriaAtestadoFilter itemAuditoriaAtestado) {
		this.itemAuditoriaAtestado = itemAuditoriaAtestado;
	}

}
