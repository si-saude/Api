package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.RegraAtendimentoLocalizacao;

public class RegraAtendimentoLocalizacaoBuilder extends GenericEntityBuilder<RegraAtendimentoLocalizacao, GenericFilter> {

	private Function<Map<String,RegraAtendimentoLocalizacao>,RegraAtendimentoLocalizacao> loadServicos;

	public static RegraAtendimentoLocalizacaoBuilder newInstance(RegraAtendimentoLocalizacao regraAtendimentoLocalizacao) {
		return new RegraAtendimentoLocalizacaoBuilder(regraAtendimentoLocalizacao);
	}
	
	public static RegraAtendimentoLocalizacaoBuilder newInstance(List<RegraAtendimentoLocalizacao> regraAtendimentoLocalizacoes) {
		return new RegraAtendimentoLocalizacaoBuilder(regraAtendimentoLocalizacoes);
	}
	
	protected RegraAtendimentoLocalizacaoBuilder(List<RegraAtendimentoLocalizacao> entityList) {
		super(entityList);
	}
	
	protected RegraAtendimentoLocalizacaoBuilder(RegraAtendimentoLocalizacao entity) {
		super(entity);
	}

	@Override
	protected void initializeFunctions() {
		this.loadServicos = regraAtendimentoLocalizacoes -> {
			if(regraAtendimentoLocalizacoes.get("origem").getServicos() != null)
				regraAtendimentoLocalizacoes.get("destino")
					.setServicos(ServicoBuilder.newInstance(regraAtendimentoLocalizacoes.get("origem").getServicos()).getEntityList());
			return regraAtendimentoLocalizacoes.get("destino");
		};
	}

	@Override
	protected RegraAtendimentoLocalizacao clone(RegraAtendimentoLocalizacao regraAtendimentoLocalizacao) {
		RegraAtendimentoLocalizacao cloneRegraAtendimentoLocalizacao = new RegraAtendimentoLocalizacao();
		
		cloneRegraAtendimentoLocalizacao.setId(regraAtendimentoLocalizacao.getId());
		cloneRegraAtendimentoLocalizacao.setVersion(regraAtendimentoLocalizacao.getVersion());
		
		if ( regraAtendimentoLocalizacao.getRegraAtendimento() != null )
			cloneRegraAtendimentoLocalizacao.setRegraAtendimento(new RegraAtendimentoBuilder(regraAtendimentoLocalizacao.getRegraAtendimento()).getEntity());
		
		return cloneRegraAtendimentoLocalizacao;
	}

	@Override
	public RegraAtendimentoLocalizacao cloneFromFilter(GenericFilter filter) {
		return null;
	}
	
	public RegraAtendimentoLocalizacaoBuilder loadServicos() {
		return (RegraAtendimentoLocalizacaoBuilder) this.loadProperty(this.loadServicos);
	}
	

}
