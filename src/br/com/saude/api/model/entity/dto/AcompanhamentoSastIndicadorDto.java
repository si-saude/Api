package br.com.saude.api.model.entity.dto;

import java.util.List;

public class AcompanhamentoSastIndicadorDto {
	private String indicador;
	private String diagnostico;
	private String intervencao;
	private int idTriagem;
	private List<AcompanhamentoSastAcaoDto> acoes;
	
	public String getIndicador() {
		return indicador;
	}
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	public String getIntervencao() {
		return intervencao;
	}
	public void setIntervencao(String intervencao) {
		this.intervencao = intervencao;
	}
	public List<AcompanhamentoSastAcaoDto> getAcoes() {
		return acoes;
	}
	public void setAcoes(List<AcompanhamentoSastAcaoDto> acoes) {
		this.acoes = acoes;
	}
	public int getIdTriagem() {
		return idTriagem;
	}
	public void setIdTriagem(int idTriagem) {
		this.idTriagem = idTriagem;
	}
}
