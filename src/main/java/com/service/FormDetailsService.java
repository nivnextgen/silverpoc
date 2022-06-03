package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.example.EvalRawscoreInst;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Service
public class FormDetailsService implements IFormDetailsService {
	
//	@Value("${spring.datasource.url}")
//	  private String dbUrl;

	@Autowired
	private DataSource dataSource;

	@Override
	public boolean ansValues(int studentIdfromUI, int[] ansValues) {

//		for (int num : ansValues) {
//			System.out.println("The loop all the solutions are  " + num);
//		}
		
		// Raw Data to DB
		evaldbUpdate(studentIdfromUI, ansValues);
		
		// Calculate Raw score and to DB
		boolean rawScoreAssert = rawScoreEval(studentIdfromUI, ansValues);
		
		// Calculate T score and to DB
//		boolean tScoreAssert = tScore(studentIdfromUI);
		
		// Calculate NIV score and to DB
//		boolean nivScoreAssert = nivScore(studentIdfromUI);
		
		// Calculate index scores and to DB
	//	boolean indexScoreAssert = indexScores(studentIdfromUI);
		
		return true;

	}

	// Constants dec scores
	// For step 2
	String phase;
	
	// For step 3


	// Step 1 Storing all the values in "form_eval"
	public boolean formEval() {
		
		return true;
	}

	// Step 2 Calculating group raw scores and store in "eval_rawscore"
	public boolean rawScoreEval(int studentIdfromUI, int[] studentData) {

		int SCO_RS = 0, VIS_RS=0, HEA_RS=0, TOU_RS=0, TNS_RS=0, BOD_RS=0, BAL_RS=0, PLA_RS=0, FULL_RS=0;

		System.out.println("*********  Raw scores are as follows ***********");
		
		for (int i = 0; i < 10; i++) {
			SCO_RS = studentData[i] + SCO_RS;
		}
		
		for (int i = 10; i < 17; i++) {
			VIS_RS= studentData[i] + VIS_RS;
		}
		
		for (int i = 17; i < 24; i++) {
			HEA_RS = studentData[i] + HEA_RS;
		}
		
		for (int i = 24; i < 32; i++) {
			TOU_RS = studentData[i] + TOU_RS;
		}
		
		for (int i = 32 ; i < 36 ; i++) {
			TNS_RS = studentData[i] + TNS_RS;
		}	
		
		for (int i = 36 ; i < 43; i++) {
			BOD_RS = studentData[i] + BOD_RS;
		}
		
		for (int i = 43; i < 52; i++) {
			BAL_RS = studentData[i] + BAL_RS;
		}
		
		for (int i = 52; i < 62; i++) {
			PLA_RS = studentData[i] + PLA_RS;
		}
		
		FULL_RS = VIS_RS + HEA_RS + TOU_RS + TNS_RS + BOD_RS + BAL_RS;
		
		System.out.println("FULL_RS calculated score is " + FULL_RS);
		
		// Update in DB
		rawScoredbUpdate(studentIdfromUI, SCO_RS, VIS_RS, HEA_RS, TOU_RS, TNS_RS, BOD_RS, BAL_RS, PLA_RS, FULL_RS);
		

		System.out.println("*********  END ***********");
		
		tScore(studentIdfromUI, SCO_RS, VIS_RS, HEA_RS, TOU_RS, TNS_RS, BOD_RS, BAL_RS, PLA_RS, FULL_RS);
		
		return true;
	}

	// Step 3 logic for making total scores and storing them in total_scores
	// Need to eval TSCORE, NIVSCORE

	// Step 3.1 eval TSCORE for all modules
	public boolean tScore(int studentIdfromUI, int tempSCO_RS, int tempVIS_RS, int tempHEA_RS, int tempTOU_RS, int tempTNS_RS, int tempBOD_RS, int tempBAL_RS, int tempPLA_RS, int tempFULL_RS) {
			
		int temptSOCScore, temptVISScore, temptHEAScore, temptTOUScore, temptTnSScore, temptBODScore, temptBALScore, temptPLAScore, temptotFullScore;


			temptSOCScore = tScoreRange("SCO_RS", tempSCO_RS);
			temptVISScore= tScoreRange("VIS_RS", tempVIS_RS);
			temptHEAScore= tScoreRange("HEA_RS", tempHEA_RS);
			temptTOUScore= tScoreRange("TOU_RS", tempTOU_RS);
			temptTnSScore= tempTNS_RS;
			temptBODScore= tScoreRange("BOD_RS", tempBOD_RS);
			temptBALScore= tScoreRange("BAL_RS", tempBAL_RS);
			temptPLAScore= tScoreRange("PLA_RS", tempPLA_RS);
			temptotFullScore= tScoreRange("FULL_RS", tempFULL_RS);
			
			System.out.println("*********  END ***********");
			
			otherScoredbUpdate(studentIdfromUI, "TSCORE",temptSOCScore, temptVISScore, temptHEAScore, temptTOUScore, temptBODScore, temptBALScore, temptPLAScore, temptotFullScore);
		
			nivScore(studentIdfromUI, temptSOCScore, temptVISScore, temptHEAScore, temptTOUScore, temptTnSScore, temptBODScore, temptBALScore, temptPLAScore, temptotFullScore);
		return true;
	}

