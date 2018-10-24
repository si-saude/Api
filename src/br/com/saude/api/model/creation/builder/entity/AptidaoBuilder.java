package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.Aptidao;

public class AptidaoBuilder extends GenericEntityBuilder<Aptidao, GenericFilter> {
	
	private Function<Map<String,Aptidao>,Aptidao> loadGrupoMonitoramento;
	
	
	public static AptidaoBuilder newInstance(Aptidao aptidoes) {
		return new AptidaoBuilder(aptidoes);
	}
	
	public static AptidaoBuilder newInstance(List<Aptidao> aptidoes) {
		return new AptidaoBuilder(aptidoes);
	}
	
	private AptidaoBuilder(List<Aptidao> aptidoes) {
		super(aptidoes);
	}

	private AptidaoBuilder(Aptidao aptidao) {
		super(aptidao);
	}

	@Override
	protected void initializeFunctions() {		
		this.loadGrupoMonitoramento = asos -> {
			if(asos.get("origem").getGrupoMonitoramento() != null)
				asos.get("destino").setGrupoMonitoramento(GrupoMonitoramentoBuilder
						.newInstance(asos.get("origem").getGrupoMonitoramento()).getEntity());
			return asos.get("destino");
		};
	}

	@Override
	protected Aptidao clone(Aptidao aptidao) {
		Aptidao newAptidao = new Aptidao();		
		newAptidao.setId(aptidao.getId());
		newAptidao.setGrupoMonitoramento(aptidao.getGrupoMonitoramento());
		newAptidao.setAptidaoAso(aptidao.getAptidaoAso());
		newAptidao.setVersion(aptidao.getVersion());	
		
		return newAptidao;
	}

	@Override
	public Aptidao cloneFromFilter(GenericFilter filter) {
		return null;
	}

	public AptidaoBuilder loadGrupoMonitoramento() {
		return (AptidaoBuilder) this.loadProperty(this.loadGrupoMonitoramento);
	}
}
