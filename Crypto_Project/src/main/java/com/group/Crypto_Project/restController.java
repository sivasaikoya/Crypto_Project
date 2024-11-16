package com.group.Crypto_Project;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.Algos.ECC;
import com.group.Algos.RSA;

@RestController
@CrossOrigin
public class restController {
	
	RSA rsa=new RSA();
	String Private="";
	String Public="";
	String Ct="";
	String Pt="";
	String apub="";
	String apvt="";
	String bpub="";
	String bpvt="";
	String sharedkey="";
	
	@RequestMapping("rsa/{bits}")
	public Map<String, String> rsa(@PathVariable("bits") int bits) throws Exception {
		rsa.setBits(bits);
		rsa.generateKeys();
		HashMap<String, String> obj=new HashMap<>();
		Private=rsa.getPrivateKey();
		Public=rsa.getPublicKey();
		obj.put("pvt",Private);
		obj.put("pub", Public);
		return obj;
		
	}
	
	@RequestMapping("rsa_encrypt")
	public Map<String, String> encrypt(@RequestParam("pub") String pub,@RequestParam("pt") String pt) throws Exception {
		Ct=rsa.encrypt(pt, pub);
		HashMap<String, String> obj=new HashMap<>();		
		obj.put("plain",pt);
		obj.put("pvt",Private);
		obj.put("pub",pub);
		obj.put("ct",Ct);
		return obj;
	}
	
	@RequestMapping("rsa_decrypt")
	public Map<String, String> decrypt(@RequestParam("pvt") String pvt,@RequestParam("ct") String ct) throws Exception {
		System.out.println(pvt);
		System.out.println(ct);
		Pt=rsa.decrypt(ct, pvt);
		HashMap<String, String> obj=new HashMap<>();
		obj.put("cipher",ct);
		obj.put("pvt",pvt);
		obj.put("pub",Public);
		obj.put("pt",Pt);
		return obj;
	}
	
	@RequestMapping("ecc_keys")
	public Map<String, String> ecc(ECC ecc) throws Exception {
		ecc.generate();
		apub=ecc.getAlice_public();
		apvt=ecc.getAlice_private();
		bpub=ecc.getBob_public();
		bpvt=ecc.getBob_private();
		HashMap<String, String> obj=new HashMap<>();
		obj.put("apub",apub);
		obj.put("apvt",apvt);
		obj.put("bpub",bpub);
		obj.put("bpvt",bpvt);
		return obj;
	}
	
	@RequestMapping("ecc_sharedkey")
	public Map<String, String> sharedkey(@RequestParam("pvt") String pvt,@RequestParam("pub") String pub,ECC ecc) throws Exception {
		ecc.setShared_key(pvt,pub);
		sharedkey=ecc.getShared_key();
		HashMap<String, String> obj=new HashMap<>();
		obj.put("apub",apub);
		obj.put("apvt",apvt);
		obj.put("bpub",bpub);
		obj.put("bpvt",bpvt);
		obj.put("sharedkey", sharedkey);
		return obj;
	}
	
	@RequestMapping("aes_en")
	public Map<String, String> aes_en(@RequestParam("pt") String pt,@RequestParam("key") String sharedkey,ECC ecc) throws Exception {
		Ct=ecc.encryptMessage(pt, sharedkey);
		HashMap<String, String> obj=new HashMap<>();
		obj.put("plain", pt);
		obj.put("sharedkey", sharedkey);
		obj.put("ct", Ct);
		return obj;
	}
	
	@RequestMapping("aes_de")
	public Map<String, String> aes_de(@RequestParam("ct") String ct,@RequestParam("key") String sharedkey,ECC ecc) throws Exception {
		Pt=ecc.decryptMessage(ct, sharedkey);
		HashMap<String, String> obj=new HashMap<>();
		obj.put("cipher", ct);
		obj.put("sharedkey", sharedkey);
		obj.put("pt", Pt);
		return obj;
	}
	
	@RequestMapping("test")
	public Map<String, String> test() {
		HashMap<String, String> obj=new HashMap<>();
		obj.put("name","siva");
		obj.put("class","5th sem");
		
		return obj;
	}
}
