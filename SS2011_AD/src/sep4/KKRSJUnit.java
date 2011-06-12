package sep4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class KKRSJUnit {

	private KKRS kkrs;

	@Before
	public void setUp() throws Exception {
		kkrs = new KKRS();
	}

	// Ermitteln Sie aus der Funktion logische (abstrakte)
	// Testdaten und konkrete Testdaten.

	/*
	 * logische Testdaten: 
	 * Schüler: Alter <= 18 && Status Schüler -> Loge: 6€, Parkett 4€ 
	 * Azubi: Alter <= 25 && Status Azubi -> Loge: 6€, Parkett 4€
	 * Student: Alter <= 25 && Status Student -> Loge: 6€, Parkett 4€
	 * Normal: Alter >= 1 && Status Normal -> Loge: 12€, Parkett 10€
	 * Rentner: Alter >= 65 && Status Rentner -> Loge: 6€, Parkett 4€
	 */

	// konkrete Testfälle, ungültige Eingaben
	@Test(expected = Exception.class)
	public void testBadAgeAndPerson() throws Exception {
		kkrs.getPrice(-1, null, Kategorie.LOGE);
	}

	@Test(expected = Exception.class)
	public void testBadAgeAndCategory() throws Exception {
		kkrs.getPrice(-1, Person.NORMAL, null);
	}

	@Test(expected = Exception.class)
	public void testBadAgeAndPersonAndCategory() throws Exception {
		kkrs.getPrice(-1, null, null);
	}

	@Test(expected = Exception.class)
	public void testAgeZero() throws Exception {
		kkrs.getPrice(0, Person.NORMAL, Kategorie.LOGE);
	}

	@Test
	public void testValidEntries() throws Exception {
		assertEquals(kkrs.getPrice(1, Person.NORMAL, Kategorie.LOGE), 12);

	}

	// Definieren Sie Testdaten mit Hilfe von Äquivalenzklassen.
	// Definieren Sie Testdaten mit Hilfe von Grenzwertanalyse.
	@Test
	public void testThresholdsInEquivalenceClassStudent() throws Exception {
		assertEquals(kkrs.getPrice(24, Person.STUDENT, Kategorie.LOGE), 6);
		assertEquals(kkrs.getPrice(25, Person.STUDENT, Kategorie.LOGE), 6);
		assertEquals(kkrs.getPrice(25, Person.STUDENT, Kategorie.PARKETT), 4);
		assertFalse(kkrs.getPrice(26, Person.STUDENT, Kategorie.LOGE) == 6);
		assertFalse(kkrs.getPrice(26, Person.STUDENT, Kategorie.PARKETT) == 4);
	}

	public void testThresholdsInEquivalenceClassAzubi() throws Exception {
		assertEquals(kkrs.getPrice(24, Person.AZUBI, Kategorie.LOGE), 6);
		assertEquals(kkrs.getPrice(25, Person.AZUBI, Kategorie.LOGE), 6);
		assertEquals(kkrs.getPrice(25, Person.AZUBI, Kategorie.PARKETT), 4);
		assertFalse(kkrs.getPrice(26, Person.AZUBI, Kategorie.LOGE) == 6);
		assertFalse(kkrs.getPrice(26, Person.AZUBI, Kategorie.PARKETT) == 4);
	}

	public void testThresholdsInEquivalenceClassSenior() throws Exception {
		assertEquals(kkrs.getPrice(66, Person.SENIOR, Kategorie.LOGE), 6);
		assertEquals(kkrs.getPrice(65, Person.SENIOR, Kategorie.LOGE), 6);
		assertFalse(kkrs.getPrice(64, Person.SENIOR, Kategorie.LOGE) == 6);
		assertEquals(kkrs.getPrice(65, Person.SENIOR, Kategorie.PARKETT), 4);
		assertFalse(kkrs.getPrice(64, Person.SENIOR, Kategorie.PARKETT) == 4);
	}

	public void testThresholdsInEquivalenceClassSchueler() throws Exception {
		assertEquals(kkrs.getPrice(18, Person.SCHUELER, Kategorie.LOGE), 6);
		assertFalse(kkrs.getPrice(19, Person.SCHUELER, Kategorie.LOGE) == 6);
		assertFalse(kkrs.getPrice(19, Person.SCHUELER, Kategorie.PARKETT) == 4);
		assertEquals(kkrs.getPrice(18, Person.SCHUELER, Kategorie.PARKETT), 4);
	}

	public void testThresholdsInEquivalenceClassNormal() throws Exception {
		assertFalse(kkrs.getPrice(1, Person.NORMAL, Kategorie.LOGE) == 6);
		assertFalse(kkrs.getPrice(18, Person.NORMAL, Kategorie.LOGE) == 6);
		assertFalse(kkrs.getPrice(65, Person.NORMAL, Kategorie.LOGE) == 6);
		assertFalse(kkrs.getPrice(1, Person.NORMAL, Kategorie.PARKETT) == 4);
		assertFalse(kkrs.getPrice(18, Person.NORMAL, Kategorie.PARKETT) == 4);
		assertFalse(kkrs.getPrice(65, Person.NORMAL, Kategorie.PARKETT) == 4);
		assertEquals(kkrs.getPrice(1, Person.NORMAL, Kategorie.PARKETT), 10);
		assertEquals(kkrs.getPrice(18, Person.NORMAL, Kategorie.PARKETT), 10);
		assertEquals(kkrs.getPrice(65, Person.NORMAL, Kategorie.PARKETT), 10);
		assertEquals(kkrs.getPrice(1, Person.NORMAL, Kategorie.LOGE), 12);
		assertEquals(kkrs.getPrice(18, Person.NORMAL, Kategorie.LOGE), 12);
		assertEquals(kkrs.getPrice(65, Person.NORMAL, Kategorie.LOGE), 12);
	}
	
	//Bauen Sie absichtlich Fehler in Ihre Implementation ein (z.B. Vorzeichenfehler, 
	//„<“ „>=“ Fehler etc.) und schauen Sie, welche Ihrer Testfälle diese Fehler abfangen können.
}
