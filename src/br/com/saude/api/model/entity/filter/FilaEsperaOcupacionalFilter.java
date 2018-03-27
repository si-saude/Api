package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericFilter;

public class FilaEsperaOcupacionalFilter extends GenericFilter {

	private ServicoFilter servico;
	private LocalizacaoFilter localizacao;
	private EmpregadoFilter empregado;
	private DateFilter horarioCheckin;
	private DateFilter atualizacao;
	private String status;
	private long tempoEspera;
	private DateFilter saida;
	private RiscoPotencialFilter riscoPotencial;
	
	public ServicoFilter getServico() {
		return servico;
	}
	public void setServico(ServicoFilter servico) {
		this.servico = servico;
	}
	public LocalizacaoFilter getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(LocalizacaoFilter localizacao) {
		this.localizacao = localizacao;
	}
	public EmpregadoFilter getEmpregado() {
		return empregado;
	}
	public void setEmpregado(EmpregadoFilter empregado) {
		this.empregado = empregado;
	}
	public DateFilter getHorarioCheckin() {
		return horarioCheckin;
	}
	public void setHorarioCheckin(DateFilter horarioCheckin) {
		this.horarioCheckin = horarioCheckin;
	}
	public DateFilter getAtualizacao() {
		return atualizacao;
	}
	public void setAtualizacao(DateFilter atualizacao) {
		this.atualizacao = atualizacao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getTempoEspera() {
		return tempoEspera;
	}
	public void setTempoEspera(long tempoEspera) {
		this.tempoEspera = tempoEspera;
	}
	public DateFilter getSaida() {
		return saida;
	}
	public void setSaida(DateFilter saida) {
		this.saida = saida;
	}
	public RiscoPotencialFilter getRiscoPotencial() {
		return riscoPotencial;
	}
	public void setRiscoPotencial(RiscoPotencialFilter riscoPotencial) {
		this.riscoPotencial = riscoPotencial;
	}
	
}
