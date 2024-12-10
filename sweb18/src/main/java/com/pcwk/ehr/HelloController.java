package com.pcwk.ehr;

import javax.annotation.Generated;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	@RequestMapping(value = "/hello.do", method = RequestMethod.GET)
	public String hello(Model model) {
		System.out.println("Hello, Spring MVC!");
		
		model.addAttribute("message", "Hello, Spring MVC!");
		
		return "hello";
	}
	
	@GetMapping("/test.do")
	public String test1() {
		return "test";
	}
	
}
