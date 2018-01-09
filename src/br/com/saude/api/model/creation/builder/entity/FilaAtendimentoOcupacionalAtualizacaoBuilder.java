package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.FilaAtendimentoOcupacionalAtualizacao;

public class FilaAtendimentoOcupacionalAtualizacaoBuilder
	extends GenericEntityBuilder<FilaAtendimentoOcupacionalAtualizacao, GenericFilter>{

	public static FilaAtendimentoOcupacionalAtualizacaoBuilder newInstance(
			FilaAtendimentoOcupacionalAtualizacao atualizacao) {
		return new FilaAtendimentoOcupacionalAtualizacaoBuilder(atualizacao);
	}
	
	public static FilaAtendimentoOcupacionalAtualizacaoBuilder newInstance(
			List<FilaAtendimentoOcupacionalAtualizacao> atualizacoes) {
		return new FilaAtendimentoOcupacionalAtualizacaoBuilder(atualizacoes);
	}
	
	private FilaAtendimentoOcupacionalAtualizacaoBuilder(FilaAtendimentoOcupacionalAtualizacao atualizacao) {
		super(atualizacao);
	}
	
	private FilaAtendimentoOcupacionalAtualizacaoBuilder(List<FilaAtendimentoOcupacionalAtualizacao> atualizacoes) {
		super(atualizacoes);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected FilaAtendimentoOcupacionalAtualizacao clone(FilaAtendimentoOcupacionalAtualizacao atualizacao) {
		FilaAtendimentoOcupacionalAtualizacao newAtualizacao = new FilaAtendimentoOcupacionalAtualizacao();
		
		newAtualizacao.setId(atualizacao.getId());
		newAtualizacao.setStatus(atualizacao.getStatus());
		newAtualizacao.setTempo(atualizacao.getTempo());
		newAtualizacao.setVersion(atualizacao.getVersion());
		
		return newAtualizacao;
	}

	@Override
	public FilaAtendimentoOcupacionalAtualizacao cloneFromFilter(GenericFilter filter) {
		return null;
	}
}
