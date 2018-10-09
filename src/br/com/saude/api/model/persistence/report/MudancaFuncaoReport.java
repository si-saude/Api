package br.com.saude.api.model.persistence.report;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.HibernateHelper;
import br.com.saude.api.model.entity.dto.MudancaFuncaoDto;

public class MudancaFuncaoReport {

	private static MudancaFuncaoReport instance;

	private MudancaFuncaoReport() {

	}

	public static MudancaFuncaoReport getInstance() {
		if (instance == null)
			instance = new MudancaFuncaoReport();
		return instance;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<MudancaFuncaoDto> getMudancaFuncoes() throws Exception {

		BufferedReader in = new BufferedReader(new FileReader(
				Helper.getProjectPath().replace("file:/", "") 
					+ "br/com/saude/api/model/persistence/sql/QueryMudancaFuncao.sql"));
		String str;
		StringBuffer query = new StringBuffer();
		while ((str = in.readLine()) != null) {
			query.append(str + "\n ");
		}
		in.close();

		List<MudancaFuncaoDto> mudancaFuncoes = new ArrayList<MudancaFuncaoDto>();
		Session session = HibernateHelper.getSession();

		List<Object[]> list = new ArrayList<Object[]>();
		
		try {
			list = session.createSQLQuery(query.toString()).list();
		} catch (Exception ex) {
			throw ex;
		} finally {
			HibernateHelper.close(session);
		}
		
		MudancaFuncaoDto mudancaFuncao = null;

		for (Object[] row : list) {
			mudancaFuncao = new MudancaFuncaoDto();
			
			if ( (Date) row[0] != null ) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime((Date) row[0]);
				mudancaFuncao.setDataInicioTarefaHigiene(
						calendar.get(Calendar.DATE)+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR));
			}
			
			mudancaFuncao.setNomeEmpregado((String) row[1]);
			mudancaFuncao.setMatriculaEmpregado((String) row[2]);
			mudancaFuncao.setGerenciaAtual((String) row[3]);
			mudancaFuncao.setGerenciaFutura((String) row[4]);
			mudancaFuncao.setGheAtual((String) row[5]);
			mudancaFuncao.setGheFuturo((String) row[6]);
			mudancaFuncao.setGheeAtual((String) row[7]);
			mudancaFuncao.setGheeFuturo((String) row[8]);
			mudancaFuncao.setFuncaoAtual((String) row[9]);
			mudancaFuncao.setFuncaoFuturo((String) row[10]);
			mudancaFuncao.setRegimeAtual((String) row[11]);
			mudancaFuncao.setRegimeFuturo((String) row[12]);
			mudancaFuncao.setBaseAtual((String) row[13]);
			mudancaFuncao.setBaseFuturo((String) row[14]);
			mudancaFuncao.setEnfaseAtual((String) row[15]);
			mudancaFuncao.setEnfaseFuturo((String) row[16]);
			mudancaFuncao.setStatusTarefaHigiene((String) row[17]);
			mudancaFuncao.setStatusTarefaErgonomia((String) row[18]);
			mudancaFuncao.setStatusTarefaMedicina((String) row[19]);
			mudancaFuncao.setAtividades((String) row[20]);
			
			mudancaFuncoes.add(mudancaFuncao);
		}

		return mudancaFuncoes;
	}

}
