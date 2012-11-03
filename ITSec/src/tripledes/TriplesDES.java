package tripledes;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TriplesDES {
	private byte[] key1 = new byte[8];
	private byte[] key2 = new byte[8];
	private byte[] key3 = new byte[8];
	private byte[] initVector = new byte[8];

	/**
	 * Liest das Keyfile aus. Byte 1-24: 24 Schlüsselbytes (3 DES-Schlüssel à 8
	 * Byte, wobei von jedem Byte jeweils 7 Bit verwendet werden) Byte 25-32: 8
	 * Bytes für den Initialisierungsvektor zum Betrieb im CFB - Modus
	 * 
	 * @param keyFile
	 *            Name des Keyfiles
	 */
	private void readKeyFile(String keyFile) {
		try {
			FileInputStream fis = new FileInputStream(keyFile);
			fis.read(key1);
			fis.read(key2);
			fis.read(key3);
			fis.read(initVector);
			fis.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fuehrt eine TripleDES Verschluesselung nach dem E-D-E Prinzip durch
	 * 
	 * @param source
	 *            zu verschluesselner Byte Array
	 * @return dest verschluesselter Byte Array
	 */
	private byte[] ede(byte[] source) {
		byte[] dest = new byte[8];

		new DES(key1).encrypt(source, 0, dest, 0);
		new DES(key2).decrypt(dest, 0, source, 0);
		new DES(key3).encrypt(source, 0, dest, 0);

		return dest;
	}

	/**
	 * Ver- oder Entschluesselt ein File nach dem TripleDES Verfahren
	 * 
	 * @param args
	 *            "plainTextFile" "keyFile" "cipherTextFile" "encrypt/decrypt"
	 */
	public static void main(String[] args) {
		if (args.length != 4) {
			System.out.println("-- usage: java -cp tripledes.jar tripledes.TripleDES <input file> <key file> <output file> <mode>");
			System.out.println("-- <input file> can be cipher text or plain text, <mode> can be set to encrypt or decrypt");
		}
		System.out.println("Triple DES encryption/decryption starting:");
		// Uebergabe der noetigen Parameter an das Programm
		// Eingabedatei, verschluesseln oder entschluesseln
		String sourceFile = args[0];
		System.out.println("Chosen inputfile: " + sourceFile);
		// schluesseldatei
		String keyFile = args[1];
		System.out.println("Keyfile to be used: " + keyFile);
		// ver- oder entschluesselte Datei hier speichern
		String destFile = args[2];
		System.out.println("Output file name: " + destFile);
		// encrypt oder decrypt
		String mode = args[3];
		System.out.println("Mode: " + (mode.equals("decrypt") ? "decrypt" : "encrypt"));

		TriplesDES tdes = new TriplesDES();
		// Keyfile auslesen und Variablen einstellen
		tdes.readKeyFile(keyFile);
		// IV (initVector) durch Fk schicken (verschluesseln)
		byte[] dest = tdes.ede(tdes.initVector);

		try {
			FileInputStream fis = new FileInputStream(sourceFile);
			FileOutputStream fos = new FileOutputStream(destFile);
			System.out.println("Working...");

			// Daten, die ver- oder entschluesselt werden sollen
			byte[] source = new byte[8];

			// Immer 8 Byte einlesen und bearbeiten
			while ((fis.read(source)) > 0) {
				// CFB, XOR anwenden
				for (int i = 0; i < 8; i++) {
					// C0 = IV
					// Ci = Mi XOR Fk(Ci-1)
					dest[i] = (byte) (dest[i] ^ source[i]);
				}

				// ver- oder entschluesselten Block wegschreiben
				fos.write(dest, 0, 8);

				// Fuer Block Ci bzw. Mi entsprechend Ci-1 bzw. Mi-1 vorbereiten
				// und durch Fk (ede) jagen
				if (mode.equalsIgnoreCase("encrypt")) {
					// verschluesseln:
					// Ci-Block wird verschluesselt und als Vektor fuer Ci+1
					// genutzt
					dest = tdes.ede(dest);
				} else if (mode.equalsIgnoreCase("decrypt")) {
					// Entschluesseln:
					// Mi-Block wird verschluesselt und als Vektor fuer Mi+1
					// genutzt
					dest = tdes.ede(source);
				}
			}
			System.out.println("\nFinished.");

			fis.close();
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
