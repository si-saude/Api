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
import br.com.saude.api.model.business.FilaEsperaOcupacionalBo;
import br.com.saude.api.model.business.validate.FilaEsperaOcupacionalValidator;
import br.com.saude.api.model.entity.filter.FilaEsperaOcupacionalFilter;
import br.com.saude.api.model.entity.po.FilaEsperaOcupacional;
import br.com.saude.api.util.RequestInterceptor;

@Path("FilaEsperaOcupacional")
@RequestInterceptor
public class FilaEsperaOcupacionalService extends GenericServiceImpl<FilaEsperaOcupacional, FilaEsperaOcupacionalFilter, FilaEsperaOcupacionalBo>
							implements GenericService<FilaEsperaOcupacional, FilaEsperaOcupacionalFilter>{

	@Override
	protected FilaEsperaOcupacionalBo getBo() {
		return FilaEsperaOcupacionalBo.getInstance();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=FilaEsperaOcupacionalValidator.class)
	@Override
	public Response save(FilaEsperaOcupacional filaEsperaOcupacional) {
		try {
			FilaEsperaOcupacionalBo.getInstance().save(filaEsperaOcupacional);
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
	public Response getList(FilaEsperaOcupacionalFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getListGeneric(filter);
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	public Response getSelectList(FilaEsperaOcupacionalFilter filter) throws InstantiationException, IllegalAccessException,
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
