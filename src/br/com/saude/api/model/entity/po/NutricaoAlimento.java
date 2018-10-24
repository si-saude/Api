package br.com.saude.api.model.entity.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.Size;

@Entity
public class NutricaoAlimento {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Size(max = 256, message="Tamanho máximo para o Alimento do Alimento: 256")
	private String alimento;
	
	@OneToMany(mappedBy="nutricaoAlimento", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<NutricaoAlimentoMedidaAlimentar> nutricaoAlimentoMedidaAlimentares;
	
	@Size(max = 64, message="Tamanho máximo para o Tipo do Alimento: 64")
	private String tipo;
	private double padrao;
	private double energia;
	private double proteina;
	private double lipideos;
	private double saturada;
	private double monoinsaturada;
	private double poliinsaturada;
	private double omega6;
	private double omega3;
	private double colesterol;
	private double cho;
	private double fibra;
	private double calcio;
	private double mg;
	private double mn;
	private double p;
	private double ferro;
	private double sodio;
	private double potassio;
	private double cobre;
	private double zinco;
	private double retinol;
	private double tiamina;
	private double riboflavina;
	private double piridoxina;
	private double niacina;
	private double vitc;
	private double vitd;
	private double vite;
	private boolean inativo;
	@Version
	private long version;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlimento() {
		return alimento;
	}
	public void setAlimento(String alimento) {
		this.alimento = alimento;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getPadrao() {
		return padrao;
	}
	public void setPadrao(double padrao) {
		this.padrao = padrao;
	}
	public double getEnergia() {
		return energia;
	}
	public void setEnergia(double energia) {
		this.energia = energia;
	}
	public double getProteina() {
		return proteina;
	}
	public void setProteina(double proteina) {
		this.proteina = proteina;
	}
	public double getLipideos() {
		return lipideos;
	}
	public void setLipideos(double lipideos) {
		this.lipideos = lipideos;
	}
	public double getSaturada() {
		return saturada;
	}
	public void setSaturada(double saturada) {
		this.saturada = saturada;
	}
	public double getMonoinsaturada() {
		return monoinsaturada;
	}
	public void setMonoinsaturada(double monoinsaturada) {
		this.monoinsaturada = monoinsaturada;
	}
	public double getPoliinsaturada() {
		return poliinsaturada;
	}
	public void setPoliinsaturada(double poliinsaturada) {
		this.poliinsaturada = poliinsaturada;
	}
	public double getOmega6() {
		return omega6;
	}
	public void setOmega6(double omega6) {
		this.omega6 = omega6;
	}
	public double getOmega3() {
		return omega3;
	}
	public void setOmega3(double omega3) {
		this.omega3 = omega3;
	}
	public double getColesterol() {
		return colesterol;
	}
	public void setColesterol(double colesterol) {
		this.colesterol = colesterol;
	}
	public double getCho() {
		return cho;
	}
	public void setCho(double cho) {
		this.cho = cho;
	}
	public double getFibra() {
		return fibra;
	}
	public void setFibra(double fibra) {
		this.fibra = fibra;
	}
	public double getCalcio() {
		return calcio;
	}
	public void setCalcio(double calcio) {
		this.calcio = calcio;
	}
	public double getMg() {
		return mg;
	}
	public void setMg(double mg) {
		this.mg = mg;
	}
	public double getMn() {
		return mn;
	}
	public void setMn(double mn) {
		this.mn = mn;
	}
	public double getP() {
		return p;
	}
	public void setP(double p) {
		this.p = p;
	}
	public double getFerro() {
		return ferro;
	}
	public void setFerro(double ferro) {
		this.ferro = ferro;
	}
	public double getSodio() {
		return sodio;
	}
	public void setSodio(double sodio) {
		this.sodio = sodio;
	}
	public double getPotassio() {
		return potassio;
	}
	public void setPotassio(double potassio) {
		this.potassio = potassio;
	}
	public double getCobre() {
		return cobre;
	}
	public void setCobre(double cobre) {
		this.cobre = cobre;
	}
	public double getZinco() {
		return zinco;
	}
	public void setZinco(double zinco) {
		this.zinco = zinco;
	}
	public double getRetinol() {
		return retinol;
	}
	public void setRetinol(double retinol) {
		this.retinol = retinol;
	}
	public double getTiamina() {
		return tiamina;
	}
	public void setTiamina(double tiamina) {
		this.tiamina = tiamina;
	}
	public double getRiboflavina() {
		return riboflavina;
	}
	public void setRiboflavina(double riboflavina) {
		this.riboflavina = riboflavina;
	}
	public double getPiridoxina() {
		return piridoxina;
	}
	public void setPiridoxina(double piridoxina) {
		this.piridoxina = piridoxina;
	}
	public double getNiacina() {
		return niacina;
	}
	public void setNiacina(double niacina) {
		this.niacina = niacina;
	}
	public double getVitc() {
		return vitc;
	}
	public void setVitc(double vitc) {
		this.vitc = vitc;
	}
	public double getVitd() {
		return vitd;
	}
	public void setVitd(double vitd) {
		this.vitd = vitd;
	}
	public double getVite() {
		return vite;
	}
	public void setVite(double vite) {
		this.vite = vite;
	}
	public void setVite(float vite) {
		this.vite = vite;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public boolean isInativo() {
		return inativo;
	}
	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}
	public List<NutricaoAlimentoMedidaAlimentar> getNutricaoAlimentoMedidaAlimentares() {
		return nutricaoAlimentoMedidaAlimentares;
	}
	public void setNutricaoAlimentoMedidaAlimentares(
			List<NutricaoAlimentoMedidaAlimentar> nutricaoAlimentoMedidaAlimentares) {
		this.nutricaoAlimentoMedidaAlimentares = nutricaoAlimentoMedidaAlimentares;
	}
	
}
