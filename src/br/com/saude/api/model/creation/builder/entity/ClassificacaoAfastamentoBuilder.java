package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.ClassificacaoAfastamentoFilter;
import br.com.saude.api.model.entity.po.ClassificacaoAfastamento;

public class ClassificacaoAfastamentoBuilder extends GenericEntityBuilder<ClassificacaoAfastamento,ClassificacaoAfastamentoFilter> {

	public static ClassificacaoAfastamentoBuilder newInstance(ClassificacaoAfastamento classificacaoAfastamento) {
		return new ClassificacaoAfastamentoBuilder(classificacaoAfastamento);
	}
	
	public static ClassificacaoAfastamentoBuilder newInstance(List<ClassificacaoAfastamento> classificacaoAfastamentos) {
		return new ClassificacaoAfastamentoBuilder(classificacaoAfastamentos);
	}
	
	private ClassificacaoAfastamentoBuilder(List<ClassificacaoAfastamento> classificacaoAfastamentos) {
		super(classificacaoAfastamentos);
	}

	private ClassificacaoAfastamentoBuilder(ClassificacaoAfastamento classificacaoAfastamento) {
		super(classificacaoAfastamento);
	}

	@Override
	protected void initializeFunctions() {
		
	}
	
	@Override
	protected ClassificacaoAfastamento clone(ClassificacaoAfastamento classificacaoAfastamento) {
		ClassificacaoAfastamento newClassificacaoAfastamento = new ClassificacaoAfastamento();
		
		newClassificacaoAfastamento.setId(classificacaoAfastamento.getId());
		newClassificacaoAfastamento.setGeraAfastamento(classificacaoAfastamento.isGeraAfastamento());
		newClassificacaoAfastamento.setDescricao(classificacaoAfastamento.getDescricao());
		newClassificacaoAfastamento.setVersion(classificacaoAfastamento.getVersion());
		
		return newClassificacaoAfastamento;
	}

	@Override
	public ClassificacaoAfastamento cloneFromFilter(ClassificacaoAfastamentoFilter filter) {
		return null;
	}

}
