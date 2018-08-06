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
	}

	@Override
	protected Atestado clone(Atestado atestado) {
		Atestado newAtestado = new Atestado();
		
		newAtestado.setId(atestado.getId());
		newAtestado.setAtestadoFisicoRecebido(atestado.isAtestadoFisicoRecebido());
		newAtestado.setCid(atestado.getCid());
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
	    
		newAtestado.setVersion(atestado.getVersion());
		
		if(atestado.getTarefa() != null)
			newAtestado.setTarefa(TarefaBuilder.newInstance(atestado.getTarefa()).getEntity());
		
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
	
}
