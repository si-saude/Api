package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.MotivoRecusaAtestadoFilter;
import br.com.saude.api.model.entity.po.MotivoRecusaAtestado;

public class MotivoRecusaAtestadoExampleBuilder extends GenericExampleBuilder<MotivoRecusaAtestado, MotivoRecusaAtestadoFilter> {

	public static MotivoRecusaAtestadoExampleBuilder newInstance(MotivoRecusaAtestadoFilter filter) {
		return new MotivoRecusaAtestadoExampleBuilder(filter);
	}
	
	private MotivoRecusaAtestadoExampleBuilder(MotivoRecusaAtestadoFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addDescricao();
		addPermiteReabertura();
	}

	@Override
	protected void createExampleSelectList() {
		
	}
	
	private void addDescricao() {
		if(this.filter.getDescricao() != null)
			this.criterions.add(Restrictions.ilike("descricao", Helper.filterLike(this.filter.getDescricao())));
	}
	
	protected void addPermiteReabertura() {
		this.entity.setPermiteReabertura(this.addBoolean("permiteReabertura", this.filter.getPermiteReabertura()));
	}

}
