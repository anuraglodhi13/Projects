package com.nagarro.controller;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.nagarro.constants.Constants;
import com.nagarro.model.AuthorDTO;
import com.nagarro.service.RestClientService;

@Controller
public class AuthorController {
	@Autowired
	RestClientService restClientService;
	
	@RequestMapping(value = "/add" , method=RequestMethod.GET)
    public ModelAndView setAuthorList() throws ParseException, JsonParseException, JsonMappingException, IOException {
		List <AuthorDTO> li = restClientService.getAllAuthor();
		ModelAndView mv = new ModelAndView();
		mv.addObject(Constants.AUTHOR_LIST,li);
		mv.setViewName(Constants.ADD_JSP_FILE);
		return mv;
    }
}
