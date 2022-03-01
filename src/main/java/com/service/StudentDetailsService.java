package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentDetailsService implements IStudentDetailsService{
	
	@Autowired
	private DataSource dataSource;
	
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
		
	    try (Connection connection = dataSource.getConnection()) {
		      System.out.println("******In DB execution updating student *****");	      
		      String statement1 = "INSERT INTO STUDENTS(STUDENT_NAME,DOB,GENDER,AGE,PARENT_NAME,EMAIL,PHONE_NUMBER) VALUES ('"
		    		  +studentName +"','" +studentCAge +"','" +studentGender +"'," +age +",'" +parentName +"','" +parentEmail +"'," +parentNumber
		              +")";
		          PreparedStatement q = connection.prepareStatement(statement1);
		          q.executeUpdate();
		    } catch (Exception e) {
		      System.out.println(e);
		    }
		  
		return true;
	}

}
