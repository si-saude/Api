package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.generic.TypeFilter;
import br.com.saude.api.model.creation.builder.entity.FeriadoBuilder;
import br.com.saude.api.model.creation.builder.example.FeriadoExampleBuilder;
import br.com.saude.api.model.entity.filter.FeriadoFilter;
import br.com.saude.api.model.entity.po.Feriado;
import br.com.saude.api.model.persistence.FeriadoDao;

public class FeriadoBo extends GenericBo<Feriado, FeriadoFilter, FeriadoDao, FeriadoBuilder, FeriadoExampleBuilder> {
	private static FeriadoBo instance;

	private FeriadoBo() {
		super();
	}

	@Override
	protected void initializeFunctions() {

	}

	public static FeriadoBo getInstance() {
		if (instance == null)
			instance = new FeriadoBo();
		return instance;
	}

	// RECEBE UMA INSTANCIA DE CALENDAR COM A DATA A SER ADICIONADO OS DIAS VALIDOS
	// E ADICIONA A DATA NO CALENDARIO
	public Calendar getValidDates(Calendar calendar, int days) throws Exception {
		int d = 0;
		while (d <= days) {
			PagedList<Feriado> feriados = configureBasicFilter(calendar);

			if (feriados.getTotal() > 0) {
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				continue;
			}

			if (calendar.get(Calendar.DAY_OF_WEEK) > 1 && calendar.get(Calendar.DAY_OF_WEEK) < 7) {
				d++;
				if(d <= days)
					calendar.add(Calendar.DAY_OF_MONTH, 1);
			}
			else
				calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		return (Calendar) calendar.clone();
	}

	public int getDaysBetweenDates(Calendar dataInicial, Calendar dataFinal) throws Exception {
		int days = 0;
		
		if (dataInicial.after(dataFinal))
			throw new Exception("Data de inicio maior que data do final.");
		
		dataInicial.add(Calendar.DATE, 1);

		while (!dataInicial.after(dataFinal)) {
			PagedList<Feriado> feriados = configureBasicFilter(dataInicial);

			if (dataInicial.get(Calendar.DAY_OF_WEEK) > 1 &&
					dataInicial.get(Calendar.DAY_OF_WEEK) < 7 && feriados.getTotal() == 0) {
				days++;
			}
			dataInicial.add(Calendar.DAY_OF_MONTH, 1);
		}
		return days;
	}

	private PagedList<Feriado> configureBasicFilter(Calendar dataInicial) throws InstantiationException,
			IllegalAccessException, InvocationTargetException, NoSuchMethodException, Exception {
		FeriadoFilter feriadoFilter = new FeriadoFilter();
		feriadoFilter.setPageNumber(1);
		feriadoFilter.setPageSize(1);
		feriadoFilter.setData(new DateFilter());
		feriadoFilter.getData().setTypeFilter(TypeFilter.IGUAL);
		feriadoFilter.getData().setInicio(dataInicial.getTime());

		PagedList<Feriado> feriados = FeriadoBo.getInstance().getList(feriadoFilter);
		return feriados;
	}
}
