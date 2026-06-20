package com.app.ecommerce;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServices {
    private List<User> userList = new ArrayList<>();
    private Long nextid=1L;
    //adding the user
    public void  addUsers(User user) {
        user.setId(nextid++);
        userList.add(user);
    }
    //fetching the users
    public List<User> fetchAllUsers() {
        return userList;
    }

    public User fetchUser(Long id) {
        for(User user:userList){
            if (user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }
}
