package com.service;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class StudentDetailsService implements IStudentDetailsService{
	
	
	@Override
	public boolean studentDetails(String studentName,Date studentCAge,int age,String studentGender,String parentName,String parentEmail,int parentNumber) {
		
		
		System.out.println("*** In StudentDetailsService Service ***");
		System.out.println("studentName :: " + studentName);
        System.out.println("studentCAge  :: "+studentCAge);
		System.out.println("studentAge :: " + age);
		System.out.println("studentGender :: " + studentGender);
		System.out.println("studentClass :: " + parentName);
		System.out.println("studentAge :: " + parentEmail);
		System.out.println("parentNumber :: " + parentNumber );
		
		return true;
	}

}
