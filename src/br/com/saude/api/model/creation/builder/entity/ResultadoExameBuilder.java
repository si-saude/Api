package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.ResultadoExameFilter;
import br.com.saude.api.model.entity.po.ResultadoExame;

public class ResultadoExameBuilder extends GenericEntityBuilder<ResultadoExame, ResultadoExameFilter> {

	public static ResultadoExameBuilder newInstance(ResultadoExame resultadoExame) {
		return new ResultadoExameBuilder(resultadoExame);
	}
	
	public static ResultadoExameBuilder newInstance(List<ResultadoExame> resultadoExames) {
		return new ResultadoExameBuilder(resultadoExames);
	}
	
	private ResultadoExameBuilder(ResultadoExame resultadoExame) {
		super(resultadoExame);
	}

	private ResultadoExameBuilder(List<ResultadoExame> resultadoExames) {
		super(resultadoExames);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected ResultadoExame clone(ResultadoExame resultadoExame) {
		ResultadoExame newResultadoExame = new ResultadoExame();
		
		newResultadoExame.setId(resultadoExame.getId());
		newResultadoExame.setConforme(resultadoExame.isConforme());
		newResultadoExame.setAcao(resultadoExame.getAcao());
		newResultadoExame.setData(resultadoExame.getData());
		newResultadoExame.setLocal(resultadoExame.getLocal());
		newResultadoExame.setTipo(resultadoExame.getTipo());
		newResultadoExame.setVersion(resultadoExame.getVersion());
		
		if(resultadoExame.getEmpregadoConvocacao() != null)
			newResultadoExame.setEmpregadoConvocacao(EmpregadoConvocacaoBuilder
					.newInstance(resultadoExame.getEmpregadoConvocacao()).loadEmpregado().loadConvocacao().getEntity());
		
		if(resultadoExame.getExame() != null)
			newResultadoExame.setExame(ExameBuilder
					.newInstance(resultadoExame.getExame()).getEntity());
		
		return newResultadoExame;
	}

	@Override
	public ResultadoExame cloneFromFilter(ResultadoExameFilter filter) {
		return null;
	}

}
