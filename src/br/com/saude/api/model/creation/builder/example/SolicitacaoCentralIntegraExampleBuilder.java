package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.SolicitacaoCentralIntegraFilter;
import br.com.saude.api.model.entity.filter.TipoSolicitacaoFilter;
import br.com.saude.api.model.entity.po.SolicitacaoCentralIntegra;

public class SolicitacaoCentralIntegraExampleBuilder extends GenericExampleBuilder<SolicitacaoCentralIntegra, SolicitacaoCentralIntegraFilter> {

	public static SolicitacaoCentralIntegraExampleBuilder newInstance(SolicitacaoCentralIntegraFilter filter) {
		return new SolicitacaoCentralIntegraExampleBuilder(filter);
	}
	
	private SolicitacaoCentralIntegraExampleBuilder(SolicitacaoCentralIntegraFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addId();
		addTipoSolicitacao();
		addStatus();
		addAbertura();
		addPrazo();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		
	}
	
	private void addId() {
		if(this.filter.getId() > 0)
			this.criterions.add(Restrictions.eq("id", (int)this.filter.getId()));
	}
	
	private void addTipoSolicitacao() throws InstantiationException, IllegalAccessException {
		if(this.filter.getTipoSolicitacao()!=null) {
			CriteriaExample criteriaExample = TipoSolicitacaoExampleBuilder
					.newInstance(this.filter.getTipoSolicitacao()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("tipoSolicitacao", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addStatus() {
		if(this.filter.getStatus()!= null)
			this.entity.setStatus(Helper.filterLike(this.filter.getStatus()));
	}
	
	private void addAbertura() {
		this.addData("abertura", this.filter.getAbertura());
	}
	
	private void addPrazo() {
		this.addData("prazo", this.filter.getPrazo());
	}
	
}
