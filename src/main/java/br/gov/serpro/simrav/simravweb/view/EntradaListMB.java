package br.gov.serpro.simrav.simravweb.view;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.serpro.simrav.simravweb.business.EntradaBC;
import br.gov.serpro.simrav.simravweb.domain.EntradaDTO;
import br.gov.serpro.simrav.simravweb.domain.mensageria.FilaDTO;
import br.gov.serpro.simrav.simravweb.domain.mensageria.ServidorJMSDTO;
import br.gov.serpro.simrav.simravweb.persistence.mensageria.MensageriaDAO;

@ViewController
public class EntradaListMB {
	
	@Inject
	private EntradaBC entradaBC;
	
	@Inject
	private MensageriaDAO mensageria;
	
	private List<EntradaDTO> listaEntradas;
	
	private Date dataInicioFiltro;
	private Date dataFimFiltro;
	
	
	public String filtrarEntradas(){
		this.listaEntradas = entradaBC.lerEntradaPorPeriodo(dataInicioFiltro, dataFimFiltro);
		
		//TESTE!!!!!!!!!!!!
		ServidorJMSDTO dto = mensageria.pesquisarFilasJMS();
		for (FilaDTO fila : dto.getFilas()){
			System.out.println(fila.getName());
		}
		
		return null;
	}
	
	public Date getDataInicioFiltro() {
		return dataInicioFiltro;
	}
	public void setDataInicioFiltro(Date dataInicioFiltro) {
		this.dataInicioFiltro = dataInicioFiltro;
	}
	public Date getDataFimFiltro() {
		return dataFimFiltro;
	}
	public void setDataFimFiltro(Date dataFimFiltro) {
		this.dataFimFiltro = dataFimFiltro;
	}
	public List<EntradaDTO> getListaEntradas() {
		return listaEntradas;
	}
	
	public Date getMinimoDataFiltro(){
		GregorianCalendar cal = new GregorianCalendar();
		int mesAtual = cal.get(Calendar.MONTH);
		cal.set(Calendar.MONTH, mesAtual-1);
		
		return cal.getTime();
	}
	
}
