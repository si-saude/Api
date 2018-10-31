package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class TipoAlimento extends GenericConstant  {
	private static TipoAlimento instance;
	
	private TipoAlimento() {
		
	}
	
	public static TipoAlimento getInstance() {
		if(instance == null)
			instance = new TipoAlimento();
		return instance;
	}
	
	public final String ACUCARADOS 				= "AÇUCARADOS";
	public final String BEBIDAS 				= "BEBIDAS";
	public final String CARNES 					= "CARNES";
	public final String CEREAIS_E_DERIVADOS 				= "CEREAIS E DERIVADOS";
	public final String FRUTAS				= "FRUTAS";
	public final String GORDURAS_E_OLEOS 					= "GORDURAS E ÓLEOS";
	public final String INDUSTRIALIZADOS 				= "INDUSTRIALIZADOS";
	public final String LATICINEOS 				= "LATICÍNEOS";
	public final String LEGUMINOSAS 					= "LEGUMINOSAS";
	public final String MISCELANEAS 				= "MISCELÂNEAS";
	public final String OVOS				= "OVOS";
	public final String PESCADOS_E_FRUTOS_DO_MAR 					= "PESCADOS E FRUTOS DO MAR";
	public final String PREPARADOS 				= "PREPARADOS";
	public final String SEMENTES				= "SEMENTES";
	public final String VERDURAS_E_HORTALICAS 					= "VERDURAS E HORTALIÇAS";
}
