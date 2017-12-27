package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.ServicoFilter;
import br.com.saude.api.model.entity.po.Servico;

public class ServicoExampleBuilder extends GenericExampleBuilder<Servico, ServicoFilter> {

	public static ServicoExampleBuilder newInstance(ServicoFilter filter) {
		return new ServicoExampleBuilder(filter);
	}
	
	private ServicoExampleBuilder(ServicoFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addCodigo();
		addGrupo();
		addNome();
		addPublico();
		addIntervalo();
		addQuantidadeSolicitacaoIntervalo();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		addPublico();
	}
	
	private void addNome() {
		if(this.filter.getNome()!=null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
	
	private void addCodigo() {
		if(this.filter.getCodigo()!=null)
			this.entity.setCodigo(Helper.filterLike(this.filter.getCodigo()));
	}
	
	private void addGrupo() {
		if(this.filter.getGrupo()!=null)
			this.entity.setGrupo(Helper.filterLike(this.filter.getGrupo()));
	}
	
	private void addPublico() {
		this.entity.setPublico(this.addBoolean("publico", this.filter.getPublico()));
	}
	
	private void addIntervalo() {
		if(this.filter.getIntervalo() > 0)
			this.entity.setIntervalo(this.filter.getIntervalo());
	}
	
	private void addQuantidadeSolicitacaoIntervalo() {
		if(this.filter.getQuantidadeSolicitacaoIntervalo() > 0)
			this.entity.setQuantidadeSolicitacaoIntervalo(this.filter.getQuantidadeSolicitacaoIntervalo());
	}

}
