package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.EmpregadoVacina;

public class EmpregadoVacinaBuilder extends GenericEntityBuilder<EmpregadoVacina, GenericFilter> {

	private Function<Map<String,EmpregadoVacina>,EmpregadoVacina> loadVacina;
	
	public static EmpregadoVacinaBuilder newInstance(EmpregadoVacina empregadoVacina) {
		return new EmpregadoVacinaBuilder(empregadoVacina);
	}
	
	public static EmpregadoVacinaBuilder newInstance(List<EmpregadoVacina> empregadoVacinas) {
		return new EmpregadoVacinaBuilder(empregadoVacinas);
	}
	
	private EmpregadoVacinaBuilder(List<EmpregadoVacina> empregadoVacinas) {
		super(empregadoVacinas);
	}

	private EmpregadoVacinaBuilder(EmpregadoVacina empregadoVacina) {
		super(empregadoVacina);
	}

	@Override
	protected void initializeFunctions() {
		this.loadVacina = empregadoVacinas -> {
			if(empregadoVacinas.get("origem").getVacina()!= null) {
				empregadoVacinas.get("destino").setVacina(VacinaBuilder.newInstance(empregadoVacinas.get("origem").getVacina()).getEntity());
			}
			return empregadoVacinas.get("destino");
		};
	}

	@Override
	protected EmpregadoVacina clone(EmpregadoVacina empregadoVacina) {
		EmpregadoVacina newEmpregadoVacina = new EmpregadoVacina();
		
		newEmpregadoVacina.setId(empregadoVacina.getId());
		newEmpregadoVacina.setData(empregadoVacina.getData());
		newEmpregadoVacina.setDose(empregadoVacina.getDose());
		newEmpregadoVacina.setLaboratorio(empregadoVacina.getLaboratorio());
		newEmpregadoVacina.setLote(empregadoVacina.getLote());
		newEmpregadoVacina.setProximaDose(empregadoVacina.getProximaDose());
		newEmpregadoVacina.setVersion(empregadoVacina.getVersion());
		
		return newEmpregadoVacina;
	}
	
	public EmpregadoVacinaBuilder loadVacina() {
		return (EmpregadoVacinaBuilder) this.loadProperty(this.loadVacina);
	}

	@Override
	public EmpregadoVacina cloneFromFilter(GenericFilter filter) {
		return null;
	}
}
