package br.com.saude.api.model.business;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.IndicadorSastBuilder;
import br.com.saude.api.model.creation.builder.example.IndicadorSastExampleBuilder;
import br.com.saude.api.model.entity.filter.IndicadorSastFilter;
import br.com.saude.api.model.entity.po.IndicadorSast;
import br.com.saude.api.model.persistence.IndicadorSastDao;

public class IndicadorSastBo extends 
	GenericBo<IndicadorSast, IndicadorSastFilter, IndicadorSastDao, IndicadorSastBuilder, IndicadorSastExampleBuilder>{

	private static IndicadorSastBo instance;

	private IndicadorSastBo() {
		super();
	}
	
	public static IndicadorSastBo getInstance() {
		if(instance == null)
			instance = new IndicadorSastBo();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadEquipe().loadIndicadorAssociadoSasts();
		};
	}

	@Override
	public IndicadorSast getById(Object id) throws Exception {
		return getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}

	@Override
	public IndicadorSast save(IndicadorSast indicadorSast) throws Exception {
		indicadorSast.getIndicadorAssociadoSasts().forEach(iAS -> iAS.setIndicadorSast(indicadorSast));
		
		IndicadorSastFilter indicadorSastFilter = new IndicadorSastFilter();
		BooleanFilter booleanFilter = new BooleanFilter();
		booleanFilter.setValue(2);
		indicadorSastFilter.setInativo(booleanFilter);
		indicadorSastFilter.setCodigo(indicadorSast.getCodigo());
		indicadorSastFilter.setPageSize(Integer.MAX_VALUE);
		PagedList<IndicadorSast> indicadorSasts = this.getList(indicadorSastFilter);
		//se existir um indicador sast ativo com mesmo codigo e q nao seja o indicador a ser salvo, retorna excessao
		if ( indicadorSasts.getList().stream().filter(iS -> iS.getId() != indicadorSast.getId() ).findFirst().isPresent() ) {
			throw new Exception("Não foi possível salvar o Indicador Sast pois já existe um indicador ativo com o mesmo código.");
		}
		
		return super.save(indicadorSast);
	}
	
	
	
}
