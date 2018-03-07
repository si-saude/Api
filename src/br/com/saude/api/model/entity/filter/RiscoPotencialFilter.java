package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericFilter;

public class RiscoPotencialFilter extends GenericFilter {

	private EmpregadoFilter empregado;
	private DateFilter data;
	private EquipeFilter equipeResponsavel;
	private String condutaPercepcao;
	private DateFilter inicioAgendamento;
	private DateFilter fimAgendamento;
	private BooleanFilter atual;
	
	public EmpregadoFilter getEmpregado() {
		return empregado;
	}
	public void setEmpregado(EmpregadoFilter empregado) {
		this.empregado = empregado;
	}
	public DateFilter getData() {
		return data;
	}
	public void setData(DateFilter data) {
		this.data = data;
	}
	public EquipeFilter getEquipeResponsavel() {
		return equipeResponsavel;
	}
	public void setEquipeResponsavel(EquipeFilter equipeResponsavel) {
		this.equipeResponsavel = equipeResponsavel;
	}
	public String getCondutaPercepcao() {
		return condutaPercepcao;
	}
	public void setCondutaPercepcao(String condutaPercepcao) {
		this.condutaPercepcao = condutaPercepcao;
	}
	public DateFilter getInicioAgendamento() {
		return inicioAgendamento;
	}
	public void setInicioAgendamento(DateFilter inicioAgendamento) {
		this.inicioAgendamento = inicioAgendamento;
	}
	public DateFilter getFimAgendamento() {
		return fimAgendamento;
	}
	public void setFimAgendamento(DateFilter fimAgendamento) {
		this.fimAgendamento = fimAgendamento;
	}
	public BooleanFilter getAtual() {
		return atual;
	}
	public void setAtual(BooleanFilter atual) {
		this.atual = atual;
	}
}
