package br.com.saude.api.model.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.PlanoAlimentar;
import br.com.saude.api.model.entity.po.RefeicaoPlano;
import br.com.saude.api.model.entity.po.Alimento;
import br.com.saude.api.model.entity.po.AlimentoMedidaAlimentar;
import br.com.saude.api.model.entity.po.ItemRefeicaoPlano;
import br.com.saude.api.model.entity.po.MedidaAlimentar;

public class PlanoAlimentarDao extends GenericDao<PlanoAlimentar> {
	private static PlanoAlimentarDao instance;
	private Function<PlanoAlimentar,PlanoAlimentar> functionLoadRefeicoesAlimentos;
	
	private PlanoAlimentarDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadRefeicoesAlimentos = planoAlimentar -> {
			planoAlimentar = loadRefeicoesAlimento(planoAlimentar);
			return planoAlimentar;
		};
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
	
	private PlanoAlimentar loadRefeicoesAlimento(PlanoAlimentar planoAlimentar) {
		if (planoAlimentar.getRefeicoes()!=null) {
			List<RefeicaoPlano> refeicoes = new ArrayList<RefeicaoPlano>();
			planoAlimentar.getRefeicoes().forEach(r -> {
				r = (RefeicaoPlano) Hibernate.unproxy(r);
				if ( r.getItens() != null ) {
					List<ItemRefeicaoPlano> itens = new ArrayList<ItemRefeicaoPlano>();
					r.getItens().forEach(i -> {
						i = (ItemRefeicaoPlano) Hibernate.unproxy(i);
						
						if(i.getAlimento() != null) {
						   Alimento alimento = (Alimento) Hibernate.unproxy(i.getAlimento());
						   
						   List<AlimentoMedidaAlimentar> alimentoMedidaAlimentares = new ArrayList<AlimentoMedidaAlimentar>();	
						   
						   if(alimento.getAlimentoMedidaAlimentares() != null)							   
							   alimento.getAlimentoMedidaAlimentares().forEach(a-> {
								   alimentoMedidaAlimentares.add((AlimentoMedidaAlimentar) Hibernate.unproxy(a));
						   		   a.setMedidaAlimentar((MedidaAlimentar) Hibernate.unproxy(a.getMedidaAlimentar()));
							   });
						   
						   alimento.setAlimentoMedidaAlimentares(alimentoMedidaAlimentares);
						   i.setAlimento(alimento);
						   
						   
						   if(i.getAlimento() != null) {
							   
						   }
						}
						itens.add(i);
				});
					r.setItens(itens);
					
				}
				refeicoes.add(r);
			});
			planoAlimentar.setRefeicoes(refeicoes);
		}
		return planoAlimentar;
	}
	
	@Override
	public PlanoAlimentar getById(Object id) throws Exception {
		return super.getById(id, functionLoadRefeicoesAlimentos);
	}
	
	@Override
	public PagedList<PlanoAlimentar> getList(GenericExampleBuilder<?, ?> exampleBuilder)
			throws Exception {
		return super.getList(exampleBuilder, this.functionLoad);
	}
	
	public PagedList<PlanoAlimentar> getListLoadAlimento(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder,this.functionLoadRefeicoesAlimentos);
	}
}
