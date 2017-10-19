package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.po.Empregado;

public class EmpregadoBuilder extends GenericEntityBuilder<Empregado,EmpregadoFilter> {

	private Function<Map<String,Empregado>,Empregado> loadCargo;
	private Function<Map<String,Empregado>,Empregado> loadFuncao;
	private Function<Map<String,Empregado>,Empregado> loadRegime;
	private Function<Map<String,Empregado>,Empregado> loadGerencia;
	private Function<Map<String,Empregado>,Empregado> loadBase;
	private Function<Map<String,Empregado>,Empregado> loadGhe;
	private Function<Map<String,Empregado>,Empregado> loadGhee;
	private Function<Map<String,Empregado>,Empregado> loadInstalacoes;
	private Function<Map<String,Empregado>,Empregado> loadTelefones;
	private Function<Map<String,Empregado>,Empregado> loadEmpregadoVacinas;
	private Function<Map<String,Empregado>,Empregado> loadGrupoMonitoramentos;
	
	public static EmpregadoBuilder newInstance(Empregado empregado) {
		return new EmpregadoBuilder(empregado);
	}
	
	public static EmpregadoBuilder newInstance(List<Empregado> empregados) {
		return new EmpregadoBuilder(empregados);
	}
	
	private EmpregadoBuilder(Empregado empregado) {
		super(empregado);
	}

	private EmpregadoBuilder(List<Empregado> empregados) {
		super(empregados);
	}
	
	@Override
	protected void initializeFunctions() {
		this.loadCargo = empregados ->{
			if(empregados.get("origem").getCargo() != null) {
				empregados.get("destino").setCargo(CargoBuilder.newInstance(empregados.get("origem").getCargo()).getEntity());
			}
			return empregados.get("destino");
		};
		
		this.loadFuncao = empregados -> {
			if(empregados.get("origem").getFuncao() != null) {
				empregados.get("destino").setFuncao(FuncaoBuilder.newInstance(empregados.get("origem").getFuncao()).getEntity());
			}
			return empregados.get("destino");
		};
		
		this.loadRegime = empregados -> {
			if(empregados.get("origem").getRegime() != null) {
				empregados.get("destino").setRegime(RegimeBuilder.newInstance(empregados.get("origem").getRegime()).getEntity());
			}
			return empregados.get("destino");
		};
		
		this.loadGerencia = empregados -> {
			if(empregados.get("origem").getGerencia() != null) {
				empregados.get("destino").setGerencia(GerenciaBuilder.newInstance(empregados.get("origem").getGerencia()).getEntity());
			}
			return empregados.get("destino");
		};
		
		this.loadBase = empregados -> {
			if(empregados.get("origem").getBase() != null) {
				empregados.get("destino").setBase(BaseBuilder.newInstance(empregados.get("origem").getBase()).getEntity());
			}
			return empregados.get("destino");
		};
		
		this.loadGhe = empregados -> {
			if(empregados.get("origem").getGhe() != null) {
				empregados.get("destino").setGhe(GheBuilder.newInstance(empregados.get("origem").getGhe()).getEntity());
			}
			return empregados.get("destino");
		};
		
		this.loadGhee = empregados -> {
			if(empregados.get("origem").getGhee() != null) {
				empregados.get("destino").setGhee(GheeBuilder.newInstance(empregados.get("origem").getGhee()).getEntity());
			}
			return empregados.get("destino");
		};
		
		this.loadInstalacoes = empregados -> {
			if(empregados.get("origem").getInstalacoes() != null) {
				empregados.get("destino").setInstalacoes(InstalacaoBuilder.newInstance(empregados.get("origem").getInstalacoes()).getEntityList());
			}
			return empregados.get("destino");
		};
		
		this.loadTelefones = empregados -> {
			if(empregados.get("origem").getTelefones() != null) {
				empregados.get("destino").setTelefones(TelefoneBuilder.newInstance(empregados.get("origem").getTelefones()).getEntityList());
			}
			return empregados.get("destino");
		};
		
		this.loadEmpregadoVacinas = empregados -> {
			if(empregados.get("origem").getEmpregadoVacinas() != null) {
				empregados.get("destino").setEmpregadoVacinas(EmpregadoVacinaBuilder
											.newInstance(empregados.get("origem").getEmpregadoVacinas())
											.loadVacina()
											.getEntityList());	
			}
			return empregados.get("destino");
		};
		
		this.loadGrupoMonitoramentos = empregados -> {
			if(empregados.get("origem").getGrupoMonitoramentos() != null) {
				empregados.get("destino").setGrupoMonitoramentos(GrupoMonitoramentoBuilder
											.newInstance(empregados.get("origem").getGrupoMonitoramentos())
											.getEntityList());
			}
			return empregados.get("destino");
		};
	}

	@Override
	protected Empregado clone(Empregado empregado) {
		Empregado newEmpregado = new Empregado();
		
		newEmpregado.setId(empregado.getId());
		newEmpregado.setNome(empregado.getNome());
		newEmpregado.setCpf(empregado.getCpf());
		newEmpregado.setDataNascimento(empregado.getDataNascimento());
		newEmpregado.setChave(empregado.getChave());
		newEmpregado.setMatricula(empregado.getMatricula());
		newEmpregado.setRamal(empregado.getRamal());
		newEmpregado.setRg(empregado.getRg());
		newEmpregado.setSexo(empregado.getSexo());
		newEmpregado.setStatus(empregado.getStatus());
		newEmpregado.setVersion(empregado.getVersion());
		
		return newEmpregado;
	}
	
	public EmpregadoBuilder loadCargo() {
		return (EmpregadoBuilder) this.loadProperty(this.loadCargo);
	}
	
	public EmpregadoBuilder loadFuncao() {
		return (EmpregadoBuilder) this.loadProperty(this.loadFuncao);
	}
	
	public EmpregadoBuilder loadRegime() {
		return (EmpregadoBuilder) this.loadProperty(this.loadRegime);
	}
	
	public EmpregadoBuilder loadGhe() {
		return (EmpregadoBuilder) this.loadProperty(this.loadGhe);
	}
	
	public EmpregadoBuilder loadGhee() {
		return (EmpregadoBuilder) this.loadProperty(this.loadGhee);
	}
	
	public EmpregadoBuilder loadGerencia() {
		return (EmpregadoBuilder) this.loadProperty(this.loadGerencia);
	}
	
	public EmpregadoBuilder loadBase() {
		return (EmpregadoBuilder) this.loadProperty(this.loadBase);
	}
	
	public EmpregadoBuilder loadInstalacoes() {
		return (EmpregadoBuilder) this.loadProperty(this.loadInstalacoes);
	}
	
	public EmpregadoBuilder loadTelefones() {
		return (EmpregadoBuilder) this.loadProperty(this.loadTelefones);
	}
	
	public EmpregadoBuilder loadEmpregadoVacinas() {
		return (EmpregadoBuilder) this.loadProperty(this.loadEmpregadoVacinas);
	}
	
	public EmpregadoBuilder loadGrupoMonitoramentos() {
		return (EmpregadoBuilder) this.loadProperty(this.loadGrupoMonitoramentos);
	}

	@Override
	public Empregado cloneFromFilter(EmpregadoFilter filter) {
		return null;
	}
}
