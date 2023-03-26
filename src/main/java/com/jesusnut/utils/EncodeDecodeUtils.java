package com.jesusnut.utils;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.Arrays;

import com.jesusnut.exception.FrameworkException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utility Class for encryption and decryption of Strings(Passwords), byte[] to
 * Base64 conversion, String to Byte[] conversion etc.
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EncodeDecodeUtils {

	private static final String secretKeyString = "yggq$%^&ywgk576dwdy67t9978t#(*^%";
	private static final String IVString = "nhd586$$5gh12((899yjjf@#$%^&7ggh";
	private static final String algorithm1 = "AES";
	private static final String algorithm2 = "AES/CBC/PKCS5Padding";
	private static final String charsetType = "UTF-8";
	private static final byte sizeOFKeysInBytes = 16;

	private static SecretKey generateSecretKey() {

		byte[] secretKeyStringinByteArr = Arrays.copyOf(secretKeyString.getBytes(), sizeOFKeysInBytes);

		SecretKey secretKey = new SecretKeySpec(secretKeyStringinByteArr, algorithm1);

		return secretKey;

	}

	private static IvParameterSpec generateIV() {

		try {

			byte[] IVStringInByteArr = Arrays.copyOf(IVString.getBytes(charsetType), sizeOFKeysInBytes);

			IvParameterSpec iv = new IvParameterSpec(IVStringInByteArr);

			return iv;

		} catch (Exception e) {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB] >>> Error while encrypting: issue in method generateIV() in com.jesusnut.utils.EncodeDecodeUtils",
					e);
		}

	}

	// to encrypt a String using powerful AES/CBC/PKCS5 Padding.

	public static String encrypt(final String strToEncrypt) {

		try {

			Cipher cipher = Cipher.getInstance(algorithm2);

			cipher.init(Cipher.ENCRYPT_MODE, generateSecretKey(), generateIV());

			String encryptedPassword = encodeByteArrToBase64String(cipher.doFinal(strToEncrypt.getBytes(charsetType)));

			return encryptedPassword;

		} catch (Exception e) {

			throw new FrameworkException("JESUSNUT SELENIUM FRAMEWORK [Powered by TMB] >>> Error while encrypting: ",
					e);

		}

	}

	// to decrypt a String using powerful AES/CBC/PKCS5 Padding.

	public static String decrypt(final String strToDecrypt) {

		try {

			Cipher cipher = Cipher.getInstance(algorithm2);

			cipher.init(Cipher.DECRYPT_MODE, generateSecretKey(), generateIV());

			String decryptedPassword = new String(cipher.doFinal(decodeByteArrToBase64String(strToDecrypt)));

			return decryptedPassword;

		} catch (Exception e) {

			throw new FrameworkException("JESUSNUT SELENIUM FRAMEWORK [Powered by TMB] >>> Error while decrypting",
					e);
		}

	}

	// to decode any String to byte[]

	private static byte[] decodeByteArrToBase64String(String source) {

		return Base64.getDecoder().decode(source);

	}

	// to encode any byte [] to Base64 String

	public static String encodeByteArrToBase64String(byte[] source) {

		return Base64.getEncoder().encodeToString(source);

	}

}
