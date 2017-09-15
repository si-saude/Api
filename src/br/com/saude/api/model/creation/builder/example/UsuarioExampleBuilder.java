package br.com.saude.api.model.creation.builder.example;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

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

	@Override
	public List<Criterion> getExample() {
		if(this.filter != null) {
			this.criterions = new ArrayList<Criterion>();
			this.entity = new Usuario();
			addChave();
			this.criterions.add(Example.create(this.entity).enableLike().ignoreCase());
			return this.criterions;
		}
		else
			return null;
	}
	
	public List<Criterion> getExampleAutenticacao() {
		if(this.filter != null) {
			this.criterions = new ArrayList<Criterion>();
			this.entity = new Usuario();
			addChaveEq();
			addSenhaEq();
			this.criterions.add(Example.create(this.entity));
			return this.criterions;
		}
		else
			return null;
	}

}
