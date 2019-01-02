package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.po.Empregado;

public class EmpregadoExampleBuilder extends GenericExampleBuilder<Empregado,EmpregadoFilter> {
	
	public static EmpregadoExampleBuilder newInstance(EmpregadoFilter filter) {
		return new EmpregadoExampleBuilder(filter);
	}
	
	private EmpregadoExampleBuilder(EmpregadoFilter filter) {
		super(filter);
	}
	
	private void addChave() {
		if(this.filter.getChave()!= null)
			this.entity.setChave(Helper.filterLike(this.filter.getChave()));
	}
	
	private void addChaveEq() {
		if(this.filter.getChave()!= null)
			this.entity.setChave(this.filter.getChave());
	}
	
	private void addMatricula() {
		if(this.filter.getMatricula()!= null)
			this.entity.setMatricula(Helper.filterLike(this.filter.getMatricula()));
	}
	
	private void addMatriculaEq() {
		if(this.filter.getMatricula()!= null)
			this.entity.setMatricula(this.filter.getMatricula());
	}
	
	private void addEstadoCivil() {
		if(this.filter.getEstadoCivil()!= null)
			this.entity.setEstadoCivil(this.filter.getEstadoCivil());
	}
	
	private void addEscolaridade() {
		if(this.filter.getEscolaridade()!= null)
			this.entity.setEscolaridade(this.filter.getEscolaridade());
	}
	
	private void addRamal() {
		if(this.filter.getRamal()!= null)
			this.entity.setRamal(Helper.filterLike(this.filter.getRamal()));
	}
	
	private void addStatus() {
		if(this.filter.getStatus()!= null)
			this.entity.setStatus(Helper.filterLike(this.filter.getStatus()));
	}
	
	private void addVinculo() {
		if(this.filter.getVinculo()!= null)
			this.entity.setVinculo(Helper.filterLike(this.filter.getVinculo()));
	}
	
	private void addPis() {
		if(this.filter.getPis()!= null)
			this.entity.setPis(Helper.filterLike(this.filter.getPis()));
	}
	
	private void addDataAdmissao() {
		this.addData("dataAdmissao", this.filter.getDataAdmissao());
	}
	
	private void addPessoa() throws InstantiationException, IllegalAccessException {
		if(this.filter.getPessoa()!=null) {
			CriteriaExample criteriaExample = PessoaExampleBuilder
					.newInstance(this.filter.getPessoa()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("pessoa", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addCargo() throws InstantiationException, IllegalAccessException {
		if(this.filter.getCargo()!=null) {
			CriteriaExample criteriaExample = CargoExampleBuilder
					.newInstance(this.filter.getCargo()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("cargo", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addFuncao() throws InstantiationException, IllegalAccessException {
		if(this.filter.getFuncao()!=null) {
			CriteriaExample criteriaExample = FuncaoExampleBuilder
					.newInstance(this.filter.getFuncao()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("funcao", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addRegime() throws InstantiationException, IllegalAccessException {
		if(this.filter.getRegime()!=null) {
			CriteriaExample criteriaExample = RegimeExampleBuilder
					.newInstance(this.filter.getRegime()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("regime", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addBase() throws InstantiationException, IllegalAccessException {
		if(this.filter.getBase()!=null) {
			CriteriaExample criteriaExample = BaseExampleBuilder
					.newInstance(this.filter.getBase()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("base", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addGhe() throws InstantiationException, IllegalAccessException {
		if(this.filter.getGhe()!=null) {
			CriteriaExample criteriaExample = GheExampleBuilder
					.newInstance(this.filter.getGhe()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("ghe", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addGhee() throws InstantiationException, IllegalAccessException {
		if(this.filter.getGhee()!=null) {
			CriteriaExample criteriaExample = GheeExampleBuilder
					.newInstance(this.filter.getGhee()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("ghee", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addId() {
		if(this.filter.getId() > 0)
			this.criterions.add(Restrictions.eq("id", (int)this.filter.getId()));
	}
	
	private void addOrChaveMatriculaCpf() {
		this.criterions.add(Restrictions.or(Restrictions.eq("chave", this.filter.getChave()), 
				Restrictions.eq("matricula", this.filter.getMatricula())));	
	}
	
	public GenericExampleBuilder<Empregado, EmpregadoFilter> exampleOrChaveMatriculaCpf()
			throws InstantiationException, IllegalAccessException {
		if(this.filter!=null) {
			initialize();
			createExampleOrChaveMatriculaCpf();
			this.criterions.add(getExample());
		}
		return this;
	}
	
	public GenericExampleBuilder<Empregado, EmpregadoFilter> exampleEq()
			throws InstantiationException, IllegalAccessException {
		if(this.filter!=null) {
			initialize();
			createExampleEq();
			this.criterions.add(getExample());
		}
		return this;
	}
	
	protected void createExampleEq() throws InstantiationException, IllegalAccessException {
		addId();
		addChaveEq();
		addMatriculaEq();
		addEstadoCivil();
		addEscolaridade();
		addRamal();
		addStatus();
		addBase();
		addPessoa();
		addCargo();
		addFuncao();
		addGhe();
		addGhee();
		addRegime();
		addPis();
		addDataAdmissao();
		addVinculo();
	}
	
	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addId();
		addChave();
		addMatricula();
		addEstadoCivil();
		addEscolaridade();
		addRamal();
		addStatus();
		addBase();
		addPessoa();
		addCargo();
		addFuncao();
		addGhe();
		addGhee();
		addRegime();
		addPis();
		addDataAdmissao();
		addVinculo();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		addId();
		addChave();
		addMatricula();
		addEstadoCivil();
		addEscolaridade();
		addRamal();
		addStatus();
		addBase();
		addPessoa();
		addCargo();
		addFuncao();
		addGhe();
		addGhee();
		addRegime();
		addVinculo();
	}
	
	protected void createExampleOrChaveMatriculaCpf() throws InstantiationException, IllegalAccessException {
		addOrChaveMatriculaCpf();
	}
}
