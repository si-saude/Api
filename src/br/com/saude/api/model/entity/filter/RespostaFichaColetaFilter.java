package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class RespostaFichaColetaFilter extends GenericFilter {
	private PerguntaFichaColetaFilter pergunta;
	private FichaColetaFilter ficha;
	private String conteudo;
	
	public PerguntaFichaColetaFilter getPergunta() {
		return pergunta;
	}
	public void setPergunta(PerguntaFichaColetaFilter pergunta) {
		this.pergunta = pergunta;
	}
	public FichaColetaFilter getFicha() {
		return ficha;
	}
	public void setFicha(FichaColetaFilter ficha) {
		this.ficha = ficha;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
}
