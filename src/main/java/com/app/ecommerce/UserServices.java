package com.app.ecommerce;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServices {
    private List<User> userList = new ArrayList<>();
    public List<User> fetchAllUsers() {
        return userList;
    }
    public List<User> addUsers(User user) {
        userList.add(user);
        return userList;
    }
}
