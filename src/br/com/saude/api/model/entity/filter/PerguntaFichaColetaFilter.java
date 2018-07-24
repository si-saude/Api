package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class PerguntaFichaColetaFilter extends GenericFilter {
	private String tipo;
	private String codigo;
	private String path;
	private String grupo;
	private BooleanFilter inativo;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public BooleanFilter getInativo() {
		return inativo;
	}
	public void setInativo(BooleanFilter inativo) {
		this.inativo = inativo;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
