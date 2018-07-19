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
import br.com.saude.api.model.business.FichaColetaBo;
import br.com.saude.api.model.business.validate.FichaColetaValidator;
import br.com.saude.api.model.entity.filter.FichaColetaFilter;
import br.com.saude.api.model.entity.po.FichaColeta;
import br.com.saude.api.util.RequestInterceptor;

@Path("ficha-coleta")
@RequestInterceptor
public class FichaColetaService extends GenericServiceImpl<FichaColeta, FichaColetaFilter, FichaColetaBo>
		implements GenericService<FichaColeta, FichaColetaFilter> {

	@Override
	protected FichaColetaBo getBo() {
		return FichaColetaBo.getInstance();
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list")
	public Response getList(FichaColetaFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getListGeneric(filter);
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	public Response getSelectList(FichaColetaFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
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
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass = FichaColetaValidator.class)
	@Override
	public Response save(FichaColeta fichaColeta) {
		try {
			FichaColetaBo.getInstance().save(fichaColeta);
			return Response.ok("Salvo com sucesso.").build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}

}
