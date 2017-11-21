package br.com.saude.api.model.entity.po;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
public class Pessoa {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Nome.")
	@Size(max = 400, message="Tamanho máximo para Nome: 400")
	private String nome;
	
	@Size(max = 32, message="Tamanho máximo para RGa: 32")
	private String rg;
	
	@Size(max = 128, message="Tamanho máximo para Email: 128")
	@Email(message="Valor inválido para Email.")
	private String email;
	
	@Column(unique=true)
	@Size(max = 11, message="Tamanho máximo para Cpf: 11")
	@NotNull(message="É necessário informar o Cpf.")
	private String cpf;
	
	private Date dataNascimento;
	
	@Size(max = 16, message="Tamanho máximo para Sexo: 16")
	private String sexo;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="telefone_pessoa", 
				joinColumns = {@JoinColumn(name="pessoa_id")}, 
				inverseJoinColumns = {@JoinColumn(name="telefone_id")})
	private List<Telefone> telefones;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "empregado_id")
	private Empregado empregado;
	
	@Version
	private long version;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public List<Telefone> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	public Empregado getEmpregado() {
		return empregado;
	}
	public void setEmpregado(Empregado empregado) {
		this.empregado = empregado;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
