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
import br.com.saude.api.model.business.AgendaPeriodicoBo;
import br.com.saude.api.model.entity.dto.AgendaPeriodicoDto;

@Path("agenda-periodico")
public class AgendaPeriodicoService implements GenericReportService<AgendaPeriodicoDto, AgendaPeriodicoBo> {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get-agenda-periodicos")
	public Response getAgendaPeriodicos(@QueryParam("dataInicioInicio") String dataInicioInicio, 
			@QueryParam("dataInicioFim") String dataInicioFim,
			@QueryParam("servicoId") String servicoId) throws IOException {
		try {
			return Response.ok(AgendaPeriodicoBo.getInstance().getAgendaPeriodicos(dataInicioInicio, dataInicioFim, servicoId)).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/get-file")
	public Response getFile(List<AgendaPeriodicoDto> entities) throws IOException {
		try {
			return Response.ok( this.exportXLSXFile(entities) ).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

}