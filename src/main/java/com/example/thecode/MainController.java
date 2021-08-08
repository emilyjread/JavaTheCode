package com.example.thecode;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String index() {
		return "index.jsp";
	}
	
	@RequestMapping(value="/trycode", method=RequestMethod.POST)
	public String trycode(@RequestParam (value="code") String code, HttpSession session) {
		
		if(code.equals("bushido")) {
			session.setAttribute("code", "true");
			return "redirect:/code";
		}
		else {
			return "redirect:/codeerror";	
		}	
	}
	
	@RequestMapping("/code")
	public String code(HttpSession session) {
		if (session.getAttribute("code")=="true") {
			return "code.jsp";
		}	
		else {
			return "redirect:/codeerror";
		}
	}
	
	@RequestMapping("/codeerror")
	public String error(RedirectAttributes ra) {
		ra.addFlashAttribute("error", "You must train harder!");	
		return "redirect:/";
	}

}
