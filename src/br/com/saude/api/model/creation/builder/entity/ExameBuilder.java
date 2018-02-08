package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.ExameFilter;
import br.com.saude.api.model.entity.po.Exame;

public class ExameBuilder extends GenericEntityBuilder<Exame,ExameFilter> {
	private Function<Map<String,Exame>,Exame> loadCampoExames;

	public static ExameBuilder newInstance(Exame exame) {
		return new ExameBuilder(exame);
	}
	
	public static ExameBuilder newInstance(List<Exame> exames) {
		return new ExameBuilder(exames);
	}
	
	private ExameBuilder(Exame exame) {
		super(exame);
	}
	
	private ExameBuilder(List<Exame> exames) {
		super(exames);
	}
	
	@Override
	protected void initializeFunctions() {
		this.loadCampoExames = exames -> {
			if(exames.get("origem").getCampoExames()!= null) {
				exames.get("destino").setCampoExames(CampoExameBuilder
													.newInstance(exames.get("origem").getCampoExames())
													.getEntityList());
			}
			
			return exames.get("destino");
		};
	}

	@Override
	protected Exame clone(Exame exame) {
		Exame newExame = new Exame();
		
		newExame.setId(exame.getId());
		newExame.setVersion(exame.getVersion());
		newExame.setCodigo(exame.getCodigo());
		newExame.setDescricao(exame.getDescricao());
		newExame.setOrdem(exame.getOrdem());

		return newExame;
	}

	public ExameBuilder loadCampoExames() {
		return (ExameBuilder) this.loadProperty(this.loadCampoExames);
	}
	
	@Override
	public Exame cloneFromFilter(ExameFilter filter) {
		return null;
	}
}
