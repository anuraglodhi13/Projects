package com.nagarro.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.constants.Constants;
import com.nagarro.model.User;
import com.nagarro.service.UserService;

@Controller

public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	ModelAndView mv;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	private ModelAndView defaultLogin() { // Route To Login Page Withour Username And Password After Logout
		mv.setViewName(Constants.LOGIN_JSP_FILE);
		return mv;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	private String login(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(Constants.USERNAME) String username, @RequestParam(Constants.PASSWORD) String password)
			throws IOException, ServletException {
		User user = userService.getUser(username);
		HttpSession session = request.getSession();
		session.setAttribute(Constants.USERNAME, username);
		if (user != null) {
			if (StringUtils.isNotEmpty(username) && user.getUsername().equals(username)) {
				if (StringUtils.isNotEmpty(password) && user.getPassword().equals(password)) {
					return Constants.REDIRECT_LISTING;
				}
			}
		}
		session.setAttribute("wrong-credentials", "true");
		return Constants.REDIRECT_LOGIN;
	}
}
