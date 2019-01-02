package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericFilter;

public class MudancaFuncaoFilter extends GenericFilter { 
		
	private CargoFilter cargo;
	private FuncaoFilter funcao;	
	private GheFilter ghe;	
	private GheeFilter ghee;	
	private RegimeFilter regime;	
	private GerenciaFilter gerencia;	
	private BaseFilter base;
	private TarefaFilter tarefa;
	private DateFilter dataTransferencia;
	
	public CargoFilter getCargo() {
		return cargo;
	}
	public void setCargo(CargoFilter cargo) {
		this.cargo = cargo;
	}
	public FuncaoFilter getFuncao() {
		return funcao;
	}
	public void setFuncao(FuncaoFilter funcao) {
		this.funcao = funcao;
	}
	public GheFilter getGhe() {
		return ghe;
	}
	public void setGhe(GheFilter ghe) {
		this.ghe = ghe;
	}
	public GheeFilter getGhee() {
		return ghee;
	}
	public void setGhee(GheeFilter ghee) {
		this.ghee = ghee;
	}
	public RegimeFilter getRegime() {
		return regime;
	}
	public void setRegime(RegimeFilter regime) {
		this.regime = regime;
	}
	public GerenciaFilter getGerencia() {
		return gerencia;
	}
	public void setGerencia(GerenciaFilter gerencia) {
		this.gerencia = gerencia;
	}
	public BaseFilter getBase() {
		return base;
	}
	public void setBase(BaseFilter base) {
		this.base = base;
	}
	public TarefaFilter getTarefa() {
		return tarefa;
	}
	public void setTarefa(TarefaFilter tarefa) {
		this.tarefa = tarefa;
	}
	public DateFilter getDataTransferencia() {
		return dataTransferencia;
	}
	public void setDataTransferencia(DateFilter dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}
	
}
