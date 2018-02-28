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
public class RespostaFichaColeta {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar a Pergunta da Resposta.")
	@ManyToOne(fetch=FetchType.EAGER)
	private PerguntaFichaColeta pergunta;
	
	@NotNull(message="É necessário informar a Ficha da Resposta.")
	@ManyToOne(fetch=FetchType.EAGER)
	private FichaColeta ficha;
	
	@NotNull(message="É necessário informar o Conteúdo da Resposta.")
	@Size(max = 2048, message="Tamanho máximo para Conteúdo da Pergunta: 2048")
	private String conteudo;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private ItemRespostaFichaColeta item;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PerguntaFichaColeta getPergunta() {
		return pergunta;
	}

	public void setPergunta(PerguntaFichaColeta pergunta) {
		this.pergunta = pergunta;
	}

	public FichaColeta getFicha() {
		return ficha;
	}

	public void setFicha(FichaColeta ficha) {
		this.ficha = ficha;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public ItemRespostaFichaColeta getItem() {
		return item;
	}

	public void setItem(ItemRespostaFichaColeta item) {
		this.item = item;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
