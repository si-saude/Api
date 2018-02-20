package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.LocalizacaoFilter;
import br.com.saude.api.model.entity.po.Localizacao;

public class LocalizacaoBuilder extends GenericEntityBuilder<Localizacao,LocalizacaoFilter> {

	private Function<Map<String,Localizacao>,Localizacao> loadRegraAtendimentoLocalizacoes;

	public static LocalizacaoBuilder newInstance(Localizacao localizacao) {
		return new LocalizacaoBuilder(localizacao);
	}
	
	public static LocalizacaoBuilder newInstance(List<Localizacao> localizacoes) {
		return new LocalizacaoBuilder(localizacoes);
	}
	
	private LocalizacaoBuilder(Localizacao localizacao) {
		super(localizacao);
	}

	private LocalizacaoBuilder(List<Localizacao> localizacoes) {
		super(localizacoes);
	}
	
	@Override
	protected void initializeFunctions() {
		this.loadRegraAtendimentoLocalizacoes = localizacoes -> {
			if(localizacoes.get("origem").getRegraAtendimentoLocalizacoes() != null)
				localizacoes.get("destino").setRegraAtendimentoLocalizacoes(
						RegraAtendimentoLocalizacaoBuilder
						.newInstance(localizacoes.get("origem").getRegraAtendimentoLocalizacoes())
						.loadServicos().getEntityList());
			return localizacoes.get("destino");
		};
	}

	@Override
	protected Localizacao clone(Localizacao localizacao) {
		Localizacao newLocalizacao = new Localizacao();
		
		newLocalizacao.setId(localizacao.getId());
		newLocalizacao.setNome(localizacao.getNome());
		newLocalizacao.setVersion(localizacao.getVersion());
		
		return newLocalizacao;
	}

	@Override
	public Localizacao cloneFromFilter(LocalizacaoFilter filter) {
		return null;
	}

	public LocalizacaoBuilder loadRegraAtendimentoLocalizacoes() {
		return (LocalizacaoBuilder) this.loadProperty(this.loadRegraAtendimentoLocalizacoes);
	}
}
