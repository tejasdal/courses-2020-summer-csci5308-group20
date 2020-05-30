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

public class CryptoUtilTest {
	private static final String PLAIN_TEXT = "helloworld";
	private static final String CIPHER_TEXT = "rL7IpQ2a5WRuytiOgiTjhQ==";
	private static final String MD5_HASH = "fc5e038d38a57032085441e7fe7010b0";
	private static final String ENCODED_PASSWORD = "044fb40d0b95872a6ba6ff7bd546436f";

	@Test
	public void encryptTest() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		assertEquals(CIPHER_TEXT, CryptoUtil.encrypt(PLAIN_TEXT));
	}

	@Test
	public void decryptTest() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		assertEquals(PLAIN_TEXT, CryptoUtil.decrypt(CIPHER_TEXT));
	}

	@Test
	public void md5HashTest() throws NoSuchAlgorithmException {
		assertEquals(MD5_HASH, CryptoUtil.md5Hash(PLAIN_TEXT));
	}

	@Test
	public void encodePasswordTest() throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		assertEquals(ENCODED_PASSWORD, CryptoUtil.encodePassword(PLAIN_TEXT));
	}
}
