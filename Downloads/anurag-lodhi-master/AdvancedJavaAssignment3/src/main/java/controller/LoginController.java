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
import model.Product;
import service.LoginService;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter(Constants.USERNAME);
		String password = request.getParameter(Constants.PASSWORD);
		request.setAttribute(Constants.USERNAME, username);
		LoginService loginService = new LoginService();
		String loginString = loginService.validateUser(username, password);
		if (loginString.equals("usernameAndPassword")) {
			
			ProductDao productDao = new ProductDao();
			List<Product> productList = productDao.getProductList();
			HttpSession session = request.getSession();
			session.setAttribute(Constants.USERNAME, username);
			session.setAttribute(Constants.PRODUCT_LIST, productList);
		
			response.sendRedirect(Constants.PRODUCT_JSP_FILE);
		}
		else if(loginString.equals(Constants.PASSWORD)) {
			request.setAttribute("only-password","true");
			request.getRequestDispatcher(Constants.LOGIN_JSP_FILE).forward(request, response);
		}
		else if(loginString.equals(Constants.USERNAME)) {
			request.setAttribute("only-username","true");
			request.getRequestDispatcher(Constants.LOGIN_JSP_FILE).forward(request, response);
		}
		else if(loginString.equals("")) {
			request.setAttribute("both-not-found","true");
			request.getRequestDispatcher(Constants.LOGIN_JSP_FILE).forward(request, response);
		}
	}
}
