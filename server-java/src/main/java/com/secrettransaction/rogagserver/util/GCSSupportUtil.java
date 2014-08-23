package com.secrettransaction.rogagserver.util;

import java.io.FileInputStream;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;

import org.apache.commons.codec.binary.Base64;

public class GCSSupportUtil {
	
	private static final GCSSupportUtil instance = new GCSSupportUtil();
	
	public static GCSSupportUtil getInstance() {
		return instance;
	}

	private String p12FileSrc = "WEB-INF/security/rogag-server-stage.p12";
	private String p12FilePassword = "notasecret";
	private String googleAccessId = "346065651051-5jqnomg7d53onsquss29pncq5si5p419@developer.gserviceaccount.com";
	private String bucketName = "rogag-server-stage.appspot.com";
	private String gcsBaseUrl = "http://storage.googleapis.com";
	private String servingUrlPrefix = "http://rogag-server-stage.appspot.com";
	
	/**
	 * for an explanation on how this method works, refer to: https://developers.google.com/storage/docs/accesscontrol#Signed-URLs
	 * 
	 * after generating this url, the client should send a put request 
	 * client should refer to: https://developers.google.com/storage/docs/reference-methods#putobject
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String generateSignedUrl(SignedUrlParams params) throws Exception {
        //step 1 (Construct the string to be signed): https://developers.google.com/storage/docs/accesscontrol#Construct-the-String
        String toSign = "PUT\n\n"
        		+ params.contentType + "\n"
        		+ params.expiration + "\n"
        		+ "x-goog-acl:public-read" + "\n/"
        		+ bucketName + "/" + params.filename;

        //step 2 (Sign the string using the RSA Algorithm): https://developers.google.com/storage/docs/accesscontrol#Signing-Strings
        PrivateKey privateKey = loadKeyFromPkcs12();
        Signature signer = Signature.getInstance("SHA256withRSA");
        signer.initSign(privateKey);
        signer.update(toSign.getBytes("UTF-8"));
        byte[] rawSignature = signer.sign();
        String signature = new String(Base64.encodeBase64(rawSignature, false), "UTF-8");

        //step 3 (Assemble the URL): https://developers.google.com/storage/docs/accesscontrol#Assembling-the-URL
        String url = gcsBaseUrl 
        		+ "/"
        		+ bucketName + "/"
        		+ params.filename
        		+ "?GoogleAccessId=" + googleAccessId
        		+ "&Expires=" + params.expiration
        		+ "&Signature=" + URLEncoder.encode(signature, "UTF-8");
        
        //step 4 (Share the URL with authorized users)
        return url;
	}
	
	public String generateServingUrl(String objectId) {
		return String.format("%s/%s", servingUrlPrefix, objectId);
	}
	
	private PrivateKey loadKeyFromPkcs12() throws Exception {
        FileInputStream fis = new FileInputStream(p12FileSrc);
        KeyStore ks = KeyStore.getInstance("PKCS12");

        ks.load(fis, p12FilePassword.toCharArray());
        return (PrivateKey) ks.getKey("privatekey", p12FilePassword.toCharArray());
    }

	public String getP12FileSrc() {
		return p12FileSrc;
	}

	public void setP12FileSrc(String p12FileSrc) {
		this.p12FileSrc = p12FileSrc;
	}

	public String getP12FilePassword() {
		return p12FilePassword;
	}

	public void setP12FilePassword(String p12FilePassword) {
		this.p12FilePassword = p12FilePassword;
	}

	public String getGoogleAccessId() {
		return googleAccessId;
	}

	public void setGoogleAccessId(String googleAccessId) {
		this.googleAccessId = googleAccessId;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getGcsBaseUrl() {
		return gcsBaseUrl;
	}

	public void setGcsBaseUrl(String gcsBaseUrl) {
		this.gcsBaseUrl = gcsBaseUrl;
	}

	public String getServingUrlPrefix() {
		return servingUrlPrefix;
	}

	public void setServingUrlPrefix(String servingUrlPrefix) {
		this.servingUrlPrefix = servingUrlPrefix;
	}

	public static class SignedUrlParams {
		private String filename;
		private String contentType;
		private long expiration;

		public String getFilename() {
			return filename;
		}

		/**
		 * should not start with "/"
		 * @param filename
		 */
		public void setFilename(String filename) {
			this.filename = filename;
		}

		public String getContentType() {
			return contentType;
		}

		public void setContentType(String contentType) {
			this.contentType = contentType;
		}

		public long getExpiration() {
			return expiration;
		}

		public void setExpiration(long expiration) {
			this.expiration = expiration;
		}

	}
}
