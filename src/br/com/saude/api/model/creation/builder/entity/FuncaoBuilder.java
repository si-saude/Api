package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.FuncaoFilter;
import br.com.saude.api.model.entity.po.Funcao;

public class FuncaoBuilder extends GenericEntityBuilder<Funcao, FuncaoFilter> {

	private Function<Map<String,Funcao>,Funcao> loadVacinas;
	
	public static FuncaoBuilder newInstance(Funcao funcao) {
		return new FuncaoBuilder(funcao);
	}
	
	public static FuncaoBuilder newInstance(List<Funcao> funcoes) {
		return new FuncaoBuilder(funcoes);
	}
	
	private FuncaoBuilder(List<Funcao> funcoes) {
		super(funcoes);
	}

	private FuncaoBuilder(Funcao funcao) {
		super(funcao);
	}
	
	@Override
	protected void initializeFunctions() {
		this.loadVacinas = vacinas -> {
			if(vacinas.get("origem").getVacinas() != null) {
				vacinas.get("destino").setVacinas(VacinaBuilder.newInstance(vacinas.get("origem").getVacinas()).getEntityList());
			}
			return vacinas.get("destino");
		};
	}

	@Override
	protected Funcao clone(Funcao funcao) {
		Funcao newFuncao = new Funcao();
		
		newFuncao.setId(funcao.getId());
		newFuncao.setNome(funcao.getNome());
		newFuncao.setVersion(funcao.getVersion());
		
		return newFuncao;
	}

	public FuncaoBuilder loadVacinas() {
		return (FuncaoBuilder) this.loadProperty(this.loadVacinas);
	}
	
	@Override
	public Funcao cloneFromFilter(FuncaoFilter filter) {
		return null;
	}

}
