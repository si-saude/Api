package br.com.saude.api.model.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Aprho;
import br.com.saude.api.model.entity.po.AprhoItem;

public class AprhoDao extends GenericDao<Aprho> {

	private static AprhoDao instance;
	
	private AprhoDao() {
		super();
	}
	
	public static AprhoDao getInstance() {
		if(instance==null)
			instance = new AprhoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {		
		this.functionLoadAll = aprho -> {
			aprho = loadArhoItens(aprho);
			
			return aprho;
		};
		
		this.functionBeforeSave = pair -> {
			Aprho aprho = pair.getValue0();		
			
			aprho.getAprhoItens().forEach(i->i.setAprho(aprho));		

			
			return aprho;
		};
	}
	
	@Override
	public Aprho getById(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
	private Aprho loadArhoItens(Aprho aprho) {
		
		
		if (aprho.getAprhoItens()!= null ) {
			List<AprhoItem> aprhoItens = new ArrayList<AprhoItem>();
			aprho.getAprhoItens().forEach(a -> {
				aprhoItens.add((AprhoItem) Hibernate.unproxy(a));
					
			});
			
			aprho.setAprhoItens(aprhoItens);
		}
		return aprho;
	}
	
}
