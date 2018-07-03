package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.AtestadoFilter;
import br.com.saude.api.model.entity.po.Atestado;

public class AtestadoBuilder extends GenericEntityBuilder<Atestado, AtestadoFilter> {

	public static AtestadoBuilder newInstance(Atestado atestado) {
		return new AtestadoBuilder(atestado);
	}
	
	public static AtestadoBuilder newInstance(List<Atestado> atestados) {
		return new AtestadoBuilder(atestados);
	}
	
	private AtestadoBuilder(List<Atestado> atestados) {
		super(atestados);
	}

	private AtestadoBuilder(Atestado atestado) {
		super(atestado);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected Atestado clone(Atestado atestado) {
		Atestado newAtestado = new Atestado();
		
		newAtestado.setId(atestado.getId());
		newAtestado.setAtestadoFisicoRecebido(atestado.isAtestadoFisicoRecebido());
		newAtestado.setCID(atestado.getCID());
		newAtestado.setControleLicenca(atestado.isControleLicenca());
		newAtestado.setDataAgendamento(atestado.getDataAgendamento());
		newAtestado.setDataSolicitacao(atestado.getDataSolicitacao());
		newAtestado.setImpossibilidadeLocomocao(atestado.isImpossibilidadeLocomocao());
		newAtestado.setLancadoSAP(atestado.isLancadoSAP());
		newAtestado.setNumeroDias(atestado.getNumeroDias());
		newAtestado.setStatus(atestado.getStatus());
		newAtestado.setVersion(atestado.getVersion());
		
		if(newAtestado.getTarefa() != null)
			newAtestado.setTarefa(TarefaBuilder.newInstance(atestado.getTarefa()).getEntity());
		
		return newAtestado;
	}

	@Override
	public Atestado cloneFromFilter(AtestadoFilter filter) {
		return null;
	}

}
