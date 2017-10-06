package br.com.saude.api.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.api.generic.CustomValidator;
import br.com.saude.api.generic.GenericService;
import br.com.saude.api.model.business.CursoBo;
import br.com.saude.api.model.business.validate.CursoValidator;
import br.com.saude.api.model.entity.filter.CursoFilter;
import br.com.saude.api.model.entity.po.Curso;
import br.com.saude.api.util.RequestInterceptor;

@Path("curso")
@RequestInterceptor
public class CursoService extends GenericService<Curso,CursoFilter,CursoBo> {
	
	@Override
	protected CursoBo getBo() {
		return CursoBo.getInstance();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=CursoValidator.class, entityClass=Curso.class)
	@Override
	public Response save(Curso curso) {
		try {
			CursoBo.getInstance().save(curso);
			return Response.ok("Salvo com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
