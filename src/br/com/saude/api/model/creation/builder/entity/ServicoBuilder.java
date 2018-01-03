package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.ServicoFilter;
import br.com.saude.api.model.entity.po.Servico;

public class ServicoBuilder extends GenericEntityBuilder<Servico, ServicoFilter> {
	
	public static ServicoBuilder newInstance(Servico servico) {
		return new ServicoBuilder(servico);
	}
	
	public static ServicoBuilder newInstance(List<Servico> servicos) {
		return new ServicoBuilder(servicos);
	}
	
	private ServicoBuilder(Servico servico) {
		super(servico);
	}
	
	private ServicoBuilder(List<Servico> servicos) {
		super(servicos);
	}

	@Override
	protected void initializeFunctions() {

	}

	@Override
	protected Servico clone(Servico servico) {
		Servico newServico = new Servico();
		
		newServico.setId(servico.getId());
		newServico.setCodigo(servico.getCodigo());
		newServico.setGrupo(servico.getGrupo());
		newServico.setNome(servico.getNome());
		newServico.setUrl(servico.getUrl());
		newServico.setPublico(servico.isPublico());
		newServico.setIntervalo(servico.getIntervalo());
		newServico.setQuantidadeSolicitacaoIntervalo(servico.getQuantidadeSolicitacaoIntervalo());
		newServico.setVersion(servico.getVersion());
		
		return newServico;
	}

	@Override
	public Servico cloneFromFilter(ServicoFilter filter) {
		return null;
	}
}
