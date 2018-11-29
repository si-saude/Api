package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.RecordatorioFilter;
import br.com.saude.api.model.entity.po.Recordatorio;

public class RecordatorioBuilder extends GenericEntityBuilder<Recordatorio, RecordatorioFilter> {
	private Function<Map<String,Recordatorio>,Recordatorio> loadRefeicoes;
	
	public static RecordatorioBuilder newInstance(Recordatorio recordatorio) {
		return new RecordatorioBuilder(recordatorio);
	}
	
	public static RecordatorioBuilder newInstance(List<Recordatorio> recordatorios) {
		return new RecordatorioBuilder(recordatorios);
	}
	
	private RecordatorioBuilder(Recordatorio recordatorios) {
		super(recordatorios);
	}
	
	private RecordatorioBuilder(List<Recordatorio> recordatorios) {
		super(recordatorios);
	}

	@Override
	protected void initializeFunctions() {
		this.loadRefeicoes = recordatorios ->{
			if(recordatorios.get("origem").getRefeicoes() != null) {
				recordatorios.get("destino").setRefeicoes(
						RefeicaoBuilder.newInstance(
								recordatorios.get("origem").getRefeicoes()).getEntityList());
			}
			return recordatorios.get("destino");
		};
	}
	
	@Override
	protected Recordatorio clone(Recordatorio recordatorio) {
		Recordatorio cloneRecordatorio = new Recordatorio();
		
		cloneRecordatorio.setId(recordatorio.getId());
		cloneRecordatorio.setVersion(recordatorio.getVersion());
		cloneRecordatorio.setNe(recordatorio.getNe());
		cloneRecordatorio.setTmb(recordatorio.getTmb());
		
		if ( recordatorio.getAtendimento() != null ) {
			recordatorio.getAtendimento().setFilaAtendimentoOcupacional(null);
			cloneRecordatorio.setAtendimento(
					AtendimentoBuilder.newInstance(recordatorio.getAtendimento()).getEntity());
		}
		
		return cloneRecordatorio;
	}
	
	@Override
	public Recordatorio cloneFromFilter(RecordatorioFilter filter) {
		return null;
	}
	
	public RecordatorioBuilder loadRefeicoes() {
		return (RecordatorioBuilder) this.loadProperty(this.loadRefeicoes);
	}
}
