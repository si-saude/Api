package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.ResultadoExameFilter;
import br.com.saude.api.model.entity.po.ResultadoExame;

public class ResultadoExameBuilder extends GenericEntityBuilder<ResultadoExame, ResultadoExameFilter> {
	
	private Function<Map<String,ResultadoExame>,ResultadoExame> loadItemResultadoExames;
	private Function<Map<String,ResultadoExame>,ResultadoExame> loadEmpregadoConvocacao;
	
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
		this.loadItemResultadoExames = resultadoExames -> {
			if(resultadoExames.get("origem").getItemResultadoExames()!= null) {
				resultadoExames.get("destino").setItemResultadoExames(ItemResultadoExameBuilder.
						newInstance(resultadoExames.get("origem").getItemResultadoExames()).getEntityList());
			}
			return resultadoExames.get("destino");
		};
		
		this.loadEmpregadoConvocacao = resultadoExames -> {
			if(resultadoExames.get("origem").getEmpregadoConvocacao()!= null) {
				resultadoExames.get("destino").setEmpregadoConvocacao(EmpregadoConvocacaoBuilder.
						newInstance(resultadoExames.get("origem").getEmpregadoConvocacao())
						.loadEmpregado()
						.getEntity());
			}
			return resultadoExames.get("destino");
		};
	}

	@Override
	protected ResultadoExame clone(ResultadoExame resultadoExame) {
		ResultadoExame newResultadoExame = new ResultadoExame();
		
		newResultadoExame.setId(resultadoExame.getId());
		newResultadoExame.setConforme(resultadoExame.isConforme());
		newResultadoExame.setAcao(resultadoExame.getAcao());
		newResultadoExame.setData(resultadoExame.getData());
		newResultadoExame.setDataRecebimento(resultadoExame.getDataRecebimento());
		newResultadoExame.setLocal(resultadoExame.getLocal());
		newResultadoExame.setTipo(resultadoExame.getTipo());
		newResultadoExame.setVersion(resultadoExame.getVersion());
		
		if(resultadoExame.getExame() != null)
			newResultadoExame.setExame(ExameBuilder
					.newInstance(resultadoExame.getExame()).getEntity());
		
		return newResultadoExame;
	}

	@Override
	public ResultadoExame cloneFromFilter(ResultadoExameFilter filter) {
		return null;
	}
	
	public ResultadoExameBuilder loadItemResultadoExames() {
		return (ResultadoExameBuilder) this.loadProperty(this.loadItemResultadoExames); 
	}
	
	public ResultadoExameBuilder loadEmpregadoConvocacao() {
		return (ResultadoExameBuilder) this.loadProperty(this.loadEmpregadoConvocacao);
	}
	

}
