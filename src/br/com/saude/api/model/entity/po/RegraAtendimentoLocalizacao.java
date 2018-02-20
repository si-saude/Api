package br.com.saude.api.model.entity.po;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
public class RegraAtendimentoLocalizacao {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar a Localização da Regra Atendimento Localizacao.")
	@ManyToOne(fetch=FetchType.LAZY)
	private Localizacao localizacao;
	
	@NotNull(message="É necessário informar a Regra Atendimento da Regra Atendimento Localizacao.")
	@ManyToOne(fetch=FetchType.EAGER)
	private RegraAtendimento regraAtendimento;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="regraatendimentolocalizacao_servico", 
	joinColumns = {@JoinColumn(name="regraatendimentolocalizacao_id")}, 
	inverseJoinColumns = {@JoinColumn(name="servico_id")})
	private List<Servico> servicos;
	
	@Version
	private long version;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public RegraAtendimento getRegraAtendimento() {
		return regraAtendimento;
	}

	public void setRegraAtendimento(RegraAtendimento regraAtendimento) {
		this.regraAtendimento = regraAtendimento;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
}
