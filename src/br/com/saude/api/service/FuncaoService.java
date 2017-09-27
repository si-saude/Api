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
import br.com.saude.api.model.business.FuncaoBo;
import br.com.saude.api.model.business.validate.FuncaoValidator;
import br.com.saude.api.model.entity.filter.FuncaoFilter;
import br.com.saude.api.model.entity.po.Funcao;
import br.com.saude.api.util.RequestInterceptor;

@Path("funcao")
@RequestInterceptor
public class FuncaoService {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list")
	public Response getList(FuncaoFilter filter) throws Exception {
		return Response.ok(FuncaoBo.getInstance().getList(filter).getGenericPagedList()).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	public Response getSelectList(FuncaoFilter filter) throws Exception {
		return Response.ok(FuncaoBo.getInstance().getSelectList(filter)).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("id") int id) throws Exception{
		return Response.ok(FuncaoBo.getInstance().getById(id)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=FuncaoValidator.class, entityClass=Funcao.class)
	public Response save(Funcao funcao) {
		try {
			FuncaoBo.getInstance().save(funcao);
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
			FuncaoBo.getInstance().delete(id);
			return Response.ok("Removido com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
