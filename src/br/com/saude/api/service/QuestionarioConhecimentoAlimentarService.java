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
import br.com.saude.api.model.business.QuestionarioConhecimentoAlimentarBo;
import br.com.saude.api.model.business.validate.QuestionarioConhecimentoAlimentarValidator;
import br.com.saude.api.model.entity.filter.QuestionarioConhecimentoAlimentarFilter;
import br.com.saude.api.model.entity.po.QuestionarioConhecimentoAlimentar;
import br.com.saude.api.util.RequestInterceptor;

@Path("questionario-conhecimento-alimentar")
@RequestInterceptor
public class QuestionarioConhecimentoAlimentarService 
	extends GenericServiceImpl<QuestionarioConhecimentoAlimentar,QuestionarioConhecimentoAlimentarFilter,QuestionarioConhecimentoAlimentarBo>
	implements GenericService<QuestionarioConhecimentoAlimentar,QuestionarioConhecimentoAlimentarFilter>{
	
	@Override
	protected QuestionarioConhecimentoAlimentarBo getBo() {
		return QuestionarioConhecimentoAlimentarBo.getInstance();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=QuestionarioConhecimentoAlimentarValidator.class)
	@Override
	public Response save(QuestionarioConhecimentoAlimentar questionario) {
		try {
			QuestionarioConhecimentoAlimentarBo.getInstance().save(questionario);
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
	public Response getList(QuestionarioConhecimentoAlimentarFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getListGeneric(filter);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	@Override
	public Response getSelectList(QuestionarioConhecimentoAlimentarFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getSelectListGeneric(filter);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response get(@QueryParam("id") String id) throws Exception {
		return super.getGeneric(new Integer(id));
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/construct-questionario")
	public Response constructQuestionario(@QueryParam("id") String id) {
		try {
			return Response.ok(QuestionarioConhecimentoAlimentarBo.getInstance().constructQuestionario(
					Integer.parseInt(id))).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete")
	@Override
	public Response delete(Object id) {
		return super.deleteGeneric(new Integer(id.toString()));
	}

}
