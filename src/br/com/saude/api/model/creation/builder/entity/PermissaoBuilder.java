package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.Perfil;
import br.com.saude.api.model.entity.po.Permissao;

public class PermissaoBuilder extends GenericEntityBuilder<Permissao,GenericFilter> {

	public static PermissaoBuilder newInstance(Permissao permissao) {
		return new PermissaoBuilder(permissao);
	}
	
	public static PermissaoBuilder newInstance(List<Permissao> permissoes) {
		return new PermissaoBuilder(permissoes);
	}
	
	private PermissaoBuilder(Permissao permissao) {
		super(permissao);
	}
	
	private PermissaoBuilder(List<Permissao> permissoes) {
		super(permissoes);
	}

	@Override
	protected Permissao clone(Permissao permissao) {
		Permissao newPermissao = new Permissao();
		
		newPermissao.setId(permissao.getId());
		newPermissao.setFuncao(permissao.getFuncao());
		newPermissao.setLeitura(permissao.isLeitura());
		newPermissao.setEscrita(permissao.isEscrita());
		newPermissao.setVersion(permissao.getVersion());
		
		return newPermissao;
	}
	
	public PermissaoBuilder loadPerfil() {
		if(this.entity != null) {
			this.newEntity = loadPerfil(this.entity,this.newEntity);
		}else {
			for(Permissao permissao:this.entityList){
				Permissao newPermissao = this.newEntityList.stream()
						.filter(p->p.getId() == permissao.getId())
						.iterator().next();
				newPermissao = loadPerfil(permissao,newPermissao);
			}
		}
		return this;
	}
	
	public PermissaoBuilder loadPerfil(Perfil perfil) {
		if(this.entity != null) {
			this.newEntity.setPerfil(perfil);
		}else {
			this.newEntityList.forEach(p->p.setPerfil(perfil));
		}
		return this;
	}
	
	private Permissao loadPerfil(Permissao origem, Permissao destino) {
		if(origem.getPerfil() != null) {
			destino.setPerfil(PerfilBuilder.newInstance(origem.getPerfil()).getEntity());
		}
		return destino;
	}

	@Override
	public Permissao cloneFromFilter(GenericFilter filter) {
		return null;
	}
}
