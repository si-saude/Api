package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.PossivelDanoSaudeFilter;
import br.com.saude.api.model.entity.po.PossivelDanoSaude;

public class PossivelDanoSaudeBuilder extends GenericEntityBuilder<PossivelDanoSaude, PossivelDanoSaudeFilter> {

	public static PossivelDanoSaudeBuilder newInstance(PossivelDanoSaude possivelDanoSaude) {
		return new PossivelDanoSaudeBuilder(possivelDanoSaude);
	}
	
	public static PossivelDanoSaudeBuilder newInstance(List<PossivelDanoSaude> possivelDanoSaudes) {
		return new PossivelDanoSaudeBuilder(possivelDanoSaudes);
	}
	
	private PossivelDanoSaudeBuilder(List<PossivelDanoSaude> possivelDanoSaudes) {
		super(possivelDanoSaudes);
	}

	private PossivelDanoSaudeBuilder(PossivelDanoSaude possivelDanoSaude) {
		super(possivelDanoSaude);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected PossivelDanoSaude clone(PossivelDanoSaude possivelDanoSaude) {
		PossivelDanoSaude newPossivelDanoSaude = new PossivelDanoSaude();
		
		newPossivelDanoSaude.setId(possivelDanoSaude.getId());
		newPossivelDanoSaude.setDescricao(possivelDanoSaude.getDescricao());
		newPossivelDanoSaude.setVersion(possivelDanoSaude.getVersion());
		
		return newPossivelDanoSaude;
	}

	@Override
	public PossivelDanoSaude cloneFromFilter(PossivelDanoSaudeFilter filter) {
		return null;
	}
}
