package pki;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.swing.JFileChooser;

/**
 * SecureFile Abstract class, just two static functions for opening pub and priv
 * keys, methods do naerly the same. One returns a pub and the other one a
 * private key for further usage in RSF and SSF.
 * 
 * Example on how to do this can be found in JCE-JCA class ReadSignedFile.java
 * 
 * @author Martin
 */
public abstract class SFHelper {

	/**
	 * Reads in and extracts a public key over a FileChooser and converts it for
	 * further usage
	 * 
	 * @return Key public key in X.509 format
	 * @throws Exception
	 */
	protected static Key openPublicKey() throws Exception {
		JFileChooser jfc = new JFileChooser();
		jfc.showDialog(null, "Oeffnen");
		// pub key waehlen
		File f = jfc.getSelectedFile();
		// Daten aus gewaehlter Datei einlesen
		DataInputStream dis = new DataInputStream(new FileInputStream(f));
		// Laenge des Erstellernamens (als int wurde gespeichert)
		int creatorNameLength = dis.readInt();
		// byte array fuer Erstellername vorbereiten mittels der gelesenen
		// Laenge
		byte[] creatorName = new byte[creatorNameLength];
		// Namen einlesen
		dis.read(creatorName);
		// Schluessellaenge einlesen (als int wurde gespeichert)
		int keyLength = dis.readInt();
		// byte array fuer Schluesserl vorbereiten mittels gelesener Laenge
		byte[] key = new byte[keyLength];
		// Schluessel einlesen
		dis.read(key);
		dis.close();
		// Schluesselspezifikation erzeugen
		X509EncodedKeySpec sks = new X509EncodedKeySpec(key);
		// Schluesselkonverter holen
		KeyFactory kf = KeyFactory.getInstance("RSA");
		// Schluessel konvertieren/erzeugen
		Key pubK = kf.generatePublic(sks);
		System.out.println("Public Key from " + new String(creatorName) + " (Format: " + pubK.getFormat() + ") from: " + f.getAbsolutePath() + " loaded!");
		return pubK;
	}

	/**
	 * Reads in and extracts a private key over a FileChooser and converts it
	 * for further usage
	 * 
	 * @return Key private key in PKCS8 format
	 * @throws Exception
	 */
	protected static Key openPrivateKey() throws Exception {
		// Vorgehen analog zu openPublicKey() ...
		JFileChooser jfc = new JFileChooser();
		jfc.showDialog(null, "Oeffnen");
		File f = jfc.getSelectedFile();
		DataInputStream dis = new DataInputStream(new FileInputStream(f));
		int creatorNameLength = dis.readInt();
		byte[] creatorName = new byte[creatorNameLength];
		dis.read(creatorName);
		int keyLength = dis.readInt();
		byte[] key = new byte[keyLength];
		dis.read(key);
		dis.close();
		PKCS8EncodedKeySpec sks = new PKCS8EncodedKeySpec(key);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		Key prvK = kf.generatePrivate(sks);
		System.out.println("Private Key from " + new String(creatorName) + " (Format: " + prvK.getFormat() + ") from: " + f.getAbsolutePath() + " loaded!");
		return prvK;
	}
}
