package com.group.Crypto_Project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class homeController {
	
	@RequestMapping("/")
	public String index() {
		return "index.jsp";
	}
	@RequestMapping("/home")
	public String home() {
		return "home.html";
	}

	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		return "hello this is test";
	}
	//for test

}
