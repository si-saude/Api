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
import br.com.saude.api.model.business.PlanoAlimentarBo;
import br.com.saude.api.model.business.validate.PlanoAlimentarValidator;
import br.com.saude.api.model.entity.filter.PlanoAlimentarFilter;
import br.com.saude.api.model.entity.po.PlanoAlimentar;
import br.com.saude.api.util.RequestInterceptor;

@Path("plano-alimentar")
public class PlanoAlimentarService extends
		GenericServiceImpl<PlanoAlimentar, PlanoAlimentarFilter, PlanoAlimentarBo>
		implements GenericService<PlanoAlimentar, PlanoAlimentarFilter> {

	@Override
	protected PlanoAlimentarBo getBo() {
		return PlanoAlimentarBo.getInstance();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass = PlanoAlimentarValidator.class)
	@Override
	public Response save(PlanoAlimentar planoAlimentar) {
		try {
			PlanoAlimentarBo.getInstance().save(planoAlimentar);
			return Response.ok("Salvo com sucesso.").build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/verify-plano-alimentar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass = PlanoAlimentarValidator.class)
	public Response verifyPlanoAlimentar(PlanoAlimentarFilter filter) {
		try {
			return Response.ok(PlanoAlimentarBo.getInstance().verifyPlanoAlimentar(filter)).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Path("/get-ne")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass = PlanoAlimentarValidator.class)
	public Response getNe(PlanoAlimentar planoAlimentar) {
		try {
			return Response.ok(PlanoAlimentarBo.getInstance().getNe(planoAlimentar)).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list")
	@Override
	public Response getList(PlanoAlimentarFilter filter)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, Exception {
		return super.getListGeneric(filter);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	@Override
	public Response getSelectList(PlanoAlimentarFilter filter)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, Exception {
		return super.getSelectListGeneric(filter);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response get(@QueryParam("id") String id) throws Exception {
		return super.getGeneric(new Integer(id));
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete")
	@Override
	public Response delete(Object id) {
		return super.deleteGeneric(new Integer(id.toString()));
	}
	
	
	@POST
	@RequestInterceptor
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/plano-pdf")
	public Response getPlanoPDF(PlanoAlimentar planoAlimentar) throws Exception {
		try {
			return Response.ok(getBo().getPlanoPDF(planoAlimentar)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
