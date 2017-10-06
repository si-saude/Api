package br.com.saude.api.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.api.generic.CustomValidator;
import br.com.saude.api.generic.GenericService;
import br.com.saude.api.model.business.CidadeBo;
import br.com.saude.api.model.business.validate.CidadeValidator;
import br.com.saude.api.model.entity.filter.CidadeFilter;
import br.com.saude.api.model.entity.po.Cidade;
import br.com.saude.api.util.RequestInterceptor;

@Path("cidade")
@RequestInterceptor
public class CidadeService extends GenericService<Cidade, CidadeFilter, CidadeBo> {
	
	@Override
	protected CidadeBo getBo() {
		return CidadeBo.getInstance();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=CidadeValidator.class, entityClass=Cidade.class)
	@Override
	public Response save(Cidade cidade) {
		try {
			CidadeBo.getInstance().save(cidade);
			return Response.ok("Salvo com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