	// Step 3.2 eval NIVSCORE for all modules
	public boolean nivScore(int studentIdfromUI, int tSOCScore, int tVISScore, int tHEAScore, int tTOUScore, int tTnSScore, int tBODScore, int tBALScore, int tPLAScore, int totFullScore) {
		int nivSOC, nivVIS, nivHEA, nivTOU, nivTnS, nivBOD, nivBAL, nivPLA, nivFullScore;
		nivSOC = nivScoreRange(tSOCScore);
		nivVIS = nivScoreRange(tVISScore);
		nivHEA = nivScoreRange(tHEAScore);
		nivTOU = nivScoreRange(tTOUScore);
		nivTnS = nivScoreRange(tTnSScore);
		nivBOD = nivScoreRange(tBODScore);
		nivBAL = nivScoreRange(tBALScore);
		nivPLA = nivScoreRange(tPLAScore);
		nivFullScore = nivScoreRange(totFullScore);
		
		System.out.println("*********  nivSOC  ***********" +nivSOC);

		
		otherScoredbUpdate(studentIdfromUI, "NIVSCORE", nivSOC, nivVIS, nivHEA, nivTOU, nivBOD, nivBAL, nivPLA, nivFullScore);
		indexScores(studentIdfromUI, nivSOC, nivVIS, nivHEA, nivTOU, nivBOD, nivBAL, nivPLA, nivFullScore);
		return true;
	}

	// Step 3.3 eval ENGINDX, ATTINDX & PerfINDEX for all modules
	public boolean indexScores(int studentIdfromUI, int nivSOC, int nivVIS, int nivHEA, int nivTOU, int nivBOD, int nivBAL, int nivPLA, int nivFullScore) {
//		double engISOC, engIVIS, engIHEA, engITOU, engITnS, engIBOD, engIBAL, engIPLA, engITotal;
//		double attISOC, attIVIS, attIHEA, attITOU, attITnS, attIBOD, attIBAL, attIPLA, attITotal;
//		double perfIndex;
		
		double engISOC=0, engIVIS=0, engIHEA=0, engITOU=0, engITnS=0, engIBOD=0, engIBAL=0, engIPLA=0, engITotal;
		double attISOC=0, attIVIS=0, attIHEA=0, attITOU=0, attITnS=0, attIBOD=0, attIBAL=0, attIPLA=0, attITotal;
		double perfIndex=0;

		engISOC = roundingNUmbers((15.15 * nivSOC) / 100);
		engIVIS = roundingNUmbers((15.15 * nivVIS) / 100);
		engIHEA = roundingNUmbers((15.15 * nivHEA) / 100);
		engITOU = roundingNUmbers((11.36 * nivTOU) / 100);
		engIBOD = roundingNUmbers((13.63 * nivBOD) / 100);
		engIBAL = roundingNUmbers((14.40 * nivBAL) / 100);
		engIPLA = roundingNUmbers((15.15 * nivPLA) / 100);
		engITotal = engISOC + engIVIS + engIHEA + engITOU + engIBOD + engIBAL + engIPLA;
		engITotal = roundingNUmbers(engITotal);
		
		System.out.println("  ");
		System.out.println("*********  Engagement index scores are as follows ***********");
		
		System.out.println("engISOC score is " + engISOC);
		System.out.println("engIVIS score is " + engIVIS);
		System.out.println("engIHEA score is " + engIHEA);
		System.out.println("engITOU score is " + engITOU);
		System.out.println("engIBOD score is " + engIBOD);
		System.out.println("engIBAL score is " + engIBAL);
		System.out.println("engIPLA score is " + engIPLA);
		System.out.println("engITotal score is " + engITotal);
		
		System.out.println("*********  END ***********");
		
		indexScoredbUpdate(studentIdfromUI, "ENGINDX", engISOC, engIVIS, engIHEA, engITOU, engIBOD, engIBAL, engIPLA, engITotal);


		attISOC = roundingNUmbers((18.51 * nivSOC) / 100);
		attIVIS = roundingNUmbers((24.70 * nivVIS) / 100);
		attIHEA = roundingNUmbers((24.70 * nivHEA) / 100);
		attITOU = roundingNUmbers((3.7 * nivTOU) / 100);
		attIBOD = roundingNUmbers((1.23 * nivBOD) / 100);
		attIBAL = roundingNUmbers((2.47 * nivBAL) / 100);
		attIPLA = roundingNUmbers((24.70 * nivPLA) / 100);
		attITotal = attISOC + attIVIS + attIHEA + attITOU + attIBOD + attIBAL + attIPLA;
		attITotal = roundingNUmbers(attITotal);
		System.out.println("  ");
		System.out.println("*********  Attention index scores are as follows ***********");
		
		System.out.println("attISOC score is " + attISOC);
		System.out.println("attIVIS score is " + attIVIS);
		System.out.println("attIHEA score is " + attIHEA);
		System.out.println("attITOU score is " + attITOU);
		System.out.println("attIBOD score is " + attIBOD);
		System.out.println("attIBAL score is " + attIBAL);
		System.out.println("attIPLA score is " + attIPLA);
		System.out.println("attITotal score is " + attITotal);
		
		System.out.println("*********  END ***********");
		
		
		indexScoredbUpdate(studentIdfromUI, "ATTINDX", attISOC, attIVIS, attIHEA, attITOU, attIBOD, attIBAL, attIPLA, attITotal);

		
		// Step 4 eval performance index
		perfIndex = (engITotal + engITotal) / 2;
		System.out.println("  ");
		System.out.println("*********  Performance index score is ***********");
		System.out.println("perfIndex score is " + perfIndex);
		System.out.println("*********  END ***********");
		
		indexScoredbUpdate(studentIdfromUI, "PERFINDX", 0, 0, 0, 0, 0, 0, 0, perfIndex);

		
		return true;
	}

	
	
