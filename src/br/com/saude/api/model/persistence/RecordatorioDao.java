package br.com.saude.api.model.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Recordatorio;
import br.com.saude.api.model.entity.po.Refeicao;
import br.com.saude.api.model.entity.po.ItemRefeicao;

public class RecordatorioDao extends GenericDao<Recordatorio> {
	private static RecordatorioDao instance;
	
	private RecordatorioDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = recordatorio -> {
			recordatorio = loadRefeicoes(recordatorio);
			return recordatorio;
		};
		
		this.functionAfterSave = pair -> {
			Recordatorio recordatorio = pair.getValue0();
			
			if(recordatorio.getAtendimento() != null && recordatorio.getAtendimento().getId() > 0) {
				try {
					StringBuilder queryBuilder = new StringBuilder("UPDATE Atendimento "
							+ "SET recordatorio_id = :recordatorio_id "
							+ "WHERE id = :id");
					pair.getValue1().createQuery(queryBuilder.toString())
						.setParameter("recordatorio_id", recordatorio.getId())
						.setParameter("id", recordatorio.getAtendimento().getId())
						.executeUpdate();
				}catch(Exception ex) {
					throw ex;
				}
			}
			
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
	
	@Override
	public Recordatorio getById(Object id) throws Exception {
		return super.getById(id, functionLoadAll);
	}
	
	@Override
	public PagedList<Recordatorio> getList(GenericExampleBuilder<?, ?> exampleBuilder)
			throws Exception {
		return super.getList(exampleBuilder, this.functionLoad);
	}
}
