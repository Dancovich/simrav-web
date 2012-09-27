package br.gov.serpro.simrav.simravweb.domain;

import java.util.Date;

public class EntradaDTO {
	private String codigoTransacaoE;
	private String codigoCorrelacaoE;
	private String nomeServicoE;
	private String numeroCanalE;
	private Date dataHoraRegistroE;
	private int indicadorPendenciaE;
	
	// private Date dataHoraLeituraE;
	
	public String getCodigoCorrelacaoE() {
		return codigoCorrelacaoE;
	}
	public void setCodigoCorrelacaoE(String codigoCorrelacaoE) {
		this.codigoCorrelacaoE = codigoCorrelacaoE;
	}
	public String getNomeServicoE() {
		return nomeServicoE;
	}
	public void setNomeServicoE(String nomeServicoE) {
		this.nomeServicoE = nomeServicoE;
	}
	public String getNumeroCanalE() {
		return numeroCanalE;
	}
	public void setNumeroCanalE(String numeroCanalE) {
		this.numeroCanalE = numeroCanalE;
	}
	public Date getDataHoraRegistroE() {
		return dataHoraRegistroE;
	}
	public void setDataHoraRegistroE(Date dataHoraRegistroE) {
		this.dataHoraRegistroE = dataHoraRegistroE;
	}
	
	
	public String getCodigoTransacaoE() {
		return codigoTransacaoE;
	}
	public void setCodigoTransacaoE(String codigoTransacaoE) {
		this.codigoTransacaoE = codigoTransacaoE;
	}
	
	
	
	public int getIndicadorPendenciaE() {
		return indicadorPendenciaE;
	}
	public void setIndicadorPendenciaE(int indicadorPendenciaE) {
		this.indicadorPendenciaE = indicadorPendenciaE;
	}
	@Override
	public String toString() {
		return "EntradaDTO [codigoTransacaoE=" + codigoTransacaoE
				+ ", codigoCorrelacaoE=" + codigoCorrelacaoE
				+ ", nomeServicoE=" + nomeServicoE + ", numeroCanalE="
				+ numeroCanalE + ", dataHoraRegistroE=" + dataHoraRegistroE 
				+ ", indicadorPendenciaE=" + indicadorPendenciaE + "]";
	}
	
	
	

}
