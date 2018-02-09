package br.com.saude.api.model.creation.builder.example;

import java.util.function.Function;

import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.model.entity.filter.TarefaFilter;
import br.com.saude.api.model.entity.po.Tarefa;
import br.com.saude.api.util.constant.StatusTarefa;

public class TarefaExampleBuilder extends GenericExampleBuilder<Tarefa, TarefaFilter> {

	private Function<TarefaExampleBuilder,TarefaExampleBuilder> functionStatusNaoConcluidoCancelado;
	
	public static TarefaExampleBuilder newInstance(TarefaFilter filter) {
		return new TarefaExampleBuilder(filter);
	}
	
	private TarefaExampleBuilder(TarefaFilter filter) {
		super(filter);
		
		this.functionStatusNaoConcluidoCancelado = exampleBuilder -> {
			try {
				exampleBuilder.createExampleStatusNaoConcluidoCancelado();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			return exampleBuilder;
		};
	}
	
	public CriteriaExample getCriteriaExampleStatusNaoConcluidoCancelado() throws InstantiationException, IllegalAccessException {
		return super.getCriteriaExample(this.functionStatusNaoConcluidoCancelado);
	}
	
	public GenericExampleBuilder<Tarefa, TarefaFilter> exampleStatusNaoConcluidoCancelado() throws InstantiationException, IllegalAccessException {
		if(this.filter!=null) {
			initialize();
			createExampleStatusNaoConcluidoCancelado();
			this.criterions.add(getExample());
		}
		return this;
	}
	
	public GenericExampleBuilder<Tarefa, TarefaFilter> exampleStatusNaoConcluido() throws InstantiationException, IllegalAccessException {
		if(this.filter!=null) {
			initialize();
			createExampleStatusNaoConcluido();
			this.criterions.add(getExample());
		}
		return this;
	}
	
	protected void createExampleStatusNaoConcluidoCancelado() throws InstantiationException, IllegalAccessException {
		addInicio();
		addFim();
		addAtualizacao();
		addCliente();
		addEquipe();
		addResponsavel();
		addServico();
		addStatusNaoConcluido();
		addStatusNaoCancelado();
	}
	
	protected void createExampleStatusNaoConcluido() throws InstantiationException, IllegalAccessException {
		addInicio();
		addFim();
		addAtualizacao();
		addCliente();
		addEquipe();
		addResponsavel();
		addServico();
		addStatusNaoConcluido();
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addInicio();
		addFim();
		addAtualizacao();
		addStatus();
		addCliente();
		addEquipe();
		addResponsavel();
		addServico();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		
	}
	
	private void addInicio() {
		this.addData("inicio", this.filter.getInicio());
	}
	
	private void addFim() {
		this.addData("fim", this.filter.getFim());
	}
	
	private void addAtualizacao() {
		this.addData("atualizacao", this.filter.getAtualizacao());
	}
	
	private void addStatus() {
		if(this.filter.getStatus() != null) {
			if ( this.filter.getStatus().equals("EXECUCAO") )
				this.entity.setStatus(StatusTarefa.getInstance().EXECUCAO);
			else this.entity.setStatus(this.filter.getStatus());
		}	
	}
	
	private void addStatusNaoConcluido() {
		this.criterions.add(Restrictions.ne("status", StatusTarefa.getInstance().CONCLUIDA));
	}
	
	private void addStatusNaoCancelado() {
		this.criterions.add(Restrictions.ne("status", StatusTarefa.getInstance().CANCELADA));
	}
	
	private void addCliente() throws InstantiationException, IllegalAccessException {
		if(this.filter.getCliente()!=null) {
			CriteriaExample criteriaExample = EmpregadoExampleBuilder
					.newInstance(this.filter.getCliente()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("cliente", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addEquipe() throws InstantiationException, IllegalAccessException {
		if(this.filter.getEquipe()!=null) {
			CriteriaExample criteriaExample = EquipeExampleBuilder
					.newInstance(this.filter.getEquipe()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("equipe", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addServico() throws InstantiationException, IllegalAccessException {
		if(this.filter.getServico()!=null) {
			CriteriaExample criteriaExample = ServicoExampleBuilder
					.newInstance(this.filter.getServico()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("servico", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addResponsavel() throws InstantiationException, IllegalAccessException {
		if(this.filter.getResponsavel()!=null) {
			CriteriaExample criteriaExample = ProfissionalExampleBuilder
					.newInstance(this.filter.getResponsavel()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("responsavel", criteriaExample, JoinType.INNER_JOIN));
		}
	}
}
