package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.TriagemBuilder;
import br.com.saude.api.model.creation.builder.example.TriagemExampleBuilder;
import br.com.saude.api.model.entity.filter.TriagemFilter;
import br.com.saude.api.model.entity.po.Triagem;
import br.com.saude.api.model.persistence.TriagemDao;

public class TriagemBo extends GenericBo<Triagem, TriagemFilter, TriagemDao, TriagemBuilder, 
	TriagemExampleBuilder> {

	private static TriagemBo instance;
	
	private TriagemBo() {
		super();
	}
	
	public static TriagemBo getInstance() {
		if(instance == null)
			instance = new TriagemBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoad = builder -> {
			return builder.loadIndicadorEquipe();
		};
	}
	
	@Override
	public PagedList<Triagem> getList(TriagemFilter filter) throws Exception {
		return super.getList(filter,this.functionLoad);
	}
}
