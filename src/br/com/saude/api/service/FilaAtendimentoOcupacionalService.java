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
import br.com.saude.api.model.business.FilaAtendimentoOcupacionalBo;
import br.com.saude.api.model.business.validate.FilaAtendimentoOcupacionalValidator;
import br.com.saude.api.model.entity.filter.FilaAtendimentoOcupacionalFilter;
import br.com.saude.api.model.entity.po.FilaAtendimentoOcupacional;
import br.com.saude.api.util.RequestInterceptor;

@Path("fila-atendimento-ocupacional")
public class FilaAtendimentoOcupacionalService extends GenericServiceImpl<FilaAtendimentoOcupacional, FilaAtendimentoOcupacionalFilter, FilaAtendimentoOcupacionalBo>
							implements GenericService<FilaAtendimentoOcupacional, FilaAtendimentoOcupacionalFilter>{

	@Override
	protected FilaAtendimentoOcupacionalBo getBo() {
		return FilaAtendimentoOcupacionalBo.getInstance();
	}

	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=FilaAtendimentoOcupacionalValidator.class)
	@Override
	public Response save(FilaAtendimentoOcupacional filaAtendimentoOcupacional) {
		try {
			getBo().save(filaAtendimentoOcupacional);
			return Response.ok("Salvo com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}	
	
	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/save-fila-atendimento-ocupacional-retroativo")
	public Response saveFilaAtendimentoOcupacionalRetroativo(FilaAtendimentoOcupacionalFilter filter) {
		try {
			getBo().saveFilaAtendimentoOcupacionalRetroativo(filter);
			return Response.ok("Salvo com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@RequestInterceptor
	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list")
	public Response getList(FilaAtendimentoOcupacionalFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getListGeneric(filter);
	}
	
	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list-all")
	public Response getListAll(FilaAtendimentoOcupacionalFilter filter) throws Exception {
		return Response.ok(getBo().getListAll(filter).getGenericPagedList()).build();
	}

	@RequestInterceptor
	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	public Response getSelectList(FilaAtendimentoOcupacionalFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getSelectListGeneric(filter);
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
