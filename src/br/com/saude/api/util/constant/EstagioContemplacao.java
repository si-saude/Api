package br.com.saude.api.util.constant;

import br.com.saude.api.generic.GenericConstant;

public class EstagioContemplacao extends GenericConstant {
	
	private static EstagioContemplacao instance;
	
	private EstagioContemplacao() {
		
	}
	
	public static EstagioContemplacao getInstance() {
		if(instance == null)
			instance = new EstagioContemplacao();
		return instance;
	}
	
	public final String PRE_CONTEMPLATIVO 				= "PR�-CONTEMPATIVO";
	public final String CONTEMPLATIVO		= "CONTEMPLATIVO";
	public final String PREPARACAO		= "PREPARA��O";
	public final String ACAO		= "A��O";
	public final String MANUTENCAO		= "MANUTEN��O";
}
