package a11;

public class VowalsConsonantsHelper {

	public boolean isVowal(char ch) {
		char chtmp = Character.toLowerCase(ch);

		return (chtmp == 'e') || (chtmp == 'a') || (chtmp == 'i')
				|| (chtmp == 'o') || (chtmp == 'u') || (chtmp == 'ä')
				|| (chtmp == 'ö') || (chtmp == 'ü');
	}

	public boolean isConsonant(char ch) {
		char chtmp = Character.toLowerCase(ch);

		return (chtmp > 96 && chtmp < 123) && chtmp != 'a' && chtmp != 'e'
				&& chtmp != 'i' && chtmp != 'o' && chtmp != 'u' && chtmp != 'ö'
				&& chtmp != 'ä' && chtmp != 'ü' || chtmp == 'ß';

	}
}
