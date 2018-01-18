package br.com.saude.api.model.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Atendimento;
import br.com.saude.api.model.entity.po.FilaAtendimentoOcupacionalAtualizacao;
import br.com.saude.api.model.entity.po.Localizacao;
import br.com.saude.api.model.entity.po.Tarefa;
import br.com.saude.api.model.entity.po.Aso;

public class AtendimentoDao extends GenericDao<Atendimento> {

	private static AtendimentoDao instance;
	
	private AtendimentoDao() {
		super();
	}
	
	public static AtendimentoDao getInstance() {
		if(instance == null)
			instance = new AtendimentoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = atendimento -> {
			if(atendimento.getTarefa() != null)
				atendimento.setTarefa((Tarefa) Hibernate.unproxy(atendimento.getTarefa()));
			
			if(atendimento.getAso() != null)
				atendimento.setAso((Aso)Hibernate.unproxy(atendimento.getAso()));
			
			if(atendimento.getFilaAtendimentoOcupacional().getLocalizacao() != null)
				atendimento.getFilaAtendimentoOcupacional().setLocalizacao(
						(Localizacao) Hibernate.unproxy(atendimento.getFilaAtendimentoOcupacional().getLocalizacao()));
			
			if(atendimento.getFilaAtendimentoOcupacional().getAtualizacoes() != null) {
				List<FilaAtendimentoOcupacionalAtualizacao> list = new ArrayList<FilaAtendimentoOcupacionalAtualizacao>();
				atendimento.getFilaAtendimentoOcupacional().getAtualizacoes().forEach(a -> {
					list.add((FilaAtendimentoOcupacionalAtualizacao) Hibernate.unproxy(a));
				});
				atendimento.getFilaAtendimentoOcupacional().setAtualizacoes(list);
			}				
			
			if(atendimento.getFilaEsperaOcupacional().getLocalizacao() != null)
				atendimento.getFilaEsperaOcupacional().setLocalizacao(
						(Localizacao) Hibernate.unproxy(atendimento.getFilaEsperaOcupacional().getLocalizacao()));
			
			return atendimento;
		};
		
		this.functionBeforeSave = pair -> {
			Atendimento atendimento = pair.getValue0();
			
			if(atendimento.getFilaAtendimentoOcupacional().getAtualizacoes() != null)
				atendimento.getFilaAtendimentoOcupacional().getAtualizacoes().forEach(a->
					a.setFila(atendimento.getFilaAtendimentoOcupacional()));
			
			return atendimento;
		};
	}
	
	public Atendimento getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
	public PagedList<Atendimento> getListLoadAll(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder,this.functionLoadAll);
	}
}
