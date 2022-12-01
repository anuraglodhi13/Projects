package com.nagarro.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.constants.Constants;
import com.nagarro.dao.TshirtDao;
import com.nagarro.service.LoginService;

@Controller

public class LoginController  {
	@RequestMapping("/login")
	private String login (HttpServletRequest request, HttpServletResponse response,
			@RequestParam(Constants.USERNAME) String username, 
			@RequestParam(Constants.PASSWORD) String password) 
					throws IOException, ServletException {
		
		request.setAttribute(Constants.USERNAME, username);
		
		ApplicationContext factory = new ClassPathXmlApplicationContext(
		        Constants.TSHIRT_XML_FILE);
		((AbstractApplicationContext) factory).refresh();
		LoginService loginService = factory.getBean(LoginService.class);
		
		String loginString = loginService.validateUser(username, password);
		HttpSession session = request.getSession();
		
		if (loginString.equals(Constants.USERNAME_AND_PASSWORD)) {
			session.setAttribute(Constants.USERNAME, username);
			return Constants.TSHIRT_JSP_FILE;
		}
		else if(loginString.equals("")) {
			request.setAttribute("both-not-found","true");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		return Constants.LOGIN_JSP_FILE;
	}
}
