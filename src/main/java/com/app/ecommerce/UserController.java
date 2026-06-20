package com.app.ecommerce;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServices userService;
//    private List<User> userList = new ArrayList<>();

    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        return userService.fetchAllUsers();
    }
    @PostMapping("/api/users")
    public String CreateUsers(@RequestBody User user) {
        userService.addUsers(user);
        return "user added suc";
    }
}
