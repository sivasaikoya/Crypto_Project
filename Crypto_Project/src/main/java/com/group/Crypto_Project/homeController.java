package com.group.Crypto_Project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class homeController {
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	

	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		return "hello this is test";
	}
	//for test

}
