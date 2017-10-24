package br.com.saude.api.model.creation.builder.example;

import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.GrupoMonitoramentoFilter;
import br.com.saude.api.model.entity.po.GrupoMonitoramento;

public class GrupoMonitoramentoExampleBuilder 
		extends GenericExampleBuilder<GrupoMonitoramento, GrupoMonitoramentoFilter> {

	public static GrupoMonitoramentoExampleBuilder newInstance(GrupoMonitoramentoFilter filter) {
		return new GrupoMonitoramentoExampleBuilder(filter);
	}
	
	private GrupoMonitoramentoExampleBuilder(GrupoMonitoramentoFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addNome();
		addTipoGrupoMonitoramento();
		addRecorrente();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		
	}

	private void addNome() {
		if(this.filter.getNome() != null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
	
	private void addTipoGrupoMonitoramento() throws InstantiationException, IllegalAccessException {
		if(this.filter.getTipoGrupoMonitoramento()!=null) {
			CriteriaExample criteriaExample = TipoGrupoMonitoramentoExampleBuilder
					.newInstance(this.filter.getTipoGrupoMonitoramento()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("tipoGrupoMonitoramento", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	protected void addRecorrente() {
		this.entity.setRecorrente(this.addBoolean("recorrente", this.filter.getRecorrente()));
	}
}
