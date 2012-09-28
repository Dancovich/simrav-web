package br.gov.serpro.simrav.simravweb.domain.mensageria;


public class ServidorJMSDTO {
	
	private FilaDTO[] filas;
	private String[] addresses;
	
	public FilaDTO[] getFilas() {
		return filas;
	}
	public void setFilas(FilaDTO[] filas) {
		this.filas = filas;
	}
	public String[] getAddresses() {
		return addresses;
	}
	public void setAddresses(String[] addresses) {
		this.addresses = addresses;
	}

}
