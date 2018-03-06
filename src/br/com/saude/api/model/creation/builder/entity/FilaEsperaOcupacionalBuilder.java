package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.FilaEsperaOcupacionalFilter;
import br.com.saude.api.model.entity.po.FilaEsperaOcupacional;

public class FilaEsperaOcupacionalBuilder 
	extends GenericEntityBuilder<FilaEsperaOcupacional, FilaEsperaOcupacionalFilter> {

	private Function<Map<String,FilaEsperaOcupacional>,FilaEsperaOcupacional> loadLocalizacao;
	private Function<Map<String,FilaEsperaOcupacional>,FilaEsperaOcupacional> loadFichaColeta;
	private Function<Map<String,FilaEsperaOcupacional>,FilaEsperaOcupacional> loadRiscoPotencial;
	
	public static FilaEsperaOcupacionalBuilder newInstance(FilaEsperaOcupacional fila) {
		return new FilaEsperaOcupacionalBuilder(fila);
	}
	
	public static FilaEsperaOcupacionalBuilder newInstance(List<FilaEsperaOcupacional> filas) {
		return new FilaEsperaOcupacionalBuilder(filas);
	}
	
	private FilaEsperaOcupacionalBuilder(FilaEsperaOcupacional fila) {
		super(fila);
	}
	
	private FilaEsperaOcupacionalBuilder(List<FilaEsperaOcupacional> filas) {
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
		
		this.loadFichaColeta = filas -> {
			if(filas.get("origem").getFichaColeta() != null)
				filas.get("destino").setFichaColeta(FichaColetaBuilder
						.newInstance(filas.get("origem").getFichaColeta()).getEntity());
			return filas.get("destino");
		};
		
		this.loadRiscoPotencial = filas -> {
			if(filas.get("origem").getRiscoPotencial() != null)
				filas.get("destino").setRiscoPotencial(RiscoPotencialBuilder
						.newInstance(filas.get("origem").getRiscoPotencial()).getEntity());
			return filas.get("destino");
		};
	}

	@Override
	protected FilaEsperaOcupacional clone(FilaEsperaOcupacional fila) {
		FilaEsperaOcupacional newFila = new FilaEsperaOcupacional();
		
		newFila.setId(fila.getId());
		newFila.setAtualizacao(fila.getAtualizacao());
		newFila.setHorarioCheckin(fila.getHorarioCheckin());
		newFila.setSaida(fila.getSaida());
		newFila.setStatus(fila.getStatus());
		newFila.setTempoEspera(fila.getTempoEspera());
		newFila.setVersion(fila.getVersion());
		
		if(fila.getEmpregado()!=null)
			newFila.setEmpregado(EmpregadoBuilder.newInstance(fila.getEmpregado()).getEntity());
		
		if(fila.getServico()!=null)
			newFila.setServico(ServicoBuilder.newInstance(fila.getServico()).getEntity());
		
		return newFila;
	}
	
	public FilaEsperaOcupacionalBuilder loadLocalizacao() {
		return (FilaEsperaOcupacionalBuilder) loadProperty(this.loadLocalizacao);
	}
	
	public FilaEsperaOcupacionalBuilder loadFichaColeta() {
		return (FilaEsperaOcupacionalBuilder) loadProperty(this.loadFichaColeta);
	}
	
	public FilaEsperaOcupacionalBuilder loadRiscoPotencial() {
		return (FilaEsperaOcupacionalBuilder) loadProperty(this.loadRiscoPotencial);
	}

	@Override
	public FilaEsperaOcupacional cloneFromFilter(FilaEsperaOcupacionalFilter filter) {
		return null;
	}
}
