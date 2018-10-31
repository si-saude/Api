package br.com.saude.api.model.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.MedidaAlimentar;
import br.com.saude.api.model.entity.po.NutricaoAlimento;
import br.com.saude.api.model.entity.po.NutricaoAlimentoMedidaAlimentar;

public class NutricaoAlimentoDao extends GenericDao<NutricaoAlimento>{
	private static NutricaoAlimentoDao instance;
	
	private NutricaoAlimentoDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoad = nutricaoAlimento -> {
			nutricaoAlimento = loadNutricaoAlimentoMedidaAlimentares(nutricaoAlimento);
			return nutricaoAlimento;
		};
	}
	
	public static NutricaoAlimentoDao getInstance() {
		if(instance == null)
			instance = new NutricaoAlimentoDao();
		return instance;
	}
	
	private NutricaoAlimento loadNutricaoAlimentoMedidaAlimentares(NutricaoAlimento nutricaoAlimento) {
		List<NutricaoAlimentoMedidaAlimentar> nutricaoAlimentoMedidaAlimentares = new ArrayList<NutricaoAlimentoMedidaAlimentar>();
		if(nutricaoAlimento.getNutricaoAlimentoMedidaAlimentares() != null) {
			Hibernate.initialize(nutricaoAlimento.getNutricaoAlimentoMedidaAlimentares());
			nutricaoAlimento.getNutricaoAlimentoMedidaAlimentares().forEach(nama -> {
				nama.setMedidaAlimentar((MedidaAlimentar) Hibernate.unproxy(nama.getMedidaAlimentar()));
				nutricaoAlimentoMedidaAlimentares.add(nama);
			});
			nutricaoAlimento.setNutricaoAlimentoMedidaAlimentares(nutricaoAlimentoMedidaAlimentares);
		}
		return nutricaoAlimento;
	}

	@Override
	public NutricaoAlimento getById(Object id) throws Exception {
		// TODO Auto-generated method stub
		return super.getById(id, functionLoad);
	}
	
}
