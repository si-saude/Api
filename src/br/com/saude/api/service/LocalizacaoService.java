package br.com.saude.api.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.api.generic.CustomValidator;
import br.com.saude.api.generic.GenericService;
import br.com.saude.api.model.business.LocalizacaoBo;
import br.com.saude.api.model.business.validate.LocalizacaoValidator;
import br.com.saude.api.model.entity.filter.LocalizacaoFilter;
import br.com.saude.api.model.entity.po.Localizacao;
import br.com.saude.api.util.RequestInterceptor;

@Path("localizacao")
@RequestInterceptor
public class LocalizacaoService extends GenericService<Localizacao,LocalizacaoFilter,LocalizacaoBo> {
	
	@Override
	protected LocalizacaoBo getBo() {
		return LocalizacaoBo.getInstance();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=LocalizacaoValidator.class, entityClass=Localizacao.class)
	@Override
	public Response save(Localizacao localizacao) {
		try {
			LocalizacaoBo.getInstance().save(localizacao);
			return Response.ok("Salvo com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
