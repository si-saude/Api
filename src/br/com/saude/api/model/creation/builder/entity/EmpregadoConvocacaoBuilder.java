package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.EmpregadoConvocacaoFilter;
import br.com.saude.api.model.entity.po.EmpregadoConvocacao;

public class EmpregadoConvocacaoBuilder 
		extends GenericEntityBuilder<EmpregadoConvocacao, EmpregadoConvocacaoFilter> {

	private Function<Map<String,EmpregadoConvocacao>,EmpregadoConvocacao> loadEmpregadoConvocacaoExames;
	private Function<Map<String,EmpregadoConvocacao>,EmpregadoConvocacao> loadEmpregadoAll;
	private Function<Map<String,EmpregadoConvocacao>,EmpregadoConvocacao> loadEmpregado;
	private Function<Map<String,EmpregadoConvocacao>,EmpregadoConvocacao> loadConvocacao;
	private Function<Map<String,EmpregadoConvocacao>,EmpregadoConvocacao> loadResultadoExames;
	
	public static EmpregadoConvocacaoBuilder newInstance(EmpregadoConvocacao empregadoConvocacao) {
		return new EmpregadoConvocacaoBuilder(empregadoConvocacao);
	}
	
	public static EmpregadoConvocacaoBuilder newInstance(List<EmpregadoConvocacao> empregadoConvocacoes) {
		return new EmpregadoConvocacaoBuilder(empregadoConvocacoes);
	}
	
	private EmpregadoConvocacaoBuilder(List<EmpregadoConvocacao> empregadoConvocacoes) {
		super(empregadoConvocacoes);
	}

	private EmpregadoConvocacaoBuilder(EmpregadoConvocacao empregadoConvocacao) {
		super(empregadoConvocacao);
	}

	@Override
	protected void initializeFunctions() {
		this.loadEmpregadoAll = empregadoConvocacoes -> {
			if(empregadoConvocacoes.get("origem").getEmpregado() != null)
				empregadoConvocacoes.get("destino").setEmpregado(EmpregadoBuilder
						.newInstance(empregadoConvocacoes.get("origem").getEmpregado())
						.loadGerencia()
						.loadCargo()
						.loadFuncao()
						.loadGrupoMonitoramentos()
						.getEntity());
			return empregadoConvocacoes.get("destino"); 
		};
		
		this.loadEmpregado = empregadoConvocacoes -> {
			if(empregadoConvocacoes.get("origem").getEmpregado() != null)
				empregadoConvocacoes.get("destino").setEmpregado(EmpregadoBuilder
						.newInstance(empregadoConvocacoes.get("origem").getEmpregado())
						.loadGerencia()
						.getEntity());
			return empregadoConvocacoes.get("destino"); 
		};
		
		this.loadConvocacao = empregadoConvocacoes -> {
			if(empregadoConvocacoes.get("origem").getConvocacao() != null)
				empregadoConvocacoes.get("destino").setConvocacao(ConvocacaoBuilder
						.newInstance(empregadoConvocacoes.get("origem").getConvocacao())
						.getEntity());
			return empregadoConvocacoes.get("destino"); 
		};
		
		this.loadEmpregadoConvocacaoExames = empregadoConvocacoes -> {
			if(empregadoConvocacoes.get("origem").getEmpregadoConvocacaoExames() != null)
				empregadoConvocacoes.get("destino").setEmpregadoConvocacaoExames(EmpregadoConvocacaoExameBuilder
						.newInstance(empregadoConvocacoes.get("origem").getEmpregadoConvocacaoExames())
						.getEntityList());
			return empregadoConvocacoes.get("destino");
		};
		
		this.loadResultadoExames = empregadoConvocacoes -> {
			if (empregadoConvocacoes.get("origem").getResultadoExames() != null)
				empregadoConvocacoes.get("destino").setResultadoExames(ResultadoExameBuilder.newInstance(
						empregadoConvocacoes.get("origem").getResultadoExames()).loadItemResultadoExames().getEntityList());
			return empregadoConvocacoes.get("destino");			
		};
	}
	
	public EmpregadoConvocacaoBuilder loadEmpregadoAll() {
		return (EmpregadoConvocacaoBuilder) this.loadProperty(this.loadEmpregadoAll);
	}
	
	public EmpregadoConvocacaoBuilder loadEmpregado() {
		return (EmpregadoConvocacaoBuilder) this.loadProperty(this.loadEmpregado);
	}
	
	public EmpregadoConvocacaoBuilder loadConvocacao() {
		return (EmpregadoConvocacaoBuilder) this.loadProperty(this.loadConvocacao);
	}
	
	public EmpregadoConvocacaoBuilder loadExames() {
		return (EmpregadoConvocacaoBuilder) this.loadProperty(this.loadEmpregadoConvocacaoExames);
	}
	
	public EmpregadoConvocacaoBuilder loadResultadoExames() {
		return (EmpregadoConvocacaoBuilder) this.loadProperty(this.loadResultadoExames);
	}

	@Override
	protected EmpregadoConvocacao clone(EmpregadoConvocacao empregadoConvocacao) {
		EmpregadoConvocacao newEmpregadoConvocacao = new EmpregadoConvocacao();
		newEmpregadoConvocacao.setId(empregadoConvocacao.getId());
		newEmpregadoConvocacao.setAuditado(empregadoConvocacao.isAuditado());
		newEmpregadoConvocacao.setResultadoAuditado(empregadoConvocacao.isResultadoAuditado());
		newEmpregadoConvocacao.setSelecionado(empregadoConvocacao.isSelecionado());
		newEmpregadoConvocacao.setConvocado(empregadoConvocacao.isConvocado());
		newEmpregadoConvocacao.setVersion(empregadoConvocacao.getVersion());
		return newEmpregadoConvocacao;
	}

	@Override
	public EmpregadoConvocacao cloneFromFilter(EmpregadoConvocacaoFilter filter) {
		return null;
	}
}
