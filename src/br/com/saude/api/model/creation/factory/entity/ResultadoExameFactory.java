package br.com.saude.api.model.creation.factory.entity;

import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.EmpregadoConvocacao;
import br.com.saude.api.model.entity.po.Exame;
import br.com.saude.api.model.entity.po.ResultadoExame;

public class ResultadoExameFactory {

	private ResultadoExame resultadoExame;
	
	public static ResultadoExameFactory newInstance() {
		return new ResultadoExameFactory();
	}
	
	private ResultadoExameFactory() {
		this.resultadoExame = new ResultadoExame();
	}
	
	public ResultadoExameFactory empregadoConvocacao() {
		this.resultadoExame.setEmpregadoConvocacao(new EmpregadoConvocacao());
		return this;
	}
	
	public ResultadoExameFactory empregadoConvocacaoEmpregado(Empregado empregado) {
		this.resultadoExame.getEmpregadoConvocacao().setEmpregado(empregado);
		return this;
	}
	
	public ResultadoExameFactory exame(Exame exame) {
		this.resultadoExame.setExame(exame);
		return this;
	}
	
	public ResultadoExame get() {
		return this.resultadoExame;
	}
}
