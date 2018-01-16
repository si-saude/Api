package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.AsoAlteracao;

public class AsoAlteracaoBuilder extends GenericEntityBuilder<AsoAlteracao, GenericFilter> {

	public static AsoAlteracaoBuilder newInstance(AsoAlteracao alteracao) {
		return new AsoAlteracaoBuilder(alteracao);
	}
	
	public static AsoAlteracaoBuilder newInstance(List<AsoAlteracao> alteracoes) {
		return new AsoAlteracaoBuilder(alteracoes);
	}
	
	private AsoAlteracaoBuilder(List<AsoAlteracao> alteracoes) {
		super(alteracoes);
	}

	private AsoAlteracaoBuilder(AsoAlteracao alteracao) {
		super(alteracao);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected AsoAlteracao clone(AsoAlteracao alteracao) {
		AsoAlteracao newAlteracao = new AsoAlteracao();
		
		newAlteracao.setId(alteracao.getId());
		newAlteracao.setData(alteracao.getData());
		newAlteracao.setStatus(alteracao.getStatus());
		newAlteracao.setVersion(alteracao.getVersion());
		
		if(alteracao.getUsuario() != null)
			newAlteracao.setUsuario(UsuarioBuilder
					.newInstance(alteracao.getUsuario()).getEntity());
		
		return newAlteracao;
	}

	@Override
	public AsoAlteracao cloneFromFilter(GenericFilter filter) {
		return null;
	}

}
