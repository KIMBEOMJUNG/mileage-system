package com.triple.mileage.api.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApiCommonFrameController {

    @RequestMapping(value = "/frame/top.do")
	public String top(HttpServletRequest request, Model model) throws Exception {
		HttpSession session = request.getSession();
	    model.addAttribute("id", ""+session.getAttribute("id") ) ;
	    model.addAttribute("nickname", ""+session.getAttribute("nickname") ) ;
		model.addAttribute("point", ""+session.getAttribute("point") ) ;
		return "frame/top";
	}

    @RequestMapping(value = "/frame/side.do")
	public String side(HttpServletRequest request, Model model) throws Exception {
		return "frame/side";
	}
    
}
