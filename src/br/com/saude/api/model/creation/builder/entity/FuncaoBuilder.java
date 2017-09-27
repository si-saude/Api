package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.FuncaoFilter;
import br.com.saude.api.model.entity.po.Funcao;

public class FuncaoBuilder extends GenericEntityBuilder<Funcao,FuncaoFilter> {

	public static FuncaoBuilder newInstance(Funcao funcao) {
		return new FuncaoBuilder(funcao);
	}
	
	public static FuncaoBuilder newInstance(List<Funcao> funcoes) {
		return new FuncaoBuilder(funcoes);
	}
	
	private FuncaoBuilder(List<Funcao> funcoes) {
		super(funcoes);
	}

	private FuncaoBuilder(Funcao funcao) {
		super(funcao);
	}

	@Override
	protected Funcao clone(Funcao funcao) {
		Funcao newFuncao = new Funcao();
		
		newFuncao.setId(funcao.getId());
		newFuncao.setNome(funcao.getNome());
		newFuncao.setVersion(funcao.getVersion());
		
		return newFuncao;
	}
	
	public FuncaoBuilder loadCursos() {
		if(this.entity != null) {
			this.entity = loadCursos(this.entity,this.newEntity);
		}else {
			for(Funcao funcao:this.entityList) {
				Funcao newFuncao = this.newEntityList.stream()
						.filter(e->e.getId() == funcao.getId())
						.iterator().next();
				newFuncao = loadCursos(funcao,newFuncao);
			}
		}
		return this;
	}
	
	private Funcao loadCursos(Funcao origem,Funcao destino) {
		if(origem.getCursos() != null) {
			destino.setCursos(CursoBuilder.newInstance(origem.getCursos()).getEntityList());
		}
		return destino;
	}

	@Override
	public Funcao cloneFromFilter(FuncaoFilter filter) {
		return null;
	}

}
