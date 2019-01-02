package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericFilter;

public class AsoFilter extends GenericFilter {

	private EmpregadoFilter empregado;
	private AtendimentoFilter atendimento;
	private DateFilter data;
	private DateFilter validade;
	private BooleanFilter conforme;
	private String naoConformidades;
	private String status;
	private BooleanFilter ausenciaExames;
	private BooleanFilter impressoSd2000;
	private BooleanFilter pendente;
	private BooleanFilter convocado;
	
	public BooleanFilter getAusenciaExames() {
		return ausenciaExames;
	}
	public void setAusenciaExames(BooleanFilter ausenciaExames) {
		this.ausenciaExames = ausenciaExames;
	}
	public BooleanFilter getImpressoSd2000() {
		return impressoSd2000;
	}
	public void setImpressoSd2000(BooleanFilter impressoSd2000) {
		this.impressoSd2000 = impressoSd2000;
	}
	public BooleanFilter getPendente() {
		return pendente;
	}
	public void setPendente(BooleanFilter pendente) {
		this.pendente = pendente;
	}
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
	public BooleanFilter getConforme() {
		return conforme;
	}
	public void setConforme(BooleanFilter conforme) {
		this.conforme = conforme;
	}
	public String getNaoConformidades() {
		return naoConformidades;
	}
	public void setNaoConformidades(String naoConformidades) {
		this.naoConformidades = naoConformidades;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BooleanFilter getConvocado() {
		return convocado;
	}
	public void setConvocado(BooleanFilter convocado) {
		this.convocado = convocado;
	}
}
