package br.com.saude.api.model.entity.po;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.saude.api.model.creation.builder.entity.RiscoEmpregadoBuilder;

@Entity
public class RiscoPotencial {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar a Data do Risco Potencial.")
	private Date data;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Empregado empregado;
	
	@OneToMany(mappedBy="riscoPotencial", fetch=FetchType.LAZY, orphanRemoval=true)
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
	
	@Transient
	private List<RiscoEmpregado> riscosInterdiciplinares;
	
	@Transient
	private double valor;
	
	@Version
	private long version;

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

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public List<RiscoEmpregado> getRiscosInterdiciplinares() {
		
		if(this.riscoEmpregados != null) {
			
			this.riscosInterdiciplinares = new ArrayList<RiscoEmpregado>();
			
			int qtd = 0;
			for(RiscoEmpregado rE : this.riscoEmpregados) {
				for(Triagem t : rE.getTriagens()) {
					
					if(t.getIndicadorSast().isAusenteCalculoInterdisciplinar())
						continue;
					
					if(t.getIndicadorSast().getIndicadorAssociadoSasts() != null)
						qtd += t.getIndicadorSast().getIndicadorAssociadoSasts().size();
				}
			}
			
			for(RiscoEmpregado riscoEmpregado : this.riscoEmpregados) {
				
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
							
							for(RiscoEmpregado rE : this.riscoEmpregados) {
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
				
				r = r * (0.05/qtd);
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

	public double getValor() {
		
		if(this.riscosInterdiciplinares == null)
			this.getRiscosInterdiciplinares();
		
		if(this.riscosInterdiciplinares != null)
			this.valor = this.riscosInterdiciplinares.stream()
							.flatMapToDouble(r-> DoubleStream.of(r.getValor()))
							.average().getAsDouble();
		
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
}
