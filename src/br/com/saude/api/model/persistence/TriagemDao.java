package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Equipe;
import br.com.saude.api.model.entity.po.RiscoEmpregado;
import br.com.saude.api.model.entity.po.Triagem;

public class TriagemDao extends GenericDao<Triagem> {

	private static TriagemDao instance;
	
	private TriagemDao() {
		super();
	}
	
	public static TriagemDao getInstance() {
		if(instance == null)
			instance = new TriagemDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoad = triagem -> {
			
			if(triagem.getIndicadorSast().getEquipe() != null)
				triagem.getIndicadorSast().setEquipe(
						(Equipe) Hibernate.unproxy(triagem.getIndicadorSast().getEquipe()));
			
			return triagem;
		};
		this.functionLoadAll = triagem -> {
			if(triagem.getIndicadorSast().getEquipe() != null)
				triagem.getIndicadorSast().setEquipe(
						(Equipe) Hibernate.unproxy(triagem.getIndicadorSast().getEquipe()));
			
			if ( triagem.getRiscoEmpregado() != null )
				triagem.setRiscoEmpregado(
						(RiscoEmpregado) Hibernate.unproxy(triagem.getRiscoEmpregado()));
			
			return triagem;
		};
	}
	
	@Override
	public PagedList<Triagem> getList(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder,this.functionLoadAll);
	}
}
