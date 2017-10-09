package br.com.saude.api.model.entity.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Instalacao {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Nome da Instalação.")
	@Size(max = 120, message="Tamanho máximo para Nome da Instalação: 120")
	@Column(unique=true)
	private String nome;
	
	@OneToMany(mappedBy="instalacao", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<IndicadorRiscoAcidenteInstalacao> indicadorRiscoAcidenteInstalacoes;
	
	@Version
	private long version;
}
