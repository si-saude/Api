package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.ImovelFilter;
import br.com.saude.api.model.entity.po.Imovel;

public class ImovelBuilder extends GenericEntityBuilder<Imovel, ImovelFilter> {

	private Function<Map<String,Imovel>, Imovel> loadBase;

	public static ImovelBuilder newInstance(Imovel imovel) {
		return new ImovelBuilder(imovel);
	}
	
	public static ImovelBuilder newInstance(List<Imovel> imoveis) {
		return new ImovelBuilder(imoveis);
	}
	
	private ImovelBuilder(List<Imovel> imoveis) {
		super(imoveis);
	}

	private ImovelBuilder(Imovel imovel) {
		super(imovel);
	}

	@Override
	protected void initializeFunctions() {
		this.loadBase = imoveis -> {
			if(imoveis.get("origem").getBase() != null)
				imoveis.get("destino").setBase(
						BaseBuilder.newInstance(imoveis.get("origem").getBase()).getEntity());
			return imoveis.get("destino");
		};
	}

	@Override
	protected Imovel clone(Imovel imovel) {
		Imovel newImovel = new Imovel();
		
		newImovel.setId(imovel.getId());
		newImovel.setNome(imovel.getNome());
		newImovel.setVersion(imovel.getVersion());
		
		return newImovel;
	}

	@Override
	public Imovel cloneFromFilter(ImovelFilter filter) {
		return null;
	}
	
	public ImovelBuilder loadBase() {
		return (ImovelBuilder) this.loadProperty(this.loadBase);
	}

}
