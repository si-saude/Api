package br.com.saude.api.service;

import java.lang.reflect.InvocationTargetException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.api.generic.CustomValidator;
import br.com.saude.api.generic.GenericService;
import br.com.saude.api.generic.GenericServiceImpl;
import br.com.saude.api.model.business.AtendimentoBo;
import br.com.saude.api.model.business.FilaAtendimentoOcupacionalBo;
import br.com.saude.api.model.business.validate.AtendimentoValidator;
import br.com.saude.api.model.entity.filter.AtendimentoFilter;
import br.com.saude.api.model.entity.po.Atendimento;
import br.com.saude.api.model.entity.po.FilaAtendimentoOcupacional;
import br.com.saude.api.util.RequestInterceptor;

@Path("atendimento")
public class AtendimentoService extends GenericServiceImpl<Atendimento, AtendimentoFilter, AtendimentoBo>
							implements GenericService<Atendimento, AtendimentoFilter>{

	@Override
	protected AtendimentoBo getBo() {
		return AtendimentoBo.getInstance();
	}

	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=AtendimentoValidator.class)
	@Override
	public Response save(Atendimento atendimento) {
		try {
			getBo().save(atendimento);
			return Response.ok("Salvo com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/registrar-solicitacao-atendimento")
	public Response registrarSolicitacaoAtendimento(Atendimento atendimento) {
		try {
			return Response.ok(getBo().registrarSolicitacaoAtendimento(atendimento)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/registrar-solicitacao-exame-pericial")
	public Response registrarSolicitacaoExamePericial(Atendimento atendimento) {
		try {
			return Response.ok(getBo().registrarSolicitacaoExamePericial(atendimento)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/get-relatorio-proaf")
	public Response getRelatorioProaf(Atendimento atendimento) {
		try {
			return Response.ok(getBo().getRelatorioProaf(atendimento)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/get-relatorio-avaliacao-ho")
	public Response getRelatorioAvaliacaoHo(Atendimento atendimento) {
		try {
			return Response.ok(getBo().getRelatorioAvaliacaoHo(atendimento)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/get-relatorio-questionario-ensaio-vedacao")
	public Response getRelatorioQuestionarioVedacao(Atendimento atendimento) {
		try {
			return Response.ok(getBo().getRelatorioQuestionarioVedacao(atendimento)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	
	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=AtendimentoValidator.class)
	@Path("/iniciar")
	public Response iniciar(Atendimento atendimento) {
		try {
			return Response.ok(getBo().iniciar(atendimento)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=AtendimentoValidator.class)
	@Path("/registrar-ausencia")
	public Response registrarAusencia(Atendimento atendimento) {
		try {
			return Response.ok(getBo().registrarAusencia(atendimento)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=AtendimentoValidator.class)
	@Path("/devolver-pra-fila")
	public Response devolverPraFila(Atendimento atendimento) {
		try {
			return Response.ok(getBo().devolverPraFila(atendimento)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=AtendimentoValidator.class)
	@Path("/liberar")
	public Response liberar(Atendimento atendimento) {
		try {
			return Response.ok(getBo().liberar(atendimento)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=AtendimentoValidator.class)
	@Path("/finalizar")
	public Response finalizar(Atendimento atendimento) {
		try {
			return Response.ok(getBo().finalizar(atendimento)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=AtendimentoValidator.class)
	@Path("/finalizar-retroativo")
	public Response finalizarRetroativo(Atendimento atendimento) {
		try {
			return Response.ok(getBo().finalizarRetroativo(atendimento)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CustomValidator(validatorClass=AtendimentoValidator.class)
	@Path("/finalizar-pausar")
	public Response finalizarPausar(Atendimento atendimento) {
		try {
			return Response.ok(getBo().finalizarPausar(atendimento)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/voltar")
	public Response voltar(FilaAtendimentoOcupacional fila) {
		try {
			return Response.ok(FilaAtendimentoOcupacionalBo.getInstance().voltar(fila)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/atualizar-lista")
	public Response atualizarLista(FilaAtendimentoOcupacional fila) {
		try {
			return Response.ok(FilaAtendimentoOcupacionalBo.getInstance().atualizarLista(fila)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/entrar")
	public Response entrar(FilaAtendimentoOcupacional fila) {
		try {
			return Response.ok(FilaAtendimentoOcupacionalBo.getInstance().entrar(fila)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/atualizar")
	public Response atualizar(Atendimento atendimento) {
		try {
			Atendimento a = FilaAtendimentoOcupacionalBo.getInstance().atualizar(atendimento);
			return Response.ok(a).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/complemento-atendimento-avulso")
	public Response getComplementoAtendimentoAvulso(Atendimento atendimento) {
		try {
			return Response.ok(getBo().getComplementoAtendimentoAvulso(atendimento)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/pausar")
	public Response pausar(FilaAtendimentoOcupacional fila) {
		try {
			return Response.ok(FilaAtendimentoOcupacionalBo.getInstance().pausar(fila)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/registrar-almoco")
	public Response registrarAlmoco(FilaAtendimentoOcupacional fila) {
		try {
			return Response.ok(FilaAtendimentoOcupacionalBo.getInstance().registrarAlmoco(fila)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/encerrar")
	public Response encerrar(FilaAtendimentoOcupacional fila) {
		try {
			return Response.ok(FilaAtendimentoOcupacionalBo.getInstance().encerrar(fila)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
	@RequestInterceptor
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/calcular-composicao-corporal")
	public Response calcularComposicaoCorporal(Atendimento atendimento) {
		try {
			return Response.ok(AtendimentoBo.getInstance().calcularComposicaoCorporal(atendimento)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}

	@RequestInterceptor
	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/list")
	public Response getList(AtendimentoFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return Response.ok(getBo().getListLoadAll(filter).getGenericPagedList()).build();
	}

	@RequestInterceptor
	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/selectList")
	public Response getSelectList(AtendimentoFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getSelectListGeneric(filter);
	}

	@RequestInterceptor
	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("id") String id) throws Exception {
		return super.getGeneric(new Integer(id));
	}

	@RequestInterceptor
	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete")
	public Response delete(Object id) {
		return super.deleteGeneric(new Integer(id.toString()));
	}
}