	// ***********     Supporting methods     *************
	
	
	// T score Range
	public int tScoreRange(String scoreType, int inputScore) {

		int val = inputScore;

		if(scoreType.equals("SCO_RS")) {
			switch (val) {
			case 40: return 80;
			case 39: return 80;
			case 38: return 78;
			case 37: return 77;
			case 36: return 76;
			case 35: return 74;
			case 34: return 73;
			case 33: return 72;
			case 32: return 70;
			case 31: return 69;
			case 30: return 68;
			case 29: return 66;
			case 28: return 65;
			case 27: return 64;
			case 26: return 63;
			case 25: return 62;
			case 24: return 61;
			case 23: return 60;
			case 22: return 59;
			case 21: return 57;
			case 20: return 56;
			case 19: return 54;
			case 18: return 53;
			case 17: return 52;
			case 16: return 50;
			case 15: return 49;
			case 14: return 47;
			case 13: return 45;
			case 12: return 44;
			case 11: return 41;
			case 10: return 40;
			default: break;
			}
		}
		
		if(scoreType.equals("VIS_RS")) {
			switch (val) {
			case 26: return 80;
			case 28: return 80;
			case 25: return 79;
			case 24: return 78;
			case 23: return 77;
			case 22: return 77;
			case 21: return 76;
			case 20: return 76;
			case 19: return 75;
			case 18: return 73;
			case 17: return 72;
			case 16: return 70;
			case 15: return 67;
			case 14: return 65;
			case 13: return 64;
			case 12: return 62;
			case 11: return 59;
			case 10: return 57;
			case 9: return 53;
			case 8: return 48;
			case 7: return 40;
			default: break;
			}
		}
		
		
		if(scoreType.equals("HEA_RS")) {
			switch (val) {
			case 28: return 80;
			case 27: return 80;
			case 26: return 80;
			case 25: return 80;
			case 24: return 80;
			case 22: return 78;
			case 23: return 78;
			case 21: return 77;
			case 19: return 76;
			case 20: return 76;
			case 18: return 75;
			case 17: return 74;
			case 16: return 72;
			case 15: return 69;
			case 14: return 67;
			case 13: return 65;
			case 12: return 63;
			case 11: return 61;
			case 10: return 59;
			case 9: return 56;
			case 8: return 52;
			case 7: return 43;
			default: break;
			}
		}
		
		if(scoreType.equals("TOU_RS")) {
			switch (val) {
			case 32: return 80;
			case 31: return 80;
			case 30: return 80;
			case 29: return 80;
			case 28: return 80;
			case 27: return 80;
			case 26: return 80;
			case 25: return 80;
			case 24: return 79;
			case 23: return 79;
			case 22: return 78;
			case 21: return 78;
			case 20: return 76;
			case 19: return 75;
			case 18: return 73;
			case 17: return 72;
			case 16: return 70;
			case 15: return 68;
			case 14: return 67;
			case 13: return 65;
			case 12: return 63;
			case 11: return 61;
			case 10: return 58;
			case 9: return 53;
			case 8: return 44;
			default: break;
			}
		}
		
		if(scoreType.equals("BOD_RS")) {
			switch (val) {
			case 32: return 80;
			case 31: return 80;
			case 30: return 80;
			case 29: return 80;
			case 28: return 80;
			case 27: return 80;
			case 26: return 80;
			case 25: return 80;
			case 24: return 79;
			case 23: return 79;
			case 22: return 77;
			case 21: return 75;
			case 20: return 72;
			case 19: return 70;
			case 18: return 69;
			case 17: return 68;
			case 16: return 67;
			case 15: return 66;
			case 14: return 65;
			case 13: return 63;
			case 12: return 61;
			case 11: return 59;
			case 10: return 57;
			case 9: return 54;
			case 8: return 52;
			case 7: return 42;
			default: break;
			}
		}
		
		if(scoreType.equals("BAL_RS")) {
			switch (val) {
			case 36: return 80;
			case 35: return 80;
			case 34: return 80;
			case 33: return 79;
			case 32: return 78;
			case 31: return 78;
			case 30: return 76;
			case 29: return 75;
			case 28: return 75;
			case 27: return 74;
			case 26: return 73;
			case 25: return 72;
			case 24: return 72;
			case 23: return 71;
			case 22: return 70;
			case 21: return 69;
			case 20: return 67;
			case 19: return 66;
			case 18: return 64;
			case 17: return 63;
			case 16: return 61;
			case 15: return 60;
			case 14: return 58;
			case 13: return 56;
			case 12: return 53;
			case 11: return 51;
			case 10: return 47;
			case 9: return 40;
			default: break;
			}
		}
		
		if(scoreType.equals("PLA_RS")) {
			switch (val) {
			case 40: return 80;
			case 39: return 79;
			case 38: return 78;
			case 37: return 76;
			case 36: return 75;
			case 35: return 74;
			case 34: return 74;
			case 33: return 73;
			case 32: return 73;
			case 31: return 72;
			case 30: return 72;
			case 29: return 71;
			case 28: return 70;
			case 27: return 68;
			case 26: return 67;
			case 25: return 66;
			case 24: return 64;
			case 23: return 63;
			case 22: return 62;
			case 21: return 62;
			case 20: return 61;
			case 19: return 60;
			case 18: return 59;
			case 17: return 58;
			case 16: return 57;
			case 15: return 55;
			case 14: return 54;
			case 13: return 52;
			case 12: return 50;
			case 11: return 47;
			case 10: return 40;
			default: break;
			}
		}
		
		if(scoreType.equals("FULL_RS")) {
			switch (val) {
	//		case 130 -- case 168: return 80;
	//		case 119 -- case 129: return 79;
			case 117: case 118: return 78;
			case 115: case 116: return 77;
	//		case 109 -- case 114: return 76;
			case 108: return 75;
	//		case 99 -- case 107: return 74;
			case 96: case 97: case 98: return 73;
			case 94: case 95: return 72;
	//		case 88: -- case 93: return 71;
			case 87: return 70;
			case 84: case 85: case 86: return 69;
			case 82: case 83: return 68;
			case 80: case 81: return 67;
			case 78: case 79: return 66;
			case 74: case 75: case 76: case 77: return 65;
			case 71: case 23: case 73: return 64;
			case 69: case 70: return 63;
			case 67: case 68: return 62;
			case 64: case 66: return 61;
			case 62: case 63: return 60;
			case 60: case 61: return 59;
			case 58: case 59: return 58;
			case 56: case 57: return 57;
			case 55: return 56;
			case 54: return 55;
			case 53: return 55;
			case 52: return 54;
			case 51: return 53;
			case 50: return 52;
			case 49: return 51;
			case 48: return 50;
			case 47: return 49;
			case 46: return 47;
			case 45: return 45;
			case 44: return 43;
			case 43: return 40;
			case 42: return 40;
			// 			

			default: 
				if (val >= 130 && val <= 168)
					return 80; 
				else if (val >= 119  && val <= 129)
					return 79; 
				else if (val >= 109 && val <= 114)
					return 76; 
				else if (val >= 99 && val <=  107)
					return 74; 
				else if (val >= 88 && val <= 93)
					return 71; 
//				else if (FULL_RS >=  && FULL_RS <= )
//					return 0; 
				break;
			}
		}
		
		return 0;
	}
	
