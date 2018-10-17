package br.com.saude.api.model.business;

import java.util.List;

import br.com.saude.api.generic.GenericReportBo;
import br.com.saude.api.model.entity.dto.AcompanhamentoSastEmpregadoDto;
import br.com.saude.api.model.persistence.report.AcompanhamentoSastReport;

public class AcompanhamentoSastBo implements GenericReportBo<AcompanhamentoSastEmpregadoDto> {

	private static AcompanhamentoSastBo instance;

	private AcompanhamentoSastBo() {

	}

	public static AcompanhamentoSastBo getInstance() {
		if (instance == null)
			instance = new AcompanhamentoSastBo();
		return instance;
	}

	public List<AcompanhamentoSastEmpregadoDto> getAcompanhamentoSasts(String abreviacaoEquipeAcolhimento, int idProfissional,
			int anoRisco) throws Exception {
		return AcompanhamentoSastReport.getInstance().getAcompanhamentoSasts(abreviacaoEquipeAcolhimento,
				idProfissional, anoRisco);
	}
}
