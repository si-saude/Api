package br.com.saude.api.model.creation.builder.example;

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
	
	private void addNome() {
		if(this.filter.getNome()!= null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
	
	private void addCpf() {
		if(this.filter.getCpf()!= null)
			this.entity.setCpf(Helper.filterLike(this.filter.getCpf()));
	}
	
	private void addDataNascimento() {
		this.addData("dataNascimento", this.filter.getDataNascimento());
	}
	
	private void addChave() {
		if(this.filter.getChave()!= null)
			this.entity.setChave(Helper.filterLike(this.filter.getChave()));
	}
	
	private void addMatricula() {
		if(this.filter.getMatricula()!= null)
			this.entity.setMatricula(Helper.filterLike(this.filter.getMatricula()));
	}
	
	private void addRg() {
		if(this.filter.getRg()!= null)
			this.entity.setRg(Helper.filterLike(this.filter.getRg()));
	}
	
	private void addSexo() {
		if(this.filter.getSexo()!= null)
			this.entity.setSexo(this.filter.getSexo());
	}
	
	private void addRamal() {
		if(this.filter.getRamal()!= null)
			this.entity.setRamal(Helper.filterLike(this.filter.getRamal()));
	}
	
	private void addStatus() {
		if(this.filter.getStatus()!= null)
			this.entity.setStatus(Helper.filterLike(this.filter.getStatus()));
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

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addNome();
		addCpf();
		addDataNascimento();
		addChave();
		addMatricula();
		addRg();
		addSexo();
		addRamal();
		addStatus();
		addBase();
		addCargo();
		addFuncao();
		addGhe();
		addGhee();
		addRegime();
	}

	@Override
	protected void createExampleSelectList() {
		
	}
}
