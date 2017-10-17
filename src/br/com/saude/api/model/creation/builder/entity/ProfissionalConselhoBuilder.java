package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.ProfissionalConselhoFilter;
import br.com.saude.api.model.entity.po.ProfissionalConselho;

public class ProfissionalConselhoBuilder 
				extends GenericEntityBuilder<ProfissionalConselho,ProfissionalConselhoFilter> {

	public static ProfissionalConselhoBuilder newInstance(ProfissionalConselho profissionalConselho) {
		return new ProfissionalConselhoBuilder(profissionalConselho);
	}
	
	public static ProfissionalConselhoBuilder newInstance(List<ProfissionalConselho> profissionalConselhos) {
		return new ProfissionalConselhoBuilder(profissionalConselhos);
	}
	
	private ProfissionalConselhoBuilder(ProfissionalConselho profissionalConselho) {
		super(profissionalConselho);
	}

	private ProfissionalConselhoBuilder(List<ProfissionalConselho> profissionalConselhos) {
		super(profissionalConselhos);
	}
	
	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected ProfissionalConselho clone(ProfissionalConselho profissionalConselho) {
		ProfissionalConselho newProfissionalConselho = new ProfissionalConselho();
		
		newProfissionalConselho.setId(profissionalConselho.getId());
		newProfissionalConselho.setConselho(profissionalConselho.getConselho());
		newProfissionalConselho.setNumero(profissionalConselho.getNumero());
		newProfissionalConselho.setUf(profissionalConselho.getUf());
		newProfissionalConselho.setVencimento(profissionalConselho.getVencimento());
		
		return newProfissionalConselho;
	}

	@Override
	public ProfissionalConselho cloneFromFilter(ProfissionalConselhoFilter filter) {
		return null;
	}

}
