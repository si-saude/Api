package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.AlimentoFilter;
import br.com.saude.api.model.entity.po.Alimento;

public class AlimentoBuilder extends GenericEntityBuilder<Alimento, AlimentoFilter> {

	private Function<Map<String,Alimento>,Alimento> loadNutricaoAlimentoMedidaAlimentares;	
	
	private AlimentoBuilder(List<Alimento> nutricaoAlimentos) {
		super(nutricaoAlimentos);
	}

	private AlimentoBuilder(Alimento nutricaoAlimento) {
		super(nutricaoAlimento);
	}

	public static AlimentoBuilder newInstance(Alimento nutricaoAlimento) {
		return new AlimentoBuilder(nutricaoAlimento);
	}
	
	public static AlimentoBuilder newInstance(List<Alimento> nutricaoAlimentos) {
		return new AlimentoBuilder(nutricaoAlimentos);
	}

	@Override
	protected void initializeFunctions() {
		loadNutricaoAlimentoMedidaAlimentares  = nutricaoAlimentos -> {
			if(nutricaoAlimentos.get("origem").getAlimentoMedidaAlimentares() != null)
				nutricaoAlimentos.get("destino").setAlimentoMedidaAlimentares(
						AlimentoMedidaAlimentarBuilder
						.newInstance(nutricaoAlimentos.get("origem").getAlimentoMedidaAlimentares())
						.loadMedidaAlimentar()
						.getEntityList());
			return nutricaoAlimentos.get("destino");
		};
		
	}
	
	@Override
	protected Alimento clone(Alimento nutricaoAlimento) {
		
		Alimento newNutricaoAlimento = new Alimento();
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
		newNutricaoAlimento.setTipoCarboidrato(nutricaoAlimento.getTipoCarboidrato());
		
		return newNutricaoAlimento;
	}

	@Override
	public Alimento cloneFromFilter(AlimentoFilter filter) {
		return null;
	}
	
	public AlimentoBuilder loadNutricaoAlimentoMedidaAlimentar() {
		return (AlimentoBuilder) this.loadProperty(this.loadNutricaoAlimentoMedidaAlimentares);
	}
}
