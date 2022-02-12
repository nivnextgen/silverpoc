package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.service.IFormDetailsService;

@Controller
public class FormPageController {

	@Autowired
	private IFormDetailsService formDetailsService;

	@GetMapping("/formPage")
	String homeIndexPage(HttpServletRequest request, HttpServletResponse response) {

		return "formPage";
	}

	@PostMapping("/formPage")
	String homepage(HttpServletRequest request, HttpServletResponse response) {

		String studentName = request.getParameter("studentname");

		// SOC
		int q1 = Integer.valueOf(request.getParameter("question1"));
		int q2 = Integer.valueOf(request.getParameter("question2"));
		int q3 = Integer.valueOf(request.getParameter("question3"));
		int q4 = Integer.valueOf(request.getParameter("question4"));
		int q5 = Integer.valueOf(request.getParameter("question5"));
		int q6 = Integer.valueOf(request.getParameter("question6"));
		int q7 = Integer.valueOf(request.getParameter("question7"));
		int q8 = Integer.valueOf(request.getParameter("question8"));
		int q9 = Integer.valueOf(request.getParameter("question9"));
		int q10 = Integer.valueOf(request.getParameter("question10"));

		 // VIS
		int q11 = Integer.valueOf(request.getParameter("question11"));
		int q12 = Integer.valueOf(request.getParameter("question12"));
		int q13 = Integer.valueOf(request.getParameter("question13"));
		int q14 = Integer.valueOf(request.getParameter("question14"));
		int q15 = Integer.valueOf(request.getParameter("question15"));
		int q16 = Integer.valueOf(request.getParameter("question16"));
		int q17 = Integer.valueOf(request.getParameter("question17"));
		
		//HEA
		int q18 = Integer.valueOf(request.getParameter("question18"));
		int q19 = Integer.valueOf(request.getParameter("question19"));
		int q20 = Integer.valueOf(request.getParameter("question20"));
		int q21 = Integer.valueOf(request.getParameter("question21"));
		int q22 = Integer.valueOf(request.getParameter("question22"));
		int q23 = Integer.valueOf(request.getParameter("question23"));
		int q24 = Integer.valueOf(request.getParameter("question24"));
		
		//TOU
		int q25 = Integer.valueOf(request.getParameter("question25"));
		int q26 = Integer.valueOf(request.getParameter("question26"));
		int q27 = Integer.valueOf(request.getParameter("question27"));
		int q28 = Integer.valueOf(request.getParameter("question28"));
		int q29 = Integer.valueOf(request.getParameter("question29"));
		int q30 = Integer.valueOf(request.getParameter("question30"));
		int q31 = Integer.valueOf(request.getParameter("question31"));
		int q32 = Integer.valueOf(request.getParameter("question32"));
		
		
		int q33 = Integer.valueOf(request.getParameter("question33"));
		int q34 = Integer.valueOf(request.getParameter("question34"));
		int q35 = Integer.valueOf(request.getParameter("question35"));
		int q36 = Integer.valueOf(request.getParameter("question36"));
		
		
		int q37 = Integer.valueOf(request.getParameter("question37"));
		int q38 = Integer.valueOf(request.getParameter("question38"));
		int q39 = Integer.valueOf(request.getParameter("question39"));
		int q40 = Integer.valueOf(request.getParameter("question40"));
		int q41 = Integer.valueOf(request.getParameter("question41"));
		int q42 = Integer.valueOf(request.getParameter("question42"));
		int q43 = Integer.valueOf(request.getParameter("question43"));
		
		
		int q44 = Integer.valueOf(request.getParameter("question44"));
		int q45 = Integer.valueOf(request.getParameter("question45"));
		int q46 = Integer.valueOf(request.getParameter("question46"));
		int q47 = Integer.valueOf(request.getParameter("question47"));
		int q48 = Integer.valueOf(request.getParameter("question48"));
		int q49 = Integer.valueOf(request.getParameter("question49"));
		int q50 = Integer.valueOf(request.getParameter("question50"));
		int q51 = Integer.valueOf(request.getParameter("question51"));
		int q52 = Integer.valueOf(request.getParameter("question52"));
		
		
		int q53 = Integer.valueOf(request.getParameter("question53"));
		int q54 = Integer.valueOf(request.getParameter("question54"));
		int q55 = Integer.valueOf(request.getParameter("question55"));
		int q56 = Integer.valueOf(request.getParameter("question56"));
		int q57 = Integer.valueOf(request.getParameter("question57"));
		int q58 = Integer.valueOf(request.getParameter("question58"));
		int q59 = Integer.valueOf(request.getParameter("question59"));
		int q60 = Integer.valueOf(request.getParameter("question60"));
		int q61 = Integer.valueOf(request.getParameter("question61"));
		int q62 = Integer.valueOf(request.getParameter("question62"));

		
		int[] ansSOCValues = {q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,q11,q12,q13,q14,q15,q16,q17,q18,q19,q20,q21,q22,q23,q24,q25,q26,q27,q28,q29,q30,q31,q32,q33,q34,q35,q36,q37,q38,q39,q40,q41,q42,q43,q44,q45,q46,q47,q48,q49,q50,q51,q52,q53,q54,q55,q56,q57,q58,q59,q60,q61,q62};

		formDetailsService.ansValues(ansSOCValues);
		
		return "formPage";

	}

}
