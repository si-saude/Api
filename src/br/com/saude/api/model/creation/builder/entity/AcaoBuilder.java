package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.Acao;

public class AcaoBuilder extends GenericEntityBuilder<Acao, GenericFilter> {

	private Function<Map<String,Acao>,Acao> loadTarefa;
	
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
	
		this.loadTarefa = tarefas -> {
			
			if(tarefas.get("origem").getTarefa() != null)
				tarefas.get("destino").setTarefa(TarefaBuilder
						.newInstance(tarefas.get("origem").getTarefa())
						.getEntity());
			
			return tarefas.get("destino");
		};
	}

	@Override
	protected Acao clone(Acao acao) {
		
		Acao newAcao = new Acao();
		newAcao.setId(acao.getId());
		newAcao.setData(acao.getData());
		newAcao.setDetalhe(acao.getDetalhe());
		newAcao.setStatus(acao.getStatus());
		newAcao.setTipo(acao.getTipo());
		newAcao.setTipoContato(acao.getTipoContato());
		newAcao.setVersion(acao.getVersion());
		
		return newAcao;
	}
	
	public AcaoBuilder loadTarefa() {
		return (AcaoBuilder) this.loadProperty(this.loadTarefa);
	}

	@Override
	public Acao cloneFromFilter(GenericFilter filter) {
		return null;
	}
}
