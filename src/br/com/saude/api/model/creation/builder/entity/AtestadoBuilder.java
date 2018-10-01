package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.AtestadoFilter;
import br.com.saude.api.model.entity.po.Atestado;

public class AtestadoBuilder extends GenericEntityBuilder<Atestado, AtestadoFilter> {
	
	private Function<Map<String,Atestado>,Atestado> loadRegime;
	private Function<Map<String,Atestado>,Atestado> loadAgendamento;
	private Function<Map<String,Atestado>,Atestado> loadExamesConvocacao;
	private Function<Map<String,Atestado>,Atestado> loadHistoricoAtestados;
	private Function<Map<String,Atestado>,Atestado> loadAuditoriaAtestados;

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
		this.loadRegime = atestados -> {
			if (atestados.get("origem").getRegime() != null) {
				atestados.get("destino").setRegime(
						RegimeBuilder.newInstance(atestados.get("origem").getRegime()).getEntity());
			}
			return atestados.get("destino");
		};
		
		this.loadAgendamento = atestados -> {
			if (atestados.get("origem").getAgendamento() != null) {
				atestados.get("destino").setAgendamento(
						TarefaBuilder.newInstance(atestados.get("origem").getAgendamento()).getEntity());
			}
			return atestados.get("destino");
		};
		
		this.loadExamesConvocacao = atestados -> {
			if (atestados.get("origem").getExamesConvocacao() != null) {
				atestados.get("destino").setExamesConvocacao(
						ExameBuilder.newInstance(atestados.get("origem").getExamesConvocacao()).getEntityList());
			}
			return atestados.get("destino");
		};
		
		this.loadHistoricoAtestados = atestados -> {
			if (atestados.get("origem").getHistoricoAtestados() != null) {
				atestados.get("destino").setHistoricoAtestados(
						HistoricoAtestadoBuilder.newInstance(atestados.get("origem").getHistoricoAtestados()).getEntityList());
			}
			return atestados.get("destino");
		};
		
		this.loadAuditoriaAtestados = atestados -> {
			if (atestados.get("origem").getAuditoriaAtestados() != null) {
				atestados.get("destino").setAuditoriaAtestados(
						AuditoriaAtestadoBuilder.newInstance(atestados.get("origem").getAuditoriaAtestados()).getEntityList());
			}
			return atestados.get("destino");
		};
	}

	@Override
	protected Atestado clone(Atestado atestado) {
		Atestado newAtestado = new Atestado();
		
		newAtestado.setId(atestado.getId());
		newAtestado.setAtestadoFisicoRecebido(atestado.isAtestadoFisicoRecebido());
		newAtestado.setControleLicenca(atestado.isControleLicenca());
		newAtestado.setDataSolicitacao(atestado.getDataSolicitacao());
		newAtestado.setImpossibilidadeLocomocao(atestado.isImpossibilidadeLocomocao());
		newAtestado.setLancadoSap(atestado.isLancadoSap());
		newAtestado.setNumeroDias(atestado.getNumeroDias());
		newAtestado.setStatus(atestado.getStatus());
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
		newAtestado.setSomaDiasAtestados(atestado.getSomaDiasAtestados());
		newAtestado.setConcatenacaoDatasCids(atestado.getConcatenacaoDatasCids());
		newAtestado.setObservacao(atestado.getObservacao());
		newAtestado.setLancamentoSd2000(atestado.isLancamentoSd2000());
		newAtestado.setFim(atestado.getFim());
		newAtestado.setAusenciaExames(atestado.isAusenciaExames());
		newAtestado.setPreviewStatus(atestado.getPreviewStatus());
		newAtestado.setDataAuditoria(atestado.getDataAuditoria());
		newAtestado.setConvocado(atestado.isConvocado());
		newAtestado.setDataHomologacao(atestado.getDataHomologacao());
		newAtestado.setJustificativa(atestado.getJustificativa());
		newAtestado.setVersion(atestado.getVersion());
		
		if (atestado.getProfissional() != null) 
			newAtestado.setProfissional(ProfissionalBuilder.newInstance(atestado.getProfissional()).getEntity());
		
		if(atestado.getEmpregado() != null)
			newAtestado.setEmpregado(EmpregadoBuilder.newInstance(atestado.getEmpregado()).getEntity());
		
		if(atestado.getTarefa() != null)
			newAtestado.setTarefa(TarefaBuilder.newInstance(atestado.getTarefa()).getEntity());
		
		if(atestado.getMotivoRecusa() != null)
			newAtestado.setMotivoRecusa(MotivoRecusaAtestadoBuilder.newInstance(atestado.getMotivoRecusa()).getEntity());
		
		if(atestado.getCid() != null) 
			newAtestado.setCid(DiagnosticoBuilder.newInstance(atestado.getCid()).getEntity());
		
		return newAtestado;
	}

	@Override
	public Atestado cloneFromFilter(AtestadoFilter filter) {
		return null;
	}
	
	public AtestadoBuilder loadRegime() {
		return (AtestadoBuilder) this.loadProperty(this.loadRegime);
	}
	
	public AtestadoBuilder loadAgendamento() {
		return (AtestadoBuilder) this.loadProperty(this.loadAgendamento);
	}
	
	public AtestadoBuilder loadExamesConvocacao() {
		return (AtestadoBuilder) this.loadProperty(this.loadExamesConvocacao);
	}
	
	public AtestadoBuilder loadHistoricoAtestados() {
		return (AtestadoBuilder) this.loadProperty(this.loadHistoricoAtestados);
	}
	
	public AtestadoBuilder loadAuditoriaAtestados() {
		return (AtestadoBuilder) this.loadProperty(this.loadAuditoriaAtestados);
	}
}
