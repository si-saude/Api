package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.po.Empregado;

public class EmpregadoBuilder extends GenericEntityBuilder<Empregado,EmpregadoFilter> {

	public static EmpregadoBuilder newInstance(Empregado empregado) {
		return new EmpregadoBuilder(empregado);
	}
	
	public static EmpregadoBuilder newInstance(List<Empregado> empregados) {
		return new EmpregadoBuilder(empregados);
	}
	
	private EmpregadoBuilder(Empregado empregado) {
		super(empregado);
	}

	private EmpregadoBuilder(List<Empregado> empregados) {
		super(empregados);
	}

	@Override
	protected Empregado clone(Empregado empregado) {
		Empregado newEmpregado = new Empregado();
		
		newEmpregado.setId(empregado.getId());
		newEmpregado.setNome(empregado.getNome());
		newEmpregado.setCpf(empregado.getCpf());
		newEmpregado.setDataNascimento(empregado.getDataNascimento());
		newEmpregado.setVersion(empregado.getVersion());
		
		return newEmpregado;
	}

	@Override
	public Empregado cloneFromFilter(EmpregadoFilter filter) {
		return null;
	}
}
