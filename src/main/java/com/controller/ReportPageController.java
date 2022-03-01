package com.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReportPageController {
	
	@Autowired
	private DataSource dataSource;

	@GetMapping("/reportPage")
	String reportIndexPage(Map<String, Object> model, Model modell) {

		 
			    try (Connection connection = dataSource.getConnection()) {
			    	System.out.println("******In DB connection *****");
			      Statement stmt = connection.createStatement();
			      ResultSet rs = stmt.executeQuery("SELECT * FROM form_eval" );
			      ArrayList<String> output = new ArrayList<String>();

			      
			     
			      while (rs.next()) {
				      output.add("******** Section 1 raw values are ********");
			    	  output.add("The value of Social Participation	is : " +rs.getString("que1") +" "  +rs.getString("que2") +" "  +rs.getString("que3") +" "  +rs.getString("que4") +" "  +rs.getString("que5") +" "  +rs.getString("que6") +" "  +rs.getString("que7") +" "  +rs.getString("que8") +" "  +rs.getString("que9") +" "  +rs.getString("que10") +" ");
			    	  output.add("The value of Vision is : " +rs.getString("que11") +" " +rs.getString("que12") +" " +rs.getString("que13") +" " +rs.getString("que14") +" " +rs.getString("que15") +" " +rs.getString("que16") +" " +rs.getString("que17") +" ");
			    	  output.add("The value of Hearing	is : " +rs.getString("que18") +" " +rs.getString("que19") +" " +rs.getString("que20") +" " +rs.getString("que21") +" " +rs.getString("que22") +" " +rs.getString("que23") +" " +rs.getString("que24"));
			    	  output.add("The value of Touch	is : " +rs.getString("que25") +" "+rs.getString("que26") +" "+rs.getString("que27") +" "+rs.getString("que28") +" "+rs.getString("que29") +" "+rs.getString("que30") +" "+rs.getString("que31") +" "+rs.getString("que32") +" ");
			    	  output.add("The value of Taste and Smell	is : " +rs.getString("que33") +" "+rs.getString("que34") +" "+rs.getString("que35") +" "+rs.getString("que36") +" ");
			    	  output.add("The value of Body Awareness	is : " +rs.getString("que37") +" "+rs.getString("que38") +" "+rs.getString("que39") +" "+rs.getString("que40") +" "+rs.getString("que41") +" "+rs.getString("que42") +" "+rs.getString("que43") +" ");
			    	  output.add("The value of Balance and Motion	is : " +rs.getString("que44") +" "+rs.getString("que45") +" "+rs.getString("que46") +" "+rs.getString("que47") +" "+rs.getString("que48") +" "+rs.getString("que49") +" "+rs.getString("que50") +" "+rs.getString("que51") +" "+rs.getString("que52") +" ");
			    	  output.add("The value of Planning & Ideas	is : " +rs.getString("que53") +" "+rs.getString("que54") +" "+rs.getString("que55") +" "+rs.getString("que56") +" "+rs.getString("que57") +" "+rs.getString("que58") +" "+rs.getString("que59") +" "+rs.getString("que60") +" "+rs.getString("que61") +" "+rs.getString("que62") +" ");
			      }
			      ResultSet rs1 = stmt.executeQuery("SELECT * FROM eval_rawscore" );
			      
			      while (rs1.next()) {
			    	  output.add("******** Section 2 raw values are ********");
				      output.add("The eval raw score of Social Participation	is : "+rs1.getString("SCO_RS"));
				      output.add("The eval raw score of Vision	is : "+rs1.getString("VIS_RS"));
				      output.add("The eval raw score of Hearing	is : "+rs1.getString("HEA_RS"));
				      output.add("The eval raw score of Touch	is : "+rs1.getString("TOU_RS"));
				      output.add("The eval raw score of Taste and Smell	is : "+rs1.getString("TNS_RS"));
				      output.add("The eval raw score of Body Awareness	is : "+rs1.getString("BOD_RS"));
				      output.add("The eval raw score of Balance and Motion	is : "+rs1.getString("BAL_RS"));
				      output.add("The eval raw score of Planning & Ideas	is : "+rs1.getString("PLA_RS"));
				      output.add("The eval raw score of total	is : "+rs1.getString("FULL_RS"));
			      }
			      
			      ResultSet rs2 = stmt.executeQuery("SELECT * FROM total_scores" );
			      
					while (rs2.next()) {
							output.add("******** ******* Calculated values are ******* ********");
							output.add("The Type of value is : " + rs2.getString("SCORE_TYPE"));
							output.add("The eval raw score of Social Participation	is : " + rs2.getString("SCO"));
							output.add("The eval raw score of Vision	is : " + rs2.getString("VIS"));
							output.add("The eval raw score of Hearing	is : " + rs2.getString("HEA"));
							output.add("The eval raw score of Touch	is : " + rs2.getString("TOU"));
							output.add("The eval raw score of Body Awareness	is : " + rs2.getString("BOD"));
							output.add("The eval raw score of Balance and Motion	is : " + rs2.getString("BAL"));
							output.add("The eval raw score of Planning & Ideas	is : " + rs2.getString("PLA"));
							output.add("The eval raw score of total	is : " + rs2.getString("FULL_SCORES"));
					}			      
			      
			      ArrayList<String> dbOutput = new ArrayList<String>();
			      
		//	      List<AccountUsers> list = new ArrayList<AccountUsers> ();
			      
			      ResultSet testr2 = stmt.executeQuery("SELECT * FROM eval_rawscore" );

			      while (testr2.next()) {
			    	  dbOutput.add(testr2.getString("SCO_RS"));
			    	  dbOutput.add(testr2.getString("VIS_RS"));
			    	  dbOutput.add(testr2.getString("HEA_RS"));
			    	  dbOutput.add(testr2.getString("TOU_RS"));
			    	  dbOutput.add("The eval raw score of Taste and Smell	is : "+testr2.getString("TNS_RS"));
			    	  dbOutput.add("The eval raw score of Body Awareness	is : "+testr2.getString("BOD_RS"));
			    	  dbOutput.add("The eval raw score of Balance and Motion	is : "+testr2.getString("BAL_RS"));
			    	  dbOutput.add("The eval raw score of Planning & Ideas	is : "+testr2.getString("PLA_RS"));
			    	  dbOutput.add("The eval raw score of total	is : "+testr2.getString("FULL_RS"));
			    	}

			      model.put("records", output);

//			      ArrayList<String> roomReservations = new ArrayList<String>();
//			      while (rs1.next()) {
//			    	  roomReservations.add("Read from DB: " + rs1.getTimestamp("SCO_RS"));
//				      roomReservations.add("Read from DB: " + rs1.getString("VIS_RS"));
//		
//				      }
//			      
//			      modell.addAttribute("roomReservations", roomReservations);
			      
			      return "reportPage";
			    } catch (Exception e) {
			    	System.out.println(e);
			      model.put("message", e.getMessage());
			      return "error";
			    }
			  }
	
	@PostMapping("/reportPage")
	String reportPage(HttpServletRequest request, HttpServletResponse response) {

		return "reportPage";
	}
	
	
	
}
