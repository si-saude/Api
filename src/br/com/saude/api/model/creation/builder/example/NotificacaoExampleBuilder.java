package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.NotificacaoFilter;
import br.com.saude.api.model.entity.po.Notificacao;

public class NotificacaoExampleBuilder extends GenericExampleBuilder<Notificacao, NotificacaoFilter> {

	public static NotificacaoExampleBuilder newInstance(NotificacaoFilter filter) {
		return new NotificacaoExampleBuilder(filter);
	}
	
	private NotificacaoExampleBuilder(NotificacaoFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addId();
		addDescricao();
		addUsuario();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		
	}
	
	private void addId() {
		if(this.filter.getId() > 0)
			this.criterions.add(Restrictions.eq("id", (int)this.filter.getId()));
	}
	
	private void addDescricao() {
		if(this.filter.getDescricao()!=null)
			this.entity.setDescricao(Helper.filterLike(this.filter.getDescricao()));
	}
	
	private void addUsuario() throws InstantiationException, IllegalAccessException {
		if(this.filter.getUsuario()!=null) {
			CriteriaExample criteriaExample = UsuarioExampleBuilder
					.newInstance(this.filter.getUsuario()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("usuario", criteriaExample, JoinType.INNER_JOIN));
		}
	}
}
