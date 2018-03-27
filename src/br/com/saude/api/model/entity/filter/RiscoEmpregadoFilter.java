package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericFilter;

public class RiscoEmpregadoFilter extends GenericFilter {

	private RiscoPotencialFilter riscoPotencial;
	private EquipeFilter equipe;
	private ProfissionalFilter profissional;
	private DateFilter data;
	private String status;

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
	

	public DateFilter getData() {
		return data;
	}

	public void setData(DateFilter data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
