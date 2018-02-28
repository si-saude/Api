package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.DiagnosticoFilter;
import br.com.saude.api.model.entity.po.Diagnostico;

public class DiagnosticoBuilder extends GenericEntityBuilder<Diagnostico,DiagnosticoFilter> {

	public static DiagnosticoBuilder newInstance(Diagnostico diagnostico) {
		return new DiagnosticoBuilder(diagnostico);
	}
	
	public static DiagnosticoBuilder newInstance(List<Diagnostico> diagnosticos) {
		return new DiagnosticoBuilder(diagnosticos);
	}
	
	private DiagnosticoBuilder(List<Diagnostico> diagnosticos) {
		super(diagnosticos);
	}

	private DiagnosticoBuilder(Diagnostico diagnostico) {
		super(diagnostico);
	}

	@Override
	protected void initializeFunctions() {}

	@Override
	protected Diagnostico clone(Diagnostico diagnostico) {
		Diagnostico cloneDiagnostico = new Diagnostico();
		
		cloneDiagnostico.setId(diagnostico.getId());
		cloneDiagnostico.setVersion(diagnostico.getVersion());
		cloneDiagnostico.setDescricao(diagnostico.getDescricao());
		cloneDiagnostico.setCodigo(diagnostico.getCodigo());
		cloneDiagnostico.setInativo(diagnostico.isInativo());
		if ( diagnostico.getEixo() != null )
			cloneDiagnostico.setEixo(EixoBuilder.newInstance(diagnostico.getEixo()).getEntity());
		
		return cloneDiagnostico;
	}

	@Override
	public Diagnostico cloneFromFilter(DiagnosticoFilter filter) {
		return null;
	}
	
	
	
}
