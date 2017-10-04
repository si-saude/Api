package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.LocalizacaoBuilder;
import br.com.saude.api.model.creation.builder.example.LocalizacaoExampleBuilder;
import br.com.saude.api.model.entity.filter.LocalizacaoFilter;
import br.com.saude.api.model.entity.po.Localizacao;
import br.com.saude.api.model.persistence.LocalizacaoDao;

public class LocalizacaoBo extends GenericBo<Localizacao, LocalizacaoFilter, LocalizacaoDao, 
									LocalizacaoBuilder, LocalizacaoExampleBuilder> {

	private static LocalizacaoBo instance;
	
	private LocalizacaoBo() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		
	}
	
	public static LocalizacaoBo getInstance() {
		if(instance==null)
			instance = new LocalizacaoBo();
		return instance;
	}
}
