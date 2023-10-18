package controller;

import java.io.File;
import java.io.FileInputStream;
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
import constants.ProgramDirectoryUtilities;

@WebServlet(name = "EditProduct", description = "Servlet For EditProduct", urlPatterns = { "/ProductEdit" })
public class EditProductController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String imageName = request.getParameter(Constants.IMAGE_NAME);
		String result = imageName.substring(6, imageName.length() - 6);
		Integer indexofUnderscroe = result.indexOf(Constants.UNDERSCORE);
		String productId = result.substring(indexofUnderscroe + 1);
		Boolean isImageNull = false;
		Product product = new Product();
		String title = request.getParameter(Constants.TITLE);
		String quantity = request.getParameter(Constants.QUANTITY);
		String size = request.getParameter(Constants.SIZE);
		if (request.getParameter(Constants.IMAGE_DATA) == "") {
			isImageNull = true;
		} else {
			String savePath;
			savePath = Constants.SAVE_DIR + "\\" + request.getParameter(Constants.IMAGE_DATA);
			File path = new File(savePath);
			FileInputStream fis = new FileInputStream(path);
			byte[] photo = new byte[(int) path.length()];
			fis.read(photo);
			product.setProduct_image(photo);
		}
		product.setId(Integer.parseInt(productId));
		product.setQuantity(quantity);
		product.setSize(size);
		product.setTitle(title);

		ProductDao productdao = new ProductDao();
		productdao.editProduct(product, isImageNull);

		ProductDao productDao = new ProductDao();
		List<Product> productList = productDao.getProductList();
		HttpSession session = request.getSession();
		session.setAttribute(Constants.PRODUCT_LIST, productList);

		response.sendRedirect(Constants.PRODUCT_JSP_FILE);

	}
}
