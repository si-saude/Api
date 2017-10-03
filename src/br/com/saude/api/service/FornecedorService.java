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
import br.com.saude.api.model.business.FornecedorBo;
import br.com.saude.api.model.business.validate.FornecedorValidator;
import br.com.saude.api.model.entity.filter.FornecedorFilter;
import br.com.saude.api.model.entity.po.Fornecedor;
import br.com.saude.api.util.RequestInterceptor;

@Path("fornecedor")
@RequestInterceptor
public class FornecedorService {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list")
	public Response getList(FornecedorFilter filter) throws Exception {
		return Response.ok(FornecedorBo.getInstance().getList(filter).getGenericPagedList()).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("id") int id) throws Exception{
		return Response.ok(FornecedorBo.getInstance().getById(id)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=FornecedorValidator.class, entityClass=Fornecedor.class)
	public Response save(Fornecedor fornecedor) {
		try {
			FornecedorBo.getInstance().save(fornecedor);
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
			FornecedorBo.getInstance().delete(id);
			return Response.ok("Removido com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
