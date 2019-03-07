package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.AvaliacaoFisicaBuilder;
import br.com.saude.api.model.creation.builder.example.AvaliacaoFisicaExampleBuilder;
import br.com.saude.api.model.entity.filter.AvaliacaoFisicaFilter;
import br.com.saude.api.model.entity.po.Atendimento;
import br.com.saude.api.model.entity.po.AvaliacaoFisica;
import br.com.saude.api.model.persistence.AvaliacaoFisicaDao;

public class AvaliacaoFisicaBo extends
		GenericBo<AvaliacaoFisica, AvaliacaoFisicaFilter, AvaliacaoFisicaDao, AvaliacaoFisicaBuilder, AvaliacaoFisicaExampleBuilder> {
	
	private static AvaliacaoFisicaBo instance;

	private AvaliacaoFisicaBo() {
		super();
	}

	public static AvaliacaoFisicaBo getInstance() {
		if (instance == null)
			instance = new AvaliacaoFisicaBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {}

	
	public String getRelatorioProafAtendimento(Long atendimentoId) throws Exception {
		
		Atendimento atendimento = AtendimentoBo.getInstance().getById(atendimentoId);
		atendimento.getFilaEsperaOcupacional().setEmpregado(EmpregadoBo.getInstance().getById(atendimento.getFilaEsperaOcupacional().getEmpregado().getId()));
		
		return AtendimentoBo.getInstance().getRelatorioProaf(atendimento);
	}
}
