package com.github.oligatorr.crypto.cipher;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
/**
 * Cryptographic engineering (Fergusson, schneier,kohno)
 * Exercise 3.8
 * @author oligatorr
 *
 */
public class DecipherTestCeAes {

	public static void main(String[] args) throws Exception {
		byte[] key = {	(byte) 0x80, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
						(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
						(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
						(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01};
		byte[] cipher = {(byte) 0x53, (byte) 0x9b, (byte) 0x33, (byte) 0x3b, (byte) 0x39, (byte) 0x70, (byte) 0x6d, (byte) 0x14, 
						 (byte) 0x90, (byte) 0x28, (byte) 0xcf, (byte) 0xe1, (byte) 0xd9, (byte) 0xd4, (byte) 0xa4, (byte) 0x07};
		SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
		System.out.println(String.format("Longeur du chiffré (en octets): %d\nTexte chiffré\n_____________\n%s\n", cipher.length, Helper.bytesToHex(cipher)));
		Cipher aes = Cipher.getInstance("AES");
		aes.init(Cipher.DECRYPT_MODE, keySpec);
		byte[] clear = aes.doFinal(cipher);
		System.out.println(String.format("Blocksize is: %d, Algorithm is: %s", aes.getBlockSize(),aes.getAlgorithm()));
		System.out.println(String.format("Longeur du déchiffré (en octets): %d\ntexte déchiffré\n_______________\n%s\n", clear.length, Helper.bytesToHex(clear)));
	}
}
