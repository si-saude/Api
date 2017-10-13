package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.GheFilter;
import br.com.saude.api.model.entity.po.Ghe;

public class GheBuilder extends GenericEntityBuilder<Ghe, GheFilter> {

	public static GheBuilder newInstance(Ghe ghe) {
		return new GheBuilder(ghe);
	}
	
	public static GheBuilder newInstance(List<Ghe> ghes) {
		return new GheBuilder(ghes);
	}
	
	private GheBuilder(List<Ghe> ghes) {
		super(ghes);
	}

	private GheBuilder(Ghe ghe) {
		super(ghe);
	}

	@Override
	protected Ghe clone(Ghe ghe) {
		Ghe newGhe = new Ghe();
		
		newGhe.setId(ghe.getId());
		newGhe.setCodigo(ghe.getCodigo());
		newGhe.setDataCriacao(ghe.getDataCriacao());
		newGhe.setDataDesativacao(ghe.getDataDesativacao());
		newGhe.setDescricao(ghe.getDescricao());
		newGhe.setDescricaoAmbiente(ghe.getDescricaoAmbiente());
		newGhe.setDescricaoTarefas(ghe.getDescricaoTarefas());
		newGhe.setDuracaoJornada(ghe.getDuracaoJornada());
		newGhe.setNome(ghe.getNome());
		newGhe.setVersion(ghe.getVersion());
		
		return newGhe;
	}

	@Override
	public Ghe cloneFromFilter(GheFilter filter) {
		return null;
	}

}
