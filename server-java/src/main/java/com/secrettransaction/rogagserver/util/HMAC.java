package com.secrettransaction.rogagserver.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class HMAC {
	private static final String HMAC_ALGORITHM = "HmacMD5";
	private static final Logger log = Logger.getLogger(HMAC.class.getName());

	/**
	 * generates a HMAC digest
	 * 
	 * @param message
	 * @return
	 */
	public String digestBase64(String message, SecretKey key) {

		String digestB64 = null;
		try {

			byte[] keyBytes = key.getEncoded();
			// Create a MAC object using HMAC-MD5 and initialize with key
			Mac mac = Mac.getInstance(key.getAlgorithm());
			mac.init(key);

			StringBuffer payload = new StringBuffer();
			payload.append(message);
			payload.append(".");
			byte[] payloadBytes = payload.toString().getBytes("UTF8");
			byte[] utf8 = new byte[payloadBytes.length + keyBytes.length];
			System.arraycopy(payloadBytes, 0, utf8, 0, payloadBytes.length);
			System.arraycopy(keyBytes, 0, utf8, payloadBytes.length, keyBytes.length);

			byte[] digest = mac.doFinal(utf8);
			// If desired, convert the digest into a string
			digestB64 = new String(Base64.encodeBase64(digest));

		} catch (InvalidKeyException e) {
		} catch (NoSuchAlgorithmException e) {
		} catch (UnsupportedEncodingException e) {
		}

		return digestB64;
	}

	/**
	 * generates a HMAC digest
	 * 
	 * @param message
	 * @return
	 */
	public String digestBase64UrlSafe(String message, SecretKey key) {

		String digestB64 = null;
		try {

			byte[] keyBytes = key.getEncoded();
			// Create a MAC object using HMAC-MD5 and initialize with key
			Mac mac = Mac.getInstance(key.getAlgorithm());
			mac.init(key);

			StringBuffer payload = new StringBuffer();
			payload.append(message);
			payload.append(".");
			byte[] payloadBytes = payload.toString().getBytes("UTF8");
			byte[] utf8 = new byte[payloadBytes.length + keyBytes.length];
			System.arraycopy(payloadBytes, 0, utf8, 0, payloadBytes.length);
			System.arraycopy(keyBytes, 0, utf8, payloadBytes.length, keyBytes.length);

			byte[] digest = mac.doFinal(utf8);
			// If desired, convert the digest into a string
			digestB64 = new String(Base64.encodeBase64URLSafe(digest));
		} catch (InvalidKeyException e) {
		} catch (NoSuchAlgorithmException e) {
		} catch (UnsupportedEncodingException e) {
		}

		return digestB64;
	}

	/**
	 * 
	 * @param digest
	 * @param key
	 * @return
	 */
	public boolean authenticate(String digest, String message, SecretKey key) {
		boolean bret = false;

		log.info("digest:" + digest);
		log.info("message:" + message);
		String digestExpected = digestBase64UrlSafe(message, key);
		bret = digestExpected.equals(digest);
		log.info("actual:" + digestExpected);
		return bret;
	}

	/**
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public SecretKey generateKey() throws NoSuchAlgorithmException {
		// Generate a key for the HMAC-MD5 keyed-hashing algorithm; see RFC 2104
		// In practice, you would save this key.

		// SecretKeySpec key = new SecretKeySpec(
		// "qnscAdgRlkIhAUPY44oiexBKtQbGY0orf7OV1I50".getBytes(),
		// "HmacMD5");
		KeyGenerator keyGen = KeyGenerator.getInstance(HMAC_ALGORITHM);
		SecretKey key = keyGen.generateKey();

		return key;
	}

	/**
	 * use this when storing the secret key to the datastore as a string
	 * 
	 * @param secretKey
	 * @return
	 */
	public String encodeToString(SecretKey secretKey) {
		String stringKey = null;

		if (secretKey != null) {
			stringKey = new String(Base64.encodeBase64(secretKey.getEncoded()));
		}

		return stringKey;
	}

	/**
	 * use this to recreate the key when retrieving it from the datastore
	 * 
	 * @param secretKeyAsString
	 * @return
	 */
	public SecretKey decodeFromString(String secretKeyAsString) {
		byte[] encodedKey = Base64.decodeBase64(secretKeyAsString);
		SecretKey originalSecretKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, HMAC_ALGORITHM);

		return originalSecretKey;
	}
}
