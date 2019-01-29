package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.EmpregadoConvocacaoExameFilter;
import br.com.saude.api.model.entity.po.EmpregadoConvocacaoExame;

public class EmpregadoConvocacaoExameBuilder
	extends GenericEntityBuilder<EmpregadoConvocacaoExame, EmpregadoConvocacaoExameFilter>{

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
		newEmpregadoConvocacaoExame.setConforme(empregadoConvocacaoExame.isConforme());
		newEmpregadoConvocacaoExame.setExigeRelatorio(empregadoConvocacaoExame.isExigeRelatorio());
		newEmpregadoConvocacaoExame.setRealizacao(empregadoConvocacaoExame.getRealizacao());
		newEmpregadoConvocacaoExame.setResultado(empregadoConvocacaoExame.getResultado());
		newEmpregadoConvocacaoExame.setRecebimento(empregadoConvocacaoExame.getRecebimento());
		newEmpregadoConvocacaoExame.setOpcional(empregadoConvocacaoExame.isOpcional());
		newEmpregadoConvocacaoExame.setAuditoria(empregadoConvocacaoExame.getAuditoria());
		newEmpregadoConvocacaoExame.setResultadoConforme(empregadoConvocacaoExame.isResultadoConforme());
		newEmpregadoConvocacaoExame.setImportado(empregadoConvocacaoExame.isImportado());
		newEmpregadoConvocacaoExame.setVersion(empregadoConvocacaoExame.getVersion());
		
		if(empregadoConvocacaoExame.getExame() != null)
			newEmpregadoConvocacaoExame.setExame(ExameBuilder
					.newInstance(empregadoConvocacaoExame.getExame()).getEntity());
		
		return newEmpregadoConvocacaoExame;
	}

	@Override
	public EmpregadoConvocacaoExame cloneFromFilter(EmpregadoConvocacaoExameFilter filter) {
		return null;
	}

	
}
