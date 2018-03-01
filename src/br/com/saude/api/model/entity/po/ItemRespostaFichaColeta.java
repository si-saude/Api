package br.com.saude.api.model.entity.po;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ItemRespostaFichaColeta {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private ItemRespostaFichaColeta item;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private RespostaFichaColeta resposta;
	
	@NotNull(message="É necessário informar o Conteúdo do Item da Resposta.")
	@Size(max = 2048, message="Tamanho máximo para Conteúdo do Item da Resposta: 2048")
	private String conteudo;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public RespostaFichaColeta getResposta() {
		return resposta;
	}

	public void setResposta(RespostaFichaColeta resposta) {
		this.resposta = resposta;
	}

	public ItemRespostaFichaColeta getItem() {
		return item;
	}

	public void setItem(ItemRespostaFichaColeta item) {
		this.item = item;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
