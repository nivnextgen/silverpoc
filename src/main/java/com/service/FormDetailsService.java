package com.service;

import org.springframework.stereotype.Service;

@Service
public class FormDetailsService implements IFormDetailsService {

	@Override
	public boolean ansValues(int[] ansValues) {

		for (int num : ansValues) {
			System.out.println("The loop number is" + num);
		}

		return true;

	}

	// Constants dec scores
	// For step 2
	int SCO_RS, VIS_RS, HEA_RS, TOU_RS, TNS_RS, BOD_RS, BAL_RS, PLA_RS, FULL_RS;
	String phase;
	// For step 3
	int tSOCScore, tVISScore, tHEAScore, tTOUScore, tTnSScore, tBODScore, tBALScore, tPLAScore, totFullScore;
	int nivSOC, nivVIS, nivHEA, nivTOU, nivTnS, nivBOD, nivBAL, nivPLA, nivFullScore;
	double engISOC, engIVIS, engIHEA, engITOU, engITnS, engIBOD, engIBAL, engIPLA, engITotal;
	double attISOC, attIVIS, attIHEA, attITOU, attITnS, attIBOD, attIBAL, attIPLA, attITotal;
	double perfIndex;

	// Step 1 Storing all the values in "form_eval"
	public boolean formEval() {

		return true;
	}

	// Step 2 Calculating group raw scores and store in "eval_rawscore"
	public boolean rawScoreEval() {

		FULL_RS = VIS_RS + HEA_RS + TOU_RS + TNS_RS + BOD_RS + BAL_RS;

		return true;
	}

	// Step 3 logic for making total scores and storing them in total_scores
	// Need to eval TSCORE, NIVSCORE

	// Step 3.1 eval TSCORE for all modules
	public boolean tScore() {

			tSOCScore = tScoreRange("SCO_RS",SCO_RS);
			tVISScore= tScoreRange("VIS_RS",VIS_RS);
			tHEAScore= tScoreRange("HEA_RS",HEA_RS);
			tTOUScore= tScoreRange("TOU_RS",TOU_RS);
			tBODScore= tScoreRange("BOD_RS",BOD_RS);
			tBALScore= tScoreRange("BAL_RS",BAL_RS);
			tPLAScore= tScoreRange("PLA_RS",PLA_RS);
			totFullScore= tScoreRange("FULL_RS",FULL_RS);
		
		return true;
	}

	// Step 3.2 eval NIVSCORE for all modules
	public boolean nivScore() {
		
		nivSOC = nivScoreRange(tSOCScore);
		nivVIS = nivScoreRange(tVISScore);
		nivHEA = nivScoreRange(tHEAScore);
		nivTOU = nivScoreRange(tTOUScore);
		nivTnS = nivScoreRange(tTnSScore);
		nivBOD = nivScoreRange(tBODScore);
		nivBAL = nivScoreRange(tBALScore);
		nivPLA = nivScoreRange(tPLAScore);
		nivFullScore = nivScoreRange(totFullScore);
		

		return true;
	}

	// Step 3.3 eval ENGINDX & ATTINDX for all modules
	public boolean indexScores() {

		engISOC = (15.15 * nivSOC) / 100;
		engIVIS = (15.15 * nivVIS) / 100;
		engIHEA = (15.15 * nivHEA) / 100;
		engITOU = (11.36 * nivTOU) / 100;
		engIBOD = (13.63 * nivBOD) / 100;
		engIBAL = (14.40 * nivBAL) / 100;
		engIPLA = (15.15 * nivPLA) / 100;
		engITotal = engISOC + engIVIS + engIHEA + engITOU + engIBOD + engIBAL + engIPLA;

		attISOC = (18.51 * nivSOC) / 100;
		attIVIS = (24.70 * nivVIS) / 100;
		attIHEA = (24.70 * nivHEA) / 100;
		attITOU = (3.7 * nivTOU) / 100;
		attIBOD = (1.23 * nivBOD) / 100;
		attIBAL = (2.47 * nivBAL) / 100;
		attIPLA = (24.70 * nivPLA) / 100;
		attITotal = attISOC + attIVIS + attIHEA + attITOU + attIBOD + attIBAL + attIPLA;

		return true;
	}

	// Step 4 eval performance index
	public double perfIndexScores() {
		perfIndex = (engITotal + engITotal) / 2;

		return perfIndex;
	}
	
	
	// Supporting methods
	
	
	// T score Range
	public int tScoreRange(String scoreType, int SCO_RS) {
		
		if(scoreType.equals("SOC")) {
			switch (SCO_RS) {
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
			switch (VIS_RS) {
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
			switch (HEA_RS) {
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
			switch (TOU_RS) {
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
			switch (BOD_RS) {
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
			switch (BAL_RS) {
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
			switch (PLA_RS) {
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
			switch (FULL_RS) {
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
				if (FULL_RS >= 130 && FULL_RS <= 168)
					return 80; 
				else if (FULL_RS >= 119  && FULL_RS <= 129)
					return 79; 
				else if (FULL_RS >= 109 && FULL_RS <= 114)
					return 76; 
				else if (FULL_RS >= 99 && FULL_RS <=  107)
					return 74; 
				else if (FULL_RS >= 88 && FULL_RS <= 93)
					return 71; 
//				else if (FULL_RS >=  && FULL_RS <= )
//					return 0; 
				break;
			}
		}
		
		return 1;
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
		
		return 1;
		
	}

}
