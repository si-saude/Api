package br.com.saude.api.generic;

import java.lang.reflect.InvocationTargetException;

import javax.ws.rs.core.Response;

public interface GenericService <T, F extends GenericFilter> {

	public Response getList(F filter) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception;
	public Response getSelectList(F filter) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception;
	public Response get(String id) throws Exception;
	public Response delete(Object id);
	public Response save(T entity) throws Exception;
}
