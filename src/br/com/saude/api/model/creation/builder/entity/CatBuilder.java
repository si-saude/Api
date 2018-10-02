package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.CatFilter;
import br.com.saude.api.model.entity.po.Cat;
import br.com.saude.api.model.persistence.EmpregadoDao;

public class CatBuilder extends GenericEntityBuilder<Cat, CatFilter> {
	
	private Function<Map<String,Cat>,Cat> loadProfissionalCaracterizacao;
	private Function<Map<String,Cat>,Cat> loadProfissionalClassificacao;
	private Function<Map<String,Cat>,Cat> loadAgenteCausador;
	private Function<Map<String,Cat>,Cat> loadParteCorpoAtingida;
	private Function<Map<String,Cat>,Cat> loadNaturezaLesao;
	private Function<Map<String,Cat>,Cat> loadMunicipio;
	private Function<Map<String,Cat>,Cat> loadInstalacao;
	private Function<Map<String,Cat>,Cat> loadCnae;
	private Function<Map<String,Cat>,Cat> loadClassificacaoGravidade;
	private Function<Map<String,Cat>,Cat> loadDiagnosticoProvavel;
	
	public static CatBuilder newInstance(Cat cat) {
		return new CatBuilder(cat);
	}
	
	public static CatBuilder newInstance(List<Cat> cats) {
		return new CatBuilder(cats);
	}
	
	private CatBuilder(List<Cat> cats) {
		super(cats);
	}

	private CatBuilder(Cat cat) {
		super(cat);
	}

	@Override
	protected void initializeFunctions() {
		this.loadProfissionalCaracterizacao = cats ->{
			if(cats.get("origem").getProfissionalCaracterizacao() != null) {
				cats.get("destino").setProfissionalCaracterizacao(
						ProfissionalBuilder.newInstance(cats.get("origem").getProfissionalCaracterizacao()).getEntity());
			}
			return cats.get("destino");
		};
		
		this.loadProfissionalClassificacao = cats ->{
			if(cats.get("origem").getProfissionalClassificacao() != null) {
				cats.get("destino").setProfissionalClassificacao(
						ProfissionalBuilder.newInstance(cats.get("origem").getProfissionalClassificacao()).getEntity());
			}
			return cats.get("destino");
		};
		
		this.loadAgenteCausador = cats ->{
			if(cats.get("origem").getAgenteCausador() != null) {
				cats.get("destino").setAgenteCausador(
						AgenteCausadorBuilder.newInstance(cats.get("origem").getAgenteCausador()).getEntity());
			}
			return cats.get("destino");
		};
		
		this.loadParteCorpoAtingida = cats ->{
			if(cats.get("origem").getParteCorpoAtingida() != null) {
				cats.get("destino").setParteCorpoAtingida(
						ParteCorpoAtingidaBuilder.newInstance(cats.get("origem").getParteCorpoAtingida()).getEntity());
			}
			return cats.get("destino");
		};
		
		this.loadNaturezaLesao = cats ->{
			if(cats.get("origem").getNaturezaLesao() != null) {
				cats.get("destino").setNaturezaLesao(
						NaturezaLesaoBuilder.newInstance(cats.get("origem").getNaturezaLesao()).getEntity());
			}
			return cats.get("destino");
		};
		
		this.loadMunicipio = cats ->{
			if(cats.get("origem").getMunicipio() != null) {
				cats.get("destino").setMunicipio(
						CidadeBuilder.newInstance(cats.get("origem").getMunicipio()).getEntity());
			}
			return cats.get("destino");
		};
		
		this.loadInstalacao = cats ->{
			if(cats.get("origem").getInstalacao() != null) {
				cats.get("destino").setInstalacao(
						InstalacaoBuilder.newInstance(cats.get("origem").getInstalacao()).getEntity());
			}
			return cats.get("destino");
		};
		
		this.loadCnae = cats ->{
			if(cats.get("origem").getCnae() != null) {
				cats.get("destino").setCnae(
						CnaeBuilder.newInstance(cats.get("origem").getCnae()).getEntity());
			}
			return cats.get("destino");
		};
		
		this.loadClassificacaoGravidade = cats ->{
			if(cats.get("origem").getClassificacaoGravidade() != null) {
				cats.get("destino").setClassificacaoGravidade(
						ClassificacaoGravidadeBuilder.newInstance(cats.get("origem").getClassificacaoGravidade()).getEntity());
			}
			return cats.get("destino");
		};
		
		this.loadDiagnosticoProvavel = cats -> {
			if(cats.get("origem").getDiagnosticoProvavel() != null) {
				cats.get("destino").setDiagnosticoProvavel(
						DiagnosticoBuilder.newInstance(cats.get("origem").getDiagnosticoProvavel()).getEntity());
			}
			return cats.get("destino");
		};
	}

