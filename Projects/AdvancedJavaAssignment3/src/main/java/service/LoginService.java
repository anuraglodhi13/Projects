package service;

import java.io.File;
import java.util.List;

import dao.UserDao;
import model.Product;
import model.User;

public class LoginService {
	private UserDao loginDao = null;
	public String validateUser(String username, String password) {
	   String ans = "";
       loginDao = new UserDao();		
       List <User> userList = loginDao.getUserList();
       for(User user: userList) {
    	   if(user.getUsername().equals(username)) {
    		   if(user.getPassword().equals(password)) {
    			   ans = "usernameAndPassword";
    			   return ans;
    		   }
    		   ans = "username";
    		   return ans;
    	   }
    	   if(user.getPassword().equals(password)) {
    		   ans = "password";
    		   return ans;
    	   }
       }
       return ans;
       
    }
}
