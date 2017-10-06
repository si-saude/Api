package br.com.saude.api.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.api.generic.CustomValidator;
import br.com.saude.api.generic.GenericService;
import br.com.saude.api.model.business.EquipeBo;
import br.com.saude.api.model.business.validate.EquipeValidator;
import br.com.saude.api.model.entity.filter.EquipeFilter;
import br.com.saude.api.model.entity.po.Equipe;
import br.com.saude.api.util.RequestInterceptor;

@Path("equipe")
@RequestInterceptor
public class EquipeService extends GenericService<Equipe,EquipeFilter,EquipeBo> {

	@Override
	protected EquipeBo getBo() {
		return EquipeBo.getInstance();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=EquipeValidator.class, entityClass=Equipe.class)
	@Override
	public Response save(Equipe equipe) {
		try {
			EquipeBo.getInstance().save(equipe);
			return Response.ok("Salvo com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
