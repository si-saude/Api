package br.com.saude.api.model.entity.po;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Endereco {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Logradouro do Endereço.")
	@Size(max = 200, message="Tamanho máximo para Logradouro do Endereço: 200")
	private String logradouro;
	
	@Size(max = 12, message="Tamanho máximo para Número do Endereço: 12")
	private String numero;
	
	@Size(max = 200, message="Tamanho máximo para Complemento do Endereço: 200")
	private String complemento;
	
	@Size(max = 100, message="Tamanho máximo para Bairro do Endereço: 100")
	private String bairro;
	
	@Size(max = 9, message="Tamanho máximo para Cep do Endereço: 9")
	private String cep;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Cidade cidade;
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="enderecos")
	private List<Profissional> profissionais;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public List<Profissional> getProfissionais() {
		return profissionais;
	}

	public void setProfissionais(List<Profissional> profissionais) {
		this.profissionais = profissionais;
	}
	
}
