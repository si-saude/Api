package br.com.saude.api.service;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.api.model.business.EmpregadosPorGrupoBo;

@Path("empregados-por-grupo")
public class EmpregadosPorGrupoService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get-empregados-por-grupo")
	public Response getPanoramas(@QueryParam("id") String id) throws IOException {
		return Response.ok(EmpregadosPorGrupoBo.getInstance().getEmpregadosPorGrupo(new Integer(id)) ).build();
	}
	
}
