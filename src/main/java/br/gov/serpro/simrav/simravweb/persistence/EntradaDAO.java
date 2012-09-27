package br.gov.serpro.simrav.simravweb.persistence;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.serpro.simrav.simravweb.config.SQLAdaConexaoConfig;
import br.gov.serpro.simrav.simravweb.domain.EntradaDTO;
import br.gov.serpro.simrav.simravweb.exception.SimravConsultasException;

@PersistenceController
public class EntradaDAO {
	
	@Inject
	private SQLAdaConexaoConfig sqlAdaConfig;
	
	public List<EntradaDTO> lerEntradaPorPeriodo(String dtInicio, String dtFim) {
		
		SQLAdaAdapter sa = new SQLAdaAdapter();
		
		List<EntradaDTO> lista = new ArrayList<EntradaDTO>();
				
		StringBuilder comando = new StringBuilder();
		
		comando.append("SELECT AB AC AD AE AS AV ");
		comando.append("FROM "+ sqlAdaConfig.getFileAdabasEntrada() );
		//comando.append(" WHERE AV = " + pendencia.toString());
		comando.append(" WHERE AS >= " + dtInicio + " AND AS <= " + dtFim);
		try {
			sa.connect( sqlAdaConfig.getUsuario() , sqlAdaConfig.getSenha(), sqlAdaConfig.getPerfil(), sqlAdaConfig.getHost());
			
			System.out.println("Coneccao Efetuada!"); 
			
			System.out.println("DtInicio: " + dtInicio + " dtFim: " + dtFim);
			
			int quantidade = sa.command(comando.toString());
			
			System.out.println("Comando Executado! " + quantidade);
			
			for (int i = 0; i < quantidade; i++) {
				try {
					EntradaDTO dto = this.gerarEntradaDTO(sa.getNext());
					lista.add(dto);
				} catch (SimravConsultasException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			
			
			}
		}catch(SQLAdaException e){
			if (e.getMessage().contains("SSA607")) {
				System.out.println("erro na conexão sqlada. " + e.getMessage());
				
			} else {
				System.out.println("erro na execução do sqlada. " + e.getMessage());
				
			}
			
		}finally{
			sa.disconnect();
		}
		
		
		return lista;
		
	}
	
	private EntradaDTO gerarEntradaDTO(String adaStr) throws SimravConsultasException{
		
		// System.out.println(adaStr);
		
		EntradaDTO entrada = null;
		
		try {
			entrada = new EntradaDTO();
			Cursor cursor = new Cursor(adaStr,TamanhoCamposEntrada.getTamanhoEntrada());
			
			entrada.setCodigoTransacaoE(cursor.nextToken(TamanhoCamposEntrada.CO_TRANSACAO_E));
			entrada.setCodigoCorrelacaoE(cursor.nextToken(TamanhoCamposEntrada.CO_CORRELACAO_E));
			entrada.setNomeServicoE(cursor.nextToken(TamanhoCamposEntrada.NO_SERVICO_E));
			entrada.setNumeroCanalE(cursor.nextToken(TamanhoCamposEntrada.NM_CANAL_E));
			
			DateFormat dfm = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String strData = cursor.nextTokenNumeric(TamanhoCamposEntrada.DA_HORA_REGISTRO_E);
			
			System.out.println("Data: " + strData);
			
			if (strData.startsWith("+")){
				strData = strData.substring(1, 16);
				System.out.println("Data Conv: " + strData);
				
			}
			
			entrada.setDataHoraRegistroE(dfm.parse(strData));
			
			entrada.setIndicadorPendenciaE(Integer.parseInt(cursor.nextTokenNumeric(TamanhoCamposEntrada.IN_PENDENCIA_E)));
			
			
		} catch (Exception e){
			throw new SimravConsultasException("Montando o DTO", e);
		}
		
		return entrada;
		
	}

}
