package com.github.oligatorr.crypto.cipher;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Cryptographic engineering (Fergusson, schneier,kohno)
 * Exercise 3.9
 * @author oligatorr
 *
 */
public class CipherTestCeAes {

	public static void main(String[] args) throws Exception {
		byte[] key = {	(byte) 0x80, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
						(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
						(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
						(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01};
		byte[] clear  = {(byte) 0x29, (byte) 0x6c, (byte) 0x93, (byte) 0xfd, (byte) 0xf4, (byte) 0x99, (byte) 0xaa, (byte) 0xeb, 
				         (byte) 0x41, (byte) 0x94, (byte) 0xba, (byte) 0xba, (byte) 0x2e, (byte) 0x63, (byte) 0x56, (byte) 0x1d};
		System.out.println("texte clair : " + Helper.bytesToHex(clear));
		SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
		Cipher aes = Cipher.getInstance("AES");
		System.out.println(String.format("Blocksize is: %d, Algorithm is: %s", aes.getBlockSize(),aes.getAlgorithm()));
		aes.init(Cipher.ENCRYPT_MODE, keySpec);
		byte[] cipher = aes.doFinal(clear);
		System.out.println(String.format("Longeur du chiffré:%s\ntexte chiffré:%s", cipher.length, Helper.bytesToHex(cipher)));
		
	}
}
