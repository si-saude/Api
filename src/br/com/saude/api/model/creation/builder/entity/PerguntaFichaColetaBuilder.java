package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.PerguntaFichaColetaFilter;
import br.com.saude.api.model.entity.po.PerguntaFichaColeta;

public class PerguntaFichaColetaBuilder extends GenericEntityBuilder<PerguntaFichaColeta,PerguntaFichaColetaFilter> {
	
	private Function<Map<String,PerguntaFichaColeta>,PerguntaFichaColeta> loadItens;
	private Function<Map<String,PerguntaFichaColeta>,PerguntaFichaColeta> loadEquipes;

	public static PerguntaFichaColetaBuilder newInstance(PerguntaFichaColeta perguntaFichaColeta) {
		return new PerguntaFichaColetaBuilder(perguntaFichaColeta);
	}
	
	public static PerguntaFichaColetaBuilder newInstance(List<PerguntaFichaColeta> perguntaFichaColetas) {
		return new PerguntaFichaColetaBuilder(perguntaFichaColetas);
	}
	
	private PerguntaFichaColetaBuilder(PerguntaFichaColeta perguntaFichaColeta) {
		super(perguntaFichaColeta);
	}

	private PerguntaFichaColetaBuilder(List<PerguntaFichaColeta> perguntaFichaColetas) {
		super(perguntaFichaColetas);
	}

	@Override
	protected void initializeFunctions() {
		this.loadItens = perguntas -> {
			if (perguntas.get("origem").getItens() != null) {
				perguntas.get("destino").setItens(
						ItemPerguntaFichaColetaBuilder.newInstance(
								perguntas.get("origem").getItens()).getEntityList());
			}
			return perguntas.get("destino");
		};
		
		this.loadEquipes = perguntas -> {
			if (perguntas.get("origem").getEquipes() != null) {
				perguntas.get("destino").setEquipes(
						EquipeBuilder.newInstance(
								perguntas.get("origem").getEquipes()).getEntityList());
			}
			return perguntas.get("destino");
		}; 
	}

	@Override
	protected PerguntaFichaColeta clone(PerguntaFichaColeta perguntaFichaColeta) {
		PerguntaFichaColeta clonePerguntaFichaColeta = new PerguntaFichaColeta();
		
		clonePerguntaFichaColeta.setId(perguntaFichaColeta.getId());
		clonePerguntaFichaColeta.setVersion(perguntaFichaColeta.getVersion());
		clonePerguntaFichaColeta.setCodigo(perguntaFichaColeta.getCodigo());
		clonePerguntaFichaColeta.setGrupo(perguntaFichaColeta.getGrupo());
		clonePerguntaFichaColeta.setInativo(perguntaFichaColeta.isInativo());
		clonePerguntaFichaColeta.setDescricao(perguntaFichaColeta.getDescricao());
		clonePerguntaFichaColeta.setTipo(perguntaFichaColeta.getTipo());
		clonePerguntaFichaColeta.setOrdem(perguntaFichaColeta.getOrdem());
		clonePerguntaFichaColeta.setPath(perguntaFichaColeta.getPath());
		
		return clonePerguntaFichaColeta;
	}

	@Override
	public PerguntaFichaColeta cloneFromFilter(PerguntaFichaColetaFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public PerguntaFichaColetaBuilder loadItens() {
		return (PerguntaFichaColetaBuilder) this.loadProperty(this.loadItens);
	}
	
	public PerguntaFichaColetaBuilder loadEquipes() {
		return (PerguntaFichaColetaBuilder) this.loadProperty(this.loadEquipes);
	}
}
