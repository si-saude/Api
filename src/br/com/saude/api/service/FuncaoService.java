package br.com.saude.api.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.api.generic.CustomValidator;
import br.com.saude.api.generic.GenericService;
import br.com.saude.api.model.business.FuncaoBo;
import br.com.saude.api.model.business.validate.FuncaoValidator;
import br.com.saude.api.model.entity.filter.FuncaoFilter;
import br.com.saude.api.model.entity.po.Funcao;
import br.com.saude.api.util.RequestInterceptor;

@Path("funcao")
@RequestInterceptor
public class FuncaoService extends GenericService<Funcao,FuncaoFilter,FuncaoBo> {

	@Override
	protected FuncaoBo getBo() {
		return FuncaoBo.getInstance();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=FuncaoValidator.class, entityClass=Funcao.class)
	@Override
	public Response save(Funcao funcao) {
		try {
			FuncaoBo.getInstance().save(funcao);
			return Response.ok("Salvo com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
