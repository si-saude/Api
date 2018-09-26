package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.EmpresaFilter;
import br.com.saude.api.model.entity.po.Empresa;

public class EmpresaBuilder extends GenericEntityBuilder<Empresa,EmpresaFilter> {
	public static EmpresaBuilder newInstance(Empresa empresa) {
		return new EmpresaBuilder(empresa);
	}
	
	public static EmpresaBuilder newInstance(List<Empresa> empresas) {
		return new EmpresaBuilder(empresas);
	}
	
	private EmpresaBuilder(List<Empresa> empresas) {
		super(empresas);
	}

	private EmpresaBuilder(Empresa empresa) {
		super(empresa);
	}

	@Override
	protected void initializeFunctions() {}

	@Override
	protected Empresa clone(Empresa empresa) {
		Empresa cloneEmpresa = new Empresa();
		
		cloneEmpresa.setId(empresa.getId());
		cloneEmpresa.setVersion(empresa.getVersion());
		cloneEmpresa.setNome(empresa.getNome());
		
		return cloneEmpresa;
	}

	@Override
	public Empresa cloneFromFilter(EmpresaFilter filter) {
		return null;
	}
	
}
