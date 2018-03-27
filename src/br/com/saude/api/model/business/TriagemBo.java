package br.com.saude.api.model.business;

import java.util.List;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.TriagemBuilder;
import br.com.saude.api.model.creation.builder.example.TriagemExampleBuilder;
import br.com.saude.api.model.entity.filter.TriagemFilter;
import br.com.saude.api.model.entity.po.RiscoEmpregado;
import br.com.saude.api.model.entity.po.Triagem;
import br.com.saude.api.model.persistence.TriagemDao;

public class TriagemBo extends GenericBo<Triagem, TriagemFilter, TriagemDao, TriagemBuilder, 
	TriagemExampleBuilder> {

	private static TriagemBo instance;
	
	private TriagemBo() {
		super();
	}
	
	public static TriagemBo getInstance() {
		if(instance == null)
			instance = new TriagemBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoad = builder -> {
			return builder.loadIndicadorEquipe();
		};
		
		this.functionLoadAll = builder -> {
			return builder.loadIndicadorEquipe().loadRiscoEmpregado();
		};
	}
	
	@Override
	public PagedList<Triagem> getList(TriagemFilter filter) throws Exception {
		return super.getList(filter,this.functionLoadAll);
	}

	public void saveListTriagem(List<Triagem> triagens) throws Exception {
		triagens.forEach(tr -> {
			Triagem t = tr;
			RiscoEmpregado riscoEmpregado = null;
			try {
				riscoEmpregado = RiscoEmpregadoBo.getInstance().getById(t.getRiscoEmpregado().getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			int indexTriagem = 0;
			for ( int i = 0; i < riscoEmpregado.getTriagens().size(); i++ ) {
				if ( riscoEmpregado.getTriagens().get(i).getId() == t.getId() ) {
					indexTriagem = i;
					break;
				}
			}
			riscoEmpregado.getTriagens().set(indexTriagem, t);
			
			try {
				RiscoEmpregadoBo.getInstance().save(riscoEmpregado);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
 
	}
	
	
}
