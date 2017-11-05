package com.github.oligatorr.crypto.cipher;

public class BreakCTR {

	public static void main(String[] args) {
		String c = "46 64 DC 06 97 BB FE 69 33 07 15 07 9B A6 C2 3D 2B 84 DE 4F 90 8D 7D 34 AA CE 96 8B 64 F3 DF 75";
		String c1 ="51 7E CC 05 C3 BD EA 3B 33 57 0E 1B D8 97 D5 30 7B D0 91 6B 8D 82 6B 35 B7 8B BB 8D 74 E2 C7 3B";
		String p = "43 72 79 70 74 6F 67 72 61 70 68 79 20 43 72 79 70 74 6F 67 72 61 70 68 79 20 43 72 79 70 74 6F";
		byte[] bc = Helper.parseHexa(c);
		byte[] bc1 = Helper.parseHexa(c1);
		byte[] bp = Helper.parseHexa(p);
		
		byte[] diffCc1 = Helper.xor(bc, bc1);
		byte[] plainPlusDiff = Helper.xor(diffCc1,bp);
		
		System.out.println(String.format("Longueur:%d\ntexte P+C+C':            %s", plainPlusDiff.length, Helper.bytesToHex(plainPlusDiff)));
		String plainPlusDiffString = new String(plainPlusDiff);
		System.out.println(plainPlusDiffString);
	}
}
