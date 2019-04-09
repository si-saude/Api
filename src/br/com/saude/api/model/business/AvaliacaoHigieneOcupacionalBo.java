package br.com.saude.api.model.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Objects;

import com.itextpdf.text.Document;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.generic.StringReplacer;
import br.com.saude.api.model.creation.builder.entity.AvaliacaoHigieneOcupacionalBuilder;
import br.com.saude.api.model.creation.builder.example.AvaliacaoHigieneOcupacionalExampleBuilder;
import br.com.saude.api.model.entity.filter.AtendimentoFilter;
import br.com.saude.api.model.entity.filter.AvaliacaoHigieneOcupacionalFilter;
import br.com.saude.api.model.entity.po.Atendimento;
import br.com.saude.api.model.entity.po.AvaliacaoHigieneOcupacional;
import br.com.saude.api.model.persistence.AvaliacaoHigieneOcupacionalDao;
import br.com.saude.api.util.constant.EnsaioVedacao;

@SuppressWarnings("deprecation")
public class AvaliacaoHigieneOcupacionalBo 
	extends GenericBo<AvaliacaoHigieneOcupacional, AvaliacaoHigieneOcupacionalFilter, AvaliacaoHigieneOcupacionalDao, AvaliacaoHigieneOcupacionalBuilder, AvaliacaoHigieneOcupacionalExampleBuilder> {

private static AvaliacaoHigieneOcupacionalBo instance;
	
	private AvaliacaoHigieneOcupacionalBo() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		
	}
	
	public static AvaliacaoHigieneOcupacionalBo getInstance() {
		if(instance==null)
			instance = new AvaliacaoHigieneOcupacionalBo();
		return instance;
	}
	
	@Override
	public AvaliacaoHigieneOcupacional getById(Object id) throws Exception {
		return getByEntity(getDao().getByIdLoadAll(id),this.functionLoadAll);
	}
	

	@SuppressWarnings({ "resource" })
	public String avaliacaoHigienOcupacionalToPdf(Atendimento atendimento) throws Exception{
		//OBTER O HTML DO RELATÓRIO		
		StringBuilder html = new StringBuilder();
		String line;
		
		URI uriDoc = new URI(Helper.getProjectPath().replace("/WEB-INF/classes", "")+"REPORT/RelatorioAvaliacaoHo.html");
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(uriDoc.getPath()));
		
		while((line = bufferedReader.readLine()) != null) {
			html.append(line);
		}
		
		bufferedReader.close();
		
		URI uri = new URI(Helper.getProjectPath()+"avaliacao-higiene-ocupacional/");
		File file = new File(uri.getPath());
		file.mkdirs();
		StringReplacer stringReplacer = new StringReplacer(html.toString());
		
		URI logoUri = new URI(Helper.getProjectPath().replace("/WEB-INF/classes", "")+"IMAGE/petrobras.png");
		DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat sourceFormatHours = new SimpleDateFormat("HH:mm");
		
		stringReplacer = stringReplacer.replace("logoPetrobras", logoUri.getPath());
	
		stringReplacer = stringReplacer
			.replace("tecnicoHo", Objects.toString(atendimento.getFilaAtendimentoOcupacional().getProfissional().getEmpregado().getPessoa().getNome()))
			.replace("dataDadosAtendimento", Objects.toString(sourceFormat.format(atendimento.getAvaliacaoHigieneOcupacional().getInicio())))
			.replace("localDadosAtendimento", Objects.toString(atendimento.getFilaAtendimentoOcupacional().getLocalizacao().getNome()))
			.replace("inicioDadosAtendimento", Objects.toString(sourceFormatHours.format(atendimento.getAvaliacaoHigieneOcupacional().getInicio())))
			.replace("finalDadosAtendimento", Objects.toString(sourceFormatHours.format(atendimento.getAvaliacaoHigieneOcupacional().getFim())))
			.replace("gerenciaDadosEmpregado", Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getGerencia().getCodigoCompleto()))
			.replace("funcaoDadosEmpregado", Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getCargo().getNome()))
			.replace("nomeDadosEmpregado",	Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getEmpregado().getPessoa().getNome()))
			.replace("matriculaDadosEmpregado", Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getEmpregado().getMatricula()))
			.replace("codigoGHE", Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getGhe() != null ? atendimento.getAvaliacaoHigieneOcupacional().getGhe().getCodigo() : ""))
			.replace("descricaoGHE", Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getGhe() != null ? atendimento.getAvaliacaoHigieneOcupacional().getGhe().getNome() : ""))
			.replace("simGHE",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().isConcordaDescricaoAprhoGhe() ? "X" : ""))
			.replace("naoGHE",Objects.toString(!atendimento.getAvaliacaoHigieneOcupacional().isConcordaDescricaoAprhoGhe() ? "X" : ""))
			.replace("booleanConcordaAgenteRiscos",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().isNaoConcordaAgentesRiscos() ? "X" : ""))
			.replace("booleanConcordaAtividades",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().isNaoConcordaAtividades() ? "X" : ""))
			.replace("booleanConcordaExposicaoRiscos",Objects.toString(
					atendimento.getAvaliacaoHigieneOcupacional().isNaoConcordaFrequenciaExposicaoRiscos() ? "X" : ""))
			.replace("booleanConcordaCategoriasRiscos",Objects.toString(
					atendimento.getAvaliacaoHigieneOcupacional().isNaoConcordaCategoriaRiscos() ? "X" : ""))
			.replace("brigada",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().isBrigada() ? "X" : ""))
			.replace("outos",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().isOutros() ? "X" : ""))
			.replace("fiscalSop",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().isFiscalSopSg() ? "X" : ""))
			.replace("opEcol",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().isOpEcolEcomp() ? "X" : ""))
			.replace("espacoConfinado",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().isEspacoConfinado() ? "X" : ""))
			.replace("usoVolutarioPR",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().isUsoVoluntario() ? "X" : ""))
			.replace("simEnsaioVedacao",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getEnsaioVedacao().equals(EnsaioVedacao.SIM) ? "X" : ""))
			.replace("naoEnsaioVedacao",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getEnsaioVedacao().equals(EnsaioVedacao.NAO) ? "X" : ""))
			.replace("naoAplicavelEnsaioVedacao",Objects.toString(
					atendimento.getAvaliacaoHigieneOcupacional().getEnsaioVedacao().equals(EnsaioVedacao.NAO_APLICAVEL) ? "X" : ""))
			.replace("booleanHoConcordaGheSim",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().isHOconcordaDescricaoAprhoGhe() ? "X" : ""))
			.replace("booleanHoConcordaGheNao",Objects.toString(!atendimento.getAvaliacaoHigieneOcupacional().isHOconcordaDescricaoAprhoGhe() ? "X" : ""))
			.replace("booleanBarbeado",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().isNaoBarbeado() ? "X" : ""))
			.replace("booleanMascara",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().isNaoUtilizaMascara() ? "X" : ""))
			.replace("booleanTesteSensibilidade",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().isTesteSensibilidadeInsatisfatorio() ? "X" : ""))
			.replace("observacaoEnsaioVedacao",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getObservacao()!= null ? atendimento.getAvaliacaoHigieneOcupacional().getObservacao() : ""))
			.replace("observacaoGheEmpregado",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getObservacaoGHE()!= null ? atendimento.getAvaliacaoHigieneOcupacional().getObservacaoGHE() : ""))
			.replace("justificativaHoGhe",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getJustificativaHO()!= null ? atendimento.getAvaliacaoHigieneOcupacional().getJustificativaHO() : ""))
			.replace("motivo", Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getMotivoAnalisePreliminar() != null ? atendimento.getAvaliacaoHigieneOcupacional().getMotivoAnalisePreliminar() : ""));
		
		
		stringReplacer.result();
		URI assinatura = null;
		
		try {
			 assinatura = new URI(Helper.getProjectPath() + "empregado/assinatura/" + atendimento.getFilaAtendimentoOcupacional().getProfissional().getEmpregado().getId() + ".png");
		} catch (Exception e) {
			assinatura = null;
		}
		
		if ( assinatura != null )
			stringReplacer = stringReplacer.replace("assinatura", assinatura.getPath());
		//GERAR PDF
		URI pdfUri = new URI(uri.getPath()+"/"+Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getId())+".pdf");
		File pdfFile = new File(pdfUri.getPath());
		FileOutputStream stream = new FileOutputStream(pdfFile);
		Document doc = new Document();
		PdfWriter.getInstance(doc, stream);
		doc.open();
		
		HTMLWorker htmlWorker = new HTMLWorker(doc);
		htmlWorker.parse(new StringReader(stringReplacer.result()));
		
		doc.close();
		stream.close();
		
		//RETORNO
		try {
			@SuppressWarnings("resource")
			FileInputStream fileInputStreamReader = new FileInputStream(pdfFile);
			byte[] fileArray = new byte[(int) pdfFile.length()];
			fileInputStreamReader.read(fileArray);
			return Base64.getEncoder().encodeToString(fileArray);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
		
		
		@SuppressWarnings({ "resource" })
		public String questionarioEnsaioVedacaoToPdf(Atendimento atendimento) throws Exception{
			//OBTER O HTML DO RELATÓRIO		
			StringBuilder html = new StringBuilder();
			String line;
			
			URI uriDoc = new URI(Helper.getProjectPath().replace("/WEB-INF/classes", "")+"REPORT/RelatorioQuestionarioEnsaioVedacao.html");
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader(uriDoc.getPath()));
			
			while((line = bufferedReader.readLine()) != null) {
				html.append(line);
			}
			
			bufferedReader.close();
			
			URI uri = new URI(Helper.getProjectPath()+"questionario-ensaio-vedacao/");
			File file = new File(uri.getPath());
			file.mkdirs();
			StringReplacer stringReplacer = new StringReplacer(html.toString());
			
			URI logoUri = new URI(Helper.getProjectPath().replace("/WEB-INF/classes", "")+"IMAGE/petrobras.png");
			URI logoNp1 = new URI(Helper.getProjectPath().replace("/WEB-INF/classes", "")+"IMAGE/np-1.png");
			DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			stringReplacer = stringReplacer.replace("logoPetrobras", logoUri.getPath())
					.replace("np1", logoNp1.getPath());
		
			stringReplacer = stringReplacer
				.replace("dataDadosAtendimento", Objects.toString(sourceFormat.format(atendimento.getAvaliacaoHigieneOcupacional().getInicio())))
				.replace("localDadosAtendimento", Objects.toString(atendimento.getFilaAtendimentoOcupacional().getLocalizacao().getNome()))
				.replace("gerenciaDadosEmpregado", Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getGerencia().getCodigoCompleto()))
				.replace("funcaoDadosEmpregado", Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getCargo().getNome()))
				.replace("nomeDadosEmpregado",	Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getEmpregado().getPessoa().getNome()))
				.replace("matriculaDadosEmpregado", Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getEmpregado().getMatricula()))				
				.replace("booleanFumaca",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().isFumacaIrritante() ? "X" : ""))
				.replace("booleanSacarina",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().isSacarina() ? "X" : ""))
				.replace("booleanAcetato",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().isAcetatoIsoamil() ? "X" : ""))
				.replace("booleanBenzoato",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().isBenzoatoDetonium() ? "X" : ""))
				.replace("modeloRespirador", Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().getModelo() != null ? atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().getModelo() :""))
				.replace("tipoRespirador", Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().getTipoRespirador()  != null ? atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().getTipoRespirador():""))
				.replace("tamanhoRespirador", Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().getTamanhoRespirador() != null ? atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().getTamanhoRespirador() :""))
				.replace("certificatoRespirador", Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().getNumeroCertificadoAprovacao()  != null ? atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().getNumeroCertificadoAprovacao()  :""))
				.replace("filtroRespirador", Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().getFiltroUtilizado() != null ? atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().getFiltroUtilizado() :""))				
				.replace("booleanBarba",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().isBarba() ? "X" : ""))
				.replace("booleanBigode",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().isBigode() ? "X" : ""))
				.replace("booleanCosteleta",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().isCosteleta() ? "X" : ""))				
				.replace("booleanPeloNa",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().isnAPelosFace() ? "X" : ""))
				.replace("booleanOculos",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().isOculos() ? "X" : ""))
				.replace("booleanLente",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().isLenteContato() ? "X" : ""))
				.replace("booleanVisaoNa",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().isnACorrecaoVisao() ? "X" : ""))
				.replace("booleanPositivaSatisfatoria",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().isSatisfatoria() ? "X" : ""))
				.replace("booleanPositivaDeficiente",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().isDeficiente() ? "X" : ""))
				.replace("booleanPositivaNa",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().isnATesteQualitativo() ? "X" : ""))				
				.replace("booleanNegativaSatisfatoria",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().isSatisfatoriaTestePressao() ? "X" : ""))
				.replace("booleanNegativaDeficiente",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().isDeficienteTestePressao() ? "X" : ""))
				.replace("booleanNegativaNa",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().isnATestePressao() ? "X" : ""))				
				.replace("booleanEnsaioSatisfatoria",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().isResultadoTesteVedacao() ? "X" : ""))
				.replace("booleanEnsaioInsatisfatoria",Objects.toString(!atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().isResultadoTesteVedacao() ? "X" : ""))
				.replace("booleanPesoSim",Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().isEsforcoFisicoUtilizandoMascara() ? "X" : ""))
				.replace("booleanPesoNao",Objects.toString(!atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().isEsforcoFisicoUtilizandoMascara() ? "X" : ""))
				.replace("comentarios", Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().getComentario() != null ? atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().getComentario(): "" ))
				.replace("aerodispersoides", Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().getExposicaoAerodispersoide() != null ? atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().getExposicaoAerodispersoide() :""))
				.replace("horasDia", Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().getHoraUsada() != null ? atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().getHoraUsada() : ""))
				.replace("diasSemana", Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().getDiaUsado() != null ? atendimento.getAvaliacaoHigieneOcupacional().getQuestionarioVedacaoMascara().getDiaUsado() : ""))
				.replace("nomeOperador", Objects.toString(atendimento.getFilaAtendimentoOcupacional().getProfissional().getEmpregado().getPessoa().getNome()))
				.replace("matriculaOperador", Objects.toString(atendimento.getFilaAtendimentoOcupacional().getProfissional().getEmpregado().getMatricula()));			
			
			stringReplacer.result();
			URI assinatura = null;
			
			try {
				 assinatura = new URI(Helper.getProjectPath() + "empregado/assinatura/" + atendimento.getFilaAtendimentoOcupacional().getProfissional().getEmpregado().getId() + ".png");
			} catch (Exception e) {
				assinatura = null;
			}
			
			if ( assinatura != null )
				stringReplacer = stringReplacer.replace("assinatura", assinatura.getPath());
			//GERAR PDF
			URI pdfUri = new URI(uri.getPath()+"/"+Objects.toString(atendimento.getAvaliacaoHigieneOcupacional().getId())+".pdf");
			File pdfFile = new File(pdfUri.getPath());
			FileOutputStream stream = new FileOutputStream(pdfFile);
			Document doc = new Document();
			PdfWriter.getInstance(doc, stream);
			doc.open();
			
			HTMLWorker htmlWorker = new HTMLWorker(doc);
			htmlWorker.parse(new StringReader(stringReplacer.result()));
			
			doc.close();
			stream.close();
			
			//RETORNO
			try {
				@SuppressWarnings("resource")
				FileInputStream fileInputStreamReader = new FileInputStream(pdfFile);
				byte[] fileArray = new byte[(int) pdfFile.length()];
				fileInputStreamReader.read(fileArray);
				return Base64.getEncoder().encodeToString(fileArray);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
	}

		public Atendimento getAvaliacaoAtendimento(long id) throws Exception {
			
			AtendimentoFilter atendimentoFilter = new AtendimentoFilter();
			atendimentoFilter.setPageNumber(1);
			atendimentoFilter.setPageSize(1);
			atendimentoFilter.setAvaliacaoHigieneOcupacional(new AvaliacaoHigieneOcupacionalFilter());
			atendimentoFilter.getAvaliacaoHigieneOcupacional().setId(id);
			PagedList<Atendimento> atendimentos = AtendimentoBo.getInstance().getListLoadAll(atendimentoFilter);		
			
			return atendimentos.getList().get(0);
		}
}