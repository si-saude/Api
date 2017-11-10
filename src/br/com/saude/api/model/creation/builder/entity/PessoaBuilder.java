package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.PessoaFilter;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.Pessoa;

public class PessoaBuilder extends GenericEntityBuilder<Pessoa,PessoaFilter>{
	private Function<Map<String,Pessoa>,Pessoa> loadTelefones;
	private Function<Map<String,Pessoa>,Pessoa> loadEndereco;

	public static PessoaBuilder newInstance(Pessoa pessoa) {
		return new PessoaBuilder(pessoa);
	}
	
	public static PessoaBuilder newInstance(List<Pessoa> pessoa) {
		return new PessoaBuilder(pessoa);
	}
	
	protected PessoaBuilder(Pessoa pessoa) {
		super(pessoa);
	}

	private PessoaBuilder(List<Pessoa> pessoa) {
		super(pessoa);
	}
	
	@Override
	protected void initializeFunctions() {
		this.loadTelefones = pessoas -> {
			if (pessoas.get("origem").getTelefones() != null) {
				pessoas.get("destino").setTelefones(
						TelefoneBuilder.newInstance(
								pessoas.get("origem").getTelefones()).getEntityList());
			}
			return pessoas.get("destino");
		};
		
		this.loadEndereco = pessoas -> {
			if (pessoas.get("origem").getEndereco() != null) {
				pessoas.get("destino").setEndereco(EnderecoBuilder.newInstance(pessoas.get("origem").getEndereco()).loadCidade().getEntity());
			}
			return pessoas.get("destino");
		};
	}
	
	@Override
	protected Pessoa clone(Pessoa pessoa) {
		Pessoa newPessoa = new Pessoa();
		
		newPessoa.setId(pessoa.getId());
		newPessoa.setVersion(pessoa.getVersion());
		newPessoa.setNome(pessoa.getNome());
		newPessoa.setCpf(pessoa.getCpf());
		newPessoa.setDataNascimento(pessoa.getDataNascimento());
		newPessoa.setRg(pessoa.getRg());
		newPessoa.setSexo(pessoa.getSexo());
		
		return newPessoa;
	}
	
	public PessoaBuilder loadTelefones() {
		return (PessoaBuilder) this.loadProperty(this.loadTelefones);
	}
	
	public PessoaBuilder loadEndereco() {
			return (PessoaBuilder) this.loadProperty(this.loadEndereco);
	}
	
	@Override
	public Pessoa cloneFromFilter(PessoaFilter filter) {
		return null;
	}
}
