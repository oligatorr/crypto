package com.github.oligatorr.crypto.cipher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;

public class CipherFile {

	public static void main(String[] args) throws Exception {
		System.out.println("Arguments:");
		for (String s: args) {
			System.out.println(s);
		}
		String modeArg = args[0];
		int cipherMode = -1;
		if (modeArg.equalsIgnoreCase("Encrypt")) {
			cipherMode=Cipher.ENCRYPT_MODE;
		} else if (modeArg.equalsIgnoreCase("Decrypt")) {
			cipherMode=Cipher.DECRYPT_MODE;
		} else {
			System.out.println("First arg must be Encrypt or Decrypt");
			return;
		}
		String passPhrase = args[1];
		String pathFile = args[2];
		String outputPathFile="";
		switch (cipherMode) {
		case Cipher.ENCRYPT_MODE:
			outputPathFile = pathFile + ".ciphered";
			break;
		case Cipher.DECRYPT_MODE:
			outputPathFile = pathFile + ".clear";
			break;
		}

		MessageDigest sha256Provider = MessageDigest.getInstance("SHA-256");
		byte[] key = sha256Provider.digest(passPhrase.getBytes("UTF8"));
		SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
		Cipher aes = Cipher.getInstance("AES/CBC/PKCS5Padding");
		System.out.println(String.format("Blocksize is: %d, Algorithm is: %s", aes.getBlockSize(),aes.getAlgorithm()));
		byte[] inputBytes = getBytesFromFile(pathFile);
		byte[] outputBytes = null;
		byte[] ivBytes = null;
		if (cipherMode == Cipher.ENCRYPT_MODE) {
			aes.init(cipherMode, keySpec);
			//Add IV before ciphered bytes 
			ivBytes = aes.getIV();
			outputBytes = ArrayUtils.addAll(ivBytes, aes.doFinal(inputBytes));
		} else {
			//Extract IV bloc
			ivBytes = ArrayUtils.subarray(inputBytes, 0, 16);
			byte[] cipheredBytes =  ArrayUtils.subarray(inputBytes, 16, inputBytes.length);
			IvParameterSpec iv = new IvParameterSpec(ivBytes);
			aes.init(cipherMode, keySpec, iv);
			// decrypt
			outputBytes = aes.doFinal(cipheredBytes);
		}
		System.out.println(String.format("Input size: %d\nIV: %s\nInput content in hexa:\n%s", outputBytes.length, Helper.bytesToHex(ivBytes), Helper.bytesToHex(outputBytes)));
		System.out.println("Writing output file...");
		writeBinaryFile(outputPathFile, outputBytes);
		System.out.println("Fichier écris.");
	}
	public static byte[] getBytesFromFile(String filePath) throws IOException {
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		byte[] targetArray = IOUtils.toByteArray(fis);
		return targetArray;
	}
	public static void writeBinaryFile(String outputFilePath, byte[] data) throws IOException {
		FileOutputStream out = new FileOutputStream(outputFilePath);
		out.write(data);
		out.close();
	}
}
