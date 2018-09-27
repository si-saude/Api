package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.ClassificacaoAfastamentoBuilder;
import br.com.saude.api.model.creation.builder.example.ClassificacaoAfastamentoExampleBuilder;
import br.com.saude.api.model.entity.filter.ClassificacaoAfastamentoFilter;
import br.com.saude.api.model.entity.po.ClassificacaoAfastamento;
import br.com.saude.api.model.persistence.ClassificacaoAfastamentoDao;

public class ClassificacaoAfastamentoBo extends
		GenericBo<ClassificacaoAfastamento, ClassificacaoAfastamentoFilter, ClassificacaoAfastamentoDao, ClassificacaoAfastamentoBuilder, ClassificacaoAfastamentoExampleBuilder> {

	private static ClassificacaoAfastamentoBo instance;

	private ClassificacaoAfastamentoBo() {
		super();
	}

	@Override
	protected void initializeFunctions() {

	}

	public static ClassificacaoAfastamentoBo getInstance() {
		if (instance == null)
			instance = new ClassificacaoAfastamentoBo();
		return instance;
	}

}
