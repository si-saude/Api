package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.AtestadoFilter;
import br.com.saude.api.model.entity.po.Atestado;

public class AtestadoBuilder extends GenericEntityBuilder<Atestado, AtestadoFilter> {
	
	private Function<Map<String,Atestado>,Atestado> loadCat;
	private Function<Map<String,Atestado>,Atestado> loadProfissionalRealizouVisita;
	private Function<Map<String,Atestado>,Atestado> loadHomologacaoAtestado;
	private Function<Map<String,Atestado>,Atestado> loadRegime;

	public static AtestadoBuilder newInstance(Atestado atestado) {
		return new AtestadoBuilder(atestado);
	}
	
	public static AtestadoBuilder newInstance(List<Atestado> atestados) {
		return new AtestadoBuilder(atestados);
	}
	
	private AtestadoBuilder(List<Atestado> atestados) {
		super(atestados);
	}

	private AtestadoBuilder(Atestado atestado) {
		super(atestado);
	}

	@Override
	protected void initializeFunctions() {
		this.loadCat = atestados ->{
			if(atestados.get("origem").getCat() != null) {
				atestados.get("destino").setCat(CatBuilder.newInstance(atestados.get("origem").getCat()).getEntity());
			}
			return atestados.get("destino");
		};
		
		this.loadProfissionalRealizouVisita = atestados ->{
			if(atestados.get("origem").getProfissionalRealizouVisita() != null) {
				atestados.get("destino").setProfissionalRealizouVisita(
						ProfissionalBuilder.newInstance(atestados.get("origem").getProfissionalRealizouVisita()).getEntity());
			}
			return atestados.get("destino");
		};
		
		this.loadHomologacaoAtestado = atestados -> {
			if (atestados.get("origem").getHomologacaoAtestado() != null) {
				atestados.get("destino").setHomologacaoAtestado(
						HomologacaoAtestadoBuilder.newInstance(atestados.get("origem").getHomologacaoAtestado()).getEntity());
			}
			return atestados.get("destino");
		};
		
		this.loadRegime = atestados -> {
			if (atestados.get("origem").getRegime() != null) {
				atestados.get("destino").setRegime(
						RegimeBuilder.newInstance(atestados.get("origem").getRegime()).getEntity());
			}
			return atestados.get("destino");
		};
	}

	@Override
	protected Atestado clone(Atestado atestado) {
		Atestado newAtestado = new Atestado();
		
		newAtestado.setId(atestado.getId());
		newAtestado.setCid(atestado.getCid());
		newAtestado.setAtestadoFisicoRecebido(atestado.isAtestadoFisicoRecebido());
		newAtestado.setControleLicenca(atestado.isControleLicenca());
		newAtestado.setDataAgendamento(atestado.getDataAgendamento());
		newAtestado.setDataSolicitacao(atestado.getDataSolicitacao());
		newAtestado.setImpossibilidadeLocomocao(atestado.isImpossibilidadeLocomocao());
		newAtestado.setLancadoSap(atestado.isLancadoSap());
		newAtestado.setNumeroDias(atestado.getNumeroDias());
		newAtestado.setStatus(atestado.getStatus());
		newAtestado.setTipoBeneficio(atestado.getTipoBeneficio());
		newAtestado.setCausaAfastamento(atestado.getCausaAfastamento());
		newAtestado.setUltimoContato(atestado.getUltimoContato());
		newAtestado.setProximoContato(atestado.getProximoContato());
		newAtestado.setSituacaoEmpregado(atestado.getSituacaoEmpregado());
		newAtestado.setInicio(atestado.getInicio());
		newAtestado.setContatoMedico(atestado.getContatoMedico());
		newAtestado.setLocalAtendimento(atestado.getLocalAtendimento());
		newAtestado.setClinica(atestado.getClinica());
		newAtestado.setTelefoneExterno(atestado.getTelefoneExterno());
		newAtestado.setEmailExterno(atestado.getEmailExterno());
		newAtestado.setAposentadoInss(atestado.isAposentadoInss());
		newAtestado.setPresencial(atestado.isPresencial());
		newAtestado.setDataInicioEscalaTrabalho(atestado.getDataInicioEscalaTrabalho());
		newAtestado.setDataFimEscalaTrabalho(atestado.getDataFimEscalaTrabalho());
		newAtestado.setPossuiFeriasAgendadas(atestado.isPossuiFeriasAgendadas());
		newAtestado.setCiente(atestado.isCiente());
		newAtestado.setLimiteAuditar(atestado.getLimiteAuditar());
		newAtestado.setLimiteHomologar(atestado.getLimiteHomologar());
		newAtestado.setLimiteLancar(atestado.getLimiteLancar());
		newAtestado.setDataInicioFerias(atestado.getDataInicioFerias());
		newAtestado.setDataFimFerias(atestado.getDataFimFerias());
		newAtestado.setVersion(atestado.getVersion());
		
		if(atestado.getEmpregado() != null)
			newAtestado.setEmpregado(EmpregadoBuilder.newInstance(atestado.getEmpregado()).getEntity());
		
		if(atestado.getTarefa() != null)
			newAtestado.setTarefa(TarefaBuilder.newInstance(atestado.getTarefa()).getEntity());
		
		if(atestado.getMotivoRecusa() != null)
			newAtestado.setMotivoRecusa(MotivoRecusaAtestadoBuilder.newInstance(atestado.getMotivoRecusa()).getEntity());
		
		return newAtestado;
	}

	@Override
	public Atestado cloneFromFilter(AtestadoFilter filter) {
		return null;
	}
	
	public AtestadoBuilder loadCat() {
		return (AtestadoBuilder) this.loadProperty(this.loadCat);
	}
	
	public AtestadoBuilder loadProfissionalRealizouVisita() {
		return (AtestadoBuilder) this.loadProperty(this.loadProfissionalRealizouVisita);
	}

	public AtestadoBuilder loadHomologacaoAgestado() {
		return (AtestadoBuilder) this.loadProperty(this.loadHomologacaoAtestado);
	}
	
	public AtestadoBuilder loadRegime() {
		return (AtestadoBuilder) this.loadProperty(this.loadRegime);
	}
	
}