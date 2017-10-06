package br.com.saude.api.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.api.generic.CustomValidator;
import br.com.saude.api.generic.GenericService;
import br.com.saude.api.model.business.IndicadorRiscoAcidenteBo;
import br.com.saude.api.model.business.validate.IndicadorRiscoValidator;
import br.com.saude.api.model.entity.filter.IndicadorRiscoFilter;
import br.com.saude.api.model.entity.po.IndicadorRiscoAcidente;
import br.com.saude.api.util.RequestInterceptor;

@Path("indicador-risco-acidente")
@RequestInterceptor
public class IndicadorRiscoAcidenteService 
		extends GenericService<IndicadorRiscoAcidente,IndicadorRiscoFilter,IndicadorRiscoAcidenteBo> {

	@Override
	protected IndicadorRiscoAcidenteBo getBo() {
		return IndicadorRiscoAcidenteBo.getInstance();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=IndicadorRiscoValidator.class, entityClass=IndicadorRiscoAcidente.class)
	@Override
	public Response save(IndicadorRiscoAcidente indicadorRiscoAcidente) {
		try {
			IndicadorRiscoAcidenteBo.getInstance().save(indicadorRiscoAcidente);
			return Response.ok("Salvo com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
