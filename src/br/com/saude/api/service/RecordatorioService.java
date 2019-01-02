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
import br.com.saude.api.model.business.RecordatorioBo;
import br.com.saude.api.model.business.validate.RecordatorioValidator;
import br.com.saude.api.model.entity.filter.RecordatorioFilter;
import br.com.saude.api.model.entity.po.Recordatorio;

@Path("recordatorio")
public class RecordatorioService extends
		GenericServiceImpl<Recordatorio, RecordatorioFilter, RecordatorioBo>
		implements GenericService<Recordatorio, RecordatorioFilter> {

	@Override
	protected RecordatorioBo getBo() {
		return RecordatorioBo.getInstance();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass = RecordatorioValidator.class)
	@Override
	public Response save(Recordatorio recordatorio) {
		try {
			RecordatorioBo.getInstance().save(recordatorio);
			return Response.ok("Salvo com sucesso.").build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/get-ne")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass = RecordatorioValidator.class)
	public Response getNe(Recordatorio recordatorio) {
		try {
			return Response.ok(RecordatorioBo.getInstance().getNe(recordatorio)).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list")
	@Override
	public Response getList(RecordatorioFilter filter)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, Exception {
		return super.getListGeneric(filter);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	@Override
	public Response getSelectList(RecordatorioFilter filter)
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
}
