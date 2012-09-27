package br.gov.serpro.simrav.simravweb.persistence;

import java.io.IOException;

import SQLAda.SQLAdaJ;

public class SQLAdaAdapter {
	private SQLAdaJ sa = new SQLAdaJ();
	private int conexao = -1;
	private int tamanho;
	private String areaDados;

	@Override
	protected void finalize() {
		disconnect();
	}

	public void connect(String usuario, String senha, String aplicacao, String host) throws SQLAdaException {
		try {
			conexao = sa.SAConnect(usuario, senha, aplicacao, host);
			if (conexao < 0) {
				throw new SQLAdaException("[" + conexao + "] " + sa.strMsgErro);
			}
		} catch (IOException e) {
			throw new SQLAdaException(e);
		}
	}

	public void disconnect() {
		try {
			if (conexao >= 0) {
				sa.SADisconnect(conexao);
			}
		} catch (Exception e) { // fica quieto...
		}
	}

	public int command(String comando) throws SQLAdaException {
		int quantidade = sa.SACommand(conexao, comando);
		if (quantidade < 0) {
			throw new SQLAdaException("[" + quantidade + "] " + sa.strMsgErro);
		}
		if (comando.startsWith("EXEC") && sa.strArea.startsWith("O96026")) {
			throw new SQLAdaException(sa.strArea);
		}
		areaDados = sa.strArea;
		return quantidade;
	}

	public String getNext() throws SQLAdaException {
		tamanho = sa.SAGetNext(conexao);
		if (tamanho < 0) {
			throw new SQLAdaException("[" + tamanho + "] " + sa.strMsgErro);
		}
		return sa.strArea;
	}

	public int getTamanho() {
		return tamanho;
	}

	public String getAreaDados() {
		return areaDados;
	}
}
