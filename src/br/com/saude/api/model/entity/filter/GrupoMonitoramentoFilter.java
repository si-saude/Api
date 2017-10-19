package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class GrupoMonitoramentoFilter extends GenericFilter {

	private String nome;
	private TipoGrupoMonitoramentoFilter tipoGrupoMonitoramento;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public TipoGrupoMonitoramentoFilter getTipoGrupoMonitoramento() {
		return tipoGrupoMonitoramento;
	}
	public void setTipoGrupoMonitoramento(TipoGrupoMonitoramentoFilter tipoGrupoMonitoramento) {
		this.tipoGrupoMonitoramento = tipoGrupoMonitoramento;
	}
}
