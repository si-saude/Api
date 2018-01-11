package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.RegraAtendimentoBuilder;
import br.com.saude.api.model.creation.builder.example.RegraAtendimentoExampleBuilder;
import br.com.saude.api.model.entity.filter.RegraAtendimentoFilter;
import br.com.saude.api.model.entity.po.RegraAtendimento;
import br.com.saude.api.model.persistence.RegraAtendimentoDao;

public class RegraAtendimentoBo extends GenericBo<RegraAtendimento, RegraAtendimentoFilter,
RegraAtendimentoDao, RegraAtendimentoBuilder, RegraAtendimentoExampleBuilder> {

	private static RegraAtendimentoBo instance;
	
	private RegraAtendimentoBo() {
		super();
	}
	
	public static RegraAtendimentoBo getInstance() {
		if(instance == null)
			instance = new RegraAtendimentoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
	
		this.functionLoadAll = builder -> {
			return builder.loadRegraAtendimentoEquipes();
		};
	}
	
	@Override
	public RegraAtendimento getById(Object id) throws Exception {
		return super.getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}
	
	@Override
	public PagedList<RegraAtendimento> getList(RegraAtendimentoFilter filter) throws Exception {
		return super.getList(getDao().getListLoadAll(getExampleBuilder(filter).example()), 
				this.functionLoadAll);
	}

	@Override
	public RegraAtendimento save(RegraAtendimento regraAtendimento) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {

		if ( regraAtendimento.getRegraAtendimentoEquipes() != null ) {
			regraAtendimento.getRegraAtendimentoEquipes().forEach(rAE -> {
				rAE.setRegraAtendimento(regraAtendimento);
				if ( rAE.getRegraAtendimentoEquipeRequisitos() != null ) {
					rAE.getRegraAtendimentoEquipeRequisitos().forEach(rAER -> {
						rAER.setRegraAtendimentoEquipe(rAE);
					});
				}
			});
		}

		return super.save(regraAtendimento);
	}
}
