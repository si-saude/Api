package br.com.saude.api.util.constant;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Funcao {
	
	private static Funcao instance;
	
	private Funcao() {
		
	}
	
	public static Funcao getInstance() {
		if(instance==null)
			instance = new Funcao();
		return instance;
	}
	
	public List<String> getList() throws IllegalArgumentException, IllegalAccessException{
		List<String> list = new ArrayList<String>();
		Field[] fields = this.getClass().getDeclaredFields();
		for(Field field:fields) {
			if(!field.getName().equals("instance"))
				list.add((String)field.get(this));
		}
		return list;
	}
	
	public final String EMPREGADO_LISTAR 				= "EMPREGADO_LISTAR";
	public final String EMPREGADO_ADICIONAR 			= "EMPREGADO_ADICIONAR";
	public final String EMPREGADO_ALTERAR 				= "EMPREGADO_ALTERAR";
	public final String EMPREGADO_REMOVER 				= "EMPREGADO_REMOVER";
}
