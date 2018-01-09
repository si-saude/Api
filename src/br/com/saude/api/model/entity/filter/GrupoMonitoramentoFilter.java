package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class GrupoMonitoramentoFilter extends GenericFilter {

	private String nome;
	private TipoGrupoMonitoramentoFilter tipoGrupoMonitoramento;
	private BooleanFilter recorrente;
	private BooleanFilter relatorio;
	private BooleanFilter auditoriaAso;
	
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
	public BooleanFilter getRecorrente() {
		return recorrente;
	}
	public void setRecorrente(BooleanFilter recorrente) {
		this.recorrente = recorrente;
	}
	public BooleanFilter getRelatorio() {
		return relatorio;
	}
	public BooleanFilter getAuditoriaAso() {
		return auditoriaAso;
	}
	public void setAuditoriaAso(BooleanFilter auditoriaAso) {
		this.auditoriaAso = auditoriaAso;
	}
	public void setRelatorio(BooleanFilter relatorio) {
		this.relatorio = relatorio;
	}
}
