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
import br.com.saude.api.model.business.RiscoPotencialBo;
import br.com.saude.api.model.business.validate.RiscoPotencialValidator;
import br.com.saude.api.model.entity.dto.RiscoPotencialDto;
import br.com.saude.api.model.entity.filter.RiscoPotencialFilter;
import br.com.saude.api.model.entity.po.RiscoPotencial;
import br.com.saude.api.util.RequestInterceptor;

@Path("risco-potencial")
@RequestInterceptor
public class RiscoPotencialService extends GenericServiceImpl<RiscoPotencial,RiscoPotencialFilter,RiscoPotencialBo>
							implements GenericReportService<RiscoPotencialDto, RiscoPotencialBo>, GenericService<RiscoPotencial,RiscoPotencialFilter>{
	
	@Override
	protected RiscoPotencialBo getBo() {
		return RiscoPotencialBo.getInstance();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=RiscoPotencialValidator.class)
	@Override
	public Response save(RiscoPotencial riscoPotencial) {
		try {
			RiscoPotencialBo.getInstance().save(riscoPotencial);
			return Response.ok("Salvo com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Path("validar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=RiscoPotencialValidator.class)
	public Response validar(RiscoPotencial riscoPotencial) {
		try {
			RiscoPotencialBo.getInstance().validar(riscoPotencial);
			return Response.ok("Validado com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=RiscoPotencialValidator.class)
	@Path("/criar-plano")
	public Response criarPlano(RiscoPotencial riscoPotencial) {
		try {
			RiscoPotencialBo.getInstance().criarPlano(riscoPotencial);
			return Response.ok("Plano criado com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=RiscoPotencialValidator.class)
	@Path("/save-acoes")
	public Response saveAcoes(RiscoPotencial riscoPotencial) {
		try {
			RiscoPotencialBo.getInstance().saveAcoes(riscoPotencial);
			return Response.ok("Salvo com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=RiscoPotencialValidator.class)
	@Path("/save-acompanhamentos")
	public Response saveAcompanhamentos(RiscoPotencial riscoPotencial) {
		try {
			RiscoPotencialBo.getInstance().saveAcompanhamentos(riscoPotencial);
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
	public Response getList(RiscoPotencialFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getListGeneric(filter);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list-all")
	public Response getListAll(RiscoPotencialFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {		
		try {
			return Response.ok(getBo().getListLoadAll(filter).getGenericPagedList()).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	public Response getSelectList(RiscoPotencialFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getSelectListGeneric(filter);
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("id") String id) throws Exception {
		return super.getGeneric(new Integer(id));
	}
	
	@Path("/get-acoes")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAcoes(@QueryParam("id") String id) throws Exception {
		return Response.ok(getBo().getByIdLoadAcoes(new Integer(id))).build();
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete")
	public Response delete(Object id) {
		return super.deleteGeneric(new Integer(id.toString()));
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get-risco-potenciais")
	public Response getRiscoPotenciais(@QueryParam("uf") String uf) throws IOException {
		return Response.ok(RiscoPotencialBo.getInstance().getRiscoPotenciais(uf)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/get-file")
	public Response getFile(List<RiscoPotencialDto> entities) throws IOException {
		try {
			return Response.ok( this.exportXLSXFile(entities) ).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
}
