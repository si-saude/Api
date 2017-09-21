package br.com.saude.api.model.persistence;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.example.ProfissionalExampleBuilder;
import br.com.saude.api.model.entity.po.Endereco;
import br.com.saude.api.model.entity.po.Profissional;
import br.com.saude.api.model.entity.po.Telefone;

public class ProfissionalDao extends GenericDao<Profissional> {

	private static ProfissionalDao instance;
	
	private ProfissionalDao() {
		super();
	}
	
	public static ProfissionalDao getInstance() {
		if(instance == null)
			instance = new ProfissionalDao();
		return instance;
	}
	
	public Profissional getByIdLoadAll(Object id) throws Exception {
		return this.getById(id, "loadAll");
	}
	
	public PagedList<Profissional> 
		getListLoadEquipeLocalizacao(ProfissionalExampleBuilder exampleBuilder) throws Exception{
		return this.getList(exampleBuilder, "loadEquipeLocalizacao");
	}
	
	@SuppressWarnings("unused")
	private Profissional loadEquipeLocalizacao(Profissional profissional) {
		profissional = loadEquipe(profissional);
		profissional = loadLocalizacao(profissional);
		return profissional;
	}
	
	@SuppressWarnings("unused")
	private Profissional loadAll(Profissional profissional) {
		profissional = loadTelefones(profissional);
		profissional = loadEnderecos(profissional);
		profissional = loadEquipe(profissional);
		profissional = loadLocalizacao(profissional);
		return profissional;
	}
	
	private Profissional loadTelefones(Profissional profissional) {
		if(profissional.getTelefones()!=null)
			Hibernate.initialize(profissional.getTelefones());
		return profissional;
	}
	
	private Profissional loadEnderecos(Profissional profissional) {
		if(profissional.getEnderecos()!=null)
			Hibernate.initialize(profissional.getEnderecos());
		return profissional;
	}
	
	private Profissional loadEquipe(Profissional profissional) {
		if(profissional.getEquipe()!=null)
			Hibernate.initialize(profissional.getEquipe());
		return profissional;
	}
	
	private Profissional loadLocalizacao(Profissional profissional) {
		if(profissional.getLocalizacao()!=null)
			Hibernate.initialize(profissional.getLocalizacao());
		return profissional;
	}
	
	@Override
	public Profissional save(Profissional profissional) throws Exception {
		return super.save(profissional,"deleteList");
	}
	
	@SuppressWarnings({ "deprecation", "unchecked", "unused" })
	private Profissional deleteList(Profissional profissional, Session session) {
		
		List<Endereco> enderecos = (List<Endereco>)session.createCriteria(Endereco.class)
				.add(Restrictions.eq("profissionais",profissional)).list();
		if(profissional.getEnderecos()!=null)
			enderecos.removeIf(x->profissional.getEnderecos().stream().filter(y->y.getId() == x.getId()).count() > 0);
		enderecos.forEach(e-> session.delete(e));
		
		List<Telefone> telefones = (List<Telefone>)session.createCriteria(Telefone.class)
				.add(Restrictions.eq("profissionais",profissional)).list();
		if(profissional.getTelefones()!=null)
			telefones.removeIf(x->profissional.getTelefones().stream().filter(y->y.getId() == x.getId()).count() > 0);
		telefones.forEach(t-> session.delete(t));
		
		return profissional;
	}
}
