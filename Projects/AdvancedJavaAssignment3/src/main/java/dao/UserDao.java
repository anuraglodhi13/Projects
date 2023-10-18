package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Product;
import model.User;
import utilities.HibernateUtil;

public class UserDao {

	public List<User> getUserList() {
		Transaction tx = null;
		Session session = null;
		List UserList = null;
		try {

			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			UserList = session.createQuery("from User").list();

		} catch (HibernateException exception) {
			if (tx != null)
				tx.rollback();
		} finally {
			session.close();
		}
		return UserList;
	}

	
}
