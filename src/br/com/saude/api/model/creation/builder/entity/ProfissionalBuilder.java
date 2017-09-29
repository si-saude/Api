package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.ProfissionalFilter;
import br.com.saude.api.model.entity.po.Profissional;

public class ProfissionalBuilder extends GenericEntityBuilder<Profissional,ProfissionalFilter> {

	public static ProfissionalBuilder newInstance(Profissional profissional) {
		return new ProfissionalBuilder(profissional);
	}
	
	public static ProfissionalBuilder newInstance(List<Profissional> profissionais) {
		return new ProfissionalBuilder(profissionais);
	}
	
	private ProfissionalBuilder(Profissional profissional) {
		super(profissional);
	}

	private ProfissionalBuilder(List<Profissional> profissionais) {
		super(profissionais);
	}

	@Override
	protected Profissional clone(Profissional profissional) {
		Profissional newProfissional = new Profissional();
		
		newProfissional.setId(profissional.getId());
		newProfissional.setChave(profissional.getChave());
		newProfissional.setDataNascimento(profissional.getDataNascimento());
		newProfissional.setMatricula(profissional.getMatricula());
		newProfissional.setMi(profissional.getMi());
		newProfissional.setNome(profissional.getNome());
		newProfissional.setRamal(profissional.getRamal());
		newProfissional.setVersion(profissional.getVersion());
		
		return newProfissional;
	}
	
	public ProfissionalBuilder loadCurriculo() {
		if(this.entity != null) {
			this.newEntity = loadCurriculo(this.entity,this.newEntity);
		}else {
			for(Profissional profissional:this.entityList) {
				Profissional newProfissional = this.newEntityList.stream()
						.filter(e->e.getId() == profissional.getId())
						.iterator().next();
				newProfissional = loadCurriculo(profissional,newProfissional);
			}
		}
		return this;
	}
	
	private Profissional loadCurriculo(Profissional origem,Profissional destino) {
		if(origem.getCurriculo()!= null) {
			destino.setCurriculo(CurriculoBuilder.newInstance(origem.getCurriculo()).getEntity());
		}
		
		return destino;
	}
	
	public ProfissionalBuilder loadFuncao() {
		if(this.entity != null) {
			this.newEntity = loadFuncao(this.entity,this.newEntity);
		}else {
			for(Profissional profissional:this.entityList) {
				Profissional newProfissional = this.newEntityList.stream()
						.filter(e->e.getId() == profissional.getId())
						.iterator().next();
				newProfissional = loadFuncao(profissional,newProfissional);
			}
		}
		return this;
	}
	
	private Profissional loadFuncao(Profissional origem,Profissional destino) {
		if(origem.getFuncao()!= null) {
			destino.setFuncao(FuncaoBuilder.newInstance(origem.getFuncao()).getEntity());
		}
		
		return destino;
	}
	
	public ProfissionalBuilder loadEquipe() {
		if(this.entity != null) {
			this.newEntity = loadEquipe(this.entity,this.newEntity);
		}else {
			for(Profissional profissional:this.entityList) {
				Profissional newProfissional = this.newEntityList.stream()
						.filter(e->e.getId() == profissional.getId())
						.iterator().next();
				newProfissional = loadEquipe(profissional,newProfissional);
			}
		}
		return this;
	}
	
	private Profissional loadEquipe(Profissional origem,Profissional destino) {
		if(origem.getEquipe()!= null) {
			destino.setEquipe(EquipeBuilder.newInstance(origem.getEquipe()).getEntity());
		}
		
		return destino;
	}
	
	public ProfissionalBuilder loadLocalizacao() {
		if(this.entity != null) {
			this.newEntity = loadLocalizacao(this.entity,this.newEntity);
		}else {
			for(Profissional profissional:this.entityList) {
				Profissional newProfissional = this.newEntityList.stream()
						.filter(e->e.getId() == profissional.getId())
						.iterator().next();
				newProfissional = loadLocalizacao(profissional,newProfissional);
			}
		}
		return this;
	}
	
	private Profissional loadLocalizacao(Profissional origem,Profissional destino) {
		if(origem.getLocalizacao()!= null) {
			destino.setLocalizacao(LocalizacaoBuilder.newInstance(origem.getLocalizacao()).getEntity());
		}
		
		return destino;
	}
	
	public ProfissionalBuilder loadEndereco() {
		if(this.entity != null) {
			this.entity = loadEndereco(this.entity,this.newEntity);
		}else {
			for(Profissional profissional:this.entityList) {
				Profissional newProfissional = this.newEntityList.stream()
						.filter(e->e.getId() == profissional.getId())
						.iterator().next();
				newProfissional = loadEndereco(profissional,newProfissional);
			}
		}
		return this;
	}
	
	private Profissional loadEndereco(Profissional origem,Profissional destino) {
		if(origem.getEndereco() != null) {
			destino.setEndereco(EnderecoBuilder.newInstance(origem.getEndereco()).getEntity());
		}
		return destino;
	}
	
	public ProfissionalBuilder loadTelefones() {
		if(this.entity != null) {
			this.entity = loadTelefones(this.entity,this.newEntity);
		}else {
			for(Profissional profissional:this.entityList) {
				Profissional newProfissional = this.newEntityList.stream()
						.filter(e->e.getId() == profissional.getId())
						.iterator().next();
				newProfissional = loadTelefones(profissional,newProfissional);
			}
		}
		return this;
	}
	
	private Profissional loadTelefones(Profissional origem,Profissional destino) {
		if(origem.getTelefones() != null) {
			destino.setTelefones(TelefoneBuilder.newInstance(origem.getTelefones()).getEntityList());
		}
		return destino;
	}

	@Override
	public Profissional cloneFromFilter(ProfissionalFilter filter) {
		return null;
	}
}
