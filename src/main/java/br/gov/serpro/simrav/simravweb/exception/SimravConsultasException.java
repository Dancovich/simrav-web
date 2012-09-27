package br.gov.serpro.simrav.simravweb.exception;

public class SimravConsultasException extends Exception {
	private static final long serialVersionUID = 1L;

	public SimravConsultasException() {
	}
	
	public SimravConsultasException(String msg, Exception e) {
		super(msg, e);
	}
	
	public SimravConsultasException(String msg) {
		super(msg);
	}

	public SimravConsultasException(Exception e) {
		super(e);
	}
}
