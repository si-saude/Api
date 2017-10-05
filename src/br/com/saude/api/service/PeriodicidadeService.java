package br.com.saude.api.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.api.generic.CustomValidator;
import br.com.saude.api.model.business.PeriodicidadeBo;
import br.com.saude.api.model.business.validate.PeriodicidadeValidator;
import br.com.saude.api.model.entity.filter.PeriodicidadeFilter;
import br.com.saude.api.model.entity.po.Periodicidade;
import br.com.saude.api.util.RequestInterceptor;

@Path("periodicidade")
@RequestInterceptor
public class PeriodicidadeService {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list")
	public Response getList(PeriodicidadeFilter filter) throws Exception {
		return Response.ok(PeriodicidadeBo.getInstance().getList(filter).getGenericPagedList()).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	public Response getSelectList(PeriodicidadeFilter filter) throws Exception {
		return Response.ok(PeriodicidadeBo.getInstance().getSelectList(filter)).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("id") int id) throws Exception{
		return Response.ok(PeriodicidadeBo.getInstance().getById(id)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=PeriodicidadeValidator.class, entityClass=Periodicidade.class)
	public Response save(Periodicidade periodicidade) {
		try {
			PeriodicidadeBo.getInstance().save(periodicidade);
			return Response.ok("Salvo com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete")
	public Response delete(int id) {
		try {
			PeriodicidadeBo.getInstance().delete(id);
			return Response.ok("Removido com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}		
	}
}
