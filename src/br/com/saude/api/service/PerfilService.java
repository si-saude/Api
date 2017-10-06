package br.com.saude.api.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.api.generic.CustomValidator;
import br.com.saude.api.generic.GenericService;
import br.com.saude.api.model.business.PerfilBo;
import br.com.saude.api.model.business.validate.PerfilValidator;
import br.com.saude.api.model.entity.filter.PerfilFilter;
import br.com.saude.api.model.entity.po.Perfil;
import br.com.saude.api.util.RequestInterceptor;

@Path("perfil")
@RequestInterceptor
public class PerfilService extends GenericService<Perfil,PerfilFilter,PerfilBo> {


	@Override
	protected PerfilBo getBo() {
		return PerfilBo.getInstance();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=PerfilValidator.class, entityClass=Perfil.class)
	@Override
	public Response save(Perfil perfil) {		
		try {
			PerfilBo.getInstance().save(perfil);
			return Response.ok("Salvo com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
