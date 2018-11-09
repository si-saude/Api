package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.RefeicaoFilter;
import br.com.saude.api.model.entity.po.Refeicao;

public class RefeicaoBuilder extends GenericEntityBuilder<Refeicao, RefeicaoFilter> {
	
	public static RefeicaoBuilder newInstance(Refeicao refeicao) {
		return new RefeicaoBuilder(refeicao);
	}
	
	public static RefeicaoBuilder newInstance(List<Refeicao> refeicaos) {
		return new RefeicaoBuilder(refeicaos);
	}
	
	private RefeicaoBuilder(Refeicao refeicaos) {
		super(refeicaos);
	}
	
	private RefeicaoBuilder(List<Refeicao> refeicaos) {
		super(refeicaos);
	}

	@Override
	protected void initializeFunctions() {
		// TODO Auto-generated method stub	
	}
	
	@Override
	protected Refeicao clone(Refeicao refeicao) {
		Refeicao cloneRefeicao = new Refeicao();
		
		cloneRefeicao.setId(refeicao.getId());
		cloneRefeicao.setVersion(refeicao.getVersion());
		cloneRefeicao.setNome(refeicao.getNome());
		
		if ( refeicao.getItens() != null ) 
			cloneRefeicao.setItens(ItemRefeicaoBuilder.newInstance(refeicao.getItens()).getEntityList());
		
		if ( refeicao.getRecordatorio() != null )
			cloneRefeicao.setRecordatorio(RecordatorioBuilder.newInstance(refeicao.getRecordatorio()).getEntity());
		
		return cloneRefeicao;
	}
	
	@Override
	public Refeicao cloneFromFilter(RefeicaoFilter filter) {
		return null;
	}
}

