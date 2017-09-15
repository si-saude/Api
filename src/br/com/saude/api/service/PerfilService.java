package br.com.saude.api.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.api.generic.CustomValidator;
import br.com.saude.api.model.business.PerfilBo;
import br.com.saude.api.model.business.validate.PerfilValidator;
import br.com.saude.api.model.entity.filter.PerfilFilter;
import br.com.saude.api.model.entity.po.Perfil;
import br.com.saude.api.util.RequestInterceptor;

@Path("perfil")
@RequestInterceptor
public class PerfilService {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list")
	public Response getList(PerfilFilter filter) throws Exception {
		return  Response.ok(PerfilBo.getInstance().getList(filter)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list/permissoes")
	public Response getListLoadPermissoes(PerfilFilter filter) throws Exception {
		return  Response.ok(PerfilBo.getInstance().getListLoadPermissoes(filter)).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/simpleGet")
	public Response simpleGet(@QueryParam("id") int id) throws Exception{
		return Response.ok(PerfilBo.getInstance().getById(id)).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("id") int id) throws Exception{
		return Response.ok(PerfilBo.getInstance().getByIdLoadPermissoes(id)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=PerfilValidator.class, entityClass=Perfil.class)
	public Response save(Perfil perfil) {		
		try {
			return Response.ok(PerfilBo.getInstance().save(perfil)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(int id) {
		try {
			PerfilBo.getInstance().delete(id);
			return Response.ok().build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
