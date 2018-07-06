package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.CatFilter;
import br.com.saude.api.model.entity.po.Cat;

public class CatExampleBuilder extends GenericExampleBuilder<Cat, CatFilter> {

	public static CatExampleBuilder newInstance(CatFilter filter) {
		return new CatExampleBuilder(filter);
	}
	
	private CatExampleBuilder(CatFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addNumero();
		addCpf();
		addGravidade();
		addNome();
		addPartesCorpo();
		addRegime();
		addSexo();
		addTipoAcidente();
		addTipoCAT();
		addAfastamento();
		addCatSd2000();
		addComunicavelSus();
		addContratado();
		addFerimentoGraveConformeAnp();
		addRegistroSd2000();
	}

	@Override
	protected void createExampleSelectList() {
	}
	
	private void addNumero() {
		if(this.filter.getNumero() != null)
			this.entity.setNumero(Helper.filterLike(this.filter.getNumero()));
	}
	
	private void addCpf() {
		if(this.filter.getCpf() != null)
			this.entity.setCpf(Helper.filterLike(this.filter.getCpf()));
	}
	
	private void addGravidade() {
		if(this.filter.getGravidade() != null)
			this.entity.setGravidade(Helper.filterLike(this.filter.getGravidade()));
	}
	
	private void addNome() {
		if(this.filter.getNome() != null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
	
	private void addPartesCorpo() {
		if(this.filter.getPartesCorpo() != null)
			this.entity.setPartesCorpo(Helper.filterLike(this.filter.getPartesCorpo()));
	}
	
	private void addRegime() {
		if(this.filter.getRegime() != null)
			this.entity.setRegime(Helper.filterLike(this.filter.getRegime()));
	}
	
	private void addSexo() {
		if(this.filter.getSexo() != null)
			this.entity.setSexo(Helper.filterLike(this.filter.getSexo()));
	}
	
	private void addTipoAcidente() {
		if(this.filter.getTipoAcidente() != null)
			this.entity.setTipoAcidente(Helper.filterLike(this.filter.getTipoAcidente()));
	}
	
	private void addTipoCAT() {
		if(this.filter.getTipoCat() != null)
			this.entity.setTipoCat(Helper.filterLike(this.filter.getTipoCat()));
	}
	
	protected void addAfastamento() {
		this.entity.setAfastamento(this.addBoolean("afastamento", this.filter.getAfastamento()));
	}
	
	protected void addCatSd2000() {
		this.entity.setCatSd2000(this.addBoolean("catSd2000", this.filter.getCatSd2000()));
	}
	
	protected void addComunicavelSus() {
		this.entity.setComunicavelSus(this.addBoolean("comunicavelSus", this.filter.getComunicavelSus()));
	}
	
	protected void addContratado() {
		this.entity.setContratado(this.addBoolean("contratado", this.filter.getContratado()));
	}
	
	protected void addFerimentoGraveConformeAnp() {
		this.entity.setFerimentoGraveConformeAnp(this.addBoolean("ferimentoGraveConformeAnp", this.filter.getFerimentoGraveConformeAnp()));
	}
	
	protected void addRegistroSd2000() {
		this.entity.setRegistroSd2000(this.addBoolean("registroSd2000", this.filter.getRegistroSd2000()));
	}
	
}