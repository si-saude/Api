package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.NotificacaoFilter;
import br.com.saude.api.model.entity.po.Notificacao;

public class NotificacaoBuilder extends GenericEntityBuilder<Notificacao, NotificacaoFilter> {

	private Function<Map<String,Notificacao>,Notificacao> loadUsuario;
	
	private NotificacaoBuilder(List<Notificacao> notificacoes) {
		super(notificacoes);
	}

	private NotificacaoBuilder(Notificacao notificacao) {
		super(notificacao);
	}

	public static NotificacaoBuilder newInstance(Notificacao notificacao) {
		return new NotificacaoBuilder(notificacao);
	}
	
	public static NotificacaoBuilder newInstance(List<Notificacao> notificacoes) {
		return new NotificacaoBuilder(notificacoes);
	}

	@Override
	protected void initializeFunctions() {
		this.loadUsuario = notificacoes -> {
			
			if(notificacoes.get("origem").getUsuario() != null )
				notificacoes.get("destino").setUsuario(UsuarioBuilder
						.newInstance(notificacoes.get("origem").getUsuario())
						.getEntity());
			
			return notificacoes.get("destino");
		};
	}
	
	public NotificacaoBuilder loadUsuario() {
		return (NotificacaoBuilder) this.loadProperty(this.loadUsuario);
	}

	@Override
	protected Notificacao clone(Notificacao notificacao) {
		
		Notificacao newNotificacao = new Notificacao();
		newNotificacao.setId(notificacao.getId());
		newNotificacao.setDescricao(notificacao.getDescricao());
		newNotificacao.setVersion(notificacao.getVersion());
		
		return newNotificacao;
	}

	@Override
	public Notificacao cloneFromFilter(NotificacaoFilter filter) {
		return null;
	}
	
	
}
