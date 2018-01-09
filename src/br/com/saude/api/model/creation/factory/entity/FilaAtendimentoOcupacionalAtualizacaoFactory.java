package br.com.saude.api.model.creation.factory.entity;

import br.com.saude.api.model.entity.po.FilaAtendimentoOcupacional;
import br.com.saude.api.model.entity.po.FilaAtendimentoOcupacionalAtualizacao;

public class FilaAtendimentoOcupacionalAtualizacaoFactory {

	private FilaAtendimentoOcupacionalAtualizacao atualizacao;
	
	public static FilaAtendimentoOcupacionalAtualizacaoFactory newInstance() {
		return new FilaAtendimentoOcupacionalAtualizacaoFactory();
	}
	
	private FilaAtendimentoOcupacionalAtualizacaoFactory() {
		this.atualizacao = new FilaAtendimentoOcupacionalAtualizacao();
	}
	
	public FilaAtendimentoOcupacionalAtualizacaoFactory filaAtendimentoOcupacional(FilaAtendimentoOcupacional fila) {
		this.atualizacao.setFila(fila);
		return this;
	}
	
	public FilaAtendimentoOcupacionalAtualizacaoFactory status(String status) {
		this.atualizacao.setStatus(status);
		return this;
	}
	
	public FilaAtendimentoOcupacionalAtualizacaoFactory tempo(long tempo) {
		this.atualizacao.setTempo(tempo);
		return this;
	}
	
	public FilaAtendimentoOcupacionalAtualizacao get() {
		return this.atualizacao;
	}
}
