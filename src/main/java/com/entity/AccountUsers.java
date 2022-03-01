package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account_users")
public class AccountUsers {

	@Id
	@Column(name="USER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int USER_ID;
	
	@Column(name="USERNAME")
	private String USERNAME;
	@Column(name="UPASSWORD")
	private String UPASSWORD;
	@Column(name="UPASSWORD")
	private String EMAIL;
	@Column(name="IS_ACTIVE")
	private Boolean IS_ACTIVE;
	@Column(name="COMMENTS")
	private String COMMENTS;

	
	public int getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(int uSER_ID) {
		USER_ID = uSER_ID;
	}

	public String getUSERNAME() {
		return USERNAME;
	}

	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}

	public String getUPASSWORD() {
		return UPASSWORD;
	}

	public void setUPASSWORD(String uPASSWORD) {
		UPASSWORD = uPASSWORD;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public Boolean getIS_ACTIVE() {
		return IS_ACTIVE;
	}

	public void setIS_ACTIVE(Boolean iS_ACTIVE) {
		IS_ACTIVE = iS_ACTIVE;
	}

	public String getCOMMENTS() {
		return COMMENTS;
	}

	public void setCOMMENTS(String cOMMENTS) {
		COMMENTS = cOMMENTS;
	}

	@Override
	public String toString() {
		return "AccountUsers [USER_ID=" + USER_ID + ", USERNAME=" + USERNAME + ", UPASSWORD=" + UPASSWORD + ", EMAIL="
				+ EMAIL + ", IS_ACTIVE=" + IS_ACTIVE + ", COMMENTS=" + COMMENTS + "]";
	}
	
	

}
