package br.com.saude.api.model.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.HibernateHelper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Atendimento;
import br.com.saude.api.model.entity.po.AvaliacaoFisicaAtividadeFisica;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.FichaColeta;
import br.com.saude.api.model.entity.po.FilaAtendimentoOcupacional;
import br.com.saude.api.model.entity.po.FilaAtendimentoOcupacionalAtualizacao;
import br.com.saude.api.model.entity.po.FilaEsperaOcupacional;
import br.com.saude.api.model.entity.po.GrupoMonitoramento;
import br.com.saude.api.model.entity.po.IndicadorSast;
import br.com.saude.api.model.entity.po.ItemPerguntaFichaColeta;
import br.com.saude.api.model.entity.po.ItemRespostaFichaColeta;
import br.com.saude.api.model.entity.po.Localizacao;
import br.com.saude.api.model.entity.po.PerguntaFichaColeta;
import br.com.saude.api.model.entity.po.Pessoa;
import br.com.saude.api.model.entity.po.Profissional;
import br.com.saude.api.model.entity.po.QuestionarioConhecimentoAlimentar;
import br.com.saude.api.model.entity.po.Recordatorio;
import br.com.saude.api.model.entity.po.RespostaFichaColeta;
import br.com.saude.api.model.entity.po.RespostaQuestionarioConhecimentoAlimentar;
import br.com.saude.api.model.entity.po.RiscoPotencial;
import br.com.saude.api.model.entity.po.Tarefa;
import br.com.saude.api.model.entity.po.Exame;
import br.com.saude.api.model.entity.po.Aptidao;
import br.com.saude.api.model.entity.po.Equipe;
import br.com.saude.api.util.constant.StatusFilaAtendimentoOcupacional;
import br.com.saude.api.util.constant.StatusTarefa;
import br.com.saude.api.model.entity.po.Aso;
import br.com.saude.api.model.entity.po.RiscoEmpregado;
import br.com.saude.api.model.entity.po.AsoAlteracao;
import br.com.saude.api.model.entity.po.AsoAvaliacao;
import br.com.saude.api.model.entity.po.ItemIndicadorConhecimentoAlimentar;
import br.com.saude.api.model.entity.po.Refeicao;
import br.com.saude.api.model.entity.po.ItemRefeicao;
import br.com.saude.api.model.entity.po.AtividadeFisica;

public class AtendimentoDao extends GenericDao<Atendimento> {

	private static AtendimentoDao instance;
	
	private AtendimentoDao() {
		super();
	}
	
