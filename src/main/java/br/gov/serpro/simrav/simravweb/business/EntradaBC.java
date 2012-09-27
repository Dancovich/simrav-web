package br.gov.serpro.simrav.simravweb.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.serpro.simrav.simravweb.domain.EntradaDTO;
import br.gov.serpro.simrav.simravweb.persistence.EntradaDAO;

@BusinessController
public class EntradaBC {
	
	@Inject
	private EntradaDAO entradaDAO;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSS");
	
	public List<EntradaDTO> lerEntradaPorPeriodo(Date dtInicio, Date dtFim){
		return entradaDAO.lerEntradaPorPeriodo( dateFormat.format(dtInicio) , dateFormat.format(dtFim));
	}

}
