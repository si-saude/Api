package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.DiagnosticoFilter;
import br.com.saude.api.model.entity.po.Diagnostico;

public class DiagnosticoExampleBuilder extends GenericExampleBuilder<Diagnostico,DiagnosticoFilter> {

	public static DiagnosticoExampleBuilder newInstance(DiagnosticoFilter filter) {
		return new DiagnosticoExampleBuilder(filter);
	}
	
	private DiagnosticoExampleBuilder(DiagnosticoFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addId();
		addCodigo();
		addDescricao();
		addEixo();
		addInativo();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		addId();
		addCodigo();
		addDescricao();
		addEixo();
		addInativo();
	}
	
	private void addCodigo() {
		if(this.filter.getCodigo() != null)
			this.entity.setCodigo(Helper.filterLike(this.filter.getCodigo()));
	}
	
	private void addDescricao() {
		if(this.filter.getDescricao() != null)
			this.entity.setDescricao(Helper.filterLike(this.filter.getDescricao()));
	}
	
	private void addEixo() throws InstantiationException, IllegalAccessException {
		if(this.filter.getEixo()!=null) {
			CriteriaExample criteriaExample = EixoExampleBuilder
					.newInstance(this.filter.getEixo()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("eixo", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	protected void addInativo() {
		this.entity.setInativo(this.addBoolean("inativo", this.filter.getInativo()));
	}

	private void addId() {
		if(this.filter.getId() > 0)
			this.criterions.add(Restrictions.eq("id", (int)this.filter.getId()));
	}
}
