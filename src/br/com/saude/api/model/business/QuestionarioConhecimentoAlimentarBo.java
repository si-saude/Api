package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.IndicadorConhecimentoAlimentarBuilder;
import br.com.saude.api.model.creation.builder.entity.QuestionarioConhecimentoAlimentarBuilder;
import br.com.saude.api.model.creation.builder.example.QuestionarioConhecimentoAlimentarExampleBuilder;
import br.com.saude.api.model.entity.filter.IndicadorConhecimentoAlimentarFilter;
import br.com.saude.api.model.entity.filter.QuestionarioConhecimentoAlimentarFilter;
import br.com.saude.api.model.entity.po.Atendimento;
import br.com.saude.api.model.entity.po.IndicadorConhecimentoAlimentar;
import br.com.saude.api.model.entity.po.QuestionarioConhecimentoAlimentar;
import br.com.saude.api.model.entity.po.RespostaQuestionarioConhecimentoAlimentar;
import br.com.saude.api.model.persistence.QuestionarioConhecimentoAlimentarDao;

public class QuestionarioConhecimentoAlimentarBo 
	extends GenericBo<QuestionarioConhecimentoAlimentar, QuestionarioConhecimentoAlimentarFilter, QuestionarioConhecimentoAlimentarDao, 
	QuestionarioConhecimentoAlimentarBuilder, QuestionarioConhecimentoAlimentarExampleBuilder> {

	private static QuestionarioConhecimentoAlimentarBo instance;
	
	private QuestionarioConhecimentoAlimentarBo() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			builder = builder.loadRespostas();
			return builder;
		};
	}
	
	public static QuestionarioConhecimentoAlimentarBo getInstance() {
		if(instance==null)
			instance = new QuestionarioConhecimentoAlimentarBo();
		return instance;
	}
	
	@Override
	public QuestionarioConhecimentoAlimentar save(QuestionarioConhecimentoAlimentar entity)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, Exception {
	
		if ( entity.getRespostas() != null )
			entity.getRespostas().forEach(r -> r.setQuestionario(entity));
		
		return super.save(entity);
	}
	
	@Override
	public QuestionarioConhecimentoAlimentar getById(Object id) throws Exception {
		return getByEntity(getDao().getById(id),functionLoadAll);
	}
	
	public QuestionarioConhecimentoAlimentar constructQuestionario(int id) throws Exception {
		QuestionarioConhecimentoAlimentar questionario = new QuestionarioConhecimentoAlimentar();
		questionario.setAtendimento(new Atendimento());
		questionario.getAtendimento().setId(id);
		
		IndicadorConhecimentoAlimentarFilter indicadorFilter = new IndicadorConhecimentoAlimentarFilter();
		indicadorFilter.setInativo(new BooleanFilter());
		indicadorFilter.getInativo().setValue(2);
		indicadorFilter.setPageNumber(1);
		indicadorFilter.setPageSize(Integer.MAX_VALUE);
		List<IndicadorConhecimentoAlimentar> indicadorConhecimentoAlimentares = 
				(List<IndicadorConhecimentoAlimentar>) IndicadorConhecimentoAlimentarBuilder.newInstance(
						IndicadorConhecimentoAlimentarBo.getInstance().getList(
								indicadorFilter).getList()).loadItemIndicadorConhecimentoAlimentares().getEntityList();
		
		List<RespostaQuestionarioConhecimentoAlimentar> respostas = new ArrayList<RespostaQuestionarioConhecimentoAlimentar>();
		for ( IndicadorConhecimentoAlimentar indicador : indicadorConhecimentoAlimentares ) {
			RespostaQuestionarioConhecimentoAlimentar resposta = new RespostaQuestionarioConhecimentoAlimentar();
			resposta.setIndicador(indicador);
			respostas.add(resposta);
		}
		
		questionario.setRespostas(respostas);
		
		return questionario;
	}
}
