package br.com.saude.api.model.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.MedidaAlimentar;
import br.com.saude.api.model.entity.po.Alimento;
import br.com.saude.api.model.entity.po.AlimentoMedidaAlimentar;

public class AlimentoDao extends GenericDao<Alimento>{
	private static AlimentoDao instance;
	
	private AlimentoDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoad = alimento -> {
			alimento = loadAlimentoMedidaAlimentares(alimento);
			return alimento;
		};
		this.functionLoadAll = alimento -> {
			alimento = loadAlimentoMedidaAlimentares(alimento);
			return alimento;
		};

	}
	
	public static AlimentoDao getInstance() {
		if(instance == null)
			instance = new AlimentoDao();
		return instance;
	}
	
	private Alimento loadAlimentoMedidaAlimentares(Alimento alimento) {
		List<AlimentoMedidaAlimentar> alimentoMedidaAlimentares = new ArrayList<AlimentoMedidaAlimentar>();
		if(alimento.getAlimentoMedidaAlimentares() != null) {
			Hibernate.initialize(alimento.getAlimentoMedidaAlimentares());
			alimento.getAlimentoMedidaAlimentares().forEach(ama -> {
				ama.setMedidaAlimentar((MedidaAlimentar) Hibernate.unproxy(ama.getMedidaAlimentar()));
				alimentoMedidaAlimentares.add(ama);
			});
			alimento.setAlimentoMedidaAlimentares(alimentoMedidaAlimentares);
		}
		return alimento;
	}

	@Override
	public Alimento getById(Object id) throws Exception {
		// TODO Auto-generated method stub
		return super.getById(id, functionLoad);
	}
	
	public PagedList<Alimento> getListFunctionLoad(GenericExampleBuilder<?,?> exampleBuilder) throws Exception {
		return getList(exampleBuilder,this.functionLoad);
	}
	
}
