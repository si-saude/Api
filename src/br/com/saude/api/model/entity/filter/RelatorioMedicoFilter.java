package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class RelatorioMedicoFilter extends GenericFilter {

	private ProfissionalFilter medico;
	private String medicoPrestador;
	private String resumo;
	private String questionamentos;
	private BooleanFilter finalizado;
	public ProfissionalFilter getMedico() {
		return medico;
	}
	public void setMedico(ProfissionalFilter medico) {
		this.medico = medico;
	}
	public String getMedicoPrestador() {
		return medicoPrestador;
	}
	public void setMedicoPrestador(String medicoPrestador) {
		this.medicoPrestador = medicoPrestador;
	}
	public String getResumo() {
		return resumo;
	}
	public void setResumo(String resumo) {
		this.resumo = resumo;
	}
	public String getQuestionamentos() {
		return questionamentos;
	}
	public void setQuestionamentos(String questionamentos) {
		this.questionamentos = questionamentos;
	}
	public BooleanFilter getFinalizado() {
		return finalizado;
	}
	public void setFinalizado(BooleanFilter finalizado) {
		this.finalizado = finalizado;
	}
}
