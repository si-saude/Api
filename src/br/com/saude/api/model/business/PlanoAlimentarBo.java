package br.com.saude.api.model.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.Objects;
import java.util.function.Function;

import com.itextpdf.text.Document;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.generic.StringReplacer;
import br.com.saude.api.model.creation.builder.entity.PlanoAlimentarBuilder;
import br.com.saude.api.model.creation.builder.example.PlanoAlimentarExampleBuilder;
import br.com.saude.api.model.entity.filter.PlanoAlimentarFilter;
import br.com.saude.api.model.entity.po.PlanoAlimentar;
import br.com.saude.api.model.entity.po.RespostaFichaColeta;
import br.com.saude.api.model.persistence.PlanoAlimentarDao;
import br.com.saude.api.util.constant.GrupoPerguntaFichaColeta;
import br.com.saude.api.util.constant.NivelAtividadeFisica;
import br.com.saude.api.util.constant.Sexo;

@SuppressWarnings("deprecation")
public class PlanoAlimentarBo extends 
	GenericBo<PlanoAlimentar, PlanoAlimentarFilter, PlanoAlimentarDao, PlanoAlimentarBuilder, PlanoAlimentarExampleBuilder> {
	
	private static PlanoAlimentarBo instance;
	
	protected Function<PlanoAlimentarBuilder, PlanoAlimentarBuilder> functionLoadAlimentos;
	
	private PlanoAlimentarBo() {
		super();
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			builder = builder.loadRefeicoes();
			return builder;
		};
		
		this.functionLoadAlimentos = builder -> {
			builder = builder.loadRefeicoesAlimentos();
			return builder;
		};
	}
	
	public static PlanoAlimentarBo getInstance() {
		if(instance==null)
			instance = new PlanoAlimentarBo();
		return instance;
	}
	
	@Override
	public PlanoAlimentar save(PlanoAlimentar entity)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, Exception {
	
		if ( entity.getRefeicoes() != null )
			entity.getRefeicoes().forEach(r -> {
				r.setPlanoAlimentar(entity);
				r.getItens().forEach(i -> i.setRefeicaoPlano(r));
			});
		
		return super.save(entity);
	}
	
	@Override
	public PlanoAlimentar getById(Object id) throws Exception {
		return getByEntity(getDao().getById(id),functionLoadAlimentos);
	}
	
	public PlanoAlimentar getNe(PlanoAlimentar planoAlimentar) throws Exception {
		planoAlimentar.setAtendimento(AtendimentoBo.getInstance().getById(planoAlimentar.getAtendimento().getId()));
		
		int idade = planoAlimentar.getAtendimento().getFilaEsperaOcupacional().getEmpregado().getPessoa().getIdade();
		
		RespostaFichaColeta rPeso = planoAlimentar.getAtendimento().
			getFilaEsperaOcupacional().getFichaColeta().
			getRespostaFichaColetas().stream().filter(r -> {
				if (r.getPergunta().getCodigo().equals("0001") && 
						r.getPergunta().getGrupo().equals(GrupoPerguntaFichaColeta.EXAME_FISICO) )
					return true;
				return false;
			}).findFirst().get();
		RespostaFichaColeta rAltura = planoAlimentar.getAtendimento().
				getFilaEsperaOcupacional().getFichaColeta().
				getRespostaFichaColetas().stream().filter(r -> {
					if (r.getPergunta().getCodigo().equals("0002") && 
							r.getPergunta().getGrupo().equals(GrupoPerguntaFichaColeta.EXAME_FISICO) )
						return true;
					return false;
				}).findFirst().get();
		RespostaFichaColeta rNivelAtividade = planoAlimentar.getAtendimento().
				getFilaEsperaOcupacional().getFichaColeta().
				getRespostaFichaColetas().stream().filter(r -> {
					if (r.getPergunta().getCodigo().equals("0018") && 
							r.getPergunta().getGrupo().equals(GrupoPerguntaFichaColeta.EXAME_FISICO) )
						return true;
					return false;
				}).findFirst().get();
		if ( rPeso == null || rAltura == null || rNivelAtividade == null ) return planoAlimentar;
		float peso = Float.parseFloat(rPeso.getConteudo().replace(",", "."));
		float altura = Float.parseFloat(rAltura.getConteudo().replace(",", "."));
		
		float tmb;
		float get;
		if ( planoAlimentar.getAtendimento().
				getFilaEsperaOcupacional().getEmpregado().getPessoa().getSexo().equals(Sexo.getInstance().FEMININO)) {
			tmb = (float) (655 + (9.6 * peso) + (1.8 * altura) - (4.7 * idade));
		} else {
			tmb = (float) (66.5 + (13.7 * peso) + (5.0 * altura) - (6.8 * idade));
		}
		
		planoAlimentar.setTmb(tmb);
		
		get = (float) (tmb*getFatorAtividade(rNivelAtividade.getConteudo())); 
		
		planoAlimentar.setNe(Float.parseFloat(new DecimalFormat(".##").format(get).replace(",", ".")));
		
		return planoAlimentar;
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
	
	public PlanoAlimentar verifyPlanoAlimentar(PlanoAlimentarFilter filter) throws Exception {
		
		filter.setPageNumber(1);
		filter.setPageSize(1);
		
		PagedList<PlanoAlimentar> planoAlimentares = getList(filter);
		
		if(planoAlimentares.getTotal() > 0)
			return planoAlimentares.getList().get(0);
		else
			return new PlanoAlimentar();	
	}
	
	@SuppressWarnings({ "resource" })
	public String getPlanoPDF(PlanoAlimentar planoAlimentar) throws Exception {
		
		planoAlimentar.setAtendimento(AtendimentoBo.getInstance().getById(planoAlimentar.getAtendimento().getId()));
		//OBTER O HTML DO RELATÓRIO		
		StringBuilder html = new StringBuilder();
		String line;
		
		URI uriDoc = new URI(Helper.getProjectPath().replace("/WEB-INF/classes", "")+"REPORT/planoAlimentar.html");
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(uriDoc.getPath()));
		
		while((line = bufferedReader.readLine()) != null) {
			html.append(line);
		}
		
		bufferedReader.close();
		
		//CONFIGURAR DIRETÓRIO DOS PDFs
		URI pdfUri;
		File pdf;
		URI uri = new URI(Helper.getProjectPath()+"planoAlimentar/");
		File file = new File(uri.getPath());
		file.mkdirs();
		
		StringReplacer stringReplacer = new StringReplacer(html.toString());
		URI logoUri = new URI(Helper.getProjectPath().replace("/WEB-INF/classes", "")+"IMAGE/petrobras.png");
		stringReplacer = stringReplacer.replace("logoPetrobras", logoUri.getPath());
		
		stringReplacer = stringReplacer
			.replace("[NOME]", Objects.toString(planoAlimentar.getAtendimento().getFilaEsperaOcupacional().getEmpregado().getPessoa().getNome()))
			.replace("[OBJETIVO]", Objects.toString(planoAlimentar.getObjetivo()));
		StringBuilder refeicoes = new StringBuilder();	
		
		planoAlimentar.getRefeicoes().forEach(r -> {
			
			refeicoes.append("<tr><td rowspan='"+r.getItens().size()+"'align='center'>"+ r.getNome()+"</td>");
			
			r.getItens().forEach(i->{
				refeicoes.append("<td>"+ i.getAlimento().getNome()+"</td>");
				refeicoes.append("<td>"+ i.getQuantidade()+" "+i.getMedidaCaseira().getDescricao()+"</td>");
			
				refeicoes.append("<td>"+ (i.getObservacao() != null ? i.getObservacao() : "")+"</td>");
			});
			refeicoes.append("</tr>");
		});
		stringReplacer = stringReplacer.replace("REFEICOES", refeicoes.toString());
		
		pdfUri = new URI(uri.getPath()+"/"+Objects.toString(
				planoAlimentar.getAtendimento().getFilaEsperaOcupacional().getEmpregado().getPessoa().getNome().replaceAll(" ", ""))+".pdf");
		
		pdf = new File(pdfUri.getPath());
		OutputStream stream = new FileOutputStream(pdf);
		Document doc = new Document();
		PdfWriter.getInstance(doc, stream);
		doc.open();
		
		HTMLWorker htmlWorker = new HTMLWorker(doc);
		htmlWorker.parse(new StringReader(stringReplacer.result()));
		
		doc.close();
		stream.close();
		
		byte[] pdfArray = new byte[(int) pdf.length()];
		new FileInputStream(pdf).read(pdfArray);
		return Base64.getEncoder().encodeToString(pdfArray);
	}
}
