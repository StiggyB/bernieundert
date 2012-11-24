package pki;

import java.io.*;

/**
 * Class SSFFileHelper is a helper to process ssf-files
 * 
 * @author Martin
 *
 */
public class SSFFileHelper {
    
    private byte[] encryptedSecretKey;
    private byte[] secretKeySignature;
    private FileInputStream encryptedDataPosition;

    /**
     * Get crypted secret AES key
     * @return encryptedSecretKey
     */
    public byte[] getEncryptedSecretKey() {
        return encryptedSecretKey;
    }

    /**
     * Set crypted secret AES key
     * @param encryptedSecretKey
     */
    public void setEncryptedSecretKey(byte[] encryptedSecretKey) {
        this.encryptedSecretKey = encryptedSecretKey;
    }

    /**
     * Get signature from ssf-file
     * @return secretKeySignature
     */
    public byte[] getSecretKeySignature() {
        return secretKeySignature;
    }

    /**
     * Set signature from ssf-file
     * @param secretKeySignature
     */
    public void setSecretKeySignature(byte[] secretKeySignature) {
        this.secretKeySignature = secretKeySignature;
    }

    /**
     * Get used FileInputStream with position of crypted data
     * @return FileInputStream for ssf-file
     */
    public FileInputStream getFis() {
        return encryptedDataPosition;
    }

    /**
     * Set FileInputStream for ssf-file with position
     * where crypted data starts
     * @param fis use FileInputStream for ssf-file
     */
    public void setFis(FileInputStream fis) {
        this.encryptedDataPosition = fis;
    }
       
    /**
     * extract AES key and signature, set FileInputStream to
     * position, where crypted data starts in ssf-file
     * 
     * @param file choosen ssf-file
     * @return SSFFileHelper to work with
     * @throws Exception
     */
    public static SSFFileHelper fromFile(File file) throws Exception{
        SSFFileHelper ssfFile = new SSFFileHelper();
        FileInputStream fis = new FileInputStream(file);
        DataInputStream dis = new DataInputStream(fis);
        int encryptedSecretKeyLength = dis.readInt();
        byte[] buffer = new byte[encryptedSecretKeyLength];
        dis.read(buffer);
        ssfFile.setEncryptedSecretKey(buffer);
        int encryptedSecretKeySignatureLength = dis.readInt();
        buffer = new byte[encryptedSecretKeySignatureLength];
        dis.read(buffer);
        ssfFile.setSecretKeySignature(buffer);
        ssfFile.setFis(fis);
        return ssfFile;
    }
    
}
