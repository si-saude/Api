package br.com.saude.api.model.business;

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
		this.functionLoadAll = builder -> {
			return builder.loadProfissionalCaracterizacao().loadProfissionalClassificacao().
					loadMunicipio().loadAgenteCausador().loadNaturezaLesao().loadParteCorpoAtingida()
					.loadCnae().loadClassificaoGravidade().loadInstalacao().loadDiagnosticoProvavel();
		};
	}

	@Override
	public Cat getById(Object id) throws Exception {
		return getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}
	
	public List<CatDto> getCats() throws Exception{
		return CatReport.getInstance().getCats();
	}

	@Override
	public PagedList<Cat> getList(CatFilter filter) throws Exception {
		return super.getList(filter);
	}
	
}
