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
import br.com.saude.api.model.business.PanoramaBo;
import br.com.saude.api.model.entity.dto.PanoramaDto;
import br.com.saude.api.util.RequestInterceptor;

@Path("panorama")
@RequestInterceptor
public class PanoramaService implements GenericReportService<PanoramaDto, PanoramaBo>
{

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get-panoramas")
	public Response getPanoramas() throws IOException {
		return Response.ok( PanoramaBo.getInstance().getPanoramas() ).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/get-file")
	public Response getFile(List<PanoramaDto> entities) throws IOException {
		try {
			return Response.ok( this.exportXLSXFile(entities) ).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
}
