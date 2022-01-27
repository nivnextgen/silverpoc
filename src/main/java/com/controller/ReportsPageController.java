package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ReportsPageController {

	
	@GetMapping("/reportsPage")
	String studentIndexPage(HttpServletRequest request, HttpServletResponse response) {

		return "reportsPage";
	}
}
