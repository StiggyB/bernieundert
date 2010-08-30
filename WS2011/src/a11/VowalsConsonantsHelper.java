package a11;

public class VowalsConsonantsHelper {

	public boolean isVowal(char ch) {
		char chtmp = Character.toLowerCase(ch);

		return (chtmp == 'e') || (chtmp == 'a') || (chtmp == 'i')
				|| (chtmp == 'o') || (chtmp == 'u') || (chtmp == '�')
				|| (chtmp == '�') || (chtmp == '�');
	}

	public boolean isConsonant(char ch) {
		char chtmp = Character.toLowerCase(ch);

		return (chtmp > 96 && chtmp < 123) && chtmp != 'a' && chtmp != 'e'
				&& chtmp != 'i' && chtmp != 'o' && chtmp != 'u' && chtmp != '�'
				&& chtmp != '�' && chtmp != '�' || chtmp == '�';

	}
}
