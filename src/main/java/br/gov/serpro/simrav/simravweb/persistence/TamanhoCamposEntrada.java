package br.gov.serpro.simrav.simravweb.persistence;

import java.lang.reflect.Field;

public class TamanhoCamposEntrada {
	public static final int CO_TRANSACAO_E = 20;
	public static final int CO_CORRELACAO_E = 20;
	public static final int NO_SERVICO_E = 60;
	public static final int NM_CANAL_E = 10;
	public static final int DA_HORA_REGISTRO_E = 16;
	// public static final int DA_HORA_LEITURA_E = 16;
	public static final int IN_PENDENCIA_E = 1;
	
	public static final int SINAIS = 2;
	
	public static int getTamanhoEntrada() throws IllegalArgumentException, IllegalAccessException {
	    Field[] fs = TamanhoCamposEntrada.class.getFields();
	    int t = 0;
		for (Field f : fs) {
			t += f.getInt(f);
		}
		return t;
	}
	
	

}
