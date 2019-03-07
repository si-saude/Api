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
import br.com.saude.api.model.business.AvaliacaoFisicaBo;
import br.com.saude.api.model.business.validate.AvaliacaoFisicaValidator;
import br.com.saude.api.model.entity.filter.AvaliacaoFisicaFilter;
import br.com.saude.api.model.entity.po.AvaliacaoFisica;
import br.com.saude.api.util.RequestInterceptor;

@Path("avaliacao-fisica")
@RequestInterceptor
public class AvaliacaoFisicaService extends GenericServiceImpl<AvaliacaoFisica, AvaliacaoFisicaFilter, AvaliacaoFisicaBo>
	implements GenericService<AvaliacaoFisica, AvaliacaoFisicaFilter>{
	
	@Override
	protected AvaliacaoFisicaBo getBo() {
		return AvaliacaoFisicaBo.getInstance();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=AvaliacaoFisicaValidator.class)
	@Override
	public Response save(AvaliacaoFisica AvaliacaoFisica) {
		try {
			AvaliacaoFisicaBo.getInstance().save(AvaliacaoFisica);
			return Response.ok("Salvo com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list")
	public Response getList(AvaliacaoFisicaFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getListGeneric(filter);
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	public Response getSelectList(AvaliacaoFisicaFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getSelectListGeneric(filter);
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("id") String id) throws Exception {
		return super.getGeneric(new Long(id));
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete")
	public Response delete(Object id) {
		return super.deleteGeneric(new Long(id.toString()));
	}
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/get-relatorio-proaf-by-atendimento")
	public Response getRelatorioProaf(Long id) {
		try {
			return Response.ok(getBo().getRelatorioProafAtendimento(id)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