	// NivScore Range
	
	public int nivScoreRange(int tScoreValue) {
	
		switch (tScoreValue) {
		case 40: return 800;
		case 41: return 800;
		case 42: return 800;
		case 43: return 770;
		case 44: return 750;
		case 45: return 720;
		case 46: return 700;
		case 47: return 670;
		case 48: return 650;
		case 49: return 625;
		case 50: return 600;
		case 51: return 595;
		case 52: return 590;
		case 53: return 582;
		case 54: return 570;
		case 55: return 555;
		case 56: return 540;
		case 57: return 525;
		case 58: return 510;
		case 59: return 500;
		case 60: return 485;
		case 61: return 480;
		case 62: return 470;
		case 63: return 460;
		case 64: return 465;
		case 65: return 450;
		case 66: return 435;
		case 67: return 420;
		case 68: return 410;
		case 69: return 400;
		case 70: return 387;
		case 71: return 385;
		case 72: return 380;
		case 73: return 370;
		case 74: return 360;
		case 75: return 350;
		case 76: return 340;
		case 77: return 330;
		case 78: return 320;
		case 79: return 310;
		case 80: return 300;
		default: break;
		}
		
		return 0;
		
	}

		public double roundingNUmbers(double roundingNumber) {
			
			roundingNumber = Math.round(roundingNumber * 100.0) / 100.0;
		return roundingNumber;
	}
		
