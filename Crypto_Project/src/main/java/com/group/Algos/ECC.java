package com.group.Algos;


import org.bouncycastle.jce.provider.BouncyCastleProvider;


import org.bouncycastle.jce.spec.ECParameterSpec;
import org.springframework.stereotype.Component;
import org.bouncycastle.jce.ECNamedCurveTable;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.spec.SecretKeySpec;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class ECC {
	static {
        Security.addProvider(new BouncyCastleProvider());
    }
	KeyPair bobKeyPair;
	String shared_key;
	KeyPair aliceKeyPair;
	
	

	public ECC() {
		super();
	}

	public void generate() throws Exception {
		aliceKeyPair = generateECCKeyPair(); 
		bobKeyPair = generateECCKeyPair();
	}
	public String getAlice_private() {
		return Base64.getEncoder().encodeToString(aliceKeyPair.getPrivate().getEncoded());
	}

	public String getAlice_public() {
		return Base64.getEncoder().encodeToString(aliceKeyPair.getPublic().getEncoded());
	}
	

	public String getBob_private() {
		return Base64.getEncoder().encodeToString(bobKeyPair.getPrivate().getEncoded());
	}


	public String getBob_public() {
		return Base64.getEncoder().encodeToString(bobKeyPair.getPublic().getEncoded());
	}
	public void setShared_key(String pvt,String pub) throws Exception {
		pvt=pvt.replaceAll("\\s","+");
		pub=pub.replaceAll("\\s","+");
		System.out.println(pvt);
		System.out.println(pub);
		byte[] publicBytes = Base64.getDecoder().decode(pub);
	    byte[] privateBytes = Base64.getDecoder().decode(pvt);

	    // Generate PublicKey from publicBytes
	    KeyFactory keyFactory = KeyFactory.getInstance("EC", "BC");
	    X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(publicBytes);
	    PublicKey publicKey = keyFactory.generatePublic(pubKeySpec);

	    // Generate PrivateKey from privateBytes
	    PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(privateBytes);
	    PrivateKey privateKey = keyFactory.generatePrivate(privKeySpec);

	    // Derive the shared secret using the reconstructed keys
	    shared_key = Base64.getEncoder().encodeToString(deriveSharedSecret(privateKey, publicKey));
	}
	public String getShared_key() {
		return shared_key;
	}

	    public static KeyPair generateECCKeyPair() throws Exception {
	        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "BC");
	        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256r1");
	        keyPairGenerator.initialize(ecSpec, new SecureRandom());
	        return keyPairGenerator.generateKeyPair();
	    }

	    // Derive a shared secret using ECDH
	    public static byte[] deriveSharedSecret(PrivateKey privateKey, PublicKey publicKey) throws Exception {
	        KeyAgreement keyAgreement = KeyAgreement.getInstance("ECDH", "BC");
	        keyAgreement.init(privateKey);
	        keyAgreement.doPhase(publicKey, true);
	        return keyAgreement.generateSecret();
	    }

	    // Encrypt a message using AES with the derived shared secret
	    public String encryptMessage(String plainText, String key) throws Exception {
	    	//plainText=plainText.replaceAll("\\s","+");
			key=key.replaceAll("\\s","+");
	    	byte[] sharedSecret=Base64.getDecoder().decode(key);
	        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
	        SecretKeySpec aesKey = new SecretKeySpec(sharedSecret, 0, 16, "AES"); // Use first 16 bytes for AES key
	        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
	        return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes()));
	    }

	    // Decrypt the message using AES with the derived shared secret
	    public String decryptMessage(String ct, String key) throws Exception {
	    	ct=ct.replaceAll("\\s","+");
			key=key.replaceAll("\\s","+");
	    	byte[] encryptedText=Base64.getDecoder().decode(ct);
	    	byte[] sharedSecret=Base64.getDecoder().decode(key);
	        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
	        SecretKeySpec aesKey = new SecretKeySpec(sharedSecret, 0, 16, "AES"); // Use first 16 bytes for AES key
	        cipher.init(Cipher.DECRYPT_MODE, aesKey);
	        byte[] decryptedText = cipher.doFinal(encryptedText);
	        return new String(decryptedText);
	    }
	}
