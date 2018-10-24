package br.com.saude.api.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.saude.api.generic.CustomValidator;
import br.com.saude.api.generic.GenericReportService;
import br.com.saude.api.generic.GenericService;
import br.com.saude.api.generic.GenericServiceImpl;
import br.com.saude.api.model.business.MudancaFuncaoBo;
import br.com.saude.api.model.business.validate.MudancaFuncaoValidator;
import br.com.saude.api.model.entity.dto.MudancaFuncaoDto;
import br.com.saude.api.model.entity.filter.MudancaFuncaoFilter;
import br.com.saude.api.model.entity.po.MudancaFuncao;
import br.com.saude.api.util.RequestInterceptor;

@Path("mudanca-funcao")
public class MudancaFuncaoService extends GenericServiceImpl<MudancaFuncao, MudancaFuncaoFilter, MudancaFuncaoBo>
		implements GenericReportService<MudancaFuncaoDto, MudancaFuncaoBo>, GenericService<MudancaFuncao, MudancaFuncaoFilter> 
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
	public Response save(MudancaFuncao mudancaFuncao) {
		try {
			MudancaFuncaoBo.getInstance().save(mudancaFuncao);
			return Response.ok("Salvo com sucesso.").build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("registrar-mudanca-funcao")
	public Response registrarMudancaFuncao(MudancaFuncao mudancaFuncao) {
		try {
			MudancaFuncaoBo.getInstance().registrarMudancaFuncao(mudancaFuncao);
			return Response.ok("Salvo com sucesso.").build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("aplicar-alteracoes")
	public Response aplicarAlteracoes(MudancaFuncao mudancaFuncao) {
		try {
			MudancaFuncaoBo.getInstance().aplicarAlteracoes(mudancaFuncao);
			return Response.ok("Mudanças aplicadas com sucesso.").build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("solicitar-convocacao")
	public Response solicitarConvocacao(MudancaFuncao mudancaFuncao) {
		try {
			return Response.ok(MudancaFuncaoBo.getInstance().solicitarConvocacao(mudancaFuncao)).build();
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

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get-mudanca-funcoes")
	public Response getMudancaFuncoes() throws IOException {
		try {
			return Response.ok( MudancaFuncaoBo.getInstance().getMudancaFuncoes() ).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/get-file")
	public Response getFile(List<MudancaFuncaoDto> entities) throws IOException {
		try {
			return Response.ok( this.exportXLSXFile(entities) ).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
}
