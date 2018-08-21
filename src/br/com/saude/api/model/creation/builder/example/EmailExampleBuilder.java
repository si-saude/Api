package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.EmailFilter;
import br.com.saude.api.model.entity.po.Email;

public class EmailExampleBuilder extends GenericExampleBuilder<Email,EmailFilter> {
	public static EmailExampleBuilder newInstance(EmailFilter filter) {
		return new EmailExampleBuilder(filter);
	}
	
	private EmailExampleBuilder(EmailFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addId();
		addAnexos(); 
		addDataEnvio();
		addConteudo();
		addAssunto();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		addId();
		addAnexos();
		addDataEnvio();
		addConteudo();
		addAssunto();
	}
	
	private void addAnexos() {
		if(this.filter.getAnexos() != null)
			this.entity.setAnexos(Helper.filterLike(this.filter.getAnexos()));
	}
	
	private void addConteudo() {
		if(this.filter.getConteudo() != null)
			this.entity.setConteudo(Helper.filterLike(this.filter.getConteudo()));
	}
	
	private void addAssunto() {
		if(this.filter.getAssunto() != null)
			this.entity.setAssunto(Helper.filterLike(this.filter.getAssunto()));
	}
	
	private void addId() {
		if(this.filter.getId() > 0)
			this.criterions.add(Restrictions.eq("id", (int)this.filter.getId()));
	}
	
	private void addDataEnvio() {
		this.addData("dataEnvio", this.filter.getDataEnvio());
	}
}
