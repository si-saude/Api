package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.EmailFilter;
import br.com.saude.api.model.entity.po.Email;

public class EmailBuilder extends GenericEntityBuilder<Email,EmailFilter> {
	private Function<Map<String,Email>,Email> loadDestinatarios;

	
	public static EmailBuilder newInstance(Email email) {
		return new EmailBuilder(email);
	}
	
	public static EmailBuilder newInstance(List<Email> emails) {
		return new EmailBuilder(emails);
	}
	
	private EmailBuilder(List<Email> emails) {
		super(emails);
	}

	private EmailBuilder(Email email) {
		super(email);
	}

	@Override
	protected void initializeFunctions() {
		this.loadDestinatarios = emails -> {
			if(emails.get("origem").getDestinatarios() != null) {
				emails.get("destino").setDestinatarios(EmpregadoBuilder
											.newInstance(emails.get("origem").getDestinatarios())
											.getEntityList());
			}
			return emails.get("destino");
		};
	}

	@Override
	protected Email clone(Email email) {
		Email cloneEmail = new Email();
		
		cloneEmail.setId(email.getId());
		cloneEmail.setVersion(email.getVersion());
		cloneEmail.setAnexos(email.getAnexos());
		cloneEmail.setAssunto(email.getAssunto());
		cloneEmail.setConteudo(email.getConteudo());
		cloneEmail.setDataEnvio(email.getDataEnvio());
		
		return cloneEmail;
	}

	@Override
	public Email cloneFromFilter(EmailFilter filter) {
		return null;
	}
	
	public EmailBuilder loadDestinatario() {
		return (EmailBuilder) this.loadProperty(this.loadDestinatarios);
	}

}
