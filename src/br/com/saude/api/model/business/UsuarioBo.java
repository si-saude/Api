package br.com.saude.api.model.business;

import java.util.function.Function;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.business.validate.UsuarioValidator;
import br.com.saude.api.model.creation.builder.entity.UsuarioBuilder;
import br.com.saude.api.model.creation.builder.example.UsuarioExampleBuilder;
import br.com.saude.api.model.entity.filter.UsuarioFilter;
import br.com.saude.api.model.entity.po.Usuario;
import br.com.saude.api.model.persistence.UsuarioDao;

public class UsuarioBo extends GenericBo<Usuario, UsuarioFilter, UsuarioDao, UsuarioBuilder, 
											UsuarioExampleBuilder> {
	
	private static UsuarioBo instance;
	private Function<UsuarioBuilder,UsuarioBuilder> function;
	
	private UsuarioBo() {
		super();
		
		this.function = builder -> {
			return builder.loadPerfis();
		};
	}
	
	public static UsuarioBo getInstance() {
		if(instance == null)
			instance = new UsuarioBo();
		return instance;
	}
	
	public PagedList<Usuario> getListLoadPerfis(UsuarioFilter filter) throws Exception{
		return getList(getDao().getListLoadPerfis(getExampleBuilder(filter).example()), function);
	}
	
	public Usuario getByIdLoadPerfis(int id) throws Exception {
		return getByEntity(getDao().getByIdLoadPerfis(id), this.function);
	}
	
	public Usuario getFirstToAutenticacao(UsuarioFilter filter) throws Exception {
		Usuario usuario = getBuilder(new Usuario()).cloneFromFilter(filter);
		UsuarioValidator usuarioValidator = new UsuarioValidator();
		usuarioValidator.validate(usuario);
		usuario = getDao().getFirst(getExampleBuilder(filter).getExampleAutenticacao());
		return getBuilder(usuario).getEntity();
	}
}
