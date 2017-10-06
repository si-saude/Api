package br.com.saude.api.service;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.api.generic.CustomValidator;
import br.com.saude.api.generic.GenericService;
import br.com.saude.api.model.business.ExameBo;
import br.com.saude.api.model.business.validate.ExameValidator;
import br.com.saude.api.model.entity.filter.ExameFilter;
import br.com.saude.api.model.entity.po.Exame;
import br.com.saude.api.util.RequestInterceptor;

@Path("exame")
@RequestInterceptor
public class ExameService extends GenericService<Exame,ExameFilter,ExameBo> {
	
	@Override
	protected ExameBo getBo() {
		return ExameBo.getInstance();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=ExameValidator.class, entityClass=Exame.class)
	@Override
	public Response save(@Valid Exame exame) {
		try {
			ExameBo.getInstance().save(exame);
			return Response.ok("Salvo com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
