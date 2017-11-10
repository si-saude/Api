package br.com.saude.api.model.business.validate;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Empregado;

public class EmpregadoValidator extends GenericValidator<Empregado> {

	
	@Override
	public void validate(Empregado empregado) throws Exception {
		super.validate(empregado);
		
		if(empregado.getPessoa() != null)
			new PessoaValidator().validate(empregado.getPessoa());
		
		if(empregado.getEmpregadoVacinas() != null)
			new EmpregadoVacinaValidator().validate(empregado.getEmpregadoVacinas());
		
		if(empregado.getHistoricoGrupoMonitoramentos() != null)
			new HistoricoGrupoMonitoramentoValidator().validate(empregado.getHistoricoGrupoMonitoramentos());
	}
}
