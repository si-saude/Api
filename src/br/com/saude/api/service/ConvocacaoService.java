package br.com.saude.api.service;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;

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
import br.com.saude.api.model.business.ConvocacaoBo;
import br.com.saude.api.model.business.validate.ConvocacaoValidator;
import br.com.saude.api.model.entity.filter.ConvocacaoFilter;
import br.com.saude.api.model.entity.po.Convocacao;
import br.com.saude.api.util.RequestInterceptor;

@Path("convocacao")
@RequestInterceptor
public class ConvocacaoService 
		extends GenericServiceImpl<Convocacao, ConvocacaoFilter, ConvocacaoBo>
		implements GenericService<Convocacao, ConvocacaoFilter>{

	@Override
	protected ConvocacaoBo getBo() {
		return ConvocacaoBo.getInstance();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=ConvocacaoValidator.class)
	@Override
	public Response save(Convocacao convocacao) {
		try {
			getBo().save(convocacao);
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
	public Response getList(ConvocacaoFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getListGeneric(filter);
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	public Response getSelectList(ConvocacaoFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getSelectListGeneric(filter);
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("id") String id) throws Exception {
		return super.getGeneric(new Integer(id));
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/get-empregados-by-gerencia")
	public Response getEmpregadoConvocacoesByGerencia(Convocacao convocacao) throws Exception {
		return Response.ok(getBo().getEmpregadoConvocacoesByGerencia(convocacao)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/get-empregado")
	public Response getEmpregadoConvocacao(Convocacao convocacao) throws Exception {
		return Response.ok(getBo().getEmpregadoConvocacao(convocacao)).build();
	}
	
	@POST
	@Produces("application/octet-stream")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/processar-convocacao")
	public Response processarConvocacao(Convocacao convocacao) throws Exception {
		File zip = getBo().processarConvocacao(convocacao);
		return Response.ok(Files.readAllBytes(zip.toPath()))
				.header("Content-Disposition", "attachment; filename=\""+convocacao.getTitulo()+".zip\"")
				.build();
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete")
	public Response delete(Object id) {
		return super.deleteGeneric(new Integer(id.toString()));
	}
}
