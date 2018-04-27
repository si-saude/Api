package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericFilter;

public class EmpregadoFilter extends GenericFilter {
	private String chave;
	private String matricula;
	private String estadoCivil;
	private String escolaridade;
	private String vinculo;
	private String ramal;
	private String status;
	private String pis;
	private DateFilter dataAdmissao;
	private PessoaFilter pessoa;
	private CargoFilter cargo;
	private FuncaoFilter funcao;
	private RegimeFilter regime;
	private GerenciaFilter gerencia;
	private BaseFilter base;
	private GheFilter ghe;
	private GheeFilter ghee;
	
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
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getEscolaridade() {
		return escolaridade;
	}
	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}
	public String getVinculo() {
		return vinculo;
	}
	public void setVinculo(String vinculo) {
		this.vinculo = vinculo;
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
	public PessoaFilter getPessoa() {
		return pessoa;
	}
	public void setPessoa(PessoaFilter pessoa) {
		this.pessoa = pessoa;
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
	public String getPis() {
		return pis;
	}
	public void setPis(String pis) {
		this.pis = pis;
	}
	public DateFilter getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(DateFilter dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	
}
