package pki;

import java.io.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import javax.swing.*;

/**
 * Class RSF (ReceiveSecureFile)
 * 
 * Decrypt and verify a secret session key and decryption for document.
 * Extends {@link SFHelper} for key usage
 * 
 * Syntax: deprecated: java RSF FMeier.prv KMueller.pub Brief.ssf Brief.doc All
 * in/out files can be choosen via a file chooser
 * 
 * @author Martin
 */
public class RSF extends SFHelper {

	public static byte[] decryptSecretKey(Key k, byte[] data) throws Exception {
		Cipher ciph = Cipher.getInstance("RSA");
		ciph.init(Cipher.DECRYPT_MODE, k);
		return ciph.doFinal(data);
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		try {
			// 3a) einlesen eines priv RSA-Keys aus Datei
			JOptionPane.showMessageDialog(null, "Bitte RSA Private-Key auswaehlen");
			// Gewaehlten Key konvertieren und ablegen (Methode aus Superklasse)
			Key privateKey = openPrivateKey();
			// 3b) einlesen eines pub RSA-Keys aus Datei
			JOptionPane.showMessageDialog(null, "Bitte RSA Public-Key auswaehlen");
			// Gewaehlten Key konvertieren und ablegen (Methode aus Superklasse)
			PublicKey publicKey = (PublicKey) openPublicKey();
			// 3c) ssf-Datei einlesen, AES Key mit priv RSA Key entschluesseln
			JOptionPane.showMessageDialog(null, "Bitte SSF-Datei auswaehlen");
			JFileChooser jfc = new JFileChooser();
			jfc.showDialog(null, "Oeffnen");
			// ssf-Datei einlesen und bearbeiten
			SSFFileHelper ssfFile = SSFFileHelper.fromFile(jfc.getSelectedFile());
			// crypted AES key holen
			byte[] encryptedAesKey = ssfFile.getEncryptedSecretKey();
			System.out.println("Laenge des Verschluesselten AES-Keys: " + encryptedAesKey.length);
			// Siehe JCA/JCE CipherEncryption.java
			// AES key entschluesseln mit RSA priv key
			Key aesKey = new SecretKeySpec(decryptSecretKey(privateKey, encryptedAesKey), "AES");
			Cipher ciph = Cipher.getInstance("AES");
			ciph.init(Cipher.DECRYPT_MODE, aesKey);
			// Daten entschluesseln
			CipherInputStream cis = new CipherInputStream(ssfFile.getFis(), ciph);
			// Speicherort Klartext
			JOptionPane.showMessageDialog(null, "Bitte Zieldatei auswaehlen");
			jfc.showDialog(null, "Speichern");
			FileOutputStream fos = new FileOutputStream(jfc.getSelectedFile());
			byte[] buffer = new byte[512];
			int len;

			// entschluesseln und in Klartext-Datei speichern
			while ((len = cis.read(buffer)) > 0) {
				System.out.println(len + " bytes gelesen.");
				fos.write(buffer, 0, len);
			}

			cis.close();
			fos.flush();
			fos.close();
			// Signatur pruefen fuer AES Key mit public RSA Key
			Signature sign = Signature.getInstance("SHA1withRSA");
			sign.initVerify(publicKey);
			sign.update(aesKey.getEncoded());
			JOptionPane.showMessageDialog(null, "Verifikation der Signatur verlief"
					+ (sign.verify(ssfFile.getSecretKeySignature()) ? " " : " nicht ") + "erfolgreich");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
