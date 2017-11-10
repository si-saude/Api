package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericFilter;

public class PessoaFilter extends GenericFilter{
	private String nome;
	private String cpf;
	private DateFilter dataNascimento;
	private String rg;
	private String sexo;
	private EmpregadoFilter empregado;
	private EnderecoFilter endereco;

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
	public EmpregadoFilter getEmpregado() {
		return empregado;
	}
	public void setEmpregado(EmpregadoFilter empregado) {
		this.empregado = empregado;
	}
	public EnderecoFilter getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoFilter endereco) {
		this.endereco = endereco;
	}
	
}
