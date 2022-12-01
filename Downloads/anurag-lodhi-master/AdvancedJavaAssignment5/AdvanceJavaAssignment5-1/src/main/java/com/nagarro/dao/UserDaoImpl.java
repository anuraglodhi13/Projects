package com.nagarro.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User getUser(String username) {
		 Session currentSession = sessionFactory.getCurrentSession();
	     User user = currentSession.get(User.class, username);
	     return user;
	}
}
