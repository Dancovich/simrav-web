package br.gov.serpro.simrav.simravweb.business;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

import br.gov.serpro.simrav.simravweb.domain.BookmarkDTO;
import br.gov.serpro.simrav.simravweb.persistence.BookmarkDAO;

@BusinessController
public class BookmarkBC extends DelegateCrud<BookmarkDTO, Long, BookmarkDAO> {
	
	private static final long serialVersionUID = 1L;
	
	@Startup
	@Transactional
	public void load() {
		if (findAll().isEmpty()) {
			insert(new BookmarkDTO("Demoiselle Portal", "http://www.frameworkdemoiselle.gov.br"));
			insert(new BookmarkDTO("Demoiselle SourceForge", "http://sf.net/projects/demoiselle"));
			insert(new BookmarkDTO("Twitter", "http://twitter.frameworkdemoiselle.gov.br"));
			insert(new BookmarkDTO("Blog", "http://blog.frameworkdemoiselle.gov.br"));
			insert(new BookmarkDTO("Wiki", "http://wiki.frameworkdemoiselle.gov.br"));
			insert(new BookmarkDTO("Bug Tracking", "http://tracker.frameworkdemoiselle.gov.br"));
			insert(new BookmarkDTO("Forum", "http://forum.frameworkdemoiselle.gov.br"));
			insert(new BookmarkDTO("SVN", "http://svn.frameworkdemoiselle.gov.br"));
			insert(new BookmarkDTO("Maven", "http://repository.frameworkdemoiselle.gov.br"));
			insert(new BookmarkDTO("Downloads", "http://download.frameworkdemoiselle.gov.br"));
		}
	}
	
}
