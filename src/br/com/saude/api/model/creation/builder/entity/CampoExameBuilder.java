package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.CampoExameFilter;
import br.com.saude.api.model.entity.po.CampoExame;

public class CampoExameBuilder extends GenericEntityBuilder<CampoExame,CampoExameFilter> {

	private CampoExameBuilder(CampoExame campoExame) {
		super(campoExame);
	}

	private CampoExameBuilder(List<CampoExame> campoExames) {
		super(campoExames);
	}
	
	public static CampoExameBuilder newInstance(CampoExame campoExame) {
		return new CampoExameBuilder(campoExame);
	}
	
	public static CampoExameBuilder newInstance(List<CampoExame> campoExames) {
		return new CampoExameBuilder(campoExames);
	}
	
	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected CampoExame clone(CampoExame campoExame) {
		CampoExame newCampoExame = new CampoExame();
		
		newCampoExame.setId(campoExame.getId());
		newCampoExame.setCodigo(campoExame.getCodigo());
		newCampoExame.setTitulo(campoExame.getTitulo());
		newCampoExame.setNumeroLinhas(campoExame.getNumeroLinhas());
		newCampoExame.setVersion(campoExame.getVersion());
		
		return newCampoExame;
	}

	@Override
	public CampoExame cloneFromFilter(CampoExameFilter filter) {
		return null;
	}

}
