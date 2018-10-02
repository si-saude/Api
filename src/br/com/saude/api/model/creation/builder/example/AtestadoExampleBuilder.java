package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.AtestadoFilter;
import br.com.saude.api.model.entity.po.Atestado;

public class AtestadoExampleBuilder extends GenericExampleBuilder<Atestado, AtestadoFilter> {

	public static AtestadoExampleBuilder newInstance(AtestadoFilter filter) {
		return new AtestadoExampleBuilder(filter);
	}
	
	private AtestadoExampleBuilder(AtestadoFilter filter) {
		super(filter);
	}
	
	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addId();
		addAtestadoFisicoRecebido();
		addControleLicenca();
		addImpossibilidadeLocomocao();
		addLancadoSap();
		addAposentadoInss();
		addPresencial();
		addPossuiFeriasAgendadas();
		addCiente();
		addEmpregado();
		addMotivoRecusa();
		addLancamentoSd2000();
		addAusenciaExames();
		addDataAuditoria();
		addConvocado();
		addDataHomologacao();
		addStatus();
	}
	
	@Override
	protected void createExampleSelectList() { }
	
	private void addStatus() {
		if(this.filter.getStatus() != null)
			this.criterions.add(Restrictions.ilike("status", Helper.filterLike(this.filter.getStatus())));
	}
	
	protected void addAtestadoFisicoRecebido() {
		this.entity.setAtestadoFisicoRecebido(this.addBoolean("atestadoFisicoRecebido", this.filter.getAtestadoFisicoRecebido()));
	}
	
	protected void addControleLicenca() {
		this.entity.setControleLicenca(this.addBoolean("controleLicenca", this.filter.getControleLicenca()));
	}
	
	protected void addImpossibilidadeLocomocao() {
		this.entity.setImpossibilidadeLocomocao(this.addBoolean("impossibilidadeLocomocao", this.filter.getImpossibilidadeLocomocao()));
	}
	
	protected void addLancadoSap() {
		this.entity.setLancadoSap(this.addBoolean("lancadoSap", this.filter.getLancadoSap()));
	}
	
	protected void addAposentadoInss() {
		this.entity.setAposentadoInss(this.addBoolean("aposentadoInss", this.filter.getAposentadoInss()));
	}
	
	protected void addPresencial() {
		this.entity.setPresencial(this.addBoolean("presencial", this.filter.getPresencial()));
	}
	
	protected void addPossuiFeriasAgendadas() {
		this.entity.setPossuiFeriasAgendadas(this.addBoolean("possuiFeriasAgendadas", this.filter.getPossuiFeriasAgendadas()));
	}
	
	protected void addCiente() {
		this.entity.setCiente(this.addBoolean("ciente", this.filter.getCiente()));
	}
	
	protected void addLancamentoSd2000() {
		this.entity.setLancamentoSd2000(this.addBoolean("lancamentoSd2000", this.filter.getLancamentoSd2000()));
	}
	
	protected void addAusenciaExames() {
		this.entity.setAusenciaExames(this.addBoolean("ausenciaExames", this.filter.getAusenciaExames()));
	}
	
	protected void addConvocado() {
		this.entity.setConvocado(this.addBoolean("convocado", this.filter.getConvocado()));
	}
	
	private void addDataHomologacao() {
		super.addData("dataHomologacao", this.filter.getDataHomologacao());
	}
	
	private void addId() {
		if(this.filter.getId() > 0)
			this.criterions.add(Restrictions.eq("id", this.filter.getId()));
	}
	
	private void addEmpregado() throws InstantiationException, IllegalAccessException {
		if(this.filter.getEmpregado()!=null) {
			CriteriaExample criteriaExample = EmpregadoExampleBuilder
					.newInstance(this.filter.getEmpregado()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("empregado", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addMotivoRecusa() throws InstantiationException, IllegalAccessException {
		if(this.filter.getMotivoRecusa()!=null) {
			CriteriaExample criteriaExample = MotivoRecusaAtestadoExampleBuilder
					.newInstance(this.filter.getMotivoRecusa()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("motivoRecusa", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addDataAuditoria() {
		super.addData("dataAuditoria", this.filter.getDataAuditoria());
	}
}