	public static AtendimentoDao getInstance() {
		if(instance == null)
			instance = new AtendimentoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = atendimento -> {
			if(atendimento.getTarefa() != null)
				atendimento.setTarefa((Tarefa) Hibernate.unproxy(atendimento.getTarefa()));
			
			if(atendimento.getAso() != null) {
				atendimento.setAso((Aso)Hibernate.unproxy(atendimento.getAso()));
				
				if(atendimento.getAso().getAsoAlteracoes() != null) {
					List<AsoAlteracao> list = new ArrayList<AsoAlteracao>();
					Hibernate.initialize(atendimento.getAso().getAsoAlteracoes());					
					atendimento.getAso().getAsoAlteracoes().forEach(x->{						
						AsoAlteracao asoAlteracoes = (AsoAlteracao)Hibernate.unproxy(x);	
						list.add(asoAlteracoes);
					});
					atendimento.getAso().setAsoAlteracoes(list);
				}	
				
				if(atendimento.getAso().getAsoAvaliacoes() != null) {
					List<AsoAvaliacao> list = new ArrayList<AsoAvaliacao>();
					Hibernate.initialize(atendimento.getAso().getAsoAvaliacoes());					
					atendimento.getAso().getAsoAvaliacoes().forEach(x->{						
						AsoAvaliacao asoAvaliacoes = (AsoAvaliacao)Hibernate.unproxy(x);	
						list.add(asoAvaliacoes);
					});
					atendimento.getAso().setAsoAvaliacoes(list);
				}	
				
				if(atendimento.getAso().getExamesConvocacao() != null) {
					List<Exame> list = new ArrayList<Exame>();
					Hibernate.initialize(atendimento.getAso().getExamesConvocacao());					
					atendimento.getAso().getExamesConvocacao().forEach(x->{						
						Exame exame = (Exame)Hibernate.unproxy(x);	
						list.add(exame);
					});
					atendimento.getAso().setExamesConvocacao(list);
				}				
				if(atendimento.getAso().getAptidoes() != null) {
					List<Aptidao> list = new ArrayList<Aptidao>();
					Hibernate.initialize(atendimento.getAso().getAptidoes());
					atendimento.getAso().getAptidoes().forEach(x->{						
						Aptidao aptidao = (Aptidao)Hibernate.unproxy(x);
						if(aptidao.getGrupoMonitoramento() != null)
							aptidao.setGrupoMonitoramento((GrupoMonitoramento)Hibernate.unproxy(aptidao.getGrupoMonitoramento()));
						list.add(aptidao);
					});
					atendimento.getAso().setAptidoes(list);
				}
			}
			
			if(atendimento.getQuestionario() != null) {
				atendimento.setQuestionario(
					(QuestionarioConhecimentoAlimentar)Hibernate.unproxy(atendimento.getQuestionario()));
				if ( atendimento.getQuestionario().getRespostas() != null ) {
					List<RespostaQuestionarioConhecimentoAlimentar> respostas = new ArrayList<RespostaQuestionarioConhecimentoAlimentar>();
					atendimento.getQuestionario().getRespostas().forEach(r -> {
						Hibernate.initialize(r);
						if ( r.getIndicador().getItemIndicadorConhecimentoAlimentares() != null ) {
							List<ItemIndicadorConhecimentoAlimentar> itens = new ArrayList<ItemIndicadorConhecimentoAlimentar>();
							r.getIndicador().getItemIndicadorConhecimentoAlimentares().forEach(i -> {
								itens.add((ItemIndicadorConhecimentoAlimentar)Hibernate.unproxy(i));
							});
							r.getIndicador().setItemIndicadorConhecimentoAlimentares(itens);
						}
						respostas.add(r);
					});
					atendimento.getQuestionario().setRespostas(respostas);
				}
			}
			
			if(atendimento.getRecordatorio() != null) {
				atendimento.setRecordatorio(
					(Recordatorio)Hibernate.unproxy(atendimento.getRecordatorio()));
				if ( atendimento.getRecordatorio().getRefeicoes() != null ) {
					List<Refeicao> refeicoes = new ArrayList<Refeicao>();
					atendimento.getRecordatorio().getRefeicoes().forEach(r -> {
						Hibernate.initialize(r);
						if ( r.getItens() != null ) {
							List<ItemRefeicao> itens = new ArrayList<ItemRefeicao>();
							r.getItens().forEach(i -> {
								itens.add((ItemRefeicao) Hibernate.unproxy(i));
							});
							r.setItens(itens);
						}
						refeicoes.add(r);
					});
					atendimento.getRecordatorio().setRefeicoes(refeicoes);
				}
			}
			
			if(atendimento.getFilaAtendimentoOcupacional().getLocalizacao() != null)
				atendimento.getFilaAtendimentoOcupacional().setLocalizacao(
						(Localizacao) Hibernate.unproxy(atendimento.getFilaAtendimentoOcupacional().getLocalizacao()));
			
			if(atendimento.getFilaAtendimentoOcupacional().getProfissional() != null) {
				atendimento.getFilaAtendimentoOcupacional().setProfissional((Profissional) Hibernate.unproxy(
						atendimento.getFilaAtendimentoOcupacional().getProfissional()));
				
				if(atendimento.getFilaAtendimentoOcupacional().getProfissional().getEquipe() != null)
					atendimento.getFilaAtendimentoOcupacional().getProfissional().setEquipe((Equipe) Hibernate.unproxy(
							atendimento.getFilaAtendimentoOcupacional().getProfissional().getEquipe()));
			}
			
			if(atendimento.getFilaAtendimentoOcupacional().getAtualizacoes() != null) {
				List<FilaAtendimentoOcupacionalAtualizacao> list = new ArrayList<FilaAtendimentoOcupacionalAtualizacao>();
				atendimento.getFilaAtendimentoOcupacional().getAtualizacoes().forEach(a -> {
					list.add((FilaAtendimentoOcupacionalAtualizacao) Hibernate.unproxy(a));
				});
				atendimento.getFilaAtendimentoOcupacional().setAtualizacoes(list);
			}				
			
			if(atendimento.getFilaEsperaOcupacional().getLocalizacao() != null)
				atendimento.getFilaEsperaOcupacional().setLocalizacao(
						(Localizacao) Hibernate.unproxy(atendimento.getFilaEsperaOcupacional().getLocalizacao()));
			
			if(atendimento.getFilaEsperaOcupacional().getRiscoPotencial() != null) {
				atendimento.getFilaEsperaOcupacional().setRiscoPotencial(
						(RiscoPotencial) Hibernate.unproxy(atendimento.getFilaEsperaOcupacional().getRiscoPotencial()));
								
				if(atendimento.getFilaEsperaOcupacional().getRiscoPotencial().getEquipes() != null) {
					List<Equipe> equipes = new ArrayList<Equipe>();
					atendimento.getFilaEsperaOcupacional().getRiscoPotencial().getEquipes().forEach(e -> {
						equipes.add((Equipe) Hibernate.unproxy(e));
					});
					atendimento.getFilaEsperaOcupacional().getRiscoPotencial().setEquipes(equipes);
				}
				
				if(atendimento.getFilaEsperaOcupacional().getRiscoPotencial().getRiscoEmpregados() != null) {
					List<RiscoEmpregado> riscos = new ArrayList<RiscoEmpregado>();
					atendimento.getFilaEsperaOcupacional().getRiscoPotencial().getRiscoEmpregados().forEach(r->{
						riscos.add((RiscoEmpregado) Hibernate.unproxy(r));
					});
					atendimento.getFilaEsperaOcupacional().getRiscoPotencial().setRiscoEmpregados(riscos);
				}
			}
			
			if(atendimento.getFilaEsperaOcupacional().getFichaColeta() != null) {
				atendimento.getFilaEsperaOcupacional().setFichaColeta((FichaColeta) 
						Hibernate.unproxy(atendimento.getFilaEsperaOcupacional().getFichaColeta()));
				
				List<RespostaFichaColeta> respostas = new ArrayList<RespostaFichaColeta>();
				
				atendimento.getFilaEsperaOcupacional().getFichaColeta().getRespostaFichaColetas().forEach(r->{
					respostas.add((RespostaFichaColeta) Hibernate.unproxy(r));
				});
				
				
				respostas.forEach(r -> {
					List<ItemRespostaFichaColeta> itens = new ArrayList<ItemRespostaFichaColeta>();
					
					r.getItens().forEach(i -> {
						itens.add(FilaEsperaOcupacionalDao.getInstance().inicializeItem(i));
					});
					
					r.setItens(itens);
					
					List<ItemPerguntaFichaColeta> itensPergunta = new ArrayList<ItemPerguntaFichaColeta>(); 
					r.setPergunta((PerguntaFichaColeta) Hibernate.unproxy(r.getPergunta()));
					r.getPergunta().getItens().forEach(i -> {
						itensPergunta.add((ItemPerguntaFichaColeta) Hibernate.unproxy(i));
					});
					
					List<Equipe> equipes = new ArrayList<Equipe>();
					r.getPergunta().getEquipes().forEach(e -> {
						equipes.add((Equipe) Hibernate.unproxy(e));
					});
					
					r.getPergunta().setEquipes(equipes);
					r.getPergunta().setItens(itensPergunta);
				});
				
				atendimento.getFilaEsperaOcupacional().getFichaColeta().setRespostaFichaColetas(respostas);
			}
			
			if(atendimento.getTriagens() != null) {
				
				Hibernate.initialize(atendimento.getTriagens());	
				
				atendimento.getTriagens().forEach(t->{
					t.setIndicadorSast((IndicadorSast) Hibernate.unproxy(t.getIndicadorSast()));
				});
			} 
			
			if ( atendimento.getAvaliacaoFisica() != null ) {
				Hibernate.initialize(atendimento.getAvaliacaoFisica());
				List<AvaliacaoFisicaAtividadeFisica> afafs = new ArrayList<>();
				atendimento.getAvaliacaoFisica().getAvaliacaoFisicaAtividadeFisicas().forEach( afaf1 -> {
					Hibernate.initialize(afaf1);
					afaf1.setAtividadeFisica((AtividadeFisica) Hibernate.unproxy(afaf1.getAtividadeFisica()));
					afafs.add(afaf1);
				});
				atendimento.getAvaliacaoFisica().setAvaliacaoFisicaAtividadeFisicas(afafs);
			}
			
			return atendimento;
		};
		
		this.functionBeforeSave = pair -> {
			Atendimento atendimento = pair.getValue0();
			
			if(atendimento.getFilaAtendimentoOcupacional().getAtualizacoes() != null)
				atendimento.getFilaAtendimentoOcupacional().getAtualizacoes().forEach(a->
					a.setFila(atendimento.getFilaAtendimentoOcupacional()));
			
			return atendimento;
		};
	}
	
