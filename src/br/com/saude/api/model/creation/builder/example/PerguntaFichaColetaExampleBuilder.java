package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.PerguntaFichaColetaFilter;
import br.com.saude.api.model.entity.po.PerguntaFichaColeta;

public class PerguntaFichaColetaExampleBuilder extends GenericExampleBuilder<PerguntaFichaColeta,PerguntaFichaColetaFilter> {
	
	public static PerguntaFichaColetaExampleBuilder newInstance(PerguntaFichaColetaFilter filter) {
		return new PerguntaFichaColetaExampleBuilder(filter);
	}
	
	private PerguntaFichaColetaExampleBuilder(PerguntaFichaColetaFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addCodigo();
		addGrupo();
		addInativo();
		addPath();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
	}
	
	private void addCodigo() {
		if(this.filter.getCodigo()!= null)
			this.entity.setCodigo(Helper.filterLike(this.filter.getCodigo()));
	}
	
	private void addPath() {
		if(this.filter.getPath()!= null)
			this.entity.setPath(Helper.filterLike(this.filter.getPath()));
	}
	
	private void addGrupo() {
		if(this.filter.getGrupo()!= null)
			this.entity.setGrupo(Helper.filterLike(this.filter.getGrupo()));
	}
	
	private void addInativo() {
		this.entity.setInativo(this.addBoolean("inativo", this.filter.getInativo()));
	}
	
}
