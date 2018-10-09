package br.com.saude.api.model.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.Instalacao;
import br.com.saude.api.model.entity.po.Telefone;
import br.com.saude.api.model.entity.po.Vacina;
import br.com.saude.api.model.entity.po.GrupoMonitoramento;
import br.com.saude.api.model.entity.po.HistoricoGrupoMonitoramento;

public class EmpregadoDao extends GenericDao<Empregado>  {
	
	private Function<Empregado,Empregado> functionLoadGrupoMonitoramentos;
	private Function<Empregado,Empregado> functionLoadGrupoMonitoramentosExames;
	
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
			empregado = loadPessoa(empregado);
			return empregado;
		};
		
		this.functionLoadAll = empregado -> {
			empregado = this.functionLoad.apply(empregado);
			empregado = loadBase(empregado);
			empregado = loadGhe(empregado);
			empregado = loadGhee(empregado);
			empregado = loadRegime(empregado);
			empregado = loadInstalacoes(empregado);
			empregado = loadEmpregadoVacinas(empregado);
			empregado = loadGrupoMonitoramentos(empregado);
			empregado = loadHistoricoGrupoMonitoramentos(empregado);
			empregado = loadEndereco(empregado);
			empregado = loadTelefones(empregado);
			empregado = loadEnfase(empregado);
			
			return empregado;
		};
		
		this.functionLoadGrupoMonitoramentos = empregado -> {
			empregado = this.functionLoad.apply(empregado);
			empregado = loadGrupoMonitoramentos(empregado);
			return empregado;
		};
		
		this.functionLoadGrupoMonitoramentosExames = empregado -> {
			empregado = this.functionLoad.apply(empregado);
			empregado = loadGrupoMonitoramentosExames(empregado);
			return empregado;
		};
		
		this.functionBeforeSave = pair -> {
			Empregado empregado = pair.getValue0();
			Session session = pair.getValue1();
			
			//CARREGA AS INSTALAÇÕES PARA QUE O HIBERNATE SALVE
			if(empregado.getInstalacoes()!=null)
				for(int i=0; i < empregado.getInstalacoes().size(); i++)
					empregado.getInstalacoes().set(i, 
							session.get(Instalacao.class, empregado.getInstalacoes()
																	.get(i).getId()));
			
			//CARREGA OS GRUPOS DE MONITORAMENTO PARA QUE O HIBERNATE SALVE
			if(empregado.getGrupoMonitoramentos()!=null)
				for(int i=0; i < empregado.getGrupoMonitoramentos().size(); i++)
					empregado.getGrupoMonitoramentos().set(i, 
							session.get(GrupoMonitoramento.class, empregado.getGrupoMonitoramentos()
																	.get(i).getId()));
			
			//CARREGAR AS VACINAS
			if(empregado.getEmpregadoVacinas() != null)
				for(int i=0; i < empregado.getEmpregadoVacinas().size(); i++)
					empregado.getEmpregadoVacinas().get(i)
						.setVacina(session.get(Vacina.class, 
								empregado.getEmpregadoVacinas().get(i).getVacina().getId()));
			
			if(empregado.getId() > 0) {				
				//REMOVE REGISTROS DE TELEFONE ÓRFÃOS
				List<Telefone> telefones = (List<Telefone>)session.createCriteria(Telefone.class)
						.createAlias("pessoas", "pessoa")
						.createAlias("pessoa.empregado", "empregado")
						.add(Restrictions.eq("empregado.id", empregado.getId()))
						.list();
				telefones.forEach(t->{
					if(!empregado.getPessoa().getTelefones().contains(t))
						session.remove(t);
				});
				
				//CARREGAR O HISTÓRICO DE GRUPOS DE MONITORAMENTO
				List<HistoricoGrupoMonitoramento> historicos = (List<HistoricoGrupoMonitoramento>)session
						.createCriteria(HistoricoGrupoMonitoramento.class)
						.add(Restrictions.eq("empregado",empregado))
						.list();
				
				if(historicos == null)
					historicos = new ArrayList<HistoricoGrupoMonitoramento>();
				
				empregado.setHistoricoGrupoMonitoramentos(historicos);
				
				//VERIFICA SE FORAM REMOVIDOS GRUPOS DE MONITORAMENTO PARA INSERÇÃO NO HISTÓRICO
				List<Integer> ids = new ArrayList<Integer>();
				empregado.getGrupoMonitoramentos().forEach(g -> ids.add(g.getId()));
				
				Criteria grupoMonitoramentoCriteria = session.createCriteria(GrupoMonitoramento.class)
						.createAlias("empregados", "empregado")
						.add(Restrictions.eq("empregado.id", empregado.getId()));
				
				if(ids.size() > 0)
					grupoMonitoramentoCriteria.add(Restrictions.not(Restrictions.in("id", ids.toArray())) );
				
				List<GrupoMonitoramento> grupoMonitoramentos = (List<GrupoMonitoramento>)grupoMonitoramentoCriteria.list();
				
				if(grupoMonitoramentos != null)
					grupoMonitoramentos.forEach(g-> {
						HistoricoGrupoMonitoramento historico = new HistoricoGrupoMonitoramento();
						historico.setDataRemocao(new Date());
						historico.setGrupoMonitoramento(g);
						empregado.getHistoricoGrupoMonitoramentos().add(historico);
					});
				
				empregado.getHistoricoGrupoMonitoramentos().forEach(h->h.setEmpregado(empregado));
			}
			
			return empregado;
		};
	}
	
	@Override
	protected Criteria finishCriteria(Criteria criteria, GenericExampleBuilder<?, ?> empregadoExampleBuilder) {
		EmpregadoFilter filter = (EmpregadoFilter)empregadoExampleBuilder.getFilter();
		
		if(filter.getGerencia() != null) {
			if(filter.getGerencia().getCodigoCompleto() != null) {
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
			}else if(filter.getGerencia().getId() > 0) {
				criteria.add(Restrictions.eq("gerencia.id", (int)filter.getGerencia().getId()));
			}
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
	
	private Empregado loadEmpregadoVacinas(Empregado empregado) {
		if(empregado.getEmpregadoVacinas()!=null)
			Hibernate.initialize(empregado.getEmpregadoVacinas());
		return empregado;
	}
	
	private Empregado loadPessoa(Empregado empregado) {
		if(empregado.getPessoa()!=null) {
			Hibernate.initialize(empregado.getPessoa());
		}
		return empregado;
	}
	
	private Empregado loadEndereco(Empregado empregado) {
		if(empregado.getPessoa().getEndereco()!=null) {
			Hibernate.initialize(empregado.getPessoa().getEndereco());
			if (empregado.getPessoa().getEndereco().getCidade()!=null)
				Hibernate.initialize(empregado.getPessoa().getEndereco().getCidade());
		}
		return empregado;
	}
	
	private Empregado loadTelefones(Empregado empregado) {
		if(empregado.getPessoa().getTelefones()!=null) {
			Hibernate.initialize(empregado.getPessoa().getTelefones());
		}
		return empregado;
	}
	
	private Empregado loadGrupoMonitoramentos(Empregado empregado) {
		if(empregado.getGrupoMonitoramentos()!=null)
			Hibernate.initialize(empregado.getGrupoMonitoramentos());
		return empregado;
	}
	
	private Empregado loadEnfase(Empregado empregado) {
		if(empregado.getEnfase()!=null)
			Hibernate.initialize(empregado.getEnfase());
		return empregado;
	}
	
	private Empregado loadGrupoMonitoramentosExames(Empregado empregado) {
		if(empregado.getGrupoMonitoramentos()!=null) {
			Hibernate.initialize(empregado.getGrupoMonitoramentos());
		}
		return empregado;
	}
	
	private Empregado loadHistoricoGrupoMonitoramentos(Empregado empregado) {
		if(empregado.getHistoricoGrupoMonitoramentos()!=null)
			Hibernate.initialize(empregado.getHistoricoGrupoMonitoramentos());
		return empregado;
	}
	
	public Empregado getByIdLoad(Object id) throws Exception {
		return super.getById(id,this.functionLoad);
	}
	
	public Empregado getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
	public Empregado getByIdLoadGrupoMonitoramento(Object id) throws Exception {
		return super.getById(id,this.functionLoadGrupoMonitoramentosExames);
	}
	
	public PagedList<Empregado> getListFunctionLoad(GenericExampleBuilder<?,?> exampleBuilder) throws Exception {
		return getList(exampleBuilder,this.functionLoad);
	}
	
	public PagedList<Empregado> getListFunctionLoadAll(GenericExampleBuilder<?,?> exampleBuilder) throws Exception {
		return getList(exampleBuilder,this.functionLoadAll);
	}
	
	public PagedList<Empregado> getListFunctionLoadGrupoMonitoramentos(GenericExampleBuilder<?,?> exampleBuilder) throws Exception {
		return getList(exampleBuilder,this.functionLoadGrupoMonitoramentos);
	}
	
	public PagedList<Empregado> getListFunctionLoadGrupoMonitoramentosExames(GenericExampleBuilder<?,?> exampleBuilder) throws Exception {
		return getList(exampleBuilder,this.functionLoadGrupoMonitoramentosExames);
	}
	
	protected Function<Empregado,Empregado> getFunctionLoad(){
		return this.functionLoad;
	}
}
