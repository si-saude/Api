package br.com.saude.api.generic;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.saude.api.model.business.FeriadoBo;
import br.com.saude.api.model.entity.filter.FeriadoFilter;
import br.com.saude.api.model.entity.po.Feriado;

public class Helper {
	private static StringBuilder stringBuilder;
	
	public static String filterLike(String str) {
		stringBuilder = new StringBuilder("%");
		stringBuilder.append(str);
		stringBuilder.append("%");
		return stringBuilder.toString();
	}
	
	public static Date getNow() {
		return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
	}
	
	@SuppressWarnings("deprecation")
	public static Date getToday() {
		Date today = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
		today.setHours(0);
		today.setMinutes(0);
		today.setSeconds(0);
		return today;
	}
	
	public static Criterion getCriterionDateFilter(String propertyName, DateFilter dateFilter) {
		if(dateFilter != null && dateFilter.getInicio() != null) {
			switch(dateFilter.getTypeFilter()) {
				case ENTRE: return Restrictions.between(propertyName, 
								dateFilter.getInicio(), 
								dateFilter.getFim());
			case MAIOR_IGUAL:return Restrictions.ge(propertyName, dateFilter.getInicio());
			case MAIOR:return Restrictions.gt(propertyName, dateFilter.getInicio());
			case MENOR_IGUAL:return Restrictions.le(propertyName, dateFilter.getInicio());
			case MENOR: return Restrictions.lt(propertyName, dateFilter.getInicio());
			case IGUAL: return Restrictions.eq(propertyName, dateFilter.getInicio());
			case DIFERENTE: return Restrictions.ne(propertyName, dateFilter.getInicio());
			default:
				return null;
			}
		}
		
		return null;
	}
	
	public static <T> Predicate<T> distinctByKey(Function<T, Object> keyExtractor){
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
	
	//RECEBE UMA INSTANCIA DE CALENDAR COM A DATA A SER ADICIONADO OS DIAS VALIDOS
	public static void getValidDates(Calendar calendar, int days) throws Exception {
		int d = 0;
		while ( d != days ) {
			FeriadoFilter feriadoFilter = new FeriadoFilter();
			feriadoFilter.setData(new DateFilter());
			feriadoFilter.getData().setInicio(calendar.getTime());
			feriadoFilter.getData().setTypeFilter(TypeFilter.IGUAL);
			ArrayList<Feriado> feriados = (ArrayList<Feriado>) FeriadoBo.getInstance().getList(feriadoFilter).getList();
			
			if ( feriados.size() > 0 ) {
				calendar.setTime(feriados.get(0).getData());
				
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				continue;
			}
		
			if( calendar.get(Calendar.DAY_OF_WEEK) <= 5 )
		        d++;
			calendar.add( Calendar.DAY_OF_MONTH, 1 );
		}
	}
	
	public static String getStringMonth(int month) {
		String[] months = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", 
		                      "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
		return months[month];
	}
	
}
