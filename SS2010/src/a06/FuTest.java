package a06;

/**
 * 
 * @author Martin Slowikowski
 * 
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FuTest {

	private Fu fu;
	private Fara fara;

	@Before
	public void setUp() throws Exception {
		this.fu = new Fu("Fu", "Fara");
		this.fara = fu.getFara();
	}

	@Test
	public void testFuStringString() {
		Fu fu2 = new Fu("FaraTest", "FuTest");
		Fara fara2 = fu2.getFara();
		assertTrue(fara2 == fara2.getFu().getFara());
		assertTrue(fu2.getFara() == fara2);
	}

	@Test
	public void testFuFaraString() {
		Fu newFu = new Fu(fara, "Fu2", "Fara2");
		Fara newFara = newFu.getFara();

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
	public void testGetFara() {
		assertTrue(fu.getFara() == fara);
	}

	@Test
	public void testSetName() {
		fu.setName("FuChange");
		assertTrue(fu.getName() == "FuChange");
		assertTrue(fara.getFu().getName() == "FuChange");
	}

	@Test
	public void testGetName() {
		assertTrue(fu.getName() == "Fu");
		assertTrue(fara.getName() == "Fara");
		assertTrue(fara.getFu().getName() == "Fu");
		assertTrue(fu.getFara().getName() == "Fara");

	}

}
