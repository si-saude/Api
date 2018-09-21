package br.com.saude.api.model.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.filter.AtestadoFilter;
import br.com.saude.api.model.entity.po.Atestado;
import br.com.saude.api.model.entity.po.HistoricoAtestado;

public class AtestadoDao extends GenericDao<Atestado> {
	
	protected Function<Atestado,Atestado> functionLoadRegime;
	protected Function<Atestado,Atestado> functionLoadAgendamento;

	private static AtestadoDao instance;
	
	private AtestadoDao() {
		super();
	}
	
	public static AtestadoDao getInstance() {
		if(instance==null)
			instance = new AtestadoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadRegime = atestado -> {
			atestado = loadRegime(atestado);
			
			return atestado;
		};
		
		this.functionLoadAgendamento = atestado -> {
			atestado = loadAgendamento(atestado);
			
			return atestado;
		};
		
		this.functionLoadAll = atestado -> {
			atestado = loadRegime(atestado);
			atestado = loadAgendamento(atestado);
			atestado = loadExamesConvocacao(atestado);
			atestado = loadHistoricoAtestados(atestado);
			
			return atestado;
		};
	}
		
	private Atestado loadRegime(Atestado atestado) {
		if (atestado.getRegime() != null)
			Hibernate.initialize(atestado.getRegime());
		return atestado;
	}
	
	private Atestado loadAgendamento(Atestado atestado) {
		if (atestado.getAgendamento() != null)
			Hibernate.initialize(atestado.getAgendamento());
		return atestado;
	}
	
	private Atestado loadExamesConvocacao(Atestado atestado) {
		if (atestado.getExamesConvocacao() != null)
			Hibernate.initialize(atestado.getExamesConvocacao());
		return atestado;
	}
	
	private Atestado loadHistoricoAtestados(Atestado atestado) {
		
		if (atestado.getHistoricoAtestados() != null) {
			List<HistoricoAtestado> historicos = new ArrayList<HistoricoAtestado>();
			atestado.getHistoricoAtestados().forEach(h->{
				historicos.add((HistoricoAtestado)Hibernate.unproxy(h));
			});
			atestado.setHistoricoAtestados(historicos);
		}
			
		return atestado;
	}
	
	public Atestado getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
	public PagedList<Atestado> getListRegime(GenericExampleBuilder<Atestado,AtestadoFilter> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder, this.functionLoadRegime);
	}
	
	public PagedList<Atestado> getListAll(GenericExampleBuilder<Atestado,AtestadoFilter> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder, this.functionLoadAll);
	}
}