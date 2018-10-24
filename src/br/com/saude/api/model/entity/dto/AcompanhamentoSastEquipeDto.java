package br.com.saude.api.model.entity.dto;

import java.util.List;

public class AcompanhamentoSastEquipeDto {
	private String nome;
	private List<AcompanhamentoSastIndicadorDto> indicadores;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<AcompanhamentoSastIndicadorDto> getIndicadores() {
		return indicadores;
	}
	public void setIndicadores(List<AcompanhamentoSastIndicadorDto> indicadores) {
		this.indicadores = indicadores;
	}
	
}
