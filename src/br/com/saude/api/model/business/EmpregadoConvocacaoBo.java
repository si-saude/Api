package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.EmpregadoConvocacaoBuilder;
import br.com.saude.api.model.creation.builder.example.EmpregadoConvocacaoExampleBuilder;
import br.com.saude.api.model.entity.filter.EmpregadoConvocacaoFilter;
import br.com.saude.api.model.entity.po.EmpregadoConvocacao;
import br.com.saude.api.model.persistence.EmpregadoConvocacaoDao;

public class EmpregadoConvocacaoBo 
	extends GenericBo<EmpregadoConvocacao, EmpregadoConvocacaoFilter, 
		EmpregadoConvocacaoDao, EmpregadoConvocacaoBuilder, EmpregadoConvocacaoExampleBuilder> {

	private static EmpregadoConvocacaoBo instance;
	
	private EmpregadoConvocacaoBo() {
		super();
	}
	
	public static EmpregadoConvocacaoBo getInstance() {
		if(instance == null)
			instance = new EmpregadoConvocacaoBo();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoad = builder -> {
			return builder.loadEmpregado().loadConvocacao();
		};
		
		this.functionLoadAll = builder -> {
			return this.functionLoad.apply(builder).loadExames().loadResultadoExames();
		};
	}
	
	@Override
	public PagedList<EmpregadoConvocacao> getList(EmpregadoConvocacaoFilter filter) throws Exception {
		return super.getList(getDao().getListFunctionLoad(getExampleBuilder(filter).example()), 
				this.functionLoad);
	}

	@Override
	public EmpregadoConvocacao getById(Object id) throws Exception {
		return super.getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}
	
	@Override
	public EmpregadoConvocacao save(EmpregadoConvocacao eC) throws Exception {
		eC.getEmpregadoConvocacaoExames().forEach(e-> e.setEmpregadoConvocacao(eC));
		eC.getResultadoExames().forEach(rE -> { 
			rE.setEmpregadoConvocacao(eC);
			rE.getItemResultadoExames().forEach(iRE -> iRE.setResultadoExame(rE));
		});
		return super.save(eC);
	}
}
