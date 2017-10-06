package br.com.saude.api.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.api.generic.CustomValidator;
import br.com.saude.api.generic.GenericService;
import br.com.saude.api.model.business.ProfissionalBo;
import br.com.saude.api.model.business.validate.ProfissionalValidator;
import br.com.saude.api.model.entity.filter.ProfissionalFilter;
import br.com.saude.api.model.entity.po.Profissional;
import br.com.saude.api.util.RequestInterceptor;

@Path("profissional")
@RequestInterceptor
public class ProfissionalService 
				extends GenericService<Profissional,ProfissionalFilter,ProfissionalBo> {
	
	@Override
	protected ProfissionalBo getBo() {
		return ProfissionalBo.getInstance();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=ProfissionalValidator.class, entityClass=Profissional.class)
	@Override
	public Response save(Profissional profissional) {
		try {
			ProfissionalBo.getInstance().save(profissional);
			return Response.ok("Salvo com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
