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

import br.com.saude.api.model.business.ExameBo;
import br.com.saude.api.model.entity.filter.ExameFilter;
import br.com.saude.api.model.entity.po.Exame;
import br.com.saude.api.util.RequestInterceptor;

@Path("exame")
@RequestInterceptor
public class ExameService {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list")
	public Response getList(ExameFilter filter) throws Exception{	
		return Response.ok(ExameBo.getInstance().getList(filter)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list/empregado")
	public Response getListLoadEmpregado(ExameFilter filter) throws Exception{	
		return Response.ok(ExameBo.getInstance().getListLoadEmpregado(filter)).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("id") int id) throws Exception{	
		return Response.ok(ExameBo.getInstance().getByIdLoadEmpregado(id)).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/simpleGet")
	public Response simpleGet(@QueryParam("id") int id) throws Exception{	
		return Response.ok(ExameBo.getInstance().getById(id)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(@Valid Exame exame) {
		try {
			return Response.ok(ExameBo.getInstance().save(exame)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(int id) {
		try {
			ExameBo.getInstance().delete(id);
			return Response.ok().build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
