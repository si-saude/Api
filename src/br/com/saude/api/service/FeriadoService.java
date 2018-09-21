package br.com.saude.api.service;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
import br.com.saude.api.model.business.FeriadoBo;
import br.com.saude.api.model.business.validate.FeriadoValidator;
import br.com.saude.api.model.entity.filter.FeriadoFilter;
import br.com.saude.api.model.entity.po.Feriado;
import br.com.saude.api.util.RequestInterceptor;

@Path("feriado")
@RequestInterceptor
public class FeriadoService extends GenericServiceImpl<Feriado,FeriadoFilter,FeriadoBo> 
								implements GenericService<Feriado,FeriadoFilter>{

	@Override
	protected FeriadoBo getBo() {
		return FeriadoBo.getInstance();
	}
	
	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list")
	public Response getList(FeriadoFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getListGeneric(filter);
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	public Response getSelectList(FeriadoFilter filter) throws InstantiationException, IllegalAccessException,
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
	@CustomValidator(validatorClass=FeriadoValidator.class)
	@Override
	public Response save(Feriado feriado) {
		try {
			FeriadoBo.getInstance().save(feriado);
			return Response.ok("Salvo com sucesso.").build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get-date-after-nday")
	public Response getDateAfterNDays(@QueryParam("data") String data, @QueryParam("dias") int dias) {
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			calendar.setTime(sdf.parse(data));
			FeriadoBo.getInstance().getValidDates(calendar, dias);
			return Response.ok(calendar.getTime()).build();	
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
