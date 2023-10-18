package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Product;
import model.User;
import utilities.HibernateUtil;

public class ProductDao {
	public void saveProduct(Product product) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			session.save(product);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public List<Product> getProductList() {
		Transaction tx = null;
		Session session = null;
		List ProductList = null;
		try {

			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			ProductList = session.createQuery("from Product").list();
			System.out.println(ProductList.size());
		} catch (HibernateException exception) {
			if (tx != null)
				tx.rollback();
		} finally {
			session.close();
		}
		return ProductList;
	}
	
	public void editProduct(Product product, Boolean isImageNull) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			Product p = (Product)session.get(Product.class,product.getId());
			p.setTitle(product.getTitle());
			p.setQuantity(product.getQuantity());
			p.setSize(product.getSize());
			if(!isImageNull) {
			p.setProduct_image(product.getProduct_image());
			}
			session.update(p);
			transaction.commit();
			
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void deleteProduct(int productId) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			Product p = (Product)session.get(Product.class,productId);
			session.delete(p);
			transaction.commit();
			
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
