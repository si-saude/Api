package br.com.saude.api.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.api.generic.CustomValidator;
import br.com.saude.api.generic.GenericService;
import br.com.saude.api.model.business.PeriodicidadeBo;
import br.com.saude.api.model.business.validate.PeriodicidadeValidator;
import br.com.saude.api.model.entity.filter.PeriodicidadeFilter;
import br.com.saude.api.model.entity.po.Periodicidade;
import br.com.saude.api.util.RequestInterceptor;

@Path("periodicidade")
@RequestInterceptor
public class PeriodicidadeService 
			extends GenericService<Periodicidade,PeriodicidadeFilter,PeriodicidadeBo> {

	@Override
	protected PeriodicidadeBo getBo() {
		return PeriodicidadeBo.getInstance();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=PeriodicidadeValidator.class, entityClass=Periodicidade.class)
	@Override
	public Response save(Periodicidade periodicidade) {
		try {
			PeriodicidadeBo.getInstance().save(periodicidade);
			return Response.ok("Salvo com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
