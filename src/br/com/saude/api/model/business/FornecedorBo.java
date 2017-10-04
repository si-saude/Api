package br.com.saude.api.model.business;

import java.util.function.Function;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.FornecedorBuilder;
import br.com.saude.api.model.creation.builder.example.FornecedorExampleBuilder;
import br.com.saude.api.model.entity.filter.FornecedorFilter;
import br.com.saude.api.model.entity.po.Fornecedor;
import br.com.saude.api.model.persistence.FornecedorDao;

public class FornecedorBo extends GenericBo<Fornecedor, FornecedorFilter, FornecedorDao, 
											FornecedorBuilder, FornecedorExampleBuilder> {
	
	private static FornecedorBo instance;
	private Function<FornecedorBuilder,FornecedorBuilder> functionLoadAll;
	
	private FornecedorBo() {
		super();
		
		this.functionLoadAll = builder -> {
			return builder.loadEndereco().loadTelefones();
		};
	}
	
	public static FornecedorBo getInstance() {
		if(instance==null)
			instance = new FornecedorBo();
		return instance;
	}
	
	@Override
	public Fornecedor getById(Object id) throws Exception {
		return getByEntity(getDao().getByIdLoadAll(id), functionLoadAll);
	}
}
