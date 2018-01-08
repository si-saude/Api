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
import br.com.saude.api.model.business.ResultadoExameBo;
import br.com.saude.api.model.business.validate.ResultadoExameValidator;
import br.com.saude.api.model.entity.filter.ResultadoExameFilter;
import br.com.saude.api.model.entity.imports.ResultadoExameImport;
import br.com.saude.api.model.entity.po.ResultadoExame;
import br.com.saude.api.util.RequestInterceptor;

@Path("resultado-exame")
@RequestInterceptor
public class ResultadoExameService extends GenericServiceImpl<ResultadoExame, ResultadoExameFilter, ResultadoExameBo>
implements GenericService<ResultadoExame, ResultadoExameFilter>{
	
	@Override
	protected ResultadoExameBo getBo() {
		return ResultadoExameBo.getInstance();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=ResultadoExameValidator.class)
	@Override
	public Response save(ResultadoExame relatorioMedico) {
		try {
			ResultadoExameBo.getInstance().save(relatorioMedico);
			return Response.ok("Salvo com sucesso.").build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list")
	public Response getList(ResultadoExameFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getListGeneric(filter);
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	public Response getSelectList(ResultadoExameFilter filter) throws InstantiationException, IllegalAccessException,
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response importFile(ResultadoExameImport arquivo) {
		try {
			String matriculasErro = ResultadoExameBo.getInstance().importFile(arquivo);
			if ( matriculasErro.length() > 0 )
				return Response.ok("Salvo com erro nas seguintes matriculas: " + matriculasErro).build();
			else
				return Response.ok("Salvo com sucesso.").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Path("/import-txt")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response importFileFromTxt(File arquivo) {
		try {
			String matriculasErro = ResultadoExameBo.getInstance().importFileFromTxt(arquivo);
			if ( matriculasErro.length() > 0 )
				return Response.ok("Salvo com erro nas seguintes matriculas: " + matriculasErro).build();
			else
				return Response.ok("Salvo com sucesso.").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
