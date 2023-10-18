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

@WebServlet(name = "DeleteProduct", description = "Servlet For DeleteProduct", urlPatterns = { "/ProductDelete" })
public class DeleteProductController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String imageName = request.getParameter(Constants.IMAGE_NAME);
		Integer indexofUnderscroe = imageName.indexOf(Constants.UNDERSCORE);
		String productId = imageName.substring(indexofUnderscroe + 1);

		ProductDao productDao = new ProductDao();
		productDao.deleteProduct(Integer.parseInt(productId));

		List<Product> productList = productDao.getProductList();
		HttpSession session = request.getSession();
		session.setAttribute(Constants.PRODUCT_LIST, productList);
		response.sendRedirect(Constants.PRODUCT_JSP_FILE);
	}
}
