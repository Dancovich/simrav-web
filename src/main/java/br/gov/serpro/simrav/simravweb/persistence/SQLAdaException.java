package br.gov.serpro.simrav.simravweb.persistence;

public class SQLAdaException extends Exception {

	private static final long serialVersionUID = 2154481441223008325L;

	public SQLAdaException(Exception e) {
		super(e);
	}

	public SQLAdaException(String msg) {
		super(msg);
	}

}
