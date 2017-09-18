package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.PerfilFilter;
import br.com.saude.api.model.entity.po.Perfil;

public class PerfilBuilder extends GenericEntityBuilder<Perfil,PerfilFilter> {

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
	
	public PerfilBuilder loadPermissoes() {
		if(this.entity != null) {
			this.newEntity = loadPermissoes(this.entity,this.newEntity);
		}else {
			for(Perfil perfil:this.entityList){
				Perfil newPerfil = this.newEntityList.stream()
						.filter(p->p.getId() == perfil.getId())
						.iterator().next();
				newPerfil = loadPermissoes(perfil,newPerfil);
			}
		}
		
		return this;
	}
	
	public PerfilBuilder loadPermissoesSetPerfil() {
		if(this.entity != null) {
			this.newEntity = loadPermissoesSetPerfil(this.entity,this.newEntity);
		}else {
			for(Perfil perfil:this.entityList){
				Perfil newPerfil = this.newEntityList.stream()
						.filter(p->p.getId() == perfil.getId())
						.iterator().next();
				newPerfil = loadPermissoesSetPerfil(perfil,newPerfil);
			}
		}
		
		return this;
	}
	
	private Perfil loadPermissoes(Perfil origem, Perfil destino) {
		if(origem.getPermissoes() != null) {
			destino.setPermissoes(PermissaoBuilder
										.newInstance(origem.getPermissoes())
										.getEntityList());	
		}
		return destino;
	}
	
	private Perfil loadPermissoesSetPerfil(Perfil origem, Perfil destino) {
		if(origem.getPermissoes() != null) {
			destino.setPermissoes(PermissaoBuilder
										.newInstance(origem.getPermissoes())
										.loadPerfil(destino)
										.getEntityList());	
		}
		return destino;
	}

	@Override
	public Perfil cloneFromFilter(PerfilFilter filter) {
		return null;
	}
}
