package br.com.saude.api.model.entity.po;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class RelatorioMedico {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Médico do Relatório.")
	@ManyToOne(fetch=FetchType.EAGER)
	private Profissional medico;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "empregadoconvocacaoexame_id")
	private EmpregadoConvocacaoExame empregadoConvocacaoExame;
	
	@Size(max = 256, message="Tamanho máximo para Médico Prestador: 256")
	private String medicoPrestador;
	
	@Size(max = 1024, message="Tamanho máximo para Resumo: 1024")
	private String resumo;
	
	@Size(max = 1024, message="Tamanho máximo para Questionamentos: 1024")
	private String questionamentos;
	
	private boolean finalizado;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Profissional getMedico() {
		return medico;
	}

	public void setMedico(Profissional medico) {
		this.medico = medico;
	}

	public EmpregadoConvocacaoExame getEmpregadoConvocacaoExame() {
		return empregadoConvocacaoExame;
	}

	public void setEmpregadoConvocacaoExame(EmpregadoConvocacaoExame empregadoConvocacaoExame) {
		this.empregadoConvocacaoExame = empregadoConvocacaoExame;
	}

	public String getMedicoPrestador() {
		return medicoPrestador;
	}

	public void setMedicoPrestador(String medicoPrestador) {
		this.medicoPrestador = medicoPrestador;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public String getQuestionamentos() {
		return questionamentos;
	}

	public void setQuestionamentos(String questionamentos) {
		this.questionamentos = questionamentos;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
