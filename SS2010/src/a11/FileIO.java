package a11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class FileIO extends InputOutput {

	private List<String> words = new LinkedList<String>();
	private List<Character> chars = new LinkedList<Character>();
	private VowalsConsonantsHelper hlp = new VowalsConsonantsHelper();

	public void processTxtFile(String filename) throws Exception {

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String singleLine = null;

		// bei bestimmten Zeichen splitten
		Pattern pat = Pattern.compile("[ ,\\n.!?;:]");

		// ein wort muss mindestens aus einem Buchstaben bestehen!
		Pattern wordPattern = Pattern.compile(".*[a-zA-z]+.*");

		while ((singleLine = reader.readLine()) != null) {
			String[] tmpWords = pat.split(singleLine);
			char[] tmpChars = singleLine.toCharArray();

			for (char c : tmpChars) {
				this.chars.add(c);
			}

			for (String s : tmpWords) {
				if (s.length() != 0 && wordPattern.matcher(s).matches()) {
					this.words.add(s);
				}
			}

		}
		reader.close();
	}

	public int countVowels() {
		int vowals = 0;
		if (this.chars.isEmpty()) {
			printEmptyArrayError();
		} else {
			for (Character c : this.chars) {
				if (this.hlp.isVowal(c)) {
					vowals++;
				}
			}
		}
		return vowals;
	}

	public int countConsonants() {
		int consonants = 0;
		if (this.chars.isEmpty()) {
			printEmptyArrayError();
		} else {
			for (Character c : this.chars) {
				if (this.hlp.isConsonant(c)) {
					consonants++;
				}
			}
		}
		return consonants;
	}

	public Map<String, Set<String>> findAndGetAnagrams() {
		Map<String, Set<String>> anagramMap = new HashMap<String, Set<String>>();
		if (this.words.isEmpty()) {
			printEmptyArrayError();
		} else {

			for (String s : this.words) {
				String lowerCase = s.toLowerCase();
				char[] charArray = lowerCase.toCharArray();
				Arrays.sort(charArray);
				String key = String.valueOf(charArray);

				if (!anagramMap.containsKey(key)) {
					anagramMap.put(key, new HashSet<String>());
				}
				anagramMap.get(key).add(s);
			}

			for (Iterator<String> iter = anagramMap.keySet().iterator(); iter
					.hasNext();) {
				String s = iter.next();
				Set<String> set = anagramMap.get(s);
				if (set.size() == 1) {
					iter.remove();
				}
			}
		}

		return anagramMap;
	}

	public List<String> findAndGetPalindromes() {
		List<String> palindromes = new LinkedList<String>();
		if (this.words.isEmpty()) {
			printEmptyArrayError();
		} else {
			StringBuilder sb;
			for (String s : this.words) {
				sb = new StringBuilder(s);
				String palin = sb.reverse().toString();
				if (palin.equalsIgnoreCase(s)) {
					palindromes.add(s);
				}
			}
		}
		return palindromes;
	}

	public int countWords() {
		return this.words.size();
	}

	public int countChars() {
		return this.chars.size();
	}

}
