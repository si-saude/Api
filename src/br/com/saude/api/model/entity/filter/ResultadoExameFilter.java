package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericFilter;

public class ResultadoExameFilter extends GenericFilter {
	private EmpregadoConvocacaoFilter empregadoConvocacao;
	private ExameFilter exame;
	private DateFilter data;
	private DateFilter dataRecebimento;
	private String tipo;
	private String acao;
	private String local;
	private BooleanFilter conforme;
	
	public EmpregadoConvocacaoFilter getEmpregadoConvocacao() {
		return empregadoConvocacao;
	}
	public void setEmpregadoConvocacao(EmpregadoConvocacaoFilter empregadoConvocacao) {
		this.empregadoConvocacao = empregadoConvocacao;
	}
	public ExameFilter getExame() {
		return exame;
	}
	public void setExame(ExameFilter exame) {
		this.exame = exame;
	}
	public DateFilter getData() {
		return data;
	}
	public void setData(DateFilter data) {
		this.data = data;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public BooleanFilter getConforme() {
		return conforme;
	}
	public void setConforme(BooleanFilter conforme) {
		this.conforme = conforme;
	}
	public DateFilter getDataRecebimento() {
		return dataRecebimento;
	}
	public void setDataRecebimento(DateFilter dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}
	
}