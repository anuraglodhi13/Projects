package com.nagarro.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nagarro.constants.Constants;

@Controller
public class LogoutController {

	@RequestMapping(value ="/logout" , method=RequestMethod.GET)
	private String logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			HttpSession session = request.getSession();
			session.removeAttribute(Constants.USERNAME);
			session.invalidate();
			return Constants.REDIRECT_LOGIN;
	}
}
