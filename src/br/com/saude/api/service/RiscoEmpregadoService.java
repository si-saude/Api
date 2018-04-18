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
import br.com.saude.api.model.business.RiscoEmpregadoBo;
import br.com.saude.api.model.business.validate.RiscoEmpregadoValidator;
import br.com.saude.api.model.entity.filter.RiscoEmpregadoFilter;
import br.com.saude.api.model.entity.po.RiscoEmpregado;
import br.com.saude.api.util.RequestInterceptor;

@Path("risco-empregado")
@RequestInterceptor
public class RiscoEmpregadoService extends GenericServiceImpl<RiscoEmpregado, RiscoEmpregadoFilter, RiscoEmpregadoBo>
implements GenericService<RiscoEmpregado, RiscoEmpregadoFilter>{
	
	@Override
	protected RiscoEmpregadoBo getBo() {
		return RiscoEmpregadoBo.getInstance();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=RiscoEmpregadoValidator.class)
	@Override
	public Response save(RiscoEmpregado riscoEmpregado) {
		try {
			RiscoEmpregadoBo.getInstance().save(riscoEmpregado);
			return Response.ok("Salvo com sucesso.").build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=RiscoEmpregadoValidator.class)
	@Path("/save-reavaliacao")
	public Response saveReavaliacao(RiscoEmpregado riscoEmpregado) {
		try {
			RiscoEmpregadoBo.getInstance().saveReavaliacao(riscoEmpregado);
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
	public Response getList(RiscoEmpregadoFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getListGeneric(filter);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list-to-copy")
	public Response getListToCopy(RiscoEmpregadoFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return Response.ok(getBo().getListToCopy(filter).getGenericPagedList()).build();
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	public Response getSelectList(RiscoEmpregadoFilter filter) throws InstantiationException, IllegalAccessException,
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
