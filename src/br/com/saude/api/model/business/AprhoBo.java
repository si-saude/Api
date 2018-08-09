package br.com.saude.api.model.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.generic.StringReplacer;
import br.com.saude.api.model.creation.builder.entity.AprhoBuilder;
import br.com.saude.api.model.creation.builder.example.AprhoExampleBuilder;
import br.com.saude.api.model.entity.filter.AprhoEmpregadoFilter;
import br.com.saude.api.model.entity.filter.AprhoFilter;
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.filter.ServicoFilter;
import br.com.saude.api.model.entity.po.Aprho;
import br.com.saude.api.model.entity.po.AprhoEmpregado;
import br.com.saude.api.model.entity.po.Cargo;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.Gerencia;
import br.com.saude.api.model.entity.po.MudancaFuncao;
import br.com.saude.api.model.entity.po.Tarefa;
import br.com.saude.api.model.persistence.AprhoDao;
import br.com.saude.api.util.constant.GrupoServico;

public class AprhoBo extends GenericBo<Aprho, AprhoFilter, AprhoDao, AprhoBuilder, AprhoExampleBuilder> {

	private static AprhoBo instance;

	private AprhoBo() {
		super();
	}

	public static AprhoBo getInstance() {
		if (instance == null)
			instance = new AprhoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {

		this.functionLoadAll = builder -> {
			return builder.loadAprhoItens().loadAprhoEmpregados().loadElaboradores();
		};
	}

	@Override
	public Aprho getById(Object id) throws Exception {
		return getByEntity(getDao().getById(id), this.functionLoadAll);
	}

	@Override
	public Aprho save(Aprho aprho) throws Exception {

		List<AprhoEmpregado> aprhoEmpregados = new ArrayList<AprhoEmpregado>();

		if (aprho.getAprhoEmpregados() != null) {

			Aprho aprhoAux = aprho;
			aprho.getAprhoEmpregados().forEach(e -> {
				if (e.isAtual()) {

					// 1 - Configurar um objeto do tipo AprhoEmpregadoFilter, passando o empregado
					// atual,
					// para obter os registros ATUAIS (GetListAll)
					AprhoEmpregadoFilter filter = new AprhoEmpregadoFilter();
					filter.setPageNumber(1);
					filter.setPageSize(Integer.MAX_VALUE);
					filter.setEmpregado(new EmpregadoFilter());
					filter.getEmpregado().setId(e.getEmpregado().getId());
					filter.setAtual(new BooleanFilter());
					filter.getAtual().setValue(1);
					List<AprhoEmpregado> aprhoEmpregadosAux = new ArrayList<AprhoEmpregado>();
					try {
						aprhoEmpregadosAux = AprhoEmpregadoBo.getInstance().getListLoadAll(filter).getList();
					} catch (Exception e1) {

					}
					aprhoEmpregadosAux.forEach(x -> {
						if (x.getAprho().getId() != aprhoAux.getId())
							aprhoEmpregados.add(x);
					});
				}
			});

		}

		if (aprhoEmpregados.size() > 0) {
			aprhoEmpregados.forEach(e -> e.setAtual(false));
		}

		List<Empregado> empregados = aprhoEmpregados.stream().map(a -> a.getEmpregado())
				.filter(Helper.distinctByKey(e -> e.getId())).collect(Collectors.toList());

		aprho = super.save(aprho);
		AprhoEmpregadoBo.getInstance().saveList(aprhoEmpregados);

		if (empregados != null && empregados.size() > 0) {
			Aprho aprhoAux = aprho;
			MudancaFuncao mudancaFuncao = new MudancaFuncao();

			ServicoFilter servicoFilte = new ServicoFilter();
			servicoFilte.setPageNumber(1);
			servicoFilte.setPageSize(1);
			servicoFilte.setGrupo(GrupoServico.SAUDE_OPERACIONAL);
			servicoFilte.setCodigo("0005");

			Tarefa tarefa = new Tarefa();
			tarefa.setServico(ServicoBo.getInstance().getList(servicoFilte).getList().get(0));

			empregados.forEach(e -> {
				if ((e.getGhe() == null)|| e.getGhe().getId() != aprhoAux.getGhe().getId()) {
					tarefa.setCliente(e);
					mudancaFuncao.setGhe(aprhoAux.getGhe());
					mudancaFuncao.setTarefas(new ArrayList<Tarefa>());
					mudancaFuncao.getTarefas().add(tarefa);
					try {
						MudancaFuncaoBo.getInstance().registrarMudancaFuncao(mudancaFuncao);
					} catch (Exception e1) {

					}
				}

			});
		}
		return aprho;

	}

	// @SuppressWarnings({ "resource" })
	public String aprhoToPdf(Aprho aprho) throws Exception {

		// OBTER O HTML DO RELATÓRIO
		StringBuilder html = new StringBuilder();
		String line;

		URI uriDoc = new URI(getClass().getProtectionDomain().getCodeSource().getLocation().toString()
				.replace("/WEB-INF/classes", "") + "REPORT/aprho.html");

		BufferedReader bufferedReader = new BufferedReader(new FileReader(uriDoc.getPath()));

		while ((line = bufferedReader.readLine()) != null) {
			html.append(line);
		}

		bufferedReader.close();

		URI uri = new URI(getClass().getProtectionDomain().getCodeSource().getLocation().toString() + "aprho/");
		File file = new File(uri.getPath());
		file.mkdirs();
		StringReplacer stringReplacer = new StringReplacer(html.toString());
		//

		URI logoUri = new URI(getClass().getProtectionDomain().getCodeSource().getLocation().toString()
				.replace("/WEB-INF/classes", "") + "IMAGE/petrobras.png");

		File imgPathLogo = new File(logoUri.getPath());

		try {
			@SuppressWarnings("resource")
			FileInputStream fileInputStreamReader = new FileInputStream(imgPathLogo);
			byte[] logoArray = new byte[(int) imgPathLogo.length()];
			fileInputStreamReader.read(logoArray);

			stringReplacer = stringReplacer.replace("logoPetrobras",
					"data:image/png;base64," + Base64.getEncoder().encodeToString(logoArray));

		} catch (IOException e) {
			e.printStackTrace();
		}

		List<Gerencia> gerenciaSetoriais = aprho.getAprhoEmpregados().stream()
				.filter(Helper.distinctByKey(aE -> aE.getEmpregado().getGerencia().getId()))
				.map(aE -> aE.getEmpregado().getGerencia()).collect(Collectors.toList());

		gerenciaSetoriais.sort(new Comparator<Gerencia>() {
			public int compare(Gerencia arg0, Gerencia arg1) {
				return arg0.getCodigoCompleto().compareTo(arg1.getCodigoCompleto());
			}
		});
	
		List<Gerencia> gerencias = gerenciaSetoriais.stream()
				.filter(Helper.distinctByKey(g -> g.getGerencia() != null ? g.getGerencia().getId() : g.getId()))
				.collect(Collectors.toList());

		List<Gerencia> unidades = gerencias.stream()
				.filter(Helper.distinctByKey(g -> GerenciaBo.getInstance().getUnidade(g).getId()))
				.collect(Collectors.toList());
		
		String gerenciaSetoriaisString = String.join("</br>",  
				gerenciaSetoriais.stream().map(g->g.getCodigoCompleto()).collect(Collectors.toList()));
		
		String gerenciaString = String.join("</br>",  
				gerencias.stream().map(g->g.getGerencia() != null ? g.getGerencia().getCodigoCompleto() : g.getCodigoCompleto()).collect(Collectors.toList()));
		
		String unidadesString = String.join("</br>",  
				unidades.stream().map(g-> GerenciaBo.getInstance().getUnidade(g).getCodigoCompleto()).collect(Collectors.toList()));			
		
		List<Cargo> cargo = aprho.getAprhoEmpregados().stream()
				.filter(Helper.distinctByKey(aE -> aE.getEmpregado().getCargo().getId()))
				.map(g -> g.getEmpregado().getCargo()).collect(Collectors.toList());

		cargo.sort(new Comparator<Cargo>() {
			public int compare(Cargo arg0, Cargo arg1) {
				return arg0.getNome().compareTo(arg1.getNome());
			}
		});

		String cargosEmpregados = String.join("</br>",
				cargo.stream().map(aE -> aE.getNome()).collect(Collectors.toList()));

		String elaboradores = String.join("<p>", aprho.getElaboradores().stream().map(
				aE -> aE.getEmpregado().getPessoa().getNome() + "- Mat." + aE.getEmpregado().getMatricula() + "</p>")
				.collect(Collectors.toList()));

		String aprovador = aprho.getAprovador().getEmpregado().getPessoa().getNome() + "- Mat."
				+ aprho.getAprovador().getEmpregado().getMatricula();

		stringReplacer = stringReplacer.replace("gheNome", Objects.toString(aprho.getGhe().getNome()))
				.replace("revisao", Objects.toString(aprho.getRevisao()))
				.replace("empresa", Objects.toString(aprho.getEmpresa()))
				.replace("unidadeOrganizacional", unidadesString).replace("gerencia", gerenciaString)
				.replace("gerenciaSetorial", gerenciaSetoriaisString).replace("Cargo", cargosEmpregados)
				.replace("numeroTrabalhadores", Objects.toString(aprho.getAprhoEmpregados().stream().count()))
				.replace("apravadorMatricula", aprovador).replace("elaboradores", elaboradores);

		SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");

		String dataRevisao = sdfData.format(aprho.getDataRevisao());
		stringReplacer = stringReplacer.replace("dataRevisao", dataRevisao);

		String data = sdfData.format(aprho.getData());
		stringReplacer = stringReplacer.replace("dataCriacao", data);

		StringBuilder aprhoItens = new StringBuilder();	
		
		aprho.getAprhoItens().forEach(e -> {			
				aprhoItens.append("<tr><td align='center'>"+e.getAgenteRisco().getDescricao()+"</td>");
				aprhoItens.append("<td>"+e.getAtividade()+"</td>");
				aprhoItens.append("<td align='center'>"+e.getLocal()+"</td>");
				aprhoItens.append("<td align='center'>"+e.getFrequencia()+"</td>");
				aprhoItens.append("<td align='center'>"+e.getDuracao()+"</td>");
				aprhoItens.append("<td align='center'>"+(e.getFonteGeradora() != null ? e.getFonteGeradora().getDescricao() : "")+"</td>");
				aprhoItens.append("<td align='center'>"+e.getMeioPropagacao()+"</td>");
				aprhoItens.append("<td align='center'>"+e.getPossivelDanoSaude().getDescricao()+"</td>");
				aprhoItens.append("<td align='center'>"+(e.getCategoriaRisco() !=null ? e.getCategoriaRisco().getDescricao() : "")+"</td>");
				aprhoItens.append("<td align='center'>"+e.getMedidaControle()+"</td>");
				aprhoItens.append("<td align='center'>"+e.getAvaliacaoEficacia()+"</td></tr>");			
		});
		stringReplacer = stringReplacer.replace("aprhoItens", aprhoItens.toString());

		StringBuilder aprhoEmpregados = new StringBuilder();

		aprho.getAprhoEmpregados().stream().filter(x -> x.isEntrevistado() == true).forEach(e -> {
			aprhoEmpregados.append("<tr><td align='left'>" + e.getEmpregado().getPessoa().getNome() + "</td>");
			aprhoEmpregados.append("<td>" + e.getEmpregado().getMatricula() + "</td>");
			aprhoEmpregados.append("<td align='center'></td>");
			aprhoEmpregados.append("<td align='center'></td></tr>");
		});
		stringReplacer = stringReplacer.replace("aprhoEmpregados", aprhoEmpregados.toString());

		return stringReplacer.result();
	}

}
