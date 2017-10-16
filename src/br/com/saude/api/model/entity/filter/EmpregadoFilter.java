package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericFilter;

public class EmpregadoFilter extends GenericFilter {
	private String nome;
	private String cpf;
	private DateFilter dataNascimento;
	private String chave;
	private String matricula;
	private String rg;
	private String sexo;
	private String ramal;
	private String status;
	private CargoFilter cargo;
	private FuncaoFilter funcao;
	private RegimeFilter regime;
	private GerenciaFilter gerencia;
	private BaseFilter base;
	private GheFilter ghe;
	private GheeFilter ghee;
	
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
	public DateFilter getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(DateFilter dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getRamal() {
		return ramal;
	}
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public CargoFilter getCargo() {
		return cargo;
	}
	public void setCargo(CargoFilter cargo) {
		this.cargo = cargo;
	}
	public FuncaoFilter getFuncao() {
		return funcao;
	}
	public void setFuncao(FuncaoFilter funcao) {
		this.funcao = funcao;
	}
	public RegimeFilter getRegime() {
		return regime;
	}
	public void setRegime(RegimeFilter regime) {
		this.regime = regime;
	}
	public GerenciaFilter getGerencia() {
		return gerencia;
	}
	public void setGerencia(GerenciaFilter gerencia) {
		this.gerencia = gerencia;
	}
	public BaseFilter getBase() {
		return base;
	}
	public void setBase(BaseFilter base) {
		this.base = base;
	}
	public GheFilter getGhe() {
		return ghe;
	}
	public void setGhe(GheFilter ghe) {
		this.ghe = ghe;
	}
	public GheeFilter getGhee() {
		return ghee;
	}
	public void setGhee(GheeFilter ghee) {
		this.ghee = ghee;
	}
}
