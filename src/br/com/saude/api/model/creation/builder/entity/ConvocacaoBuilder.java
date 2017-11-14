package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.ConvocacaoFilter;
import br.com.saude.api.model.entity.po.Convocacao;

public class ConvocacaoBuilder extends GenericEntityBuilder<Convocacao, ConvocacaoFilter> {

	private Function<Map<String,Convocacao>,Convocacao> loadProfissiograma;
	private Function<Map<String,Convocacao>,Convocacao> loadGerenciaConvocacoes;
	private Function<Map<String,Convocacao>,Convocacao> loadEmpregadoConvocacoesAll;
	private Function<Map<String,Convocacao>,Convocacao> loadEmpregadoConvocacoes;
	
	public static ConvocacaoBuilder newInstance(Convocacao convocacao) {
		return new ConvocacaoBuilder(convocacao);
	}
	
	public static ConvocacaoBuilder newInstance(List<Convocacao> convocacoes) {
		return new ConvocacaoBuilder(convocacoes);
	}
	
	private ConvocacaoBuilder(List<Convocacao> convocacoes) {
		super(convocacoes);
	}

	private ConvocacaoBuilder(Convocacao convocacao) {
		super(convocacao);
	}

	@Override
	protected void initializeFunctions() {
		this.loadProfissiograma = convocacoes -> {
			if(convocacoes.get("origem").getProfissiograma() != null)
				convocacoes.get("destino").setProfissiograma(ProfissiogramaBuilder
						.newInstance(convocacoes.get("origem").getProfissiograma())
						.getEntity());
			return convocacoes.get("destino");
		};
		
		this.loadGerenciaConvocacoes = convocacoes -> {
			if(convocacoes.get("origem").getGerenciaConvocacoes() != null)
				convocacoes.get("destino").setGerenciaConvocacoes(GerenciaConvocacaoBuilder
						.newInstance(convocacoes.get("origem").getGerenciaConvocacoes())
						.getEntityList());
			return convocacoes.get("destino");
		};
		
		this.loadEmpregadoConvocacoesAll  = convocacoes -> {
			if(convocacoes.get("origem").getEmpregadoConvocacoes() != null)
				convocacoes.get("destino").setEmpregadoConvocacoes(EmpregadoConvocacaoBuilder
						.newInstance(convocacoes.get("origem").getEmpregadoConvocacoes())
						.loadEmpregadoAll().loadExames()
						.getEntityList());
			return convocacoes.get("destino");
		};
		
		this.loadEmpregadoConvocacoes  = convocacoes -> {
			if(convocacoes.get("origem").getEmpregadoConvocacoes() != null)
				convocacoes.get("destino").setEmpregadoConvocacoes(EmpregadoConvocacaoBuilder
						.newInstance(convocacoes.get("origem").getEmpregadoConvocacoes())
						.loadEmpregado().loadExames()
						.getEntityList());
			return convocacoes.get("destino");
		};
	}

	@Override
	protected Convocacao clone(Convocacao convocacao) {
		Convocacao newConvocacao = new Convocacao();
		newConvocacao.setId(convocacao.getId());
		newConvocacao.setTipo(convocacao.getTipo());
		newConvocacao.setTitulo(convocacao.getTitulo());
		newConvocacao.setVersion(convocacao.getVersion());
		return newConvocacao;
	}
	
	public ConvocacaoBuilder loadProfissiograma() {
		return (ConvocacaoBuilder) this.loadProperty(this.loadProfissiograma);
	}
	
	public ConvocacaoBuilder loadGerenciaConvocacoes() {
		return (ConvocacaoBuilder) this.loadProperty(this.loadGerenciaConvocacoes);
	}
	
	public ConvocacaoBuilder loadEmpregadoConvocacoesAll() {
		return (ConvocacaoBuilder) this.loadProperty(this.loadEmpregadoConvocacoesAll);
	}
	
	public ConvocacaoBuilder loadEmpregadoConvocacoes() {
		return (ConvocacaoBuilder) this.loadProperty(this.loadEmpregadoConvocacoes);
	}

	@Override
	public Convocacao cloneFromFilter(ConvocacaoFilter filter) {
		return null;
	}

}