	  private String evaldbUpdate(int studentIdInsert, int[] evaldbValues) {
		    try (Connection connection = dataSource.getConnection()) {
		    	System.out.println("******In DB connection *****");
	//	      Statement stmt = connection.createStatement();
		      
		      int q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,q11,q12,q13,q14,q15,q16,q17,q18,q19,q20,q21,q22,q23,q24,q25,q26,q27,q28,q29,q30,q31,q32,q33,q34,q35,q36,q37,q38,q39,q40,q41,q42,q43,q44,q45,q46,q47,q48,q49,q50,q51,q52,q53,q54,q55,q56,q57,q58,q59,q60,q61,q62;
		      q1 = evaldbValues[0];
		      q2 = evaldbValues[1];
		      q3 = evaldbValues[2];
		      q4 = evaldbValues[3];
		      q5 = evaldbValues[4];
		      q6 = evaldbValues[5];
		      q7 = evaldbValues[6];
		      q8 = evaldbValues[7];
		      q9 = evaldbValues[8];
		      q10 = evaldbValues[9];
		      q11 = evaldbValues[10];
		      q12 = evaldbValues[11];
		      q13 = evaldbValues[12];
		      q14 = evaldbValues[13];
		      q15 = evaldbValues[14];
		      q16 = evaldbValues[15];
		      q17 = evaldbValues[16];
		      q18 = evaldbValues[17];
		      q19 = evaldbValues[18];
		      q20 = evaldbValues[19];
		      q21 = evaldbValues[20];
		      q22 = evaldbValues[21];
		      q23 = evaldbValues[22];
		      q24 = evaldbValues[23];
		      q25 = evaldbValues[24];
		      q26 = evaldbValues[25];
		      q27 = evaldbValues[26];
		      q28 = evaldbValues[27];
		      q29 = evaldbValues[28];
		      q30 = evaldbValues[29];
		      q31 = evaldbValues[30];
		      q32 = evaldbValues[31];
		      q33 = evaldbValues[32];
		      q34 = evaldbValues[33];
		      q35 = evaldbValues[34];
		      q36 = evaldbValues[35];
		      q37 = evaldbValues[36];
		      q38 = evaldbValues[37];
		      q39 = evaldbValues[38];
		      q40 = evaldbValues[39];
		      q41 = evaldbValues[40];
		      q42 = evaldbValues[41];
		      q43 = evaldbValues[42];
		      q44 = evaldbValues[43];
		      q45 = evaldbValues[44];
		      q46 = evaldbValues[45];
		      q47 = evaldbValues[46];
		      q48 = evaldbValues[47];
		      q49 = evaldbValues[48];
		      q50 = evaldbValues[49];
		      q51 = evaldbValues[50];
		      q52 = evaldbValues[51];
		      q53 = evaldbValues[52];
		      q54 = evaldbValues[53];
		      q55 = evaldbValues[54];
		      q56 = evaldbValues[55];
		      q57 = evaldbValues[56];
		      q58 = evaldbValues[57];
		      q59 = evaldbValues[58];
		      q60 = evaldbValues[59];
		      q61 = evaldbValues[60];
		      q62 = evaldbValues[61];
		      
		      // Updating the characteristic of the students score  // Ravi Write
		      String cq0, cq1, cq2, cq3, cq4, cq5, cq6, cq7, cq8, cq9, cq10, cq11, cq12, cq13, cq14, cq15, cq16, cq17, cq18, cq19, cq20, cq21, cq22, cq23, cq24, cq25, cq26, cq27, cq28, cq29, cq30, cq31, cq32, cq33, cq34, cq35, cq36, cq37, cq38, cq39, cq40, cq41, cq42, cq43, cq44, cq45, cq46, cq47, cq48, cq49, cq50, cq51, cq52, cq53, cq54, cq55, cq56, cq57, cq58, cq59, cq60, cq61 ;
		      cq0 = cq1 = cq2 = cq3 = cq4 = cq5 = cq6 = cq7 = cq8 = cq9 = cq10 = cq11 = cq12 = cq13 = cq14 = cq15 = cq16 = cq17 = cq18 = cq19 = cq20 = cq21 = cq22 = cq23 = cq24 = cq25 = cq26 = cq27 = cq28 = cq29 = cq30 = cq31 = cq32 = cq33 = cq34 = cq35 = cq36 = cq37 = cq38 = cq39 = cq40 = cq41 = cq42 = cq43 = cq44 = cq45 = cq46 = cq47 = cq48 = cq49 = cq50 = cq51 = cq52 = cq53 = cq54 = cq55 = cq56 = cq57 = cq58 = cq59 = cq60 = cq61 = "0";
		      
		      String IDEACHA	=	"Ideation";
		      String MPCHAR		=	"Motor Planning";
		      String ORCHAR		=	"Over Responsive";
		      String PERCCHAR	=	"Perception";
		      String PCCHAR		=	"Postural Control";
		      String SEKCHAR	=	"Seeking";
		      String URCHAR		=	"Under Responsive";
		      System.out.println("Print setting up ");
		      
		      for(int charInt = 10; charInt < 62; charInt++) {
			      System.out.println("Print for loop " +charInt);
		    	  
		    	  if(evaldbValues[charInt] > 2 ){	      
		    		  if (charInt == 53){ cq53 = IDEACHA;}  // Ideation
		    		  if (charInt == 59){ cq59 = IDEACHA;}
		    		  if (charInt == 60){ cq60 = IDEACHA;}
		    		  if (charInt == 61){ cq61 = IDEACHA;}	 
		    		  
		    		  if (charInt == 52){ cq52  = MPCHAR;}	// Motor Planning
		    		  if (charInt == 54){ cq54  = MPCHAR;}
		    		  if (charInt == 55){ cq55  = MPCHAR;}
		    		  if (charInt == 56){ cq56  = MPCHAR;}
		    		  if (charInt == 57){ cq57  = MPCHAR;}
		    		  if (charInt == 58){ cq58  = MPCHAR;}
		    		  
		    		  if (charInt == 10){ cq10	 = ORCHAR;}	// Over Responsive
		    		  if (charInt == 11){ cq11	 = ORCHAR;}
		    		  if (charInt == 12){ cq12	 = ORCHAR;}
		    		  if (charInt == 16){ cq16	 = ORCHAR;}
		    		  if (charInt == 17){ cq17	 = ORCHAR;}
		    		  if (charInt == 18){ cq18	 = ORCHAR;}
		    		  if (charInt == 24){ cq24	 = ORCHAR;}
		    		  if (charInt == 25){ cq25	 = ORCHAR;}
		    		  if (charInt == 26){ cq26	 = ORCHAR;}
		    		  if (charInt == 27){ cq27	 = ORCHAR;}
		    		  if (charInt == 32){ cq32	 = ORCHAR;}
		    		  
		    		  if (charInt == 20 ){ cq20	= PERCCHAR;}	// Perception
		    		  if (charInt == 34 ){ cq34	= PERCCHAR;}
		    		  if (charInt == 36 ){ cq36	= PERCCHAR;}
		    		  if (charInt == 38 ){ cq38	= PERCCHAR;}
		    		  
		    		  if (charInt == 44 ){ cq44 = PCCHAR;}	// Postural Control
		    		  if (charInt == 47 ){ cq47 = PCCHAR;}
		    		  if (charInt == 48 ){ cq48 = PCCHAR;}
		    		  if (charInt == 49 ){ cq49 = PCCHAR;}
		    		  if (charInt == 50 ){ cq50 = PCCHAR;}
		    		  if (charInt == 51 ){ cq51 = PCCHAR;}
		    		  
		    		  if (charInt == 14 ){ cq14 = SEKCHAR;}	// Seeking
		    		  if (charInt == 15 ){ cq15 = SEKCHAR;}
		    		  if (charInt == 21 ){ cq21 = SEKCHAR;}
		    		  if (charInt == 22 ){ cq22 = SEKCHAR;}
		    		  if (charInt == 23 ){ cq23 = SEKCHAR;}
		    		  if (charInt == 29 ){ cq29 = SEKCHAR;}
		    		  if (charInt == 30 ){ cq30 = SEKCHAR;}
		    		  if (charInt == 35 ){ cq35 = SEKCHAR;}
		    		  if (charInt == 37 ){ cq37 = SEKCHAR;}
		    		  if (charInt == 39 ){ cq39 = SEKCHAR;}
		    		  if (charInt == 40 ){ cq40 = SEKCHAR;}
		    		  if (charInt == 41 ){ cq41 = SEKCHAR;}
		    		  if (charInt == 42 ){ cq42 = SEKCHAR;}
		    		  if (charInt == 43 ){ cq43 = SEKCHAR;}
		    		  if (charInt == 45 ){ cq45 = SEKCHAR;}
		    		  if (charInt == 46 ){ cq46 = SEKCHAR;}
		    		  
		    		  if (charInt == 13 ){ cq13 = URCHAR;}	// Under Responsive
		    		  if (charInt == 19 ){ cq19 = URCHAR;}
		    		  if (charInt == 28 ){ cq28 = URCHAR;}
		    		  if (charInt == 31 ){ cq31 = URCHAR;}
		    		  if (charInt == 33 ){ cq33 = URCHAR;}
		    	  
		    	  }
		      }
		      
		      System.out.println("Print before the char db operation");
		      
		      // Updating characteristic form the scores
		      String charStatement1 = "INSERT INTO student_charst VALUES ("
		    		  +studentIdInsert
		    		  +",1,'"
		    		  +cq0 +"','"  +cq1 +"','"  +cq2+"','"  +cq3+"','"  +cq4+"','"  +cq5+"','"  +cq6+"','"  +cq7+"','"  +cq8+"','"  +cq9+"','"  +cq10+"','"  +cq11+"','"  +cq12+"','"  +cq13+"','"  +cq14+"','"  +cq15+"','"  +cq16+"','"  +cq17+"','"  +cq18+"','"  +cq19+"','"  +cq20+"','"  +cq21+"','"  +cq22+"','"  +cq23+"','"  +cq24+"','"  +cq25+"','"  +cq26+"','"  +cq27+"','"  +cq28+"','"  +cq29+"','"  +cq30+"','"  +cq31+"','"  +cq32+"','"  +cq33+"','"  +cq34+"','"  +cq35+"','"  +cq36+"','"  +cq37+"','"  +cq38+"','"  +cq39+"','"  +cq40+"','"  +cq41+"','"  +cq42+"','"  +cq43+"','"  +cq44+"','"  +cq45+"','"  +cq46+"','"  +cq47+"','"  +cq48+"','"  +cq49+"','"  +cq50+"','"  +cq51+"','"  +cq52+"','"  +cq53+"','"  +cq54+"','"  +cq55+"','"  +cq56+"','"  +cq57+"','"  +cq58+"','"  +cq59+"','"  +cq60+"','"  +cq61		    		  			
		    		  +"')";
              PreparedStatement cqdb1 = connection.prepareStatement(charStatement1);
              cqdb1.executeUpdate();

		      System.out.println("Print after the char db operation");

		      // Updating the form questions scores
		      String statement1 = "INSERT INTO form_eval VALUES ("
		    		  +studentIdInsert
		    		  +",1,"
					  +q1 +","  +q2+","  +q3+","  +q4+","  +q5+","  +q6+","  +q7+","  +q8+","  +q9+","  +q10+","  +q11+","  +q12+","  +q13+","  +q14+","  +q15+","  +q16+","  +q17+","  +q18+","  +q19+","  +q20+","  +q21+","  +q22+","  +q23+","  +q24+","  +q25+","  +q26+","  +q27+","  +q28+","  +q29+","  +q30+","  +q31+","  +q32+","  +q33+","  +q34+","  +q35+","  +q36+","  +q37+","  +q38+","  +q39+","  +q40+","  +q41+","  +q42+","  +q43+","  +q44+","  +q45+","  +q46+","  +q47+","  +q48+","  +q49+","  +q50+","  +q51+","  +q52+","  +q53+","  +q54+","  +q55+","  +q56+","  +q57+","  +q58+","  +q59+","  +q60+","  +q61+","  +q62	    		  			
							+")";
		      
		      
              PreparedStatement q = connection.prepareStatement(statement1);
              q.executeUpdate();
		      System.out.println("Print regula db data done");

		      return "db";
		    } catch (Exception e) {
		    	System.out.println(e);
		      return "error";
		    }
		  }
	  
