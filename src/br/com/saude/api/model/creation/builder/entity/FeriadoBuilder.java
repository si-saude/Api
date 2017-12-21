package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.FeriadoFilter;
import br.com.saude.api.model.entity.po.Feriado;

public class FeriadoBuilder extends GenericEntityBuilder<Feriado, FeriadoFilter> {

	public static FeriadoBuilder newInstance(Feriado feriado) {
		return new FeriadoBuilder(feriado);
	}
	
	public static FeriadoBuilder newInstance(List<Feriado> feriados) {
		return new FeriadoBuilder(feriados);
	}
	
	private FeriadoBuilder(Feriado feriado) {
		super(feriado);
	}
	
	private FeriadoBuilder(List<Feriado> feriados) {
		super(feriados);
	}
	
	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected Feriado clone(Feriado feriado) {
		Feriado newFeriado = new Feriado();
		
		newFeriado.setId(feriado.getId());
		newFeriado.setVersion(feriado.getVersion());
		newFeriado.setTitulo(feriado.getTitulo());
		newFeriado.setData(feriado.getData());
		
		return newFeriado;
	}
	
	@Override
	public Feriado cloneFromFilter(FeriadoFilter filter) {
		return null;
	}


}
