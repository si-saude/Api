package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.FonteGeradora;

public class AprhoItemFilter extends GenericFilter{

	private AprhoFilter aprho;
	private AgenteRiscoFilter agenteRisco;
	private PossivelDanoSaudeFilter possivelDanoSaude;
	private CategoriaRiscoFilter categoriaRisco;	
	private String medidaControle;	
	private FonteGeradora fonteGeradora;
	private String atividade;
	private String local;
	private String frequencia;
	private String duracao;
	private String avaliacaoEficacia;	
	
	public AprhoFilter getAprho() {
		return aprho;
	}
	public void setAprho(AprhoFilter aprho) {
		this.aprho = aprho;
	}
	public AgenteRiscoFilter getAgenteRisco() {
		return agenteRisco;
	}
	public void setAgenteRisco(AgenteRiscoFilter agenteRisco) {
		this.agenteRisco = agenteRisco;
	}
	public PossivelDanoSaudeFilter getPossivelDanoSaude() {
		return possivelDanoSaude;
	}
	public void setPossivelDanoSaude(PossivelDanoSaudeFilter possivelDanoSaude) {
		this.possivelDanoSaude = possivelDanoSaude;
	}
	public CategoriaRiscoFilter getCategoriaRisco() {
		return categoriaRisco;
	}
	public void setCategoriaRisco(CategoriaRiscoFilter categoriaRisco) {
		this.categoriaRisco = categoriaRisco;
	}
	public String getMedidaControle() {
		return medidaControle;
	}
	public void setMedidaControle(String medidaControle) {
		this.medidaControle = medidaControle;
	}
	public String getAtividade() {
		return atividade;
	}
	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getFrequencia() {
		return frequencia;
	}
	public void setFrequencia(String frequencia) {
		this.frequencia = frequencia;
	}
	public String getDuracao() {
		return duracao;
	}
	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}
	public FonteGeradora getFonteGeradora() {
		return fonteGeradora;
	}
	public void setFonteGeradora(FonteGeradora fonteGeradora) {
		this.fonteGeradora = fonteGeradora;
	}
	public String getAvaliacaoEficacia() {
		return avaliacaoEficacia;
	}
	public void setAvaliacaoEficacia(String avaliacaoEficacia) {
		this.avaliacaoEficacia = avaliacaoEficacia;
	}
	
	
	
}
