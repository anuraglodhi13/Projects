package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.Constants;

@WebServlet(name = "Logout", description = "Servlet For Logout", urlPatterns = { "/logout" })
public class LogOutController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String logoutForm = request.getParameter(Constants.LOGOUT);
		if (logoutForm.equals(Constants.LOGOUT)) {
			HttpSession session = request.getSession();
			session.removeAttribute(Constants.USERNAME);
			session.invalidate();
		}
		response.sendRedirect(Constants.LOGIN_JSP_FILE);
	}
}
