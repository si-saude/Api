package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class TriagemFilter extends GenericFilter {

	private RiscoEmpregadoFilter riscoEmpregado;
	private EquipeFilter equipeAbordagem;
	private IndicadorSastFilter indicadorSast;
	private BooleanFilter ignorarAcoes;

	public IndicadorSastFilter getIndicadorSast() {
		return indicadorSast;
	}

	public void setIndicadorSast(IndicadorSastFilter indicadorSast) {
		this.indicadorSast = indicadorSast;
	}

	public EquipeFilter getEquipeAbordagem() {
		return equipeAbordagem;
	}

	public void setEquipeAbordagem(EquipeFilter equipeAbordagem) {
		this.equipeAbordagem = equipeAbordagem;
	}

	public RiscoEmpregadoFilter getRiscoEmpregado() {
		return riscoEmpregado;
	}

	public void setRiscoEmpregado(RiscoEmpregadoFilter riscoEmpregado) {
		this.riscoEmpregado = riscoEmpregado;
	}

	public BooleanFilter isIgnorarAcoes() {
		return ignorarAcoes;
	}

	public void setIgnorarAcoes(BooleanFilter ignorarAcoes) {
		this.ignorarAcoes = ignorarAcoes;
	}
	
}
