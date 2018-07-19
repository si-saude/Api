package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.FichaColetaBuilder;
import br.com.saude.api.model.creation.builder.example.FichaColetaExampleBuilder;
import br.com.saude.api.model.entity.filter.FichaColetaFilter;
import br.com.saude.api.model.entity.po.FichaColeta;
import br.com.saude.api.model.persistence.FichaColetaDao;

public class FichaColetaBo extends GenericBo<FichaColeta, FichaColetaFilter, FichaColetaDao, FichaColetaBuilder, FichaColetaExampleBuilder> {
	private static FichaColetaBo instance;
	
	private FichaColetaBo() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {

	}
	
	public static FichaColetaBo getInstance() {
		if (instance == null)
			instance = new FichaColetaBo();
		return instance;
	}

	@Override
	public FichaColeta save(FichaColeta fichaColeta) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		
		fichaColeta.getRespostaFichaColetas().forEach(rFC -> {
			rFC.setFicha(fichaColeta);
			rFC.getItens().forEach(i -> i.setResposta(rFC));
		});
		
		return super.save(fichaColeta);
	}
	
}
