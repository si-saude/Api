package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.InstalacaoFilter;
import br.com.saude.api.model.entity.po.Instalacao;

public class InstalacaoBuilder extends GenericEntityBuilder<Instalacao,InstalacaoFilter> {

	public InstalacaoBuilder newInstance(Instalacao instalacao) {
		return new InstalacaoBuilder(instalacao);
	}
	
	public InstalacaoBuilder newInstance(List<Instalacao> instalacoes) {
		return new InstalacaoBuilder(instalacoes);
	}
	
	private InstalacaoBuilder(List<Instalacao> instalacoes) {
		super(instalacoes);
	}

	private InstalacaoBuilder(Instalacao instalacao) {
		super(instalacao);
	}

	@Override
	protected Instalacao clone(Instalacao instalacao) {
		Instalacao newInstalacao = new Instalacao();
		
		newInstalacao.setId(instalacao.getId());
		newInstalacao.setNome(instalacao.getNome());
		newInstalacao.setVersion(instalacao.getVersion());
		
		return newInstalacao;
	}
	
	public InstalacaoBuilder loadIndicadorRiscoAcidenteInstalacoes() {
		if(this.entity != null) {
			this.newEntity = loadIndicadorRiscoAcidenteInstalacoes(this.entity,this.newEntity);
		}else {
			for(Instalacao instalacao:this.entityList) {
				Instalacao newInstalacao = this.newEntityList.stream()
						.filter(e->e.getId() == instalacao.getId())
						.iterator().next();
				newInstalacao = loadIndicadorRiscoAcidenteInstalacoes(instalacao,newInstalacao);
			}
		}
		return this;
	}
	
	private Instalacao loadIndicadorRiscoAcidenteInstalacoes(Instalacao origem,Instalacao destino) {
		if(origem.getIndicadorRiscoAcidenteInstalacoes() != null) {
			destino.setIndicadorRiscoAcidenteInstalacoes(IndicadorRiscoAcidenteInstalacaoBuilder
					.newInstance(origem.getIndicadorRiscoAcidenteInstalacoes()).getEntityList());
		}
		return destino;
	}
	
	public InstalacaoBuilder loadIndicadorRiscoAmbientalInstalacoes() {
		if(this.entity != null) {
			this.newEntity = loadIndicadorRiscoAmbientalInstalacoes(this.entity,this.newEntity);
		}else {
			for(Instalacao instalacao:this.entityList) {
				Instalacao newInstalacao = this.newEntityList.stream()
						.filter(e->e.getId() == instalacao.getId())
						.iterator().next();
				newInstalacao = loadIndicadorRiscoAmbientalInstalacoes(instalacao,newInstalacao);
			}
		}
		return this;
	}
	
	private Instalacao loadIndicadorRiscoAmbientalInstalacoes(Instalacao origem,Instalacao destino) {
		if(origem.getIndicadorRiscoAmbientalInstalacoes() != null) {
			destino.setIndicadorRiscoAmbientalInstalacoes(IndicadorRiscoAmbientalInstalacaoBuilder
					.newInstance(origem.getIndicadorRiscoAmbientalInstalacoes()).getEntityList());
		}
		return destino;
	}
	
	public InstalacaoBuilder loadIndicadorRiscoErgonomicoInstalacoes() {
		if(this.entity != null) {
			this.newEntity = loadIndicadorRiscoErgonomicoInstalacoes(this.entity,this.newEntity);
		}else {
			for(Instalacao instalacao:this.entityList) {
				Instalacao newInstalacao = this.newEntityList.stream()
						.filter(e->e.getId() == instalacao.getId())
						.iterator().next();
				newInstalacao = loadIndicadorRiscoErgonomicoInstalacoes(instalacao,newInstalacao);
			}
		}
		return this;
	}
	
	private Instalacao loadIndicadorRiscoErgonomicoInstalacoes(Instalacao origem,Instalacao destino) {
		if(origem.getIndicadorRiscoErgonomicoInstalacoes() != null) {
			destino.setIndicadorRiscoErgonomicoInstalacoes(IndicadorRiscoErgonomicoInstalacaoBuilder
					.newInstance(origem.getIndicadorRiscoErgonomicoInstalacoes()).getEntityList());
		}
		return destino;
	}
	
	public InstalacaoBuilder loadIndicadorRiscoSanitarioInstalacoes() {
		if(this.entity != null) {
			this.newEntity = loadIndicadorRiscoSanitarioInstalacoes(this.entity,this.newEntity);
		}else {
			for(Instalacao instalacao:this.entityList) {
				Instalacao newInstalacao = this.newEntityList.stream()
						.filter(e->e.getId() == instalacao.getId())
						.iterator().next();
				newInstalacao = loadIndicadorRiscoSanitarioInstalacoes(instalacao,newInstalacao);
			}
		}
		return this;
	}
	
	private Instalacao loadIndicadorRiscoSanitarioInstalacoes(Instalacao origem,Instalacao destino) {
		if(origem.getIndicadorRiscoSanitarioInstalacoes() != null) {
			destino.setIndicadorRiscoSanitarioInstalacoes(IndicadorRiscoSanitarioInstalacaoBuilder
					.newInstance(origem.getIndicadorRiscoSanitarioInstalacoes()).getEntityList());
		}
		return destino;
	}
	
	public InstalacaoBuilder loadIndicadorRiscoSaudeAmbientalInstalacoes() {
		if(this.entity != null) {
			this.newEntity = loadIndicadorRiscoSaudeAmbientalInstalacoes(this.entity,this.newEntity);
		}else {
			for(Instalacao instalacao:this.entityList) {
				Instalacao newInstalacao = this.newEntityList.stream()
						.filter(e->e.getId() == instalacao.getId())
						.iterator().next();
				newInstalacao = loadIndicadorRiscoSaudeAmbientalInstalacoes(instalacao,newInstalacao);
			}
		}
		return this;
	}
	
	private Instalacao loadIndicadorRiscoSaudeAmbientalInstalacoes(Instalacao origem,Instalacao destino) {
		if(origem.getIndicadorRiscoSaudeAmbientalInstalacoes() != null) {
			destino.setIndicadorRiscoSaudeAmbientalInstalacoes(IndicadorRiscoSaudeAmbientalInstalacaoBuilder
					.newInstance(origem.getIndicadorRiscoSaudeAmbientalInstalacoes()).getEntityList());
		}
		return destino;
	}

	@Override
	public Instalacao cloneFromFilter(InstalacaoFilter filter) {
		return null;
	}

}
