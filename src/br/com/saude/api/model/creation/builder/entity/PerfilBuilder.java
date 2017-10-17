package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.PerfilFilter;
import br.com.saude.api.model.entity.po.Perfil;

public class PerfilBuilder extends GenericEntityBuilder<Perfil,PerfilFilter> {

	private Function<Map<String,Perfil>,Perfil> loadPermissoes;
	
	public static PerfilBuilder newInstance(Perfil perfil) {
		return new PerfilBuilder(perfil);
	}
	
	public static PerfilBuilder newInstance(List<Perfil> perfis) {
		return new PerfilBuilder(perfis);
	}
	
	private PerfilBuilder(Perfil perfil) {
		super(perfil);
	}

	private PerfilBuilder(List<Perfil> perfis) {
		super(perfis);
	}

	@Override
	protected Perfil clone(Perfil perfil) {
		Perfil newPerfil = new Perfil();
		
		newPerfil.setId(perfil.getId());
		newPerfil.setTitulo(perfil.getTitulo());
		newPerfil.setVersion(perfil.getVersion());
		
		return newPerfil;
	}
	
	@Override
	protected void initializeFunctions() {
		this.loadPermissoes = perfis -> {
			if(perfis.get("origem").getPermissoes() != null) {
				perfis.get("destino").setPermissoes(PermissaoBuilder
											.newInstance(perfis.get("origem").getPermissoes())
											.getEntityList());	
			}
			return perfis.get("destino");
		};
	}
	
	public PerfilBuilder loadPermissoes() {
		return (PerfilBuilder) this.loadProperty(this.loadPermissoes);
	}

	@Override
	public Perfil cloneFromFilter(PerfilFilter filter) {
		return null;
	}
}
