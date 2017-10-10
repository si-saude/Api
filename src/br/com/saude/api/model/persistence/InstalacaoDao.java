package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.IndicadorRiscoAcidente;
import br.com.saude.api.model.entity.po.IndicadorRiscoAmbiental;
import br.com.saude.api.model.entity.po.IndicadorRiscoErgonomico;
import br.com.saude.api.model.entity.po.IndicadorRiscoSanitario;
import br.com.saude.api.model.entity.po.IndicadorRiscoSaudeAmbiental;
import br.com.saude.api.model.entity.po.Instalacao;

public class InstalacaoDao extends GenericDao<Instalacao> {

	private static InstalacaoDao instance;
	
	private InstalacaoDao() {
		super();
	}
	
	public static InstalacaoDao getInstance() {
		if(instance==null)
			instance = new InstalacaoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = instalacao -> {
			
			if(instalacao.getIndicadorRiscoAcidenteInstalacoes()!=null)
				Hibernate.initialize(instalacao.getIndicadorRiscoAcidenteInstalacoes());
			
			if(instalacao.getIndicadorRiscoAmbientalInstalacoes()!=null)
				Hibernate.initialize(instalacao.getIndicadorRiscoAmbientalInstalacoes());
			
			if(instalacao.getIndicadorRiscoErgonomicoInstalacoes()!=null)
				Hibernate.initialize(instalacao.getIndicadorRiscoErgonomicoInstalacoes());
			
			if(instalacao.getIndicadorRiscoSanitarioInstalacoes()!=null)
				Hibernate.initialize(instalacao.getIndicadorRiscoSanitarioInstalacoes());
			
			if(instalacao.getIndicadorRiscoSaudeAmbientalInstalacoes()!=null)
				Hibernate.initialize(instalacao.getIndicadorRiscoSaudeAmbientalInstalacoes());
			
			return instalacao;
		};
		
		this.functionBeforeSave = pair -> {
			Instalacao instalacao = pair.getValue0();
			Session session = pair.getValue1();
			
			//CARREGAR OS INDICADORES
			if(instalacao.getIndicadorRiscoAcidenteInstalacoes() != null)
				for(int i=0; i < instalacao.getIndicadorRiscoAcidenteInstalacoes().size(); i++)
					instalacao.getIndicadorRiscoAcidenteInstalacoes().get(i)
						.setIndicadorRisco(session.get(IndicadorRiscoAcidente.class,
								instalacao.getIndicadorRiscoAcidenteInstalacoes().get(i).getId()));
			
			if(instalacao.getIndicadorRiscoAmbientalInstalacoes() != null)
				for(int i=0; i < instalacao.getIndicadorRiscoAmbientalInstalacoes().size(); i++)
					instalacao.getIndicadorRiscoAmbientalInstalacoes().get(i)
						.setIndicadorRisco(session.get(IndicadorRiscoAmbiental.class,
								instalacao.getIndicadorRiscoAmbientalInstalacoes().get(i).getId()));
			
			if(instalacao.getIndicadorRiscoErgonomicoInstalacoes() != null)
				for(int i=0; i < instalacao.getIndicadorRiscoErgonomicoInstalacoes().size(); i++)
					instalacao.getIndicadorRiscoErgonomicoInstalacoes().get(i)
						.setIndicadorRisco(session.get(IndicadorRiscoErgonomico.class,
								instalacao.getIndicadorRiscoErgonomicoInstalacoes().get(i).getId()));
			
			if(instalacao.getIndicadorRiscoSanitarioInstalacoes() != null)
				for(int i=0; i < instalacao.getIndicadorRiscoSanitarioInstalacoes().size(); i++)
					instalacao.getIndicadorRiscoSanitarioInstalacoes().get(i)
						.setIndicadorRisco(session.get(IndicadorRiscoSanitario.class,
								instalacao.getIndicadorRiscoSanitarioInstalacoes().get(i).getId()));
			
			if(instalacao.getIndicadorRiscoSaudeAmbientalInstalacoes() != null)
				for(int i=0; i < instalacao.getIndicadorRiscoSaudeAmbientalInstalacoes().size(); i++)
					instalacao.getIndicadorRiscoSaudeAmbientalInstalacoes().get(i)
						.setIndicadorRisco(session.get(IndicadorRiscoSaudeAmbiental.class,
								instalacao.getIndicadorRiscoSaudeAmbientalInstalacoes().get(i).getId()));
			
			return instalacao;
		};
	}
	
	@Override
	public Instalacao getById(Object id) throws Exception {
		return super.getById(id, this.functionLoadAll);
	}
}
