package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class DiagnosticoFilter extends GenericFilter {
	private String codigo;
	private String descricao;
	private BooleanFilter inativo = new BooleanFilter();
	private EixoFilter eixo;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BooleanFilter getInativo() {
		return inativo;
	}
	public void setInativo(BooleanFilter inativo) {
		this.inativo = inativo;
	}
	public EixoFilter getEixo() {
		return eixo;
	}
	public void setEixo(EixoFilter eixo) {
		this.eixo = eixo;
	}
		
}