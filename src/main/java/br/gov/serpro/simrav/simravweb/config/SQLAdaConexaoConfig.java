package br.gov.serpro.simrav.simravweb.config;

import br.gov.frameworkdemoiselle.configuration.Configuration;

@Configuration(resource="simrav", prefix="simrav.sqlada")
public class SQLAdaConexaoConfig {
	
	private String usuario;
	private String senha;
	private String perfil;
	private String host;
	private String fileAdabasEntrada;
	private String fileAdabasSaida;


	public String getUsuario() {
		return usuario;
	}
	public String getSenha() {
		return senha;
	}
	public String getPerfil() {
		return perfil;
	}
	public String getHost() {
		return host;
	}
	public String getFileAdabasEntrada() {
		return fileAdabasEntrada;
	}
	public String getFileAdabasSaida() {
		return fileAdabasSaida;
	}
	
	

}
