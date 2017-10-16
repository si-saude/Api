package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.ProfissionalFilter;
import br.com.saude.api.model.entity.po.Profissional;

public class ProfissionalBuilder extends GenericEntityBuilder<Profissional,ProfissionalFilter> {

	private Function<Map<String,Profissional>,Profissional> loadProfissionalConselho;
	private Function<Map<String,Profissional>,Profissional> loadCurriculo;
	private Function<Map<String,Profissional>,Profissional> loadCargo;
	private Function<Map<String,Profissional>,Profissional> loadEquipe;
	private Function<Map<String,Profissional>,Profissional> loadLocalizacao;
	private Function<Map<String,Profissional>,Profissional> loadEndereco;
	private Function<Map<String,Profissional>,Profissional> loadTelefones;
	private Function<Map<String,Profissional>,Profissional> loadProfissionalVacinas;
	
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
		newProfissional.setChave(profissional.getChave());
		newProfissional.setDataNascimento(profissional.getDataNascimento());
		newProfissional.setDataAso(profissional.getDataAso());
		newProfissional.setMatricula(profissional.getMatricula());
		newProfissional.setMi(profissional.getMi());
		newProfissional.setNome(profissional.getNome());
		newProfissional.setRamal(profissional.getRamal());
		newProfissional.setVersion(profissional.getVersion());
		
		return newProfissional;
	}
	
	@Override
	protected void initializeFunctions() {
		this.loadTelefones = profissionais -> {
			if(profissionais.get("origem").getTelefones() != null) {
				profissionais.get("destino").setTelefones(TelefoneBuilder
													.newInstance(profissionais.get("origem").getTelefones())
													.getEntityList());
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
		
		this.loadCargo = profissionais -> {
			if(profissionais.get("origem").getCargo()!= null) {
				profissionais.get("destino").setCargo(CargoBuilder.newInstance(profissionais.get("origem").getCargo()).getEntity());
			}
			return profissionais.get("destino");
		};
		
		this.loadEquipe = profissionais -> {
			if(profissionais.get("origem").getEquipe()!= null) {
				profissionais.get("destino").setEquipe(EquipeBuilder.newInstance(profissionais.get("origem").getEquipe()).getEntity());
			}
			return profissionais.get("destino");
		};
		
		this.loadLocalizacao = profissionais -> {
			if(profissionais.get("origem").getLocalizacao()!= null) {
				profissionais.get("destino").setLocalizacao(LocalizacaoBuilder.newInstance(profissionais.get("origem").getLocalizacao()).getEntity());
			}
			return profissionais.get("destino");
		};
		
		this.loadEndereco = profissionais -> {
			if(profissionais.get("origem").getEndereco() != null) {
				profissionais.get("destino").setEndereco(EnderecoBuilder.newInstance(profissionais.get("origem").getEndereco()).loadCidade().getEntity());
			}
			return profissionais.get("destino");
		};
		
		this.loadProfissionalVacinas = profissionais -> {
			if(profissionais.get("origem").getProfissionalVacinas() != null) {
				profissionais.get("destino").setProfissionalVacinas(ProfissionalVacinaBuilder
											.newInstance(profissionais.get("origem").getProfissionalVacinas())
											.loadVacina()
											.getEntityList());	
			}
			return profissionais.get("destino");
		};
	}
	
	public ProfissionalBuilder loadProfissionalConselho() {
		return (ProfissionalBuilder) this.loadProperty(this.loadProfissionalConselho);
	}
	
	public ProfissionalBuilder loadCurriculo() {
		return (ProfissionalBuilder) this.loadProperty(this.loadCurriculo);
	}
	
	public ProfissionalBuilder loadCargo() {
		return (ProfissionalBuilder) this.loadProperty(this.loadCargo);
	}
	
	public ProfissionalBuilder loadEquipe() {
		return (ProfissionalBuilder) this.loadProperty(this.loadEquipe);
	}
	
	public ProfissionalBuilder loadLocalizacao() {
		return (ProfissionalBuilder) this.loadProperty(this.loadLocalizacao);
	}
	
	public ProfissionalBuilder loadEndereco() {
		return (ProfissionalBuilder) this.loadProperty(this.loadEndereco);
	}
	
	public ProfissionalBuilder loadTelefones() {
		return (ProfissionalBuilder) this.loadProperty(this.loadTelefones);
	}
	
	public ProfissionalBuilder loadProfissionalVacinas() {
		return (ProfissionalBuilder) this.loadProperty(this.loadProfissionalVacinas);
	}

	@Override
	public Profissional cloneFromFilter(ProfissionalFilter filter) {
		return null;
	}
}
