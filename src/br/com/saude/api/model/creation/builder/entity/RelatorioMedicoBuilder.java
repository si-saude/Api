package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.RelatorioMedicoFilter;
import br.com.saude.api.model.entity.po.RelatorioMedico;

public class RelatorioMedicoBuilder extends GenericEntityBuilder<RelatorioMedico, RelatorioMedicoFilter> {

	public static RelatorioMedicoBuilder newInstance(RelatorioMedico relatorioMedico) {
		return new RelatorioMedicoBuilder(relatorioMedico);
	}
	
	public static RelatorioMedicoBuilder newInstance(List<RelatorioMedico> relatorioMedicos) {
		return new RelatorioMedicoBuilder(relatorioMedicos);
	}
	
	private RelatorioMedicoBuilder(RelatorioMedico relatorioMedico) {
		super(relatorioMedico);
	}

	private RelatorioMedicoBuilder(List<RelatorioMedico> relatorioMedicos) {
		super(relatorioMedicos);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected RelatorioMedico clone(RelatorioMedico relatorioMedico) {
		RelatorioMedico newRelatorioMedico = new RelatorioMedico();
		
		newRelatorioMedico.setId(relatorioMedico.getId());
		newRelatorioMedico.setFinalizado(relatorioMedico.isFinalizado());
		newRelatorioMedico.setMedicoPrestador(relatorioMedico.getMedicoPrestador());
		newRelatorioMedico.setQuestionamentos(relatorioMedico.getQuestionamentos());
		newRelatorioMedico.setResumo(relatorioMedico.getResumo());
		newRelatorioMedico.setVersion(relatorioMedico.getVersion());
		
		if(relatorioMedico.getEmpregadoConvocacaoExame() != null)
			newRelatorioMedico.setEmpregadoConvocacaoExame(EmpregadoConvocacaoExameBuilder
					.newInstance(relatorioMedico.getEmpregadoConvocacaoExame()).getEntity());
		
		if(relatorioMedico.getMedico() != null)
			newRelatorioMedico.setMedico(ProfissionalBuilder
					.newInstance(relatorioMedico.getMedico()).getEntity());
		
		return newRelatorioMedico;
	}

	@Override
	public RelatorioMedico cloneFromFilter(RelatorioMedicoFilter filter) {
		return null;
	}

}
