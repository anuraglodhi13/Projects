package com.nagarro.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nagarro.constants.*;

@Controller
public class LogOutController {
	@RequestMapping("/logout")
	private String logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			HttpSession session = request.getSession();
			session.removeAttribute(Constants.USERNAME);
			session.invalidate();
			return Constants.LOGIN_JSP_FILE;
	}
}
