package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericFilter;

public class AsoFilter extends GenericFilter {

	private EmpregadoFilter empregado;
	private AtendimentoFilter atendimento;
	private DateFilter data;
	private DateFilter validade;
	
	public EmpregadoFilter getEmpregado() {
		return empregado;
	}
	public void setEmpregado(EmpregadoFilter empregado) {
		this.empregado = empregado;
	}
	public AtendimentoFilter getAtendimento() {
		return atendimento;
	}
	public void setAtendimento(AtendimentoFilter atendimento) {
		this.atendimento = atendimento;
	}
	public DateFilter getData() {
		return data;
	}
	public void setData(DateFilter data) {
		this.data = data;
	}
	public DateFilter getValidade() {
		return validade;
	}
	public void setValidade(DateFilter validade) {
		this.validade = validade;
	}
}