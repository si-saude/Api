package br.com.saude.api.model.creation.builder.example;


import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.ProfissionalFilter;
import br.com.saude.api.model.entity.po.Profissional;

public class ProfissionalExampleBuilder extends GenericExampleBuilder<Profissional,ProfissionalFilter> {

	public static ProfissionalExampleBuilder newInstance(ProfissionalFilter filter) {
		return new ProfissionalExampleBuilder(filter);
	}
	
	private ProfissionalExampleBuilder(ProfissionalFilter filter) {
		super(filter);
	}
	
	private void addNome() {
		if(this.filter.getNome() != null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
	
	private void addMatricula() {
		if(this.filter.getMatricula() != null)
			this.entity.setMatricula(Helper.filterLike(this.filter.getMatricula()));
	}
	
	private void addChave() {
		if(this.filter.getChave() != null)
			this.entity.setChave(Helper.filterLike(this.filter.getChave()));
	}
	
	private void addRamal() {
		if(this.filter.getRamal() != null)
			this.entity.setRamal(Helper.filterLike(this.filter.getRamal()));
	}
	
	private void addMi() {
		if(this.filter.getMi() != null)
			this.entity.setMi(Helper.filterLike(this.filter.getMi()));
	}
	
	private void addDataNascimento() {
		this.addData("dataNascimento", this.filter.getDataNascimento());
	}
	
	private void addDataAso() {
		this.addData("dataAso", this.filter.getDataAso());
	}
	
	private void addEquipe() throws InstantiationException, IllegalAccessException {
		if(this.filter.getEquipe()!=null) {
			CriteriaExample criteriaExample = EquipeExampleBuilder
					.newInstance(this.filter.getEquipe()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("equipe", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addLocalizacao() throws InstantiationException, IllegalAccessException {
		if(this.filter.getLocalizacao()!=null) {
			CriteriaExample criteriaExample = LocalizacaoExampleBuilder
					.newInstance(this.filter.getLocalizacao()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("localizacao", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addFuncao() throws InstantiationException, IllegalAccessException {
		if(this.filter.getFuncao()!=null) {
			CriteriaExample criteriaExample = FuncaoExampleBuilder
					.newInstance(this.filter.getFuncao()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("funcao", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addCurriculo() throws InstantiationException, IllegalAccessException {
		if(this.filter.getCurriculo()!=null) {
			CriteriaExample criteriaExample = CurriculoExampleBuilder
					.newInstance(this.filter.getCurriculo()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("curriculo", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addEndereco() throws InstantiationException, IllegalAccessException {
		if(this.filter.getEndereco()!=null) {
			CriteriaExample criteriaExample = EnderecoExampleBuilder
					.newInstance(this.filter.getEndereco()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("curriculo", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addProfissionalConselho() throws InstantiationException, IllegalAccessException {
		if(this.filter.getProfissionalConselho()!=null) {
			CriteriaExample criteriaExample = ProfissionalConselhoExampleBuilder
					.newInstance(this.filter.getProfissionalConselho()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("profissionalConselho", criteriaExample, JoinType.INNER_JOIN));
		}
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addChave();
		addDataNascimento();
		addDataAso();
		addMatricula();
		addMi();
		addNome();
		addRamal();
		addEquipe();
		addLocalizacao();
		addFuncao();
		addCurriculo();
		addEndereco();
		addProfissionalConselho();
	}

	@Override
	protected void createExampleSelectList() {
		
	}
}
