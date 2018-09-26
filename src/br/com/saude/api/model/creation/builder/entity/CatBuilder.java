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
	
	@Override
	public Cat cloneFromFilter(CatFilter filter) {
		return null;
	}

}
