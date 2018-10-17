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
import br.com.saude.api.model.business.AcompanhamentoSastBo;
import br.com.saude.api.model.entity.dto.AcompanhamentoSastEmpregadoDto;
import br.com.saude.api.util.RequestInterceptor;

@Path("acompanhamento-sast")
@RequestInterceptor
public class AcompanhamentoSastService implements GenericReportService<AcompanhamentoSastEmpregadoDto, AcompanhamentoSastBo>
{

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get-acompanhamento-sasts")
	public Response getAcompanhamentoSasts(@QueryParam("abreviacaoEquipeAcolhimento") String abreviacaoEquipeAcolhimento, 
			@QueryParam("idProfissional") int idProfissional,
			@QueryParam("anoRisco") int anoRisco) throws IOException {
		try {
			return Response.ok( AcompanhamentoSastBo.getInstance().getAcompanhamentoSasts(
					abreviacaoEquipeAcolhimento, idProfissional, anoRisco) ).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/get-file")
	public Response getFile(List<AcompanhamentoSastEmpregadoDto> entities) throws IOException {
		try {
			return Response.ok( this.exportXLSXFile(entities) ).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
}