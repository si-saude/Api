package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.ServicoBuilder;
import br.com.saude.api.model.creation.builder.example.ServicoExampleBuilder;
import br.com.saude.api.model.entity.filter.ServicoFilter;
import br.com.saude.api.model.entity.po.Servico;
import br.com.saude.api.model.persistence.ServicoDao;

public class ServicoBo extends GenericBo<Servico, ServicoFilter, 
							ServicoDao, ServicoBuilder, ServicoExampleBuilder> {

	private static ServicoBo instance;
	
	private ServicoBo() {
		super();
	}
	
	public static ServicoBo getInstance() {
		if(instance == null)
			instance = new ServicoBo();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadAtividades();
		};
	}
	
	@Override
	public Servico getById(Object id) throws Exception {
		return super.getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}
	
	@Override
	public PagedList<Servico> getList(GenericExampleBuilder<Servico, ServicoFilter> exampleBuilder)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, InstantiationException, Exception {
		return super.getList(exampleBuilder);
	}

	@Override
	public Servico save(Servico servico) throws Exception {
		servico.getAtividades().forEach(d->d.setServico(servico));
		return super.save(servico);
	}

}
