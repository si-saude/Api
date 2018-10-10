package br.com.saude.api.model.persistence.report;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.HibernateHelper;
import br.com.saude.api.model.business.RiscoPotencialBo;
import br.com.saude.api.model.entity.dto.AcaoDto;
import br.com.saude.api.model.entity.dto.AcompanhamentoSastDto;

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
	public List<AcompanhamentoSastDto> getAcompanhamentoSasts(String abreviacaoEquipeAcolhimento, 
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
		query.replace(index, index+30, abreviacaoEquipeAcolhimento);
		
		index = query.indexOf("[ID_PROFISSIONAL]");
		query.replace(index, index+16, String.valueOf(idProfissional));
		
		index = query.indexOf("[ANO_RISCO]");
		query.replace(index, index+10, String.valueOf(anoRisco));
		
		List<AcompanhamentoSastDto> acompanhamentoSasts = new ArrayList<AcompanhamentoSastDto>();

		Session session = HibernateHelper.getSession();
		List<Object[]> list = new ArrayList<Object[]>();
		
		try {
			list = session.createSQLQuery(query.toString()).list();
		}catch (Exception ex) {
			throw ex;
		} finally {
			HibernateHelper.close(session);
		}
		
		AcompanhamentoSastDto acompanhamentoSast = null;
		
		for(Object[] row : list) {
			
			if ( acompanhamentoSast == null || acompanhamentoSast.getIdTriagem() != (int)row[8]) {
				acompanhamentoSast = new AcompanhamentoSastDto();
				acompanhamentoSast.setGerencia((String)row[0]);
				acompanhamentoSast.setNome((String)row[1]);
				acompanhamentoSast.setMatricula((String)row[2]);
				acompanhamentoSast.setStatusRisco((String)row[3]);
				acompanhamentoSast.setStatusRPSat(RiscoPotencialBo.getInstance().getStatusRPSat((double)row[4]));
				acompanhamentoSast.setEquipe((String)row[5]);
				acompanhamentoSast.setIndicador((String)row[6]);
				acompanhamentoSast.setDiagnostico((String)row[7]);
				acompanhamentoSast.setIntervencao((String)row[8]);
				acompanhamentoSast.setIdTriagem((int)row[9]);
				acompanhamentoSast.setIdAcao((int)row[10]);
				acompanhamentoSast.setDescricaoAcao((String)row[11]);
				acompanhamentoSast.setTipoAcao((String)row[12]);
				acompanhamentoSast.setContatoAcao((String)row[13]);
				acompanhamentoSast.setDescricaoAcompanhamento((String)row[15]);
				acompanhamentoSast.setAcoes( new ArrayList<AcaoDto>() );
				AcaoDto acaoDto = new AcaoDto();
				acaoDto.setDescricao((String)row[11]);
				acaoDto.setAcompanhamentos(new ArrayList<String>());
				acaoDto.getAcompanhamentos().add((String)row[15]);
				acompanhamentoSast.getAcoes().add(acaoDto);
			} else {
				if ( acompanhamentoSast.getIdAcao() != (int)row[10] ) {
					AcaoDto acaoDto = new AcaoDto();
					acaoDto.setDescricao((String)row[11]);
					acaoDto.setAcompanhamentos(new ArrayList<String>());
					acaoDto.getAcompanhamentos().add((String)row[15]);
					acaoDto.setStatus((String)row[14]);
					acompanhamentoSast.getAcoes().add(acaoDto);
				} else {
					acompanhamentoSast.getAcoes()
						.get(acompanhamentoSast.getAcoes().size()-1)
						.getAcompanhamentos().add((String)row[15]);
				}
			}
			
			acompanhamentoSasts.add(acompanhamentoSast);
		}
		
		return acompanhamentoSasts;
	}
}
