package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.Avaliacao;

public class AvaliacaoBuilder extends GenericEntityBuilder<Avaliacao, GenericFilter> {

	public static AvaliacaoBuilder newInstance(Avaliacao avaliacao) {
		return new AvaliacaoBuilder(avaliacao);
	}
	
	public static AvaliacaoBuilder newInstance(List<Avaliacao> list) {
		return new AvaliacaoBuilder(list);
	}
	
	private AvaliacaoBuilder(Avaliacao avaliacao) {
		super(avaliacao);
	}

	private AvaliacaoBuilder(List<Avaliacao> list) {
		super(list);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected Avaliacao clone(Avaliacao avaliacao) {
		Avaliacao newAvaliacao = new Avaliacao();
		
		newAvaliacao.setId(avaliacao.getId());
		newAvaliacao.setNome(avaliacao.getNome());
		newAvaliacao.setVersion(avaliacao.getVersion());
		newAvaliacao.setAuditoriaAso((avaliacao.isAuditoriaAso()));
		newAvaliacao.setAuditoriaMedico((avaliacao.isAuditoriaMedico()));
		
		return newAvaliacao;
	}

	@Override
	public Avaliacao cloneFromFilter(GenericFilter filter) {
		return null;
	}
}
