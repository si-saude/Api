package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.EmpregadoConvocacaoExame;

public class EmpregadoConvocacaoExameBuilder
	extends GenericEntityBuilder<EmpregadoConvocacaoExame, GenericFilter>{

	public static EmpregadoConvocacaoExameBuilder newInstance(EmpregadoConvocacaoExame empregadoConvocacaoExame) {
		return new EmpregadoConvocacaoExameBuilder(empregadoConvocacaoExame);
	}
	
	public static EmpregadoConvocacaoExameBuilder newInstance(List<EmpregadoConvocacaoExame> empregadoConvocacaoExames) {
		return new EmpregadoConvocacaoExameBuilder(empregadoConvocacaoExames);
	}
	
	private EmpregadoConvocacaoExameBuilder(List<EmpregadoConvocacaoExame> empregadoConvocacaoExames) {
		super(empregadoConvocacaoExames);
	}

	private EmpregadoConvocacaoExameBuilder(EmpregadoConvocacaoExame empregadoConvocacaoExame) {
		super(empregadoConvocacaoExame);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected EmpregadoConvocacaoExame clone(EmpregadoConvocacaoExame empregadoConvocacaoExame) {
		EmpregadoConvocacaoExame newEmpregadoConvocacaoExame = new EmpregadoConvocacaoExame();
		
		newEmpregadoConvocacaoExame.setId(empregadoConvocacaoExame.getId());
		newEmpregadoConvocacaoExame.setVersion(empregadoConvocacaoExame.getVersion());
		newEmpregadoConvocacaoExame.setConforme(empregadoConvocacaoExame.isConforme());
		newEmpregadoConvocacaoExame.setRealizacao(empregadoConvocacaoExame.getRealizacao());
		newEmpregadoConvocacaoExame.setRecebimento(empregadoConvocacaoExame.getRecebimento());
		newEmpregadoConvocacaoExame.setAuditoria(empregadoConvocacaoExame.getAuditoria());
		
		if(empregadoConvocacaoExame.getExame() != null)
			newEmpregadoConvocacaoExame.setExame(ExameBuilder
					.newInstance(empregadoConvocacaoExame.getExame()).getEntity());
		
		return newEmpregadoConvocacaoExame;
	}

	@Override
	public EmpregadoConvocacaoExame cloneFromFilter(GenericFilter filter) {
		return null;
	}

	
}
