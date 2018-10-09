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
import br.com.saude.api.model.business.FeriadoBo;
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
				Helper.getProjectPath().replace("file:/", "") 
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

			cat.setOrgao((String) row[0]);
			cat.setRta((String) row[1]);
			cat.setMes((String) Helper.getStringMonth((int)((double)row[2])));
			cat.setDiaSemana(Helper.getStringDiaSemana( (int)((double) row[3]) ));
			cat.setInstalacao((String) row[4]);
			cat.setProprioContratado((String) row[5]);
			cat.setEmpresa((String) row[6]);
			cat.setCnae((String) row[7]);
			cat.setGrauRiscoEmpresa((int) row[8]);
			cat.setNomeAcidentado((String) row[9]);
			cat.setRegimeTrabalho((String) row[10]);
			
			if ( row[11] != null )
				cat.setJornadaTrabalho((int) row[11]);
			
			cat.setAcidenteComSemAfastamento((boolean) row[12] ? "COM" : "SEM");
			cat.setParteCorpoAtingida((String) row[13]);
			cat.setHoraAcidente((String) row[14]);
			cat.setMunicipioAcidente((String) row[15]);
			cat.setNumeroSisin((String) row[16]);
			cat.setClassificacaoSisin((int) row[17]);
			cat.setDataAcidente((String) row[18]);
			cat.setDataEmissaoCat((String) row[19]);
			
			if ( row[20] != null && row[21] != null ) {
				Calendar calendar1 = Calendar.getInstance();
				calendar1.setTime((Date) row[20]);
				Calendar calendar2 = Calendar.getInstance();
				calendar2.setTime((Date) row[21]);
				cat.setDiasAtraso(FeriadoBo.getInstance().getDaysBetweenDates(calendar1, calendar2));
				cat.setPrazo(cat.getDiasAtraso() > 1 ? "FORA DO PRAZO" : "DENTRO DO PRAZO");
			}
			
			cat.setClassificacaoGravidade((String) row[22]);
			cat.setDataAvaliacaoMedica((String) row[23]);
			cat.setRegistroSd2000((boolean) row[24]);
			cat.setCatSd2000((boolean) row[25]);
			
			if ( (Date) row[50] != null )
				cat.setSituacaoAvaliacaoMedica("AVALIAÇÃO REALIZADA");
			cat.setDataLiberacao((String) row[26]);
			cat.setPendenciaCorrecaoCat((boolean) row[27]);
			
			cat.setJustificativaAtrasoEmissaoCat((String) row[28]);
			cat.setNumeroCartaMultaEmpresa((String) row[29]);
			cat.setTipoAcidente((String) row[30]);
			cat.setTipoCat((String) row[31]);
			cat.setDiagnosticoProvavel((String) row[32]);
			cat.setAgenteCausador((String) row[33]);
			cat.setComunicavelSus((boolean) row[34]);
			cat.setFerimentoGraveConformeANP((boolean) row[35]);
			cat.setNumeroCat((String) row[36]);
			cat.setLocalizacaoLesao((String) row[37]);
			cat.setNaturezaLesao((String) row[38]);

			//DIAS AFASTAMENTO, INTERVALO AFASTAMENTO, HORAS PERDIDAS, SALARIO HORA, CUSTO ACIDENTE
			cat.setDiasAfastamento((boolean) row[12] ? String.valueOf((int) row[51]) : "");
			int diasAfastamento = !cat.getDiasAfastamento().equals("") ? Integer.parseInt(cat.getDiasAfastamento()) : 0;
			if ( diasAfastamento == 0 )
				cat.setIntervaloAfastamento("SEM AFASTAMENTO");
			else if ( diasAfastamento > 0 && diasAfastamento <= 2 )
				cat.setIntervaloAfastamento("01 a 02 DIAS");
			else if ( diasAfastamento > 2 && diasAfastamento <= 7 )
				cat.setIntervaloAfastamento("03 a 07 DIAS");
			else if ( diasAfastamento > 7 && diasAfastamento <= 15 )
				cat.setIntervaloAfastamento("07 a 15 DIAS");
			else if ( diasAfastamento > 15 && diasAfastamento <= 30 )
				cat.setIntervaloAfastamento("15 a 30 DIAS");
			else if ( diasAfastamento > 30 )
				cat.setIntervaloAfastamento("MAIOR QUE 30 DIAS");
			if ( cat.getJornadaTrabalho() > 0 )
				cat.setSalarioHora( ((double) row[47] / 22) / cat.getJornadaTrabalho() );
		
			cat.setCodigoCartaSindicato((String) row[39]);
			cat.setClassificacaoAnomalia((int) row[40]);
			cat.setDataComunicacaoSindicato((String) row[41]);
			
			//DIAS ACIDENTE COMUNICACAO SINDICATO
			if ( (Date) row[21] != null && (Date) row[52] != null ) {
				Calendar calendar1 = Calendar.getInstance();
				calendar1.setTime((Date) row[21]);
				Calendar calendar2 = Calendar.getInstance();
				calendar2.setTime((Date) row[52]);
				try {
					cat.setDiasAcidenteComunicacaoSindicato(
							String.valueOf(FeriadoBo.getInstance().getDaysBetweenDates(calendar1, calendar2)));
				} catch (Exception e) {
					cat.setDiasAcidenteComunicacaoSindicato(
							String.valueOf(0));
				}
			}
			
			cat.setJustificativaAtrasoEmissaoCarta((String) row[42]);
			cat.setDataNascimento((String) row[43]);
			if ( (Date) row[50] != null )
				cat.setIdade(Helper.calculateIdade((Date) row[50]).getYears());

			cat.setSexo((String) row[44]);
			cat.setGrauInstrucao((String) row[45]);
			cat.setEstadoCivil((String) row[46]);
			cat.setRemuneracao( (double) row[47] );
			cat.setCargo((String) row[48]);
			
			cat.setAto1((String) row[53]);
			cat.setAto2((String) row[54]);
			cat.setAto3((String) row[55]);
			cat.setAto4((String) row[56]);
			cats.add(cat);
		}

		return cats;
	}

}
