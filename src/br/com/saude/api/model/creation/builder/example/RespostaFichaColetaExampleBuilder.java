package br.com.saude.api.model.creation.builder.example;

import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.RespostaFichaColetaFilter;
import br.com.saude.api.model.entity.po.RespostaFichaColeta;

public class RespostaFichaColetaExampleBuilder extends GenericExampleBuilder<RespostaFichaColeta, RespostaFichaColetaFilter> {
	
	public static RespostaFichaColetaExampleBuilder newInstance(RespostaFichaColetaFilter filter) {
		return new RespostaFichaColetaExampleBuilder(filter);
	}
	
	private RespostaFichaColetaExampleBuilder(RespostaFichaColetaFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addConteudo();
		addFicha();
		addPergunta();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {}
	
	private void addConteudo() {
		if(this.filter.getConteudo() != null)
			this.entity.setConteudo(Helper.filterLike(this.filter.getConteudo()));
	}
	
	private void addFicha() throws InstantiationException, IllegalAccessException {
		if(this.filter.getFicha()!=null) {
			CriteriaExample criteriaExample = FichaColetaExampleBuilder
					.newInstance(this.filter.getFicha()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("ficha", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addPergunta() throws InstantiationException, IllegalAccessException {
		if(this.filter.getPergunta()!=null) {
			CriteriaExample criteriaExample = PerguntaFichaColetaExampleBuilder
					.newInstance(this.filter.getPergunta()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("pergunta", criteriaExample, JoinType.INNER_JOIN));
		}
	}
}
