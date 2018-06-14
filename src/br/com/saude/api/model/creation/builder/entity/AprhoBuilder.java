package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.AprhoFilter;
import br.com.saude.api.model.entity.po.Aprho;


public class AprhoBuilder extends GenericEntityBuilder<Aprho, AprhoFilter> {

	private Function<Map<String,Aprho>,Aprho> loadAprhoItens;
	
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
	protected Aprho clone(Aprho aprho) {
		Aprho newAprho = new Aprho();
		
		newAprho.setId(aprho.getId());
		newAprho.setEmpresa(aprho.getEmpresa());
		newAprho.setVersion(aprho.getVersion());	
		newAprho.setData(aprho.getData());	
		if(aprho.getGhe() != null)
			newAprho.setGhe(new GheBuilder(aprho.getGhe()).getEntity());
		return newAprho;
	}
	@Override
	protected void initializeFunctions() {
		this.loadAprhoItens = aprhos ->{
			if(aprhos.get("origem").getAprhoItens() != null) {
				aprhos.get("destino").setAprhoItens(AprhoItemBuilder.newInstance(aprhos.get("origem").getAprhoItens()).getEntityList());
			}
			return aprhos.get("destino");
		};	
	}
	
	public AprhoBuilder loadAprhoItens() {
		return (AprhoBuilder) this.loadProperty(this.loadAprhoItens);
	}
	
	@Override
	public Aprho cloneFromFilter(AprhoFilter filter) {
		return null;
	}
}
