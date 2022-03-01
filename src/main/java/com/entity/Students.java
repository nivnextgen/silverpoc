package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Date;

@Entity
@Table(name="students")
public class Students {
	
	@Id
	@Column(name="STUDENT_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int STUDENT_ID;
	
	
	@Column(name="STUDENT_NAME")
	private String STUDENT_NAME;
	
	@Column(name="DOB")
	private Date DOB;
	
	@Column(name="GENDER")
	private String GENDER;
	
	@Column(name="AGE")
	private int AGE;
	
	@Column(name="PARENT_NAME")
	private String PARENT_NAME;
	
	@Column(name="EMAIL")
	private String EMAIL;
	
	
	@Column(name="PHONE_NUMBER")
	private int PHONE_NUMBER;


	public int getSTUDENT_ID() {
		return STUDENT_ID;
	}


	public void setSTUDENT_ID(int sTUDENT_ID) {
		STUDENT_ID = sTUDENT_ID;
	}


	public String getSTUDENT_NAME() {
		return STUDENT_NAME;
	}


	public void setSTUDENT_NAME(String sTUDENT_NAME) {
		STUDENT_NAME = sTUDENT_NAME;
	}


	public Date getDOB() {
		return DOB;
	}


	public void setDOB(Date dOB) {
		DOB = dOB;
	}


	public String getGENDER() {
		return GENDER;
	}


	public void setGENDER(String gENDER) {
		GENDER = gENDER;
	}


	public int getAGE() {
		return AGE;
	}


	public void setAGE(int aGE) {
		AGE = aGE;
	}


	public String getPARENT_NAME() {
		return PARENT_NAME;
	}


	public void setPARENT_NAME(String pARENT_NAME) {
		PARENT_NAME = pARENT_NAME;
	}


	public String getEMAIL() {
		return EMAIL;
	}


	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}


	public int getPHONE_NUMBER() {
		return PHONE_NUMBER;
	}


	public void setPHONE_NUMBER(int pHONE_NUMBER) {
		PHONE_NUMBER = pHONE_NUMBER;
	}
	
	

}
