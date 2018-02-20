package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.FilaAtendimentoOcupacionalFilter;
import br.com.saude.api.model.entity.po.FilaAtendimentoOcupacional;

public class FilaAtendimentoOcupacionalBuilder 
	extends GenericEntityBuilder<FilaAtendimentoOcupacional, FilaAtendimentoOcupacionalFilter> {

	private Function<Map<String,FilaAtendimentoOcupacional>,FilaAtendimentoOcupacional> loadLocalizacao;
	private Function<Map<String,FilaAtendimentoOcupacional>,FilaAtendimentoOcupacional> loadAtualizacoes;
	
	public static FilaAtendimentoOcupacionalBuilder newInstance(FilaAtendimentoOcupacional fila) {
		return new FilaAtendimentoOcupacionalBuilder(fila);
	}
	
	public static FilaAtendimentoOcupacionalBuilder newInstance(List<FilaAtendimentoOcupacional> filas) {
		return new FilaAtendimentoOcupacionalBuilder(filas);
	}
	
	private FilaAtendimentoOcupacionalBuilder(FilaAtendimentoOcupacional fila) {
		super(fila);
	}
	
	private FilaAtendimentoOcupacionalBuilder(List<FilaAtendimentoOcupacional> filas) {
		super(filas);
	}

	@Override
	protected void initializeFunctions() {
		this.loadLocalizacao = filas -> {
			if(filas.get("origem").getLocalizacao() != null)
				filas.get("destino").setLocalizacao(LocalizacaoBuilder
						.newInstance(filas.get("origem").getLocalizacao()).getEntity());
			return filas.get("destino");
		};
		
		this.loadAtualizacoes = filas -> {
			if(filas.get("origem").getAtualizacoes() != null)
				filas.get("destino").setAtualizacoes(FilaAtendimentoOcupacionalAtualizacaoBuilder
						.newInstance(filas.get("origem").getAtualizacoes()).getEntityList());
			return filas.get("destino");
		};
	}

	@Override
	protected FilaAtendimentoOcupacional clone(FilaAtendimentoOcupacional fila) {
		FilaAtendimentoOcupacional newFila = new FilaAtendimentoOcupacional();
		
		newFila.setId(fila.getId());
		newFila.setInicio(fila.getInicio());
		newFila.setAtualizacao(fila.getAtualizacao());
		newFila.setFim(fila.getFim());
		newFila.setStatus(fila.getStatus());
		newFila.setVersion(fila.getVersion());
		
		if(fila.getProfissional() != null)
			newFila.setProfissional(ProfissionalBuilder.newInstance(fila.getProfissional())
					.loadEquipe().loadServicos()
					.getEntity());
		
		return newFila;
	}

	@Override
	public FilaAtendimentoOcupacional cloneFromFilter(FilaAtendimentoOcupacionalFilter filter) {
		return null;
	}
	
	public FilaAtendimentoOcupacionalBuilder loadLocalizacao() {
		return (FilaAtendimentoOcupacionalBuilder) this.loadProperty(this.loadLocalizacao);
	}
	
	public FilaAtendimentoOcupacionalBuilder loadAtualizacoes() {
		return (FilaAtendimentoOcupacionalBuilder) this.loadProperty(this.loadAtualizacoes);
	}
}
