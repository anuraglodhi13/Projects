package com.nagarro.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nagarro.model.User;
import com.nagarro.utilities.HibernateUtil;



public class UserDao {

	public List<User> getUserList(String username, String password) {
		Transaction tx = null;
		Session session = null;
		List UserList = null;
		try {

			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			String hql = "from User U where U.username = :username and U.password = :password";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            query.setParameter("password", password);
			UserList = query.list();
			session.getTransaction().commit();
		} catch (HibernateException exception) {
			if (tx != null)
				tx.rollback();
		} finally {
			session.close();
		}
		return UserList;
	}
	
	public void updatePassword(User user) {
		Transaction tx = null;
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.merge(user);
			session.getTransaction().commit();
		} catch (HibernateException exception) {
			if (tx != null)
				tx.rollback();
		} finally {
			session.close();
		}
	}

	
}
