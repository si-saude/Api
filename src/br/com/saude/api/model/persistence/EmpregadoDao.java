package br.com.saude.api.model.persistence;

import java.util.List;

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
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.Instalacao;
import br.com.saude.api.model.entity.po.Telefone;

public class EmpregadoDao extends GenericDao<Empregado>  {
	private static EmpregadoDao instance;
	
	private EmpregadoDao(){
		super();
	}
	
	
	public static EmpregadoDao getInstance() {
		if(instance == null)
			instance = new EmpregadoDao();
		return instance;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	protected void initializeFunctions() {
		this.functionLoad = empregado -> {
			empregado = loadCargo(empregado);
			empregado = loadFuncao(empregado);
			empregado = loadGerencia(empregado);
			return empregado;
		};
		
		this.functionLoadAll = empregado -> {
			empregado = this.functionLoad.apply(empregado);
			empregado = loadBase(empregado);
			empregado = loadGhe(empregado);
			empregado = loadGhee(empregado);
			empregado = loadRegime(empregado);
			empregado = loadInstalacoes(empregado);
			
			return empregado;
		};
		
		this.functionBeforeSave = pair -> {
			Empregado empregado = pair.getValue0();
			Session session = pair.getValue1();
			
			//CARREGA AS INSTALA��ES PARA QUE O HIBERNATE SALVE
			if(empregado.getInstalacoes()!=null)
				for(int i=0; i < empregado.getInstalacoes().size(); i++)
					empregado.getInstalacoes().set(i, 
							session.get(Instalacao.class, empregado.getInstalacoes()
																	.get(i).getId()));
			
			//REMOVE REGISTROS DE TELEFONE �RF�OS
			if(empregado.getId() > 0) {				
				List<Telefone> telefones = (List<Telefone>)session.createCriteria(Telefone.class)
						.createAlias("empregados", "empregado")
						.add(Restrictions.eq("empregado.id", empregado.getId()))
						.list();
				telefones.forEach(t->{
					if(!empregado.getTelefones().contains(t))
						session.remove(t);
				});
			}
			
			return empregado;
		};
	}
	
	@Override
	protected Criteria finishCriteria(Criteria criteria, GenericExampleBuilder<?, ?> empregadoExampleBuilder) {
		EmpregadoFilter filter = (EmpregadoFilter)empregadoExampleBuilder.getFilter();
		
		if(filter.getGerencia() != null && filter.getGerencia().getCodigoCompleto() != null) {
			String[] gerencias = filter.getGerencia().getCodigoCompleto().split("/");
			
			criteria.createAlias("gerencia", "gerencia0", JoinType.LEFT_OUTER_JOIN);
			criteria.createAlias("gerencia0.gerencia", "gerencia1", JoinType.LEFT_OUTER_JOIN);
			criteria.createAlias("gerencia1.gerencia", "gerencia2", JoinType.LEFT_OUTER_JOIN);
			criteria.createAlias("gerencia2.gerencia", "gerencia3", JoinType.LEFT_OUTER_JOIN);
			
			Criterion or = null;
			int i = 0;
			for(int x = gerencias.length - 1; x <= 3; x++) {
				Criterion and = null;
				for(int y = i; y < gerencias.length + i; y++) {
					if(and == null) {
						and = Restrictions.ilike("gerencia"+y,gerencias[x-y]);
					}else {
						and = Restrictions.and(and, Restrictions.ilike("gerencia"+y,Helper.filterLike(gerencias[x-y])));
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
		
		return criteria;
	}
	
	private Empregado loadCargo(Empregado empregado) {
		if(empregado.getCargo()!=null)
			Hibernate.initialize(empregado.getCargo());
		return empregado;
	}
	
	private Empregado loadFuncao(Empregado empregado) {
		if(empregado.getFuncao() != null)
			Hibernate.initialize(empregado.getFuncao());
		return empregado;
	}
	
	private Empregado loadRegime(Empregado empregado) {
		if(empregado.getRegime() != null)
			Hibernate.initialize(empregado.getRegime());
		return empregado;
	}
	
	private Empregado loadGhe(Empregado empregado) {
		if(empregado.getGhe() != null)
			Hibernate.initialize(empregado.getGhe());
		return empregado;
	}
	
	private Empregado loadGhee(Empregado empregado) {
		if(empregado.getGhee() != null)
			Hibernate.initialize(empregado.getGhee());
		return empregado;
	}
	
	private Empregado loadBase(Empregado empregado) {
		if(empregado.getBase() != null)
			Hibernate.initialize(empregado.getBase());
		return empregado;
	}
	
	private Empregado loadGerencia(Empregado empregado) {
		if(empregado.getGerencia() != null)
			Hibernate.initialize(empregado.getGerencia());
		return empregado;
	}
	
	private Empregado loadInstalacoes(Empregado empregado) {
		if(empregado.getInstalacoes() != null)
			Hibernate.initialize(empregado.getInstalacoes());
		return empregado;
	}
	
	public Empregado getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
	public PagedList<Empregado> getListFunctionLoad(GenericExampleBuilder<?,?> exampleBuilder) throws Exception {
		return getList(exampleBuilder,this.functionLoad);
	}
}
