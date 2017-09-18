package br.com.saude.api.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.api.util.RequestInterceptor;
import br.com.saude.api.util.constant.Funcao;

@Path("generic")
@RequestInterceptor
public class GenericService {
	@GET
	@Path("/funcao")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(Funcao.getInstance().getList().stream()
							.filter(f-> filter!=null?f.toLowerCase().contains(filter.toLowerCase()):true)
							.toArray()).build();
	}
}
