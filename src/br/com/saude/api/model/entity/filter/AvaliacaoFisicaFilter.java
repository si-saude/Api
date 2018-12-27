package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class AvaliacaoFisicaFilter extends GenericFilter {
	private BooleanFilter  praticaExercicioFisico;
	private BooleanFilter  interesseProgramaFisico;
	private BooleanFilter  acaoRealizarExercicioFisico;
	private AtendimentoFilter atendimento;
	
	public BooleanFilter getPraticaExercicioFisico() {
		return praticaExercicioFisico;
	}
	public void setPraticaExercicioFisico(BooleanFilter praticaExercicioFisico) {
		this.praticaExercicioFisico = praticaExercicioFisico;
	}
	public BooleanFilter getInteresseProgramaFisico() {
		return interesseProgramaFisico;
	}
	public void setInteresseProgramaFisico(BooleanFilter interesseProgramaFisico) {
		this.interesseProgramaFisico = interesseProgramaFisico;
	}
	public BooleanFilter getAcaoRealizarExercicioFisico() {
		return acaoRealizarExercicioFisico;
	}
	public void setAcaoRealizarExercicioFisico(BooleanFilter acaoRealizarExercicioFisico) {
		this.acaoRealizarExercicioFisico = acaoRealizarExercicioFisico;
	}
	public AtendimentoFilter getAtendimento() {
		return atendimento;
	}
	public void setAtendimento(AtendimentoFilter atendimento) {
		this.atendimento = atendimento;
	}
}
