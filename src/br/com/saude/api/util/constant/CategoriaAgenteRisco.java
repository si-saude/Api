package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class CategoriaAgenteRisco extends GenericConstant  {

	private static CategoriaAgenteRisco instance;
	
	private CategoriaAgenteRisco() {
		
	}
	
	public static CategoriaAgenteRisco getInstance() {
		if(instance==null)
			instance = new CategoriaAgenteRisco();
		return instance;
	}
	
	public final String SEM_RISCOS			= "SEM RISCOS";
	public final String BIOLOGICOS			= "BIOLÓGICOS";
	public final String QUIMICOS			= "QUÍMICOS";
	public final String FISICOS				= "FÍSICOS";
	public final String ERGONOMICOS			= "ERGONÔMICOS";
	public final String ACIDENTES			= "ACIDENTES";
}
