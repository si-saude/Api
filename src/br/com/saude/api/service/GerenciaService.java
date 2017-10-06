package br.com.saude.api.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.api.generic.CustomValidator;
import br.com.saude.api.generic.GenericService;
import br.com.saude.api.model.business.GerenciaBo;
import br.com.saude.api.model.business.validate.GerenciaValidator;
import br.com.saude.api.model.entity.filter.GerenciaFilter;
import br.com.saude.api.model.entity.po.Gerencia;
import br.com.saude.api.util.RequestInterceptor;

@Path("gerencia")
@RequestInterceptor
public class GerenciaService extends GenericService<Gerencia,GerenciaFilter,GerenciaBo> {

	@Override
	protected GerenciaBo getBo() {
		return GerenciaBo.getInstance();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=GerenciaValidator.class, entityClass=Gerencia.class)
	@Override
	public Response save(Gerencia gerencia) {
		try {
			GerenciaBo.getInstance().save(gerencia);
			return Response.ok("Salvo com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
