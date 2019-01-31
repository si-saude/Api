package br.com.saude.api.model.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.PlanoAlimentar;
import br.com.saude.api.model.entity.po.RefeicaoPlano;
import br.com.saude.api.model.entity.po.Alimento;
import br.com.saude.api.model.entity.po.ItemRefeicaoPlano;

public class PlanoAlimentarDao extends GenericDao<PlanoAlimentar> {
	private static PlanoAlimentarDao instance;
	
	private PlanoAlimentarDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = planoAlimentar -> {
			planoAlimentar = loadRefeicoes(planoAlimentar);
			return planoAlimentar;
		};
		
		this.functionBeforeSave = pair -> {
			PlanoAlimentar planoAlimentar = pair.getValue0();
			Session session = pair.getValue1();
			
			if(planoAlimentar.getRefeicoes() !=null ) {
				for(int i=0; i < planoAlimentar.getRefeicoes().size(); i++) {
					if(planoAlimentar.getRefeicoes().get(i).getItens() != null) {
						for(int j=0; j < planoAlimentar.getRefeicoes().get(i).getItens().size(); j++) {
						   
						    planoAlimentar.getRefeicoes().get(i).getItens().get(j)
						   		.setAlimento(session.get(Alimento.class, planoAlimentar.getRefeicoes().get(i).getItens().get(j).getAlimento().getId()));
						   
						   if(planoAlimentar.getRefeicoes().get(i).getItens().get(j).getAlimentos() != null) {
							   
							   for(int y=0; y < planoAlimentar.getRefeicoes().get(i).getItens().get(j).getAlimentos().size(); y++) {
							   
							   planoAlimentar.getRefeicoes().get(i).getItens().get(j).getAlimentos()
							   			.set(y, session.get(Alimento.class, planoAlimentar.getRefeicoes().get(i).getItens().get(j).getAlimentos().get(y).getId()));
							   }
						   }
						   
					   }
						   
					}
			}
		}
			return planoAlimentar;
	};
}
	
	public static PlanoAlimentarDao getInstance() {
		if(instance == null)
			instance = new PlanoAlimentarDao();
		return instance;
	}
	
	private PlanoAlimentar loadRefeicoes(PlanoAlimentar planoAlimentar) {
		if (planoAlimentar.getRefeicoes()!=null) {
			List<RefeicaoPlano> planoAlimentares = new ArrayList<RefeicaoPlano>();
			Hibernate.initialize(planoAlimentar.getRefeicoes());
			planoAlimentar.getRefeicoes().forEach(r -> {
				r = (RefeicaoPlano) Hibernate.unproxy(r);
				if ( r.getItens() != null ) {
					List<ItemRefeicaoPlano> itens = new ArrayList<ItemRefeicaoPlano>();
					r.getItens().forEach(i -> {
						i = (ItemRefeicaoPlano) Hibernate.unproxy(i);
						
						if(i.getAlimento() != null) {
						   Alimento alimento = (Alimento) Hibernate.unproxy(i.getAlimento());
						   i.setAlimento(alimento);
						   
						   if(i.getAlimento().getSubstituicoes() != null) {
							   List<Alimento> alimentos = new ArrayList<Alimento>();
							   Hibernate.initialize(i.getAlimento().getSubstituicoes());
							   i.getAlimento().getSubstituicoes().forEach(a-> {
								   alimentos.add(a);
							   });
							   i.getAlimento().setSubstituicoes(alimentos);
						   }
						}
						itens.add(i);
					});
					r.setItens(itens);
						
				}
				planoAlimentares.add(r);
			});
			planoAlimentar.setRefeicoes(planoAlimentares);
		}
		return planoAlimentar;
	}
	
	@Override
	public PlanoAlimentar getById(Object id) throws Exception {
		return super.getById(id, functionLoadAll);
	}
	
	@Override
	public PagedList<PlanoAlimentar> getList(GenericExampleBuilder<?, ?> exampleBuilder)
			throws Exception {
		return super.getList(exampleBuilder, this.functionLoad);
	}
}
