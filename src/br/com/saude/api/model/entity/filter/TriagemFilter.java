package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class TriagemFilter extends GenericFilter {

	private RiscoEmpregadoFilter riscoEmpregado;
	private EquipeFilter equipeAbordagem;

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
}
