package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
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
			return builder.loadDemandas();
		};
	}
	
	@Override
	public Servico getById(Object id) throws Exception {
		return super.getById(id, this.functionLoadAll);
	}
	
	@Override
	public Servico save(Servico servico) throws Exception {
		servico.getDemandas().forEach(d->d.setServico(servico));
		return super.save(servico);
	}

}
