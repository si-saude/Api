package br.com.saude.api.model.persistence.report;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.com.saude.api.generic.HibernateHelper;
import br.com.saude.api.model.entity.dto.CatDto;

public class CatReport {

	private static CatReport instance;

	private CatReport() {

	}

	public static CatReport getInstance() {
		if (instance == null)
			instance = new CatReport();
		return instance;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<CatDto> getCats() throws Exception {

		BufferedReader in = new BufferedReader(new FileReader(
				getClass().getProtectionDomain().getCodeSource().getLocation().toString().replace("file:/", "")
						+ "br/com/saude/api/model/persistence/sql/QueryCat.sql"));
		String str;
		StringBuffer query = new StringBuffer();
		while ((str = in.readLine()) != null) {
			query.append(str + "\n ");
		}
		in.close();

		List<CatDto> cats = new ArrayList<CatDto>();
		Session session = HibernateHelper.getSession();

		List<Object[]> list = new ArrayList<Object[]>();
		
		try {
			list = session.createSQLQuery(query.toString()).list();
		} catch (Exception ex) {
			throw ex;
		} finally {
			HibernateHelper.close(session);
		}
		
		CatDto cat = null;

		for (Object[] row : list) {
			cat = new CatDto();

			cat.setNumero((String) row[0]);
			cat.setContratado((boolean) row[1]);
			cat.setNome((String) row[2]);
			cat.setDataNascimento((String) row[3]);
			cat.setCargo((String) row[4]);
			cat.setRegime((String) row[5]);
			cat.setCpf((String) row[6]);
			cat.setSexo((String) row[7]);
			cat.setRta((String) row[8]);
			cat.setInstalacao((String) row[9]);
			cat.setParteCorpoAtingida((String) row[10]);
			cat.setAgenteCausador((String) row[11]);
			cat.setNaturezaLesao((String) row[12]);
			cat.setGerencia((String) row[13]);
			cat.setClassificacaoSisin((int) row[14]);
			cat.setDiaHoraAcidente((String) row[15]);
			cat.setAfastamento((boolean) row[16]);
			cat.setDataEmissaoCat((String) row[17]);
			cat.setGravidade((String) row[18]);
			cat.setDataAvaliacaoMedica((String) row[19]);
			cat.setRegistoSd2000((boolean) row[20]);
			cat.setCatSd2000((boolean) row[21]);
			cat.setTipoAcidente((String) row[22]);
			cat.setTipoCat((String) row[23]);
			cat.setDiagnostico((String) row[24]);
			cat.setCodigoCartaSindicato((String) row[25]);
			cat.setComunicavelSus((boolean) row[26]);
			cat.setFerimentoGraveConformeAnp((boolean) row[27]);
			cat.setDataComunicacaoSindicato((String) row[28]);
			cat.setRemuneracao((float) row[29]);
			cat.setFornecedor((String) row[30]);

			cats.add(cat);
		}

		return cats;
	}

}