	@Override
	protected Cat clone(Cat cat) {
		Cat newCat = new Cat();
		
		newCat.setId(cat.getId());
		newCat.setGerenteContrato(cat.getGerenteContrato());
		newCat.setTelefoneGerente(cat.getTelefoneGerente());
		newCat.setFiscalContrato(cat.getFiscalContrato());
		newCat.setTelefoneFiscal(cat.getTelefoneFiscal());
		newCat.setDataOcorrencia(cat.getDataOcorrencia());
		newCat.setLocal(cat.getLocal());
		newCat.setDescricao(cat.getDescricao());
		newCat.setEmpregadoServicoCompanhia(cat.isEmpregadoServicoCompanhia());
		newCat.setOcorrenciaAmbienteTrabalho(cat.isOcorrenciaAmbienteTrabalho());
		newCat.setOcorrenciaTrajeto(cat.isOcorrenciaTrajeto());
		newCat.setResponsavelInformacao(cat.getResponsavelInformacao());
		newCat.setDataInformacao(cat.getDataInformacao());
		newCat.setCaracterizacao(cat.getCaracterizacao());
		newCat.setLesaoCorporal(cat.isLesaoCorporal());
		newCat.setNexoCausal(cat.getNexoCausal());
		newCat.setDataCaracterizacao(cat.getDataCaracterizacao());
		newCat.setTempoPrevisto(cat.getTempoPrevisto());
		newCat.setFerimentoGrave(cat.isFerimentoGrave());
		newCat.setDataClassificacao(cat.getDataClassificacao());
		newCat.setRemuneracao(cat.getRemuneracao());
		newCat.setDataObito(cat.getDataObito());
		newCat.setDataAvaliacaoMedica(cat.getDataAvaliacaoMedica());
		newCat.setNumeroSisin(cat.getNumeroSisin());
		newCat.setClassificacaoSisin(cat.getClassificacaoSisin());
		newCat.setGrauRiscoEmpresa(cat.getGrauRiscoEmpresa());
		newCat.setRegistroSd2000(cat.isRegistroSd2000());
		newCat.setRta(cat.getRta());
		newCat.setCatSd2000(cat.isCatSd2000());
		newCat.setDataEmissao(cat.getDataEmissao());
		newCat.setPendenciaCorrecao(cat.isPendenciaCorrecao());
		newCat.setJustificativaAtrasoEmissaoCat(cat.getJustificativaAtrasoEmissaoCat());
		newCat.setNumeroCartaMulta(cat.getNumeroCartaMulta());
		newCat.setTipoAcidente(cat.getTipoAcidente());
		newCat.setTipoCat(cat.getTipoCat());
		newCat.setComunicavelSus(cat.isComunicavelSus());
		newCat.setNumeroCat(cat.getNumeroCat());
		newCat.setCodigoCartaSindicato(cat.getCodigoCartaSindicato());
		newCat.setClassificacaoAnomalia(cat.getClassificacaoAnomalia());
		newCat.setDataComunicacaoSindicato(cat.getDataComunicacaoSindicato());
		newCat.setJustificativaAtrasoEmissaoCarta(cat.getJustificativaAtrasoEmissaoCarta());
		
		newCat.setVersion(cat.getVersion());
		
		if ( cat.getEmpregado() != null ) {
			try {
				newCat.setEmpregado(EmpregadoBuilder.newInstance(
						EmpregadoDao.getInstance().getByIdLoad(
								cat.getEmpregado().getId())).loadCargo().loadFuncao().loadGerencia().getEntity());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if ( cat.getEmpresa() != null )
			newCat.setEmpresa(EmpresaBuilder.newInstance(cat.getEmpresa()).getEntity());
		
		if ( cat.getGerencia() != null )
			newCat.setGerencia(GerenciaBuilder.newInstance(cat.getGerencia()).getEntity());
		
		if ( cat.getClassificacao() != null )
			newCat.setClassificacao(ClassificacaoAfastamentoBuilder.newInstance(cat.getClassificacao()).getEntity());
		
		if ( cat.getCid() != null )
			newCat.setCid(DiagnosticoBuilder.newInstance(cat.getCid()).getEntity());
	 	
		return newCat;
	}
	
	public CatBuilder loadProfissionalCaracterizacao() {
		return (CatBuilder) this.loadProperty(this.loadProfissionalCaracterizacao);
	}
	
	public CatBuilder loadProfissionalClassificacao() {
		return (CatBuilder) this.loadProperty(this.loadProfissionalClassificacao);
	}
	
	public CatBuilder loadAgenteCausador() {
		return (CatBuilder) this.loadProperty(this.loadAgenteCausador);
	}
	
	public CatBuilder loadParteCorpoAtingida() {
		return (CatBuilder) this.loadProperty(this.loadParteCorpoAtingida);
	}
	
	public CatBuilder loadNaturezaLesao() {
		return (CatBuilder) this.loadProperty(this.loadNaturezaLesao);
	}
	
	public CatBuilder loadMunicipio() {
		return (CatBuilder) this.loadProperty(this.loadMunicipio);
	}
	
	public CatBuilder loadInstalacao() {
		return (CatBuilder) this.loadProperty(this.loadInstalacao);
	}
	
	public CatBuilder loadCnae() {
		return (CatBuilder) this.loadProperty(this.loadCnae);
	}
	
	public CatBuilder loadClassificaoGravidade() {
		return (CatBuilder) this.loadProperty(this.loadClassificacaoGravidade);
	}
	
	public CatBuilder loadDiagnosticoProvavel() {
		return (CatBuilder) this.loadProperty(this.loadDiagnosticoProvavel);
	}
	
	@Override
	public Cat cloneFromFilter(CatFilter filter) {
		return null;
	}

}
