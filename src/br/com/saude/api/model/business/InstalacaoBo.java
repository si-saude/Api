package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.InstalacaoBuilder;
import br.com.saude.api.model.creation.builder.example.InstalacaoExampleBuilder;
import br.com.saude.api.model.entity.filter.InstalacaoFilter;
import br.com.saude.api.model.entity.po.Instalacao;
import br.com.saude.api.model.persistence.InstalacaoDao;

public class InstalacaoBo extends GenericBo<Instalacao, InstalacaoFilter, 
						InstalacaoDao, InstalacaoBuilder, InstalacaoExampleBuilder> {

	private static InstalacaoBo instance;
	
	private InstalacaoBo() {
		super();
	}
	
	public static InstalacaoBo getInstance() {
		if(instance == null)
			instance = new InstalacaoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadIndicadorRiscoAcidenteInstalacoes()
						.loadIndicadorRiscoAmbientalInstalacoes()
						.loadIndicadorRiscoErgonomicoInstalacoes()
						.loadIndicadorRiscoSanitarioInstalacoes()
						.loadIndicadorRiscoSaudeAmbientalInstalacoes();
		};
	}
	
	@Override
	public Instalacao getById(Object id) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return this.getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}
	
	@Override
	public PagedList<Instalacao> getList(InstalacaoFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return this.getList(getDao().getListFunctionLoad(getExampleBuilder(filter).example()), this.functionLoad);
	}
	
	@Override
	public Instalacao save(Instalacao instalacao) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		
		//SETAR A INSTALAÇÃO NOS INDICADORES
		instalacao.getIndicadorRiscoAcidenteInstalacoes()
		.forEach(i->i.setInstalacao(instalacao));
		
		instalacao.getIndicadorRiscoAmbientalInstalacoes()
		.forEach(i->i.setInstalacao(instalacao));
		
		instalacao.getIndicadorRiscoErgonomicoInstalacoes()
		.forEach(i->i.setInstalacao(instalacao));
		
		instalacao.getIndicadorRiscoSanitarioInstalacoes()
		.forEach(i->i.setInstalacao(instalacao));
		
		instalacao.getIndicadorRiscoSaudeAmbientalInstalacoes()
		.forEach(i->i.setInstalacao(instalacao));
		
		return super.save(instalacao);
	}
}
