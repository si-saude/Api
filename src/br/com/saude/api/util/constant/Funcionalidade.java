package br.com.saude.api.util.constant;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Funcionalidade {
	
	private static Funcionalidade instance;
	
	private Funcionalidade() {
		
	}
	
	public static Funcionalidade getInstance() {
		if(instance==null)
			instance = new Funcionalidade();
		return instance;
	}
	
	public Map<String,String> getList() throws IllegalArgumentException, IllegalAccessException{
		Map<String,String> map = new HashMap<String,String>();
		Field[] fields = this.getClass().getDeclaredFields();
		for(Field field:fields) {
			if(!field.getName().equals("instance"))
				map.put("value", (String)field.get(this));
		}
		return map;
	}
	
	public final String EMPREGADO_LISTAR 				= "EMPREGADO_LISTAR";
	public final String EMPREGADO_ADICIONAR 			= "EMPREGADO_ADICIONAR";
	public final String EMPREGADO_ALTERAR 				= "EMPREGADO_ALTERAR";
	public final String EMPREGADO_REMOVER 				= "EMPREGADO_REMOVER";
}
