package br.com.saude.api.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.api.generic.CustomValidator;
import br.com.saude.api.generic.GenericService;
import br.com.saude.api.model.business.EmpregadoBo;
import br.com.saude.api.model.business.validate.EmpregadoValidator;
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.util.RequestInterceptor;

@Path("empregado")
@RequestInterceptor
public class EmpregadoService extends GenericService<Empregado,EmpregadoFilter,EmpregadoBo> {
	
	@Override
	protected EmpregadoBo getBo() {
		return EmpregadoBo.getInstance();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=EmpregadoValidator.class, entityClass=Empregado.class)
	public Response save(Empregado empregado) {
		try {
			EmpregadoBo.getInstance().save(empregado);
			return Response.ok("Salvo com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
