package br.com.saude.api.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.api.model.persistence.report.SugestaoAgendamentoReport;

@Path("sugestao-agendamento")
public class SugestaoAgendamentoService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("id") String id) throws Exception {
		try {
			return Response.ok(SugestaoAgendamentoReport.getInstance().getSugestaoAgendamentos(new Integer(id))).build();	
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
}
