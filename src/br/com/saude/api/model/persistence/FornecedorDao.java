package br.com.saude.api.model.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Fornecedor;
import br.com.saude.api.model.entity.po.Telefone;

public class FornecedorDao extends GenericDao<Fornecedor>{

	private static FornecedorDao instance;
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	private FornecedorDao() {
		super();
		
		//CRIA��O DAS FUN��ES
		this.functionLoadAll = fornecedor -> {
			fornecedor = loadTelefones(fornecedor);
			fornecedor = loadEndereco(fornecedor);
			return fornecedor;
		};
		
		this.functionBeforeSave = pair -> {
			Fornecedor fornecedor = pair.getValue0();
			Session session = pair.getValue1();
			
			//REMOVE REGISTROS DE TELEFONE �RF�OS
			if(fornecedor.getId() > 0) {
				if(fornecedor.getTelefones() == null)
					fornecedor.setTelefones(new ArrayList<Telefone>());
				
				List<Telefone> telefones = (List<Telefone>)session.createCriteria(Telefone.class)
						.createAlias("fornecedores", "fornecedor")
						.add(Restrictions.eq("fornecedor.id", fornecedor.getId()))
						.list();
				telefones.forEach(t->{
					if(!fornecedor.getTelefones().contains(t))
						session.remove(t);
				});
			}
			return fornecedor;
		};
	}
	
	public static FornecedorDao getInstance() {
		if(instance == null)
			instance = new FornecedorDao();
		return instance;
	}
	
	public Fornecedor getByIdLoadAll(Object id) throws Exception {
		return this.getById(id, this.functionLoadAll);
	}
	
	private Fornecedor loadEndereco(Fornecedor fornecedor) {
		if(fornecedor.getEndereco()!=null)
			Hibernate.initialize(fornecedor.getEndereco());
		return fornecedor;
	}
	
	private Fornecedor loadTelefones(Fornecedor fornecedor) {
		if(fornecedor.getTelefones()!=null)
			Hibernate.initialize(fornecedor.getTelefones());
		return fornecedor;
	}
}
