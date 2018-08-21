package br.com.saude.api.model.creation.factory.entity;

import br.com.saude.api.model.entity.po.Email;

public class EmailFactory {
	private Email email;
	
	public static EmailFactory newInstance(){
		return new EmailFactory();
	}
	
	private EmailFactory() {
		this.email = new Email();
	}
	
	public EmailFactory conteudo(String conteudo) {
		this.email.setConteudo(conteudo);
		return this;
	}
	
	public EmailFactory assunto(String assunto) {
		this.email.setAssunto(assunto);
		return this;
	}
	
	public Email get() {
		return this.email;
	}

}
