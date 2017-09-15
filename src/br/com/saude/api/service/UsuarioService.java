package br.com.saude.api.service;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.api.model.business.UsuarioBo;
import br.com.saude.api.model.entity.filter.UsuarioFilter;
import br.com.saude.api.model.entity.po.Usuario;
import br.com.saude.api.util.RequestInterceptor;

@Path("usuario")
public class UsuarioService {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response login(@Valid UsuarioFilter filter) throws Exception {
		return Response.ok(UsuarioBo.getInstance().getFirstToAutenticacao(filter) ).build();
	}
	
	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list")
	public Response getList(UsuarioFilter filter) throws Exception {
		return  Response.ok(UsuarioBo.getInstance().getList(filter)).build();
	}
	
	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list/perfis")
	public Response getListLoadPerfis(UsuarioFilter filter) throws Exception {
		return  Response.ok(UsuarioBo.getInstance().getListLoadPerfis(filter)).build();
	}
	
	@RequestInterceptor
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/simpleGet")
	public Response simpleGet(@QueryParam("id") int id) throws Exception{
		return Response.ok(UsuarioBo.getInstance().getById(id)).build();
	}
	
	@RequestInterceptor
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("id") int id) throws Exception{
		return Response.ok(UsuarioBo.getInstance().getByIdLoadPerfis(id)).build();
	}
	
	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(@Valid Usuario usuario) {
		try {
			return Response.ok(UsuarioBo.getInstance().save(usuario)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@RequestInterceptor
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(int id) {
		try {
			UsuarioBo.getInstance().delete(id);
			return Response.ok().build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
