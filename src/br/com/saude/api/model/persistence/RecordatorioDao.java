package br.com.saude.api.model.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Recordatorio;
import br.com.saude.api.model.entity.po.Refeicao;
import br.com.saude.api.model.entity.po.Alimento;
import br.com.saude.api.model.entity.po.AlimentoMedidaAlimentar;
import br.com.saude.api.model.entity.po.ItemRefeicao;

public class RecordatorioDao extends GenericDao<Recordatorio> {
	private Function<Recordatorio,Recordatorio> functionLoadRefeicoesAlimentos;
	
	private static RecordatorioDao instance;
	
	private RecordatorioDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadRefeicoesAlimentos = recordatorio -> {
			recordatorio = loadRefeicoesAlimento(recordatorio);
			return recordatorio;
		};
		
		this.functionLoadAll = recordatorio -> {
			recordatorio = loadRefeicoes(recordatorio);
			return recordatorio;
		};
	}
	
	public static RecordatorioDao getInstance() {
		if(instance == null)
			instance = new RecordatorioDao();
		return instance;
	}
	
	private Recordatorio loadRefeicoes(Recordatorio recordatorio) {
		if (recordatorio.getRefeicoes()!=null) {
			List<Refeicao> refeicoes = new ArrayList<Refeicao>();
			Hibernate.initialize(recordatorio.getRefeicoes());
			recordatorio.getRefeicoes().forEach(r -> {
				r = (Refeicao) Hibernate.unproxy(r);
				if ( r.getItens() != null ) {
					List<ItemRefeicao> itens = new ArrayList<ItemRefeicao>();
					r.getItens().forEach(i -> itens.add((ItemRefeicao) Hibernate.unproxy(i)));
					r.setItens(itens);
				}
				refeicoes.add(r);
			});
			recordatorio.setRefeicoes(refeicoes);
		}
		return recordatorio;
	}
	
	private Recordatorio loadRefeicoesAlimento(Recordatorio recordatorio) {
		if (recordatorio.getRefeicoes()!=null) {
			List<Refeicao> refeicoes = new ArrayList<Refeicao>();
			recordatorio.getRefeicoes().forEach(r -> {
				r = (Refeicao) Hibernate.unproxy(r);
				if ( r.getItens() != null ) {
					List<ItemRefeicao> itens = new ArrayList<ItemRefeicao>();
					r.getItens().forEach(i -> {
						i = (ItemRefeicao) Hibernate.unproxy(i);
						
						if(i.getAlimento() != null) {
						   Alimento alimento = (Alimento) Hibernate.unproxy(i.getAlimento());
						   
						   List<AlimentoMedidaAlimentar> alimentoMedidaAlimentares = new ArrayList<AlimentoMedidaAlimentar>();	
						   
						   if(alimento.getAlimentoMedidaAlimentares() != null) 
							   alimento.getAlimentoMedidaAlimentares().forEach(a-> alimentoMedidaAlimentares.add((AlimentoMedidaAlimentar) Hibernate.unproxy(a)));
						   
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
			recordatorio.setRefeicoes(refeicoes);
		}
		return recordatorio;
	}
	
	@Override
	public Recordatorio getById(Object id) throws Exception {
		return super.getById(id, functionLoadRefeicoesAlimentos);
	}
	
	@Override
	public PagedList<Recordatorio> getList(GenericExampleBuilder<?, ?> exampleBuilder)
			throws Exception {
		return super.getList(exampleBuilder, this.functionLoad);
	}
	
	public PagedList<Recordatorio> getListLoadAll(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder,this.functionLoadAll);
	}
	
	public PagedList<Recordatorio> getListLoadAlimento(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder,this.functionLoadRefeicoesAlimentos);
	}
	
}
