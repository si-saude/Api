package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.RecordatorioBuilder;
import br.com.saude.api.model.creation.builder.example.RecordatorioExampleBuilder;
import br.com.saude.api.model.entity.filter.RecordatorioFilter;
import br.com.saude.api.model.entity.po.Recordatorio;
import br.com.saude.api.model.entity.po.RespostaFichaColeta;
import br.com.saude.api.model.persistence.RecordatorioDao;
import br.com.saude.api.util.constant.GrupoPerguntaFichaColeta;
import br.com.saude.api.util.constant.NivelAtividadeFisica;
import br.com.saude.api.util.constant.Sexo;

public class RecordatorioBo extends 
	GenericBo<Recordatorio, RecordatorioFilter, RecordatorioDao, RecordatorioBuilder, RecordatorioExampleBuilder> {
	
	private static RecordatorioBo instance;
	
	private RecordatorioBo() {
		super();
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			builder = builder.loadRefeicoes();
			return builder;
		};
	}
	
	public static RecordatorioBo getInstance() {
		if(instance==null)
			instance = new RecordatorioBo();
		return instance;
	}
	
	@Override
	public Recordatorio save(Recordatorio entity)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, Exception {
	
		if ( entity.getRefeicoes() != null )
			entity.getRefeicoes().forEach(r -> {
				r.setRecordatorio(entity);
				r.getItens().forEach(i -> i.setRefeicao(r));
			});
		
		return super.save(entity);
	}
	
	@Override
	public Recordatorio getById(Object id) throws Exception {
		return getByEntity(getDao().getById(id),functionLoadAll);
	}
	
	public Recordatorio getNe(Recordatorio recordatorio) throws Exception {
		recordatorio.setAtendimento(AtendimentoBo.getInstance().getById(recordatorio.getAtendimento().getId()));
		
		int idade = recordatorio.getAtendimento().getFilaEsperaOcupacional().getEmpregado().getPessoa().getIdade();
		
		RespostaFichaColeta rPeso = recordatorio.getAtendimento().
			getFilaEsperaOcupacional().getFichaColeta().
			getRespostaFichaColetas().stream().filter(r -> {
				if (r.getPergunta().getCodigo().equals("0001") && 
						r.getPergunta().getGrupo().equals(GrupoPerguntaFichaColeta.EXAME_FISICO) )
					return true;
				return false;
			}).findFirst().get();
		RespostaFichaColeta rAltura = recordatorio.getAtendimento().
				getFilaEsperaOcupacional().getFichaColeta().
				getRespostaFichaColetas().stream().filter(r -> {
					if (r.getPergunta().getCodigo().equals("0002") && 
							r.getPergunta().getGrupo().equals(GrupoPerguntaFichaColeta.EXAME_FISICO) )
						return true;
					return false;
				}).findFirst().get();
		RespostaFichaColeta rNivelAtividade = recordatorio.getAtendimento().
				getFilaEsperaOcupacional().getFichaColeta().
				getRespostaFichaColetas().stream().filter(r -> {
					if (r.getPergunta().getCodigo().equals("0018") && 
							r.getPergunta().getGrupo().equals(GrupoPerguntaFichaColeta.EXAME_FISICO) )
						return true;
					return false;
				}).findFirst().get();
		if ( rPeso == null || rAltura == null || rNivelAtividade == null ) return recordatorio;
		float peso = Float.parseFloat(rPeso.getConteudo().replace(",", "."));
		float altura = Float.parseFloat(rAltura.getConteudo().replace(",", "."));
		
		float tmb;
		float get;
		if ( recordatorio.getAtendimento().
				getFilaEsperaOcupacional().getEmpregado().getPessoa().getSexo().equals(Sexo.getInstance().FEMININO)) {
			tmb = (float) (655 + (9.6 * peso) + (1.8 * altura) - (4.7 * idade));
		} else {
			tmb = (float) (66.5 + (13.7 * peso) + (5.0 * altura) - (6.8 * idade));
		}
		
		recordatorio.setTmb(tmb);
		
		get = (float) (tmb*getFatorAtividade(rNivelAtividade.getConteudo())); 
		
		recordatorio.setNe(Float.parseFloat(new DecimalFormat(".##").format(get).replace(",", ".")));
		
		return recordatorio;
	}
	
	private double getFatorAtividade(String nivelAtividade) {
		switch(nivelAtividade) {
			case NivelAtividadeFisica.IRREGULAR_ATIVO_B:
			case NivelAtividadeFisica.SEDENTARIO:
				return 1.2;
			case NivelAtividadeFisica.IRREGULAR_ATIVO_A:
				return 1.375;
			case NivelAtividadeFisica.REGULARMENTE_ATIVO:
				return 1.55;
			default:
				return 1.725;
		}
	}
}
