package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.IndicadorSastFilter;
import br.com.saude.api.model.entity.po.IndicadorSast;

public class IndicadorSastBuilder extends GenericEntityBuilder<IndicadorSast,IndicadorSastFilter>{
	private Function<Map<String,IndicadorSast>,IndicadorSast> loadEquipe;
	private Function<Map<String,IndicadorSast>,IndicadorSast> loadIndicadorAssociadoSasts;
	
	protected IndicadorSastBuilder(IndicadorSast indicadorSast) {
		super(indicadorSast);
	}
	
	private IndicadorSastBuilder(List<IndicadorSast> indicadorSasts) {
		super(indicadorSasts);
	}
	
	public static IndicadorSastBuilder newInstance(IndicadorSast indicadorSast) {
		return new IndicadorSastBuilder(indicadorSast);
	}
	
	public static IndicadorSastBuilder newInstance(List<IndicadorSast> indicadorSasts) {
		return new IndicadorSastBuilder(indicadorSasts);
	}

	@Override
	protected void initializeFunctions() {
		this.loadEquipe = indicadorSast -> {
			if (indicadorSast.get("origem").getEquipe() != null)
				indicadorSast.get("destino").setEquipe(EquipeBuilder.newInstance(indicadorSast.get("origem").getEquipe()).getEntity());
			return indicadorSast.get("destino");
		};
		
		this.loadIndicadorAssociadoSasts = indicadorAssociadoSast -> {
			if (indicadorAssociadoSast.get("origem").getIndicadorAssociadoSasts() != null)
				indicadorAssociadoSast.get("destino").setIndicadorAssociadoSasts(IndicadorAssociadoSastBuilder.newInstance(indicadorAssociadoSast.get("origem").getIndicadorAssociadoSasts()).getEntityList());
			return indicadorAssociadoSast.get("destino");
		};
	}

	@Override
	protected IndicadorSast clone(IndicadorSast indicadorSast) {
		IndicadorSast cloneIndicadorSast = new IndicadorSast();
		
		cloneIndicadorSast.setId(indicadorSast.getId());
		cloneIndicadorSast.setCodigo(indicadorSast.getCodigo());
		cloneIndicadorSast.setCodigoExcludente(indicadorSast.getCodigoExcludente());
		cloneIndicadorSast.setCritico(indicadorSast.getCritico());
		cloneIndicadorSast.setIndice0(indicadorSast.getIndice0());
		cloneIndicadorSast.setIndice1(indicadorSast.getIndice1());
		cloneIndicadorSast.setIndice2(indicadorSast.getIndice2());
		cloneIndicadorSast.setIndice3(indicadorSast.getIndice3());
		cloneIndicadorSast.setIndice4(indicadorSast.getIndice4());
		cloneIndicadorSast.setNome(indicadorSast.getNome());
		cloneIndicadorSast.setObrigatorio(indicadorSast.isObrigatorio());
		cloneIndicadorSast.setInativo(indicadorSast.isInativo());
		cloneIndicadorSast.setVersion(indicadorSast.getVersion());
		
		return cloneIndicadorSast;
	}

	@Override
	public IndicadorSast cloneFromFilter(IndicadorSastFilter filter) {
		return null;
	}
	
	public IndicadorSastBuilder loadEquipe() {
		return (IndicadorSastBuilder) this.loadProperty(this.loadEquipe);
	}
	
	public IndicadorSastBuilder loadIndicadorAssociadoSasts() {
		return (IndicadorSastBuilder) this.loadProperty(this.loadIndicadorAssociadoSasts);
	}

}
