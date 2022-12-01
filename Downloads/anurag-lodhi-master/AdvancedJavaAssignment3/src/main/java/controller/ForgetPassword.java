package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.Constants;
import dao.ProductDao;
import dao.UserDao;
import model.Product;
import model.User;
import service.LoginService;

@WebServlet(name = "ForgetPassword", description = "Servlet For ForgetPassword", urlPatterns = { "/forgetPassword" })
public class ForgetPassword extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username-forget");
		System.out.print(username);
		UserDao userDao = new UserDao();
		List <User> userList = userDao.getUserList();
		for(User user: userList) {
	    	   if(user.getUsername().equals(username)) {
	    		   request.setAttribute("found-password", user.getPassword());
	    		   request.setAttribute("username-found", "true");
	    		   request.getRequestDispatcher("forget_password.jsp").forward(request, response);
	    	   }
	    }
		request.setAttribute("username-found", "false");
		request.getRequestDispatcher("forget_password.jsp").forward(request, response);
	}
}
