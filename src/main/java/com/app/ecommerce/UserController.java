package com.app.ecommerce;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServices userService;
//    private List<User> userList = new ArrayList<>();

    @GetMapping("/api/users")
    //response entity make uniformitiy
    public ResponseEntity<List<User>> getAllUsers() {
//        HttpStatus.
        return new ResponseEntity<>(userService.fetchAllUsers(),HttpStatus.OK);
    }
    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userService.fetchUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());

//        User user=userService.fetchUser(id);
//        if(user==null){
//            return ResponseEntity.notFound().build();
//        }
//        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @PostMapping("/api/users")
    public ResponseEntity<String> CreateUsers(@RequestBody User user) {
        userService.addUsers(user);
        return ResponseEntity.ok( "user added successfully");
    }
    @PutMapping("/api/users/{id}")
    public ResponseEntity<String> UpdateUsers(@PathVariable Long id,@RequestBody User updatedUser) {
        boolean updated=userService.UpdateUsers(updatedUser, id);
        if (updated){
            return ResponseEntity.ok( "user added successfully");

        }
        return ResponseEntity.notFound().build();
    }

}
