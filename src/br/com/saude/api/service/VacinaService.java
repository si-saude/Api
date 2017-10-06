package br.com.saude.api.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.api.generic.CustomValidator;
import br.com.saude.api.generic.GenericService;
import br.com.saude.api.model.business.VacinaBo;
import br.com.saude.api.model.business.validate.VacinaValidator;
import br.com.saude.api.model.entity.filter.VacinaFilter;
import br.com.saude.api.model.entity.po.Vacina;
import br.com.saude.api.util.RequestInterceptor;

@Path("vacina")
@RequestInterceptor
public class VacinaService extends GenericService<Vacina,VacinaFilter,VacinaBo> {

	@Override
	protected VacinaBo getBo() {
		return VacinaBo.getInstance();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=VacinaValidator.class, entityClass=Vacina.class)
	@Override
	public Response save(Vacina vacina) {
		try {
			VacinaBo.getInstance().save(vacina);
			return Response.ok("Salvo com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}	
}
