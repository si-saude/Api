package br.com.saude.api.model.creation.builder.example;

import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.IndicadorSastFilter;
import br.com.saude.api.model.entity.po.IndicadorSast;

public class IndicadorSastExampleBuilder  extends GenericExampleBuilder<IndicadorSast,IndicadorSastFilter>{

	protected IndicadorSastExampleBuilder(IndicadorSastFilter filter) {
		super(filter);
	}
	
	public static IndicadorSastExampleBuilder newInstance(IndicadorSastFilter filter) {
		return new IndicadorSastExampleBuilder(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addNome();
		addIndice0();
		addIndice1();
		addIndice2();
		addIndice3();
		addIndice4();
		addObrigatorio();
		addInativo();
		addCodigo();
		addCodigoExcludente();
		addEquipe();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
	}
	
	private void addNome() {
		if(this.filter.getNome()!= null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
	
	private void addIndice0() {
		if(this.filter.getIndice0()!= null)
			this.entity.setIndice0(Helper.filterLike(this.filter.getIndice0()));
	}
	
	private void addIndice1() {
		if(this.filter.getIndice1()!= null)
			this.entity.setIndice1(Helper.filterLike(this.filter.getIndice1()));
	}
	
	private void addIndice2() {
		if(this.filter.getIndice2()!= null)
			this.entity.setIndice2(Helper.filterLike(this.filter.getIndice2()));
	}
	
	private void addIndice3() {
		if(this.filter.getIndice3()!= null)
			this.entity.setIndice3(Helper.filterLike(this.filter.getIndice3()));
	}
	
	private void addIndice4() {
		if(this.filter.getIndice4()!= null)
			this.entity.setIndice4(Helper.filterLike(this.filter.getIndice4()));
	}
	
	private void addObrigatorio() {
		this.entity.setObrigatorio(this.addBoolean("obrigatorio", this.filter.getObrigatorio()));
	}
	
	private void addInativo() {
		this.entity.setInativo(this.addBoolean("inativo", this.filter.getInativo()));
	}
	
	private void addCodigo() {
		if(this.filter.getCodigo()!= null)
			this.entity.setCodigo(Helper.filterLike(this.filter.getCodigo()));
	}
	
	private void addCodigoExcludente() {
		if(this.filter.getCodigoExcludente()!= null)
			this.entity.setCodigoExcludente(Helper.filterLike(this.filter.getCodigoExcludente()));
	}
	
	private void addEquipe() throws InstantiationException, IllegalAccessException {
		if(this.filter.getEquipe()!=null) {
			CriteriaExample criteriaExample = EquipeExampleBuilder
					.newInstance(this.filter.getEquipe()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("equipe", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
}
