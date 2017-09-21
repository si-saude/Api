package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.LocalizacaoFilter;
import br.com.saude.api.model.entity.po.Localizacao;

public class LocalizacaoBuilder extends GenericEntityBuilder<Localizacao,LocalizacaoFilter> {

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

}
