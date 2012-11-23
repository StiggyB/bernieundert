package pki;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class RSAKeyCreation {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: RSAKeyCreation <creator_name>");
			System.exit(1);
		}
		String creatorName = args[0];
		byte[] creatorNameBytes = creatorName.getBytes();
		// Dateien vorbereiten Name.pub/.prv
		File pubK = new File(creatorName + ".pub");
		File prvK = new File(creatorName + ".prv");
		try {
			// RSA nutzen
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			// Schluessellaenge 1024 Bit 
			kpg.initialize(1024);
			// Pub und Priv Key generieren
			KeyPair keyPair = kpg.genKeyPair();
			// Keys einzeln speichern
			Key privateKey = keyPair.getPrivate();
			Key publicKey = keyPair.getPublic();
			byte[] privateKeyBytes = privateKey.getEncoded();
			byte[] publicKeyBytes = publicKey.getEncoded();
			// Dateien jetzt erzeugen
			if (!pubK.exists()) {
				pubK.createNewFile();
			}
			if (!prvK.exists()) {
				prvK.createNewFile();
			}
			// Pub Key in Datei speichern
			DataOutputStream pubKout = new DataOutputStream(new FileOutputStream(pubK, false));
			// Laenge des Inhabernamens
			pubKout.writeInt(creatorName.length());
			// Inhabername (Byteefolge)
			pubKout.write(creatorNameBytes);
			// Laenge des Pub Key
			pubKout.writeInt(publicKeyBytes.length);
			// eigentlicher Pub Key (Bytefolge) in X.509
			pubKout.write(publicKeyBytes);
			pubKout.close();
			// Prv Key in Datei speichern
			DataOutputStream prvKout = new DataOutputStream(new FileOutputStream(prvK, false));
			prvKout.writeInt(creatorName.length());
			prvKout.write(creatorNameBytes);
			prvKout.writeInt(privateKeyBytes.length);
			prvKout.write(privateKeyBytes);
			prvKout.close();
			// Rueckmeldung ueber Speicherort und Format
			System.out.println("Public Key (Format: " + publicKey.getFormat() + ") written to: " + pubK.getAbsolutePath());
			System.out.println("Private Key (Format: " + privateKey.getFormat() + ") written to: " + prvK.getAbsolutePath());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
