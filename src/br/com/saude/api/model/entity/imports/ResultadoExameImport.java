package br.com.saude.api.model.entity.imports;

import java.util.Date;
import java.util.Map;

public class ResultadoExameImport {
	
	private Map<Integer,Integer> arquivo;

	private String arquivoBase64;
	
	private Date inicio;
	
	private Date fim;
	
	public Map<Integer, Integer> getArquivo() {
		return arquivo;
	}

	public void setArquivo(Map<Integer, Integer> arquivo) {
		this.arquivo = arquivo;
	}

	public String getArquivoBase64() {
		return arquivoBase64;
	}

	public void setArquivoBase64(String arquivoBase64) {
		this.arquivoBase64 = arquivoBase64;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}
	
}
