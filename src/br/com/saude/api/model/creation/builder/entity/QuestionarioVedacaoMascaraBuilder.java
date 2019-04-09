package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.QuestionarioVedacaoMascaraFilter;
import br.com.saude.api.model.entity.po.QuestionarioVedacaoMascara;

public class QuestionarioVedacaoMascaraBuilder extends GenericEntityBuilder<QuestionarioVedacaoMascara, QuestionarioVedacaoMascaraFilter> {
	
	public static QuestionarioVedacaoMascaraBuilder newInstance(QuestionarioVedacaoMascara questionario) {
		return new QuestionarioVedacaoMascaraBuilder(questionario);
	}
	
	public static QuestionarioVedacaoMascaraBuilder newInstance(List<QuestionarioVedacaoMascara> list) {
		return new QuestionarioVedacaoMascaraBuilder(list);
	}
	
	private QuestionarioVedacaoMascaraBuilder(QuestionarioVedacaoMascara questionario) {
		super(questionario);
	}

	private QuestionarioVedacaoMascaraBuilder(List<QuestionarioVedacaoMascara> list) {
		super(list);
	}
	
	@Override
	protected void initializeFunctions() {
		
	}
	@Override
	protected QuestionarioVedacaoMascara clone(QuestionarioVedacaoMascara entity) {
		QuestionarioVedacaoMascara newQuestionario= new QuestionarioVedacaoMascara();
		
		newQuestionario.setId(entity.getId());
		newQuestionario.setVersion(entity.getVersion());
		newQuestionario.setData(entity.getData());
		newQuestionario.setFumacaIrritante(entity.isFumacaIrritante());		
		newQuestionario.setSacarina(entity.isSacarina());
		newQuestionario.setAcetatoIsoamil(entity.isAcetatoIsoamil());
		newQuestionario.setBenzoatoDetonium(entity.isBenzoatoDetonium());
		newQuestionario.setTipoRespirador(entity.getTipoRespirador());
		newQuestionario.setTamanhoRespirador(entity.getTamanhoRespirador());
		newQuestionario.setModelo(entity.getModelo());
		newQuestionario.setNumeroCertificadoAprovacao(entity.getNumeroCertificadoAprovacao());
		newQuestionario.setFiltroUtilizado(entity.getFiltroUtilizado());
		newQuestionario.setBarba(entity.isBarba());
		newQuestionario.setBigode(entity.isBigode());
		newQuestionario.setCosteleta(entity.isCosteleta());
		newQuestionario.setnAPelosFace(entity.isnAPelosFace());
		newQuestionario.setOculos(entity.isOculos());
		newQuestionario.setLenteContato(entity.isLenteContato());
		newQuestionario.setnACorrecaoVisao(entity.isnACorrecaoVisao());
		newQuestionario.setSatisfatoria(entity.isSatisfatoria());
		newQuestionario.setDeficiente(entity.isDeficiente());
		newQuestionario.setnATesteQualitativo(entity.isnATesteQualitativo());

		newQuestionario.setSatisfatoriaTestePressao(entity.isSatisfatoriaTestePressao());
		newQuestionario.setDeficienteTestePressao(entity.isDeficienteTestePressao());
		newQuestionario.setnATestePressao(entity.isnATestePressao());
		newQuestionario.setResultadoTesteVedacao(entity.isResultadoTesteVedacao());
		newQuestionario.setComentario(entity.getComentario());
		newQuestionario.setExposicaoAerodispersoide(entity.getExposicaoAerodispersoide());
		newQuestionario.setHoraUsada(entity.getHoraUsada());
		newQuestionario.setDiaUsado(entity.getDiaUsado());
		newQuestionario.setEsforcoFisicoUtilizandoMascara(entity.isEsforcoFisicoUtilizandoMascara());
		
		return newQuestionario;
	}

	@Override
	public QuestionarioVedacaoMascara cloneFromFilter(QuestionarioVedacaoMascaraFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}
}
