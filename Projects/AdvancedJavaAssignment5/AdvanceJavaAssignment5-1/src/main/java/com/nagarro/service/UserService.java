package com.nagarro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.dao.UserDao;
import com.nagarro.model.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDAO;

	@Transactional
	public User getUser(String username) {
		return userDAO.getUser(username);
	}
}
