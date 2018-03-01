package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.PerguntaFichaColetaBuilder;
import br.com.saude.api.model.creation.builder.example.PerguntaFichaColetaExampleBuilder;
import br.com.saude.api.model.entity.filter.PerguntaFichaColetaFilter;
import br.com.saude.api.model.entity.po.PerguntaFichaColeta;
import br.com.saude.api.model.persistence.PerguntaFichaColetaDao;

public class PerguntaFichaColetaBo extends GenericBo<PerguntaFichaColeta, PerguntaFichaColetaFilter, PerguntaFichaColetaDao, 
PerguntaFichaColetaBuilder, PerguntaFichaColetaExampleBuilder>{
	
	private static PerguntaFichaColetaBo instance;
	
	private PerguntaFichaColetaBo() {
		super();
	}
	
	public static PerguntaFichaColetaBo getInstance() {
		if(instance == null)
			instance = new PerguntaFichaColetaBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadItens();
		};
	}
	
	@Override
	public PerguntaFichaColeta getById(Object id) throws Exception {
		return getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}
	
	public PagedList<PerguntaFichaColeta> getListLoadAll(PerguntaFichaColetaFilter filter) throws Exception {
		return super.getList(getDao().getListLoadAll(getExampleBuilder(filter).example()),
				this.functionLoadAll);
	}
}
