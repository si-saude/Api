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
import br.com.saude.api.model.business.GheBo;
import br.com.saude.api.model.business.validate.GheValidator;
import br.com.saude.api.model.entity.filter.GheFilter;
import br.com.saude.api.model.entity.po.Ghe;
import br.com.saude.api.util.RequestInterceptor;

@Path("ghe")
@RequestInterceptor
public class GheService 
				extends GenericServiceImpl<Ghe, GheFilter, GheBo>
				implements GenericService<Ghe, GheFilter> {

	@Override
	protected GheBo getBo() {
		return GheBo.getInstance();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=GheValidator.class)
	@Override
	public Response save(Ghe ghe) {
		try {
			GheBo.getInstance().save(ghe);
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
	public Response getList(GheFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getListGeneric(filter);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list-ativo")
	public Response getListAtivos(GheFilter filter) {

			try {
				return Response.ok(GheBo.getInstance().getListGheAtivos(filter).getGenericPagedList()).build();
			}catch (Exception e) {
				return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
			}
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	public Response getSelectList(GheFilter filter) throws InstantiationException, IllegalAccessException,
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
	@Path("/import")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response importFile(File arquivo) {
		try {
			GheBo.getInstance().importFile(arquivo);
			return Response.ok("Salvo com sucesso.").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
