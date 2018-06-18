package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class AprhoEmpregadoFilter extends GenericFilter{

	private AprhoFilter aprho;
	private EmpregadoFilter empregado;
	private BooleanFilter atual;
	
	public AprhoFilter getAprho() {
		return aprho;
	}
	public void setAprho(AprhoFilter aprho) {
		this.aprho = aprho;
	}	
	public EmpregadoFilter getEmpregado() {
		return empregado;
	}
	public void setEmpregado(EmpregadoFilter empregado) {
		this.empregado = empregado;
	}
	public BooleanFilter getAtual() {
		return atual;
	}
	public void setAtual(BooleanFilter atual) {
		this.atual = atual;
	}
}
