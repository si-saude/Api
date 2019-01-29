package br.com.saude.api.model.creation.builder.example;

import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.model.entity.filter.EmpregadoConvocacaoExameFilter;
import br.com.saude.api.model.entity.po.EmpregadoConvocacaoExame;

public class EmpregadoConvocacaoExameExampleBuilder 
	extends GenericExampleBuilder<EmpregadoConvocacaoExame, EmpregadoConvocacaoExameFilter> {

	public static EmpregadoConvocacaoExameExampleBuilder newInstance(EmpregadoConvocacaoExameFilter filter) {
		return new EmpregadoConvocacaoExameExampleBuilder(filter);
	}
	
	private EmpregadoConvocacaoExameExampleBuilder(EmpregadoConvocacaoExameFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addEmpregadoConvocacao();
		addExame();
		addRealizacao();
		addRecebimento();
		addAuditoria();
		addExigeRelatorio();
		addConforme();
		addOpcional();
		addImportado();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		
	}
	
	private void addEmpregadoConvocacao() throws InstantiationException, IllegalAccessException {
		if(this.filter.getEmpregadoConvocacao()!=null) {
			CriteriaExample criteriaExample = EmpregadoConvocacaoExampleBuilder
					.newInstance(this.filter.getEmpregadoConvocacao()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("empregadoConvocacao", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addExame() throws InstantiationException, IllegalAccessException {
		if(this.filter.getExame()!=null) {
			CriteriaExample criteriaExample = ExameExampleBuilder
					.newInstance(this.filter.getExame()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("exame", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addRealizacao() {
		this.addData("realizacao", this.filter.getRealizacao());
	}
	
	private void addRecebimento() {
		this.addData("recebimento", this.filter.getRecebimento());
	}
	
	private void addAuditoria() {
		this.addData("auditoria", this.filter.getAuditoria());
	}
	
	private void addExigeRelatorio() {
		this.entity.setExigeRelatorio(this.addBoolean("inativo", this.filter.getExigeRelatorio()));
	}
	
	private void addConforme() {
		this.entity.setConforme(this.addBoolean("inativo", this.filter.getConforme()));
	}
	
	private void addOpcional() {
		this.entity.setOpcional(this.addBoolean("opcional", this.filter.getOpcional()));
	}
	
	private void addImportado() {
		this.entity.setImportado(this.addBoolean("importado", this.filter.getImportado()));
	}
}
