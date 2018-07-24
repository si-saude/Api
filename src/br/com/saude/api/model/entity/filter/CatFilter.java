package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class CatFilter extends GenericFilter {

	private String numero;
	private String nome;
	private String cpf;
	private String sexo;
	private String regime;
	private String gravidade;
	private String partesCorpo;
	private String tipoAcidente;
	private String tipoCat;
	private BooleanFilter afastamento;
	private BooleanFilter catSd2000;
	private BooleanFilter comunicavelSus;
	private BooleanFilter contratado;
	private BooleanFilter ferimentoGraveConformeAnp;
	private BooleanFilter registroSd2000;
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getRegime() {
		return regime;
	}

	public void setRegime(String regime) {
		this.regime = regime;
	}
	
	public String getGravidade() {
		return gravidade;
	}

	public void setGravidade(String gravidade) {
		this.gravidade = gravidade;
	}
	
	public String getPartesCorpo() {
		return partesCorpo;
	}

	public void setPartesCorpo(String partesCorpo) {
		this.partesCorpo = partesCorpo;
	}
	
	public String getTipoAcidente() {
		return tipoAcidente;
	}

	public void setTipoAcidente(String tipoAcidente) {
		this.tipoAcidente = tipoAcidente;
	}
	
	public String getTipoCat() {
		return tipoCat;
	}

	public void setTipoCat(String tipoCat) {
		this.tipoCat = tipoCat;
	}

	public BooleanFilter getAfastamento() {
		return afastamento;
	}

	public void setAfastamento(BooleanFilter afastamento) {
		this.afastamento = afastamento;
	}

	public BooleanFilter getCatSd2000() {
		return catSd2000;
	}

	public void setCatSd2000(BooleanFilter catSd2000) {
		this.catSd2000 = catSd2000;
	}

	public BooleanFilter getComunicavelSus() {
		return comunicavelSus;
	}

	public void setComunicavelSus(BooleanFilter comunicavelSus) {
		this.comunicavelSus = comunicavelSus;
	}

	public BooleanFilter getContratado() {
		return contratado;
	}

	public void setContratado(BooleanFilter contratado) {
		this.contratado = contratado;
	}

	public BooleanFilter getFerimentoGraveConformeAnp() {
		return ferimentoGraveConformeAnp;
	}

	public void setFerimentoGraveConformeAnp(BooleanFilter ferimentoGraveConformeAnp) {
		this.ferimentoGraveConformeAnp = ferimentoGraveConformeAnp;
	}

	public BooleanFilter getRegistroSd2000() {
		return registroSd2000;
	}

	public void setRegistroSd2000(BooleanFilter registroSd2000) {
		this.registroSd2000 = registroSd2000;
	}
	
}