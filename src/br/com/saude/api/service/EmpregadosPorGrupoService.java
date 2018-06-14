package br.com.saude.api.service;

import java.io.IOException;
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

import br.com.saude.api.generic.GenericReportService;
import br.com.saude.api.model.business.EmpregadosPorGrupoBo;
import br.com.saude.api.model.entity.dto.EmpregadosPorGrupoDto;

@Path("empregados-por-grupo")
public class EmpregadosPorGrupoService implements GenericReportService<EmpregadosPorGrupoDto, EmpregadosPorGrupoBo> {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get-empregados-por-grupo")
	public Response getEmpregadosPorGrupo(@QueryParam("id") String id) throws IOException {
		return Response.ok(EmpregadosPorGrupoBo.getInstance().getEmpregadosPorGrupo(new Integer(id)) ).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/get-file")
	public Response getFile(List<EmpregadosPorGrupoDto> entities) throws IOException {
		try {
			return Response.ok( this.exportXLSXFile(entities) ).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
}
