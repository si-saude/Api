package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.GerenciaFilter;
import br.com.saude.api.model.entity.po.Gerencia;

public class GerenciaBuilder extends GenericEntityBuilder<Gerencia,GerenciaFilter> {

	public static GerenciaBuilder newInstance(Gerencia gerencia) {
		return new GerenciaBuilder(gerencia);
	}
	
	public static GerenciaBuilder newInstance(List<Gerencia> gerencias) {
		return new GerenciaBuilder(gerencias);
	}
	
	private GerenciaBuilder(List<Gerencia> gerencias) {
		super(gerencias);
	}

	private GerenciaBuilder(Gerencia gerencia) {
		super(gerencia);
	}

	@Override
	protected Gerencia clone(Gerencia gerencia) {
		Gerencia newGerencia = new Gerencia();
		
		newGerencia.setId(gerencia.getId());
		newGerencia.setCodigo(gerencia.getCodigo());
		newGerencia.setVersion(gerencia.getVersion());
		newGerencia.setDescricao(gerencia.getDescricao());
		
		if(gerencia.getGerencia() != null)
			newGerencia.setGerencia(new GerenciaBuilder(gerencia.getGerencia()).getEntity());
		
		return newGerencia;
	}

	@Override
	public Gerencia cloneFromFilter(GerenciaFilter filter) {
		return null;
	}

}
