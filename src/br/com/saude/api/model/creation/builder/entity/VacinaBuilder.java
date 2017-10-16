package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.VacinaFilter;
import br.com.saude.api.model.entity.po.Vacina;

public class VacinaBuilder extends GenericEntityBuilder<Vacina,VacinaFilter> {

	public static VacinaBuilder newInstance(Vacina vacina) {
		return new VacinaBuilder(vacina);
	}
	
	public static VacinaBuilder newInstance(List<Vacina> vacinas) {
		return new VacinaBuilder(vacinas);
	}
	
	private VacinaBuilder(Vacina vacina) {
		super(vacina);
	}

	private VacinaBuilder(List<Vacina> vacinas) {
		super(vacinas);
	}
	
	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected Vacina clone(Vacina vacina) {
		Vacina newVacina = new Vacina();
		
		newVacina.setId(vacina.getId());
		newVacina.setDescricao(vacina.getDescricao());
		newVacina.setDoses(vacina.getDoses());
		newVacina.setReforco(vacina.getReforco());
		newVacina.setVersion(vacina.getVersion());
		
		return newVacina;
	}

	@Override
	public Vacina cloneFromFilter(VacinaFilter filter) {
		return null;
	}

}
