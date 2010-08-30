package a02;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class EasterCalcTest {
	
	private EasterCalc easterTest;

	@Before
	public void setUp() throws Exception {
		this.easterTest = new EasterCalc();
	}

	
	// Da im Kalender die Monate nicht, wie üblich, bei 1 anfangen sondern
	// bei 0 ist der März == 2 und der April == 3 :)
	@Test
	public void testEasterDateCalc() {
		// Test für das Jahr 2008
		Calendar cal = easterTest.easterDateCalc(2008);
		int y1 = cal.get(Calendar.YEAR);
		int m1 = cal.get(Calendar.MONTH);
		int d1 = cal.get(Calendar.DAY_OF_MONTH);
		assertTrue(y1 == 2008);
		assertTrue(m1 == 2);
		assertTrue(d1 == 23);
		
		// Test für das Jahr 2055
		Calendar cal2 = easterTest.easterDateCalc(2055);
		int y2 = cal2.get(Calendar.YEAR);
		int m2 = cal2.get(Calendar.MONTH);
		int d2 = cal2.get(Calendar.DAY_OF_MONTH);
		assertTrue(y2 == 2055);
		assertTrue(m2 == 3);
		assertTrue(d2 == 18);
		
		// Test für das Jahr 1583
		Calendar cal3 = easterTest.easterDateCalc(1583);
		int y3 = cal3.get(Calendar.YEAR);
		int m3 = cal3.get(Calendar.MONTH);
		int d3 = cal3.get(Calendar.DAY_OF_MONTH);
		assertTrue(y3 == 1583);
		assertTrue(m3 == 3);
		assertTrue(d3 == 10);
	}

	@Test
	public void testEarlyEasterNext() {
		// Test für das Jahr 2008
		Calendar cal = easterTest.easterDateCalc(2008);
		int nextYear = easterTest.earlyEasterNext(cal);
		assertTrue(nextYear == 2160);
	}

	@Test
	public void testEarlyEasterPrev() {
		// Test für das Jahr 2008
		Calendar cal = easterTest.easterDateCalc(2008);
		int lastYear = easterTest.earlyEasterPrev(cal);
		assertTrue(lastYear == 1913);
	}

}
