package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.NutricaoAlimentoFilter;
import br.com.saude.api.model.entity.po.NutricaoAlimento;

public class NutricaoAlimentoBuilder extends GenericEntityBuilder<NutricaoAlimento, NutricaoAlimentoFilter> {

	private Function<Map<String,NutricaoAlimento>,NutricaoAlimento> loadNutricaoAlimentoMedidaAlimentares;

	
	private NutricaoAlimentoBuilder(List<NutricaoAlimento> nutricaoAlimentos) {
		super(nutricaoAlimentos);
	}

	private NutricaoAlimentoBuilder(NutricaoAlimento nutricaoAlimento) {
		super(nutricaoAlimento);
	}

	public static NutricaoAlimentoBuilder newInstance(NutricaoAlimento nutricaoAlimento) {
		return new NutricaoAlimentoBuilder(nutricaoAlimento);
	}
	
	public static NutricaoAlimentoBuilder newInstance(List<NutricaoAlimento> nutricaoAlimentos) {
		return new NutricaoAlimentoBuilder(nutricaoAlimentos);
	}

	@Override
	protected void initializeFunctions() {
		loadNutricaoAlimentoMedidaAlimentares  = nutricaoAlimentos -> {
			if(nutricaoAlimentos.get("origem").getNutricaoAlimentoMedidaAlimentares() != null)
				nutricaoAlimentos.get("destino").setNutricaoAlimentoMedidaAlimentares(
						NutricaoAlimentoMedidaAlimentarBuilder
						.newInstance(nutricaoAlimentos.get("origem").getNutricaoAlimentoMedidaAlimentares())
						.loadMedidaAlimentar()
						.getEntityList());
			return nutricaoAlimentos.get("destino");
		};
	}
	
	@Override
	protected NutricaoAlimento clone(NutricaoAlimento nutricaoAlimento) {
		
		NutricaoAlimento newNutricaoAlimento = new NutricaoAlimento();
		newNutricaoAlimento.setId(nutricaoAlimento.getId());
		newNutricaoAlimento.setVersion(nutricaoAlimento.getVersion());
		newNutricaoAlimento.setNome(nutricaoAlimento.getNome());
		newNutricaoAlimento.setTipo(nutricaoAlimento.getTipo());
		newNutricaoAlimento.setPadrao(nutricaoAlimento.getPadrao());
		newNutricaoAlimento.setEnergia(nutricaoAlimento.getEnergia());
		newNutricaoAlimento.setProteina(nutricaoAlimento.getProteina());
		newNutricaoAlimento.setLipideos(nutricaoAlimento.getLipideos());
		newNutricaoAlimento.setSaturada(nutricaoAlimento.getSaturada());
		newNutricaoAlimento.setMonoinsaturada(nutricaoAlimento.getMonoinsaturada());
		newNutricaoAlimento.setPoliinsaturada(nutricaoAlimento.getPoliinsaturada());
		newNutricaoAlimento.setOmega6(nutricaoAlimento.getOmega6());
		newNutricaoAlimento.setOmega3(nutricaoAlimento.getOmega3());
		newNutricaoAlimento.setColesterol(nutricaoAlimento.getColesterol());
		newNutricaoAlimento.setCho(nutricaoAlimento.getCho());
		newNutricaoAlimento.setFibra(nutricaoAlimento.getFibra());
		newNutricaoAlimento.setCalcio(nutricaoAlimento.getCalcio());
		newNutricaoAlimento.setMg(nutricaoAlimento.getMg());
		newNutricaoAlimento.setMn(nutricaoAlimento.getMn());
		newNutricaoAlimento.setP(nutricaoAlimento.getP());
		newNutricaoAlimento.setFerro(nutricaoAlimento.getFerro());
		newNutricaoAlimento.setSodio(nutricaoAlimento.getSodio());
		newNutricaoAlimento.setPotassio(nutricaoAlimento.getPotassio());
		newNutricaoAlimento.setCobre(nutricaoAlimento.getCobre());
		newNutricaoAlimento.setZinco(nutricaoAlimento.getZinco());
		newNutricaoAlimento.setRetinol(nutricaoAlimento.getRetinol());
		newNutricaoAlimento.setTiamina(nutricaoAlimento.getTiamina());
		newNutricaoAlimento.setRiboflavina(nutricaoAlimento.getRiboflavina());
		newNutricaoAlimento.setPiridoxina(nutricaoAlimento.getPiridoxina());
		newNutricaoAlimento.setNiacina(nutricaoAlimento.getNiacina());
		newNutricaoAlimento.setVitc(nutricaoAlimento.getVitc());
		newNutricaoAlimento.setVitd(nutricaoAlimento.getVitd());
		newNutricaoAlimento.setVite(nutricaoAlimento.getVite());
		newNutricaoAlimento.setInativo(nutricaoAlimento.isInativo());
		
		return newNutricaoAlimento;
	}

	@Override
	public NutricaoAlimento cloneFromFilter(NutricaoAlimentoFilter filter) {
		return null;
	}
	
	public NutricaoAlimentoBuilder loadNutricaoAlimentoMedidaAlimentar() {
		return (NutricaoAlimentoBuilder) this.loadProperty(this.loadNutricaoAlimentoMedidaAlimentares);
	}
}
