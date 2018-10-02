package br.com.saude.api.model.entity.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Empresa {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar Nome da Empresa.")
	@Size(max = 512, message="Tamanho máximo para Nome da Empresa: 512")
	private String nome;
	
	@Size(max = 16, message="Tamanho máximo para CNPJ da Empresa: 16")
	private String cnpj;
	
	@Size(max = 8, message="Tamanho máximo para CEP da Empresa: 8")
	private String cep;
	
	@Size(max = 256, message="Tamanho máximo para Endereço da Empresa: 256")
	private String endereco;
	
	@Size(max = 128, message="Tamanho máximo para Bairro da Empresa: 128")
	private String bairro;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Cidade municipio;
	
	@Size(max = 16, message="Tamanho máximo para Telefone da Empresa: 16")
	private String telefone;
	
	@OneToMany(mappedBy="empresa", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Cnae> cnaes;
	
	@Version
	private long version; 
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Cidade getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Cidade municipio) {
		this.municipio = municipio;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public List<Cnae> getCnaes() {
		return cnaes;
	}

	public void setCnaes(List<Cnae> cnaes) {
		this.cnaes = cnaes;
	}
	
}
