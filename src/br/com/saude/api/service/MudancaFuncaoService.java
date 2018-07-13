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
import br.com.saude.api.model.business.MudancaFuncaoBo;
import br.com.saude.api.model.business.validate.MudancaFuncaoValidator;
import br.com.saude.api.model.entity.filter.MudancaFuncaoFilter;
import br.com.saude.api.model.entity.po.MudancaFuncao;
import br.com.saude.api.util.RequestInterceptor;

@Path("mudanca-funcao")
public class MudancaFuncaoService extends GenericServiceImpl<MudancaFuncao, MudancaFuncaoFilter, MudancaFuncaoBo>
		implements GenericService<MudancaFuncao, MudancaFuncaoFilter> 
{

	@Override
	protected MudancaFuncaoBo getBo() {
		return MudancaFuncaoBo.getInstance();
	}

	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass = MudancaFuncaoValidator.class)
	@Override
	public Response save(MudancaFuncao solicitacao) {
		try {
			MudancaFuncao sCI = MudancaFuncaoBo.getInstance().save(solicitacao);
			return Response.ok(sCI).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("registrar-mudanca-funcao")
	public Response registrarMudancaFuncao(MudancaFuncao solicitacao) {
		try {
			MudancaFuncaoBo.getInstance().registrarMudancaFuncao(solicitacao);
			return Response.ok("Salvo com sucesso.").build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}

	@RequestInterceptor
	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list")
	public Response getList(MudancaFuncaoFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getListGeneric(filter);
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	public Response getSelectList(MudancaFuncaoFilter filter) throws InstantiationException, IllegalAccessException,
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