	public void devolverPraFila(Atendimento atendimento) throws Exception {
		Session session = HibernateHelper.getSession();
		
		try {
			Transaction transaction = session.beginTransaction();
						
			if(atendimento.getFilaEsperaOcupacional().getFichaColeta() != null &&
					atendimento.getFilaEsperaOcupacional().getFichaColeta().getRespostaFichaColetas() != null) {
				FichaColeta ficha = atendimento.getFilaEsperaOcupacional().getFichaColeta();
				atendimento.getFilaEsperaOcupacional().getFichaColeta().getRespostaFichaColetas()
					.forEach(r->r.setFicha(ficha));
			}
					
			//SALVAR ATENDIMENTO E DELETAR
			atendimento = (Atendimento) session.merge(atendimento);
			
			session.delete(atendimento);
			
			transaction.commit();
		}catch(Exception ex) {
			throw ex;
		}finally {
			HibernateHelper.close(session);
		}
	}
	
	public Atendimento getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
	public PagedList<Atendimento> getListLoadAll(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder,this.functionLoadAll);
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Atendimento> getListFilaAtendimentoLocalizacaoDoMomento(Atendimento atendimento){
		
		List<Atendimento> atendimentos = new ArrayList<Atendimento>();
		Session session = HibernateHelper.getSession();
		
		try {
			
			Date inicio = Date.from(Helper.getToday().toInstant());
			Date fim = Date.from(inicio.toInstant());
			
			fim.setHours(23);
			fim.setMinutes(59);
			
			Criteria criteria = session.createCriteria(Atendimento.class,"a");
			
			//MONTAR CRITÉRIO
			criteria.createAlias("a.filaAtendimentoOcupacional", "filaAtendimentoOcupacional", 
					JoinType.RIGHT_OUTER_JOIN)
				.createAlias("filaAtendimentoOcupacional.localizacao", "localizacao", JoinType.INNER_JOIN)
				.createAlias("filaAtendimentoOcupacional.profissional", "profissional", JoinType.LEFT_OUTER_JOIN)
				.createAlias("profissional.empregado", "empregado", JoinType.LEFT_OUTER_JOIN)
				.createAlias("empregado.pessoa", "pessoa", JoinType.LEFT_OUTER_JOIN)
				.createCriteria("a.tarefa", "tarefa", JoinType.LEFT_OUTER_JOIN, 
					Restrictions.eq("tarefa.status", StatusTarefa.getInstance().EXECUCAO));
				criteria.createAlias("a.filaEsperaOcupacional", "filaEsperaOcupacional", JoinType.LEFT_OUTER_JOIN,
						Restrictions.conjunction().add(
								Subqueries.exists(
										DetachedCriteria.forClass(Atendimento.class,"a2")
										.createAlias("a2.tarefa", "t")
										.setProjection(Projections.id())
										.add(Restrictions.eqProperty("a.id", "a2.id"))
										.add(Restrictions.eq("t.status", StatusTarefa.getInstance().EXECUCAO)))))
				.createAlias("filaEsperaOcupacional.empregado", "emp", JoinType.LEFT_OUTER_JOIN)
				.createAlias("emp.pessoa", "pes", JoinType.LEFT_OUTER_JOIN);
			
			criteria
				.add(Restrictions.between("filaAtendimentoOcupacional.inicio", inicio, fim))
				.add(Restrictions.eq("localizacao.id",
						atendimento.getFilaAtendimentoOcupacional().getLocalizacao().getId()))
				.add(Restrictions.or(
						Restrictions.and(Restrictions.ne("filaAtendimentoOcupacional.status", 
											StatusFilaAtendimentoOcupacional.getInstance().EM_ATENDIMENTO), 
										Restrictions.and(
													Restrictions.ne("filaAtendimentoOcupacional.status", 
															StatusFilaAtendimentoOcupacional.getInstance().AGUARDANDO_EMPREGADO), 
													Restrictions.ne("filaAtendimentoOcupacional.status", 
															StatusFilaAtendimentoOcupacional.getInstance().LANCAMENTO_DE_INFORMACOES))), 
						Restrictions.isNotNull("tarefa.id")));
			
			ProjectionList pL = Projections.projectionList();
			pL.add(Projections.groupProperty("pessoa.nome"));
			pL.add(Projections.groupProperty("filaAtendimentoOcupacional.status"));
			pL.add(Projections.groupProperty("pes.nome"));
			criteria.setProjection(pL);
			criteria.addOrder(Order.asc("filaAtendimentoOcupacional.status"));
			criteria.addOrder(Order.asc("pessoa.nome"));
			
			List<Object[]> list = criteria.list();
			Atendimento a = null;
			
			for(Object[] obj : list) {
				a = new Atendimento();
				a.setFilaAtendimentoOcupacional(new FilaAtendimentoOcupacional());
				a.getFilaAtendimentoOcupacional().setProfissional(new Profissional());
				a.getFilaAtendimentoOcupacional().getProfissional().setEmpregado(new Empregado());
				a.getFilaAtendimentoOcupacional().getProfissional().getEmpregado().setPessoa(new Pessoa());
				a.getFilaAtendimentoOcupacional().getProfissional().getEmpregado().getPessoa().setNome((String)obj[0]);
				a.getFilaAtendimentoOcupacional().setStatus((String)obj[1]);
				
				if(obj[2] != null) {
					a.setFilaEsperaOcupacional(new FilaEsperaOcupacional());
					a.getFilaEsperaOcupacional().setEmpregado(new Empregado());
					a.getFilaEsperaOcupacional().getEmpregado().setPessoa(new Pessoa());
					a.getFilaEsperaOcupacional().getEmpregado().getPessoa().setNome((String)obj[2]);
				}
				
				atendimentos.add(a);
			}
		}catch (Exception ex) {
			throw ex;
		}
		finally {
			HibernateHelper.close(session);
		}
		
		return atendimentos;
	}
}
