package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class AtestadoFilter extends GenericFilter {

	private BooleanFilter atestadoFisicoRecebido;
	private BooleanFilter controleLicenca;
	private BooleanFilter impossibilidadeLocomocao;
	private BooleanFilter lancadoSap;
	
	public BooleanFilter getAtestadoFisicoRecebido() {
		return atestadoFisicoRecebido;
	}
	public void setAtestadoFisicoRecebido(BooleanFilter atestadoFisicoRecebido) {
		this.atestadoFisicoRecebido = atestadoFisicoRecebido;
	}
	public BooleanFilter getControleLicenca() {
		return controleLicenca;
	}
	public void setControleLicenca(BooleanFilter controleLicenca) {
		this.controleLicenca = controleLicenca;
	}
	public BooleanFilter getImpossibilidadeLocomocao() {
		return impossibilidadeLocomocao;
	}
	public void setImpossibilidadeLocomocao(BooleanFilter impossibilidadeLocomocao) {
		this.impossibilidadeLocomocao = impossibilidadeLocomocao;
	}
	public BooleanFilter getLancadoSap() {
		return lancadoSap;
	}
	public void setLancadoSap(BooleanFilter lancadoSap) {
		this.lancadoSap = lancadoSap;
	}
	
}
