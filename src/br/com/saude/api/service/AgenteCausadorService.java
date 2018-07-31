package br.com.saude.api.service;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.api.generic.CustomValidator;
import br.com.saude.api.generic.GenericService;
import br.com.saude.api.generic.GenericServiceImpl;
import br.com.saude.api.model.business.AgenteCausadorBo;
import br.com.saude.api.model.business.validate.AgenteCausadorValidator;
import br.com.saude.api.model.entity.filter.AgenteCausadorFilter;
import br.com.saude.api.model.entity.po.AgenteCausador;
import br.com.saude.api.util.RequestInterceptor;

@Path("agente-causador")
@RequestInterceptor
public class AgenteCausadorService
		extends GenericServiceImpl<AgenteCausador, AgenteCausadorFilter, AgenteCausadorBo>
		implements GenericService<AgenteCausador, AgenteCausadorFilter> {

	@Override
	protected AgenteCausadorBo getBo() {
		return AgenteCausadorBo.getInstance();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass = AgenteCausadorValidator.class)
	@Override
	public Response save(AgenteCausador agenteCausador) {
		try {
			AgenteCausadorBo.getInstance().save(agenteCausador);
			return Response.ok("Salvo com sucesso.").build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list")
	public Response getList(AgenteCausadorFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getListGeneric(filter);
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	public Response getSelectList(AgenteCausadorFilter filter)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, Exception {
		return super.getSelectListGeneric(filter);
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("id") String id) throws Exception {
		return super.getGeneric(new Integer(id));
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete")
	public Response delete(Object id) {
		return super.deleteGeneric(new Integer(id.toString()));
	}

	@POST
	@Path("/import")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response importFile(File arquivo) {
		try {
			AgenteCausadorBo.getInstance().importFile(arquivo);
			return Response.ok("Salvo com sucesso.").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}

	}

}
