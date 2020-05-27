package org.dal.cs5308.t20.Project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.jupiter.api.Test;

public class EncryptUtilTest {
	final String plainText = "helloworld";
	final String cipherText = "rL7IpQ2a5WRuytiOgiTjhQ==";

	@Test
	public void encryptTest() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		System.out.println(EncryptUtil.encrypt("AdvSdc@5308"));
		assertEquals(cipherText, EncryptUtil.encrypt(plainText));
	}

	@Test
	public void decryptTest() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		assertEquals(plainText, EncryptUtil.decrypt(cipherText));
	}
}
