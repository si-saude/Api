package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.po.Usuario;

public class UsuarioBuilder extends GenericEntityBuilder<Usuario> {

	public static UsuarioBuilder newInstance(Usuario usuario) {
		return new UsuarioBuilder(usuario);
	}
	
	public static UsuarioBuilder newInstance(List<Usuario> usuarios) {
		return new UsuarioBuilder(usuarios);
	}
	
	private UsuarioBuilder(Usuario usuario) {
		super(usuario);
	}
	
	private UsuarioBuilder(List<Usuario> usuarios) {
		super(usuarios);
	}

	@Override
	protected Usuario clone(Usuario usuario) {
		if(usuario != null) {
			Usuario newUsuario = new Usuario();
			
			newUsuario.setId(usuario.getId());
			newUsuario.setChave(usuario.getChave());
			newUsuario.setSenha(usuario.getSenha());
			newUsuario.setVersion(usuario.getVersion());
			
			return newUsuario;
		}
		return null;
	}
	
	public UsuarioBuilder loadPerfis() {
		if(this.entity != null) {
			this.newEntity = loadPerfis(this.entity,this.newEntity);
		}else {
			for(Usuario usuario:this.entityList) {
				Usuario newUsuario = this.newEntityList.stream()
										.filter(u->u.getId() == usuario.getId())
										.iterator().next();
				newUsuario = loadPerfis(usuario,newUsuario);
			}
		}
		return this;
	}
	
	private Usuario loadPerfis(Usuario origem, Usuario destino) {
		if(origem.getPerfis() != null) {
			destino.setPerfis(PerfilBuilder
										.newInstance(origem.getPerfis())
										.getEntityList());
		}
		return destino;
	}
}
