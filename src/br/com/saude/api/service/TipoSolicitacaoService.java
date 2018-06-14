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
import br.com.saude.api.model.business.TipoSolicitacaoBo;
import br.com.saude.api.model.business.validate.TipoSolicitacaoValidator;
import br.com.saude.api.model.entity.filter.TipoSolicitacaoFilter;
import br.com.saude.api.model.entity.po.TipoSolicitacao;
import br.com.saude.api.util.RequestInterceptor;

@Path("tipo-solicitacao")
@RequestInterceptor
public class TipoSolicitacaoService
	extends GenericServiceImpl<TipoSolicitacao, TipoSolicitacaoFilter, TipoSolicitacaoBo> 
implements GenericService<TipoSolicitacao, TipoSolicitacaoFilter>{

	@Override
	protected TipoSolicitacaoBo getBo() {
		return TipoSolicitacaoBo.getInstance();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=TipoSolicitacaoValidator.class)
	@Override
	public Response save(TipoSolicitacao tipoSolicitacao) {
		try {
			TipoSolicitacaoBo.getInstance().save(tipoSolicitacao);
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
	public Response getList(TipoSolicitacaoFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getListGeneric(filter);
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	public Response getSelectList(TipoSolicitacaoFilter filter) throws InstantiationException, IllegalAccessException,
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
