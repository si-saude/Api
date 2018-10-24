package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
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
			profissiograma = loadGrupoMonitoramentoProfissiogramas(profissiograma);
			return profissiograma;
		};
		
		this.functionLoadAll = profissiograma -> {
			profissiograma = this.functionLoad.apply(profissiograma);
			return profissiograma;
		};
	}
	
	private Profissiograma loadGrupoMonitoramentoProfissiogramas(Profissiograma profissiograma) {
		if(profissiograma.getGrupoMonitoramentoProfissiogramas() != null) {
			Hibernate.initialize(profissiograma.getGrupoMonitoramentoProfissiogramas());
			
			profissiograma.getGrupoMonitoramentoProfissiogramas()
				.forEach(g->{
					
					if(g.getGrupoMonitoramentoProfissiogramaExames() != null) {
						Hibernate.initialize(g.getGrupoMonitoramentoProfissiogramaExames());
						g.getGrupoMonitoramentoProfissiogramaExames().forEach(e->{
							if(e.getCriterios() != null)
								Hibernate.initialize(e.getCriterios());
						});
					}
				});
		}
		return profissiograma;
	}
	
	public Profissiograma getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
	public PagedList<Profissiograma> getListFunctionLoad(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder,this.functionLoad);
	}
}
