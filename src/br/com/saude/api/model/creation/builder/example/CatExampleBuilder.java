package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

import br.com.saude.api.generic.CriteriaExample;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.CatFilter;
import br.com.saude.api.model.entity.po.Cat;

public class CatExampleBuilder extends GenericExampleBuilder<Cat, CatFilter> {

	public static CatExampleBuilder newInstance(CatFilter filter) {
		return new CatExampleBuilder(filter);
	}
	
	private CatExampleBuilder(CatFilter filter) {
		super(filter);
	}
	
	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addEmpregado();
		addEmpresa();
		addGerencia();
		addTelefoneGerente();
		addFiscalContrato();
		addTelefoneFiscal();
		addDataOcorrencia();
		addLocal();
		addDescricao();
		addEmpregadoServicoCompanhia();
		addOcorrenciaAmbienteTrabalho();
		addOcorrenciaTrajeto();
		addResponsavelInformacao();
		addDataInformacao();
		addCaracterizacao();
		addLesaoCorporal();
		addNexoCausal();
		addProfissionalCaracterizacao();
		addDataCaracterizacao();
		addClassificacao();
		addTempoPrevisto();
		addCid();
		addFerimentoGrave();
		addProfissionalClassificacao();
		addDataClassificacao();
		addRegistroSd2000();
		addCatSd2000();
		addComunicavelSus();
		addPendenciaCorrecao();
		addCatInss();
		addConvocado();
		addAusenciaExames();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		addEmpregado();
		addEmpresa();
		addGerencia();
		addTelefoneGerente();
		addFiscalContrato();
		addTelefoneFiscal();
		addDataOcorrencia();
		addLocal();
		addDescricao();
		addEmpregadoServicoCompanhia();
		addOcorrenciaAmbienteTrabalho();
		addOcorrenciaTrajeto();
		addResponsavelInformacao();
		addDataInformacao();
		addCaracterizacao();
		addLesaoCorporal();
		addNexoCausal();
		addProfissionalCaracterizacao();
		addDataCaracterizacao();
		addClassificacao();
		addTempoPrevisto();
		addCid();
		addFerimentoGrave();
		addProfissionalClassificacao();
		addDataClassificacao();
		addRegistroSd2000();
		addCatSd2000();
		addComunicavelSus();
		addPendenciaCorrecao();
		addCatInss();
		addConvocado();
		addAusenciaExames();
	}
	
	private void addEmpregado() throws InstantiationException, IllegalAccessException {
		if(this.filter.getEmpregado()!=null) {
			CriteriaExample criteriaExample = EmpregadoExampleBuilder
					.newInstance(this.filter.getEmpregado()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("empregado", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addEmpresa() throws InstantiationException, IllegalAccessException {
		if(this.filter.getEmpresa()!=null) {
			CriteriaExample criteriaExample = EmpresaExampleBuilder
					.newInstance(this.filter.getEmpresa()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("empresa", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addGerencia() throws InstantiationException, IllegalAccessException {
		if(this.filter.getGerencia()!=null) {
			CriteriaExample criteriaExample = GerenciaExampleBuilder
					.newInstance(this.filter.getGerencia()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("gerencia", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addTelefoneGerente() {
		if(this.filter.getTelefoneGerente() != null)
			this.criterions.add(Restrictions.ilike("telefoneGerente", Helper.filterLike(this.filter.getTelefoneGerente())));
	}
	
	private void addFiscalContrato() {
		if(this.filter.getFiscalContrato() != null)
			this.criterions.add(Restrictions.ilike("fiscalContrato", Helper.filterLike(this.filter.getFiscalContrato())));
	}
	
	private void addTelefoneFiscal() {
		if(this.filter.getTelefoneFiscal() != null)
			this.criterions.add(Restrictions.ilike("telefoneFiscal", Helper.filterLike(this.filter.getTelefoneFiscal())));
	}
	
	private void addDataOcorrencia() {
		this.addData("dataOcorrencia", this.filter.getDataOcorrencia());
	}
	
	private void addLocal() {
		if(this.filter.getLocal() != null)
			this.criterions.add(Restrictions.ilike("local", Helper.filterLike(this.filter.getLocal())));
	}
	
	private void addDescricao() {
		if(this.filter.getDescricao() != null)
			this.criterions.add(Restrictions.ilike("descricao", Helper.filterLike(this.filter.getDescricao())));
	}
	
	protected void addEmpregadoServicoCompanhia() {
		this.entity.setEmpregadoServicoCompanhia(this.addBoolean("empregadoServicoCompanhia", this.filter.getEmpregadoServicoCompanhia()));
	}
	
	protected void addOcorrenciaAmbienteTrabalho() {
		this.entity.setOcorrenciaAmbienteTrabalho(this.addBoolean("ocorrenciaAmbienteTrabalho", this.filter.getOcorrenciaAmbienteTrabalho()));
	}
	
	protected void addComunicavelSus() {
		this.entity.setComunicavelSus(this.addBoolean("comunicavelSus", this.filter.getComunicavelSus()));
	}
	
	protected void addPendenciaCorrecao() {
		this.entity.setPendenciaCorrecao(this.addBoolean("pendenciaCorrecao", this.filter.getPendenciaCorrecao()));
	}
	
	protected void addOcorrenciaTrajeto() {
		this.entity.setOcorrenciaTrajeto(this.addBoolean("ocorrenciaTrajeto", this.filter.getOcorrenciaTrajeto()));
	}
	
	private void addResponsavelInformacao() {
		if(this.filter.getResponsavelInformacao() != null)
			this.criterions.add(Restrictions.ilike("responsavelInformacao", Helper.filterLike(this.filter.getResponsavelInformacao())));
	}
	
	private void addDataInformacao() {
		this.addData("dataInformacao", this.filter.getDataInformacao());
	}
	
	private void addCaracterizacao() {
		if(this.filter.getCaracterizacao() != null)
			this.criterions.add(Restrictions.ilike("caracterizacao", Helper.filterLike(this.filter.getCaracterizacao())));
	}
	
	protected void addLesaoCorporal() {
		this.entity.setLesaoCorporal(this.addBoolean("lesaoCorporal", this.filter.getLesaoCorporal()));
	}
	
	private void addNexoCausal() {
		if(this.filter.getNexoCausal() != null)
			this.criterions.add(Restrictions.ilike("nexoCausal", Helper.filterLike(this.filter.getNexoCausal())));
	}
	
	private void addProfissionalCaracterizacao() throws InstantiationException, IllegalAccessException {
		if(this.filter.getProfissionalCaracterizacao()!=null) {
			CriteriaExample criteriaExample = ProfissionalExampleBuilder
					.newInstance(this.filter.getProfissionalCaracterizacao()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("profissionalCaracterizacao", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addDataCaracterizacao() {
		this.addData("dataCaracterizacao", this.filter.getDataCaracterizacao());
	}
	
	private void addClassificacao() throws InstantiationException, IllegalAccessException {
		if(this.filter.getClassificacao()!=null) {
			CriteriaExample criteriaExample = ClassificacaoAfastamentoExampleBuilder
					.newInstance(this.filter.getClassificacao()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("classificacao", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addTempoPrevisto() {
		if(this.filter.getTempoPrevisto() > 0)
			this.criterions.add(Restrictions.eq("tempoPrevisto", (int)this.filter.getTempoPrevisto()));
	}
	
	private void addCid() throws InstantiationException, IllegalAccessException {
		if(this.filter.getCid()!=null) {
			CriteriaExample criteriaExample = DiagnosticoExampleBuilder
					.newInstance(this.filter.getCid()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("cid", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	protected void addFerimentoGrave() {
		this.entity.setFerimentoGrave(this.addBoolean("ferimentoGrave", this.filter.getFerimentoGrave()));
	}
	
	private void addProfissionalClassificacao() throws InstantiationException, IllegalAccessException {
		if(this.filter.getProfissionalClassificacao()!=null) {
			CriteriaExample criteriaExample = ProfissionalExampleBuilder
					.newInstance(this.filter.getProfissionalClassificacao()).getCriteriaExample();
			this.criterias.add(new Triplet<String,CriteriaExample,JoinType>("profissionalClassificacao", criteriaExample, JoinType.INNER_JOIN));
		}
	}
	
	private void addDataClassificacao() {
		this.addData("dataClassificacao", this.filter.getDataClassificacao());
	}
	
	protected void addRegistroSd2000() {
		this.entity.setRegistroSd2000(this.addBoolean("registroSd2000", this.filter.getRegistroSd2000()));
	}
	
	protected void addCatSd2000() {
		this.entity.setCatSd2000(this.addBoolean("catSd2000", this.filter.getCatSd2000()));
	}
	
	protected void addCatInss() {
		this.entity.setCatInss(this.addBoolean("catInss", this.filter.getCatInss()));
	}

	protected void addConvocado() {
		this.entity.setConvocado(this.addBoolean("convocado", this.filter.getConvocado()));
	}
	
	protected void addAusenciaExames() {
		this.entity.setAusenciaExames(this.addBoolean("ausenciaExames", this.filter.getAusenciaExames()));
	}
	
}