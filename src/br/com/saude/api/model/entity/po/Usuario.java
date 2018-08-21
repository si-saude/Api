package br.com.saude.api.model.entity.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;

@Entity
public class Usuario {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar a Chave do Usuário.")
	@Size(max = 8, message="Tamanho máximo para Chave do Usuário: 8")
	@Column(unique=true)
	private String chave;
	
	@NotNull(message="É necessário informar a Senha do Usuário.")
	private String senha;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name="usuario_perfil", 
	joinColumns = {@JoinColumn(name="usuario_id")}, 
	inverseJoinColumns = {@JoinColumn(name="perfil_id")})
	private List<Perfil> perfis;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pessoa_id")
	private Pessoa pessoa;
	
	private boolean gestorCss;
	
	@Transient
	private String token;
	
	@Version
	private long version;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public List<Perfil> getPerfis() {
		return perfis;
	}
	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public boolean isGestorCss() {
		return gestorCss;
	}
	public void setGestorCss(boolean gestorCss) {
		this.gestorCss = gestorCss;
	}
	
}
