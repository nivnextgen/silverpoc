package com.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.IAccountUsersService;

@Controller
public class LoginPageContoller {

	@Autowired
	private IAccountUsersService accountUsersService;

	@GetMapping("/loginPage")
	String studentIndexPage(HttpServletRequest request, HttpServletResponse response) {

		return "loginPage";
	}

	@PostMapping("/loginPage")
	String loginPage(HttpServletRequest request, HttpServletResponse response) {

		String username = request.getParameter("userid");
		String password = request.getParameter("userpwd");
		System.out.println("Values are   :: " + username + "  :::  " + password);

		if (username != null && password != null) {
			if (username.equals("teacher") && password.equals("1234")) {
				System.out.println("User authenticated  : " + username);
				
				// Authenticate here
				accountUsersService.userAccounts(username, password);

				try {
					response.sendRedirect("/homePage");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return "loginPage";
		}

		return "loginPage";
	}

}
