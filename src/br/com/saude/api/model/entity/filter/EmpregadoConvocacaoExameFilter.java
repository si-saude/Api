package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericFilter;

public class EmpregadoConvocacaoExameFilter extends GenericFilter {

	private ExameFilter exame;
	private EmpregadoConvocacaoFilter empregadoConvocacao;
	private BooleanFilter exigeRelatorio;
	private BooleanFilter conforme;
	private BooleanFilter opcional;
	private DateFilter realizacao;
	private DateFilter recebimento;
	private DateFilter auditoria;
	
	public ExameFilter getExame() {
		return exame;
	}
	public void setExame(ExameFilter exame) {
		this.exame = exame;
	}
	public EmpregadoConvocacaoFilter getEmpregadoConvocacao() {
		return empregadoConvocacao;
	}
	public void setEmpregadoConvocacao(EmpregadoConvocacaoFilter empregadoConvocacao) {
		this.empregadoConvocacao = empregadoConvocacao;
	}
	public DateFilter getRealizacao() {
		return realizacao;
	}
	public void setRealizacao(DateFilter realizacao) {
		this.realizacao = realizacao;
	}
	public BooleanFilter getExigeRelatorio() {
		return exigeRelatorio;
	}
	public void setExigeRelatorio(BooleanFilter exigeRelatorio) {
		this.exigeRelatorio = exigeRelatorio;
	}
	public BooleanFilter getConforme() {
		return conforme;
	}
	public void setConforme(BooleanFilter conforme) {
		this.conforme = conforme;
	}
	public DateFilter getRecebimento() {
		return recebimento;
	}
	public void setRecebimento(DateFilter recebimento) {
		this.recebimento = recebimento;
	}
	public DateFilter getAuditoria() {
		return auditoria;
	}
	public void setAuditoria(DateFilter auditoria) {
		this.auditoria = auditoria;
	}
	public BooleanFilter getOpcional() {
		return opcional;
	}
	public void setOpcional(BooleanFilter opcional) {
		this.opcional = opcional;
	}
	
}
