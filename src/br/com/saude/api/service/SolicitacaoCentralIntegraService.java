package br.com.saude.api.service;

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
import br.com.saude.api.model.business.SolicitacaoCentralIntegraBo;
import br.com.saude.api.model.business.validate.SolicitacaoCentralIntegraValidator;
import br.com.saude.api.model.entity.filter.SolicitacaoCentralIntegraFilter;
import br.com.saude.api.model.entity.po.SolicitacaoCentralIntegra;
import br.com.saude.api.util.RequestInterceptor;

@Path("solicitacao-central-integra")
public class SolicitacaoCentralIntegraService extends GenericServiceImpl<SolicitacaoCentralIntegra, SolicitacaoCentralIntegraFilter, SolicitacaoCentralIntegraBo>
		implements GenericService<SolicitacaoCentralIntegra, SolicitacaoCentralIntegraFilter> {

	@Override
	protected SolicitacaoCentralIntegraBo getBo() {
		return SolicitacaoCentralIntegraBo.getInstance();
	}

	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass = SolicitacaoCentralIntegraValidator.class)
	@Override
	public Response save(SolicitacaoCentralIntegra solicitacao) {
		try {
			SolicitacaoCentralIntegra sCI = SolicitacaoCentralIntegraBo.getInstance().save(solicitacao);
			return Response.ok(sCI).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("registrar-solicitacao")
	public Response registrarSolicitacao(SolicitacaoCentralIntegra solicitacao) {
		try {
			SolicitacaoCentralIntegraBo.getInstance().registrarSolicitacao(solicitacao);
			return Response.ok("Salvo com sucesso.").build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}

	@RequestInterceptor
	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list")
	public Response getList(SolicitacaoCentralIntegraFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getListGeneric(filter);
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	public Response getSelectList(SolicitacaoCentralIntegraFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getSelectListGeneric(filter);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/get-anexo")
	public Response getAnexo(int id) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		try {
			return Response.ok(getBo().getAnexo(id)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@RequestInterceptor
	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("id") String id) throws Exception {
		return super.getGeneric(new Integer(id));
	}

	@RequestInterceptor
	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete")
	public Response delete(Object id) {
		return super.deleteGeneric(new Integer(id.toString()));
	}

}
