package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.ProfissiogramaBuilder;
import br.com.saude.api.model.creation.builder.example.ProfissiogramaExampleBuilder;
import br.com.saude.api.model.entity.filter.ProfissiogramaFilter;
import br.com.saude.api.model.entity.po.Profissiograma;
import br.com.saude.api.model.persistence.ProfissiogramaDao;

public class ProfissiogramaBo 
	extends GenericBo<Profissiograma, ProfissiogramaFilter, ProfissiogramaDao, 
			ProfissiogramaBuilder, ProfissiogramaExampleBuilder> {

	private static ProfissiogramaBo instance;
	
	private ProfissiogramaBo() {
		super();
	}
	
	public static ProfissiogramaBo getInstance() {
		if(instance==null)
			instance = new ProfissiogramaBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadGrupoMonitoramentoProfissiogramas();
		};
	}
	
	@Override
	public Profissiograma getById(Object id) throws Exception {
		return this.getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}
	
	@Override
	public Profissiograma save(Profissiograma profissiograma) throws Exception {
		
		profissiograma.getGrupoMonitoramentoProfissiogramas().forEach(g->{
			g.setProfissiograma(profissiograma);
			g.getGrupoMonitoramentoProfissiogramaExames().forEach(p -> {
				p.setGrupoMonitoramentoProfissiograma(g);
			});
		});
		
		return super.save(profissiograma);
	}
}
