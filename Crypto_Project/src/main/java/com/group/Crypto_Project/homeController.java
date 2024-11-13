package com.group.Crypto_Project;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.group.Algos.ECC;

@Controller
public class homeController {
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("rsa")
	public String rsa() {
		return "rsa";
	}
	
	@RequestMapping("res")
	public String res(ModelMap m) {
		m.addAttribute("str", "Sivasai");
		return "res";
	}
	@RequestMapping("test")
	@ResponseBody
	public String test() {
		return "hello this is test";
	}
	
	@RequestMapping("ecc")
	public String ecc(ECC ecc,ModelMap m) {
		m.addAttribute("str",ecc.getAlice_private());
		return "res";
	}

}
