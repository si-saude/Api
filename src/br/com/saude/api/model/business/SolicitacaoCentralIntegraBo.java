package br.com.saude.api.model.business;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.List;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.GenericReportBo;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.SolicitacaoCentralIntegraBuilder;
import br.com.saude.api.model.creation.builder.example.SolicitacaoCentralIntegraExampleBuilder;
import br.com.saude.api.model.entity.dto.SolicitacaoCentralIntegraDto;
import br.com.saude.api.model.entity.filter.EquipeFilter;
import br.com.saude.api.model.entity.filter.SolicitacaoCentralIntegraFilter;
import br.com.saude.api.model.entity.po.SolicitacaoCentralIntegra;
import br.com.saude.api.model.persistence.SolicitacaoCentralIntegraDao;
import br.com.saude.api.model.persistence.report.SolicitacaoCentralIntegraReport;
import br.com.saude.api.util.constant.StatusSolicitacao;
import br.com.saude.api.util.constant.StatusTarefa;

public class SolicitacaoCentralIntegraBo
		extends GenericBo<SolicitacaoCentralIntegra, SolicitacaoCentralIntegraFilter, SolicitacaoCentralIntegraDao, SolicitacaoCentralIntegraBuilder, SolicitacaoCentralIntegraExampleBuilder> 
		implements GenericReportBo<SolicitacaoCentralIntegraDto>
{

	private static SolicitacaoCentralIntegraBo instance;

	private SolicitacaoCentralIntegraBo() {
		super();
	}

	public static SolicitacaoCentralIntegraBo getInstance() {
		if (instance == null)
			instance = new SolicitacaoCentralIntegraBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoad = builder -> {
			return builder.loadTarefa().loadTipoSolicitacao();
		};

	}

	@Override
	public PagedList<SolicitacaoCentralIntegra> getList(SolicitacaoCentralIntegraFilter filter) throws Exception {
		return super.getList(getDao().getList(getExampleBuilder(filter).example()), this.functionLoad);
	}

	@Override
	public List<SolicitacaoCentralIntegra> getSelectList(SolicitacaoCentralIntegraFilter filter) throws Exception {
		return super.getSelectList(
				this.getDao().getList(this.getExampleBuilder(filter).exampleSelectList()).getList(),
				this.functionLoad);
	}

	@Override
	public SolicitacaoCentralIntegra getById(Object id) throws Exception {
		SolicitacaoCentralIntegra solicitacao = getByEntity(getDao().getById(id), this.functionLoad);
		solicitacao = loadFiles(solicitacao);
		return solicitacao;
	}

	@Override
	public SolicitacaoCentralIntegra save(SolicitacaoCentralIntegra solicitacao) throws Exception {
		solicitacao = super.save(solicitacao);
		saveFiles(solicitacao);
		return solicitacao;
	}
	
	public List<SolicitacaoCentralIntegraDto> getSolicitacoesCentralIntegra(String cpf) throws IOException{
		return SolicitacaoCentralIntegraReport.getInstance().getSolicitacaoCentralIntegra(cpf);
	}
	
	public SolicitacaoCentralIntegra registrarSolicitacao(SolicitacaoCentralIntegra solicitacao) throws Exception {
		solicitacao.setAbertura(Helper.getNow());
		solicitacao.setStatus(StatusSolicitacao.getInstance().ABERTO);
		EquipeFilter equipeFilter = new EquipeFilter();
		equipeFilter.setPageNumber(1);
		equipeFilter.setPageSize(1);
		equipeFilter.setAbreviacao("INT");
		solicitacao.getTarefa().setEquipe(EquipeBo.getInstance().getList(equipeFilter).getList().get(0));
		solicitacao.getTarefa().setStatus(StatusTarefa.getInstance().ABERTA);
		
		solicitacao = super.save(solicitacao);
		saveFiles(solicitacao);
		return solicitacao;
	}
	
	@SuppressWarnings("resource")
	public String getAnexo(int id) throws Exception {
		URI uri = new URI(getClass().getProtectionDomain().getCodeSource().getLocation().toString() + "solicitacaoCentralIntegra/anexo/"
				+ id + ".zip");
		
		File anexoPath = new File(uri.getPath());

		byte[] zipArray = new byte[(int) anexoPath.length()];
		
		try {
			new FileInputStream(anexoPath).read(zipArray);
		} catch (FileNotFoundException e) {
			throw new Exception("Arquivo não encontrado para essa solicitação.");
		} catch (IOException e) {
			throw new Exception("Erro no servidor. Por favor, contacte o administrador do sistema.");
		}
		
		return Base64.getEncoder().encodeToString(zipArray);
	}

	@SuppressWarnings("resource")
	private SolicitacaoCentralIntegra loadFiles(SolicitacaoCentralIntegra solicitacao) throws URISyntaxException {

		URI uri = new URI(getClass().getProtectionDomain().getCodeSource().getLocation().toString() + "solicitacaoCentralIntegra/anexo/"
				+ solicitacao.getId() + ".zip");

		File anexoPath = new File(uri.getPath());

		try {
			FileInputStream fileInputStreamReader = new FileInputStream(anexoPath);
			byte[] anexoArray = new byte[(int) anexoPath.length()];
			fileInputStreamReader.read(anexoArray);
			solicitacao.setAnexoBase64(Base64.getEncoder().encodeToString(anexoArray));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return solicitacao;
	}

	@SuppressWarnings("unlikely-arg-type")
	private void saveFiles(SolicitacaoCentralIntegra solicitacao) throws URISyntaxException, IOException {
		if (solicitacao.getAnexo() != null) {
			
			URI uri = new URI(getClass().getProtectionDomain().getCodeSource().getLocation().toString()
					+ "solicitacaoCentralIntegra/anexo/" + solicitacao.getId() + ".zip");
			File file = new File(uri.getPath());
			file.getParentFile().mkdirs();
			
			
			byte[] anexoArray = new byte[solicitacao.getAnexo().size()];
			
			for (int i = 0; i < solicitacao.getAnexo().size(); i++) {
				anexoArray[i] = new Integer(solicitacao.getAnexo().get(i + "")).byteValue();
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
	}

}
