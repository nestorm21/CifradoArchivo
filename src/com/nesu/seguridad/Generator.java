package com.nesu.seguridad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.apache.tomcat.util.codec.binary.Base64;


public class Generator {
	public static void generateKeyPair(int length) {
		try {
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(length);
			KeyPair keys = generator.generateKeyPair();
			PrivateKey privKey = keys.getPrivate();
			PublicKey pubKey = keys.getPublic();
			File fPriv = new File("Keys/private");
			File fPub = new File("Keys/public");
			fPriv.getParentFile().mkdirs();
			fPub.getParentFile().mkdirs();
			try (FileOutputStream fos = new FileOutputStream(fPriv);
					FileOutputStream fos1 = new FileOutputStream(fPub)) {
				System.out.println("PrivateKey " + new String(Base64.encodeBase64(privKey.getEncoded())));
				System.out.println("PublicKey " + new String(Base64.encodeBase64(pubKey.getEncoded())));
				fos1.write(pubKey.getEncoded());
				fos.write(privKey.getEncoded());
			}
		} catch (NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		generateKeyPair(1024);
	}

}
