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
public class ItemResultadoExame {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Resultado Exame do Item.")
	@ManyToOne(fetch=FetchType.LAZY)
	private ResultadoExame resultadoExame;
	
	private String codigo;
	
	private String titulo;
	
	@Size(max = 2048, message="Tamanho máximo para Resultado: 2048")
	private String resultado;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ResultadoExame getResultadoExame() {
		return resultadoExame;
	}

	public void setResultadoExame(ResultadoExame resultadoExame) {
		this.resultadoExame = resultadoExame;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
}