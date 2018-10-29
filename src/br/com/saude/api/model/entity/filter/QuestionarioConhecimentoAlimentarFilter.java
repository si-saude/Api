package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class QuestionarioConhecimentoAlimentarFilter extends GenericFilter {
	private AtendimentoFilter atendimento;

	public AtendimentoFilter getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(AtendimentoFilter atendimento) {
		this.atendimento = atendimento;
	}
}
