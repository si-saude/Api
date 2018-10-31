package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.AsoAvaliacao;

public class AsoAvaliacaoBuilder extends GenericEntityBuilder<AsoAvaliacao, GenericFilter> {

	public static AsoAvaliacaoBuilder newInstance(AsoAvaliacao avaliacaoAso) {
		return new AsoAvaliacaoBuilder(avaliacaoAso);
	}
	
	public static AsoAvaliacaoBuilder newInstance(List<AsoAvaliacao> avaliacaoAso) {
		return new AsoAvaliacaoBuilder(avaliacaoAso);
	}
	
	private AsoAvaliacaoBuilder(List<AsoAvaliacao> avaliacaoAso) {
		super(avaliacaoAso);
	}

	private AsoAvaliacaoBuilder(AsoAvaliacao avaliacaoAso) {
		super(avaliacaoAso);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected AsoAvaliacao clone(AsoAvaliacao avaliacaoAso) {
		AsoAvaliacao newAvaliacaoAso = new AsoAvaliacao();
		
		newAvaliacaoAso.setId(avaliacaoAso.getId());
		newAvaliacaoAso.setConforme(avaliacaoAso.isConforme());
		newAvaliacaoAso.setDescricao(avaliacaoAso.getDescricao());
		newAvaliacaoAso.setVersion(avaliacaoAso.getVersion());
		
		return newAvaliacaoAso;
	}

	@Override
	public AsoAvaliacao cloneFromFilter(GenericFilter filter) {
		return null;
	}

}