	  private String rawScoredbUpdate(int studentIdInsert, int s1, int s2,int s3, int s4, int s5, int s6, int s7, int s8, int s9 ) {
		  // SCO_RS, VIS_RS, HEA_RS, TOU_RS, TNS_RS, BOD_RS, BAL_RS, PLA_RS, FULL_RS
		    try (Connection connection = dataSource.getConnection()) {
		      System.out.println("******In DB execution rawScoredbUpdate *****");	      
		      String statement1 = "INSERT INTO eval_rawscore VALUES ("
		      		+ studentIdInsert
		      		+ ",1,"
		    		  +s1 +"," +s2 +"," +s3 +"," +s4 +"," +s5 +"," +s6 +"," +s7 +"," +s8 +"," +s9
		              +")";
		          PreparedStatement q = connection.prepareStatement(statement1);
		          q.executeUpdate();
		      return "db";
		    } catch (Exception e) {
		      System.out.println(e);
		      return "error";
		    }
		  }
		
	  private String otherScoredbUpdate(int studentIdfromUI, String scoreType, int tscr1, int tscr2,int tscr3, int tscr4, int tscr5, int tscr6, int tscr7, int tscr8) {
		    try (Connection connection = dataSource.getConnection()) {
		      System.out.println("******In DB execution T Score  *****");
		      String statement1 = "INSERT INTO total_scores VALUES ("
		      		  + studentIdfromUI
		      		  + ",1,'"
		    		  +scoreType +"',"
		    		  +tscr1 +"," +tscr2 +"," +tscr3 +"," +tscr4 +"," +tscr5 +"," +tscr6 +"," +tscr7 +"," +tscr8
		              +")";
		          PreparedStatement q = connection.prepareStatement(statement1);
		          q.executeUpdate();
		      return "db";
		    } catch (Exception e) {
		      System.out.println(e);
		      return "error";
		    }
		  }
	  
