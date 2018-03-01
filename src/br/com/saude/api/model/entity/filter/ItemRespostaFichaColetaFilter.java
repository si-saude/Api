package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class ItemRespostaFichaColetaFilter extends GenericFilter {
	private String conteudo;
	private ItemRespostaFichaColetaFilter item;
	private RespostaFichaColetaFilter resposta;
	
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public ItemRespostaFichaColetaFilter getItem() {
		return item;
	}
	public void setItem(ItemRespostaFichaColetaFilter item) {
		this.item = item;
	}
	public RespostaFichaColetaFilter getResposta() {
		return resposta;
	}
	public void setResposta(RespostaFichaColetaFilter resposta) {
		this.resposta = resposta;
	}
	
}
