package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.CatFilter;
import br.com.saude.api.model.entity.po.Cat;

public class CatBuilder extends GenericEntityBuilder<Cat, CatFilter> {
	
	private Function<Map<String,Cat>,Cat> loadGerencia;
	private Function<Map<String,Cat>,Cat> loadEmpregado;
	private Function<Map<String,Cat>,Cat> loadEmpresa;
	private Function<Map<String,Cat>,Cat> loadDiagnostico;
	private Function<Map<String,Cat>,Cat> loadParteCorpoAtingida;
	private Function<Map<String,Cat>,Cat> loadAgenteCausador;
	private Function<Map<String,Cat>,Cat> loadNaturezaLesao;
	private Function<Map<String,Cat>,Cat> loadBase;

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
		this.loadGerencia = cats ->{
			if(cats.get("origem").getGerencia() != null) {
				cats.get("destino").setGerencia(GerenciaBuilder.newInstance(cats.get("origem").getGerencia()).getEntity());
			}
			return cats.get("destino");
		};
		
		this.loadEmpregado = cats ->{
			if(cats.get("origem").getEmpregado() != null) {
				cats.get("destino").setEmpregado(EmpregadoBuilder.newInstance(cats.get("origem").getEmpregado()).getEntity());
			}
			return cats.get("destino");
		};
		
		this.loadEmpresa = cats ->{
			if(cats.get("origem").getEmpresa() != null) {
				cats.get("destino").setEmpresa(FornecedorBuilder.newInstance(cats.get("origem").getEmpresa()).getEntity());
			}
			return cats.get("destino");
		};
		
		this.loadDiagnostico = cats ->{
			if(cats.get("origem").getDiagnostico() != null) {
				cats.get("destino").setDiagnostico(DiagnosticoBuilder.newInstance(cats.get("origem").getDiagnostico()).getEntity());
			}
			return cats.get("destino");
		};
		
		this.loadParteCorpoAtingida = cats ->{
			if(cats.get("origem").getParteCorpoAtingida() != null) {
				cats.get("destino").setParteCorpoAtingida(ParteCorpoAtingidaBuilder.newInstance(cats.get("origem").getParteCorpoAtingida()).getEntity());
			}
			return cats.get("destino");
		};
		
		this.loadAgenteCausador = cats ->{
			if(cats.get("origem").getAgenteCausador() != null) {
				cats.get("destino").setAgenteCausador(AgenteCausadorBuilder.newInstance(cats.get("origem").getAgenteCausador()).getEntity());
			}
			return cats.get("destino");
		};
		
		this.loadNaturezaLesao = cats ->{
			if(cats.get("origem").getNaturezaLesao() != null) {
				cats.get("destino").setNaturezaLesao(NaturezaLesaoBuilder.newInstance(cats.get("origem").getNaturezaLesao()).getEntity());
			}
			return cats.get("destino");
		};
		
		this.loadBase = cats -> {
			if(cats.get("origem").getBase() != null) {
				cats.get("destino").setBase(BaseBuilder.newInstance(cats.get("origem").getBase()).getEntity());
			}
			return cats.get("destino");
		};
	}

	@Override
	protected Cat clone(Cat cat) {
		Cat newCat = new Cat();
		
		newCat.setId(cat.getId());
		newCat.setAfastamento(cat.isAfastamento());
		newCat.setCargo(cat.getCargo());
		newCat.setCatSd2000(cat.isCatSd2000());
		newCat.setClassificacaoSisin(cat.getClassificacaoSisin());
		newCat.setCodigoCartaSindicato(cat.getCodigoCartaSindicato());
		newCat.setComunicavelSus(cat.isComunicavelSus());
		newCat.setContratado(cat.isContratado());
		newCat.setCpf(cat.getCpf());
		newCat.setDataAvaliacaoMedica(cat.getDataAvaliacaoMedica());
		newCat.setDataComunicacaoSindicato(cat.getDataComunicacaoSindicato());
		newCat.setDataEmissaoCat(cat.getDataEmissaoCat());
		newCat.setDataNascimento(cat.getDataNascimento());
		newCat.setDiaHoraAcidente(cat.getDiaHoraAcidente());
		newCat.setFerimentoGraveConformeAnp(cat.isFerimentoGraveConformeAnp());
		newCat.setGravidade(cat.getGravidade());
		newCat.setInstalacao(cat.getInstalacao());
		newCat.setNome(cat.getNome());
		newCat.setNumeroSisin(cat.getNumeroSisin());
		newCat.setRemuneracao(cat.getRemuneracao());
		newCat.setRta(cat.getRta());
		newCat.setSexo(cat.getSexo());
		newCat.setTipoAcidente(cat.getTipoAcidente());
		newCat.setNumero(cat.getNumero());
		newCat.setPartesCorpo(cat.getPartesCorpo());
		newCat.setTipoCat(cat.getTipoCat());
		newCat.setRegime(cat.getRegime());
		newCat.setRegistroSd2000(cat.isRegistroSd2000());
		newCat.setVersion(cat.getVersion());
		
		return newCat;
	}
	
	public CatBuilder loadGerencia() {
		return (CatBuilder) this.loadProperty(this.loadGerencia);
	}
	
	public CatBuilder loadEmpregado() {
		return (CatBuilder) this.loadProperty(this.loadEmpregado);
	}
	
	public CatBuilder loadEmpresa() {
		return (CatBuilder) this.loadProperty(this.loadEmpresa);
	}
	
	public CatBuilder loadDiagnostico() {
		return (CatBuilder) this.loadProperty(this.loadDiagnostico);
	}
	
	public CatBuilder loadParteCorpoAtingida() {
		return (CatBuilder) this.loadProperty(this.loadParteCorpoAtingida);
	}
	
	public CatBuilder loadAgenteCausador() {
		return (CatBuilder) this.loadProperty(this.loadAgenteCausador);
	}
	
	public CatBuilder loadNaturezaLesao() {
		return (CatBuilder) this.loadProperty(this.loadNaturezaLesao);
	}
	
	public CatBuilder loadBase() {
		return (CatBuilder) this.loadProperty(this.loadBase);
	}

	@Override
	public Cat cloneFromFilter(CatFilter filter) {
		return null;
	}

}
