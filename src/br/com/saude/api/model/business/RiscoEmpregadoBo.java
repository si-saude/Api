package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.RiscoEmpregadoBuilder;
import br.com.saude.api.model.creation.builder.example.RiscoEmpregadoExampleBuilder;
import br.com.saude.api.model.entity.filter.RiscoEmpregadoFilter;
import br.com.saude.api.model.entity.po.Profissional;
import br.com.saude.api.model.entity.po.RiscoEmpregado;
import br.com.saude.api.model.entity.po.RiscoPotencial;
import br.com.saude.api.model.entity.po.Triagem;
import br.com.saude.api.model.persistence.RiscoEmpregadoDao;
import br.com.saude.api.util.constant.StatusAcao;
import br.com.saude.api.util.constant.StatusRiscoEmpregado;
import br.com.saude.api.util.constant.StatusRiscoPotencial;

public class RiscoEmpregadoBo extends 
	GenericBo<RiscoEmpregado, RiscoEmpregadoFilter, RiscoEmpregadoDao, RiscoEmpregadoBuilder, RiscoEmpregadoExampleBuilder> {
	
	private static RiscoEmpregadoBo instance;

	private RiscoEmpregadoBo() {
		super();
	}

	public static RiscoEmpregadoBo getInstance() {
		if (instance == null)
			instance = new RiscoEmpregadoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoad = builder -> {
			return builder.loadRiscoPotencial().loadTriagens();
		};
		
		this.functionLoadAll = builder -> {
			return builder.loadRiscoPotencial().loadTriagensAll();
		};
	}

	@Override
	public PagedList<RiscoEmpregado> getList(RiscoEmpregadoFilter filter)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, Exception {
		return super.getList(filter, this.functionLoad);
	}
	
	private boolean bloquerReavaliacao(long riscoPotencialId, long equipeId) throws Exception {
		RiscoPotencial riscoPotencial = RiscoPotencialBo.getInstance().getByIdLoadAcoes(new Integer((int) riscoPotencialId));
		
		if(riscoPotencial.getEquipeResponsavel().getId() == equipeId) {
			if( riscoPotencial.getRiscoEmpregados().stream().filter(r->{
					return r.getEquipe().getId() != equipeId && r.isAtivo() &&
							r.getTriagens().stream().filter(t->{
								if(t.getPrazo() != null && (t.getAcoes() == null || t.getAcoes().size() == 0))
									return true;
								return t.getAcoes().stream().filter(a->a.getStatus()
										.equals(StatusAcao.getInstance().ENCERRADA)).count() > 0;
							}).count() > 0;
					}).count() == 0)
				return false;
			else
				throw new Exception("Não é possível prosseguir pois as demais reavaliações não foram realizadas.");
		}
		
		return false;
	}
	
	private boolean existeReavaliacao(long riscoPotencialId, long equipeId) throws Exception {
		RiscoPotencial riscoPotencial = RiscoPotencialBo.getInstance().getByIdLoadAcoes(new Integer((int) riscoPotencialId));
		for(RiscoEmpregado r : riscoPotencial.getRiscoEmpregados()){
			if(r.getEquipe().getId() == equipeId && r.isAtivo()) {
				for(Triagem t : r.getTriagens()) {
					if (t.getPrazo() != null && (t.getAcoes() == null || t.getAcoes().size() == 0))
						throw new Exception("Não é possível reavaliar pois não há ações.");
					else if (t.getAcoes().stream().filter(a -> a.getStatus()
								.equals(StatusAcao.getInstance().REAVALIADA)).count() > 0)
						return true;
				}
			}
		}
		return false;
	}

	public PagedList<RiscoEmpregado> getListToCopy(RiscoEmpregadoFilter filter)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, Exception {
		
		//VERIFICAR SE O RISCO POTENCIAL DEVE SER FINALIZADO
		if(bloquerReavaliacao(filter.getRiscoPotencial().getId(), filter.getEquipe().getId())) {
			throw new Exception("Não é possível finalizar a sistematização, pois as demais ações não foram reavaliadas.");
		}
		
		filter.setProfissional(null);
		
		PagedList<RiscoEmpregado> pagedRiscos = super.getList(filter, this.functionLoad);
		RiscoEmpregado risco = new RiscoEmpregado();
		risco = (RiscoEmpregado) pagedRiscos.getList().get(0);
		
		List<Triagem> triagens = new ArrayList<Triagem>();
		risco.getTriagens().forEach(t -> {
			Triagem triagem = new Triagem();
			triagem.setIndicadorSast(t.getIndicadorSast());
			triagem.setIndice(t.getIndice());
			triagens.add(triagem);
		});
		
		risco.setData(Helper.getNow());
		risco.setAtivo(false);
		risco.setTriagens(triagens);
		risco.setStatus(StatusRiscoEmpregado.getInstance().REAVALIADO);
		risco.setId(0);
		risco.setValor(0);
		
		pagedRiscos.getList().set(0, risco);
		return pagedRiscos;
	}

	@Override
	public RiscoEmpregado getById(Object id) throws Exception {
		return super.getById(id, this.functionLoad);
	}
	
	public RiscoEmpregado getByIdAll(Object id) throws Exception {
		return super.getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}
	
	@Override
	public RiscoEmpregado save(RiscoEmpregado riscoEmpregado) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		
		riscoEmpregado.getTriagens().forEach(t -> t.setRiscoEmpregado(riscoEmpregado));
		
		return super.save(riscoEmpregado);
	}

	public RiscoEmpregado saveReavaliacao(RiscoEmpregado riscoEmpregado) throws Exception {
		riscoEmpregado = gerarRisco(riscoEmpregado);
		
		RiscoPotencial riscoPotencial = RiscoPotencialBo.getInstance().getByIdLoadAcoes(riscoEmpregado
				.getRiscoPotencial().getId());
		
		if((!bloquerReavaliacao(riscoEmpregado.getRiscoPotencial().getId(), riscoEmpregado.getEquipe().getId())) &&
				riscoPotencial.getEquipeResponsavel().getId() == riscoEmpregado.getEquipe().getId() ) {
			riscoPotencial.setStatus(StatusRiscoPotencial.getInstance().ABERTO);
		}
		
		if(existeReavaliacao(riscoEmpregado.getRiscoPotencial().getId(), riscoEmpregado.getEquipe().getId())) {
			throw new Exception("Não é possível prosseguir pois esta ação já foi reavaliada.");
		}
		
		//ALTERAR O STATUS DAS AÇÕES DAS TRIAGENS 
		RiscoEmpregado aux = riscoEmpregado;
		riscoPotencial.getRiscoEmpregados().stream().filter(r->r.getEquipe().getId() == aux
				.getEquipe().getId()).forEach(r->{
					r.getTriagens().forEach(t->{
						if ( t.getAcoes() == null || t.getAcoes().size() == 0 ) return;
						t.getAcoes().forEach(a->{
							a.setStatus(StatusAcao.getInstance().REAVALIADA);
						});
					});
				});
		
		riscoPotencial.getRiscoEmpregados().add(riscoEmpregado);
		riscoEmpregado.setRiscoPotencial(riscoPotencial);
		RiscoPotencialBo.getInstance().simpleSave(riscoPotencial);
		
		return riscoEmpregado;
	}
	
	private RiscoEmpregado gerarRisco(RiscoEmpregado riscoEmpregado) {
		
		if(riscoEmpregado.getTriagens() != null && riscoEmpregado.getTriagens().size() > 0) {			
			List<Triagem> triagens = riscoEmpregado.getTriagens().stream().filter(t->t.getIndice() >= 0)
					.collect(Collectors.toList());
			
			//CARREGAR OS INDICADORES
			triagens.forEach(t->{
				try {
					t.setIndicadorSast(
							IndicadorSastBo.getInstance().getById(t.getIndicadorSast().getId()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			
			double r0 = 0.95 - (triagens.stream().flatMapToInt(t-> IntStream.of(t.getIndice())).average().getAsDouble()/4.3);
			Profissional profissional = null;
			
			try {
				 profissional = ProfissionalBo.getInstance().getById(riscoEmpregado.getProfissional().getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			double r01 = (Math.log10(profissional.getEquipe().getPrioridadeSast() + 1) /
						 	(profissional.getEquipe().getPrioridadeSast() + 5)) / 
					(profissional.getEquipe()
								.getPrioridadeSast() + triagens.size());
			
			double r1 = r0 + r01;
			
			if (triagens.stream().filter(t->t.getIndice() <= t.getIndicadorSast().getCritico()).count() > 0)
				r1 = 0.95;
			
			riscoEmpregado.setValor(r1);
			
			riscoEmpregado.getTriagens().forEach(t->t.setRiscoEmpregado(riscoEmpregado));
		}
		
		return riscoEmpregado;
	}

}