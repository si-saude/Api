package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.EmpresaFilter;
import br.com.saude.api.model.entity.po.Empresa;

public class EmpresaBuilder extends GenericEntityBuilder<Empresa,EmpresaFilter> {
	private Function<Map<String,Empresa>,Empresa> loadMunicipio;
	private Function<Map<String,Empresa>,Empresa> loadCnaes;
	
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
	protected void initializeFunctions() {
		this.loadMunicipio = empresas ->{
			if(empresas.get("origem").getMunicipio() != null) {
				empresas.get("destino").setMunicipio(CidadeBuilder.newInstance(empresas.get("origem").getMunicipio()).getEntity());
			}
			return empresas.get("destino");
		};
		
		this.loadCnaes = empresas ->{
			if(empresas.get("origem").getCnaes() != null) {
				empresas.get("destino").setCnaes(CnaeBuilder.newInstance(empresas.get("origem").getCnaes()).getEntityList());
			}
			return empresas.get("destino");
		};
	}

	@Override
	protected Empresa clone(Empresa empresa) {
		Empresa cloneEmpresa = new Empresa();
		
		cloneEmpresa.setId(empresa.getId());
		cloneEmpresa.setVersion(empresa.getVersion());
		cloneEmpresa.setNome(empresa.getNome());
		cloneEmpresa.setCnpj(empresa.getCnpj());
		cloneEmpresa.setCep(empresa.getCep());
		cloneEmpresa.setEndereco(empresa.getEndereco());
		cloneEmpresa.setTelefone(empresa.getTelefone());
		cloneEmpresa.setBairro(empresa.getBairro());
		
		return cloneEmpresa;
	}

	@Override
	public Empresa cloneFromFilter(EmpresaFilter filter) {
		return null;
	}
	
	public EmpresaBuilder loadMunicipio() {
		return (EmpresaBuilder) this.loadProperty(this.loadMunicipio);
	}
	
	public EmpresaBuilder loadCnaes() {
		return (EmpresaBuilder) this.loadProperty(this.loadCnaes);
	}
	
}
