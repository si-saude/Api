package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Localizacao;

public class LocalizacaoDao extends GenericDao<Localizacao> {
	
	private static LocalizacaoDao instance;
	
	private LocalizacaoDao() {
		super();
	}
	
	public static LocalizacaoDao getInstance() {
		if(instance==null)
			instance = new LocalizacaoDao();
		return instance;
	}
}
