package br.com.saude.api.generic;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class Helper {
	private static StringBuilder stringBuilder;
	
	public static String filterLike(String str) {
		stringBuilder = new StringBuilder("%");
		stringBuilder.append(str);
		stringBuilder.append("%");
		return stringBuilder.toString();
	}
	
	public static Criterion getCriterionDateFilter(String propertyName, DateFilter dateFilter) {
		if(dateFilter != null) {
			switch(dateFilter.getTypeFilter()) {
				case ENTRE: return Restrictions.between(propertyName, 
								dateFilter.getInicio(), 
								dateFilter.getFim());
			case MAIOR_IGUAL:
				case MAIOR: return Restrictions.gt(propertyName, dateFilter.getInicio());
			case MENOR_IGUAL:
				case MENOR: return Restrictions.lt(propertyName, dateFilter.getInicio());
			case IGUAL: return Restrictions.eq(propertyName, dateFilter.getInicio());
			case DIFERENTE: return Restrictions.ne(propertyName, dateFilter.getInicio());
			default:
				return null;
			}
		}
		
		return null;
	}
}
