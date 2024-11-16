package com.group.Crypto_Project;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.group.Algos.ECC;
import com.group.Algos.RSA;

@Controller
public class homeController {
	
	String Private="";
	String Public="";
	String Ct="";
	String Pt="";
	String apub="";
	String apvt="";
	String bpub="";
	String bpvt="";
	String sharedkey="";
	
	RSA rsa=new RSA();
	
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	@RequestMapping("/t")
	public String t() {
		return "test";
	}
	
	
	@RequestMapping("rsa")
	public void rsa(@RequestParam("bits") int bits,ModelMap m) throws Exception {
		rsa.setBits(bits);
		rsa.generateKeys();
		Private=rsa.getPrivateKey();
		Public=rsa.getPublicKey();
		m.addAttribute("pvt", Private);
		m.addAttribute("pub", Public);
	}
	
	@RequestMapping("encrypt")
	public String encrypt(@RequestParam("pub") String key,@RequestParam("pt") String pt,ModelMap m) throws Exception {
		Ct=rsa.encrypt(pt, key);
		m.addAttribute("plain",pt);
		m.addAttribute("pvt", Private);
		m.addAttribute("pub", Public);
		m.addAttribute("ct", Ct);
		return "rsa";
	}
	
	@RequestMapping("decrypt")
	public String decrypt(@RequestParam("pvt") String key,@RequestParam("ct") String ct,ModelMap m) throws Exception {
		Pt=rsa.decrypt(ct, key);
		m.addAttribute("cipher", ct);
		m.addAttribute("pvt", Private);
		m.addAttribute("pub", Public);
		m.addAttribute("pt", Pt);
		
		return "rsa";
	}
	
	@RequestMapping("ecc")
	public String ecc(ECC ecc,ModelMap m) throws Exception {
		ecc.generate();
		apub=ecc.getAlice_public();
		apvt=ecc.getAlice_private();
		bpub=ecc.getBob_public();
		bpvt=ecc.getBob_private();
		m.addAttribute("apub",apub);
		m.addAttribute("apvt",apvt);
		m.addAttribute("bpub",bpub);
		m.addAttribute("bpvt",bpvt);
		return "ecc";
	}
	
	@RequestMapping("sharedkey")
	public String sharedkey(@RequestParam("pvt") String pvt,@RequestParam("pub") String pub,ECC ecc,ModelMap m) throws Exception {
		ecc.setShared_key(pvt,pub);
		sharedkey=ecc.getShared_key();
		m.addAttribute("apub",apub);
		m.addAttribute("apvt",apvt);
		m.addAttribute("bpub",bpub);
		m.addAttribute("bpvt",bpvt);
		m.addAttribute("sharedkey", sharedkey);
		return "ecc";
	}
	
	@RequestMapping("aes_encrypt")
	public String aes_en(@RequestParam("pt") String pt,@RequestParam("key") String sharedkey,ECC ecc,ModelMap m) throws Exception {
		Ct=ecc.encryptMessage(pt, sharedkey);
		m.addAttribute("plain", pt);
		m.addAttribute("sharedkey", sharedkey);
		m.addAttribute("ct", Ct);
		return "aes";
	}
	
	@RequestMapping("aes_decrypt")
	public String aes_de(@RequestParam("ct") String ct,@RequestParam("key") String sharedkey,ECC ecc,ModelMap m) throws Exception {
		Pt=ecc.decryptMessage(ct, sharedkey);
		m.addAttribute("cipher", ct);
		m.addAttribute("sharedkey", sharedkey);
		m.addAttribute("pt", Pt);
		return "aes";
	}
	
	
}
