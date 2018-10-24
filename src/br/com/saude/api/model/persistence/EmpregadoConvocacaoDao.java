package br.com.saude.api.model.persistence;

import java.util.function.Function;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.filter.EmpregadoConvocacaoFilter;
import br.com.saude.api.model.entity.po.Convocacao;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.EmpregadoConvocacao;
import br.com.saude.api.model.entity.po.Exame;

public class EmpregadoConvocacaoDao extends GenericDao<EmpregadoConvocacao> {

	private static EmpregadoConvocacaoDao instance;
	
	private EmpregadoConvocacaoDao() {
		super();
	}
	
	public static EmpregadoConvocacaoDao getInstance() {
		if(instance == null)
			instance = new EmpregadoConvocacaoDao();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoad = eC -> {
			eC = loadConvocacao(eC);
			eC = loadEmpregado(eC);
			return eC;
		};
		
		this.functionLoadAll = eC -> {
			eC = this.functionLoad.apply(eC);
			eC = loadEmpregadoConvocacaoExames(eC);
			return eC;
		};
		
		this.functionBeforeSave = pair -> {
			EmpregadoConvocacao eC = pair.getValue0();
			Session session = pair.getValue1();
			
			eC.getEmpregadoConvocacaoExames().forEach(ex->{
				ex.setExame(session.get(Exame.class, ex.getExame().getId()));
			});
			
			return eC;
		};
	}
	
	@Override
	protected PagedList<EmpregadoConvocacao> getList(GenericExampleBuilder<?, ?> exampleBuilder,
			Function<EmpregadoConvocacao, EmpregadoConvocacao> function) throws Exception { 
		return super.getList(exampleBuilder, this.functionLoad);
	}
	
	private EmpregadoConvocacao loadConvocacao(EmpregadoConvocacao eC) {
		if(eC.getConvocacao() != null) {
			Convocacao convocacao = (Convocacao) Hibernate.unproxy(eC.getConvocacao());
			eC.setConvocacao(convocacao);
		}
		return eC;
	}
	
	private EmpregadoConvocacao loadEmpregado(EmpregadoConvocacao eC) {
		if(eC.getEmpregado() != null) {

			Object empregado = Hibernate.unproxy(eC.getEmpregado());
			eC.setEmpregado(EmpregadoDao.getInstance().getFunctionLoad().apply((Empregado) empregado));
		}
		return eC;
	}
	
	private EmpregadoConvocacao loadEmpregadoConvocacaoExames(EmpregadoConvocacao eC) {
		if(eC.getEmpregadoConvocacaoExames() != null)
			Hibernate.initialize(eC.getEmpregadoConvocacaoExames());
		
		return eC;
	}
	
	public EmpregadoConvocacao getByIdLoadAll(Object id) throws Exception {
		return super.getById(id, this.functionLoadAll);
	}
	
	public PagedList<EmpregadoConvocacao> getListFunctionLoad(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder, this.functionLoad);
	}
	
	public PagedList<EmpregadoConvocacao> getListFunctionLoadAll(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder, this.functionLoadAll);
	}
	
	protected Function<EmpregadoConvocacao,EmpregadoConvocacao> getFunctionLoad(){
		return this.functionLoad;
	}
	
	@Override
	protected Criteria finishCriteria(Criteria criteria, GenericExampleBuilder<?, ?> exampleBuilder) {
		EmpregadoConvocacaoFilter filter = (EmpregadoConvocacaoFilter)exampleBuilder.getFilter();
		
		if(filter.getEmpregado() != null && filter.getEmpregado().getGerencia() != null) {
			if(filter.getEmpregado().getGerencia().getCodigoCompleto() != null) {
				String[] gerencias = filter.getEmpregado().getGerencia().getCodigoCompleto().split("/");
				
				criteria.createAlias("empregado.gerencia", "gerencia0", JoinType.LEFT_OUTER_JOIN);
				criteria.createAlias("gerencia0.gerencia", "gerencia1", JoinType.LEFT_OUTER_JOIN);
				criteria.createAlias("gerencia1.gerencia", "gerencia2", JoinType.LEFT_OUTER_JOIN);
				criteria.createAlias("gerencia2.gerencia", "gerencia3", JoinType.LEFT_OUTER_JOIN);
				
				Criterion or = null;
				int i = 0;
				for(int x = gerencias.length - 1; x <= 3; x++) {
					Criterion and = null;
					for(int y = i; y < gerencias.length + i; y++) {
						if(and == null) {
							and = Restrictions.ilike("gerencia"+y+".codigo",Helper.filterLike(gerencias[x-y]));
						}else {
							and = Restrictions.and(and, Restrictions.ilike("gerencia"+y+".codigo",Helper.filterLike(gerencias[x-y])));
						}
					}
					
					if(or == null) {
						or = and;
					}else {
						or = Restrictions.or(or, and);
					}
					i++;
				}
				
				criteria.add(or);
			}
		}
		
		return criteria;
	}
}
