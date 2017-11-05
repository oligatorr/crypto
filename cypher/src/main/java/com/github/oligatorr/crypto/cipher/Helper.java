package com.github.oligatorr.crypto.cipher;
import static java.lang.Math.*;
public class Helper {
	private Helper() {}
	public static String bytesToHex(byte[] in) {
		if (in == null) {
			return "";
		}
	    final StringBuilder builder = new StringBuilder();
	    for(byte b : in) {
	        builder.append(String.format("%02x ", b));
	    }
	    return builder.toString();
	}
	public static byte[] complement(byte[] in) {
		byte[] complement = new byte[in.length];
	    for(int i=0;i<in.length;i++) {
	        complement[i] = (byte) ~in[i];
	    }
	    return complement;
	}

	public static byte[] xor(byte[] in1, byte[] in2) {
		int size = min(in1.length,in2.length);
		byte[] xored = new byte[size];
	    for(int i=0;i<size;i++) {
	    		xored[i] = (byte) (in1[i] ^ in2[i]);
	    }
	    return xored;
	}
	public static byte[] parseHexa(String s) {
		String cleaned = s.replace(" " ,"");
		int size = cleaned.length()/2;
		byte[] parsed = new byte[size];
		for (int i=0;i<size;i++) {
			String token = cleaned.substring(i*2, i*2+2);
			parsed[i] = (byte) Integer.parseInt(token,16);
		}
		return parsed;
	}
}
