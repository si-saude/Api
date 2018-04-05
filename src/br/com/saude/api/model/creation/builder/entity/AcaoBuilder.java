package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.Acao;

public class AcaoBuilder extends GenericEntityBuilder<Acao, GenericFilter> {
	
	private Function<Map<String,Acao>,Acao> loadAcompanhamentos;

	public static AcaoBuilder newInstance(Acao acao) {
		return new AcaoBuilder(acao);
	}
	
	public static AcaoBuilder newInstance(List<Acao> acoes) {
		return new AcaoBuilder(acoes);
	}
	
	private AcaoBuilder(Acao acao) {
		super(acao);
	}
	
	private AcaoBuilder(List<Acao> acoes) {
		super(acoes);
	}

	@Override
	protected void initializeFunctions() {
		this.loadAcompanhamentos = acoes -> {
			
			if(acoes.get("origem").getAcompanhamentos() != null) {
				acoes.get("destino").setAcompanhamentos(
						AcompanhamentoBuilder
							.newInstance(acoes.get("origem").getAcompanhamentos())
							.getEntityList());
			}
			
			return acoes.get("destino");
		};
	}

	@Override
	protected Acao clone(Acao acao) {
		
		Acao newAcao = new Acao();
		newAcao.setId(acao.getId());
		newAcao.setDetalhe(acao.getDetalhe());
		newAcao.setStatus(acao.getStatus());
		newAcao.setTipo(acao.getTipo());
		newAcao.setTipoContato(acao.getTipoContato());
		newAcao.setVersion(acao.getVersion());
		
		if ( acao.getTarefa() != null )
			newAcao.setTarefa(TarefaBuilder.newInstance(acao.getTarefa()).getEntity());
		
		return newAcao;
	}
	
	@Override
	public Acao cloneFromFilter(GenericFilter filter) {
		return null;
	}
	
	public AcaoBuilder loadAcompanhamentos() {
		return (AcaoBuilder) this.loadProperty(this.loadAcompanhamentos);
	}
}
