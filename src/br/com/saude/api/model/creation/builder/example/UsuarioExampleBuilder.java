package br.com.saude.api.model.creation.builder.example;

import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.UsuarioFilter;
import br.com.saude.api.model.entity.po.Usuario;

public class UsuarioExampleBuilder extends GenericExampleBuilder<Usuario,UsuarioFilter> {

	public static UsuarioExampleBuilder newInstance(UsuarioFilter filter) {
		return new UsuarioExampleBuilder(filter);
	}
	
	private UsuarioExampleBuilder(UsuarioFilter filter) {
		super(filter);
	}
	
	private void addChave() {
		if(this.filter.getChave()!= null)
			this.entity.setChave(Helper.filterLike(this.filter.getChave()));
	}
	
	private void addChaveEq() {
		if(this.filter.getChave()!= null)
			this.entity.setChave(this.filter.getChave());
	}
	
	private void addSenhaEq() {
		if(this.filter.getSenha()!= null)
			this.entity.setSenha(this.filter.getSenha());
	}
	
	private void addPessoa() throws InstantiationException, IllegalAccessException {
		if(this.filter.getPessoa()!=null) {
			CriteriaExample criteriaExample = PessoaExampleBuilder
					.newInstance(this.filter.getPessoa()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("pessoa", criteriaExample, JoinType.INNER_JOIN));
		}
	}

	public List<Criterion> getExampleAutenticacao() throws InstantiationException, IllegalAccessException {
		if(this.filter != null) {
			initialize();
			addChaveEq();
			addSenhaEq();
			this.criterions.add(Example.create(this.entity));
			return this.criterions;
		}
		else
			return null;
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addChave();
		addPessoa();
	}

	@Override
	protected void createExampleSelectList() {
		
	}
}
