package br.com.saude.api.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.api.generic.GenericService;
import br.com.saude.api.model.business.UsuarioBo;
import br.com.saude.api.model.creation.builder.entity.UsuarioBuilder;
import br.com.saude.api.model.entity.filter.UsuarioFilter;
import br.com.saude.api.model.entity.po.Usuario;
import br.com.saude.api.util.RequestInterceptor;
import br.com.saude.api.util.UserManager;

@Path("usuario")
public class UsuarioService extends GenericService<Usuario,UsuarioFilter,UsuarioBo> {
	
	@Override
	protected UsuarioBo getBo() {
		return UsuarioBo.getInstance();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response login(UsuarioFilter filter) throws Exception  {
		Usuario usuario = UsuarioBo.getInstance().getFirstToAutenticacao(filter);
		if(usuario != null) {
			usuario = UsuarioBuilder.newInstance(usuario).getEntity();
			usuario = UserManager.getInstance().authenticate(usuario);
			return Response.ok(usuario).build();
		}else {
			return Response.status(Response.Status.NOT_FOUND).entity("Usuário ou senha inválidos.").build();
		}
	}
	
	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response save(Usuario usuario) {
		try {
			UsuarioBo.getInstance().save(usuario);
			return Response.ok("Salvo com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
