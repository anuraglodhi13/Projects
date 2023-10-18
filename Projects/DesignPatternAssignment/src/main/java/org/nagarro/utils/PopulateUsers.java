package org.nagarro.utils;

import org.nagarro.model.User;

import java.util.ArrayList;
import java.util.List;

public class PopulateUsers {
    public List<User> instantiateUser () {
        List<User> userList = new ArrayList<>();
        User user1 = new User("Sonu",true);
        User user2 = new User("Rishi",false);
        User user3 = new User("Anurag",true);
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        return userList;
    }
}
