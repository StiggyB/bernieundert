package pki;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.PrivateKey;
import java.security.Signature;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Class SSF (SendSecureFile)
 * 
 * Create, sign, encrypt a secret session key and encryption for document.
 * 
 * Extends {@link SFHelper} for key usage
 * 
 * Syntax: deprecated: java SSF KMueller.prv FMeier.pub Brief.doc Brief.ssf All
 * in/out files can be choosen via a file chooser
 * 
 * @author Martin
 */
public class SSF extends SFHelper {

	/**
	 * generate AES key
	 * 
	 * @param size
	 *            length for AES algorithm in bits
	 * @return Key generated AES secret key
	 * @throws Exception
	 */
	private static Key generateAESKey(int size) throws Exception {
		KeyGenerator kg = KeyGenerator.getInstance("AES");
		kg.init(size);
		return kg.generateKey();
	}

	//
	/**
	 * generate signature for data (AES-Key) with RSA private key
	 * 
	 * @param k
	 *            private RSA key to use
	 * @param data
	 *            bytes to process
	 * @return signature for data bytes
	 * @throws Exception
	 */
	private static byte[] generateSignature(PrivateKey k, byte[] data) throws Exception {
		Signature sign = Signature.getInstance("SHA1withRSA");
		sign.initSign(k);
		sign.update(data);
		return sign.sign();
	}

	/**
	 * encryption of data with RSA public key
	 * 
	 * @param k
	 *            public key to be used
	 * @param data
	 *            to be encrypted
	 * @return crypted byte array
	 * @throws Exception
	 */
	private static byte[] encrypt(Key k, byte[] data) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return cipher.doFinal(data);
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		try {
			// 2a) einlesen eines priv RSA-Keys aus Datei
			JOptionPane.showMessageDialog(null, "Bitte RSA Private-Key auswaehlen");
			// Gewaehlten Key konvertieren und ablegen (Methode aus Superklasse)
			Key privateKey = openPrivateKey();
			// 2b) einlesen eines pub RSA-Keys aus Datei
			JOptionPane.showMessageDialog(null, "Bitte RSA Public-Key auswaehlen");
			// Gewaehlten Key konvertieren und ablegen (Methode aus Superklasse)
			Key publicKey = openPublicKey();
			// 2c) AES Key (128 Bit) erstellen (siehe JCA/JCE:
			// CipherEncryption.java)
			Key aesKey = generateAESKey(128);
			// 2d) Sig fuer Key aus 2c) erstellen mit priv RSA Key (SHA1withRSA)
			System.out.println(privateKey instanceof PrivateKey);
			byte[] signature = generateSignature((PrivateKey) privateKey, aesKey.getEncoded());
			// 2e) Key aus 2c) mit RSA pub Key verschluesseln
			byte[] encryptedRSAKey = encrypt(publicKey, aesKey.getEncoded());
			// 2f+g) Datei einlesen und verschluesseln mit AES Key
			JFileChooser jfc = new JFileChooser();
			JOptionPane.showMessageDialog(null, "Bitte Quelldatei auswaehlen");
			jfc.showDialog(null, "Oeffnen");
			File infile = jfc.getSelectedFile();
			// Ergebnis Datei angeben
			JOptionPane.showMessageDialog(null, "Bitte Ausgabedatei auswaehlen");
			jfc.showDialog(null, "Erzeugen");
			File outfile = jfc.getSelectedFile();
			if (!outfile.exists()) {
				outfile.createNewFile();
			}
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(outfile, false));
			dos.writeInt(encryptedRSAKey.length);
			dos.write(encryptedRSAKey);
			dos.writeInt(signature.length);
			dos.write(signature);
			// 2f) Datei einlesen und verschluesseln
			FileInputStream fis = new FileInputStream(infile);
			Cipher c = Cipher.getInstance("AES");
			c.init(Cipher.ENCRYPT_MODE, aesKey);
			CipherOutputStream cout = new CipherOutputStream(dos, c);
			byte[] buffer = new byte[512];
			int len;
			// verschluesseln und in Datei schreiben
			while (fis.available() > 0) {
				len = fis.read(buffer);
				cout.write(buffer, 0, len);
			}
			cout.close();
			fis.close();
			System.out.println("Success!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
