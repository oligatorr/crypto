package com.github.oligatorr.crypto.cipher;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
/**
 * Cryptographic engineering (Fergusson, schneier,kohno)
 * Exercise 3.10
 * @author oligatorr
 *
 */
public class CheckDESComplement {
	public static void main(String[] args) throws Exception {
		byte[] key = 				{ (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff };
		byte[] complementedKey = 	{ (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 };
		
		byte[] clear  =             {(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff};
		byte[] complementedClear  = {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};
		System.out.println(String.format("Longeur du texte clair:%d\ntexte clair:%s", clear.length, Helper.bytesToHex(clear)));
		System.out.println(String.format("texte clair complémenté:%s", Helper.bytesToHex(complementedClear)));
		Cipher desCipher = Cipher.getInstance("DES");
		System.out.println(String.format("Blocksize is: %d, Algorithm is: %s", desCipher.getBlockSize(),desCipher.getAlgorithm()));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		DESKeySpec keySpec = new DESKeySpec(key); 
		SecretKey secretKey = keyFactory.generateSecret(keySpec);
		desCipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] cipher = desCipher.doFinal(clear);
		System.out.println(String.format("Longueur du chiffré:%d\ntexte chiffré:            %s", cipher.length, Helper.bytesToHex(cipher)));
		
		//complemented ciphering
		keySpec = new DESKeySpec(complementedKey); 
		secretKey = keyFactory.generateSecret(keySpec);
		desCipher.init(Cipher.ENCRYPT_MODE, secretKey);
		cipher = desCipher.doFinal(complementedClear);
		System.out.println(String.format("=\ntexte chiffré complémenté:%s", Helper.bytesToHex(Helper.complement(cipher))));
	}
}
