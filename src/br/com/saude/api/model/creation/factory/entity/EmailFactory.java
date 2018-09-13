package br.com.saude.api.model.creation.factory.entity;

import java.util.Date;
import java.util.List;

import br.com.saude.api.model.entity.po.Email;
import br.com.saude.api.model.entity.po.Empregado;

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
	
	public EmailFactory anexos(String anexos) {
		this.email.setAnexos(anexos);
		return this;
	}
	
	public EmailFactory dataEnvio(Date dataEnvio) {
		this.email.setDataEnvio(dataEnvio);
		return this;
	}
	
	public EmailFactory destinatarios(List<Empregado> destinatarios) {
		this.email.setDestinatarios(destinatarios);
		return this;
	}
	
	public Email get() {
		return this.email;
	}

}
