package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.UsuarioFilter;
import br.com.saude.api.model.entity.po.Usuario;

public class UsuarioBuilder extends GenericEntityBuilder<Usuario,UsuarioFilter> {

	private Function<Map<String,Usuario>,Usuario> loadPerfis;
	private Function<Map<String,Usuario>,Usuario> loadPermissoes;
	private Function<Map<String,Usuario>,Usuario> loadPessoa;
	
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
	protected void initializeFunctions() {
		this.loadPessoa = usuarios -> {
			if ( usuarios.get("origem").getPessoa() != null ) {
				usuarios.get("destino").setPessoa(PessoaBuilder
						.newInstance(usuarios.get("origem").getPessoa())
						.getEntity());
			}
			return usuarios.get("destino");
		};
		
		this.loadPerfis = usuarios -> {
			if(usuarios.get("origem").getPerfis() != null) {
				usuarios.get("destino").setPerfis(PerfilBuilder
											.newInstance(usuarios.get("origem").getPerfis())
											.getEntityList());
			}
			return usuarios.get("destino");
		};
		
		this.loadPermissoes = usuarios -> {
			if ( usuarios.get("origem").getPerfis() != null ) {
				usuarios.get("destino").setPerfis(PerfilBuilder
						.newInstance(usuarios.get("origem").getPerfis())
						.loadPermissoes()
						.getEntityList());
			}
			return usuarios.get("destino");
		};
		
	}

	@Override
	protected Usuario clone(Usuario usuario) {
		if(usuario != null) {
			Usuario newUsuario = new Usuario();
			
			newUsuario.setId(usuario.getId());
			newUsuario.setChave(usuario.getChave());
			newUsuario.setSenha(usuario.getSenha());
			newUsuario.setGestorCss(usuario.isGestorCss());
			newUsuario.setVersion(usuario.getVersion());
			
			return newUsuario;
		}
		return null;
	}
	
	public UsuarioBuilder loadPerfis() {
		return (UsuarioBuilder) this.loadProperty(this.loadPerfis);
	}
	
	public UsuarioBuilder loadPermissoes() {
		return (UsuarioBuilder) this.loadProperty(this.loadPermissoes);
	}
	
	public UsuarioBuilder loadPessoa() {
		return (UsuarioBuilder) this.loadProperty(this.loadPessoa);
	}

	@Override
	public Usuario cloneFromFilter(UsuarioFilter filter) {
		Usuario usuario = new Usuario();
		usuario.setChave(filter.getChave());
		usuario.setSenha(filter.getSenha());
		return usuario;
	}
}
