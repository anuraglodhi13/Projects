package com.nagarro.controller;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.nagarro.constants.Constants;

@Controller
public class HomePageController {

	    @RequestMapping(value = { "/" })
	    public String homePage() throws JsonParseException, JsonMappingException, IOException, ParseException {
	        return Constants.LOGIN_JSP_FILE;
	    }
}
