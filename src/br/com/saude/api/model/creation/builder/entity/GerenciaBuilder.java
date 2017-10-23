package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.GerenciaFilter;
import br.com.saude.api.model.entity.po.Gerencia;

public class GerenciaBuilder extends GenericEntityBuilder<Gerencia,GerenciaFilter> {

	private Function<Map<String,Gerencia>,Gerencia> loadGerente;
	private Function<Map<String,Gerencia>,Gerencia> loadSecretario1;
	private Function<Map<String,Gerencia>,Gerencia> loadSecretario2;
	
	public static GerenciaBuilder newInstance(Gerencia gerencia) {
		return new GerenciaBuilder(gerencia);
	}
	
	public static GerenciaBuilder newInstance(List<Gerencia> gerencias) {
		return new GerenciaBuilder(gerencias);
	}
	
	private GerenciaBuilder(List<Gerencia> gerencias) {
		super(gerencias);
	}

	private GerenciaBuilder(Gerencia gerencia) {
		super(gerencia);
	}
	
	@Override
	protected void initializeFunctions() {
		this.loadGerente = gerencias -> {
			if(gerencias.get("origem").getGerente() != null)
				gerencias.get("destino").setGerente(EmpregadoBuilder
											.newInstance(gerencias.get("origem").getGerente())
											.loadTelefones()
											.getEntity());
			return gerencias.get("destino");
		};
		
		this.loadSecretario1 = gerencias -> {
			if(gerencias.get("origem").getSecretario1() != null)
				gerencias.get("destino").setSecretario1(EmpregadoBuilder
											.newInstance(gerencias.get("origem").getSecretario1())
											.loadTelefones()
											.getEntity());
			return gerencias.get("destino");
		};
		
		this.loadSecretario2 = gerencias -> {
			if(gerencias.get("origem").getSecretario2() != null)
				gerencias.get("destino").setSecretario2(EmpregadoBuilder
											.newInstance(gerencias.get("origem").getSecretario2())
											.loadTelefones()
											.getEntity());
			return gerencias.get("destino");
		};
	}

	@Override
	protected Gerencia clone(Gerencia gerencia) {
		Gerencia newGerencia = new Gerencia();
		
		newGerencia.setId(gerencia.getId());
		newGerencia.setCodigo(gerencia.getCodigo());
		newGerencia.setVersion(gerencia.getVersion());
		newGerencia.setDescricao(gerencia.getDescricao());
		
		if(gerencia.getGerencia() != null)
			newGerencia.setGerencia(new GerenciaBuilder(gerencia.getGerencia()).getEntity());
		
		return newGerencia;
	}
	
	public GerenciaBuilder loadGerente() {
		return (GerenciaBuilder) this.loadProperty(this.loadGerente);
	}
	
	public GerenciaBuilder loadSecretario1() {
		return (GerenciaBuilder) this.loadProperty(this.loadSecretario1);
	}
	
	public GerenciaBuilder loadSecretario2() {
		return (GerenciaBuilder) this.loadProperty(this.loadSecretario2);
	}

	@Override
	public Gerencia cloneFromFilter(GerenciaFilter filter) {
		return null;
	}

}
