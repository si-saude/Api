package br.com.saude.api.model.entity.po;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.saude.api.model.business.RiscoEmpregadoBo;
import br.com.saude.api.model.creation.builder.entity.RiscoEmpregadoBuilder;
import br.com.saude.api.util.constant.StatusRPSat;

@Entity
public class RiscoPotencial {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar a Data do Risco Potencial.")
	private Date data;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Empregado empregado;
	
	@OneToMany(mappedBy="riscoPotencial", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<RiscoEmpregado> riscoEmpregados;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Equipe equipeResponsavel;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="riscopotencial_equipe", 
	joinColumns = {@JoinColumn(name="riscopotencial_id")}, 
	inverseJoinColumns = {@JoinColumn(name="equipe_id")})
	private List<Equipe> equipes;
	
	@Size(max = 2048, message="Tamanho máximo para Conduta/Percepção: 2048")
	private String condutaPercepcao;
	
	private Date inicioAgendamento;
	
	private Date fimAgendamento;
	
	private boolean atual;
	
	@Size(max = 32, message="Tamanho máximo para Status do Risco Potencial: 32")
	private String status;
	
	@Size(max = 4, message="Tamanho máximo para Abreviação: 4")
	private String abreviacaoEquipeAcolhimento;
	
	@Transient
	private List<RiscoEmpregado> riscosInterdiciplinares;
	
	@Transient
	private double valor;
	
	@Transient
	private String statusRPSat;
	
	@Transient
	private Profissional profissional;
	
	@Transient
	private List<Acao> acoesDelete;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Empregado getEmpregado() {
		return empregado;
	}

	public void setEmpregado(Empregado empregado) {
		this.empregado = empregado;
	}

	public List<RiscoEmpregado> getRiscoEmpregados() {
		return riscoEmpregados;
	}

	public void setRiscoEmpregados(List<RiscoEmpregado> riscoEmpregados) {
		this.riscoEmpregados = riscoEmpregados;
	}

	public List<RiscoEmpregado> getRiscosInterdiciplinares() throws Exception {
		
		if(this.riscoEmpregados != null && this.riscosInterdiciplinares == null) {
			
			this.riscosInterdiciplinares = new ArrayList<RiscoEmpregado>();
			
			List<RiscoEmpregado> riscosAtivos = this.riscoEmpregados.stream().filter(r->
					r.isAtivo()).collect(Collectors.toList());
			
			int qtd = 0;
			for(RiscoEmpregado rE : riscosAtivos) {
				
				if(rE.getTriagens() == null) {
					this.riscosInterdiciplinares = null;
					return this.riscosInterdiciplinares;
				}
				
				for(Triagem t : rE.getTriagens()) {
					
					if(t.getIndicadorSast().isAusenteCalculoInterdisciplinar())
						continue;
					
					if(t.getIndicadorSast().getIndicadorAssociadoSasts() != null)
						qtd += t.getIndicadorSast().getIndicadorAssociadoSasts().size();
				}
			}
			
			for(RiscoEmpregado riscoEmpregado : riscosAtivos) {
				
				riscoEmpregado = RiscoEmpregadoBo.getInstance().getByIdAll(riscoEmpregado.getId());
				
				RiscoEmpregado newRiscoEmpregado = RiscoEmpregadoBuilder
						.newInstance(riscoEmpregado).getEntity();
				double r = 0;
				
				for(Triagem triagem : riscoEmpregado.getTriagens()) {
					if(triagem.getIndice() < 0 || triagem.getIndice() >= 3)
						continue;
					
					if(triagem.getIndicadorSast().isAusenteCalculoInterdisciplinar())
						continue;
					
					if(triagem.getIndicadorSast().getIndicadorAssociadoSasts() != null) {
						for(IndicadorAssociadoSast indicadorAssociado : triagem
								.getIndicadorSast().getIndicadorAssociadoSasts()) {
							
							//OBTER A TRIAGEM CUJO CÓDIGO DO INDICADOR SEJA IGUAL AO CÓDIGO ASSOCIADO
							Triagem associada = null;
							
							for(RiscoEmpregado rE : riscosAtivos) {
								for(Triagem t : rE.getTriagens()) {
									if(t.getIndicadorSast().getCodigo().equals(indicadorAssociado.getCodigo())) {
										associada = t;									
										break;
									}
								}
								
								if(associada != null)
									break;
							}
							
							if(associada != null && associada.getIndice() < 3 && associada.getIndice() >= 0)
								r += 1;
						}
					}
				}
				
				if (qtd > 0)
					r = r * (0.05/qtd);
				else r = 0;
				
				newRiscoEmpregado.setValor(newRiscoEmpregado.getValor() + r);
				this.riscosInterdiciplinares.add(newRiscoEmpregado);
			}
			
			this.riscosInterdiciplinares.sort(new Comparator<RiscoEmpregado>(){
				public int compare(RiscoEmpregado arg0, RiscoEmpregado arg1) {
					return new Double(arg1.getValor()).compareTo(new Double(arg0.getValor()));
				}
			});
		}
		
		return riscosInterdiciplinares;
	}

