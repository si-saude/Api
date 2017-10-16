package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.CurriculoFilter;
import br.com.saude.api.model.entity.po.Curriculo;

public class CurriculoBuilder extends GenericEntityBuilder<Curriculo,CurriculoFilter> {

	private Function<Map<String,Curriculo>,Curriculo> loadCurriculoCursos;
	
	public static CurriculoBuilder newInstance(Curriculo curriculo) {
		return new CurriculoBuilder(curriculo);
	}
	
	public static CurriculoBuilder newInstance(List<Curriculo> curriculos) {
		return new CurriculoBuilder(curriculos);
	}
	
	private CurriculoBuilder(List<Curriculo> curriculos) {
		super(curriculos);
	}

	private CurriculoBuilder(Curriculo curriculo) {
		super(curriculo);
	}
	
	@Override
	protected void initializeFunctions() {
		this.loadCurriculoCursos = curriculos -> {
			if(curriculos.get("origem").getCurriculoCursos() != null) {
				curriculos.get("destino").setCurriculoCursos(CurriculoCursoBuilder.newInstance(curriculos.get("origem").getCurriculoCursos()).loadCurso().getEntityList());
			}
			return curriculos.get("destino");
		};
	}

	@Override
	protected Curriculo clone(Curriculo curriculo) {
		Curriculo newCurriculo = new Curriculo();
		
		newCurriculo.setId(curriculo.getId());
		newCurriculo.setHistorico(curriculo.getHistorico());
		newCurriculo.setFormacao(curriculo.getFormacao());
		newCurriculo.setAtuacao(curriculo.getAtuacao());
		newCurriculo.setVersion(curriculo.getVersion());
		
		return newCurriculo;
	}
	
	public CurriculoBuilder loadCurriculoCursos() {
		return (CurriculoBuilder) this.loadProperty(this.loadCurriculoCursos);
	}

	@Override
	public Curriculo cloneFromFilter(CurriculoFilter filter) {
		return null;
	}
}
