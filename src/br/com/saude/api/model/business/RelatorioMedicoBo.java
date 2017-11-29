package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.RelatorioMedicoBuilder;
import br.com.saude.api.model.creation.builder.example.RelatorioMedicoExampleBuilder;
import br.com.saude.api.model.entity.filter.RelatorioMedicoFilter;
import br.com.saude.api.model.entity.po.RelatorioMedico;
import br.com.saude.api.model.persistence.RelatorioMedicoDao;

public class RelatorioMedicoBo extends GenericBo<RelatorioMedico, RelatorioMedicoFilter, RelatorioMedicoDao, 
	RelatorioMedicoBuilder, RelatorioMedicoExampleBuilder> {
	
	private static RelatorioMedicoBo instance;
	
	private RelatorioMedicoBo() {
		super();
	}
	
	public static RelatorioMedicoBo getInstance() {
		if (instance == null)
			instance = new RelatorioMedicoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() { }

}
