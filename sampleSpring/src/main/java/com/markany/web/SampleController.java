package com.markany.web;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.markany.serviceImp.SampleServiceImp;

@Controller
public class SampleController {
	

	@RequestMapping(value = "/sample.do", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		
		String keyString=request.getParameter("keyString");
		
		SampleServiceImp sampleServiceImp = new SampleServiceImp();
		model.addAttribute("result", sampleServiceImp.selectDataResult(keyString));

		return "home";
	}
	
}
