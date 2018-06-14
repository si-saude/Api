package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.AprhoItemFilter;
import br.com.saude.api.model.entity.po.AprhoItem;

public class AprhoItemBuilder 
		extends GenericEntityBuilder<AprhoItem, AprhoItemFilter> {

	
	public static AprhoItemBuilder newInstance(AprhoItem aprhoItem) {
		return new AprhoItemBuilder(aprhoItem);
	}
	
	public static AprhoItemBuilder newInstance(List<AprhoItem> aprhoItens) {
		return new AprhoItemBuilder(aprhoItens);
	}
	
	private AprhoItemBuilder(List<AprhoItem> aprhoItens) {
		super(aprhoItens);
	}

	private AprhoItemBuilder(AprhoItem aprhoItem) {
		super(aprhoItem);
	}

	
	@Override
	protected AprhoItem clone(AprhoItem aprhoItem) {
		AprhoItem newAprhoItem = new AprhoItem();
		newAprhoItem.setId(aprhoItem.getId());
		newAprhoItem.setAtividade(aprhoItem.getAtividade());
		newAprhoItem.setLocal(aprhoItem.getLocal());
		newAprhoItem.setFrequencia(aprhoItem.getFrequencia());
		newAprhoItem.setDuracao(aprhoItem.getDuracao());
		newAprhoItem.setMedidaControle(aprhoItem.getMedidaControle());
		newAprhoItem.setMeioPropagacao(aprhoItem.getMeioPropagacao());
		newAprhoItem.setVersion(aprhoItem.getVersion());	
		
		if(aprhoItem.getPossivelDanoSaude() != null)
			newAprhoItem.setPossivelDanoSaude(new PossivelDanoSaudeBuilder(aprhoItem.getPossivelDanoSaude()).getEntity());
		
		if(aprhoItem.getAgenteRisco() != null)
			newAprhoItem.setAgenteRisco(new AgenteRiscoBuilder(aprhoItem.getAgenteRisco()).getEntity());
		
		if(aprhoItem.getFonteGeradora() != null)
			newAprhoItem.setFonteGeradora(new FonteGeradoraBuilder(aprhoItem.getFonteGeradora()).getEntity());
		
		if(aprhoItem.getCategoriaRisco() != null)
			newAprhoItem.setCategoriaRisco(new CategoriaRiscoBuilder(aprhoItem.getCategoriaRisco()).getEntity());
		
		return newAprhoItem;
	}

	@Override
	protected void initializeFunctions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AprhoItem cloneFromFilter(AprhoItemFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}
}
