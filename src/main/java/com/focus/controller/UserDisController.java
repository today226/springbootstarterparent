package com.focus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserDisController {

	   @RequestMapping(value = "/frm")
	    public String fromData( Model model) {
	        return "/user/list";
	    }
	    
}
