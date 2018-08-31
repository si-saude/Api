package br.com.saude.api.generic;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.javatuples.Triplet;

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
	
	@SuppressWarnings("deprecation")
	public static Criterion getCriterionDateFilter(String propertyName, DateFilter dateFilter) {
		if(dateFilter != null && dateFilter.getInicio() != null) {
			switch(dateFilter.getTypeFilter()) {
				case ENTRE:
					dateFilter.getFim().setHours(23);
					dateFilter.getFim().setMinutes(59);
					return Restrictions.between(propertyName, 
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

	public static String getStringMonth(int month) {
		String[] months = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
				"Outubro", "Novembro", "Dezembro" };
		return months[month];
	}

	public static Criteria loopCriterias(Criteria criteria, List<Triplet<String,CriteriaExample,JoinType>> criterias) {
		if(criterias != null)
			for(Triplet<String,CriteriaExample,JoinType> c: criterias) {
				Criteria example = criteria.createCriteria(c.getValue0(),c.getValue0(),c.getValue2());
				for(Criterion criterion : c.getValue1().getCriterions())
					example.add(criterion);
				example.add(c.getValue1().getExample());
				
				example = loopCriterias(example, c.getValue1().getCriterias());
			}
		return criteria;
	}
	
	public static DetachedCriteria loopCriterias(DetachedCriteria criteria, List<Triplet<String,CriteriaExample,JoinType>> criterias) {
		if(criterias != null)
			for(Triplet<String,CriteriaExample,JoinType> c: criterias) {
				DetachedCriteria example = criteria.createCriteria(c.getValue0(),c.getValue0(),c.getValue2());
				for(Criterion criterion : c.getValue1().getCriterions())
					example.add(criterion);
				example.add(c.getValue1().getExample());
				
				example = loopCriterias(example, c.getValue1().getCriterias());
			}
		return criteria;
	}
	
	public static <T> Predicate<T> distinctByKey(Function<T, Object> keyExtractor){
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
