package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.ProfissionalVacina;

public class ProfissionalVacinaBuilder extends GenericEntityBuilder<ProfissionalVacina,GenericFilter> {

	public static ProfissionalVacinaBuilder newInstance(ProfissionalVacina profissionalVacina) {
		return new ProfissionalVacinaBuilder(profissionalVacina);
	}
	
	public static ProfissionalVacinaBuilder newInstance(List<ProfissionalVacina> profissionalVacinas) {
		return new ProfissionalVacinaBuilder(profissionalVacinas);
	}
	
	private ProfissionalVacinaBuilder(ProfissionalVacina profissionalVacina) {
		super(profissionalVacina);
	}

	private ProfissionalVacinaBuilder(List<ProfissionalVacina> profissionalVacinas) {
		super(profissionalVacinas);
	}

	@Override
	protected ProfissionalVacina clone(ProfissionalVacina profissionalVacina) {
		ProfissionalVacina newProfissionalVacina = new ProfissionalVacina();
		
		newProfissionalVacina.setId(profissionalVacina.getId());
		newProfissionalVacina.setData(profissionalVacina.getData());
		newProfissionalVacina.setDose(profissionalVacina.getDose());
		newProfissionalVacina.setLaboratorio(profissionalVacina.getLaboratorio());
		newProfissionalVacina.setLote(profissionalVacina.getLote());
		newProfissionalVacina.setProximaDose(profissionalVacina.getProximaDose());
		newProfissionalVacina.setVersion(profissionalVacina.getVersion());
		
		return newProfissionalVacina;
	}
	
	public ProfissionalVacinaBuilder loadVacina() {
		if(this.entity != null) {
			this.newEntity = loadVacina(this.entity,this.newEntity);
		}else {
			for(ProfissionalVacina profissionalVacina:this.entityList) {
				ProfissionalVacina newProfissionalVacina = this.newEntityList.stream()
						.filter(e->e.getId() == profissionalVacina.getId())
						.iterator().next();
				newProfissionalVacina = loadVacina(profissionalVacina,newProfissionalVacina);
			}
		}
		return this;
	}
	
	private ProfissionalVacina loadVacina(ProfissionalVacina origem,ProfissionalVacina destino) {
		if(origem.getVacina()!= null) {
			destino.setVacina(VacinaBuilder.newInstance(origem.getVacina()).getEntity());
		}
		
		return destino;
	}

	@Override
	public ProfissionalVacina cloneFromFilter(GenericFilter filter) {
		return null;
	}
}
