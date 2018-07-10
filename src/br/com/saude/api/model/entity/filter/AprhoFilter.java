package br.com.saude.api.model.entity.filter;


import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.Profissional;

public class AprhoFilter extends GenericFilter {

	private String empresa;
	
	private DateFilter data;
	private GheFilter ghe;
	private String revisao;
	private DateFilter dataRevisao;
	private Profissional aprovador;

	public String getRevisao() {
		return revisao;
	}

	public void setRevisao(String revisao)  
	{
		this.revisao = revisao;
	}

	public DateFilter getDataRevisao() {
		return dataRevisao;
	}

	public void setDataRevisao(DateFilter dataRevisao) {
		this.dataRevisao = dataRevisao;
	}

	public Profissional getAprovador() {
		return aprovador;
	}

	public void setAprovador(Profissional aprovador) {
		this.aprovador = aprovador;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public DateFilter getData() {
		return data;
	}
	public void setData(DateFilter data) {
		this.data = data;
	}
	
	public GheFilter getGhe() {
		return ghe;
	}
	public void setGhe(GheFilter ghe) {
		this.ghe = ghe;
	}
}
