package br.com.saude.api.model.persistence.report;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.HibernateHelper;
import br.com.saude.api.model.business.RiscoPotencialBo;
import br.com.saude.api.model.entity.dto.AcompanhamentoSastAcaoDto;
import br.com.saude.api.model.entity.dto.AcompanhamentoSastEmpregadoDto;
import br.com.saude.api.model.entity.dto.AcompanhamentoSastEquipeDto;
import br.com.saude.api.model.entity.dto.AcompanhamentoSastIndicadorDto;

public class AcompanhamentoSastReport {
	private static AcompanhamentoSastReport instance;
	
	private AcompanhamentoSastReport() {
		
	}
	
	public static AcompanhamentoSastReport getInstance() {
		if(instance == null)
			instance = new AcompanhamentoSastReport();
		return instance;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<AcompanhamentoSastEmpregadoDto> getAcompanhamentoSasts(String abreviacaoEquipeAcolhimento, 
			int idProfissional, int anoRisco) 
			throws Exception {
		
		BufferedReader in = new BufferedReader(new FileReader(Helper.getProjectPath().replace("file:/", "")
				+"br/com/saude/api/model/persistence/sql/QueryAcompanhamentoSast.sql"));
		String str;
		StringBuffer query = new StringBuffer();
		
		while ((str = in.readLine()) != null) {
			query.append(str + "\n ");
		}
		in.close();
		
		int index = query.indexOf("[ABREVIACAO_EQUIPE_ACOLHIMENTO]");
		query.replace(index, index+31, "'"+abreviacaoEquipeAcolhimento+"'");
		
		index = query.indexOf("[ID_PROFISSIONAL]");
		query.replace(index, index+17, String.valueOf(idProfissional));
		
		index = query.indexOf("[ANO_RISCO]");
		query.replace(index, index+11, String.valueOf(anoRisco));
		
		List<AcompanhamentoSastEmpregadoDto> acompanhamentoSastEmpregados = new ArrayList<AcompanhamentoSastEmpregadoDto>();

		Session session = HibernateHelper.getSession();
		List<Object[]> list = new ArrayList<Object[]>();
		
		try {
			list = session.createSQLQuery(query.toString()).list();
		}catch (Exception ex) {
			throw ex;
		} finally {
			HibernateHelper.close(session);
		}
		
		AcompanhamentoSastEmpregadoDto acompanhamentoSastEmpregado = null;
		
		for(Object[] row : list) {
			
			if ( acompanhamentoSastEmpregado == null || !acompanhamentoSastEmpregado.getNome().equals((String)row[1])) {
				acompanhamentoSastEmpregado = new AcompanhamentoSastEmpregadoDto();
				setAcompanhamentoSastEmpregado(acompanhamentoSastEmpregado, row);
				acompanhamentoSastEmpregados.add(acompanhamentoSastEmpregado);
			} else if ( !acompanhamentoSastEmpregado.getEquipes()
					.stream().reduce((first, second) -> second).get().getNome().equals((String)row[5]) )
				setAcompanhamentoSastEquipe(acompanhamentoSastEmpregado.getEquipes(), row);
			else if ( !acompanhamentoSastEmpregado.getEquipes()
					.stream().reduce((first, second) -> second).get().getIndicadores()
					.stream().reduce((first, second) -> second).get().getIndicador().
					equals((String)row[6]) )
				setAcompanhamentoSastIndicador(acompanhamentoSastEmpregado.getEquipes()
					.stream().reduce((first, second) -> second).get().getIndicadores(), row);
			else if ( acompanhamentoSastEmpregado.getEquipes()
					.stream().reduce((first, second) -> second).get().getIndicadores()
					.stream().reduce((first, second) -> second).get().getAcoes().size() > 0 &&
					acompanhamentoSastEmpregado.getEquipes()
						.stream().reduce((first, second) -> second).get().getIndicadores()
						.stream().reduce((first, second) -> second).get().getAcoes()
						.stream().reduce((first, second) -> second).get().getIdAcao() != (int)row[9] )
				setAcompanhamentoSastAcao(acompanhamentoSastEmpregado.getEquipes()
					.stream().reduce((first, second) -> second).get().getIndicadores()
					.stream().reduce((first, second) -> second).get().getAcoes(), row);
			else if ( acompanhamentoSastEmpregado.getEquipes()
					.stream().reduce((first, second) -> second).get().getIndicadores()
					.stream().reduce((first, second) -> second).get().getAcoes().size() > 0 )
				setAcompanhamentoSastAcompanhamento(acompanhamentoSastEmpregado.getEquipes()
					.stream().reduce((first, second) -> second).get().getIndicadores()
					.stream().reduce((first, second) -> second).get().getAcoes()
					.stream().reduce((first, second) -> second).get().getAcompanhamentos(), row);
		}
		 
		return acompanhamentoSastEmpregados;
	}
	
	public void setAcompanhamentoSastEmpregado(AcompanhamentoSastEmpregadoDto acompanhamentoSastEmpregado, Object[] row) {
		acompanhamentoSastEmpregado.setGerencia((String)row[0]);
		acompanhamentoSastEmpregado.setNome((String)row[1]);
		acompanhamentoSastEmpregado.setMatricula((String)row[2]);
		acompanhamentoSastEmpregado.setStatusRPSat(RiscoPotencialBo.getInstance().getStatusRPSat((double)row[3]));
		acompanhamentoSastEmpregado.setStatusRisco((String)row[4]);
		acompanhamentoSastEmpregado.setEquipes(new ArrayList<AcompanhamentoSastEquipeDto>());
		setAcompanhamentoSastEquipe(acompanhamentoSastEmpregado.getEquipes(), row);
	}
	
	public void setAcompanhamentoSastEquipe(List<AcompanhamentoSastEquipeDto> acompanhamentoSastEquipes, Object[] row) {
		if ( (String)row[5] != null ) {
			AcompanhamentoSastEquipeDto acompanhamentoSastEquipe = new AcompanhamentoSastEquipeDto();
			acompanhamentoSastEquipe.setNome((String)row[5]);
			acompanhamentoSastEquipes.add(acompanhamentoSastEquipe);
			acompanhamentoSastEquipe.setIndicadores(new ArrayList<AcompanhamentoSastIndicadorDto>());
			setAcompanhamentoSastIndicador(acompanhamentoSastEquipe.getIndicadores(), row);
		}
	}
	
	public void setAcompanhamentoSastIndicador(List<AcompanhamentoSastIndicadorDto> acompanhamentoSastIndicadores, Object[] row) {
		if ( (String)row[6] != null ) {
			AcompanhamentoSastIndicadorDto acompanhamentoSastIndicador = new AcompanhamentoSastIndicadorDto();
			acompanhamentoSastIndicador.setIndicador((String)row[6]);
			acompanhamentoSastIndicador.setDiagnostico((String)row[7]);
			acompanhamentoSastIndicador.setIntervencao((String)row[8]);
			acompanhamentoSastIndicador.setIdTriagem((int)row[9]);
			acompanhamentoSastIndicadores.add(acompanhamentoSastIndicador);
			acompanhamentoSastIndicador.setAcoes(new ArrayList<AcompanhamentoSastAcaoDto>());
			setAcompanhamentoSastAcao(acompanhamentoSastIndicador.getAcoes(), row);
		}
	}
	
	public void setAcompanhamentoSastAcao(List<AcompanhamentoSastAcaoDto> acompanhamentoSastAcoes, Object[] row) {
		if ( (String)row[11] != null ) { 
			AcompanhamentoSastAcaoDto acompanhamentoSastAcao = new AcompanhamentoSastAcaoDto();
			acompanhamentoSastAcao.setIdAcao((int)row[10]);
			acompanhamentoSastAcao.setAcao((String)row[11]);
			acompanhamentoSastAcao.setTipoAcao((String)row[12]);
			acompanhamentoSastAcao.setContatoAcao((String)row[13]);
			acompanhamentoSastAcao.setStatusAcao((String)row[14]);
			acompanhamentoSastAcoes.add(acompanhamentoSastAcao);
			acompanhamentoSastAcao.setAcompanhamentos(new ArrayList<String>());
			setAcompanhamentoSastAcompanhamento(acompanhamentoSastAcao.getAcompanhamentos(), row);
		}
	}
	
	public void setAcompanhamentoSastAcompanhamento(List<String> acompanhamentos, Object[] row) {
		if ( (String)row[15] != null )
			acompanhamentos.add((String)row[15]);
	}
}
