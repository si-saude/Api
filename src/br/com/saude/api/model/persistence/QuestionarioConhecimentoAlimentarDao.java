package br.com.saude.api.model.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.ItemIndicadorConhecimentoAlimentar;
import br.com.saude.api.model.entity.po.QuestionarioConhecimentoAlimentar;
import br.com.saude.api.model.entity.po.Tarefa;

public class QuestionarioConhecimentoAlimentarDao extends GenericDao<QuestionarioConhecimentoAlimentar> {
	
	private static QuestionarioConhecimentoAlimentarDao instance;
	
	private QuestionarioConhecimentoAlimentarDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoad = questionario -> {
			questionario = loadTarefa(questionario);
			return questionario;
		};
		this.functionLoadAll = questionario -> {
			questionario = this.functionLoad.apply(questionario);
			questionario = loadRespostas(questionario);
			return questionario;
		};
		
		this.functionAfterSave = pair -> {
			QuestionarioConhecimentoAlimentar questionario = pair.getValue0();
			
			if(questionario.getAtendimento() != null && questionario.getAtendimento().getId() > 0) {
				try {
					StringBuilder queryBuilder = new StringBuilder("UPDATE Atendimento "
							+ "SET questionario_id = :questionario_id "
							+ "WHERE id = :id");
					pair.getValue1().createQuery(queryBuilder.toString())
						.setParameter("questionario_id", questionario.getId())
						.setParameter("id", questionario.getAtendimento().getId())
						.executeUpdate();
				}catch(Exception ex) {
					throw ex;
				}
			}
			
			return questionario;
		};
	}
	
	public static QuestionarioConhecimentoAlimentarDao getInstance() {
		if(instance == null)
			instance = new QuestionarioConhecimentoAlimentarDao();
		return instance;
	}
	
	private QuestionarioConhecimentoAlimentar loadTarefa(QuestionarioConhecimentoAlimentar questionario) {
		if (questionario.getAtendimento().getTarefa()!=null)
			questionario.getAtendimento().setTarefa((Tarefa) Hibernate.unproxy(questionario.getAtendimento().getTarefa()));
		return questionario;
	}
	
	private QuestionarioConhecimentoAlimentar loadRespostas(QuestionarioConhecimentoAlimentar questionario) {
		if (questionario.getRespostas()!=null) {
			Hibernate.initialize(questionario.getRespostas());
			questionario.getRespostas().forEach(r -> {
				List<ItemIndicadorConhecimentoAlimentar> itens = new ArrayList<ItemIndicadorConhecimentoAlimentar>();
				r.getIndicador().getItemIndicadorConhecimentoAlimentares().forEach(i -> {
					itens.add((ItemIndicadorConhecimentoAlimentar) Hibernate.unproxy(i));
				});
				r.getIndicador().setItemIndicadorConhecimentoAlimentares(itens);
			});			
		}
		return questionario;
	}

	@Override
	public QuestionarioConhecimentoAlimentar getById(Object id) throws Exception {
		return super.getById(id, functionLoadAll);
	}
	
	@Override
	public PagedList<QuestionarioConhecimentoAlimentar> getList(GenericExampleBuilder<?, ?> exampleBuilder)
			throws Exception {
		return super.getList(exampleBuilder, this.functionLoad);
	}
}