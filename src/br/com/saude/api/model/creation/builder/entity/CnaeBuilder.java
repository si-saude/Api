package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.CnaeFilter;
import br.com.saude.api.model.entity.po.Cnae;

public class CnaeBuilder extends GenericEntityBuilder<Cnae,CnaeFilter> {
	private Function<Map<String,Cnae>,Cnae> loadEmpresa;
	
	public static CnaeBuilder newInstance(Cnae cnae) {
		return new CnaeBuilder(cnae);
	}
	
	public static CnaeBuilder newInstance(List<Cnae> cnaes) {
		return new CnaeBuilder(cnaes);
	}
	
	private CnaeBuilder(List<Cnae> cnaes) {
		super(cnaes);
	}

	private CnaeBuilder(Cnae cnae) {
		super(cnae);
	}

	@Override
	protected void initializeFunctions() {
		this.loadEmpresa = cnaes ->{
			if(cnaes.get("origem").getEmpresa() != null) {
				cnaes.get("destino").setEmpresa(EmpresaBuilder.newInstance(cnaes.get("origem").getEmpresa()).getEntity());
			}
			return cnaes.get("destino");
		};
	}
	
	@Override
	protected Cnae clone(Cnae cnae) {
		Cnae newCnae = new Cnae();
		
		newCnae.setId(cnae.getId());
		newCnae.setCodigo(cnae.getCodigo());
		newCnae.setVersion(cnae.getVersion());
		
		return newCnae;
	}

	@Override
	public Cnae cloneFromFilter(CnaeFilter filter) {
		return null;
	}
	
	public CnaeBuilder loadEmpresa() {
		return (CnaeBuilder) this.loadProperty(this.loadEmpresa);
	}
}