	  private String indexScoredbUpdate(int studentIdfromUI, String scoreNType, double inxsc1, double inxsc2,double inxsc3, double inxsc4, double inxsc5, double inxsc6, double inxsc7, double inxsc8) {
		    try (Connection connection = dataSource.getConnection()) {
		      System.out.println("******In DB execution scoreNType  *****  " +scoreNType);
		      String statement1 = "INSERT INTO total_scores VALUES ("
		      		+ studentIdfromUI
		      		+ ",1,'"
		    		  +scoreNType +"',"
		    		  +inxsc1 +"," +inxsc2 +"," +inxsc3 +"," +inxsc4 +"," +inxsc5 +"," +inxsc6 +"," +inxsc7 +"," +inxsc8
		              +")";
		          PreparedStatement q = connection.prepareStatement(statement1);
		          q.executeUpdate();
		      return "db";
		    } catch (Exception e) {
		      System.out.println(e);
		      return "error";
		    }
		  }
		
	  
//		@Bean
//		  public DataSource dataSource() throws SQLException {
//		      System.out.println("******In line 62*****" +dbUrl);
//			  if (dbUrl == null || dbUrl.isEmpty()) {
//		      return new HikariDataSource();
//		    } else {
//		      HikariConfig config = new HikariConfig();
//		      config.setJdbcUrl(dbUrl);
//		      System.out.println("******In line 87*****" +dbUrl);
//		      return new HikariDataSource(config);
//		    }
//		  }
}
