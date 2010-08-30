package a06;

/**
 * 
 * @author Martin Slowikowski
 * 
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FaraTest {

	private Fu fu;
	private Fara fara;

	@Before
	public void setUp() throws Exception {
		this.fara = new Fara("Fara", "Fu");
		this.fu = fara.getFu();
	}

	@Test
	public void testFaraStringString() {
		Fara fara2 = new Fara("FaraTest", "FuTest");
		Fu fu2 = fara2.getFu();
		assertTrue(fara2 == fara2.getFu().getFara());
		assertTrue(fu2.getFara() == fara2);
	}

	@Test
	public void testFaraFuString() {
		Fara newFara = new Fara(fu, "Fara2", "Fu2");
		Fu newFu = newFara.getFu();

		// ursprüngliches Objektpaar noch vorhanden
		assertTrue(fara.getName() == "Fara");
		assertTrue(fu.getName() == "Fu");
		assertTrue(fu.getFara() == fara);

		// neues Objektpaar erstellt
		assertTrue(newFara.getName() == "Fara2");
		assertTrue(newFu.getName() == "Fu2");
		assertTrue(newFu.getFara() == newFara);
	}

	@Test
	public void testGetFu() {
		assertTrue(fara.getFu() == fu);
	}

	@Test
	public void testSetName() {
		fara.setName("FaraChange");
		assertTrue(fara.getName() == "FaraChange");
		assertTrue(fu.getFara().getName() == "FaraChange");
	}

	@Test
	public void testGetName() {
		assertTrue(fu.getName() == "Fu");
		assertTrue(fara.getName() == "Fara");
		assertTrue(fara.getFu().getName() == "Fu");
		assertTrue(fu.getFara().getName() == "Fara");
	}

}
