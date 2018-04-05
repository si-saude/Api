package br.com.saude.api.model.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.RiscoEmpregado;
import br.com.saude.api.model.entity.po.Triagem;
import br.com.saude.api.model.entity.po.IndicadorAssociadoSast;

public class RiscoEmpregadoDao extends GenericDao<RiscoEmpregado> {

	private static RiscoEmpregadoDao instance;
	
	private RiscoEmpregadoDao() {
		super();
	}
	
	public static RiscoEmpregadoDao getInstance() {
		if(instance==null)
			instance = new RiscoEmpregadoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoad = riscoEmpregado -> {
			riscoEmpregado = loadRiscoPotencial(riscoEmpregado);
			riscoEmpregado = loadTriagens(riscoEmpregado);
			return riscoEmpregado;
		};

	}
	
	private RiscoEmpregado loadRiscoPotencial(RiscoEmpregado riscoEmpregado) {
		if (riscoEmpregado.getRiscoPotencial() != null)
			Hibernate.unproxy(riscoEmpregado.getRiscoPotencial());
		
		return riscoEmpregado;
	}
	
	private RiscoEmpregado loadTriagens(RiscoEmpregado riscoEmpregado) {
		if (riscoEmpregado.getTriagens() != null) {
			List<Triagem> triagens = new ArrayList<Triagem>();
			riscoEmpregado.getTriagens().forEach(t -> {
				if ( t.getIndicadorSast().getIndicadorAssociadoSasts() != null ) {
					List<IndicadorAssociadoSast> iass = new ArrayList<IndicadorAssociadoSast>();
					t.getIndicadorSast().getIndicadorAssociadoSasts().forEach(ias -> {
						iass.add((IndicadorAssociadoSast) Hibernate.unproxy(ias));
					});
					t.getIndicadorSast().setIndicadorAssociadoSasts(iass);
				}
				triagens.add((Triagem) Hibernate.unproxy(t));
			});
			riscoEmpregado.setTriagens(triagens);
		}
		
		return riscoEmpregado;
	}
	
	@Override
	public PagedList<RiscoEmpregado> getList(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder, this.functionLoad);
	}
	
	@Override
	public RiscoEmpregado getById(Object id) throws Exception {
		return super.getById(id, this.functionLoad);
	}
	
}
