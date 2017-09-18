package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.ExameFilter;
import br.com.saude.api.model.entity.po.Exame;

public class ExameBuilder extends GenericEntityBuilder<Exame,ExameFilter> {
	
	public static ExameBuilder newInstance(Exame exame) {
		return new ExameBuilder(exame);
	}
	
	public static ExameBuilder newInstance(List<Exame> exames) {
		return new ExameBuilder(exames);
	}
	
	private ExameBuilder(Exame exame) {
		super(exame);
	}
	
	private ExameBuilder(List<Exame> exames) {
		super(exames);
	}

	@Override
	protected Exame clone(Exame exame) {
		Exame newExame = new Exame();
		
		newExame.setId(exame.getId());
		newExame.setVersion(exame.getVersion());
		newExame.setCodigo(exame.getCodigo());
		newExame.setDescricao(exame.getDescricao());

		return newExame;
	}
	
	public ExameBuilder loadEmpregado() {
		if(this.entity != null) {
			this.newEntity = loadEmpregado(this.entity,this.newEntity);
		}else {
			for(Exame exame:this.entityList) {
				Exame newExame = this.newEntityList.stream()
										.filter(e->e.getId() == exame.getId())
										.iterator().next();
				newExame = loadEmpregado(exame,newExame);
			}				
		}
		return this;
	}
	
	private Exame loadEmpregado(Exame origem, Exame destino) {
		if(origem.getEmpregado() != null) {
			destino
				.setEmpregado(EmpregadoBuilder.newInstance(origem.getEmpregado()).getEntity());
		}
		return destino;
	}

	@Override
	public Exame cloneFromFilter(ExameFilter filter) {
		return null;
	}
}
