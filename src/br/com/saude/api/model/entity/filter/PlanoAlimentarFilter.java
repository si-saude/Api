package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class PlanoAlimentarFilter extends GenericFilter {
	private String objetivo;
	
	private AtendimentoFilter atendimento;
	
	public String getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	public AtendimentoFilter getAtendimento() {
		return atendimento;
	}
	public void setAtendimento(AtendimentoFilter atendimento) {
		this.atendimento = atendimento;
	}
}
