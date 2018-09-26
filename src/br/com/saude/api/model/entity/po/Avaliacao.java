package br.com.saude.api.model.entity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Avaliacao {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Nome da Avaliação.")
	@Size(max = 128, message="Tamanho máximo para Nome da Avaliação: 128")
	@Column(unique=true)
	private String nome;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private GrupoMonitoramento grupoMonitoramento;
	
	@Version
	private long version;
	
	
}
