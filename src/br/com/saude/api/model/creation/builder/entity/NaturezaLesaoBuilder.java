package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.NaturezaLesaoFilter;
import br.com.saude.api.model.entity.po.NaturezaLesao;

public class NaturezaLesaoBuilder extends GenericEntityBuilder<NaturezaLesao, NaturezaLesaoFilter> {

	private NaturezaLesaoBuilder(List<NaturezaLesao> naturezaLesaos) {
		super(naturezaLesaos);
	}

	private NaturezaLesaoBuilder(NaturezaLesao naturezaLesao) {
		super(naturezaLesao);
	}

	public static NaturezaLesaoBuilder newInstance(NaturezaLesao naturezaLesao) {
		return new NaturezaLesaoBuilder(naturezaLesao);
	}
	
	public static NaturezaLesaoBuilder newInstance(List<NaturezaLesao> naturezaLesaos) {
		return new NaturezaLesaoBuilder(naturezaLesaos);
	}

	@Override
	protected void initializeFunctions() {
		
	}
	
	@Override
	protected NaturezaLesao clone(NaturezaLesao naturezaLesao) {
		
		NaturezaLesao newNaturezaLesao = new NaturezaLesao();
		newNaturezaLesao.setId(naturezaLesao.getId());
		newNaturezaLesao.setCodigo(naturezaLesao.getCodigo());
		newNaturezaLesao.setDescricao(naturezaLesao.getDescricao());
		newNaturezaLesao.setVersion(naturezaLesao.getVersion());
		
		return newNaturezaLesao;
	}

	@Override
	public NaturezaLesao cloneFromFilter(NaturezaLesaoFilter filter) {
		return null;
	}

}
