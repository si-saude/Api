package br.com.saude.api.service;

import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.api.util.RequestInterceptor;
import br.com.saude.api.util.constant.Conformidade;
import br.com.saude.api.util.constant.Funcionalidade;
import br.com.saude.api.util.constant.Sexo;
import br.com.saude.api.util.constant.StatusEmpregado;
import br.com.saude.api.util.constant.TipoCriterio;
import br.com.saude.api.util.constant.TipoPessoa;

@Path("generic")
@RequestInterceptor
public class UtilService {
	@GET
	@Path("/funcionalidade")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFuncionalidade(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(Funcionalidade.getInstance().getList().entrySet().stream() 
							.filter(f-> filter!=null?f.getValue().toLowerCase().contains(filter.toLowerCase()):true)
							.collect(Collectors.toMap(e->e.getKey(),e->e.getValue()))
							).build();
	}
	
	@GET
	@Path("/tipo-pessoa")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTipoPessoa(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(TipoPessoa.getInstance().getList().entrySet().stream() 
							.filter(f-> filter!=null?f.getValue().toLowerCase().contains(filter.toLowerCase()):true)
							.collect(Collectors.toMap(e->e.getKey(),e->e.getValue()))
							).build();
	}
	
	@GET
	@Path("/conformidade")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getConformidade(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(Conformidade.getInstance().getList().entrySet().stream() 
							.filter(f-> filter!=null?f.getValue().toLowerCase().contains(filter.toLowerCase()):true)
							.collect(Collectors.toMap(e->e.getKey(),e->e.getValue()))
							).build();
	}
	
	@GET
	@Path("/tipo-criterio")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTipoCriterio(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(TipoCriterio.getInstance().getList().entrySet().stream() 
							.filter(f-> filter!=null?f.getValue().toLowerCase().contains(filter.toLowerCase()):true)
							.collect(Collectors.toMap(e->e.getKey(),e->e.getValue()))
							).build();
	}
	
	@GET
	@Path("/sexo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSexo(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(Sexo.getInstance().getList().entrySet().stream() 
							.filter(f-> filter!=null?f.getValue().toLowerCase().contains(filter.toLowerCase()):true)
							.collect(Collectors.toMap(e->e.getKey(),e->e.getValue()))
							).build();
	}
	
	@GET
	@Path("/status-empregado")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatusEmpregado(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(StatusEmpregado.getInstance().getList().entrySet().stream() 
							.filter(f-> filter!=null?f.getValue().toLowerCase().contains(filter.toLowerCase()):true)
							.collect(Collectors.toMap(e->e.getKey(),e->e.getValue()))
							).build();
	}
}
