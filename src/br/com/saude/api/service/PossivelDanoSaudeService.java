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
import br.com.saude.api.model.business.PossivelDanoSaudeBo;
import br.com.saude.api.model.business.validate.PossivelDanoSaudeValidator;
import br.com.saude.api.model.entity.filter.PossivelDanoSaudeFilter;
import br.com.saude.api.model.entity.po.PossivelDanoSaude;
import br.com.saude.api.util.RequestInterceptor;

@Path("possivel-dano-saude")
@RequestInterceptor
public class PossivelDanoSaudeService extends GenericServiceImpl<PossivelDanoSaude, PossivelDanoSaudeFilter, PossivelDanoSaudeBo>
							implements GenericService<PossivelDanoSaude, PossivelDanoSaudeFilter>{

	@Override
	protected PossivelDanoSaudeBo getBo() {
		return PossivelDanoSaudeBo.getInstance();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=PossivelDanoSaudeValidator.class)
	@Override
	public Response save(PossivelDanoSaude possivelDanoSaude) {
		try {
			PossivelDanoSaudeBo.getInstance().save(possivelDanoSaude);
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
	public Response getList(PossivelDanoSaudeFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getListGeneric(filter);
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	public Response getSelectList(PossivelDanoSaudeFilter filter) throws InstantiationException, IllegalAccessException,
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
			PossivelDanoSaudeBo.getInstance().importFile(arquivo);
			return Response.ok("Salvo com sucesso.").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
		
	}
}
