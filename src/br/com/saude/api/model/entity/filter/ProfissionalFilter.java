package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericFilter;

public class ProfissionalFilter extends GenericFilter {
	private EmpregadoFilter empregado;
	private DateFilter dataAso;
	private LocalizacaoFilter localizacao;
	private EquipeFilter equipe;
	private CurriculoFilter curriculo;
	private ProfissionalConselhoFilter profissionalConselho;
	private String mi;
	
	public EmpregadoFilter getEmpregado() {
		return empregado;
	}
	public void setEmpregado(EmpregadoFilter empregado) {
		this.empregado = empregado;
	}
	public LocalizacaoFilter getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(LocalizacaoFilter localizacao) {
		this.localizacao = localizacao;
	}
	public EquipeFilter getEquipe() {
		return equipe;
	}
	public void setEquipe(EquipeFilter equipe) {
		this.equipe = equipe;
	}
	public String getMi() {
		return mi;
	}
	public void setMi(String mi) {
		this.mi = mi;
	}
	public CurriculoFilter getCurriculo() {
		return curriculo;
	}
	public void setCurriculo(CurriculoFilter curriculo) {
		this.curriculo = curriculo;
	}
	public ProfissionalConselhoFilter getProfissionalConselho() {
		return profissionalConselho;
	}
	public void setProfissionalConselho(ProfissionalConselhoFilter profissionalConselho) {
		this.profissionalConselho = profissionalConselho;
	}
	public DateFilter getDataAso() {
		return dataAso;
	}
	public void setDataAso(DateFilter dataAso) {
		this.dataAso = dataAso;
	}
}
