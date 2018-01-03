package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.RegraAtendimentoFilter;
import br.com.saude.api.model.entity.po.RegraAtendimento;

public class RegraAtendimentoBuilder 
	extends GenericEntityBuilder<RegraAtendimento, RegraAtendimentoFilter> {

	private Function<Map<String,RegraAtendimento>,RegraAtendimento> loadRegraAtendimentoEquipes;
	
	public static RegraAtendimentoBuilder newInstance(RegraAtendimento regra) {
		return new RegraAtendimentoBuilder(regra);
	}
	
	public static RegraAtendimentoBuilder newInstance(List<RegraAtendimento> regras) {
		return new RegraAtendimentoBuilder(regras);
	}
	
	private RegraAtendimentoBuilder(RegraAtendimento regra) {
		super(regra);
	}
	
	private RegraAtendimentoBuilder(List<RegraAtendimento> regras) {
		super(regras);
	}

	@Override
	protected void initializeFunctions() {
		this.loadRegraAtendimentoEquipes = regras -> {
			if(regras.get("origem").getRegraAtendimentoEquipes() != null) {
				regras.get("destino").setRegraAtendimentoEquipes(
						RegraAtendimentoEquipeBuilder.newInstance(
								regras.get("origem").getRegraAtendimentoEquipes())
						.loadRequisitos()
						.getEntityList());
			}
			return regras.get("destino");
		};
	}

	@Override
	protected RegraAtendimento clone(RegraAtendimento regra) {
		RegraAtendimento newRegra = new RegraAtendimento();
		
		newRegra.setId(regra.getId());
		newRegra.setNome(regra.getNome());
		newRegra.setVersion(regra.getVersion());
		
		return newRegra;
	}
	
	public RegraAtendimentoBuilder loadRegraAtendimentoEquipes() {
		return (RegraAtendimentoBuilder) this.loadProperty(this.loadRegraAtendimentoEquipes);
	}

	@Override
	public RegraAtendimento cloneFromFilter(RegraAtendimentoFilter filter) {
		return null;
	}
}
