package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.GenericReportBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.CatBuilder;
import br.com.saude.api.model.creation.builder.example.CatExampleBuilder;
import br.com.saude.api.model.entity.dto.CatDto;
import br.com.saude.api.model.entity.filter.CatFilter;
import br.com.saude.api.model.entity.po.Cat;
import br.com.saude.api.model.persistence.CatDao;
import br.com.saude.api.model.persistence.report.CatReport;

public class CatBo extends GenericBo<Cat, CatFilter, CatDao, CatBuilder, CatExampleBuilder> 
	implements GenericReportBo<CatDto> {

	private static CatBo instance;

	private CatBo() {
		super();
	}

	public static CatBo getInstance() {
		if (instance == null)
			instance = new CatBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoad = builder -> {
			return builder.loadEmpregado();
		};
		
		this.functionLoadAll = builder -> {
			return this.functionLoad.apply(builder).loadDiagnostico().loadEmpregado().loadEmpresa().
					loadGerencia().loadAgenteCausador().loadParteCorpoAtingida().loadNaturezaLesao();
		};
	}

	@Override
	public PagedList<Cat> getList(CatFilter filter) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getList(getDao().getListFunctionLoad(getExampleBuilder(filter).example()), this.functionLoad);
	}

	@Override
	public Cat getById(Object id) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}

	public List<CatDto> getCats() throws Exception{
		return CatReport.getInstance().getCats();
	}
}
