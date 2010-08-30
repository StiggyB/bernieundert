package a11;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InputOutput {

	public void printEmptyArrayError() {
		System.out.println("Keine Zeichen im Array ...");
		System.out.println("Bitte immer erst processTxtFile aufrufen!");
	}

	public void printVowelsCount(int vowelsCount) {
		System.out.println("Die Datei enth�lt " + vowelsCount + " Vokale.");
		System.out.println("Vokale: a,e,i,o,u,�,�,u");

	}

	public void printConsonantsCount(int consonantsCount) {
		System.out.println("Die Datei enth�lt " + consonantsCount + " Konsonanten.");
		System.out.println("Konsonanten: b,c,d,f,g,h,j,k,l,m,n,p,q,r,s,�,t,v,w,x,y,z");

	}

	public void printWordsVowelsConsonantsCount(int w, int v, int c, int ch) {
		System.out.println("Die Datei enth�lt:");
		System.out.println("W�rter: " + w);
		System.out.println("Zeichen: " + ch);
		System.out.println("Vokale: " + v);
		System.out.println("Konsonanten: " + c);
		System.out.println("\n" + ch + " Zeichen insgesamt, davon " + (v + c) + " Vokale oder Konsonanten,");
		System.out.println((ch - (v + c))
						+ " Zeichen sind keine Buchstaben oder Sonderzeichen (z.B.: � oder �.");
		System.out.println("Vokale: a,e,i,o,u,�,�,u");
		System.out.println("Konsonanten: b,c,d,f,g,h,j,k,l,m,n,p,q,r,s,�,t,v,w,x,y,z");

	}

	public void printFoundAnagrams(Map<String, Set<String>> anagrams) {
		if (anagrams.isEmpty()) {
			System.out.println("Keine Anagramme gefunden ...");
		} else {
			System.out.println("\nGefundene Anagramme:");
			for (Set<String> s : anagrams.values()) {
				System.out.println(s.toString());
			}
		}
	}

	public void printFoundPalindromes(List<String> palindromes) {
		if (palindromes.isEmpty()) {
			System.out.println("Keine Palindrome gefunden ....");
		} else {
			Collections.sort(palindromes);
			System.out.println("\nGefundene Palindrome:");
			System.out.println(palindromes);
		}
	}

}
