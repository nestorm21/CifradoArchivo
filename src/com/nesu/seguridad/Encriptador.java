package com.nesu.seguridad;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;

public class Encriptador {

	public String decryptMsg(String msg) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		PrivateKey privKey = this.getPrivateKey("Keys/private");
		System.out.println(msg);
		cipher.init(Cipher.DECRYPT_MODE, privKey);
		return new String(cipher.doFinal(Base64.decodeBase64(msg)), "UTF-8");
	}
	
	public PrivateKey getPrivateKey(String filename) throws Exception {
		InputStream stream = getClass().getClassLoader().getResourceAsStream(filename);
		byte[] keyBytes = new byte[stream.available()];
		IOUtils.readFully(stream, keyBytes);
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);
	}
}
