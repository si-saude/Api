package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class EmpregadoConvocacaoFilter extends GenericFilter{

	private EmpregadoFilter empregado;
	private ConvocacaoFilter convocacao;
	private BooleanFilter convocado;
	private BooleanFilter auditado;
	private BooleanFilter resultadoAuditado;
	private BooleanFilter auditadoSd2000;
	
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
	public BooleanFilter getResultadoAuditado() {
		return resultadoAuditado;
	}
	public void setResultadoAuditado(BooleanFilter resultadoAuditado) {
		this.resultadoAuditado = resultadoAuditado;
	}
	public BooleanFilter getAuditadoSd2000() {
		return auditadoSd2000;
	}
	public void setAuditadoSd2000(BooleanFilter auditadoSd2000) {
		this.auditadoSd2000 = auditadoSd2000;
	}
}
