package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class RecordatorioFilter extends GenericFilter {
	private String nome;
	private AtendimentoFilter atendimento;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public AtendimentoFilter getAtendimento() {
		return atendimento;
	}
	public void setAtendimento(AtendimentoFilter atendimento) {
		this.atendimento = atendimento;
	}
}
