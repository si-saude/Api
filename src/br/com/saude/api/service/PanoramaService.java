package br.com.saude.api.service;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.api.model.business.PanoramaBo;
import br.com.saude.api.util.RequestInterceptor;

@Path("panorama")
@RequestInterceptor
public class PanoramaService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get-panoramas")
	public Response getPanoramas() throws IOException {
		return Response.ok( PanoramaBo.getInstance().getPanoramas() ).build();
	}
}
