package br.com.saude.api.model.entity.po;

import java.util.List;

import javax.persistence.CascadeType;
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
public class Fornecedor {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Size(max = 64, message="Tamanho máximo para Nome/Razão Social do Fornecedor: 64")
	@NotNull(message="É necessário informar o Nome/Razão Social do Fornecedor.")
	private String razaoSocial;
	
	@NotNull(message="É necessário informar o Logradouro do Endereço.")
	@Size(max = 16, message="Tamanho máximo para Tipo de Pessoa: 16")
	private String tipoPessoa;
	
	@NotNull(message="É necessário informar o CPF/CNPJ do Fornecedor.")
	@Size(max = 14, message="Tamanho máximo para CPF/CNPJ do Fornecedor: 14")
	private String cpfCnpj;
	
	@Size(max = 32, message="Tamanho máximo para Código SAP do Fornecedor: 32")
	private String codigoSap;
	
	@Size(max = 128, message="Tamanho máximo para Email do Fornecedor: 128")
	@Email(message="Email inválido para Fornecedor.")
	private String email;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;
	
	@Size(max = 16, message="Tamanho máximo para Sexo: 16")
	private String atividadeFornecedor;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="telefone_fornecedor", 
				joinColumns = {@JoinColumn(name="fornecedor_id")}, 
				inverseJoinColumns = {@JoinColumn(name="telefone_id")})	
	private List<Telefone> telefones;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getCodigoSap() {
		return codigoSap;
	}

	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public String getAtividadeFornecedor() {
		return atividadeFornecedor;
	}

	public void setAtividadeFornecedor(String atividadeFornecedor) {
		this.atividadeFornecedor = atividadeFornecedor;
	}


	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
