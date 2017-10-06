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
import br.com.saude.api.model.business.IndicadorRiscoErgonomicoBo;
import br.com.saude.api.model.business.validate.IndicadorRiscoValidator;
import br.com.saude.api.model.entity.filter.IndicadorRiscoFilter;
import br.com.saude.api.model.entity.po.IndicadorRiscoErgonomico;
import br.com.saude.api.util.RequestInterceptor;

@Path("indicador-risco-ergonomico")
@RequestInterceptor
public class IndicadorRiscoErgonomicoService {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list")
	public Response getList(IndicadorRiscoFilter filter) throws Exception {
		return Response.ok(IndicadorRiscoErgonomicoBo.getInstance()
													.getList(filter).getGenericPagedList()).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	public Response getSelectList(IndicadorRiscoFilter filter) throws Exception {
		return Response.ok(IndicadorRiscoErgonomicoBo.getInstance().getSelectList(filter)).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("id") int id) throws Exception{
		return Response.ok(IndicadorRiscoErgonomicoBo.getInstance().getById(id)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=IndicadorRiscoValidator.class, entityClass=IndicadorRiscoErgonomico.class)
	public Response save(IndicadorRiscoErgonomico indicadorRiscoErgonomico) {
		try {
			IndicadorRiscoErgonomicoBo.getInstance().save(indicadorRiscoErgonomico);
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
			IndicadorRiscoErgonomicoBo.getInstance().delete(id);
			return Response.ok("Removido com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
