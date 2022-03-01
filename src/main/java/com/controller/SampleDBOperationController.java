package com.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Controller
public class SampleDBOperationController {
//	  @Value("${spring.datasource.url}")
//	  private String dbUrl;
//
	  @Autowired
	  private DataSource dataSource;
//	
	@RequestMapping("/test1")
	  String db(Map<String, Object> model) {
	    try (Connection connection = dataSource.getConnection()) {
	    	System.out.println("******In DB connection *****");
	      Statement stmt = connection.createStatement();
//	      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
//	      stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
	      ResultSet rs = stmt.executeQuery("SELECT * FROM account_users" );
	      ArrayList<String> output = new ArrayList<String>();
	      while (rs.next()) {
	       // output.add("Read from DB: " + rs.getTimestamp("tick"));
		        output.add("Read from DB: "  +rs.getString("email"));
	      }
	      
//	      ResultSet rsemail = stmt.executeQuery("SELECT email FROM account_users");
//	      ArrayList<String> output1 = new ArrayList<String>();
//	      while (rsemail.next()) {
//		       // output.add("Read from DB: " + rs.getTimestamp("tick"));
//	    	  output1.add("Read from DB: " + rsemail.getString("email"));
//
//		      }

	      model.put("records", output);
	      return "db";
	    } catch (Exception e) {
	    	System.out.println(e);
	      model.put("message", e.getMessage());
	      return "error";
	    }
	  }
//	
//	  @Bean
//	  public DataSource dataSource() throws SQLException {
//	      System.out.println("******In line 62*****" +dbUrl);
//		  if (dbUrl == null || dbUrl.isEmpty()) {
//	      return new HikariDataSource();
//	    } else {
//	      HikariConfig config = new HikariConfig();
//	      config.setJdbcUrl(dbUrl);
//	      System.out.println("******In line 87*****" +dbUrl);
//	      return new HikariDataSource(config);
//	    }
//	  }


}
