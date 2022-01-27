package com.controller;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.service.IAccountUsersService;
import com.service.IStudentDetailsService;

@Controller
public class StudentPageController {
	
	@Autowired
	private IStudentDetailsService StudentPageController;
	
	@GetMapping("/studentDetailsPage")
	String studentIndexPage(HttpServletRequest request, HttpServletResponse response) {
		return "studentDetailsPage";
	}
	
	@PostMapping("/studentDetailsPage")
	String studentpage(HttpServletRequest request, HttpServletResponse response) { 
		
		
		String studentName = request.getParameter("studentname");
		String studentDOB= request.getParameter("studentdob");
		String studentGender = request.getParameter("gender");
		String parentName = request.getParameter("parentname");
		String parentEmail = request.getParameter("parentemail");
		int parentNumber = Integer.valueOf(request.getParameter("parentnumber"));

		DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		Date studentCAge;
		
		try {
			studentCAge = formatter.parse(studentDOB);
			int age = getAge(studentCAge);
			

			System.out.println("studentName :: " + studentName);
	        System.out.println("studentDOB  :: "+studentDOB);
	        System.out.println("studentCAge  :: "+studentCAge);
			System.out.println("studentAge :: " + age);
			System.out.println("studentGender :: " + studentGender);
			System.out.println("studentClass :: " + parentName);
			System.out.println("studentAge :: " + parentEmail);
			System.out.println("parentNumber :: " + parentNumber );
			
			
			// TODO Write logic to post data to DB
			
			boolean datainDB = StudentPageController.studentDetails(studentName, studentCAge, age, studentGender, parentName, parentEmail, parentNumber);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return "studentDetailsPage";
	}
	
	
	// To calculate age from DOB
	public int getAge(Date dateOfBirth) {
	    int age = 0;
	    Calendar born = Calendar.getInstance();
	    Calendar now = Calendar.getInstance();
	    if(dateOfBirth!= null) {
	        now.setTime(new Date());
	        born.setTime(dateOfBirth);  
	        if(born.after(now)) {
	            throw new IllegalArgumentException("Can't be born in the future");
	        }
	        age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);             
	        if(now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR))  {
	            age-=1;
	        }
	    }  
	    return age;
	}
	
	
	
}
