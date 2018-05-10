package br.com.saude.api.service;

import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.api.generic.GenericConstant;
import br.com.saude.api.util.RequestInterceptor;
import br.com.saude.api.util.constant.Abrangencia;
import br.com.saude.api.util.constant.AcaoResultadoExame;
import br.com.saude.api.util.constant.AptidaoCardiorrespiratoria;
import br.com.saude.api.util.constant.AptidaoFisicaBrigadista;
import br.com.saude.api.util.constant.AutoavaliacaoHabitosAlimentares;
import br.com.saude.api.util.constant.Conformidade;
import br.com.saude.api.util.constant.DoresCorporaisIntensidade;
import br.com.saude.api.util.constant.Escolaridade;
import br.com.saude.api.util.constant.EstadoCivil;
import br.com.saude.api.util.constant.EstagioContemplacao;
import br.com.saude.api.util.constant.ExposicaoRiscosAmbientaisCategoria;
import br.com.saude.api.util.constant.Flexibilidade;
import br.com.saude.api.util.constant.ForcaAbdominal;
import br.com.saude.api.util.constant.ForcaPreensaoManual;
import br.com.saude.api.util.constant.FumaClassificacao;
import br.com.saude.api.util.constant.Funcionalidade;
import br.com.saude.api.util.constant.GrupoPerguntaFichaColeta;
import br.com.saude.api.util.constant.GrupoServico;
import br.com.saude.api.util.constant.NivelAtividadeFisica;
import br.com.saude.api.util.constant.Operador;
import br.com.saude.api.util.constant.PrazoEmMeses;
import br.com.saude.api.util.constant.RefereQualidadeAguaQualidade;
import br.com.saude.api.util.constant.Requisito;
import br.com.saude.api.util.constant.SensacaoDor;
import br.com.saude.api.util.constant.Sexo;
import br.com.saude.api.util.constant.StatusAcao;
import br.com.saude.api.util.constant.StatusEmpregado;
import br.com.saude.api.util.constant.StatusFilaEsperaOcupacional;
import br.com.saude.api.util.constant.StatusRiscoEmpregado;
import br.com.saude.api.util.constant.StatusRiscoPotencial;
import br.com.saude.api.util.constant.StatusSimNao;
import br.com.saude.api.util.constant.StatusTarefa;
import br.com.saude.api.util.constant.TempoAnos;
import br.com.saude.api.util.constant.TempoMeses;
import br.com.saude.api.util.constant.TipoAcao;
import br.com.saude.api.util.constant.TipoContato;
import br.com.saude.api.util.constant.TipoConvocacao;
import br.com.saude.api.util.constant.TipoCriterio;
import br.com.saude.api.util.constant.TipoPerguntaFichaColeta;
import br.com.saude.api.util.constant.TipoPessoa;
import br.com.saude.api.util.constant.TipoResultadoExame;
import br.com.saude.api.util.constant.UF;
import br.com.saude.api.util.constant.VinculoEmpregado;

@Path("generic")
@RequestInterceptor
public class UtilService {
	
	private Map<String,String> getMap(GenericConstant constant, String filter) throws IllegalArgumentException, IllegalAccessException{
		return constant.getList().entrySet().stream() 
		.filter(f-> filter!=null?f.getValue().toLowerCase().contains(filter.toLowerCase()):true)
		.collect(Collectors.toMap(e->e.getKey(),e->e.getValue()));
	}
	
	@GET
	@Path("/funcionalidade")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFuncionalidade(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(Funcionalidade.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/nivel-atividade-fisica")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNivelAtividadeFisica(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(NivelAtividadeFisica.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/estagio-contemplacao")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEstagioContemplacao(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(EstagioContemplacao.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/sensacao-dor")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSensacaoDor(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(SensacaoDor.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/aptidao-cardiorrespiratoria")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAptidaoCardiorrespiratoria(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(AptidaoCardiorrespiratoria.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/forca-abdominal")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getForcaAbdominal(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(ForcaAbdominal.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/flexibilidade")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFlexibilidade(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(Flexibilidade.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/exposicao-riscos-ambientais")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getExposicaoRiscosAmbientaisCategoria(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(ExposicaoRiscosAmbientaisCategoria.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/refere-qualidade-agua")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRefereQualidadeAguaQualidade(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(RefereQualidadeAguaQualidade.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/tempo-meses")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTempoMeses(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(TempoMeses.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/dores-corporais")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDoresCorporaisIntensidade(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(DoresCorporaisIntensidade.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/fuma-classificacao")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFumaClassificacao(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(FumaClassificacao.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/tempo-anos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTempoAnos(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(TempoAnos.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/aptidao-fisica-brigadista")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAptidaoFisicaBrigadista(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(AptidaoFisicaBrigadista.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/autoavaliacao-habitos-alimentares")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAutoavaliacaoHabitosAlimentares(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(AutoavaliacaoHabitosAlimentares.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/forca-prenssao-manual")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getForcaPrenssaoManual(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(ForcaPreensaoManual.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/abrangencia")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAbrangencia(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(Abrangencia.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/tipo-pessoa")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTipoPessoa(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(TipoPessoa.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/conformidade")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getConformidade(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(Conformidade.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/tipo-criterio")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTipoCriterio(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(TipoCriterio.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/tipo-convocacao")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTipoConvocacao(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(TipoConvocacao.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/sexo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSexo(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(Sexo.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/status-empregado")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatusEmpregado(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(StatusEmpregado.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/status-tarefa")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatusTarefa(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(StatusTarefa.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/status-fila-espera-ocupacional")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatusFilaEsperaOcupacional(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(StatusFilaEsperaOcupacional.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/requisito")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRequisito(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(Requisito.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/operador")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOperador(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(Operador.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/estado-civil")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEstadoCivil(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(EstadoCivil.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/escolaridade")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEscolaridade(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(Escolaridade.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/vinculo-empregado")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVinculoEmpregado(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(VinculoEmpregado.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/acao-resultado-exame")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAcaoResultadoExame(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(AcaoResultadoExame.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/tipo-resultado-exame")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTipoResultadoExame(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(TipoResultadoExame.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/grupo-servico")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGrupoServico(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(GrupoServico.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/grupo-pergunta-ficha-coleta")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGrupoPerguntaFichaColeta(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(GrupoPerguntaFichaColeta.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/tipo-pergunta-ficha-coleta")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTipoPerguntaFichaColeta(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(TipoPerguntaFichaColeta.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/tipo-acao")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTipoAcao(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(TipoAcao.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/status-acao")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatusAcao(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(StatusAcao.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/status-risco-potencial")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatusRiscoPotencial(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(StatusRiscoPotencial.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/status-risco-empregado")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatusRiscoEmpregado(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(StatusRiscoEmpregado.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/tipo-contato")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTipoContato(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(TipoContato.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/status-sim-nao")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatusSimNao(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(StatusSimNao.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/prazo-em-meses")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPrazoEmMeses(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(PrazoEmMeses.getInstance(),filter)).build();
	}
	
	@GET
	@Path("/uf")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUf(@QueryParam("filter") String filter) throws IllegalArgumentException, IllegalAccessException {
		return Response.ok(getMap(UF.getInstance(),filter)).build();
	}
}
