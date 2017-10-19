package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.javatuples.Pair;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.GrupoMonitoramento;
import br.com.saude.api.model.entity.po.Profissiograma;

public class ProfissiogramaDao extends GenericDao<Profissiograma> {

	private static ProfissiogramaDao instance;
	
	private ProfissiogramaDao() {
		super();
	}
	
	public static ProfissiogramaDao getInstance() {
		if(instance==null)
			instance = new ProfissiogramaDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoad = profissiograma -> {
			profissiograma = loadGrupoMonitoramentos(profissiograma);
			return profissiograma;
		};
		
		this.functionLoadAll = profissiograma -> {
			profissiograma = this.functionLoad.apply(profissiograma);
			return profissiograma;
		};
		
		this.functionBeforeSave = pair -> {
			Profissiograma profissiograma = pair.getValue0();
			Session session = pair.getValue1();
			
			//CARREGA OS EXAMES
			if(profissiograma.getGrupoMonitoramentos() != null) {
				for(int i = 0; i < profissiograma.getGrupoMonitoramentos().size(); i++) {
					profissiograma.getGrupoMonitoramentos().set(i, 
							GrupoMonitoramentoDao.getInstance()
												.getFunctionBeforeSave()
												.apply(new Pair<GrupoMonitoramento,Session>(
																profissiograma.getGrupoMonitoramentos()
																				.get(i), 
																session)));
				}
			}
			return profissiograma;
		};
	}
	
	private Profissiograma loadGrupoMonitoramentos(Profissiograma profissiograma) {
		if(profissiograma.getGrupoMonitoramentos() != null)
			Hibernate.initialize(profissiograma.getGrupoMonitoramentos());
		return profissiograma;
	}
	
	public Profissiograma getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
	public PagedList<Profissiograma> getListFunctionLoad(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder,this.functionLoad);
	}
}