	public double getValor() throws Exception {
		
		if(this.riscosInterdiciplinares == null)
			this.getRiscosInterdiciplinares();
		
		if(this.riscosInterdiciplinares != null && this.riscosInterdiciplinares.size() > 0) {
			
			List<RiscoEmpregado> riscos = new ArrayList<RiscoEmpregado>();
			int idade = this.empregado.getPessoa().getIdade();
			int indice = -1;
			
			if(idade >= 60)
				indice = 0;
			else if(idade >= 50)
				indice = 1;
			else if(idade >= 40)
				indice = 2;
			else if(idade >= 30)
				indice = 3;
			else if(idade >= 1)
				indice = 4;
			
			if(indice >= 0) {
				double r0 = 0.95 - (new Double(indice)/4.3);
				double r01 = (Math.log10(6 + 1) /(6 + 5)) / (6 + 1);
				double r1 = r0 + r01;
				
				RiscoEmpregado risco = new RiscoEmpregado();
				risco.setValor(r1);
				riscos.add(risco);
			}
			
			riscos.addAll(this.riscosInterdiciplinares);
			
			this.valor = riscos.stream()
							.flatMapToDouble(r-> DoubleStream.of(r.getValor()))
							.average().getAsDouble();
		}
		
		return this.valor;
	}

	public Equipe getEquipeResponsavel() {
		return equipeResponsavel;
	}

	public void setEquipeResponsavel(Equipe equipeResponsavel) {
		this.equipeResponsavel = equipeResponsavel;
	}

	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}

	public String getCondutaPercepcao() {
		return condutaPercepcao;
	}

	public void setCondutaPercepcao(String condutaPercepcao) {
		this.condutaPercepcao = condutaPercepcao;
	}

	public Date getInicioAgendamento() {
		return inicioAgendamento;
	}

	public void setInicioAgendamento(Date inicioAgendamento) {
		this.inicioAgendamento = inicioAgendamento;
	}

	public Date getFimAgendamento() {
		return fimAgendamento;
	}

	public void setFimAgendamento(Date fimAgendamento) {
		this.fimAgendamento = fimAgendamento;
	}

	public boolean isAtual() {
		return atual;
	}

	public void setAtual(boolean atual) {
		this.atual = atual;
	}
	

	public String getStatusRPSat() throws Exception {
		this.getValor();
		
		if ( this.valor > 0 && this.valor < 0.57 ) {
			this.statusRPSat = StatusRPSat.getInstance().ACEITAVEL;
		} else if ( this.valor >= 0.57 && this.valor < 0.72 ) {
			this.statusRPSat = StatusRPSat.getInstance().TOLERAVEL;
		} else if ( this.valor >= 0.72 ) {
			this.statusRPSat = StatusRPSat.getInstance().INACEITAVEL;
		}
		
		return statusRPSat;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Profissional getProfissional() {
		return profissional;
	}
	
	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public List<Acao> getAcoesDelete() {
		return acoesDelete;
	}

	public void setAcoesDelete(List<Acao> acoesDelete) {
		this.acoesDelete = acoesDelete;
	}

	public void setRiscosInterdiciplinares(List<RiscoEmpregado> riscosInterdiciplinares) {
		this.riscosInterdiciplinares = riscosInterdiciplinares;
	}

	public String getAbreviacaoEquipeAcolhimento() {
		return abreviacaoEquipeAcolhimento;
	}

	public void setAbreviacaoEquipeAcolhimento(String abreviacaoEquipeAcolhimento) {
		this.abreviacaoEquipeAcolhimento = abreviacaoEquipeAcolhimento;
	}
}
