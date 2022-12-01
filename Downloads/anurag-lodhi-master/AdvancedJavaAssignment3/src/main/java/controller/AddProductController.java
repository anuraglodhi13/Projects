package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import constants.Constants;
import dao.ProductDao;
import model.Product;

@WebServlet(name = "Product", description = "Servlet For Product", urlPatterns = { "/product" })
@MultipartConfig
public class AddProductController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter(Constants.TITLE);
		String quantity = request.getParameter(Constants.QUANTITY);
		String size = request.getParameter(Constants.SIZE);
		Part filePart = request.getPart(Constants.IMAGE);
		InputStream inputStream = null;

		inputStream = filePart.getInputStream();
		byte[] photo = new byte[inputStream.available()];
		inputStream.read(photo);
		ProductDao productDao = new ProductDao();
		if(StringUtils.isEmpty(title) || StringUtils.isEmpty(quantity) || StringUtils.isEmpty(size) || ArrayUtils.isEmpty(photo)) {
			System.out.print("here");
			List<Product> productList = productDao.getProductList();
			HttpSession session = request.getSession();
			session.setAttribute(Constants.PRODUCT_LIST, productList);

			response.sendRedirect(Constants.PRODUCT_JSP_FILE);
			return;
		}
		Product product = new Product();
		product.setProduct_image(photo);
		product.setQuantity(quantity);
		product.setSize(size);
		product.setTitle(title);
		
		productDao.saveProduct(product);

		List<Product> productList = productDao.getProductList();
		HttpSession session = request.getSession();
		session.setAttribute(Constants.PRODUCT_LIST, productList);

		response.sendRedirect(Constants.PRODUCT_JSP_FILE);
	}
}
