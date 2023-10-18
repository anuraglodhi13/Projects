package com.nagarro.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nagarro.constants.Constants;
import com.nagarro.dao.UserDao;
import com.nagarro.model.User;
import com.nagarro.service.LoginService;


@Controller
public class ForgetPasswordController extends HttpServlet{
	@RequestMapping("/forgetPassword")
	protected void doPost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(Constants.USERNAME) String username, 
			@RequestParam(Constants.PASSWORD) String password) throws ServletException, IOException {
		request.setAttribute("password-reset", "false");
		System.out.print(username);
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		ApplicationContext factory = new ClassPathXmlApplicationContext(
		        Constants.TSHIRT_XML_FILE);
		((AbstractApplicationContext) factory).refresh();
		UserDao userDao = factory.getBean(UserDao.class);
		userDao.updatePassword(user);
		request.setAttribute("password-reset", "true");
		request.getRequestDispatcher("forget_password.jsp").forward(request, response);
	}
}
