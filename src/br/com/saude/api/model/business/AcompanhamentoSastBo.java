package br.com.saude.api.model.business;

import java.io.IOException;
import java.util.List;

import br.com.saude.api.generic.GenericReportBo;
import br.com.saude.api.model.entity.dto.AcompanhamentoSastDto;
import br.com.saude.api.model.persistence.report.AcompanhamentoSastReport;

public class AcompanhamentoSastBo implements GenericReportBo<AcompanhamentoSastDto> {

	private static AcompanhamentoSastBo instance;

	private AcompanhamentoSastBo() {

	}

	public static AcompanhamentoSastBo getInstance() {
		if (instance == null)
			instance = new AcompanhamentoSastBo();
		return instance;
	}

	public List<AcompanhamentoSastDto> getAcompanhamentoSasts(String abreviacaoEquipeAcolhimento, int idProfissional,
			int anoRisco) throws Exception {
		return AcompanhamentoSastReport.getInstance().getAcompanhamentoSasts(abreviacaoEquipeAcolhimento,
				idProfissional, anoRisco);
	}
}
