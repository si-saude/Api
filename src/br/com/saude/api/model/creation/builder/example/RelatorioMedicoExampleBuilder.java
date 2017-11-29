package br.com.saude.api.model.creation.builder.example;

import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.RelatorioMedicoFilter;
import br.com.saude.api.model.entity.po.RelatorioMedico;

public class RelatorioMedicoExampleBuilder extends GenericExampleBuilder<RelatorioMedico, RelatorioMedicoFilter> {

	public static RelatorioMedicoExampleBuilder newInstance(RelatorioMedicoFilter filter) {
		return new RelatorioMedicoExampleBuilder(filter);
	}
	
	protected RelatorioMedicoExampleBuilder(RelatorioMedicoFilter filter) {
		super(filter);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addMedico();
		addMedicoPrestador();
		addResumo();
		addQuestionamentos();
		addFinalizado();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		addMedico();
		addMedicoPrestador();
		addResumo();
		addQuestionamentos();
		addFinalizado();
	}
	
	private void addMedico() throws InstantiationException, IllegalAccessException {
		if (this.filter.getMedico() != null) {
			CriteriaExample criteriaExample = ProfissionalExampleBuilder
					.newInstance(this.filter.getMedico()).getCriteriaExample();
			this.criterias.add(new Triplet<String, CriteriaExample, JoinType>("profissional", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addMedicoPrestador(){
		if (this.filter.getMedicoPrestador() !=null ) {
			this.entity.setMedicoPrestador(Helper.filterLike(this.filter.getMedicoPrestador()));
		}
	}
	
	private void addResumo() {
		if ( this.filter.getResumo() != null ) {
			this.entity.setResumo(Helper.filterLike(this.filter.getResumo()));
		}
	}
	
	private void addQuestionamentos() {
		if ( this.filter.getQuestionamentos() != null ) {
			this.entity.setQuestionamentos(Helper.filterLike(this.filter.getQuestionamentos()));
		}
	}
	
	private void addFinalizado() { 
		if ( this.filter.getFinalizado() != null ) {
			this.entity.setFinalizado(this.addBoolean("finalizado", this.filter.getFinalizado()));
		}
	}

}
