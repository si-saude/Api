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

//import org.glassfish.jersey.media.multipart.FormDataParam;

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
	public Response save(Profissional profissional) throws Exception {
		ProfissionalBo.getInstance().save(profissional);
		return Response.ok("Salvo com sucesso.").build();
	}
	
	
//	public Response salvar(@FormDataParam("assinatura") InputStream assinatura, 
//						@FormDataParam("profissional") Profissional profissional) {
//		try {
//			Response sucesso = this.save(profissional);
//			
//			//SALVAR ASSINATURA
//			
//			return sucesso;
//		}catch (Exception e) {
//			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
//		}
//	}
	
	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list")
	public Response getList(ProfissionalFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getListGeneric(filter);
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	public Response getSelectList(ProfissionalFilter filter) throws InstantiationException, IllegalAccessException,
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
