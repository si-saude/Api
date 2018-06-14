package br.com.saude.api.generic;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public interface GenericReportService<T, B extends GenericReportBo<T>> {

	@SuppressWarnings("unchecked")
	default B getReportBo() throws Exception {
		return (B) ((Class<B>) ((ParameterizedType)getClass().getGenericInterfaces()[0] )
				.getActualTypeArguments()[1]).getDeclaredMethod("getInstance", new Class[] {})
				.invoke(null, new Object[]{});
	}

	default String exportXLSXFile(List<T> array) throws Exception {
		return this.getReportBo().createFileToExport(array);
	}

}
