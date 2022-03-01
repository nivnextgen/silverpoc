package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.UserT;

@Controller
public class ReportsPageController1 {

	@Autowired
	private DataSource dataSource;

	@GetMapping("/test001")
	String studentIndexPage(Map<String, Object> model, Model modell) {

		try (Connection connection = dataSource.getConnection()) {
			System.out.println("******In DB connection reportsPage*****");
			Statement stmt = connection.createStatement();
			ResultSet rsemail = stmt.executeQuery("SELECT student_id,student_name FROM STUDENTS");
			
			
			List<UserT> output1 = new ArrayList<UserT>();
			while (rsemail.next()) {
				UserT user1 = new UserT();
				user1.setName(rsemail.getString("student_name"));
				user1.setId(rsemail.getInt("student_id"));
				output1.add(user1);

			}
			model.put("records", output1);

			return "test001";
		} catch (Exception e) {
			System.out.println(e);
			return "error";
		}
	}
	
	@PostMapping("/test001")
	String homepage(HttpServletRequest request, HttpServletResponse response) { 
				int studentId = Integer.valueOf(request.getParameter("studentID"));
		
		System.out.println("studentId value is " +studentId);


		
		try {
			System.out.println("******** test001 page ********");
			response.sendRedirect("/test001");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "test001";
	}
	
	
	
	
	
}
