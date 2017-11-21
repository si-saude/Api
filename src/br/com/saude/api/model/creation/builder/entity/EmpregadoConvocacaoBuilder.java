package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.EmpregadoConvocacao;

public class EmpregadoConvocacaoBuilder 
		extends GenericEntityBuilder<EmpregadoConvocacao, GenericFilter> {

	private Function<Map<String,EmpregadoConvocacao>,EmpregadoConvocacao> loadExames;
	private Function<Map<String,EmpregadoConvocacao>,EmpregadoConvocacao> loadEmpregadoAll;
	private Function<Map<String,EmpregadoConvocacao>,EmpregadoConvocacao> loadEmpregado;
	
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
		
		this.loadExames = empregadoConvocacoes -> {
			if(empregadoConvocacoes.get("origem").getExames() != null)
				empregadoConvocacoes.get("destino").setExames(ExameBuilder
						.newInstance(empregadoConvocacoes.get("origem").getExames())
						.getEntityList());
			return empregadoConvocacoes.get("destino");
		};
	}
	
	public EmpregadoConvocacaoBuilder loadEmpregadoAll() {
		return (EmpregadoConvocacaoBuilder) this.loadProperty(this.loadEmpregadoAll);
	}
	
	public EmpregadoConvocacaoBuilder loadEmpregado() {
		return (EmpregadoConvocacaoBuilder) this.loadProperty(this.loadEmpregado);
	}
	
	public EmpregadoConvocacaoBuilder loadExames() {
		return (EmpregadoConvocacaoBuilder) this.loadProperty(this.loadExames);
	}

	@Override
	protected EmpregadoConvocacao clone(EmpregadoConvocacao empregadoConvocacao) {
		EmpregadoConvocacao newEmpregadoConvocacao = new EmpregadoConvocacao();
		newEmpregadoConvocacao.setId(empregadoConvocacao.getId());
		newEmpregadoConvocacao.setAuditado(empregadoConvocacao.isAuditado());
		newEmpregadoConvocacao.setSelecionado(empregadoConvocacao.isSelecionado());
		newEmpregadoConvocacao.setDivergente(empregadoConvocacao.isDivergente());
		newEmpregadoConvocacao.setConvocado(empregadoConvocacao.isConvocado());
		newEmpregadoConvocacao.setVersion(empregadoConvocacao.getVersion());
		return newEmpregadoConvocacao;
	}

	@Override
	public EmpregadoConvocacao cloneFromFilter(GenericFilter filter) {
		return null;
	}
}
