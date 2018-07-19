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
import br.com.saude.api.model.business.AvaliacaoHigieneOcupacionalBo;
import br.com.saude.api.model.business.validate.AvaliacaoHigieneOcupacionalValidator;
import br.com.saude.api.model.entity.filter.AvaliacaoHigieneOcupacionalFilter;
import br.com.saude.api.model.entity.po.AvaliacaoHigieneOcupacional;

@Path("avaliacao-higiene-ocupacional")
public class AvaliacaoHigieneOcupacionalService extends GenericServiceImpl<AvaliacaoHigieneOcupacional, AvaliacaoHigieneOcupacionalFilter, AvaliacaoHigieneOcupacionalBo>
		implements GenericService<AvaliacaoHigieneOcupacional, AvaliacaoHigieneOcupacionalFilter> {

	@Override
	protected AvaliacaoHigieneOcupacionalBo getBo() {
		return AvaliacaoHigieneOcupacionalBo.getInstance();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass = AvaliacaoHigieneOcupacionalValidator.class)
	@Override
	public Response save(AvaliacaoHigieneOcupacional avaliacaoHigieneOcupacional) {
		try {
			AvaliacaoHigieneOcupacionalBo.getInstance().save(avaliacaoHigieneOcupacional);
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
	public Response getList(AvaliacaoHigieneOcupacionalFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getListGeneric(filter);
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	public Response getSelectList(AvaliacaoHigieneOcupacionalFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getSelectListGeneric(filter);
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("id") String id) throws Exception {
		return super.getGeneric(Long.parseLong(id));
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete")
	public Response delete(Object id) {
		return super.deleteGeneric(Long.parseLong(id.toString()));
	}

}
