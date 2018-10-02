package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.RiscoEmpregadoBuilder;
import br.com.saude.api.model.creation.builder.example.RiscoEmpregadoExampleBuilder;
import br.com.saude.api.model.entity.filter.RiscoEmpregadoFilter;
import br.com.saude.api.model.entity.po.RiscoEmpregado;
import br.com.saude.api.model.entity.po.RiscoPotencial;
import br.com.saude.api.model.entity.po.Triagem;
import br.com.saude.api.model.persistence.RiscoEmpregadoDao;
import br.com.saude.api.util.constant.StatusAcao;
import br.com.saude.api.util.constant.StatusRiscoEmpregado;
import br.com.saude.api.util.constant.StatusRiscoPotencial;

public class RiscoEmpregadoBo extends 
	GenericBo<RiscoEmpregado, RiscoEmpregadoFilter, RiscoEmpregadoDao, RiscoEmpregadoBuilder, RiscoEmpregadoExampleBuilder> {
	
	private Function<RiscoEmpregadoBuilder,RiscoEmpregadoBuilder> functionLoadAtendimentoAll;
	private Function<RiscoEmpregadoBuilder,RiscoEmpregadoBuilder> functionLoadAcoes;
	
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
		
		this.functionLoadAtendimentoAll = builder -> {
			return builder.loadRiscoPotencial().loadTriagensAtendimentoAll();
		};
		this.functionLoadAcoes = builder -> {
			return builder.loadRiscoPotencial().loadTriagensAll().loadAcoes();
		};
	}

	@Override
	public PagedList<RiscoEmpregado> getList(RiscoEmpregadoFilter filter)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, Exception {
		return super.getList(filter, this.functionLoadAll);
	}
	
	public PagedList<RiscoEmpregado> getListAll(RiscoEmpregadoFilter filter)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, Exception {
		return super.getList(getDao().getListAll(getExampleBuilder(filter).example()), this.functionLoadAtendimentoAll);
	}
	
	private boolean bloquerReavaliacao(long riscoPotencialId, long equipeId) throws Exception {
		RiscoPotencial riscoPotencial = RiscoPotencialBo.getInstance().getByIdLoadAcoes(new Integer((int) riscoPotencialId));
		
		if(riscoPotencial.getEquipeResponsavel().getId() == equipeId) {
			if( riscoPotencial.getRiscoEmpregados().stream().filter(r->{
					return r.getEquipe().getId() != equipeId && r.isAtivo() &&
							r.getTriagens().stream().filter(t->{
								if(t.getEquipeAbordagem() != null && 
										(t.getAcoes() == null || t.getAcoes().size() == 0))
									return true;
								return t.getAcoes().stream().filter(a->!a.getStatus()
										.equals(StatusAcao.getInstance().REAVALIADA)).count() > 0;
							}).count() > 0;
					}).count() == 0)
				return false;
			else
				throw new Exception("Não é possível prosseguir pois as demais reavaliações não foram realizadas.");
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
	public RiscoEmpregado getByIdAcoes(Object id) throws Exception {
		return super.getByEntity(getDao().getListAcoes(id), this.functionLoadAcoes);
	}
	
	@Override
	public RiscoEmpregado save(RiscoEmpregado riscoEmpregado) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {

		if(riscoEmpregado.getRiscoPotencial() == null ||riscoEmpregado.getRiscoPotencial().getId() == 0)
			throw new Exception("Não é possível salvar a ficha de triagem/planejamento sem informar o Risco Potencial.");
		
		riscoEmpregado = gerarRisco(riscoEmpregado);
		super.save(riscoEmpregado);
		
		RiscoPotencial riscoPotencial = RiscoPotencialBo.getInstance().getByIdLoadAcoes(riscoEmpregado.getRiscoPotencial().getId());
		
		riscoPotencial.getRiscoEmpregados().sort(new Comparator<RiscoEmpregado>(){
			public int compare(RiscoEmpregado arg0, RiscoEmpregado arg1) {
				return new Double(arg1.getValorPonderado()).compareTo(new Double(arg0.getValorPonderado()));
			}
		});
		
		riscoPotencial.setEquipeResponsavel(riscoPotencial.getRiscoEmpregados().get(0).getEquipe());
		riscoPotencial.setStatus(StatusRiscoPotencial.getInstance().PLANEJAMENTO);
		
		RiscoPotencialBo.getInstance().save(riscoPotencial);
		
		return riscoEmpregado;
	}

	public RiscoEmpregado saveReavaliacao(RiscoEmpregado riscoEmpregado) throws Exception {
			
		RiscoEmpregado riscoEmpregadoAnterior = RiscoEmpregadoBo.getInstance().getByIdAcoes(riscoEmpregado.getId());
		
		riscoEmpregadoAnterior.setAtivo(false);
		riscoEmpregadoAnterior.setStatus(StatusRiscoEmpregado.getInstance().REALIZADO);
		
		riscoEmpregado.setId(0);
		riscoEmpregado.setVersion(0);
		riscoEmpregado.setData(Helper.getToday());
		riscoEmpregado.setAtivo(true);
		riscoEmpregado.setStatus(StatusRiscoEmpregado.getInstance().REAVALIADO);
		
		
		riscoEmpregado.getTriagens().forEach(x->{
			
			x.setId(0);
			x.setVersion(0);
			x.setDiagnostico(null);
			x.setIntervencao(null);
			x.setEquipeAbordagem(null);
			x.setPrazo(null);
			x.setJustificativa(null);
			x.setAcoes(null);
			
		});
		
		riscoEmpregadoAnterior.getTriagens().forEach(t->{
			t.setRiscoEmpregado(riscoEmpregadoAnterior);
			
			if(t.getAcoes() != null)
				t.getAcoes().forEach(a->{
					a.setTriagem(t);
					
					if(a.getAcompanhamentos() != null)
						a.getAcompanhamentos().forEach(aa->aa.setAcao(a));
				});
		});
		riscoEmpregadoAnterior.setRiscoPotencial(riscoEmpregado.getRiscoPotencial());
		super.save(riscoEmpregadoAnterior);
		
		riscoEmpregado = gerarRisco(riscoEmpregado);
		super.save(riscoEmpregado);
		
		if(riscoEmpregado.getEquipe().getId() == riscoEmpregado.getRiscoPotencial().getEquipeResponsavel().getId()) {
			RiscoPotencial riscoPotencial = RiscoPotencialBo.getInstance().getByIdLoadAcoes(riscoEmpregado.getRiscoPotencial().getId());
			
			riscoPotencial.getRiscoEmpregados().sort(new Comparator<RiscoEmpregado>(){
				public int compare(RiscoEmpregado arg0, RiscoEmpregado arg1) {
					return new Double(arg1.getValorPonderado()).compareTo(new Double(arg0.getValorPonderado()));
				}
			});
			
			riscoPotencial.getRiscoEmpregados().forEach(x->{
				if(x.isAtivo())
					x.setStatus(StatusRiscoEmpregado.getInstance().REALIZADO);
				
			});
			
			riscoPotencial.setEquipeResponsavel(riscoPotencial.getRiscoEmpregados().get(0).getEquipe());
			riscoPotencial.setStatus(StatusRiscoPotencial.getInstance().PLANEJAMENTO);
			
			RiscoPotencialBo.getInstance().save(riscoPotencial);
		}

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
			
			double r01 = (Math.log10(riscoEmpregado.getEquipe().getPrioridadeSast() + 1) /
						 	(riscoEmpregado.getEquipe().getPrioridadeSast() + 5)) / 
					(riscoEmpregado.getEquipe()
								.getPrioridadeSast() + triagens.size());
			
			double r1 = r0 + r01;
			
			if (triagens.stream().filter(t->t.getIndice() <= t.getIndicadorSast().getCritico()).count() > 0)
				r1 = 0.95 + r01;
			
			riscoEmpregado.setValor(r1);
			
			riscoEmpregado.getTriagens().forEach(t->t.setRiscoEmpregado(riscoEmpregado));
		}
		
		return riscoEmpregado;
	}

}