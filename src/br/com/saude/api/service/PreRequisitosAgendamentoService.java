package br.com.saude.api.service;

import java.io.IOException;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.saude.api.generic.GenericReportService;
import br.com.saude.api.model.business.PreRequisitosAgendamentoBo;
import br.com.saude.api.model.entity.dto.PreRequisitosAgendamentoDto;
import br.com.saude.api.util.RequestInterceptor;

@Path("pre-requisitos-agendamento")
@RequestInterceptor
public class PreRequisitosAgendamentoService implements GenericReportService<PreRequisitosAgendamentoDto, PreRequisitosAgendamentoBo> {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get-pre-requisitos-agendamento")
	public Response getPreRequisitosAgendamentos() throws IOException {
		return Response.ok( PreRequisitosAgendamentoBo.getInstance().getPanoramas() ).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/get-file")
	public Response getFile(List<PreRequisitosAgendamentoDto> entities) throws IOException {
		try {
			return Response.ok( this.exportXLSXFile(entities) ).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
}
