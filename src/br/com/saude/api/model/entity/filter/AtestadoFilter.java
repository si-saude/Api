package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class AtestadoFilter extends GenericFilter {

	private BooleanFilter atestadoFisicoRecebido;
	private BooleanFilter controleLicenca;
	private BooleanFilter impossibilidadeLocomocao;
	private BooleanFilter lancadoSap;
	private BooleanFilter aposentadoInss;
	private BooleanFilter presencial;
	private BooleanFilter possuiFeriasAgendadas;
	private BooleanFilter ciente;
	private EmpregadoFilter empregado;
	private MotivoRecusaAtestadoFilter motivoRecusa;
	
	public BooleanFilter getAtestadoFisicoRecebido() {
		return atestadoFisicoRecebido;
	}
	public void setAtestadoFisicoRecebido(BooleanFilter atestadoFisicoRecebido) {
		this.atestadoFisicoRecebido = atestadoFisicoRecebido;
	}
	public BooleanFilter getControleLicenca() {
		return controleLicenca;
	}
	public void setControleLicenca(BooleanFilter controleLicenca) {
		this.controleLicenca = controleLicenca;
	}
	public BooleanFilter getImpossibilidadeLocomocao() {
		return impossibilidadeLocomocao;
	}
	public void setImpossibilidadeLocomocao(BooleanFilter impossibilidadeLocomocao) {
		this.impossibilidadeLocomocao = impossibilidadeLocomocao;
	}
	public BooleanFilter getLancadoSap() {
		return lancadoSap;
	}
	public void setLancadoSap(BooleanFilter lancadoSap) {
		this.lancadoSap = lancadoSap;
	}
	public BooleanFilter getAposentadoInss() {
		return aposentadoInss;
	}
	public void setAposentadoInss(BooleanFilter aposentadoInss) {
		this.aposentadoInss = aposentadoInss;
	}
	public BooleanFilter getPresencial() {
		return presencial;
	}
	public void setPresencial(BooleanFilter presencial) {
		this.presencial = presencial;
	}
	public BooleanFilter getPossuiFeriasAgendadas() {
		return possuiFeriasAgendadas;
	}
	public void setPossuiFeriasAgendadas(BooleanFilter possuiFeriasAgendadas) {
		this.possuiFeriasAgendadas = possuiFeriasAgendadas;
	}
	public BooleanFilter getCiente() {
		return ciente;
	}
	public void setCiente(BooleanFilter ciente) {
		this.ciente = ciente;
	}
	public EmpregadoFilter getEmpregado() {
		return empregado;
	}
	public void setEmpregado(EmpregadoFilter empregado) {
		this.empregado = empregado;
	}
	public MotivoRecusaAtestadoFilter getMotivoRecusa() {
		return motivoRecusa;
	}
	public void setMotivoRecusa(MotivoRecusaAtestadoFilter motivoRecusa) {
		this.motivoRecusa = motivoRecusa;
	}
}
