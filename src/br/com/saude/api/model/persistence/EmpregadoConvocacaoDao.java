package br.com.saude.api.model.persistence;

import java.util.function.Function;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Convocacao;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.EmpregadoConvocacao;
import br.com.saude.api.model.entity.po.Exame;

public class EmpregadoConvocacaoDao extends GenericDao<EmpregadoConvocacao> {

	private static EmpregadoConvocacaoDao instance;
	
	private EmpregadoConvocacaoDao() {
		super();
	}
	
	public static EmpregadoConvocacaoDao getInstance() {
		if(instance == null)
			instance = new EmpregadoConvocacaoDao();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoad = eC -> {
			eC = loadConvocacao(eC);
			eC = loadEmpregado(eC);
			return eC;
		};
		
		this.functionLoadAll = eC -> {
			eC = this.functionLoad.apply(eC);
			eC = loadEmpregadoConvocacaoExames(eC);
			eC = loadResultadoExames(eC);
			return eC;
		};
		
		this.functionBeforeSave = pair -> {
			EmpregadoConvocacao eC = pair.getValue0();
			Session session = pair.getValue1();
			
			eC.getEmpregadoConvocacaoExames().forEach(ex->{
				ex.setExame(session.get(Exame.class, ex.getExame().getId()));
			});
			
			return eC;
		};
	}
	
	@Override
	protected PagedList<EmpregadoConvocacao> getList(GenericExampleBuilder<?, ?> exampleBuilder,
			Function<EmpregadoConvocacao, EmpregadoConvocacao> function) throws Exception { 
		return super.getList(exampleBuilder, this.functionLoad);
	}
	
	private EmpregadoConvocacao loadConvocacao(EmpregadoConvocacao eC) {
		if(eC.getConvocacao() != null) {
			Convocacao convocacao = (Convocacao) Hibernate.unproxy(eC.getConvocacao());
			eC.setConvocacao(convocacao);
		}
		return eC;
	}
	
	private EmpregadoConvocacao loadEmpregado(EmpregadoConvocacao eC) {
		if(eC.getEmpregado() != null) {

			Object empregado = Hibernate.unproxy(eC.getEmpregado());
			eC.setEmpregado(EmpregadoDao.getInstance().getFunctionLoad().apply((Empregado) empregado));
		}
		return eC;
	}
	
	private EmpregadoConvocacao loadEmpregadoConvocacaoExames(EmpregadoConvocacao eC) {
		if(eC.getEmpregadoConvocacaoExames() != null)
			Hibernate.initialize(eC.getEmpregadoConvocacaoExames());
		
		return eC;
	}
	
	private EmpregadoConvocacao loadResultadoExames(EmpregadoConvocacao eC) {
		if (eC.getResultadoExames() != null) {
			Hibernate.initialize(eC.getResultadoExames());
			eC.getResultadoExames().forEach(r -> {
				if ( r.getItemResultadoExames() != null )
					Hibernate.initialize(r.getItemResultadoExames());
			});
		}
		return eC;
	}
	
	public EmpregadoConvocacao getByIdLoadAll(Object id) throws Exception {
		return super.getById(id, this.functionLoadAll);
	}
	
	public PagedList<EmpregadoConvocacao> getListFunctionLoadAll(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder, this.functionLoadAll);
	}
	
	protected Function<EmpregadoConvocacao,EmpregadoConvocacao> getFunctionLoad(){
		return this.functionLoad;
	}
}
