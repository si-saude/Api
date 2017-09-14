package br.com.saude.api.model.creation.builder.entity;

import java.util.ArrayList;
import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.Exame;

public class ExameBuilder extends GenericEntityBuilder<Exame, ExameBuilder> {
	
	public ExameBuilder(Exame exame) {
		super(exame);
	}
	
	public ExameBuilder(List<Exame> exames) {
		super(exames);
	}

	@Override
	protected Exame clone(Exame exame) {
		Exame newExame = new Exame();
		
		newExame.setId(exame.getId());
		newExame.setVersion(exame.getVersion());
		newExame.setCodigo(exame.getCodigo());
		newExame.setDescricao(exame.getDescricao());

		return newExame;
	}

	@Override
	protected List<Exame> clone(List<Exame> exames){
		List<Exame> newExames = new ArrayList<Exame>();
		
		for(Exame exame : exames)
			newExames.add(clone(exame));
			
		return newExames;
	}
	
	public ExameBuilder loadEmpregado() {
		if(this.entity.getEmpregado() != null) {
			this.newEntity.setEmpregado(new Empregado());
			this.newEntity.getEmpregado().setId(this.entity.getEmpregado().getId());
			this.newEntity.getEmpregado().setCpf(this.entity.getEmpregado().getCpf());
			this.newEntity.getEmpregado().setNome(this.entity.getEmpregado().getNome());
			this.newEntity.getEmpregado().setDataNascimento(this.entity.getEmpregado().getDataNascimento());			
		}
		return this;
	}
}
