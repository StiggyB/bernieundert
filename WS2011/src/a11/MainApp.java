package a11;

import java.io.FileNotFoundException;

public class MainApp {

	public static void main(String[] args) {

		FileIO io = new FileIO();

		try {
			io.processTxtFile("Hugo.txt");
		} catch (FileNotFoundException e) {
			System.err.println("Datei nicht im Projektdir gefunden!");
		} catch (Exception e) {
			e.toString();
		}

		int vowels = io.countVowels();
		int consonants = io.countConsonants();
		int words = io.countWords();
		int chars = io.countChars();
		io.printWordsVowelsConsonantsCount(words, vowels, consonants, chars);

		io.printFoundAnagrams(io.findAndGetAnagrams());
		io.printFoundPalindromes(io.findAndGetPalindromes());

	}

}
