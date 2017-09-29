package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.Vacina;

public class VacinaBuilder extends GenericEntityBuilder<Vacina,GenericFilter> {

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
	protected Vacina clone(Vacina vacina) {
		Vacina newVacina = new Vacina();
		
		newVacina.setId(vacina.getId());
		newVacina.setData(vacina.getData());
		newVacina.setDescricao(vacina.getDescricao());
		newVacina.setDose(vacina.getDose());
		newVacina.setLaboratorio(vacina.getLaboratorio());
		newVacina.setLote(vacina.getLote());
		newVacina.setProximaDose(vacina.getProximaDose());
		
		return newVacina;
	}

	@Override
	public Vacina cloneFromFilter(GenericFilter filter) {
		return null;
	}

}
