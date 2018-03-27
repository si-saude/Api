package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class RiscoEmpregadoFilter extends GenericFilter {

	private RiscoPotencialFilter riscoPotencial;
	private EquipeFilter equipe;
	private ProfissionalFilter profissional;

	public RiscoPotencialFilter getRiscoPotencial() {
		return riscoPotencial;
	}

	public void setRiscoPotencial(RiscoPotencialFilter riscoPotencial) {
		this.riscoPotencial = riscoPotencial;
	}

	public EquipeFilter getEquipe() {
		return equipe;
	}

	public void setEquipe(EquipeFilter equipe) {
		this.equipe = equipe;
	}

	public ProfissionalFilter getProfissional() {
		return profissional;
	}

	public void setProfissional(ProfissionalFilter profissional) {
		this.profissional = profissional;
	}
	
}
