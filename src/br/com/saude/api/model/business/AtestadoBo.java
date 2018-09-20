package br.com.saude.api.model.business;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.GenericReportBo;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.AtestadoBuilder;
import br.com.saude.api.model.creation.builder.example.AtestadoExampleBuilder;
import br.com.saude.api.model.creation.factory.entity.EmailFactory;
import br.com.saude.api.model.entity.dto.AtestadoDto;
import br.com.saude.api.model.entity.filter.AtestadoFilter;
import br.com.saude.api.model.entity.filter.ServicoFilter;
import br.com.saude.api.model.entity.po.Atestado;
import br.com.saude.api.model.entity.po.Servico;
import br.com.saude.api.model.persistence.AtestadoDao;
import br.com.saude.api.model.persistence.report.AtestadoReport;
import br.com.saude.api.util.constant.GrupoServico;
import br.com.saude.api.util.constant.StatusAtestado;
import br.com.saude.api.util.constant.StatusTarefa;

public class AtestadoBo
		extends GenericBo<Atestado, AtestadoFilter, AtestadoDao, AtestadoBuilder, AtestadoExampleBuilder>
		implements GenericReportBo<AtestadoDto> {

	protected Function<AtestadoBuilder, AtestadoBuilder> functionLoadRegime;
	
	private static AtestadoBo instance;

	private AtestadoBo() {
		super();
	}

	public static AtestadoBo getInstance() {
		if (instance == null)
			instance = new AtestadoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadCat().loadProfissionalRealizouVisita().loadHomologacaoAgestado().loadRegime();
		};
		
		this.functionLoadRegime = builder -> {
			return builder.loadRegime();
		};
	}

	@Override
	protected Atestado getById(Object id, Function<AtestadoBuilder, AtestadoBuilder> loadFunction)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, Exception {
		return getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}
	
	public PagedList<Atestado> getListRegime(AtestadoFilter filter) throws Exception {
		return super.getList(getDao().getListRegime(getExampleBuilder(filter).example()), this.functionLoadRegime);
	}
	
	public List<AtestadoDto> getAtestados() throws Exception {
		return AtestadoReport.getInstance().getAtestados();
	}

	public boolean verifyDeadlineAtestado(Atestado atestado) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(atestado.getInicio());
		Calendar today = Calendar.getInstance();
		today.setTime(Helper.getToday());

		calendar = FeriadoBo.getInstance().getValidDates(calendar, 4);

		if (calendar.before(today))
			return false;
		return true;
	}

	@SuppressWarnings("static-access")
	@Override
	public Atestado save(Atestado atestado) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {

		atestado = configureAtestado(atestado);

		if (atestado.getHomologacaoAtestado() != null)
			atestado.getHomologacaoAtestado().setAtestado(atestado);

		if (atestado.getStatus().equals(StatusAtestado.ANALISE_ADMINISTRATIVA))
			atestado.getTarefa().setStatus(StatusTarefa.getInstance().ABERTA);
		else if (atestado.getStatus().equals(StatusAtestado.HOMOLOGADO) && atestado.isAtestadoFisicoRecebido()
				&& atestado.isLancadoSap())
			atestado.getTarefa().setStatus(StatusTarefa.getInstance().CONCLUIDA);
		else if (atestado.getStatus().equals(StatusAtestado.RECUSADO))
			atestado.getTarefa().setStatus(StatusTarefa.getInstance().CONCLUIDA);
		else
			atestado.getTarefa().setStatus(StatusTarefa.getInstance().EXECUCAO);

		Map<Integer, Integer> anexo = atestado.getAnexo();
		atestado = super.save(atestado);
		atestado.setAnexo(anexo);
		saveFiles(atestado);
		return atestado;
	}

	public Atestado solicitacaoServico(Atestado atestado) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {

		if (atestado.getNumeroDias() >= 5) {
			atestado.setPresencial(true);
		}

		atestado.setDataSolicitacao(Helper.getToday());
		atestado.setStatus(StatusAtestado.ANALISE_ADMINISTRATIVA);
		atestado.setDataSolicitacao(Helper.getToday());
		atestado = definirLimites(atestado);

		String servico = atestado.getTarefa().getServico().getNome();
		atestado.setTarefa(null);
		atestado.setMotivoRecusa(null);

		Map<Integer, Integer> anexo = atestado.getAnexo();
		Map<Integer, Integer> anexoRelatorioMedico = atestado.getAnexoRelatorioMedico();
		atestado = super.save(atestado);

		EmailFactory emailFactory = EmailFactory.newInstance();
		emailFactory.assunto(servico)
				.conteudo("Prezado(a),</br></br> " + "informamos que o seu atestado " + atestado.getId()
						+ " está em ANÁLISE ADMINISTRATIVA. "
						+ "</br>Obs: Atestados de 1 dia devem ser tratados no âmbito da atribuição gerencial. </br>");
		EmailBo.getInstance().save(emailFactory.get());

		atestado.setAnexo(anexo);
		atestado.setAnexoRelatorioMedico(anexoRelatorioMedico);
		saveFiles(atestado);
		return atestado;
	}

	@SuppressWarnings("static-access")
	public Atestado configureAtestado(Atestado atestado) throws Exception {
		ServicoFilter servicoFilter = new ServicoFilter();
		servicoFilter.setCodigo("0001");
		servicoFilter.setGrupo(GrupoServico.getInstance().HOMOLOGACAO_ATESTADO);
		servicoFilter.setPageNumber(1);
		servicoFilter.setPageSize(1);
		Servico servico = ServicoBo.getInstance().getList(servicoFilter).getList().get(0);
		servico = ServicoBo.getInstance().getById(servico.getId());

		if (servico.getAtividades() == null)
			throw new Exception(
					"Não existe atividade cadastrada para o serviço. Por favor, contacte o administrador do sistema.");

		atestado.getTarefa().setServico(servico);
		atestado.getTarefa().setEquipe(servico.getAtividades().get(0).getEquipe());

		return atestado;
	}

	public Atestado definirLimites(Atestado atestado) throws Exception {
		Calendar hoje = Calendar.getInstance();

		int qtdDiasUteis = dataLimite(hoje);
		int qtdMax = 9;
		if (!atestado.isPresencial())
			qtdMax = 7;
		if (qtdDiasUteis <= qtdMax)
			qtdMax = qtdDiasUteis;

		atestado.setLimiteAuditar(1);
		atestado.setLimiteHomologar(3);
		atestado.setLimiteLancar(1);

		while (atestado.getLimiteAuditar() + atestado.getLimiteHomologar() + atestado.getLimiteLancar() < qtdMax) {
			if (atestado.isPresencial() && atestado.getLimiteHomologar() < 5 && qtdMax > 5)
				atestado.setLimiteHomologar(atestado.getLimiteHomologar() + 1);
			else if (atestado.getLimiteAuditar() < 2)
				atestado.setLimiteAuditar(atestado.getLimiteAuditar() + 1);
			else
				atestado.setLimiteLancar(atestado.getLimiteLancar() + 1);
		}

		return atestado;
	}

	private int dataLimite(Calendar amanha) throws Exception {
		amanha.setTime(Helper.getToday());
		amanha.add(Calendar.DATE, 1);

		Calendar mesSeguinte = Calendar.getInstance();
		mesSeguinte.setTime(Helper.getToday());
		mesSeguinte.add(Calendar.MONTH, 1);
		mesSeguinte.set(Calendar.DATE, 10);

		int days = FeriadoBo.getInstance().getDaysBetweenDates(amanha, mesSeguinte);

		return days;
	}

	@SuppressWarnings("resource")
	public String getAnexo(int id) throws Exception {
		URI uri = new URI(Helper.getProjectPath() + "atestado/anexo/"+ id + ".pdf");

		File anexoPath = new File(uri.getPath());

		byte[] pdfArray = new byte[(int) anexoPath.length()];

		try {
			new FileInputStream(anexoPath).read(pdfArray);
		} catch (FileNotFoundException e) {
			throw new Exception("Arquivo não encontrado para esse atestado.");
		} catch (IOException e) {
			throw new Exception("Erro no servidor. Por favor, contacte o administrador do sistema.");
		}

		return Base64.getEncoder().encodeToString(pdfArray);
	}

	@SuppressWarnings({ "resource", "unused" })
	private Atestado loadFiles(Atestado atestado) throws URISyntaxException {

		URI uri = new URI(Helper.getProjectPath() + "atestado/anexo/"+ atestado.getId() + ".zip");

		File anexoPath = new File(uri.getPath());

		try {
			FileInputStream fileInputStreamReader = new FileInputStream(anexoPath);
			byte[] anexoArray = new byte[(int) anexoPath.length()];
			fileInputStreamReader.read(anexoArray);
			atestado.setAnexoBase64(Base64.getEncoder().encodeToString(anexoArray));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return atestado;
	}

	@SuppressWarnings("unlikely-arg-type")
	private void saveFiles(Atestado atestado) throws URISyntaxException, IOException {
		if (atestado.getAnexo() != null) {

			URI uri = new URI(Helper.getProjectPath()+ "atestado/anexo/" + atestado.getId() + ".pdf");

			File file = new File(uri.getPath());

			file.getParentFile().mkdirs();

			byte[] anexoArray = new byte[atestado.getAnexo().size()];

			for (int i = 0; i < atestado.getAnexo().size(); i++) {
				anexoArray[i] = new Integer(atestado.getAnexo().get(i + "")).byteValue();
			}

			InputStream in;
			FileOutputStream stream = new FileOutputStream(file);
			byte[] buffer = new byte[1024];

			in = new ByteArrayInputStream(anexoArray);

			int len;
			while ((len = in.read(buffer)) > 0) {
				stream.write(buffer, 0, len);
			}

			in.close();
			stream.close();

		}
		if (atestado.getAnexoRelatorioMedico() != null) {

			URI uriRM = new URI(Helper.getProjectPath() + "atestado/relatorio/" + atestado.getId() + ".pdf");

			File fileRM = new File(uriRM.getPath());

			fileRM.getParentFile().mkdirs();

			byte[] anexoArrayRM = new byte[atestado.getAnexoRelatorioMedico().size()];

			for (int i = 0; i < atestado.getAnexoRelatorioMedico().size(); i++) {
				anexoArrayRM[i] = new Integer(atestado.getAnexoRelatorioMedico().get(i + "")).byteValue();
			}

			InputStream inRM;
			FileOutputStream streamRM = new FileOutputStream(fileRM);
			byte[] bufferRM = new byte[1024];

			inRM = new ByteArrayInputStream(anexoArrayRM);

			int lenRM;
			while ((lenRM = inRM.read(bufferRM)) > 0) {
				streamRM.write(bufferRM, 0, lenRM);
			}

			inRM.close();
			streamRM.close();
		}
	}

}