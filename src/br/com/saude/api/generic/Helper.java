package br.com.saude.api.generic;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

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
		if (dateFilter != null && dateFilter.getInicio() != null) {
			switch (dateFilter.getTypeFilter()) {
			case ENTRE:
				return Restrictions.between(propertyName, dateFilter.getInicio(), dateFilter.getFim());
			case MAIOR_IGUAL:
				return Restrictions.ge(propertyName, dateFilter.getInicio());
			case MAIOR:
				return Restrictions.gt(propertyName, dateFilter.getInicio());
			case MENOR_IGUAL:
				return Restrictions.le(propertyName, dateFilter.getInicio());
			case MENOR:
				return Restrictions.lt(propertyName, dateFilter.getInicio());
			case IGUAL:
				return Restrictions.eq(propertyName, dateFilter.getInicio());
			case DIFERENTE:
				return Restrictions.ne(propertyName, dateFilter.getInicio());
			default:
				return null;
			}
		}

		return null;
	}

	public static <T> Predicate<T> distinctByKey(Function<T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	public static String getStringMonth(int month) {
		String[] months = { "Janeiro", "Fevereiro", "Mar�o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
				"Outubro", "Novembro", "Dezembro" };
		return months[month];
	}

}
