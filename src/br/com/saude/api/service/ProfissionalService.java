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
import br.com.saude.api.model.business.ProfissionalBo;
import br.com.saude.api.model.business.validate.ProfissionalValidator;
import br.com.saude.api.model.entity.filter.ProfissionalFilter;
import br.com.saude.api.model.entity.po.Profissional;
import br.com.saude.api.util.RequestInterceptor;

@Path("profissional")
@RequestInterceptor
public class ProfissionalService 
				extends GenericServiceImpl<Profissional,ProfissionalFilter,ProfissionalBo>
				implements GenericService<Profissional,ProfissionalFilter>{

	@Override
	protected ProfissionalBo getBo() {
		return ProfissionalBo.getInstance();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=ProfissionalValidator.class)
	@Override
	public Response save(Profissional profissional) {
		try {
			ProfissionalBo.getInstance().save(profissional);
			return Response.ok("Salvo com sucesso.").build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list")
	@Override
	public Response getList(ProfissionalFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getListGeneric(filter);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	@Override
	public Response getSelectList(ProfissionalFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getSelectListGeneric(filter);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectListSimples")
	public Response getSelectListSimples(ProfissionalFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return Response.ok(ProfissionalBo.getInstance().getSelectListSimples(filter)).build();
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
}
