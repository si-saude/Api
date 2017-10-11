package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class TipoGrupoMonitoramentoFilter extends GenericFilter {

	private String nome;
	private String descricao;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
