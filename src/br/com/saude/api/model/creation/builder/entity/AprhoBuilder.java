package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.AprhoFilter;
import br.com.saude.api.model.entity.po.Aprho;


public class AprhoBuilder extends GenericEntityBuilder<Aprho, AprhoFilter> {

	private Function<Map<String,Aprho>,Aprho> loadAprhoItens;
	
	private Function<Map<String,Aprho>,Aprho> loadAprhoEmpregados;
	
	private Function<Map<String,Aprho>,Aprho> loadElaboradores;
	
	public static AprhoBuilder newInstance(Aprho aprho) {
		return new AprhoBuilder(aprho);
	}
	
	public static AprhoBuilder newInstance(List<Aprho> aprhos) {
		return new AprhoBuilder(aprhos);
	}
	
	private AprhoBuilder(List<Aprho> aprhos) {
		super(aprhos);
	}

	protected AprhoBuilder(Aprho aprho) {
		super(aprho);
	}


	@Override
	protected Aprho clone(Aprho aprho) {
		Aprho newAprho = new Aprho();
		
		newAprho.setId(aprho.getId());
		newAprho.setEmpresa(aprho.getEmpresa());
		newAprho.setVersion(aprho.getVersion());	
		newAprho.setData(aprho.getData());	
		newAprho.setDataRevisao(aprho.getDataRevisao());
		newAprho.setRevisao(aprho.getRevisao());
		if(aprho.getGhe() != null)
			newAprho.setGhe(GheBuilder.newInstance(aprho.getGhe()).getEntity());
		if(aprho.getAprovador() != null)
			newAprho.setAprovador(ProfissionalBuilder.newInstance(aprho.getAprovador()).getEntity());
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
		
		this.loadAprhoEmpregados = aprhos ->{
			
			if(aprhos.get("origem").getAprhoEmpregados() != null) {
				aprhos.get("destino").setAprhoEmpregados(AprhoEmpregadoBuilder.newInstance(aprhos.get("origem").getAprhoEmpregados()).loadEmpregado().getEntityList());
			}	
			return aprhos.get("destino");
		};	
		
		this.loadElaboradores = aprhos ->{
			if(aprhos.get("origem").getElaboradores() != null) {
				aprhos.get("destino").setElaboradores(ProfissionalBuilder.newInstance(aprhos.get("origem").getElaboradores()).getEntityList());
			}
			return aprhos.get("destino");
		};
	}
	
	public AprhoBuilder loadAprhoItens() {
		return (AprhoBuilder) this.loadProperty(this.loadAprhoItens);
	}
	
	public AprhoBuilder loadAprhoEmpregados() {
		return (AprhoBuilder) this.loadProperty(this.loadAprhoEmpregados);
	}
	
	@Override
	public Aprho cloneFromFilter(AprhoFilter filter) {
		return null;
	}
	
	public AprhoBuilder loadElaboradores() {
		return (AprhoBuilder) this.loadProperty(this.loadElaboradores);
	}
}
