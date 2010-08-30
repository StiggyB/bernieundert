package a02;

/*
 * Eine Klasse zum Errechnen des Osterdatums nach
 * gregorianischem Kalender
 * @author Martin Slowikowski
 */

import java.util.Calendar;

public class EasterCalc {

	
	// Methode zur Berechnung des Osterdatums für ein übergebenes Jahr,
	// das Datum wird nach dem Algorithmus von Butcher berechnet.
	public Calendar easterDateCalc(int year) {

		int a, b, c, d, e, f, g, h, i, k, l, p, m;
		int mon, tag;

		Calendar cal = Calendar.getInstance();

		a = year % 19;
		b = year / 100;
		c = year % 100;
		d = b / 4;
		e = b % 4;
		f = (b + 8) / 25;
		g = (b - f + 1) / 3;
		h = (19 * a + b - d - g + 15) % 30;
		i = c / 4;
		k = c % 4;
		l = (32 + 2 * e + 2 * i - h - k) % 7;
		m = (a + 11 * h + 22 * l) / 451;
		mon = (h + l - 7 * m + 114) / 31;
		p = (h + l - 7 * m + 114) % 31;
		tag = p + 1;

		cal.set(Calendar.DAY_OF_MONTH, tag);
		cal.set(Calendar.MONTH, mon - 1);
		cal.set(Calendar.YEAR, year);
		return cal;
	}

	// Methode, die berechnet, wann Ostern wieder so früh ist, wie an dem übergebenen Datum
	public int earlyEasterNext(Calendar cal) {

		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH);
		int d = cal.get(Calendar.DAY_OF_MONTH);
		
		int newYear = y;
		Calendar cal2;
		do {
			newYear++;
			cal2 = easterDateCalc(newYear);
		} while (m != cal2.get(Calendar.MONTH)
				|| d != cal2.get(Calendar.DAY_OF_MONTH));

		return newYear;

	}
	
	// Methode, die berechnet, wann Ostern das letzte Mal so früh war, wie an dem übergebenen Datum
	public int earlyEasterPrev(Calendar cal) {

		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH);
		int d = cal.get(Calendar.DAY_OF_MONTH);
		
		int newYear = y;
		Calendar cal2;
		do {
			newYear--;
			cal2 = easterDateCalc(newYear);
		} while (m != cal2.get(Calendar.MONTH) || d != cal2.get(Calendar.DAY_OF_MONTH));

		return newYear;

	}

}
