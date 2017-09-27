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
import br.com.saude.api.model.business.CursoBo;
import br.com.saude.api.model.business.validate.CursoValidator;
import br.com.saude.api.model.entity.filter.CursoFilter;
import br.com.saude.api.model.entity.po.Curso;
import br.com.saude.api.util.RequestInterceptor;

@Path("curso")
@RequestInterceptor
public class CursoService {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list")
	public Response getList(CursoFilter filter) throws Exception {
		return Response.ok(CursoBo.getInstance().getList(filter).getGenericPagedList()).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	public Response getSelectList(CursoFilter filter) throws Exception {
		return Response.ok(CursoBo.getInstance().getSelectList(filter)).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("id") int id) throws Exception{
		return Response.ok(CursoBo.getInstance().getById(id)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=CursoValidator.class, entityClass=Curso.class)
	public Response save(Curso curso) {
		try {
			CursoBo.getInstance().save(curso);
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
			CursoBo.getInstance().delete(id);
			return Response.ok("Removido com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
