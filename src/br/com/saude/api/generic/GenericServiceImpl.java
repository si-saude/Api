package br.com.saude.api.generic;

import java.lang.reflect.InvocationTargetException;

import javax.ws.rs.core.Response;

public abstract class GenericServiceImpl<	T, 
										F extends GenericFilter,
										BO extends GenericBo<T, F, ?, ?, ?>>{
	
	protected abstract BO getBo();
	
	public Response getListGeneric(F filter) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return Response.ok(getBo().getList(filter).getGenericPagedList()).build();
	}
	
	public Response getSelectListGeneric(F filter) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return Response.ok(getBo().getSelectList(filter)).build();
	}
	
	public Response getGeneric(String id) throws Exception{
		return Response.ok(getBo().getById(new Long(id))).build();
	}
	
	public Response deleteGeneric(Object id) {
		try {
			getBo().delete(id);
			return Response.ok("Removido com sucesso.").build();
		}catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
