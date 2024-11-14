package com.group.Algos;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class RSA{
    
    private static final String RSA = "RSA";
    
    KeyPair keyPair ;
    PublicKey publicKey;
    PrivateKey privateKey;
    int bits;
    
    public int getBits() {
		return bits;
	}


	public void setBits(int bits) {
		this.bits = bits;
	}


	public RSA(){
		super();
	}
	
	public void generateKeys() throws Exception {
		KeyPair keyPair = generateKeyPair(bits);
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();
	}
    

	public String getPublicKey() {
		return Base64.getEncoder().encodeToString(publicKey.getEncoded()); 
	}


	public String getPrivateKey() {
		return Base64.getEncoder().encodeToString(privateKey.getEncoded());
	}


	// Method to generate an RSA key pair
    public static KeyPair generateKeyPair(int bits) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
        keyPairGenerator.initialize(bits); // RSA key size
        return keyPairGenerator.generateKeyPair();
    }

    // Method to encrypt data with a public key
    public static String encrypt(String data, String key) throws Exception {
    	byte[] keyBytes = Base64.getDecoder().decode(key);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
    	PublicKey publicKey =keyFactory.generatePublic(spec);
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Method to decrypt data with a private key
    public static String decrypt(String encryptedData, String key) throws Exception {
    	byte[] keyBytes = Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PrivateKey privateKey =keyFactory.generatePrivate(keySpec);
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }

//    public static void main(String[] args) {
//        try {
//            // Generate RSA key pair
//            KeyPair keyPair = generateKeyPair();
//            PublicKey publicKey = keyPair.getPublic();
//            PrivateKey privateKey = keyPair.getPrivate();
//            
//            // Original message
//            String message = "Hello, this is a secret message!";
//            System.out.println("Original Message: " + message);
//            
//            // Encrypt the message
//            String encryptedMessage = encrypt(message, publicKey);
//            System.out.println("Encrypted Message: " + encryptedMessage);
//            
//            // Decrypt the message
//            String decryptedMessage = decrypt(encryptedMessage, privateKey);
//            System.out.println("Decrypted Message: " + decryptedMessage);
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}

