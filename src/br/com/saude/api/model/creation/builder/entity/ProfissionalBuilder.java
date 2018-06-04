package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.ProfissionalFilter;
import br.com.saude.api.model.entity.po.Profissional;

public class ProfissionalBuilder extends GenericEntityBuilder<Profissional,ProfissionalFilter> {

	private Function<Map<String,Profissional>,Profissional> loadEmpregado;
	private Function<Map<String,Profissional>,Profissional> loadProfissionalConselho;
	private Function<Map<String,Profissional>,Profissional> loadCurriculo;
	private Function<Map<String,Profissional>,Profissional> loadEquipe;
	private Function<Map<String,Profissional>,Profissional> loadEquipeCoordenador; 
	private Function<Map<String,Profissional>,Profissional> loadLocalizacao;
	private Function<Map<String,Profissional>,Profissional> loadServicos;
	
	public static ProfissionalBuilder newInstance(Profissional profissional) {
		return new ProfissionalBuilder(profissional);
	}
	
	public static ProfissionalBuilder newInstance(List<Profissional> profissionais) {
		return new ProfissionalBuilder(profissionais);
	}
	
	private ProfissionalBuilder(Profissional profissional) {
		super(profissional);
	}

	private ProfissionalBuilder(List<Profissional> profissionais) {
		super(profissionais);
	}

	@Override
	protected Profissional clone(Profissional profissional) {
		Profissional newProfissional = new Profissional();
		
		newProfissional.setId(profissional.getId());
		newProfissional.setDataAso(profissional.getDataAso());
		newProfissional.setMi(profissional.getMi());
		newProfissional.setVersion(profissional.getVersion());
		
		if(profissional.getEmpregado() != null)
			newProfissional.setEmpregado(new EmpregadoBuilder(profissional.getEmpregado()).getEntity());
		
		return newProfissional;
	}
	
	@Override
	protected void initializeFunctions() {
		this.loadEmpregado = profissionais -> {
			if(profissionais.get("origem").getEmpregado()!= null) {
				profissionais.get("destino").setEmpregado(EmpregadoBuilder
													.newInstance(profissionais.get("origem").getEmpregado())
						.getEntity());
			}
			
			return profissionais.get("destino");
		};
		
		
		this.loadProfissionalConselho = profissionais -> {
			if(profissionais.get("origem").getProfissionalConselho()!= null) {
				profissionais.get("destino").setProfissionalConselho(ProfissionalConselhoBuilder
													.newInstance(profissionais.get("origem").getProfissionalConselho())
						.getEntity());
			}
			
			return profissionais.get("destino");
		};
		
		this.loadCurriculo = profissionais -> {
			if(profissionais.get("origem").getCurriculo()!= null) {
				profissionais.get("destino").setCurriculo(CurriculoBuilder.newInstance(profissionais.get("origem").getCurriculo())
						.loadCurriculoCursos().getEntity());
			}
			return profissionais.get("destino");
		};
		
		this.loadEquipe = profissionais -> {
			if(profissionais.get("origem").getEquipe()!= null) {
				profissionais.get("destino").setEquipe(EquipeBuilder.newInstance(profissionais.get("origem").getEquipe()).getEntity());
			}
			return profissionais.get("destino");
		};
		
		this.loadEquipeCoordenador = profissionais -> {
			if(profissionais.get("origem").getEquipe()!= null) {
				profissionais.get("destino").setEquipe(EquipeBuilder.newInstance(profissionais.get("origem").getEquipe()).loadCoordenador().getEntity());
			}
			return profissionais.get("destino");
		};
		
		this.loadLocalizacao = profissionais -> {
			if(profissionais.get("origem").getLocalizacao()!= null) {
				profissionais.get("destino").setLocalizacao(LocalizacaoBuilder.newInstance(profissionais.get("origem").getLocalizacao()).getEntity());
			}
			return profissionais.get("destino");
		};
		
		this.loadServicos = profissionais -> {
			if(profissionais.get("origem").getServicos()!=null) {
				profissionais.get("destino").setServicos(ServicoBuilder.newInstance(profissionais.get("origem").getServicos()).getEntityList());
			}
			return profissionais.get("destino");
		};
		
	}
	
	public ProfissionalBuilder loadEmpregado() {
		return (ProfissionalBuilder) this.loadProperty(this.loadEmpregado);
	}
	
	public ProfissionalBuilder loadProfissionalConselho() {
		return (ProfissionalBuilder) this.loadProperty(this.loadProfissionalConselho);
	}
	
	public ProfissionalBuilder loadCurriculo() {
		return (ProfissionalBuilder) this.loadProperty(this.loadCurriculo);
	}
	
	public ProfissionalBuilder loadEquipe() {
		return (ProfissionalBuilder) this.loadProperty(this.loadEquipe);
	}
	
	public ProfissionalBuilder loadEquipeCoordenador() {
		return (ProfissionalBuilder) this.loadProperty(this.loadEquipeCoordenador);
	}
	
	public ProfissionalBuilder loadLocalizacao() {
		return (ProfissionalBuilder) this.loadProperty(this.loadLocalizacao);
	}
	
	public ProfissionalBuilder loadServicos() {
		return (ProfissionalBuilder) this.loadProperty(this.loadServicos);
	} 

	@Override
	public Profissional cloneFromFilter(ProfissionalFilter filter) {
		return null;
	}
}
