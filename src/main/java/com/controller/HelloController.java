package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HelloController {
	
	  @RequestMapping("/")
	  String index() {
	    return "index";
	  }
	  
	  @RequestMapping("/homePage")
	  String homePage() {
	    return "homePage";
	  }
	  
	  @RequestMapping("/loginPage")
	  String loginPage() {
	    return "loginPage";
	  }
	  
	  @RequestMapping("/formSelectPage")
	  String formSelectPage() {
	    return "formSelectPage";
	  }
	  
	  @RequestMapping("/formOnePage")
	  String formOnePage() {
	    return "formOnePage";
	  }

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

}