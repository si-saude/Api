package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.AprhoFilter;
import br.com.saude.api.model.entity.po.Aprho;

public class AprhoBuilder extends GenericEntityBuilder<Aprho, AprhoFilter> {

	public static AprhoBuilder newInstance(Aprho aprho) {
		return new AprhoBuilder(aprho);
	}
	
	public static AprhoBuilder newInstance(List<Aprho> aprhos) {
		return new AprhoBuilder(aprhos);
	}
	
	private AprhoBuilder(List<Aprho> aprhos) {
		super(aprhos);
	}

	private AprhoBuilder(Aprho aprho) {
		super(aprho);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected Aprho clone(Aprho aprho) {
		Aprho newAprho = new Aprho();
		
		newAprho.setId(aprho.getId());
		newAprho.setEmpresa(aprho.getEmpresa());
		newAprho.setData(aprho.getData());
		newAprho.setVersion(aprho.getVersion());
		
		if(aprho.getGhe() != null)
			newAprho.setGhe(GheBuilder.newInstance(aprho.getGhe()).getEntity());
		
		return newAprho;
	}

	@Override
	public Aprho cloneFromFilter(AprhoFilter filter) {
		return null;
	}
}
