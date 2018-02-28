package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.EixoFilter;
import br.com.saude.api.model.entity.po.Eixo;

public class EixoBuilder extends GenericEntityBuilder<Eixo,EixoFilter> {
	
	public static EixoBuilder newInstance(Eixo eixo) {
		return new EixoBuilder(eixo);
	}
	
	public static EixoBuilder newInstance(List<Eixo> eixos) {
		return new EixoBuilder(eixos);
	}
	
	private EixoBuilder(List<Eixo> eixos) {
		super(eixos);
	}

	private EixoBuilder(Eixo eixo) {
		super(eixo);
	}

	@Override
	protected void initializeFunctions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Eixo clone(Eixo eixo) {
		Eixo cloneEixo = new Eixo();
		
		cloneEixo.setId(eixo.getId());
		cloneEixo.setVersion(eixo.getVersion());
		cloneEixo.setTitulo(eixo.getTitulo());
		if ( eixo.getEquipe() != null )
			cloneEixo.setEquipe(EquipeBuilder.newInstance(eixo.getEquipe()).getEntity());
		
		return cloneEixo;
	}

	@Override
	public Eixo cloneFromFilter(EixoFilter filter) {
		return null;
	}
}
