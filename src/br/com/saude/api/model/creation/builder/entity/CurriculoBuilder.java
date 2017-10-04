package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.CurriculoFilter;
import br.com.saude.api.model.entity.po.Curriculo;

public class CurriculoBuilder extends GenericEntityBuilder<Curriculo,CurriculoFilter> {

	public static CurriculoBuilder newInstance(Curriculo curriculo) {
		return new CurriculoBuilder(curriculo);
	}
	
	public static CurriculoBuilder newInstance(List<Curriculo> curriculos) {
		return new CurriculoBuilder(curriculos);
	}
	
	private CurriculoBuilder(List<Curriculo> curriculos) {
		super(curriculos);
	}

	private CurriculoBuilder(Curriculo curriculo) {
		super(curriculo);
	}

	@Override
	protected Curriculo clone(Curriculo curriculo) {
		Curriculo newCurriculo = new Curriculo();
		
		newCurriculo.setId(curriculo.getId());
		newCurriculo.setHistorico(curriculo.getHistorico());
		newCurriculo.setFormacao(curriculo.getFormacao());
		newCurriculo.setAtuacao(curriculo.getAtuacao());
		newCurriculo.setVersion(curriculo.getVersion());
		
		return newCurriculo;
	}
	
	public CurriculoBuilder loadCurriculoCursos() {
		if(this.entity != null) {
			this.newEntity = loadCurriculoCursos(this.entity,this.newEntity);
		}else {
			for(Curriculo curriculo:this.entityList) {
				Curriculo newCurriculo = this.newEntityList.stream()
						.filter(e->e.getId() == curriculo.getId())
						.iterator().next();
				newCurriculo = loadCurriculoCursos(curriculo,newCurriculo);
			}
		}
		return this;
	}
	
	private Curriculo loadCurriculoCursos(Curriculo origem,Curriculo destino) {
		if(origem.getCurriculoCursos() != null) {
			destino.setCurriculoCursos(CurriculoCursoBuilder.newInstance(origem.getCurriculoCursos()).loadCurso().getEntityList());
		}
		
		return destino;
	}

	@Override
	public Curriculo cloneFromFilter(CurriculoFilter filter) {
		return null;
	}

}
