package br.gov.serpro.simrav.simravweb.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.gov.serpro.simrav.simravweb.domain.BookmarkDTO;

@RunWith(DemoiselleRunner.class)
public class BookmarkBCTest {

	@Inject
	private BookmarkBC bookmarkBC;
	
	@Before
	public void before() {
		for (BookmarkDTO bookmark : bookmarkBC.findAll()) {
			bookmarkBC.delete(bookmark.getId());
		}
	}

	@Test
	public void testLoad() {
		bookmarkBC.load();
		List<BookmarkDTO> listaBookmarks = bookmarkBC.findAll();
		assertNotNull(listaBookmarks);
		assertEquals(10, listaBookmarks.size());
	}
	
	@Test
	public void testInsert() {
		BookmarkDTO bookmark = new BookmarkDTO("Demoiselle Portal", "http://www.frameworkdemoiselle.gov.br");
		bookmarkBC.insert(bookmark);
		List<BookmarkDTO> listaBookmarks = bookmarkBC.findAll();
		assertNotNull(listaBookmarks);
		assertEquals(1, listaBookmarks.size());
	}
	
	@Test
	public void testDelete() {
		BookmarkDTO bookmark = new BookmarkDTO("Demoiselle Portal", "http://www.frameworkdemoiselle.gov.br");
		bookmarkBC.insert(bookmark);
		
		List<BookmarkDTO> listaBookmarks = bookmarkBC.findAll();
		assertNotNull(listaBookmarks);
		assertEquals(1, listaBookmarks.size());
		
		bookmarkBC.delete(bookmark.getId());
		listaBookmarks = bookmarkBC.findAll();
		assertEquals(0, listaBookmarks.size());
	}
	@Test
	public void testUpdate() {
		BookmarkDTO bookmark = new BookmarkDTO("Demoiselle Portal", "http://www.frameworkdemoiselle.gov.br");
		bookmarkBC.insert(bookmark);
		
		List<BookmarkDTO> listaBookmarks = bookmarkBC.findAll();
		BookmarkDTO bookmark2 = (BookmarkDTO)listaBookmarks.get(0);
		assertNotNull(listaBookmarks);
		assertEquals("Demoiselle Portal", bookmark2.getDescription());
		
		bookmark2.setDescription("Demoiselle Portal alterado");
		bookmarkBC.update(bookmark2);
		
		listaBookmarks = bookmarkBC.findAll();
		BookmarkDTO bookmark3 = (BookmarkDTO)listaBookmarks.get(0);
		assertEquals("Demoiselle Portal alterado", bookmark3.getDescription());
	}
}
