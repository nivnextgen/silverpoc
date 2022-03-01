package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.StudentReportsDetails;
import com.example.UserT;
import java.util.List;

@Controller
public class ReportsPageController {


	@Autowired
	private DataSource dataSource;
	
	@GetMapping("/reportsPage")
	String studentIndexPage(Map<String, Object> model) {
		
		try (Connection connection = dataSource.getConnection()) {
			System.out.println("******In DB connection reportsPage*****");
			
			// Db connection
			Statement stmt = connection.createStatement();
			
			// Query
			ResultSet rsemail = stmt.executeQuery("SELECT student_id,student_name FROM STUDENTS");
			List<UserT> studentListOutput = new ArrayList<UserT>();
			while (rsemail.next()) {
				UserT user1 = new UserT();
				user1.setName(rsemail.getString("student_name"));
				user1.setId(rsemail.getInt("student_id"));
				studentListOutput.add(user1);
			}
			
			// Insert Data
			model.put("records", studentListOutput);	

	      return "reportsPage";
	    } catch (Exception e) {
	    	System.out.println(e);
	      return "error";
	    }
	}
	
	@PostMapping("/reportsPage")
	String homepage(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) { 
		

	    try (Connection connection = dataSource.getConnection()) {
	    	System.out.println("******In DB connection *****");
	      Statement stmt = connection.createStatement();
	      
			ResultSet studentDetials = stmt.executeQuery("SELECT student_id,student_name FROM STUDENTS");
			List<UserT> studentDetailsOutput = new ArrayList<UserT>();
			while (studentDetials.next()) {
				UserT user1 = new UserT();
				user1.setName(studentDetials.getString("student_name"));
				user1.setId(studentDetials.getInt("student_id"));
				studentDetailsOutput.add(user1);

			}
			model.put("records", studentDetailsOutput);
	      
	      System.out.println("Selected student is :: " +Integer.valueOf(request.getParameter("studentID")));
	      
	      
	      String studentsReportstmt = "SELECT phase,SCORE_TYPE,FULL_SCORES FROM total_scores where STUDENT_ID=" 
	    		  						+Integer.valueOf(request.getParameter("studentID"))
	    		  						+ " and "
	    		  						+ "SCORE_TYPE IN ('ENGINDX','ATTINDX','PERFINDX')";
	    		  						
          PreparedStatement studentsReportpstmt = connection.prepareStatement(studentsReportstmt);	      
	      ResultSet resultSetStudentReport = studentsReportpstmt.executeQuery();
	      
	      ArrayList<String> testOutput = new ArrayList<String>();
	      int engIndexScore, attIndexScore, perfIndexScore, resultPhase;
	      

    	  testOutput.add("******** Student Report  for phase ********");

	      while (resultSetStudentReport.next()) {
	   // 	  testOutput.add("The value of SCORE_TYPE is : " +resultSetStudentReport.getString("SCORE_TYPE") );
	    	  
	    	  resultPhase = resultSetStudentReport.getInt("phase");

		      if(resultSetStudentReport.getString("SCORE_TYPE").contentEquals("ENGINDX")) {
		    	  engIndexScore = resultSetStudentReport.getInt("FULL_SCORES");
		    	  testOutput.add("ENGINDX for phase " +resultPhase +" is : " +engIndexScore );
		      }
		      if(resultSetStudentReport.getString("SCORE_TYPE").contentEquals("ATTINDX")) {
		    	  attIndexScore = resultSetStudentReport.getInt("FULL_SCORES");
		    	  testOutput.add("ATTINDX for phase "  +resultPhase +" is : " +attIndexScore );
		      }
		      if(resultSetStudentReport.getString("SCORE_TYPE").contentEquals("PERFINDX")) {
		    	  perfIndexScore = resultSetStudentReport.getInt("FULL_SCORES");
		    	  testOutput.add("PERFINDX for phase "  +resultPhase +" is : "  +perfIndexScore );
		      }
	      }
	      
	      model.put("resultRecord", testOutput);
	      
//		List<StudentReportsDetails> studentDisplayRespult = new ArrayList<StudentReportsDetails>();
//	      while (resultSetStudentReport.next()) {
//		      StudentReportsDetails studentReportsInst = new StudentReportsDetails();
//		      studentReportsInst.setPhase(resultSetStudentReport.getInt("phase"));
//		      
//		      
//		      
//		      studentDisplayRespult.add(studentReportsInst);
//			}
//	      
//			model.put("studentResultDisplays", studentDisplayRespult);
   
	      	      
	    } catch (Exception e) {
	    	System.out.println(e);
	      return "error";
	    }
		
		
		return "reportsPage";
	}
	
}
