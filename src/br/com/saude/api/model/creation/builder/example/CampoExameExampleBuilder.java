package br.com.saude.api.model.creation.builder.example;

import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.CampoExameFilter;
import br.com.saude.api.model.entity.po.CampoExame;

public class CampoExameExampleBuilder extends GenericExampleBuilder<CampoExame,CampoExameFilter> {

	public static CampoExameExampleBuilder newInstance(CampoExameFilter filter) {
		return new CampoExameExampleBuilder(filter);
	}
	
	private CampoExameExampleBuilder(CampoExameFilter filter) {
		super(filter);
	}
	
	private void addNome() {
		if(this.filter.getCodigo() != null)
			this.entity.setCodigo(Helper.filterLike(this.filter.getCodigo()));
	}
	
	private void addTitulo() {
		if(this.filter.getTitulo() != null)
			this.entity.setTitulo(Helper.filterLike(this.filter.getTitulo()));
	}
	
	private void addNumeroLinhas() {
		if(this.filter.getNumeroLinhas() > 0)
			this.entity.setNumeroLinhas(this.filter.getNumeroLinhas());
	}

	private void addExame() throws InstantiationException, IllegalAccessException {
		if(this.filter.getExame()!=null) {
			CriteriaExample criteriaExample = ExameExampleBuilder
					.newInstance(this.filter.getExame()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("exame", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addNome();
		addTitulo();
		addNumeroLinhas();
		addExame();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		addNome();
		addTitulo();
		addNumeroLinhas();
		addExame();
	}

}
