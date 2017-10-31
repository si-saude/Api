package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.InstalacaoFilter;
import br.com.saude.api.model.entity.po.Instalacao;

public class InstalacaoBuilder extends GenericEntityBuilder<Instalacao,InstalacaoFilter> {

	private Function<Map<String,Instalacao>,Instalacao> loadIndicadorRiscoAcidenteInstalacoes;
	private Function<Map<String,Instalacao>,Instalacao> loadIndicadorRiscoAmbientalInstalacoes;
	private Function<Map<String,Instalacao>,Instalacao> loadIndicadorRiscoErgonomicoInstalacoes;
	private Function<Map<String,Instalacao>,Instalacao> loadIndicadorRiscoSanitarioInstalacoes;
	private Function<Map<String,Instalacao>,Instalacao> loadIndicadorRiscoSaudeAmbientalInstalacoes;
	
	public static InstalacaoBuilder newInstance(Instalacao instalacao) {
		return new InstalacaoBuilder(instalacao);
	}
	
	public static InstalacaoBuilder newInstance(List<Instalacao> instalacoes) {
		return new InstalacaoBuilder(instalacoes);
	}
	
	private InstalacaoBuilder(List<Instalacao> instalacoes) {
		super(instalacoes);
	}

	private InstalacaoBuilder(Instalacao instalacao) {
		super(instalacao);
	}
	
	@Override
	protected void initializeFunctions() {
		this.loadIndicadorRiscoAcidenteInstalacoes = instalacoes -> {
			if(instalacoes.get("origem").getIndicadorRiscoAcidenteInstalacoes() != null) {
				instalacoes.get("destino").setIndicadorRiscoAcidenteInstalacoes(IndicadorRiscoAcidenteInstalacaoBuilder
						.newInstance(instalacoes.get("origem").getIndicadorRiscoAcidenteInstalacoes()).getEntityList());
			}
			return instalacoes.get("destino");
		};
		
		this.loadIndicadorRiscoAmbientalInstalacoes = instalacoes -> {
			if(instalacoes.get("origem").getIndicadorRiscoAmbientalInstalacoes() != null) {
				instalacoes.get("destino").setIndicadorRiscoAmbientalInstalacoes(IndicadorRiscoAmbientalInstalacaoBuilder
						.newInstance(instalacoes.get("origem").getIndicadorRiscoAmbientalInstalacoes()).getEntityList());
			}
			return instalacoes.get("destino");
		};
		
		this.loadIndicadorRiscoErgonomicoInstalacoes = instalacoes -> {
			if(instalacoes.get("origem").getIndicadorRiscoErgonomicoInstalacoes() != null) {
				instalacoes.get("destino").setIndicadorRiscoErgonomicoInstalacoes(IndicadorRiscoErgonomicoInstalacaoBuilder
						.newInstance(instalacoes.get("origem").getIndicadorRiscoErgonomicoInstalacoes()).getEntityList());
			}
			return instalacoes.get("destino");
		};
		
		this.loadIndicadorRiscoSanitarioInstalacoes = instalacoes -> {
			if(instalacoes.get("origem").getIndicadorRiscoSanitarioInstalacoes() != null) {
				instalacoes.get("destino").setIndicadorRiscoSanitarioInstalacoes(IndicadorRiscoSanitarioInstalacaoBuilder
						.newInstance(instalacoes.get("origem").getIndicadorRiscoSanitarioInstalacoes()).getEntityList());
			}
			return instalacoes.get("destino");
		};
		
		this.loadIndicadorRiscoSaudeAmbientalInstalacoes = instalacoes -> {
			if(instalacoes.get("origem").getIndicadorRiscoSaudeAmbientalInstalacoes() != null) {
				instalacoes.get("destino").setIndicadorRiscoSaudeAmbientalInstalacoes(IndicadorRiscoSaudeAmbientalInstalacaoBuilder
						.newInstance(instalacoes.get("origem").getIndicadorRiscoSaudeAmbientalInstalacoes()).getEntityList());
			}
			return instalacoes.get("destino");
		};
	}

	@Override
	protected Instalacao clone(Instalacao instalacao) {
		Instalacao newInstalacao = new Instalacao();
		
		newInstalacao.setId(instalacao.getId());
		newInstalacao.setNome(instalacao.getNome());
		newInstalacao.setLatitude(instalacao.getLatitude());
		newInstalacao.setLongitude(instalacao.getLongitude());
		newInstalacao.setVersion(instalacao.getVersion());
		
		return newInstalacao;
	}
	
	public InstalacaoBuilder loadIndicadorRiscoAcidenteInstalacoes() {
		return (InstalacaoBuilder) this.loadProperty(this.loadIndicadorRiscoAcidenteInstalacoes);
	}
	
	public InstalacaoBuilder loadIndicadorRiscoAmbientalInstalacoes() {
		return (InstalacaoBuilder) this.loadProperty(this.loadIndicadorRiscoAmbientalInstalacoes);
	}
	
	public InstalacaoBuilder loadIndicadorRiscoErgonomicoInstalacoes() {
		return (InstalacaoBuilder) this.loadProperty(this.loadIndicadorRiscoErgonomicoInstalacoes);
	}
	
	public InstalacaoBuilder loadIndicadorRiscoSanitarioInstalacoes() {
		return (InstalacaoBuilder) this.loadProperty(this.loadIndicadorRiscoSanitarioInstalacoes);
	}
	
	public InstalacaoBuilder loadIndicadorRiscoSaudeAmbientalInstalacoes() {
		return (InstalacaoBuilder) this.loadProperty(this.loadIndicadorRiscoSaudeAmbientalInstalacoes);
	}

	@Override
	public Instalacao cloneFromFilter(InstalacaoFilter filter) {
		return null;
	}
}
