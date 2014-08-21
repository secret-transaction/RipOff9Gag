package com.secrettransaction.rogagserver.util;

import java.security.NoSuchAlgorithmException;

import javax.crypto.SecretKey;

public class AccessTokenGenerator {
	
	private static HMAC hmac = new HMAC();

	public static String generate(String raw) {
		String accessToken = "";
		
		try {
			SecretKey secretKey = hmac.generateKey();
			accessToken = hmac.digestBase64(raw, secretKey);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return accessToken;
	}
}
