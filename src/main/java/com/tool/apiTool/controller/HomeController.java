package com.tool.apiTool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * The HomeController is a main controller for application
 *
 * @author Atinder
 * @version 1.0
 * @since 2018-09-10
 */
@Controller
public class HomeController {
	 @RequestMapping(value="/home", method = RequestMethod.GET)
	    public String showLoginPage(ModelMap model){
	        return "home";
	    }
	 
	 @RequestMapping(value="/", method = RequestMethod.GET)
	    public String test(ModelMap model){
	        return "home";
	    }
}
