package com.vineet.random;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;




public class AESEncryptionSample {

	public static void main(String[] args) {
		String aesKey = "AbcdBCDE@N9@8coo";
		String encAlgorithm= "AES/CBC/PKCS7Padding";
		
		String toEncrypt = "Decryption Successful. Cheers !";

        String encryptedText = AESEncryptionSample.encrypt(aesKey, encAlgorithm, toEncrypt, 16);

        System.out.println(" Encrypted text:" + encryptedText);

        String decryptedText = AESEncryptionSample.decrypt(aesKey, encAlgorithm, "ebb6da7f4df4f7769fd7adbae5e7f97f4ef46dee3879fe3d53616c7465645f5f9ec448fcb3da35189c20089ec8db5fa735e553aff653e230", 16);

        System.out.println(" Decrypted Text:" + decryptedText);

	}

	public static String encrypt(final String key, final String algorithm, final String plainText, final int keysize) {

		try {
			final SecretKeySpec keySpec = new SecretKeySpec(getKeyBytes(key, 16), "AES");
			final byte[] ivBytes = new byte[16];
			final SecureRandom thisRNG = new SecureRandom();
			thisRNG.nextBytes(ivBytes);

			final IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

			final Cipher aesECB = Cipher.getInstance(algorithm);
			aesECB.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			final byte[] iv = ivSpec.getIV();
			baos.write(iv);
			try (final CipherOutputStream cos = new CipherOutputStream(baos, aesECB)) {
				cos.write(plainText.getBytes(StandardCharsets.UTF_8));
			}
			final byte[] encrypted = baos.toByteArray();

			return Base64.getEncoder().encodeToString(encrypted); 

		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static byte[] getKeyBytes(final String key, final int keysize) throws UnsupportedEncodingException {
		final byte[] keyBytes = new byte[keysize];
		final byte[] b = key.getBytes("UTF-8");
		int len = b.length;
		if (len > keyBytes.length) {
			len = keyBytes.length;
		}
		System.arraycopy(b, 0, keyBytes, 0, len);
		return keyBytes;
	}

	public static String decrypt(final String key, final String algorithm, final String content, final int keysize) {
		try {

			
			final byte[] rawBytes = Base64.getDecoder().decode(content);
			final Cipher aesECB = Cipher.getInstance(algorithm);
			final SecretKeySpec keySpec = new SecretKeySpec(getKeyBytes(key, keysize), "AES");
			final ByteArrayInputStream bais = new ByteArrayInputStream(rawBytes);
			final IvParameterSpec ivSpec = readIV(aesECB.getBlockSize(), bais);
			aesECB.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

			final byte[] decryptedBytes;
			final byte[] buf = new byte[2048];

			try (final CipherInputStream cis = new CipherInputStream(bais, aesECB);
					final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
				int read;
				while ((read = cis.read(buf)) != -1) {
					baos.write(buf, 0, read);
				}
				decryptedBytes = baos.toByteArray();
			}
			return new String(decryptedBytes);

		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static IvParameterSpec readIV(final int ivSizeBytes, final InputStream is) throws IOException {
		final byte[] iv = (ivSizeBytes != 16) ? new byte[16] : new byte[ivSizeBytes];
		int offset = 0;
		while (offset < ivSizeBytes) {
			final int read = is.read(iv, offset, ivSizeBytes - offset);
			if (read == -1) {
				throw new IOException("Too few bytes for IV in input stream");
			}
			offset += read;
		}
		return new IvParameterSpec(iv);
	}
}
