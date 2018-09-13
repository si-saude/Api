package br.com.saude.api.model.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.Base64;
import java.util.Objects;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.StringReplacer;
import br.com.saude.api.model.creation.builder.entity.AvaliacaoHigieneOcupacionalBuilder;
import br.com.saude.api.model.creation.builder.example.AvaliacaoHigieneOcupacionalExampleBuilder;
import br.com.saude.api.model.entity.filter.AprhoEmpregadoFilter;
import br.com.saude.api.model.entity.filter.AvaliacaoHigieneOcupacionalFilter;
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.po.AprhoEmpregado;
import br.com.saude.api.model.entity.po.AvaliacaoHigieneOcupacional;
import br.com.saude.api.model.entity.po.Empregado;
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
		this.functionLoadAll = builder -> {
			return builder.loadLocal();
		};
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
	
	public String avaliacaoHigienOcupacionalToPdf(AvaliacaoHigieneOcupacional avaliacaoHigieneOcupacional) throws Exception{
		//OBTER O HTML DO RELATÓRIO		
		StringBuilder html = new StringBuilder();
		String line;
		
		avaliacaoHigieneOcupacional = AvaliacaoHigieneOcupacionalBo.getInstance().getById(avaliacaoHigieneOcupacional.getId());
		Empregado empregado = EmpregadoBo.getInstance().getById(avaliacaoHigieneOcupacional.getEmpregado().getId());
		
		URI uriDoc = new URI(getClass().getProtectionDomain().getCodeSource().getLocation().toString()
				.replace("/WEB-INF/classes", "")+"REPORT/RelatorioAvaliacaoHigieneOcupacional.html");
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(uriDoc.getPath()));
		
		while((line = bufferedReader.readLine()) != null) {
			html.append(line);
		}
		
		bufferedReader.close();
		
		URI uri = new URI(getClass().getProtectionDomain().getCodeSource().getLocation().toString()
				+"avaliacao-higiene-ocupacional/");
		File file = new File(uri.getPath());
		file.mkdirs();
		StringReplacer stringReplacer = new StringReplacer(html.toString());
		
		URI logoUri = new URI(getClass().getProtectionDomain().getCodeSource().getLocation().toString()
				.replace("/WEB-INF/classes", "")+"IMAGE/petrobras.png");
		stringReplacer = stringReplacer.replace("logoPetrobras", logoUri.getPath());
		
		String aprhoEmpregadoRevisao = "";
		AprhoEmpregado aprhoEmpregado = null;
		AprhoEmpregadoFilter aprhoEmpregadoFilter = new AprhoEmpregadoFilter();
		aprhoEmpregadoFilter.setEmpregado(new EmpregadoFilter());
		aprhoEmpregadoFilter.getEmpregado().setId(avaliacaoHigieneOcupacional.getEmpregado().getId());
		aprhoEmpregadoFilter.setAtual(new BooleanFilter());
		aprhoEmpregadoFilter.getAtual().setValue(1);
		aprhoEmpregadoFilter.setPageNumber(1);
		aprhoEmpregadoFilter.setPageSize(1);
		if ( AprhoEmpregadoBo.getInstance().getListLoadAll(aprhoEmpregadoFilter).getTotal() > 0 ) { 
			aprhoEmpregado = AprhoEmpregadoBo.getInstance().getListLoadAll(aprhoEmpregadoFilter).getList().get(0);
			if ( aprhoEmpregado.getAprho() != null) aprhoEmpregadoRevisao = aprhoEmpregado.getAprho().getRevisao();  
		}
		
		
		stringReplacer = stringReplacer
			.replace("dataDadosAtendimento", Objects.toString(avaliacaoHigieneOcupacional.getData().toLocaleString().substring(0, 10)))
			.replace("localDadosAtendimento", Objects.toString(avaliacaoHigieneOcupacional.getLocal().getNome()))
			.replace("tecnicoHODadosAtendimento", Objects.toString(avaliacaoHigieneOcupacional.getTecnico().getEmpregado().getPessoa().getNome()))
			.replace("inicioDadosAtendimento", Objects.toString(avaliacaoHigieneOcupacional.getInicio().getHours()))
			.replace("finalDadosAtendimento", Objects.toString(avaliacaoHigieneOcupacional.getFim().getHours()))
			.replace("gerenciaDadosEmpregado", Objects.toString(empregado.getGerencia().getCodigoCompleto()))
			.replace("funcaoDadosEmpregado", Objects.toString(empregado.getFuncao().getNome()))
			.replace("nomeDadosEmpregado",	Objects.toString(avaliacaoHigieneOcupacional.getEmpregado().getPessoa().getNome()))
			.replace("matriculaDadosEmpregado", Objects.toString(avaliacaoHigieneOcupacional.getEmpregado().getMatricula()))
			.replace("codigoGHE", Objects.toString(empregado.getGhe() != null ? empregado.getGhe().getCodigo() : ""))
			.replace("descricaoGHE", Objects.toString(empregado.getGhe() != null ? empregado.getGhe().getDescricao() : ""))
			.replace("aprhoEmpregado", Objects.toString(aprhoEmpregadoRevisao))
			.replace("simGHE",Objects.toString(avaliacaoHigieneOcupacional.isConcordaDescricaoAprhoGhe() ? "X" : ""))
			.replace("naoGHE",Objects.toString(!avaliacaoHigieneOcupacional.isConcordaDescricaoAprhoGhe() ? "X" : ""))
			.replace("booleanConcordaAgenteRisco",Objects.toString(avaliacaoHigieneOcupacional.isNaoConcordaAgentesRiscos() ? "X" : ""))
			.replace("booleanConcordaAtividades",Objects.toString(avaliacaoHigieneOcupacional.isNaoConcordaAtividades() ? "X" : ""))
			.replace("booleanConcordaExposicaoRiscos",Objects.toString(
					avaliacaoHigieneOcupacional.isNaoConcordaFrequenciaExposicaoRiscos() ? "X" : ""))
			.replace("booleanConcordaCategoriasRiscos",Objects.toString(
					avaliacaoHigieneOcupacional.isNaoConcordaCategoriaRiscos() ? "X" : ""))
			.replace("brigada",Objects.toString(avaliacaoHigieneOcupacional.isBrigada() ? "X" : ""))
			.replace("espacoConfinado",Objects.toString(avaliacaoHigieneOcupacional.isEspacoConfinado() ? "X" : ""))
			.replace("usoVolutarioPR",Objects.toString(avaliacaoHigieneOcupacional.isUsoVoluntario() ? "X" : ""))
			.replace("simEnsaioVedacao",Objects.toString(avaliacaoHigieneOcupacional.getEnsaioVedacao().equals(EnsaioVedacao.SIM) ? "X" : ""))
			.replace("naoEnsaioVedacao",Objects.toString(avaliacaoHigieneOcupacional.getEnsaioVedacao().equals(EnsaioVedacao.NAO) ? "X" : ""))
			.replace("naoAplicaEnsaioVedacao",Objects.toString(
					avaliacaoHigieneOcupacional.getEnsaioVedacao().equals(EnsaioVedacao.NAO_APLICAVEL) ? "X" : ""))
			.replace("booleanBarbeado",Objects.toString(avaliacaoHigieneOcupacional.isNaoBarbeado() ? "X" : ""))
			.replace("booleanMascara",Objects.toString(avaliacaoHigieneOcupacional.isNaoUtilizaMascara() ? "X" : ""))
			.replace("booleanTesteSensibilidade",Objects.toString(avaliacaoHigieneOcupacional.isTesteSensibilidadeInsatisfatorio() ? "X" : ""))
			.replace("observacaoEnsaioVedacao",Objects.toString(avaliacaoHigieneOcupacional.getObservacao()));
		
		stringReplacer.result();
		
		//GERAR PDF
		URI pdfUri = new URI(uri.getPath()+"/"+Objects.toString(avaliacaoHigieneOcupacional.getId())+".pdf");
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
}