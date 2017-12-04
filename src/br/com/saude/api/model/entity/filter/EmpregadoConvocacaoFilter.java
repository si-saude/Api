package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class EmpregadoConvocacaoFilter extends GenericFilter{

	private EmpregadoFilter empregado;
	private ConvocacaoFilter convocacao;
	private BooleanFilter convocado;
	private BooleanFilter auditado;
	public EmpregadoFilter getEmpregado() {
		return empregado;
	}
	public void setEmpregado(EmpregadoFilter empregado) {
		this.empregado = empregado;
	}
	public ConvocacaoFilter getConvocacao() {
		return convocacao;
	}
	public void setConvocacao(ConvocacaoFilter convocacao) {
		this.convocacao = convocacao;
	}
	public BooleanFilter getConvocado() {
		return convocado;
	}
	public void setConvocado(BooleanFilter convocado) {
		this.convocado = convocado;
	}
	public BooleanFilter getAuditado() {
		return auditado;
	}
	public void setAuditado(BooleanFilter auditado) {
		this.auditado = auditado;
	}
}
