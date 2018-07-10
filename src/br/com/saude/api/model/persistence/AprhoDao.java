package br.com.saude.api.model.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Aprho;
import br.com.saude.api.model.entity.po.AprhoEmpregado;
import br.com.saude.api.model.entity.po.AprhoItem;
import br.com.saude.api.model.entity.po.Cargo;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.Gerencia;
import br.com.saude.api.model.entity.po.Pessoa;
import br.com.saude.api.model.entity.po.Profissional;

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
			aprho = loadArhoEmpregados(aprho);
			
			if(aprho.getElaboradores()!=null)
				Hibernate.initialize(aprho.getElaboradores());
			
			return aprho;
		};
		
		this.functionBeforeSave = pair -> {
			Aprho aprho = pair.getValue0();		
			Session session = pair.getValue1();
			
			aprho.getAprhoItens().forEach(i->i.setAprho(aprho));		

			aprho.getAprhoEmpregados().forEach(i->i.setAprho(aprho));		

			if(aprho.getElaboradores() != null)
				for(int i=0; i < aprho.getElaboradores().size(); i++)
					aprho.getElaboradores().set(i, session.get(Profissional.class, aprho.getElaboradores().get(i).getId()));
			
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
	
	private Aprho loadArhoEmpregados(Aprho aprho) {	
		
		if (aprho.getAprhoEmpregados()!= null ) {
			List<AprhoEmpregado> aprhoEmpregados = new ArrayList<AprhoEmpregado>();
			aprho.getAprhoEmpregados().forEach(a -> {
				aprhoEmpregados.add((AprhoEmpregado) Hibernate.unproxy(a));
					
				a.setEmpregado((Empregado)Hibernate.unproxy(a.getEmpregado()));
				a.getEmpregado().setPessoa((Pessoa)Hibernate.unproxy(a.getEmpregado().getPessoa()));
				a.getEmpregado().setGerencia((Gerencia)Hibernate.unproxy(a.getEmpregado().getGerencia()));
				a.getEmpregado().setCargo((Cargo)Hibernate.unproxy(a.getEmpregado().getCargo()));
			});			
			aprho.setAprhoEmpregados(aprhoEmpregados);
		}
		return aprho;
	}
	
}
