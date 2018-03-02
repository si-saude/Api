package br.com.saude.api.model.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.FichaColeta;
import br.com.saude.api.model.entity.po.FilaEsperaOcupacional;
import br.com.saude.api.model.entity.po.ItemRespostaFichaColeta;
import br.com.saude.api.model.entity.po.RespostaFichaColeta;
import br.com.saude.api.model.entity.po.RiscoPotencial;

public class FilaEsperaOcupacionalDao extends GenericDao<FilaEsperaOcupacional> {

	private static FilaEsperaOcupacionalDao instance;
	
	private FilaEsperaOcupacionalDao() {
		super();
	}
	
	public static FilaEsperaOcupacionalDao getInstance() {
		if(instance == null)
			instance = new FilaEsperaOcupacionalDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = fila -> {
			if(fila.getLocalizacao() != null)
				Hibernate.initialize(fila.getLocalizacao());
			
			if(fila.getRiscoPotencial() != null)
				fila.setRiscoPotencial((RiscoPotencial) Hibernate.unproxy(fila.getRiscoPotencial()));
			
			if(fila.getFichaColeta() != null) {
				fila.setFichaColeta((FichaColeta) 
						Hibernate.unproxy(fila.getFichaColeta()));
				
				List<RespostaFichaColeta> respostas = new ArrayList<RespostaFichaColeta>();
				
				fila.getFichaColeta().getRespostaFichaColetas().forEach(r->{
					respostas.add((RespostaFichaColeta) Hibernate.unproxy(r));
				});
				
				
				respostas.forEach(r -> {
					List<ItemRespostaFichaColeta> itens = new ArrayList<ItemRespostaFichaColeta>();
					
					r.getItens().forEach(i -> {
						itens.add(inicializeItem(i));
					});
					
					r.setItens(itens);
				});
				
				fila.getFichaColeta().setRespostaFichaColetas(respostas);
			}
			
			return fila;
		};
	}
	
	protected ItemRespostaFichaColeta inicializeItem(ItemRespostaFichaColeta item) {
		item = (ItemRespostaFichaColeta) Hibernate.unproxy(item);
		
		if(item.getItem() != null)
			item.setItem(inicializeItem(item.getItem()));
		
		return item;
	}
	
	public FilaEsperaOcupacional getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
	public PagedList<FilaEsperaOcupacional> getListLoadAll(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder,this.functionLoadAll);
	}
}
