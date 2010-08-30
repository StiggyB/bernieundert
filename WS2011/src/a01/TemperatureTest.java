package a01;
/**
 * 
 * JUnit Testfälle, um die Konstruktoren und Methoden zu testen
 * @author Martin Slowikowski
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TemperatureTest {

	private Temperature newTemp;
	private Temperature newTemp2;

	@Before
	public void setUp() throws Exception {
		this.newTemp = new Temperature(20);
	}

	@Test
	public void testTemperature() {
		this.newTemp2 = new Temperature();
		assertTrue(newTemp2.showTemp() == 0);
	}

	@Test
	public void testTemperatureDouble() {
		assertTrue(newTemp.showTemp() == 20);
		// alternativ
		assertEquals(20, newTemp.showTemp(), 0.00);
	}

	@Test
	public void testGetKelvin() {
		assertTrue(newTemp.getKelvin() == 293.15);
		// alternativ
		assertEquals(293.15, newTemp.getKelvin(), 0.00);
	}

	@Test
	public void testGetReaumur() {
		assertTrue(newTemp.getReaumur() == 16);
	}

	@Test
	public void testGetFahrenheit() {
		assertTrue(newTemp.getFahrenheit() == 68);
	}

	@Test
	public void testShowTemp() {
		assertTrue(newTemp.showTemp() == 20);
		// alternativ auf console ausgegeben
		System.out.println(newTemp.showTemp());
	}

}
