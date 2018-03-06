package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class RiscoEmpregadoFilter extends GenericFilter {

	private RiscoPotencialFilter riscoPotencial;

	public RiscoPotencialFilter getRiscoPotencial() {
		return riscoPotencial;
	}

	public void setRiscoPotencial(RiscoPotencialFilter riscoPotencial) {
		this.riscoPotencial = riscoPotencial;
	}
}
