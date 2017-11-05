package com.github.oligator.crypto.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.oligatorr.crypto.cipher.Helper;

public class HelperTest {

	@Test
	public void testParser() {
		byte[] expected = 	{(byte) 0x29, (byte) 0x6c, (byte) 0x93, (byte) 0xfd, (byte) 0xf4, (byte) 0x99, (byte) 0xaa, (byte) 0xeb};
		String input = "296c93FDf499aaeb";
		byte[] actuals = Helper.parseHexa(input);
		assertArrayEquals(expected, actuals);
	}

	@Test
	public void testParserSparse() {
		byte[] expected = 	{(byte) 0x29, (byte) 0x6c, (byte) 0x93, (byte) 0xfd, (byte) 0xf4, (byte) 0x99, (byte) 0xaa, (byte) 0xeb};
		String input = "29 6c 93 FDf49 9aaeb";
		byte[] actuals = Helper.parseHexa(input);
		assertArrayEquals(expected, actuals);
	}

}
