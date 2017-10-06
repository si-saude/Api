package br.com.saude.api.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.api.generic.CustomValidator;
import br.com.saude.api.generic.GenericService;
import br.com.saude.api.model.business.IndicadorRiscoSanitarioBo;
import br.com.saude.api.model.business.validate.IndicadorRiscoValidator;
import br.com.saude.api.model.entity.filter.IndicadorRiscoFilter;
import br.com.saude.api.model.entity.po.IndicadorRiscoSanitario;
import br.com.saude.api.util.RequestInterceptor;

@Path("indicador-risco-sanitario")
@RequestInterceptor
public class IndicadorRiscoSanitarioService 
		extends GenericService<IndicadorRiscoSanitario,IndicadorRiscoFilter,IndicadorRiscoSanitarioBo> {

	@Override
	protected IndicadorRiscoSanitarioBo getBo() {
		return IndicadorRiscoSanitarioBo.getInstance();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=IndicadorRiscoValidator.class, entityClass=IndicadorRiscoSanitario.class)
	@Override
	public Response save(IndicadorRiscoSanitario indicadorRiscoSanitario) {
		try {
			IndicadorRiscoSanitarioBo.getInstance().save(indicadorRiscoSanitario);
			return Response.ok("Salvo com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
