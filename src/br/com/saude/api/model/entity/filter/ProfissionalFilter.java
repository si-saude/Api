package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericFilter;

public class ProfissionalFilter extends GenericFilter {
	private String nome;
	private DateFilter dataNascimento;
	private String matricula;
	private String chave;
	private String ramal;
	private LocalizacaoFilter localizacao;
	private EquipeFilter equipe;
	private FuncaoFilter funcao;
	private CurriculoFilter curriculo;
	private ProfissionalConselhoFilter profissionalConselho;
	private String mi;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public DateFilter getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(DateFilter dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}
	public String getRamal() {
		return ramal;
	}
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}
	public LocalizacaoFilter getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(LocalizacaoFilter localizacao) {
		this.localizacao = localizacao;
	}
	public EquipeFilter getEquipe() {
		return equipe;
	}
	public void setEquipe(EquipeFilter equipe) {
		this.equipe = equipe;
	}
	public String getMi() {
		return mi;
	}
	public void setMi(String mi) {
		this.mi = mi;
	}
	public FuncaoFilter getFuncao() {
		return funcao;
	}
	public void setFuncao(FuncaoFilter funcao) {
		this.funcao = funcao;
	}
	public CurriculoFilter getCurriculo() {
		return curriculo;
	}
	public void setCurriculo(CurriculoFilter curriculo) {
		this.curriculo = curriculo;
	}
	public ProfissionalConselhoFilter getProfissionalConselho() {
		return profissionalConselho;
	}
	public void setProfissionalConselho(ProfissionalConselhoFilter profissionalConselho) {
		this.profissionalConselho = profissionalConselho;
	}
}
