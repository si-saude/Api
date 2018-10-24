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
import br.com.saude.api.model.business.IndicadorConhecimentoAlimentarBo;
import br.com.saude.api.model.business.validate.IndicadorConhecimentoAlimentarValidator;
import br.com.saude.api.model.entity.filter.IndicadorConhecimentoAlimentarFilter;
import br.com.saude.api.model.entity.po.IndicadorConhecimentoAlimentar;
import br.com.saude.api.util.RequestInterceptor;

@Path("indicador-conhecimento-alimentar")
@RequestInterceptor
public class IndicadorConhecimentoAlimentarService extends GenericServiceImpl<IndicadorConhecimentoAlimentar, IndicadorConhecimentoAlimentarFilter, IndicadorConhecimentoAlimentarBo>
		implements GenericService<IndicadorConhecimentoAlimentar, IndicadorConhecimentoAlimentarFilter> {

	@Override
	protected IndicadorConhecimentoAlimentarBo getBo() {
		return IndicadorConhecimentoAlimentarBo.getInstance();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass = IndicadorConhecimentoAlimentarValidator.class)
	@Override
	public Response save(IndicadorConhecimentoAlimentar indicadorConhecimentoAlimentar) {
		try {
			IndicadorConhecimentoAlimentarBo.getInstance().save(indicadorConhecimentoAlimentar);
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
	public Response getList(IndicadorConhecimentoAlimentarFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getListGeneric(filter);
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	public Response getSelectList(IndicadorConhecimentoAlimentarFilter filter) throws InstantiationException, IllegalAccessException,
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

}
