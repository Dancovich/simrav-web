package br.gov.serpro.simrav.simravweb.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

import br.gov.serpro.simrav.simravweb.domain.BookmarkDTO;

@PersistenceController
public class BookmarkDAO extends JPACrud<BookmarkDTO, Long> {
	
	private static final long serialVersionUID = 1L;
	
}
