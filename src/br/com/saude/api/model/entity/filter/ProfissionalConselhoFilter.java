package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericFilter;

public class ProfissionalConselhoFilter extends GenericFilter {

	private String conselho;
	private String uf;
	private String numero;
	private DateFilter vencimento;
	public String getConselho() {
		return conselho;
	}
	public void setConselho(String conselho) {
		this.conselho = conselho;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public DateFilter getVencimento() {
		return vencimento;
	}
	public void setVencimento(DateFilter vencimento) {
		this.vencimento = vencimento;
	}
}
